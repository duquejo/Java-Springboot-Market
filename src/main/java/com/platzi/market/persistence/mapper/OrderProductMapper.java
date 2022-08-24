package com.platzi.market.persistence.mapper;

import com.platzi.market.domain.OrderProduct;
import com.platzi.market.persistence.entity.OrdersProduct;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OrderProductMapper {
    @Mappings({
            /* Compound primary key */
            @Mapping(source = "id.idProduct", target = "productId"),
            @Mapping(source = "quantity", target = "quantity"),
            @Mapping(source = "total", target = "total"),
            @Mapping(source = "status", target = "active")
    })
    OrderProduct toOrderProduct( OrdersProduct ordersProduct );

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "order", ignore = true),
            @Mapping(target = "product", ignore = true),
            @Mapping(target = "id.idOrder", ignore = true),
    })
    OrdersProduct toOrderProductDB( OrderProduct orderProduct );
}
