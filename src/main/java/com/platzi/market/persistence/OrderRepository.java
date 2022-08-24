package com.platzi.market.persistence;

import com.platzi.market.domain.Order;
import com.platzi.market.persistence.crud.OrderCrudRepository;
import com.platzi.market.persistence.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository implements com.platzi.market.domain.repository.OrderRepository {

    @Autowired
    private OrderCrudRepository orderCrudRepository;

    @Autowired
    private OrderMapper mapper;

    @Override
    public List<Order> getAll() {
        return mapper.toOrders( (List<com.platzi.market.persistence.entity.Order>) orderCrudRepository.findAll());
    }

    @Override
    public Optional<List<Order>> getByClient(String clientId) {
        return orderCrudRepository.findByIdClient( clientId ).map( orders -> mapper.toOrders( orders ) );
    }

    @Override
    public Order save(Order order) {
        com.platzi.market.persistence.entity.Order orderDB = mapper.toOrderDB( order );
        orderDB.getProducts().forEach( product -> product.setOrder( orderDB ) );
        return mapper.toOrder( orderCrudRepository.save( orderDB ) );
    }
}
