/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Address;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author shruti
 */
public class AddressDao {

    public boolean addAddress(String street, String city, String state, String pincode) {
        boolean flag = true;
        try {

            String sql = "INSERT INTO `clothing`.`address` (`street`,`state`,`city`,`zipcode`) VALUES ('" + street + "','" + city + "','" + state + "'," + pincode + ");";
            Statement statement = SqlUtil.getConnection().createStatement();

            statement.executeUpdate(sql);
            ResultSet generatedKeys = statement.getGeneratedKeys();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            flag = false;
        }
        return flag;
    }

    public static Address getAddressForUser(String id) {
        Address address = null;
        try {

            String sql = "SELECT `address`.`address_id`,`address`.`street`,`address`.`state`,`address`.`city`,`address`.`zipcode`\n"
                    + "FROM `clothing`.`address`, `clothing`.`usermaster` where `usermaster`.`address_id`=`address`.`address_id` and `usermaster`.`userid`=" + id + ";";
            Statement statement = SqlUtil.getConnection().createStatement();

            ResultSet executeQuery = statement.executeQuery(sql);
            if (executeQuery != null && executeQuery.next()) {
                address = new Address(executeQuery.getInt(1), executeQuery.getString(2), executeQuery.getString(3), executeQuery.getString(4), executeQuery.getInt(5));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return address;
    }
   

}
