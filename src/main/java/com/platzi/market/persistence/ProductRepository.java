package com.platzi.market.persistence;

import com.platzi.market.persistence.crud.ProductCrudRepository;
import com.platzi.market.persistence.entity.Product;
import com.platzi.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository implements com.platzi.market.domain.repository.ProductRepository {

    @Autowired
    private ProductCrudRepository productCrudRepository;

    @Autowired
    private ProductMapper mapper;

    @Override
    public List<com.platzi.market.domain.Product> getAll() {
        List<Product> productsDB = (List<Product>) productCrudRepository.findAll();
        return mapper.toProducts( productsDB );
    }

    @Override
    public Optional<List<com.platzi.market.domain.Product>> getByCategory(int categoryId) {
        List<Product> productsDB = productCrudRepository.findByIdCategoryOrderByNameAsc( categoryId );
        return Optional.of( mapper.toProducts( productsDB ) );
    }

    public List<Product> rawGetByCategory(int idCategory ) {
        return productCrudRepository.rawFindByIdCategoryOrderByNameAsc( idCategory );
    }

    @Override
    public Optional<List<com.platzi.market.domain.Product>> getLowStock(int quantity ) {
        Optional<List<Product>> productsDB = productCrudRepository.findByStockLessThanAndStatus( quantity, true );
        return productsDB.map( prods -> mapper.toProducts( prods ) );
    }

    @Override
    public Optional<com.platzi.market.domain.Product> getProduct(int idProduct ) {
        Optional<Product> product = productCrudRepository.findById( idProduct );
        return product.map( prod -> mapper.toProduct( prod ) );
    }

    @Override
    public com.platzi.market.domain.Product save( com.platzi.market.domain.Product product) {
        Product productDB = mapper.toDBProduct( product );
        return mapper.toProduct( productCrudRepository.save( productDB ) );
    }

    @Override
    public void delete( int idProduct ) {
        productCrudRepository.deleteById( idProduct );
    }
}
