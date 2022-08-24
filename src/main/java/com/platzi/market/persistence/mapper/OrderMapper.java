package com.platzi.market.persistence.mapper;

import com.platzi.market.domain.Order;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = { OrderProductMapper.class })
public interface OrderMapper {
    @Mappings({
            @Mapping(source = "idOrder", target = "orderId"),
            @Mapping(source = "idClient", target = "clientId"),
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "paymentMethod", target = "paymentMethod"),
            @Mapping(source = "comments", target = "comments"),
            @Mapping(source = "status", target = "active"),
            @Mapping(source = "products", target = "products"),
    })
    Order toOrder( com.platzi.market.persistence.entity.Order order);

    List<Order> toOrders(List<com.platzi.market.persistence.entity.Order> orders );

    @InheritInverseConfiguration
    @Mapping(target = "client", ignore = true)
    com.platzi.market.persistence.entity.Order toOrderDB(Order order );
}
