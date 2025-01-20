package com.gulowsen.vegvesenproxy.mapper;

import com.gulowsen.vegvesenproxy.dataobjects.ParkingInfoEntry;
import com.gulowsen.vegvesenproxy.dataobjects.ParkingInfoResponse;
import com.gulowsen.vegvesenproxy.errorhandling.exception.CustomParseException;
import com.gulowsen.vegvesenproxy.interfaces.ResponseMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ParkingInfoMapper implements ResponseMapper<ParkingInfoResponse> {

    @Override
    public ParkingInfoResponse parseResponse(String jsonString) throws CustomParseException {
        List<ParkingInfoEntry> entries = new ArrayList<>();

        JSONParser jsonParser = new JSONParser();
        JSONArray data_obj = null;
        try {
            data_obj = (JSONArray) jsonParser.parse(jsonString);
        } catch (ParseException e) {
            throw new CustomParseException("Failed to parse and map Parking Info ", e.getCause());
        }
        data_obj.forEach(entry -> mapEntry(entries, (JSONObject) entry));
        return new ParkingInfoResponse(entries);
    }

    private void mapEntry(List<ParkingInfoEntry> entries, JSONObject entry) {
        int id = Math.toIntExact((Long)entry.get("id"));
        String orgNumber = (String) entry.get("organisasjonsnummer");
        String name = (String) entry.get("navn");
        String status = (String) entry.get("status");
        entries.add(new ParkingInfoEntry(id, orgNumber, name, status));
    }
}
