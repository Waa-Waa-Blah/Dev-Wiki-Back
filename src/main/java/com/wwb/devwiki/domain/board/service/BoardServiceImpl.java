package com.wwb.devwiki.domain.board.service;

import com.wwb.devwiki.domain.board.domain.Board;
import com.wwb.devwiki.domain.board.dto.BoardReqDto;
import com.wwb.devwiki.domain.board.repository.BoardRepository;
import com.wwb.devwiki.domain.member.domain.Member;
import com.wwb.devwiki.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final MemberService memberService;

    @Override
    @Transactional
    public Board writeBoard(BoardReqDto boardReqDto, Long loginUserId) {
        Member member = memberService.findMemberById(loginUserId);
        Board board = boardReqDto.toBoard();
        board.addAuthor(member);
        return boardRepository.save(board);
    }

    @Override
    public Board findBoardById(Long id) {
        return boardRepository.findById(id).get();
    }

    @Override
    public List<Board> findBoards() {
        return boardRepository.findAll();
    }
}
