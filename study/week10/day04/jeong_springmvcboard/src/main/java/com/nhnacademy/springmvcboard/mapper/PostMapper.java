package com.nhnacademy.springmvcboard.mapper;

import com.nhnacademy.springmvcboard.domain.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    // 등록
    long register(Post post);

    // 게시물 조호
    List<Post> getPosts();

    // 수정
    void modify(Post post);

    // 삭제
    void delete(long id);

    // 게시물 중복 아이디 확인
    boolean exitsById(long id);

    // 해당 아이디 조회
    Post getPostById(long id);
}
