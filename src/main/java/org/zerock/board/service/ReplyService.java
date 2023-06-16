package org.zerock.board.service;

import org.springframework.transaction.annotation.Transactional;
import org.zerock.board.dto.PageRequsetDTO;
import org.zerock.board.dto.PageResponseDTO;
import org.zerock.board.dto.ReplyDTO;


@Transactional
public interface ReplyService {

    Long register(ReplyDTO replyDTO);

    PageResponseDTO<ReplyDTO> getList(Long bno, PageRequsetDTO pageRequestDTO);

    int delete(Long rno);

    int update(ReplyDTO replyDTO);
  
    
}
