package com.nhnacademy.jpa.controller.restController;

import com.nhnacademy.jpa.dto.resident.ResidentCreateRequest;
import com.nhnacademy.jpa.dto.resident.ResidentDto;
import com.nhnacademy.jpa.dto.resident.ResidentUpdateRequest;
import com.nhnacademy.jpa.entity.Resident;
import com.nhnacademy.jpa.service.resident.ResidentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/residents", produces = "application/json;")
public class ResidentRestController {

    private final ResidentService residentService;

    public ResidentRestController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @GetMapping("/{serialNumber}")
    public ResidentDto getResident(@PathVariable("serialNumber") Long serialNumber) {
        return residentService.getResident(serialNumber);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Resident createResident(@RequestBody ResidentCreateRequest residentCreateRequest) {
        return residentService.createResident(residentCreateRequest);
    }

    @PutMapping("/{serialNumber}")
    public ResidentDto updateResident(@PathVariable("serialNumber") Long serialNumber, @RequestBody ResidentUpdateRequest residentUpdateRequest) {
        return residentService.updateResident(serialNumber, residentUpdateRequest);
    }

    @DeleteMapping("/{serialNumber}")
    public void deleteResident(@PathVariable("serialNumber") Long serialNumber) {
        residentService.deleteResident(serialNumber);
    }
}
