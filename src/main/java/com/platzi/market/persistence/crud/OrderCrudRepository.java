package com.platzi.market.persistence.crud;

import com.platzi.market.persistence.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderCrudRepository extends CrudRepository<Order, Integer> {
    Optional<List<Order>> findByIdClient( String idClient );
}
