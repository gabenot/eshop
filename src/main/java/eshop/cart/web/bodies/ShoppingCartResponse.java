package eshop.cart.web.bodies;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

import eshop.cart.data.ShoppingCart;

@Getter
@Setter

public class ShoppingCartResponse {
    private long id;
    private List<ShoppingCartList> shoppingList;
    private boolean payed;

    public ShoppingCartResponse(ShoppingCart shoppingCart) {
        this.id = shoppingCart.getId();
        this.payed = shoppingCart.isPayed();
        this.shoppingList = shoppingCart.getShoppingList().stream().map(ShoppingCartList::new).collect(Collectors.toList());
    }
}
