package org.zerock.board.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.PageRequsetDTO;
import org.zerock.board.dto.PageResponseDTO;
import org.zerock.board.service.BoardService;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/board/")
@RequiredArgsConstructor
public class BoardController {
    
    private final BoardService boardService;

    @GetMapping("list")
    
    public void list(Model model,PageRequsetDTO requsetDTO) {

        PageResponseDTO<BoardDTO> board = boardService.getList(requsetDTO);
        model.addAttribute("list", board);

        log.info("LIST......", board);
    }

    @GetMapping("read/{bno}")
    public String read(@PathVariable("bno") Long bno, Model model) {

        BoardDTO board = boardService.selectOne(bno);

        model.addAttribute("board", board);

        log.info("READ....");
        return "board/read";
    }

    @GetMapping("registForm")
    public void registForm(){
        log.info("registForm......");
    }

    @PostMapping("regist")
    public String regist(
        // @RequestParam("writer") String writer,
        // @RequestParam("title") String title,
        // @RequestParam("content") String content){
            BoardDTO board
            ){
        
        
        // BoardDTO board = BoardDTO.builder()
        // .writer(writer)
        // .title(title)
        // .content(content)
        // .build();

        boardService.register(board);

        log.info(board);
        return "redirect:/board/list";
    }

    @PostMapping("delete")
    public String delete(@RequestParam Long bno){
        log.info(">>>>>>>>>> /board/delete");
        
        log.info(bno);
        int result = boardService.delete(bno);
        log.info(result);
        return "redirect:/board/list";
    }

    @PostMapping("modify")
    public String modify(BoardDTO boardDTO, 
        Model model,
        RedirectAttributes rttr){

        int result = boardService.modify(boardDTO);
        model.addAttribute("board", boardDTO);
        rttr.addFlashAttribute("resultFlesh", "SUCCESS");

        log.info(result);
        long bno = boardDTO.getBno();
        log.info(">>>>>>>" + bno);
        return "redirect:/board/read/" + bno;
    }

    @GetMapping("modifyForm/{bno}")
    public String modifyForm(@PathVariable("bno") Long bno, Model model){
        log.info(bno);

        BoardDTO boardDTO  = boardService.selectOne(bno);
        log.info(boardDTO);
        model.addAttribute("board", boardDTO);
        return "board/modifyForm";
    }

        @GetMapping("/add")
    public String add(RedirectAttributes rttr) {
        log.info("add");
        rttr.addFlashAttribute("result", "SUCCESS");
        return "redirect:/sample/resultAdd";
    }



    


}
