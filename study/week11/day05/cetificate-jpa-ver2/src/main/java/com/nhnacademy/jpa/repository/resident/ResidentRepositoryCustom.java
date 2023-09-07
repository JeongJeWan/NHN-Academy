package com.nhnacademy.jpa.repository.resident;

import com.nhnacademy.jpa.dto.resident.ResidentDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface ResidentRepositoryCustom {
    List<ResidentDto> getResidents();
    ResidentDto getResident(Long residentSerialNumber);
}
