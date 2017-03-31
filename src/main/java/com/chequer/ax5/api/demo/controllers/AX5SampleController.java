package com.chequer.ax5.api.demo.controllers;

import com.chequer.ax5.api.demo.entity.sample.Sample;
import com.chequer.ax5.api.demo.entity.sample.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ax5sample")
public class AX5SampleController extends BaseController {

    @Autowired
    private SampleRepository sampleRepository;

    @GetMapping
    public List<Sample> gets() {
        return sampleRepository.findAll();
    }
}