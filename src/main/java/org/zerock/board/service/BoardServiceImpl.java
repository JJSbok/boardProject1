package org.zerock.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.PageRequsetDTO;
import org.zerock.board.dto.PageResponseDTO;
import org.zerock.board.mappers.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {


    private final BoardMapper boardMapper;

    @Override
    public PageResponseDTO<BoardDTO> getList(PageRequsetDTO requsetDTO) {
 

        List<BoardDTO> boardList = boardMapper.list(requsetDTO);
        long total = boardMapper.listCount(requsetDTO);

        return PageResponseDTO.<BoardDTO>withAll()
        .list(boardList)
        .total(total)
        .build();
        
    }

    @Override
    public int register(BoardDTO boardDTO) {
        
        int result = boardMapper.insert(boardDTO);
        return result;
        
    }

    @Override
    public BoardDTO selectOne(long bno) {
        
        BoardDTO board = boardMapper.selectOne(bno);
        return board;
    }

    @Override
    public int delete(long bno) {
        int result = boardMapper.delete(bno);
        return result;
    }

    @Override
    public int modify(BoardDTO boardDTO) {

        int result = boardMapper.update(boardDTO);
        return result;
    }
    
    
}
