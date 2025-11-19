package com.example.cartService.repository;

import com.example.cartService.model.CartItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CartRepository {

    private final JdbcTemplate jdbcTemplate;

    public CartRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<CartItem> cartRowMapper = new RowMapper<CartItem>() {
        @Override
        public CartItem mapRow(ResultSet rs, int rowNum) throws SQLException {
            CartItem item = new CartItem();
            item.setId(rs.getLong("id"));
            item.setProductId(rs.getLong("product_id"));
            item.setName(rs.getString("name"));
            item.setPrice(rs.getDouble("price"));
            item.setSku(rs.getString("sku"));
            item.setImage(rs.getString("image"));
            item.setQuantity(rs.getInt("quantity"));
            return item;
        }
    };

    public List<CartItem> findAll() {
        return jdbcTemplate.query("SELECT * FROM cart", cartRowMapper);
    }

    public int insertItem(CartItem item) {
        return jdbcTemplate.update(
                "INSERT INTO cart (product_id, name, price, sku, image, quantity) VALUES (?, ?, ?, ?, ?, ?)",
                item.getProductId(),
                item.getName(),
                item.getPrice(),
                item.getSku(),
                item.getImage(),
                item.getQuantity()
        );
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM cart WHERE id = ?", id);
    }

    public int emptyCart() {
        return jdbcTemplate.update("DELETE FROM cart");
    }
}
