package eshop.cart.data;


import org.springframework.data.jpa.repository.JpaRepository;

public interface IShoppingCartListRepository  extends JpaRepository<ShoppingCartList, Long>{
    ShoppingCartList findShoppingCartListById(Long id);
}
