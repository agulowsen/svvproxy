package com.gulowsen.vegvesenproxy.service;

import com.gulowsen.vegvesenproxy.errorhandling.exception.BadRequestException;
import com.gulowsen.vegvesenproxy.errorhandling.exception.CustomParseException;
import com.gulowsen.vegvesenproxy.errorhandling.exception.ExternalAPIException;
import com.gulowsen.vegvesenproxy.interfaces.ExternalAPIRepository;
import com.gulowsen.vegvesenproxy.interfaces.ResponseMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ExternalAPIService {


    public <RESPONSE_TYPE, SERVICE extends ExternalAPIRepository> RESPONSE_TYPE handleRequest(Map<String, String> params, ResponseMapper<RESPONSE_TYPE> mapper, SERVICE service) throws CustomParseException, ExternalAPIException, BadRequestException {
        return mapper.parseResponse(service.performRequest(params));
    }
}
