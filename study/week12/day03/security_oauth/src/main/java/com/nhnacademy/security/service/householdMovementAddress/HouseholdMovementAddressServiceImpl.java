package com.nhnacademy.security.service.householdMovementAddress;

import com.nhnacademy.security.dto.householdMovementAddress.HouseholdMovementAddressCreateRequest;
import com.nhnacademy.security.dto.householdMovementAddress.HouseholdMovementAddressDto;
import com.nhnacademy.security.dto.householdMovementAddress.HouseholdMovementAddressUpdateRequest;
import com.nhnacademy.security.entity.Household;
import com.nhnacademy.security.entity.HouseholdMovementAddress;
import com.nhnacademy.security.repository.household.HouseholdRepository;
import com.nhnacademy.security.repository.householdMovementAddress.HouseholdMovementAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HouseholdMovementAddressServiceImpl implements HouseholdMovementAddressService {

    private final HouseholdMovementAddressRepository householdMovementAddressRepository;
    private final HouseholdRepository householdRepository;

    @Override
    public HouseholdMovementAddressDto createHouseholdMovementAddress(Long householdSerialNumber, HouseholdMovementAddressCreateRequest request) {

        HouseholdMovementAddress householdMovementAddress = new HouseholdMovementAddress();
        householdMovementAddress.setPk(new HouseholdMovementAddress.Pk(
                householdSerialNumber, request.getHouseMovementReportDate()
        ));

        householdMovementAddress.setHouseMovementAddress(request.getHouseMovementAddress());
        householdMovementAddress.setLastAddressYn(request.getLastAddressYn());

        Optional<Household> household = householdRepository.findById(householdSerialNumber);
        householdMovementAddress.setHousehold(household.get());

        householdMovementAddressRepository.save(householdMovementAddress);

        return householdMovementAddressRepository.getHouseholdMovementAddress(householdSerialNumber, request.getHouseMovementReportDate());
    }

    @Override
    public HouseholdMovementAddressDto updateHouseholdMovementAddress(Long householdSerialNumber, String houseMovementReportDate, HouseholdMovementAddressUpdateRequest request) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = format.parse(houseMovementReportDate);

        householdMovementAddressRepository.updateHouseholdMovementAddress(householdSerialNumber, date, request.getHouseMovementAddress(), request.getLastAddressYn());

        return householdMovementAddressRepository.getHouseholdMovementAddress(householdSerialNumber, date);
    }

    @Override
    public void deleteHouseholdMovementAddress(Long householdSerialNumber, String houseMovementReportDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = format.parse(houseMovementReportDate);

        householdMovementAddressRepository.deleteHouseholdMovementAddress(householdSerialNumber, date);
    }
}
