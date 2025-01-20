package com.gulowsen.vegvesenproxy.repository;

import com.gulowsen.vegvesenproxy.errorhandling.exception.BadRequestException;
import com.gulowsen.vegvesenproxy.errorhandling.exception.ExternalAPIException;
import com.gulowsen.vegvesenproxy.interfaces.ExternalAPIRepository;
import com.gulowsen.vegvesenproxy.utils.apihelper.ExternalAPIClient;
import com.gulowsen.vegvesenproxy.utils.apihelper.ExternalAPIClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class SvvVehicleRepository implements ExternalAPIRepository {

    @Value("${svv_token}")
    private String apiToken;// = System.getenv("SVV-TOKEN");
    private final String endPoint = "https://akfell-datautlevering.atlas.vegvesen.no/enkeltoppslag/kjoretoydata";

    @Override
    public String performRequest(Map<String, String> params) throws ExternalAPIException, BadRequestException {
        ExternalAPIClient externalAPIClient = new ExternalAPIClientBuilder().endpoint(endPoint).params(params).apiKey(apiToken).apiKeyPrefix("SVV-Authorization").build();
        return externalAPIClient.fetchData();
    }
}
