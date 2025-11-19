package com.example.cartService.controller;

import com.example.cartService.model.CartItem;
import com.example.cartService.repository.CartRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "*")
public class CartController {

    private final CartRepository cartRepository;

    public CartController(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    // GET /cart
    @GetMapping
    public List<CartItem> getCartItems() {
        return cartRepository.findAll();
    }

    // POST /cart
    @PostMapping
    public String addToCart(@RequestBody CartItem item) {
        cartRepository.insertItem(item);
        return "Added to cart!";
    }

    // DELETE /cart/{id}
    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable Long id) {
        cartRepository.deleteById(id);
        return "Item removed!";
    }

    // DELETE /emptycart
    @DeleteMapping("/emptycart")
    public String emptyCart() {
        cartRepository.emptyCart();
        return "Cart emptied!";
    }
}
