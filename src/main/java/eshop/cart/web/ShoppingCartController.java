package eshop.cart.web;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import eshop.cart.logic.IShoppingCartService;
import eshop.cart.web.bodies.ShoppingCartList;
import eshop.cart.web.bodies.ShoppingCartResponse;
import eshop.exception.IllegalOperationException;
import eshop.exception.NotFoundException;


@RestController
@RequestMapping("/cart")
public class ShoppingCartController {
    @Autowired
    private IShoppingCartService service;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ShoppingCartResponse infoCart(@PathVariable("id") long cartId) throws NotFoundException {
        return new ShoppingCartResponse(this.service.getById(cartId));
    }

    @GetMapping(value = "/{id}/pay", produces = MediaType.TEXT_PLAIN_VALUE)
    public String payForCart(@PathVariable("id") Long cartId) throws NotFoundException, IllegalOperationException {
        return "" + this.service.payForCart(cartId);
    }

    @PostMapping(value = "/{id}/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ShoppingCartResponse addToCart(@PathVariable("id") Long cartId, @RequestBody ShoppingCartList shoppingCartList) throws NotFoundException, IllegalOperationException {
        return new ShoppingCartResponse(this.service.addToCart(cartId, shoppingCartList));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShoppingCartResponse> addCart() {
        return new ResponseEntity<>(new ShoppingCartResponse(this.service.create()), HttpStatus.CREATED);
    }


    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") long cartId) throws NotFoundException {
        this.service.delete(cartId);
    }
}
