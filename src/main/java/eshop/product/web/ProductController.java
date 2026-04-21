package eshop.product.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import eshop.exception.NotFoundException;
import eshop.product.logic.IProductService;
import eshop.product.web.bodies.Amount;
import eshop.product.web.bodies.ProductRequest;
import eshop.product.web.bodies.ProductResponse;
import eshop.product.web.bodies.ProductUpdateRequest;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")

public class ProductController {
    @Autowired
    private IProductService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductResponse> getAllProducts() {
        return this.service.getAll().stream().map(ProductResponse::new).collect(Collectors.toList());
    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponse getProduct(@PathVariable("id") Long productId) throws NotFoundException {
        return new ProductResponse(this.service.getById(productId));
    }

    @GetMapping(value = "/{id}/amount", produces = MediaType.APPLICATION_JSON_VALUE)
    public Amount getAmount(@PathVariable("id") Long productId) throws NotFoundException {
        return new Amount(this.service.getAmount(productId));
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productRequest) {
        return new ResponseEntity<>(new ProductResponse(this.service.create(productRequest)), HttpStatus.CREATED);
    }

    @PostMapping(value = "/{id}/amount", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Amount addAmount(@PathVariable("id") Long productId, @RequestBody Amount amount) throws NotFoundException {
        return new Amount(this.service.addAmount(productId, amount.getAmount()));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponse updateProduct(@PathVariable("id") Long productId, @RequestBody ProductUpdateRequest productUpdateRequest) throws NotFoundException {
        return new ProductResponse(this.service.update(productId, productUpdateRequest));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteProduct(@PathVariable("id") Long productId) throws NotFoundException {
        this.service.delete(productId);
    }




}
