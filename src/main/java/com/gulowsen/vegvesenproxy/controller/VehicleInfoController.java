package com.gulowsen.vegvesenproxy.controller;


import com.gulowsen.vegvesenproxy.dataobjects.VehicleInfoResponse;
import com.gulowsen.vegvesenproxy.errorhandling.exception.BadRequestException;
import com.gulowsen.vegvesenproxy.errorhandling.exception.CustomParseException;
import com.gulowsen.vegvesenproxy.errorhandling.exception.ExternalAPIException;
import com.gulowsen.vegvesenproxy.mapper.VehicleInfoMapper;
import com.gulowsen.vegvesenproxy.repository.SvvVehicleRepository;
import com.gulowsen.vegvesenproxy.service.ExternalAPIService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@Validated
@RequestMapping("/api")
@RestController
@Tag(name = "Vehicle Information API")
public class VehicleInfoController {

    private static final String VEHICLE_INFO_ENDPOINT = "/vehicleinfo/{regNumber}";

    private final ExternalAPIService externalAPIService;
    private final VehicleInfoMapper vehicleInfoMapper;
    private final SvvVehicleRepository vehicleInfoService;

    public VehicleInfoController(ExternalAPIService externalAPIService, VehicleInfoMapper vehicleInfoMapper, SvvVehicleRepository vehicleInfoService) {
        this.externalAPIService = externalAPIService;
        this.vehicleInfoMapper = vehicleInfoMapper;
        this.vehicleInfoService = vehicleInfoService;
    }

    @Operation(summary = "Get data for vehicle based on registration number")
    @GetMapping(value = VEHICLE_INFO_ENDPOINT, produces = "application/json")
    public VehicleInfoResponse getVehicleInformation(@PathVariable @NotBlank @Size(min = 3, max = 8) String regNumber) throws CustomParseException, ExternalAPIException, BadRequestException {
        Map<String, String> params = new HashMap<>();
        params.put("kjennemerke", regNumber);
        return externalAPIService.handleRequest(params, vehicleInfoMapper, vehicleInfoService);
    }
}
