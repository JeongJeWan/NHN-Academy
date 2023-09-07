package com.nhnacademy.security.repository.resident;

import com.nhnacademy.security.dto.resident.ResidentDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface ResidentRepositoryCustom {
    List<ResidentDto> getResidents();
    ResidentDto getResident(Long residentSerialNumber);
}
