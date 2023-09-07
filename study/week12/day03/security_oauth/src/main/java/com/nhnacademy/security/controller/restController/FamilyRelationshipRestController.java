package com.nhnacademy.security.controller.restController;

import com.nhnacademy.security.dto.familyRelationship.RelationshipDto;
import com.nhnacademy.security.dto.familyRelationship.RelationshipRegisterRequest;
import com.nhnacademy.security.dto.familyRelationship.RelationshipUpdateRequest;
import com.nhnacademy.security.service.familyRelationship.FamilyRelationshipService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/residents/{serialNumber}/relationship", produces = "application/json;")
public class FamilyRelationshipRestController {

    private final FamilyRelationshipService familyRelationshipService;

    public FamilyRelationshipRestController(FamilyRelationshipService familyRelationshipService) {
        this.familyRelationshipService = familyRelationshipService;
    }

    @GetMapping
    public List<RelationshipDto> getFamilyRelationship(@PathVariable("serialNumber") Long serialNumber) {
        return familyRelationshipService.getRelationships(serialNumber);
    }

    @PostMapping
    public RelationshipDto registerRelationship(@PathVariable("serialNumber") Long baseSerialNumber,
                                                @RequestBody RelationshipRegisterRequest request) {
        return familyRelationshipService.registerRelationshipAndGet(baseSerialNumber, request);
    }

    @PutMapping("/{familySerialNumber}")
    public RelationshipDto updateFamilyRelationship(@PathVariable("serialNumber") Long baseSerialNumber, @PathVariable("familySerialNumber") Long familySerialNumber, @RequestBody RelationshipUpdateRequest request) {
        familyRelationshipService.modifyRelationship(baseSerialNumber, familySerialNumber, request);
        return familyRelationshipService.getRelationship(baseSerialNumber, familySerialNumber);
    }

    @DeleteMapping("/{familySerialNumber}")
    public void deleteFamilyRelationship(@PathVariable("serialNumber") Long baseSerialNumber, @PathVariable("familySerialNumber") Long familySerialNumber) {
        familyRelationshipService.deleteRelationship(baseSerialNumber, familySerialNumber);
    }
}
