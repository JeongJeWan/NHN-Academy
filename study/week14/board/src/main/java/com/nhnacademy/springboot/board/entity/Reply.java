package com.nhnacademy.springboot.board.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BoardReplies")
public class Reply {
    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name="reply_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Post parent;

    @Column(name="bundle_id")
    private Long bundleId;

    @Column(name = "bundle_order")
    private int bundleOrder;
}
