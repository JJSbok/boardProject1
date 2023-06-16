package org.zerock.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.board.dto.PageRequsetDTO;
import org.zerock.board.dto.ReplyDTO;
import org.zerock.board.mappers.ReplyMapper;

import lombok.extern.log4j.Log4j2;


@Log4j2
@SpringBootTest
public class ReplyTests {
    
  @Autowired(required = false)
  private ReplyMapper replyMapper;

  @Test
  public void testSelectList() {
    PageRequsetDTO pageRequestDTO = PageRequsetDTO.builder().build();//1,10
    Long bno = 1L;

    replyMapper.selectList(bno, pageRequestDTO)
    .forEach(dto -> log.info(dto));

    }

    @Test
    @Transactional
    @Commit
    public void deleteTest(){
        int rno = 2;
        int result = replyMapper.delete(2L);
        log.info(">>>>>>>>>>" + result);
    }

    @Test
    @Transactional
    @Commit
    public void updateTest() {
        ReplyDTO replyDTO = ReplyDTO.builder()
        .reply("가나다라마바사")
        .rno(14L)
        .build();
        int result = replyMapper.update(replyDTO);
        log.info(">>>>>>>>>>" + result);
    }
}