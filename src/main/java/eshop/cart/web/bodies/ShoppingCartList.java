package eshop.cart.web.bodies;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class ShoppingCartList {
    private Long productId;
    private Long amount;

    public ShoppingCartList(eshop.cart.data.ShoppingCartList ShoppingCartList) {
        this.productId = ShoppingCartList.getProduct().getId();
        this.amount = ShoppingCartList.getAmount();
    }
}
