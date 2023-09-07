package com.nhnacademy.springmvcboard.repository;

import com.nhnacademy.springmvcboard.entity.EntityPost;
import com.nhnacademy.springmvcboard.entity.QEntityPost;
import com.nhnacademy.springmvcboard.entity.QEntityUser;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class EntityPostRepositoryImpl extends QuerydslRepositorySupport implements EntityPostRepositoryCustomer {

    public EntityPostRepositoryImpl(){
        super(EntityPost.class);
    }
    @Override
    public List<EntityPost> getEntityPostsWithAssociation() {
        QEntityPost entityPost = QEntityPost.entityPost;
        QEntityUser entityUser = QEntityUser.entityUser;
        return from(entityPost)
                .leftJoin(entityPost.user, entityUser).fetchJoin()
                .fetch();
    }
}
