package com.yahma.movie_review.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReplyDTO {

  private Long rno;

  private String text;

  private String replier;

  private Long bno;

  private LocalDateTime regDate, modDate;
}
