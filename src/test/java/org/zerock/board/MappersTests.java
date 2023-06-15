package org.zerock.board;

import java.util.List;

import org.apache.catalina.connector.Request;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.PageRequsetDTO;
import org.zerock.board.mappers.BoardMapper;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MappersTests {
    @Autowired(required = false)
    private BoardMapper boardMapper;
    
    @Test
    public void listTest() {
        PageRequsetDTO requsetDTO = PageRequsetDTO.builder().build();
        List<BoardDTO> test = boardMapper.list(requsetDTO);
        // log.info("listTest....", test);
         test.forEach(todo -> log.info(todo));
    }

    @Transactional
    @Commit
    @Test
    public void insertTest() {
        BoardDTO board = BoardDTO.builder()
        .writer("asdf")
        .title("fghj")
        .content("zxcv")
        .build();
        int result = 0;
        result= boardMapper.insert(board);
        
        if(result == 1){
            log.info(">>>>> 삽입성공");
        }else if (result == 0){
            log.info("<<<<< 실패");
        }
        log.info(">>>>>>" + result);

    }

    @Test
    public void readTest() {
        BoardDTO board = boardMapper.selectOne(20);
        log.info(board);

    }

    @Test
    public void delete(){
        int bno = 20;
        int result = boardMapper.delete(bno);
        log.info(">>>>>>" + result);
    }

    @Test
    @Transactional
    @Commit
    public void modTest(){
        BoardDTO boardDTO = BoardDTO.builder()
        .bno(16L)
        .writer("teestwwww")
        .title("testttt")
        .content("testccc")
        .build();

        int result = boardMapper.update(boardDTO);
        log.info(">>>>>>>" + result);
    }
}
