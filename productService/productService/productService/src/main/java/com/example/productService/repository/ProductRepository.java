package com.example.productService.repository;

import com.example.productService.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
}

    // convert database rows to product objects
    private final RowMapper<Product> productRowMapper = new RowMapper<Product>() {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();
            product.setId(rs.getLong("id"));
            product.setName(rs.getString("name"));
            product.setDescription(rs.getString("description"));
            product.setPrice(rs.getDouble("price"));
            product.setSku(rs.getString("sku"));
            product.setIsActive(rs.getBoolean("is_active"));
            product.setImage(rs.getString("image"));
            return product;
        }
    };

    // GET all products
    public List<Product> findAll() {
        String sql = "SELECT * FROM products";
        return jdbcTemplate.query(sql, productRowMapper);
    }

    // GET product by ID
    public Product findById(Long id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, productRowMapper, id);
    }

    // POST - Create new product
    public int save(Product product) {
        String sql = "INSERT INTO products (name, description, price, sku, is_active, image) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getSku(),
                product.getIsActive(),
                product.getImage()
        );
    }

    // PUT - Update existing product
    public int update(Long id, Product product) {
        String sql = "UPDATE products SET name = ?, description = ?, price = ?, sku = ?, is_active = ?, image = ? WHERE id = ?";
        return jdbcTemplate.update(sql,
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getSku(),
                product.getIsActive(),
                product.getImage(),
                id
        );
    }

    // DELETE product by ID
    public int deleteById(Long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}

