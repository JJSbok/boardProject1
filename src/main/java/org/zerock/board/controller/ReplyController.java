package org.zerock.board.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.ibatis.annotations.Delete;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.board.dto.PageRequsetDTO;
import org.zerock.board.dto.PageResponseDTO;
import org.zerock.board.dto.ReplyDTO;
import org.zerock.board.service.ReplyService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/replies/")
public class ReplyController {
    

  public static class DataNotFoundException extends RuntimeException{
    
    public DataNotFoundException(String msg){
      super(msg);
    }
  }

  private final ReplyService service;

  @PostMapping("{bno}/new")
  public Map<String, Long> register(@PathVariable("bno") Long bno,
    @Valid @RequestBody ReplyDTO replyDTO, 
    BindingResult bindingResult)throws BindException{

    replyDTO.setBno(bno);

       if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }

    Long rno  = service.register(replyDTO);

    log.info(replyDTO);
    
    return Map.of("result", rno);
  }
 
  @GetMapping(value = "{bno}/list" ,produces = MediaType.APPLICATION_JSON_VALUE )
  public PageResponseDTO<ReplyDTO> getReplyList(
    @PathVariable("bno") Long bno, PageRequsetDTO PageRequsetDTO){
    
    log.info("bno: " + bno);

    return service.getList(bno, PageRequsetDTO);
  }

  @DeleteMapping("delete/{rno}")
  public Map<String, String> delete(@PathVariable("rno") Long rno){

    log.info("----------------------------------");
    log.info(rno);
    int result = service.delete(rno);
    log.info(result);
    return Map.of("delResult", "success");
  }

  @PutMapping("update/{rno}")
  public void update(@PathVariable("rno") Long rno, ReplyDTO replyDTO) {

     service.update(replyDTO);
  }
}
