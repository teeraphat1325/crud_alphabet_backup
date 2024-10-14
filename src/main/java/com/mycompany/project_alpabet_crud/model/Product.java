/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project_alpabet_crud.model;

import com.mycompany.project_alpabet_crud.dao.CategoryDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author werapan
 */
public class Product {
    private int id;
    private String name;
    private float price;
    private String size;
    private String sweetLevel;
    private String type;
    private int categoryId;
    private Category category;

    public Product(int id, String name, float price, String size, String sweetLevel, String type, int categoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.size = size;
        this.sweetLevel = sweetLevel;
        this.type = type;
        this.categoryId = categoryId;
    }
    
    public Product(String name, float price, String size, String sweetLevel, String type, int categoryId) {
        this.id = -1;
        this.name = name;
        this.price = price;
        this.size = size;
        this.sweetLevel = sweetLevel;
        this.type = type;
        this.categoryId = categoryId;
    }
    
    public Product() {
        this.id = -1;
        this.name = "";
        this.price = 0;
        this.size = "";
        this.sweetLevel = "";
        this.type = "";
        this.categoryId = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSweetLevel() {
        return sweetLevel;
    }

    public void setSweetLevel(String sweetLevel) {
        this.sweetLevel = sweetLevel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", price=" + price + ", size=" + size + ", sweetLevel=" + sweetLevel + ", type=" + type + ", categoryId=" + categoryId + ", category=" + category + '}';
    }

   
    
    public static Product fromRS(ResultSet rs) {
        Product product = new Product();
        try {
            product.setId(rs.getInt("product_id"));
            product.setName(rs.getString("product_name"));
            product.setPrice(rs.getFloat("product_price"));
            product.setSize(rs.getString("product_size"));
            product.setSweetLevel(rs.getString("product_sweet_level"));
            product.setType(rs.getString("product_type"));
            product.setCategoryId(rs.getInt("categoryId_id"));
            
            CategoryDao categoryDao = new CategoryDao();
            Category category = categoryDao.get(product.getCategoryId());
            product.setCategory(category);

        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return product;
    }
}
