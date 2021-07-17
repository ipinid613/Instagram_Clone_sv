package com.sparta.instagram_clone_sv.service;

import com.sparta.instagram_clone_sv.dto.ArticleLikeRequestDto;
import com.sparta.instagram_clone_sv.model.ArticleLike;
import com.sparta.instagram_clone_sv.repository.ArticleLikeRepository;
import com.sparta.instagram_clone_sv.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

////// 좋아요 눌렀는지 안 눌렀는지에 따라 ture / false 반환하는 코드 뼈대입니다 //////
////// 기본적인 흐름은 = 컨트롤러에서 articleLike(서비스) 호출 -> articleLike 서비스에서 작업 시작//////
// -> 우선 게시글 id로 해당 게시글이 있는지 여부 탐색 / 없을 시 NPE 날리기
// -> 게시글 id에 일치하는 게시글이 있다면 optional.ofnullable로 null 여부 확인 / null이 아닐 경우 '좋아요를 누른 상태'이므로 if문으로 들어가서 로직 처리 후 false 반환
// -> null일 경우 '좋아요를 누르지 않은 상태'이므로 로직 처리 후 true를 반환
////// 빨간줄이 많을건데 아직 완성된 코드가 아니어서 그렇습니다 //////
////// 수정하실 부분 수정해도 좋습니다.
////// 저는 사실 이 ArticleLikeService와 연계되는 ArticleLikeRepository와 ArticleLike 클래스 작성할 때 좀 어렵던데 혹시 아이디어 있으시면 작성하시고 알려주셔도 좋습니다 ㅠㅠ.

@RequiredArgsConstructor
@Service
public class ArticleLikeService {

    private final ArticleLikeRepository articleLikeRepository;
    private final ArticleRepository articleRepository;

    @Transactional
    public Map<String, Boolean> articleLike(ArticleLikeRequestDto articleLikeRequestDto) {
        Map<String, Boolean> articleLikeMap = new HashMap<>();
        if (articleRepository.findById(articleLikeRequestDto.getArticleId()).isPresent()) {
            Optional<ArticleLike> isArticleLike = Optional.ofNullable(articleLikeRepository.findByUsernameAndArticleId(articleLikeRequestDto.getUsername(), articleLikeRequestDto.getArticleId()));
            if (isArticleLike.isPresent()) {
                articleLikeRepository.deleteByUsernameAndArticleId(articleLikeRequestDto.getUsername(), articleLikeRequestDto.getArticleId());
                articleLikeMap.put("articleLike", false);
            } else {
                ArticleLike articleLike = new ArticleLike(articleLikeRequestDto);
                articleLikeRepository.save(articleLike);
                articleLikeMap.put("articleLike", true);
            }
        } else {
            throw new NullPointerException("id가" + articleLikeRequestDto.getArticleId() + "인 게시글이 존재하지 않습니다.");
        }
        return articleLikeMap;
    }
}
