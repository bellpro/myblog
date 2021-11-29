package com.bellpro.myblog.service;

import com.bellpro.myblog.domain.Board;
import com.bellpro.myblog.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor    // 의존성 주입, final 필드에 대해 생성자를 생성,  @Autowired 대신 사용
@Service    // Service 명시(@Component 포함): Bean 등록
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional  // 트랜잭션 처리
    public List<Board> searchBoard(String keyword){
        return boardRepository.findByTitleContaining(keyword);
    }


}
