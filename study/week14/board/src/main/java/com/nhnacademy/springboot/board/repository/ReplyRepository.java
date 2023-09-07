package com.nhnacademy.springboot.board.repository;

import com.nhnacademy.springboot.board.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
