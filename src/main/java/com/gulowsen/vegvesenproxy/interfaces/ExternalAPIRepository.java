package com.gulowsen.vegvesenproxy.interfaces;

import com.gulowsen.vegvesenproxy.errorhandling.exception.BadRequestException;
import com.gulowsen.vegvesenproxy.errorhandling.exception.CustomParseException;
import com.gulowsen.vegvesenproxy.errorhandling.exception.ExternalAPIException;

import java.util.Map;

public interface ExternalAPIRepository {

    String performRequest(Map<String, String> params) throws ExternalAPIException, BadRequestException, CustomParseException;

}
