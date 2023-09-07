package com.nhnacademy.springboot.board.repository;

import com.nhnacademy.springboot.board.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {
    List<File> findByPost_Id(Long postId);
}
