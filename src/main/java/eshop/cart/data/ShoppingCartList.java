package eshop.cart.data;

import lombok.Data;

import javax.persistence.*;

import eshop.product.data.Product;

@Data
@Entity
public class ShoppingCartList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Product product;

    private Long amount;

    public ShoppingCartList() {

    }

    public ShoppingCartList(Product product, Long amount) {
        this.product = product;
        this.amount = amount;
    }

    }
