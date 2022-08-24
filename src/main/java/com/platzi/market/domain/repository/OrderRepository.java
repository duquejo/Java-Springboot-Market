package com.platzi.market.domain.repository;

import com.platzi.market.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    List<Order> getAll();
    Optional<List<Order>> getByClient(String clientId );
    Order save( Order order );
}
