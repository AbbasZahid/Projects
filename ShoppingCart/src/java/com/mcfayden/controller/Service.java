/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcfayden.controller;
import com.macfayden.model.CommerceItem;
import com.macfayden.model.Product;
import com.macfayden.model.ShoppingCart;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 *
 * @author Abbas Zahid
 */

@Path("/service")
@SessionScoped
public class Service  implements Serializable{
    
    ShoppingCart shoppingCart;
    List<Product> products;
    
    @GET
    @Path("/product")
    @Produces("application/json")
    public Response productsGet () {
        return Response.status(200).entity(getProducts()).build();
    }

    /**
     *
     * @param request
     * @return
     */
    
    @GET
    @Path("/shoppingcart")
    @Produces("application/json")
    public Response shoppingcartGet(@Context HttpServletRequest request){
        HttpSession session = request.getSession(true);
        if(session != null && session.getAttribute("shoppingCart") != null){
            setShoppingCart((ShoppingCart)session.getAttribute("shoppingCart"));
        }
        for (CommerceItem commerceItem : getShoppingCart().getItems()) {
            if(getShoppingCart().getAmount() == null){
                getShoppingCart().setAmount(commerceItem.getAmount());
            }else{
                getShoppingCart().setAmount(getShoppingCart().getAmount().add(commerceItem.getAmount()));
            }
        }
        return Response.status(200).entity(getShoppingCart()).build();
    }
    
    @POST
    @Path("/shoppingcartItems")
    @Produces("application/json")
    public Response shoppingcartItemsPost(@QueryParam("product_id") String product_id, 
            @QueryParam("quantity") Integer quantity, @Context HttpServletRequest request ){
        
        HttpSession session = request.getSession(true);
        if(session != null && session.getAttribute("shoppingCart") != null){
            setShoppingCart((ShoppingCart)session.getAttribute("shoppingCart"));
        }
        
        CommerceItem commerceItem;
        Integer commerceId = null;
        
        if((product_id == null) || (quantity == 0)){
            System.out.println("Error"); 
            return Response.status(Response.Status.BAD_REQUEST).build();
        }else{
            commerceItem = new CommerceItem();
            
            if(session != null && session.getAttribute("lastCommerceId") != null){
                commerceId = (Integer) session.getAttribute("lastCommerceId");
                commerceId++;
            }
            else{
                commerceId = 1;
            }
            commerceItem.setCommerceId(String.valueOf(commerceId));
            
            commerceItem.setProduct_id(product_id);
            commerceItem.setQuantity(quantity);
            for(Product product :getProducts()){
               if(product.getProductId().equals(product_id)){
                    commerceItem.setAmount(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
               }
            }
            getShoppingCart().getItems().add(commerceItem);
            session.setAttribute("lastCommerceID", commerceId);
            session.setAttribute("shoppingCart", getShoppingCart());
        }
        return Response.status(200).entity(commerceItem).build();
    }
    
    
    @DELETE
    @Path("/shoppingcartItemsIdDelete")
    @Produces("application/json")
    public Response shoppingcartItemsIdDelete (@QueryParam("commerceId") String commerceId, @Context HttpServletRequest request){
        HttpSession session = request.getSession(true);
        if(session != null && session.getAttribute("shoppingCart") != null){
            setShoppingCart((ShoppingCart)session.getAttribute("shoppingCart"));
        }
        
        if(commerceId == null){
            System.out.println("Commerce id is required");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if(!getShoppingCart().getItems().isEmpty()){
            for(CommerceItem commerceItem : getShoppingCart().getItems()){
                if(commerceItem.getCommerceId().equals(commerceId)){
                    getShoppingCart().getItems().remove(commerceItem);
                }
            }
        }
        session.setAttribute("shoppingCart", getShoppingCart());
        return Response.status(200).entity(getShoppingCart()).build();
    }
    
    
    // Getter & Setters
    public ShoppingCart getShoppingCart() {
        if(shoppingCart == null){
            shoppingCart = new ShoppingCart();
        }
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public List<Product> getProducts() {
        if(products == null){
            this.products = new ArrayList<Product>();
            Product product1 = new Product("ID-1","IPad Air","Image1",BigDecimal.valueOf(99.99));
            Product product2 = new Product("ID-2","Iphone 7","Image 2",BigDecimal.valueOf(99.99));
            this.products.add(product1);
            this.products.add(product2);
        }
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}