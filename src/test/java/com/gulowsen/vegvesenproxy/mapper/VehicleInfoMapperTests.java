package com.gulowsen.vegvesenproxy.mapper;


import com.gulowsen.vegvesenproxy.TestData;
import com.gulowsen.vegvesenproxy.dataobjects.VehicleInfoResponse;
import com.gulowsen.vegvesenproxy.errorhandling.exception.BadRequestException;
import com.gulowsen.vegvesenproxy.errorhandling.exception.CustomParseException;
import com.gulowsen.vegvesenproxy.errorhandling.exception.ExternalAPIException;
import com.gulowsen.vegvesenproxy.repository.SvvVehicleRepository;
import com.gulowsen.vegvesenproxy.service.ExternalAPIService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class VehicleInfoMapperTests {

    @Mock
    SvvVehicleRepository svvVehicleInfoService;

    @InjectMocks
    VehicleInfoMapper vehicleInfoMapper;

    @BeforeEach
    public void createMocks () {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testParkingInfoMapperMapsRegnumberAndVin() throws BadRequestException, ExternalAPIException, CustomParseException {
        when(svvVehicleInfoService.performRequest(anyMap())).thenReturn(TestData.getMockedVehicleDataResponseEF47800());
        ExternalAPIService externalAPIService = new ExternalAPIService();
        Map<String, String> params = new HashMap<>();
        final VehicleInfoResponse vehicleInfoResponse = externalAPIService.handleRequest(params, vehicleInfoMapper, svvVehicleInfoService);
        Assertions.assertEquals("EF 47880", vehicleInfoResponse.regNumber());
        Assertions.assertEquals("XP7YGCEKXPB084218", vehicleInfoResponse.vin());
    }

    @Test
    public void testParkingInfoMapperMapsRegnumberAndVin2() throws BadRequestException, ExternalAPIException, CustomParseException {
        when(svvVehicleInfoService.performRequest(anyMap())).thenReturn(TestData.getMockedVehicleDataResponseEK86643());
        ExternalAPIService externalAPIService = new ExternalAPIService();
        Map<String, String> params = new HashMap<>();
        final VehicleInfoResponse vehicleInfoResponse = externalAPIService.handleRequest(params, vehicleInfoMapper, svvVehicleInfoService);
        Assertions.assertEquals("EK 86643", vehicleInfoResponse.regNumber());
        Assertions.assertEquals("LA7DGCEFXPB084218", vehicleInfoResponse.vin());
    }

}
