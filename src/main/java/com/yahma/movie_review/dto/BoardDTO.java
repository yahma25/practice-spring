package com.yahma.movie_review.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

  private Long bno;

  private String title;

  private String content;

  private String writerEmail;

  private String writerName;

  // html에서 사용하는 형식과 같아야 String -> LocalDateTime 변환됨
  @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
  private LocalDateTime regDate;

  @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
  private LocalDateTime modDate;

  private int replyCount;
}
