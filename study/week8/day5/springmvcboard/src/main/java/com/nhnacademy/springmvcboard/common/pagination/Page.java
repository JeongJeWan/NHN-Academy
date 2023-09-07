package com.nhnacademy.springmvcboard.common.pagination;

import java.util.List;

public interface Page<T> {
    //  현재 페이지 번호
    int getPageNumber();
    //  한 페이지에 보여줄 게시물
    int getPageSize();
    //  총 페이지 수
    int getTotalPageCount();

    //  총 게시물 수
    long getTatalCount();
    //  게싯물 목록
    List<T> getList();
}
