package bjfu.eight.mall.service;


import bjfu.eight.mall.entity.po.Cart;
import bjfu.eight.mall.entity.po.Products;
import bjfu.eight.mall.entity.vo.Cart2;
import bjfu.eight.mall.entity.vo.CartList;
import bjfu.eight.mall.mapper.CartsMapper;
import bjfu.eight.mall.mapper.ProductsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartsMapper cartsMapper;
    @Autowired
    private ProductsMapper productsMapper;

    public int getCartCount(int uid){
        List<Cart> carts=cartsMapper.getByUid(uid);
        int count=0;
        for(Cart cart:carts){
            count+=(cart.getQuantity());
        }
        return count;
    }

    public CartList updateCarts(int productid,int quantity,int uid){
        cartsMapper.updateCartAmount(quantity,productid);
        List<Cart> carts=cartsMapper.getByUid(uid);
        ArrayList<Cart2> cart2s=new ArrayList<Cart2>();
        double totalPrice=0;
        for(Cart cart:carts){
            Products products=productsMapper.getProductById(cart.getProductId());
            Cart2 cart2=new Cart2();
            cart2.setId(cart.getId());
            cart2.setUserId(cart.getUserId());
            cart2.setProductId(products.getId());
            cart2.setName(products.getName());
            cart2.setQuantity(cart.getQuantity());
            cart2.setPrice(products.getPrice());
            cart2.setStatus(products.getStatus());
            cart2.setTotalPrice(cart.getQuantity()*products.getPrice());
            totalPrice+=cart2.getTotalPrice();
            cart2.setStock(products.getStock());
            cart2.setIconUrl(products.getIconUrl());
            cart2s.add(cart2);
        }
        CartList cartList=new CartList();
        cartList.setLists(cart2s.toArray(new Cart2[cart2s.size()]));
        cartList.setTotalPrice(totalPrice);
        return cartList;
    }

    public int clearCarts(int uid){
        return cartsMapper.clearCart(uid);
    }

    public CartList delCart(int productId,int uid){
        cartsMapper.deleteCart(uid,productId);
        List<Cart> carts=cartsMapper.getByUid(uid);
        ArrayList<Cart2> cart2s=new ArrayList<Cart2>();
        double totalPrice=0;
        for(Cart cart:carts){
            Products products=productsMapper.getProductById(cart.getProductId());
            Cart2 cart2=new Cart2();
            cart2.setId(cart.getId());
            cart2.setUserId(cart.getUserId());
            cart2.setProductId(products.getId());
            cart2.setName(products.getName());
            cart2.setQuantity(cart.getQuantity());
            cart2.setPrice(products.getPrice());
            cart2.setStatus(products.getStatus());
            cart2.setTotalPrice(cart.getQuantity()*products.getPrice());
            totalPrice+=cart2.getTotalPrice();
            cart2.setStock(products.getStock());
            cart2.setIconUrl(products.getIconUrl());
            cart2s.add(cart2);
        }
        CartList cartList=new CartList();
        cartList.setLists(cart2s.toArray(new Cart2[cart2s.size()]));
        cartList.setTotalPrice(totalPrice);
        return cartList;
    }

    public CartList findAllCarts(int uid){
        List<Cart> carts=cartsMapper.getByUid(uid);
        ArrayList<Cart2> cart2s=new ArrayList<Cart2>();
        double totalPrice=0;
        for(Cart cart:carts){
            Products products=productsMapper.getProductById(cart.getProductId());
            Cart2 cart2=new Cart2();
            cart2.setId(cart.getId());
            cart2.setUserId(cart.getUserId());
            cart2.setProductId(products.getId());
            cart2.setName(products.getName());
            cart2.setQuantity(cart.getQuantity());
            cart2.setPrice(products.getPrice());
            cart2.setStatus(products.getStatus());
            cart2.setTotalPrice(cart.getQuantity()*products.getPrice());
            totalPrice+=cart2.getTotalPrice();
            cart2.setStock(products.getStock());
            cart2.setIconUrl(products.getIconUrl());
            cart2s.add(cart2);
        }
        CartList cartList=new CartList();
        cartList.setLists(cart2s.toArray(new Cart2[cart2s.size()]));
        cartList.setTotalPrice(totalPrice);
        return cartList;
    }

    public CartList saveCarts(Cart cart,int uid){
        cart.setUserId(uid);
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        cart.setCreateTime(date);
        List<Cart> carts=cartsMapper.getByUid(uid);
        for(Cart cart1:carts)
        {
            if(cart1.getProductId()==cart.getProductId()){
                cartsMapper.updateCartAmount(cart1.getQuantity()+cart.getQuantity(),cart.getProductId());
                return findAllCarts(uid);
            }
        }
        cartsMapper.insertCart(cart);
        return findAllCarts(uid);
    }
}
