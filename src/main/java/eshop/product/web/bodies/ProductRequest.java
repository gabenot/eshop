package eshop.product.web.bodies;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductRequest {
    private String name;
    private String description;
    private Long amount;
    private String unit;
    private Double price;
}
