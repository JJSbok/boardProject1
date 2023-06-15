package org.zerock.board.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.PageRequsetDTO;

@Mapper
public interface BoardMapper {
    List<BoardDTO> list(PageRequsetDTO requsetDTO);

    int insert(BoardDTO boardDTO);

    BoardDTO selectOne(long bno);

    int delete(long bno);

    int update(BoardDTO boardDTO);

    long listCount(PageRequsetDTO pageRequestDTO);
}
