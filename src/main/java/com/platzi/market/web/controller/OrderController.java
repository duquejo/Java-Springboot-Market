package com.platzi.market.web.controller;

import com.platzi.market.domain.Order;
import com.platzi.market.domain.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    @ApiOperation(value = "", authorizations = @Authorization(value = "JWT") )
    public ResponseEntity<List<Order>> getAll() {
        return ResponseEntity.ok( orderService.getAll() );
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "", authorizations = @Authorization(value = "JWT") )
    public ResponseEntity<List<Order>> getByClient( @PathVariable("id") String clientId ) {
        return ResponseEntity.of( orderService.getByClient( clientId ) );
    }

    @PostMapping
    @ApiOperation(value = "", authorizations = @Authorization(value = "JWT") )
    public ResponseEntity<Order> save( @RequestBody Order order ) {
        return new ResponseEntity<>( orderService.save( order ), HttpStatus.CREATED );
    }
}
