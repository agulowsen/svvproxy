package com.gulowsen.vegvesenproxy.utils.apihelper;

import java.util.Map;

public class ExternalAPIClientBuilder {

    private String endpoint;
    private String apiKey;
    private String apiKeyPrefix;
    private String mediaType;
    private Map<String, String> params;

    public ExternalAPIClientBuilder endpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    public ExternalAPIClientBuilder apiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public ExternalAPIClientBuilder apiKeyPrefix(String apiKeyPrefix) {
        this.apiKeyPrefix = apiKeyPrefix;
        return this;
    }

    public ExternalAPIClientBuilder mediaType(String mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public ExternalAPIClientBuilder params(Map<String, String> params) {
        this.params = params;
        return this;
    }

    public ExternalAPIClient build() {
        return new ExternalAPIClient(endpoint, apiKey, apiKeyPrefix, mediaType, params);
    }





}
