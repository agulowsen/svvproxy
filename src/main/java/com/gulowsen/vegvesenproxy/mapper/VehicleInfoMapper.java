package com.gulowsen.vegvesenproxy.mapper;

import com.gulowsen.vegvesenproxy.dataobjects.VehicleInfoResponse;
import com.gulowsen.vegvesenproxy.errorhandling.exception.CustomParseException;
import com.gulowsen.vegvesenproxy.interfaces.ResponseMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

@Component
public class VehicleInfoMapper implements ResponseMapper<VehicleInfoResponse> {


    @Override
    public VehicleInfoResponse parseResponse(String jsonString) throws CustomParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject data_obj = null;
        try {
            data_obj = (JSONObject) jsonParser.parse(jsonString);
        } catch (ParseException e) {
            throw new CustomParseException("Failed to parse and map vehicle info", e.getCause());
        }
        JSONArray kjoretøyDataListe = (JSONArray) data_obj.get("kjoretoydataListe");
        final JSONObject kjoretoyId = (JSONObject) ((JSONObject) kjoretøyDataListe.get(0)).get("kjoretoyId");

        String regNumber = (String) kjoretoyId.get("kjennemerke");
        String vin = (String) kjoretoyId.get("understellsnummer");
        return new VehicleInfoResponse(regNumber, vin);
    }
}
