package com.gulowsen.vegvesenproxy.interfaces;

import com.gulowsen.vegvesenproxy.errorhandling.exception.CustomParseException;

public interface ResponseMapper<RESPONSE_TYPE> {

    RESPONSE_TYPE parseResponse(String jsonString) throws CustomParseException;

}
