package com.example.usershopapp.Database.Local;


import com.example.usershopapp.Database.DataStore.ICartDataSource;
import com.example.usershopapp.Database.ModelDB.Cart;

import java.util.List;

import io.reactivex.Flowable;

public class CartDataSource implements ICartDataSource {
    private CartDAO cartDAO;
    private static CartDataSource instance;


    public CartDataSource(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    public static CartDataSource getInstance(CartDAO cartDAO){
        if (instance==null)
            instance=new CartDataSource(cartDAO);
        return instance;
    }

    @Override
    public Flowable<List<Cart>> getCartItem() {
        return cartDAO.getCartItem();
    }

    @Override
    public Flowable<List<Cart>> getCartItemById(int cartItemId) {
        return cartDAO.getCartItemById(cartItemId);
    }

    @Override
    public int countCartItems() {
        return cartDAO.countCartItems();
    }

    @Override
    public void emptyCart() {
        cartDAO.emptyCart();
    }

    @Override
    public void insertToCart(Cart... carts) {
        cartDAO.insertToCart(carts);
    }

    @Override
    public void updateToCart(Cart... carts) {
        cartDAO.updateToCart(carts);
    }

    @Override
    public void deleteToCart(Cart cart) {
        cartDAO.deleteToCart(cart);
    }

    @Override
    public float sumPrice() {
        return cartDAO.sumPrice();
    }
}
