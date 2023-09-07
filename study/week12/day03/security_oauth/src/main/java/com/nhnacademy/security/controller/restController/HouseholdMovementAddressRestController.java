package com.nhnacademy.security.controller.restController;

import com.nhnacademy.security.dto.householdMovementAddress.HouseholdMovementAddressCreateRequest;
import com.nhnacademy.security.dto.householdMovementAddress.HouseholdMovementAddressDto;
import com.nhnacademy.security.dto.householdMovementAddress.HouseholdMovementAddressUpdateRequest;
import com.nhnacademy.security.service.householdMovementAddress.HouseholdMovementAddressService;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping(value = "/household/{householdSerialNumber}/movement", produces = "application/json;")
public class HouseholdMovementAddressRestController {

    private final HouseholdMovementAddressService householdMovementAddressService;


    public HouseholdMovementAddressRestController(HouseholdMovementAddressService householdMovementAddressService) {
        this.householdMovementAddressService = householdMovementAddressService;
    }

    @PostMapping
    public HouseholdMovementAddressDto createHouseholdMovementAddress(@PathVariable("householdSerialNumber") Long householdSerialNumber, @RequestBody HouseholdMovementAddressCreateRequest householdMovementAddressCreateRequest) {
        return householdMovementAddressService.createHouseholdMovementAddress(householdSerialNumber, householdMovementAddressCreateRequest);
    }

    @PutMapping("/{reportDate}")
    public HouseholdMovementAddressDto updateHouseholdMovementAddress(@PathVariable("householdSerialNumber") Long householdSerialNumber, @PathVariable("reportDate") String reportDate, @RequestBody HouseholdMovementAddressUpdateRequest householdMovementAddressUpdateRequest) throws ParseException {
        return householdMovementAddressService.updateHouseholdMovementAddress(householdSerialNumber, reportDate, householdMovementAddressUpdateRequest);
    }

    @DeleteMapping("/{reportDate}")
    public void deleteHouseholdMovementAddress(@PathVariable("householdSerialNumber") Long householdSerialNumber, @PathVariable("reportDate") String reportDate) throws ParseException {
        householdMovementAddressService.deleteHouseholdMovementAddress(householdSerialNumber, reportDate);
    }
}
