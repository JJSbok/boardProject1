package org.zerock.board.dto;


import java.time.LocalDate;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {
    

    private Long rno;

    
    private Long bno;
    
    private String reply;
    
    private String replyer;

    @Builder.Default
    private Long gno = 0L;

    private int step;

    private LocalDate replyDate;

}
