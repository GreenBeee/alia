package com.botscrew.facebook.controller;

import com.botscrew.facebook.model.Doctor;
import com.botscrew.facebook.service.Docfinder;
import com.botscrew.framework.flow.model.GeoCoordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DoctorViewController {

    @Autowired
    private Docfinder docfinder;

    @RequestMapping(value = "/doctor", method = RequestMethod.GET)
    private ModelAndView index(@RequestParam(name = "id", required = true) String id,
                               @RequestParam(name = "long", required = true) Double longitude,
                               @RequestParam(name = "lat", required = true) Double latitude) {

        GeoCoordinates coordinates = new GeoCoordinates(latitude, longitude);
        List<Doctor> currentDoc = docfinder.findAllSortedDistanceDoctors(coordinates).stream().filter(x -> x.getId().equals(id))
                .collect(Collectors.toList());
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("doctor", currentDoc.get(0));
        modelAndView.addObject("userLongitude", longitude);
        modelAndView.addObject("userLatitude", latitude);
        return modelAndView;
    }
}
