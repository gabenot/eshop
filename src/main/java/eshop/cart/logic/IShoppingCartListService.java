package eshop.cart.logic;


import eshop.cart.data.ShoppingCartList;
import eshop.exception.NotFoundException;

public interface IShoppingCartListService {
    ShoppingCartList getById(long id) throws NotFoundException;

    ShoppingCartList create();

    void delete(long id) throws NotFoundException;

    ShoppingCartList save(ShoppingCartList shoppingCartList);
}
