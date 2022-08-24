package com.platzi.market.web.controller;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(("/products"))
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @ApiOperation("Get all market products")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok( productService.getAll() );
    }

    @GetMapping("/{id}")
    @ApiOperation("Search product by own ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    public ResponseEntity<Product> getProduct(
            @ApiParam(value = "Product searching ID", required = true, example = "7")
            @PathVariable("id")
            int productId
    ) {
        return ResponseEntity.of( productService.getProduct( productId ) );
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Product>> getByCategory( @PathVariable("id") int categoryId ) {
        return ResponseEntity.of( productService.getByCategory( categoryId ) );
    }

    @GetMapping("/quantity-stock/{quantity}")
    @ApiOperation(value = "", authorizations = @Authorization(value = "JWT") )
    public ResponseEntity<List<Product>> getLowStock( @PathVariable("quantity") int quantity ) {
        return ResponseEntity.of( productService.getLowStock( quantity ) );
    }

    @PostMapping
    @ApiOperation(value = "", authorizations = @Authorization(value = "JWT") )
    public ResponseEntity<Product> save( @RequestBody Product product ) {
        return new ResponseEntity<>( productService.save( product ), HttpStatus.CREATED );
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "", authorizations = @Authorization(value = "JWT") )
    public ResponseEntity<Boolean> delete( @PathVariable("id") int productId ) {
        boolean deleteResult = productService.delete(productId);
        return new ResponseEntity<>( deleteResult, deleteResult ? HttpStatus.OK : HttpStatus.NOT_FOUND );
    }
}
