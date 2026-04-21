package eshop.product.web.bodies;
import eshop.product.data.Product;
import lombok.Getter;

@Getter
public class ProductResponse {
    private final Long id;
    private final String name;
    private final String description;
    private final Long amount;
    private final String unit;
    private final Double price;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.amount = product.getAmount();
        this.unit = product.getUnit();
        this.price = product.getPrice();
    }
}
