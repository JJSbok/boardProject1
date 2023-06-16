package org.zerock.board.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.board.dto.PageRequsetDTO;
import org.zerock.board.dto.ReplyDTO;

public interface ReplyMapper {

    int insert(ReplyDTO replyDTO );

    int updateGno (Long rno);

    int insertChild (ReplyDTO replyDTO);

    List<ReplyDTO> selectList(
    @Param("bno")Long bno, 
    @Param("pr")PageRequsetDTO pageRequestDTO);
    
    @Select("select count(rno) from tbl_reply where bno =#{bno}")
  int getBnoCount(Long bno);

    @Delete("delete from tbl_reply where rno = #{rno}")
    int delete(Long rno);

    
    int update(ReplyDTO replyDTO);

}
