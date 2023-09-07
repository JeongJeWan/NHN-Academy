package com.nhnacademy.springmvcboard.repository;

import com.nhnacademy.springmvcboard.entity.EntityUser;
import com.nhnacademy.springmvcboard.entity.QEntityPost;
import com.nhnacademy.springmvcboard.entity.QEntityUser;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class EntityUserRepositoryImpl extends QuerydslRepositorySupport implements EntityUserRepositoryCustomer {
    public EntityUserRepositoryImpl() {
        super(EntityUser.class);
    }

    @Override
    public List<EntityUser> getEntityUsersWithAssociation() {
        QEntityUser entityUser = QEntityUser.entityUser;
        QEntityPost entityPost = QEntityPost.entityPost;
        return from(entityUser)
                .leftJoin(entityUser.post, entityPost).fetchJoin()
                .fetch();
    }
}
