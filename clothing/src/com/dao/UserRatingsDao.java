/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.User;
import com.model.UserRatings;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author shruti
 */
public class UserRatingsDao {

    public boolean addUserRating(String comment, String user, String item, String rating) {
        boolean flag = true;
        try {
            String sql = "INSERT INTO `clothing`.`user_ratings`  "
                    + "(`user`,  "
                    + "`item`,  "
                    + "`rating`,  "
                    + "`comment`)  "
                    + "VALUES (?,?,?,?);";
            Statement statement = SqlUtil.getConnection().createStatement();
            statement.executeUpdate(sql);
//                }
        } catch (SQLException ex) {
            ex.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public List<UserRatings> getUserRatingForItem(String item) {
        List<UserRatings> userRatings = new LinkedList<>();
        ResultSet result;

        try {
            String sql = "SELECT `user_ratings`.`id`,\n"
                    + "    `user_ratings`.`user`,\n"
                    + " `usermaster`.`firstname`,\n"
                    + "    `usermaster`.`lastname`,\n"
                    + "    `user_ratings`.`rating`,\n"
                    + "    `user_ratings`.`comment`\n"
                    + "FROM `clothing`.`user_ratings` , `clothing`.`usermaster` where  `user_ratings`.`user`= `usermaster`.`userid` and item=" + item + ";";
            Statement statement = SqlUtil.getConnection().createStatement();
            result = statement.executeQuery(sql);
            if (result != null) {
                while (result.next()) {
                    userRatings.add(new UserRatings(result.getInt(1), null, new User(0, null, result.getString(3), result.getString(4), true), result.getInt(5), result.getString(6)));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return userRatings;
    }
}
