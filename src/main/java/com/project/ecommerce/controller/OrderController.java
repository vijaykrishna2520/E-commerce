package com.project.ecommerce.controller;

import com.project.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    //Creating a new order for the given user
    @RequestMapping(value = "{userId}", method = RequestMethod.POST)
    public void sample(@PathVariable("userId") Long userId) {
        orderService.addNewOrderForTheUserByUserId(userId);
    }
}
