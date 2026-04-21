package eshop.product.logic;

import org.springframework.stereotype.Service;

import eshop.exception.IllegalOperationException;
import eshop.exception.NotFoundException;
import eshop.product.data.IProductRepository;
import eshop.product.data.Product;
import eshop.product.web.bodies.ProductRequest;
import eshop.product.web.bodies.ProductUpdateRequest;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepository repository;

    @Override
    public List<Product> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Product getById(long id) throws NotFoundException {
        Product product = this.repository.findProductById(id);
        if (product == null) {
            throw new NotFoundException();
        }
        return product;
    }

    @Override
    public Product create(ProductRequest request) {
        return this.repository.save(new Product(request));
    }


    @Override
    public Product update(long id, ProductUpdateRequest request) throws NotFoundException {
        Product product = this.getById(id);
        if (request.getName() != null) {
            product.setName(request.getName());
        }
        if (request.getDescription() != null) {
            product.setDescription(request.getDescription());
        }
        return this.repository.save(product);
    }

    @Override
    public long getAmount(long id) throws NotFoundException {
        return this.getById(id).getAmount();
    }

    @Override
    public long addAmount(long id, long increment) throws NotFoundException {
        Product product = this.getById(id);
        product.setAmount(product.getAmount() + increment);
        this.repository.save(product);
        return product.getAmount();
    }

    @Override
    public void removeAmount(long id, long decrement) throws NotFoundException, IllegalOperationException {
        Product product = this.getById(id);
        if (product.getAmount() < decrement) {
            throw new IllegalOperationException();
        }
        product.setAmount(product.getAmount() - decrement);
        this.repository.save(product);
    }

    @Override
    public void delete(long id) throws NotFoundException {
        this.repository.delete(this.getById(id));
    }



}
