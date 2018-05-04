package com.botscrew.facebook.service;

import com.botscrew.facebook.model.Doctor;
import com.botscrew.facebook.model.outgoing.generic.MessageElement;
import com.botscrew.framework.flow.model.GeoCoordinates;

import java.util.List;

public interface Docfinder {

    List<Doctor> findAllSortedDistanceDoctors(GeoCoordinates coordinates);
    List<Doctor> findAllDoctors();
    Doctor findOne(String id);
}
