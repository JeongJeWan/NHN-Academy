package com.nhnacademy.springmvcboard.repository;

import com.nhnacademy.springmvcboard.entity.EntityUser;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface EntityUserRepositoryCustomer {

    List<EntityUser> getEntityUsersWithAssociation();
}
