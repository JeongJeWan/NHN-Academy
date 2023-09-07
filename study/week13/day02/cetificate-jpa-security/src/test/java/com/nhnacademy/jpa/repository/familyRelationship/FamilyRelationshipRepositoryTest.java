package com.nhnacademy.jpa.repository.familyRelationship;

import com.nhnacademy.jpa.config.RootConfig;
import com.nhnacademy.jpa.config.WebConfig;
import com.nhnacademy.jpa.dto.familyRelationship.RelationshipDto;
import com.nhnacademy.jpa.entity.FamilyRelationship;
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
public class FamilyRelationshipRepositoryTest {

    @Autowired
    private FamilyRelationshipRepository familyRelationshipRepository;

    @Test
    void updateRelationship() {
        familyRelationshipRepository.updateRelationship(4L, 7L, "강아지");


    }

    @Test
    void deleteRelationship() {
        familyRelationshipRepository.deleteRelationship(4L, 7L);
    }

    @Test
    void getFatherRelationship() {
        FamilyRelationship familyRelationship = familyRelationshipRepository.getFatherRelationship(4L);

        assertThat(familyRelationship.getFamilyResident().getName()).isEqualTo("남석환");
    }

    @Test
    void getMotherRelationship() {
        FamilyRelationship familyRelationship = familyRelationshipRepository.getMotherRelationship(4L);

        assertThat(familyRelationship.getFamilyResident().getName()).isEqualTo("박한나");
    }
}