package com.botscrew.facebook.service.impl;

import com.botscrew.facebook.entity.User;
import com.botscrew.facebook.model.Button;
import com.botscrew.facebook.model.Doctor;
import com.botscrew.facebook.model.outgoing.generic.MessageElement;
import com.botscrew.facebook.service.Docfinder;
import com.botscrew.framework.flow.model.GeoCoordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class DocfinderImpl implements Docfinder {

    @Value("${docfinder.url}")
    private String DOCFINDER_URL;
    @Value("${system.base.url}")
    private String baseUrl;
    @Autowired
    private RestTemplate restTemplate;


    @Override
    public List<Doctor> findAllSortedDistanceDoctors(GeoCoordinates coordinates) {
        String url = DOCFINDER_URL + "/doctors";
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("lat", coordinates.getLatitude())
                .queryParam("lng", coordinates.getLongitude());

        ResponseEntity<Doctor[]> responseEntity = restTemplate.getForEntity(builder.toUriString(), Doctor[].class);
        return Arrays.asList(responseEntity.getBody());
    }

    @Override
    public List<Doctor> findAllDoctors() {
        ResponseEntity<Doctor[]> responseEntity = restTemplate.getForEntity(DOCFINDER_URL + "/doctors", Doctor[].class);
        return Arrays.asList(responseEntity.getBody());
    }

    @Override
    public Doctor findOne(String id) {
        ResponseEntity<Doctor> responseEntity = restTemplate.getForEntity(DOCFINDER_URL + "/doctor/" + id, Doctor.class);
        return responseEntity.getBody();
    }
}
