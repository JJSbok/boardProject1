package org.zerock.board.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.PageRequsetDTO;
import org.zerock.board.dto.PageResponseDTO;

@Transactional
public interface BoardService {

PageResponseDTO<BoardDTO> getList(PageRequsetDTO requsetDTO);

int register(BoardDTO boardDTO);

BoardDTO selectOne(long bno);

int delete(long bno);

int modify(BoardDTO boardDTO);


}


