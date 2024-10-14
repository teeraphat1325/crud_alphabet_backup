/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project_alpabet_crud.dao;

import com.mycompany.project_alpabet_crud.helper.DatabaseHelper;
import com.mycompany.project_alpabet_crud.model.Stock;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kissa
 */
public class StockDao implements Dao<Stock>{

    @Override
    public Stock get(int id) {
        Stock stock = null;
        String sql = "SELECT * FROM stock WHERE stock_id";
        Connection conn = DatabaseHelper.getConnect();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                stock = Stock.fromRS(rs);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return stock;
    }

    public Stock getByName(String name) {
        Stock stock = null;
        String sql = "SELECT * FROM customer WHERE stock_name=?";
        Connection conn = DatabaseHelper.getConnect();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                stock = Stock.fromRS(rs);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return stock;
    }

    public List<Stock> getAll() {
        ArrayList<Stock> list = new ArrayList();
        String sql = "SELECT * FROM stock";
        Connection conn = DatabaseHelper.getConnect();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Stock user = Stock.fromRS(rs);
                list.add(user);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
    @Override
    public List<Stock> getAll(String where, String order) {
        ArrayList<Stock> list = new ArrayList();
        String sql = "SELECT * FROM stock where " + where + " ORDER BY" + order;
        Connection conn = DatabaseHelper.getConnect();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Stock user = Stock.fromRS(rs);
                list.add(user);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    

    public List<Stock> getAll(String order) {
        ArrayList<Stock> list = new ArrayList();
        String sql = "SELECT * FROM stock  ORDER BY" + order;
        Connection conn = DatabaseHelper.getConnect();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Stock user = Stock.fromRS(rs);
                list.add(user);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }


    @Override
    public Stock save(Stock obj) {
        String sql = "INSERT INTO stock (stock_name, stock_total, stock_amount)"
                + "VALUES(?,?,?)";
        Connection conn = DatabaseHelper.getConnect();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getName()); 
            stmt.setInt(2, obj.getTotal());
            stmt.setInt(3, obj.getAmount());

            stmt.executeUpdate();
            int id = DatabaseHelper.getInsertedId(stmt);
            obj.setId(id);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return obj;
    }

    @Override
    public Stock update(Stock obj) {
        String sql = "UPDATE stock"
                + " SET stock_name = ?, stock_total = ?, stock_amount = ?"
                + " WHERE stock_id = ?";
        Connection conn = DatabaseHelper.getConnect();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getName()); 
            stmt.setInt(2, obj.getTotal());
            stmt.setInt(3, obj.getAmount());
//            System.out.println(stmt);
            int ret = stmt.executeUpdate();
            System.out.println(ret);
            return obj;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public int delete(Stock obj) {
       String sql = "DELETE FROM stock WHERE stock_id=?";
        Connection conn = DatabaseHelper.getConnect();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            int ret = stmt.executeUpdate();
            return ret;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;        
    }
    

    
}
