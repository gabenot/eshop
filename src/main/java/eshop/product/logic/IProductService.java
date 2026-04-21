package eshop.product.logic;

import java.util.List;

import eshop.exception.IllegalOperationException;
import eshop.exception.NotFoundException;
import eshop.product.data.Product;
import eshop.product.web.bodies.ProductRequest;
import eshop.product.web.bodies.ProductUpdateRequest;
public interface IProductService {
    List<Product> getAll();

    Product getById(long id) throws NotFoundException;

    Product create(ProductRequest r);

    Product update(long id, ProductUpdateRequest request) throws NotFoundException;

    long getAmount(long id) throws NotFoundException;

    long addAmount(long id, long increment) throws NotFoundException;

    void removeAmount(long id, long decrement) throws NotFoundException, IllegalOperationException;

    void delete(long id) throws NotFoundException;

}
