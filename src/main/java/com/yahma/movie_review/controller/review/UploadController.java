package com.yahma.movie_review.controller.review;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.yahma.movie_review.dto.review.UploadResultDTO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

@RestController
@Log4j2
public class UploadController {

    @Value("${com.yahma.movie_review.upload.path}") // application.properties 업로드 경로 변수
    private String uploadPath;
    
    /**
     * 파일 업로드
     * - 요청하는 쪽에서 파라미터 key 이름과 동일하게 @RequestParam("파라미터 키 이름") 설정 필요
     */
    @PostMapping("/uploadAjax")
    public ResponseEntity<List<UploadResultDTO>> uploadFile(@RequestParam("uploadFiles") MultipartFile[] uploadFiles) {
        
        List<UploadResultDTO> resultDTOList = new ArrayList<>();

        for (MultipartFile uploadFile : uploadFiles) {
            
            // 이미지 파일만 업로드 가능
            if (!uploadFile.getContentType().startsWith("image")) {
                log.info("This file is not image type");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            
            // 파일 원본 이름. IE/Edge는 전체 경로가 들어옴
            String originalName = uploadFile.getOriginalFilename();
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

            log.info("fileName: " + fileName);

            String folderPath = makeFolder();
            log.info("folderPath: " + folderPath);

            String uuid = UUID.randomUUID().toString();

            String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;
            log.info("saveName: " + saveName);

            Path savePath = Paths.get(saveName);
            log.info("savePath: " + savePath);

            try {
                // 원본 저장
                uploadFile.transferTo(savePath);

                // 썸네일 생성
                String thumbnailSaveName = uploadPath + File.separator 
                                        + folderPath + File.separator + "s_" + uuid + "_" + fileName;

                File thumbnailFile = new File(thumbnailSaveName);

                Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile, 100, 100);

                resultDTOList.add(new UploadResultDTO(fileName, uuid, folderPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new ResponseEntity<List<UploadResultDTO>>(resultDTOList, HttpStatus.OK);
    }

    private String makeFolder() {

        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String folderPath = str.replace("/", File.separator);

        File uploadPathFolder = new File(uploadPath, folderPath);

        if (!uploadPathFolder.exists()) {
            uploadPathFolder.mkdirs();
        }

        return folderPath;
    }

    @GetMapping("/display")
    public ResponseEntity<byte[]> getFile(String fileName) {

        ResponseEntity<byte[]> result = null;

        try {
            String srcFileName = URLDecoder.decode(fileName, "UTF-8");

            log.info("fileName: " + srcFileName);

            File file = new File(uploadPath + File.separator + srcFileName);

            log.info("file: " + file);

            HttpHeaders headers = new HttpHeaders();

            // MIME 타입 처리, Files.probeContentType = 파일의 확장에 따라서 브라우저에 전송하는 MIME 타입 처리
            headers.add("Content-Type", Files.probeContentType(file.toPath()));

            // 파일 데이터 처리
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return result;
    }

    @PostMapping("/removeFile")
    public ResponseEntity<Boolean> removeFile(String fileName) {
        
        String srcFileName = null;
        try {
            srcFileName = URLDecoder.decode(fileName, "UTF-8");
            File file = new File(uploadPath + File.separator + srcFileName);
            boolean result = file.delete();

            File thumbnail = new File(file.getParent(), "s_" + file.getName());

            result = thumbnail.delete();

            return new ResponseEntity<>(result, HttpStatus.OK);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
