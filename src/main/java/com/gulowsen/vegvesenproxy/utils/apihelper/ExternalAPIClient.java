package com.gulowsen.vegvesenproxy.utils.apihelper;

import com.gulowsen.vegvesenproxy.errorhandling.exception.BadRequestException;
import com.gulowsen.vegvesenproxy.errorhandling.exception.ExternalAPIException;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;
import java.util.Objects;

public class ExternalAPIClient {

    private String endpoint;
    private String apiKey;
    private String apiKeyPrefix;
    private String mediaType;
    private Map<String, String> params;

    private final String FALLBACK_MEDIA_TYPE = "application/json";


    ExternalAPIClient(String endpoint, String apiKey, String apiKeyPrefix, String mediaType, Map<String, String> params) {
        this.endpoint = endpoint;
        this.apiKey = apiKey;
        this.apiKeyPrefix = apiKeyPrefix;
        this.mediaType = mediaType;
        this.params = params;
    }

    public String fetchData() throws ExternalAPIException, BadRequestException {
        try {
            URL url = new URL(getEndpoint());
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            if(apiKey != null)
                conn.setRequestProperty(apiKeyPrefix, apiKey);
            conn.setRequestProperty("Accept", Objects.requireNonNullElse(mediaType, FALLBACK_MEDIA_TYPE));
            conn.connect();
            int responseCode = conn.getResponseCode();

            if(responseCode == 400) {
                throw new BadRequestException("Bad request");
            }

            if(responseCode != 200) {
                throw new ExternalAPIException("Got back non 200 responseCode: " + responseCode);
            }

            StringBuilder resp = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while((line = bufferedReader.readLine()) != null) {
                resp.append(line);
            }
            bufferedReader.close();
            return resp.toString();

        } catch (IOException e) {
            throw new ExternalAPIException("Failed to fetch data from " + endpoint, e);
        }
    }

    private String getEndpoint() {
        if(params == null || params.isEmpty()) return endpoint;
        StringBuilder endpointBuilder = new StringBuilder(endpoint);
        endpointBuilder.append("?");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            endpointBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        return endpointBuilder.toString();
    }
}
