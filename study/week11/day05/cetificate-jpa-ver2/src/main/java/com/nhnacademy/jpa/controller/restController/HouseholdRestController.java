package com.nhnacademy.jpa.controller.restController;

import com.nhnacademy.jpa.dto.household.HouseholdCreateRequest;
import com.nhnacademy.jpa.dto.household.HouseholdDto;
import com.nhnacademy.jpa.service.household.HouseholdService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/household", produces = "application/json;")
public class HouseholdRestController {

    private final HouseholdService householdService;

    public HouseholdRestController(HouseholdService householdService) {
        this.householdService = householdService;
    }

    @PostMapping
    public HouseholdDto createHousehold(@RequestBody HouseholdCreateRequest householdCreateRequest) {
        return householdService.registerHousehold(householdCreateRequest);
    }

    @DeleteMapping("/{householdSerialNumber}")
    public void deleteHousehold(@PathVariable("householdSerialNumber") Long householdSerialNumber) {
        householdService.deleteHousehold(householdSerialNumber);
    }
}
