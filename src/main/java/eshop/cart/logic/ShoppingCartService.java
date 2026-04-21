package eshop.cart.logic;

import org.springframework.stereotype.Service;

import eshop.cart.data.IShoppingCartRepository;
import eshop.cart.data.ShoppingCart;
import eshop.cart.web.bodies.ShoppingCartList;
import eshop.exception.IllegalOperationException;
import eshop.exception.NotFoundException;
import eshop.product.logic.IProductService;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service
public class ShoppingCartService implements IShoppingCartService{
    @Autowired
    private IShoppingCartRepository repository;

    @Autowired
    private IProductService productService;

    @Autowired
    private IShoppingCartListService shoppingCartListService;

    @Override
    public ShoppingCart create() {
        return this.repository.save(new ShoppingCart());
    }

    @Override
    public ShoppingCart getById(long id) throws NotFoundException {
        ShoppingCart cart = this.repository.findShoppingCartById(id);
        if (cart == null) {
            throw new NotFoundException();
        }
        return cart;
    }


    @Override
    public ShoppingCart addToCart(long id, ShoppingCartList list) throws NotFoundException, IllegalOperationException {
        ShoppingCart cart = this.Unpaid(id);
        this.productService.removeAmount(list.getProductId(), list.getAmount());
        var realShopCartItem = this.findCartItemWithProduct(cart.getShoppingList(), list.getProductId());
        if (realShopCartItem == null) {
            eshop.cart.data.ShoppingCartList shoppingCartList = shoppingCartListService.create();
            shoppingCartList.setProduct(productService.getById(list.getProductId()));
            shoppingCartList.setAmount(list.getAmount());
            cart.getShoppingList().add(shoppingCartListService.save(shoppingCartList));
        } else {
            realShopCartItem.setAmount(realShopCartItem.getAmount() + list.getAmount());
            shoppingCartListService.save(realShopCartItem);
        }
        return this.repository.save(cart);
    }

    @Override
    public double payForCart(long id) throws NotFoundException, IllegalOperationException {
        ShoppingCart cart = this.Unpaid(id);
        double sum = cart.getShoppingList().stream().mapToDouble(item -> item.getAmount() * item.getProduct().getPrice()).sum();
        cart.setPayed(true);
        this.repository.save(cart);
        return sum;
    }

    @Override
    public void delete(long id) throws NotFoundException {
        this.repository.delete(this.getById(id));
    }
    private eshop.cart.data.ShoppingCartList findCartItemWithProduct(List<eshop.cart.data.ShoppingCartList> items, long productId) {
        for (var item : items) {
            if (item.getProduct().getId().equals(productId)) {
                return item;
            }
        }
        return null;
    }
    private ShoppingCart Unpaid(long id) throws NotFoundException, IllegalOperationException {
        ShoppingCart cart = this.getById(id);
        if (cart.isPayed()) {
            throw new IllegalOperationException();
        }
        return cart;
    }



}
