package com.nhnacademy.springmvcboard.repository;

import com.nhnacademy.springmvcboard.entity.EntityPost;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface EntityPostRepositoryCustomer {
    List<EntityPost> getEntityPostsWithAssociation();
}
