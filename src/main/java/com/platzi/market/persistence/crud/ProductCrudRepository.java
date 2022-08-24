package com.platzi.market.persistence.crud;

import com.platzi.market.persistence.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductCrudRepository extends CrudRepository<Product, Integer> {
    List<Product> findByIdCategoryOrderByNameAsc(int idCategory );

    @Query(value = "SELECT * FROM products WHERE id_category = ?", nativeQuery = true )
    List<Product> rawFindByIdCategoryOrderByNameAsc(int idCategory );

    Optional<List<Product>> findByStockLessThanAndStatus( int stock, boolean status );
}
