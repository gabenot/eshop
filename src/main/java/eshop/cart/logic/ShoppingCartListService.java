package eshop.cart.logic;

import org.springframework.stereotype.Service;

import eshop.cart.data.IShoppingCartListRepository;
import eshop.cart.data.ShoppingCartList;
import eshop.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ShoppingCartListService implements IShoppingCartListService{
    @Autowired
    IShoppingCartListRepository repository;

    @Override
    public ShoppingCartList getById(long id) throws NotFoundException {
        ShoppingCartList shoppingCartList = this.repository.findShoppingCartListById(id);
        if (shoppingCartList == null)
            throw new NotFoundException();
        return shoppingCartList;
    }

    @Override
    public ShoppingCartList create() {
        return this.repository.save(new ShoppingCartList());
    }


    @Override
    public void delete(long id) throws NotFoundException {
        this.repository.delete(this.getById(id));
    }

    @Override
    public ShoppingCartList save(ShoppingCartList shoppingCartList) {
        return this.repository.save(shoppingCartList);
    }
}
