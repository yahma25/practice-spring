package com.yahma.movie_review.service;

import com.yahma.movie_review.dto.ReplyDTO;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReplyServiceTests {

  @Autowired
  private ReplyService service;

  @Test
  public void testGetList() {
    Long bno = 76L;

    List<ReplyDTO> replyDTOList = service.getList(bno);

    replyDTOList.forEach(replyDTO -> System.out.println(replyDTO));
  }
}
