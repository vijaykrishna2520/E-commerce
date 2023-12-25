package com.project.ecommerce.service;

import com.project.ecommerce.entity.OrderEntity;
import com.project.ecommerce.entity.UserEntity;
import com.project.ecommerce.exception.InternalServerErrorException;
import com.project.ecommerce.repository.OrderRepository;
import jakarta.persistence.criteria.Order;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class OrderService {

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public void addNewOrderForTheUserByUserId(Long userId) {
        UserEntity user = userService.getUserById(userId);
        OrderEntity orderNew = new OrderEntity();
        orderNew.setUser(user);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd'T'HH:mm:ss");
        Date orderPlacedDate = new Date();
        String orderPlacedDateFormattedString = simpleDateFormat.format(orderPlacedDate);
        try {
            orderPlacedDate = simpleDateFormat.parse(orderPlacedDateFormattedString);
            orderNew.setOrderPlacedDateTime(orderPlacedDate);
        } catch (ParseException e) {
            LOGGER.error("Date formmatting failed");
            throw new InternalServerErrorException("Internal Server Error");
        }
        addOrder(orderNew);
    }
    public OrderEntity addOrder(OrderEntity order){
        try {
            OrderEntity orderSaved = orderRepository.save(order);
            return orderSaved;
        } catch (Exception e) {
            LOGGER.error("error occured "+e);
            throw new InternalServerErrorException("Internal Server error");
        }
    }

}
