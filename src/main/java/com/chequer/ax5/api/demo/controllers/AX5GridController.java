package com.chequer.ax5.api.demo.controllers;

import com.chequer.ax5.api.demo.entity.grid.AX5Grid;
import com.chequer.ax5.api.demo.entity.grid.GridRepository;
import com.chequer.ax5.api.demo.entity.grid.order.AX5GridOrder;
import com.chequer.ax5.api.demo.entity.grid.order.GridOrderRepository;
import com.chequer.ax5.api.demo.utils.RequestParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ax5grid")
public class AX5GridController extends BaseController {

    @Autowired
    private GridRepository gridRepository;

    @Autowired
    private GridOrderRepository gridOrderRepository;

    @GetMapping()
    public List<AX5Grid> gets() {
        return gridRepository.findAll();
    }

    @GetMapping(value = "/order")
    public List<AX5GridOrder> getOrder() {
        PageRequest limit = new PageRequest(0, 10);
        return gridOrderRepository.findAll(limit).getContent();
    }

    @GetMapping(value = "/orders")
    public List<AX5GridOrder> getOrders(RequestParams<AX5GridOrder> requestParams) {
        return gridOrderRepository.findAll(requestParams.getPageable()).getContent();
    }
}