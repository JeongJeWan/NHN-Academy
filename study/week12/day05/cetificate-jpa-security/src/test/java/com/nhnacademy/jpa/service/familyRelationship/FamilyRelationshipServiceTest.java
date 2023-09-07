package com.nhnacademy.jpa.service.familyRelationship;

import com.nhnacademy.jpa.config.RootConfig;
import com.nhnacademy.jpa.config.WebConfig;
import com.nhnacademy.jpa.dto.familyRelationship.RelationshipDto;
import com.nhnacademy.jpa.dto.familyRelationship.RelationshipRegisterRequest;
import com.nhnacademy.jpa.dto.familyRelationship.RelationshipUpdateRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class FamilyRelationshipServiceTest {

    @Autowired
    FamilyRelationshipService familyRelationshipService;

    @Test
    void getRelationship() {
        RelationshipDto relationshipDto = familyRelationshipService.getRelationship(4L, 7L);

        System.out.println(relationshipDto);

        assertThat(relationshipDto.getFamilyRelationshipCode()).isEqualTo("자녀");
    }

    @Test
    void getRelationships() {
        List<RelationshipDto> relationshipDto = familyRelationshipService.getRelationships(4L);
        relationshipDto.stream().forEach(System.out::println);

        assertThat(relationshipDto).hasSize(4);
    }

    @Test
    void registerRelationshipAndGet() {
        RelationshipRegisterRequest request = new RelationshipRegisterRequest();
        request.setFamilySerialNumber(8L);
        request.setRelationship("자녀");

        RelationshipDto relationshipDto = familyRelationshipService.registerRelationshipAndGet(4L, request);

        System.out.println(relationshipDto);

        assertThat(relationshipDto.getFamilyRelationshipCode()).isEqualTo("자녀");
    }

    @Test
    void modifyRelationship() {
        RelationshipRegisterRequest request = new RelationshipRegisterRequest();
        request.setFamilySerialNumber(8L);
        request.setRelationship("자녀");

        familyRelationshipService.registerRelationshipAndGet(4L, request);

        RelationshipUpdateRequest request1 = new RelationshipUpdateRequest();
        request1.setRelationship("강아지");

        familyRelationshipService.modifyRelationship(4L, 8L, request1);

        assertThat(familyRelationshipService.getRelationship(4L, 8L).getFamilyRelationshipCode()).isEqualTo("강아지");
    }
}