package eshop.cart.logic;

import eshop.cart.data.ShoppingCart;
import eshop.cart.web.bodies.ShoppingCartList;
import eshop.exception.IllegalOperationException;
import eshop.exception.NotFoundException;

public interface IShoppingCartService {
    ShoppingCart create();

    ShoppingCart getById(long id) throws NotFoundException;

    void delete(long id) throws NotFoundException;

    ShoppingCart addToCart(long id, ShoppingCartList body) throws NotFoundException, IllegalOperationException;

    double payForCart(long id) throws NotFoundException, IllegalOperationException;
}
