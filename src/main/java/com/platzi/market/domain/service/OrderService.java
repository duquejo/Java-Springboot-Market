package com.platzi.market.domain.service;

import com.platzi.market.domain.Order;
import com.platzi.market.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAll() {
        return orderRepository.getAll();
    }

    public Optional<List<Order>> getByClient(String clientId ) {
        return orderRepository.getByClient( clientId );
    }

    public Order save( Order order ) {
        return orderRepository.save( order );
    }
}
