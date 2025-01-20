package com.gulowsen.vegvesenproxy.repository;

import com.gulowsen.vegvesenproxy.errorhandling.exception.BadRequestException;
import com.gulowsen.vegvesenproxy.errorhandling.exception.ExternalAPIException;
import com.gulowsen.vegvesenproxy.interfaces.ExternalAPIRepository;
import com.gulowsen.vegvesenproxy.utils.apihelper.ExternalAPIClient;
import com.gulowsen.vegvesenproxy.utils.apihelper.ExternalAPIClientBuilder;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class SvvParkingRepository implements ExternalAPIRepository {

    private final String endpoint = "https://parkreg-open.atlas.vegvesen.no/ws/no/vegvesen/veg/parkeringsomraade/parkeringsregisteret/v1/parkeringstilbyder";

    @Override
    public String performRequest(Map<String, String> params) throws ExternalAPIException, BadRequestException {
        ExternalAPIClient externalAPIClient = new ExternalAPIClientBuilder().endpoint(endpoint).build();
        return externalAPIClient.fetchData();

    }
}
