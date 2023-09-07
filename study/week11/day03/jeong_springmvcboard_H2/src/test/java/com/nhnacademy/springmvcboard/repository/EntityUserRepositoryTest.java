package com.nhnacademy.springmvcboard.repository;


import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.springmvcboard.config.RootConfig;
import com.nhnacademy.springmvcboard.config.WebConfig;
import com.nhnacademy.springmvcboard.domain.EntityUserDto;
import com.nhnacademy.springmvcboard.entity.EntityPost;
import com.nhnacademy.springmvcboard.entity.EntityUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

//@Disabled("temporary")
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class EntityUserRepositoryTest {

    @Autowired
    EntityUserRepository entityUserRepository;

    @Test
    void test1() {
        EntityUser user1 = entityUserRepository.findByUserId("user1");
        assertThat("1234").isEqualTo(user1.getUserPassword());

        EntityUser user2 = entityUserRepository.findByUserName("jeong");
        assertThat("user2").isEqualTo(user2.getUserId());
    }

    @Test
    void test2() {
        List<EntityUser> users = entityUserRepository.getEntityUsersByCreatedAt(LocalDateTime.now());
        assertThat(users).hasSize(2);
    }

    @Test
    void test3() {
        entityUserRepository.getEntityUsersWithAssociation()
                .stream()
                .map(EntityUser::getPost)
                .flatMap(Collection::stream)
                .map(EntityPost::getUser)
                .collect(Collectors.toList());
    }

    @Test
    void test4() {
        entityUserRepository.readAllBy();
    }

    @Test
    void test5() {
        entityUserRepository.getAllEntityUserWithAssosiation();
    }

}