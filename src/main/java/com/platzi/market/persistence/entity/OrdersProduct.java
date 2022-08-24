package com.platzi.market.persistence.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "orders_products")
public class OrdersProduct {
    @EmbeddedId
    private OrdersProductPK id;

    private Integer quantity;
    private BigDecimal total;
    private Boolean status;

    @ManyToOne
    @MapsId("idOrder")
    @JoinColumn(name = "id_order", insertable = false, updatable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "id_product", insertable = false, updatable = false)
    private Product product;

    public OrdersProductPK getId() {
        return id;
    }

    public void setId(OrdersProductPK id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
