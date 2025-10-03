package com.example.storeelectronic.demo.Services.impl;

import com.example.storeelectronic.demo.Dto.AddItemDto;
import com.example.storeelectronic.demo.Dto.CartDto;
import com.example.storeelectronic.demo.Repository.CarItemRepository;
import com.example.storeelectronic.demo.Repository.Cartrepository;
import com.example.storeelectronic.demo.Repository.ProductRepository;
import com.example.storeelectronic.demo.Repository.UserRepository;
import com.example.storeelectronic.demo.Services.CartService;
import com.example.storeelectronic.demo.entities.Cart;
import com.example.storeelectronic.demo.entities.CartItem;
import com.example.storeelectronic.demo.entities.Product;
import com.example.storeelectronic.demo.entities.User;
import com.example.storeelectronic.demo.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class CartServiceimpl implements CartService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private Cartrepository cartRepo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CarItemRepository cartItemrepo;


    @Override
    public CartDto additemtocart(String userid, AddItemDto request) {
        int quantity = request.getQuantity();
        String productid = request.getProductid();
        Product product = this.productRepo.findById(productid).orElseThrow(() -> new ResourceNotFoundException("productid", "productid", productid));
        User user = this.userRepo.findById(userid).orElseThrow(() -> new ResourceNotFoundException("user", "userid", userid));

        Cart cart;
        try {
            cart = cartRepo.findByUser(user).get();
        } catch (NoSuchElementException ex) {
            cart = new Cart();
            cart.setCartId(UUID.randomUUID().toString());
            cart.setUser(user);
            cart.setCartItems(new ArrayList<>());
            cart.setCreatedate(new Date());
        }

        AtomicReference<Boolean> update = new AtomicReference<>(false);

        List<CartItem> items = cart.getCartItems();
        List<CartItem> updateditem = items.stream().map(obj -> {
                    if (obj.getProduct().getProductId().equals(productid)) {
                        obj.setQuantity(quantity);
                        obj.setTotalPrice(quantity * product.getPrice());
                        update.set(true);
                    }
                    return obj;


                }
        ).collect(Collectors.toList());

        cart.setCartItems(updateditem);

        List<CartItem> itemsofcart = cart.getCartItems();
        if (!update.get()) {
            CartItem item = CartItem.builder().quantity(quantity).totalPrice(quantity * product.getPrice()).cart(cart).product(product).build();
            cart.getCartItems().add(item);

        }
        Cart savedcart = this.cartRepo.save(cart);
        return this.mapper.map(savedcart, CartDto.class);
    }


    @Override
    public void removeitemfromcart(String userid, int cartitemid) {
        CartItem caritem = this.cartItemrepo.findById(cartitemid).orElseThrow(() -> new ResourceNotFoundException("Cartitem", "cartitem", cartitemid));
        cartItemrepo.delete(caritem);
    }


    @Override
    public void clearCart(String userid) {
        User user = this.userRepo.findById(userid).orElseThrow(() -> new ResourceNotFoundException("user", "userid", userid));
        Cart cart = cartRepo.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("CART", "foruser id", userid));
        cart.getCartItems().clear();
        cartRepo.save(cart);
    }

    @Override
    public CartDto getCart(String Userid) {
        User user = this.userRepo.findById(Userid).orElseThrow(() -> new ResourceNotFoundException("user", "userid", Userid));
        Optional<Cart> cart = this.cartRepo.findByUser(user);
        return this.mapper.map(cart, CartDto.class);
    }

}
