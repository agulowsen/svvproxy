package com.gulowsen.vegvesenproxy.controller;


import com.gulowsen.vegvesenproxy.dataobjects.ParkingInfoResponse;
import com.gulowsen.vegvesenproxy.errorhandling.exception.BadRequestException;
import com.gulowsen.vegvesenproxy.errorhandling.exception.CustomParseException;
import com.gulowsen.vegvesenproxy.errorhandling.exception.ExternalAPIException;
import com.gulowsen.vegvesenproxy.mapper.ParkingInfoMapper;
import com.gulowsen.vegvesenproxy.repository.SvvParkingRepository;
import com.gulowsen.vegvesenproxy.service.ExternalAPIService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api")
@RestController
@Tag(name = "Parking Data API")
public class ParkingDataController {

    private static final String PARKING_INFO_ENDPOINT = "/parkinginfo";

    private final ExternalAPIService externalAPIService;
    private final ParkingInfoMapper parkingInfoMapper;
    private final SvvParkingRepository parkingService;


    public ParkingDataController(ExternalAPIService externalAPIService, ParkingInfoMapper parkingInfoMapper, SvvParkingRepository parkingService) {
        this.externalAPIService = externalAPIService;
        this.parkingInfoMapper = parkingInfoMapper;
        this.parkingService = parkingService;
    }

    @Operation(summary = "Get all parking data", description = "Returns all parking data that can be found")
    @GetMapping(value = PARKING_INFO_ENDPOINT, produces = "application/json")
    public ParkingInfoResponse getParkingInfo() throws CustomParseException, ExternalAPIException, BadRequestException {
        return externalAPIService.handleRequest(null, parkingInfoMapper, parkingService);
    }
}
