package eshop.product.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import eshop.product.web.bodies.ProductRequest;



@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Long amount;
    private String unit;
    private Double price;


    public Product(ProductRequest r) {
        this.name = r.getName();
        this.description = r.getDescription();
        this.amount = r.getAmount();
        this.unit = r.getUnit();
        this.price = r.getPrice();
    }
}
