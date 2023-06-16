package org.zerock.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.board.dto.PageRequsetDTO;
import org.zerock.board.dto.PageResponseDTO;
import org.zerock.board.dto.ReplyDTO;
import org.zerock.board.mappers.BoardMapper;
import org.zerock.board.mappers.ReplyMapper;

import groovy.util.logging.Log4j2;
import lombok.RequiredArgsConstructor;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

  private final ReplyMapper replyMapper;
  private final BoardMapper boardMapper;
  @Override
  public Long register(ReplyDTO replyDTO) {

    Long result = null;
    Long gno = replyDTO.getGno();

    if(gno == 0L){
      int count = replyMapper.insert(replyDTO);

      if(count != 1){
        throw new RuntimeException("Reply Insert Exception");
      }

      Long rno = replyDTO.getRno();
      replyMapper.updateGno(rno);
      result = rno;
    }else {
      int count = replyMapper.insertChild(replyDTO);

      if(count != 1){
        throw new RuntimeException("Reply Insert Exception");
      }
      result = replyDTO.getRno();
    }

    return result;
  }
  @Override
  public PageResponseDTO<ReplyDTO> getList(Long bno, PageRequsetDTO pageRequestDTO) {
    
    pageRequestDTO.setSize(100);

    List<ReplyDTO> list = replyMapper.selectList(bno, pageRequestDTO);
    int total = replyMapper.getBnoCount(bno);

    return PageResponseDTO.<ReplyDTO>withAll()
    .list(list)
    .total(total)
    .build();
  }


@Override
public int delete(Long rno) {

    return replyMapper.delete(rno);
}
@Override
public int update(ReplyDTO replyDTO) {
   

    return replyMapper.update(replyDTO);
}

//   @Override
//   public ReplyDTO getOne(Long bno) {
    
//     return replyMapper.selectOne(bno);
//   }

}
