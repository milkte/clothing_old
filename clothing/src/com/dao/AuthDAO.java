/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Seller;
import com.model.User;
import com.mysql.jdbc.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthDAO {

    static Connection con;
    static String url;

    public static final String USER_ROLE = "USER";
    public static final String SELLER_ROLE = "SELLER";
    public static final String ADMIN = "ADMIN";

    /**
     * Create DB connection
     *
     * @return
     */
    
    public static int checkUserPass(String userName, String password) {
        try {
            ResultSet result = null;
            PreparedStatement ps = new com.mysql.jdbc.PreparedStatement((MySQLConnection) SqlUtil.getConnection(), url);
            try {
                String sql = "SELECT userid FROM usermaster WHERE username LIKE ? and password LIKE ?";
                ps = SqlUtil.getConnection().prepareStatement(sql);
                ps.setString(1, userName);
                ps.setString(2, password);
                result = ps.executeQuery();
            } catch (SQLException ex) {

            }
            if (result.next()) {
                return result.getInt(1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(AuthDAO.class.getName()).log(Level.SEVERE, null,
                    ex);

        }
        return 0;
    }

    public static User getUserByUserNamePassword(String userName, String password) {
        User user = null;
        try {
            ResultSet result = null;

            PreparedStatement ps = new com.mysql.jdbc.PreparedStatement((MySQLConnection) SqlUtil.getConnection(), url);
            try {
                String sql = "SELECT `usermaster`.`userid`, `usermaster`.`username`, `usermaster`.`password`,`usermaster`.`firstname`,`usermaster`.`lastname`,`usermaster`.`role` FROM `clothing`.`usermaster` where `usermaster`.`username`= ? and `usermaster`.`password`=?;";
                ps = SqlUtil.getConnection().prepareStatement(sql);
                ps.setString(1, userName);
                ps.setString(2, password);
                result = ps.executeQuery();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            try {
                if (result != null && result.next()) {
                    user = new User(result.getInt(1), result.getString(2), result.getString(4), result.getString(5), true);
                    user.setRole(result.getString(6));
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (SQLException ex) {
            Logger.getLogger(AuthDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
        return user;
    }

    public static User getUserByUserName(String userName) {
        User user = null;
        try {
            ResultSet result = null;

            PreparedStatement ps = new com.mysql.jdbc.PreparedStatement((MySQLConnection) SqlUtil.getConnection(), url);
            try {
                String sql = "SELECT `usermaster`.`userid`, `usermaster`.`username`, `usermaster`.`password`,`usermaster`.`firstname`,`usermaster`.`lastname`,`usermaster`.`email`,`usermaster`.`phoneno`,`usermaster`.`role` FROM `clothing`.`usermaster` where `usermaster`.`username`= ? ;";
                ps = SqlUtil.getConnection().prepareStatement(sql);
                ps.setString(1, userName);

                result = ps.executeQuery();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            try {
                if (result != null && result.next()) {
                    user = new User(result.getInt(1), result.getString(2), null, result.getString(4), result.getString(5), true, null, result.getString(6), result.getString(7), result.getString(8));
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace(System.out);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AuthDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
        return user;
    }

    public static User getUserById(int userId) {
        try {
            ResultSet result = null;

            PreparedStatement ps = new com.mysql.jdbc.PreparedStatement((MySQLConnection) SqlUtil.getConnection(), url);
            try {
                String sql = "SELECT userid,username,password,firstname,lastname FROM usermaster WHERE userid LIKE ?";
                ps = SqlUtil.getConnection().prepareStatement(sql);
                ps.setInt(1, userId);
                result = ps.executeQuery();
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }

            try {
                if (result.next()) {
                    try {
                        User user = new User(result.getInt(1), result.getString(2), result.getString(3));
                        user.setFirstName(result.getString(4));
                        user.setLastName(result.getString(5));
                        return user;
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(AuthDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }
    public static Seller getSellerById(int userId) {
        try {
            ResultSet result = null;

            PreparedStatement ps = new com.mysql.jdbc.PreparedStatement((MySQLConnection) SqlUtil.getConnection(), url);
            try {
                String sql = "SELECT userid,username,password,firstname FROM usermaster WHERE userid LIKE ?";
                ps = SqlUtil.getConnection().prepareStatement(sql);
                ps.setInt(1, userId);
                result = ps.executeQuery();
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }

            try {
                if (result.next()) {
                    try {
                        Seller seller = new Seller();
                        seller.setUser(new User(result.getInt(1), result.getString(2), result.getString(3)));
                        seller.setCompany(result.getString(4));
                        return seller;
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(AuthDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

    
    public static int enterNewUser(String userName, String password) {
        int flag = 1;
        try {
            PreparedStatement ps = new com.mysql.jdbc.PreparedStatement((MySQLConnection) SqlUtil.getConnection(), url);
            ResultSet result = null;

            try {
                String sql = "insert into `clothing`.`usermaster`(`usermaster`.`username`,`usermaster`.`password`) values (?,?)";
                ps = SqlUtil.getConnection().prepareStatement(sql);
                ps.setString(1, userName);
                ps.setString(2, password);
                ps.executeUpdate();
                System.out.println("result is " + result);
            } catch (SQLException ex) {
                ex.printStackTrace();
                flag = 0;
            }

            return flag;
        } catch (SQLException ex) {
            Logger.getLogger(AuthDAO.class.getName()).log(Level.SEVERE, null, ex);
            flag = 0;
        }
        return flag;
    }

    public static int updateRole(String userid, String role) {
        int flag = 1;
        ResultSet result = null;

        try {
            String sql = "UPDATE `clothing`.`usermaster` SET `role` = '" + role + "' WHERE `userid` = " + userid + ";";
            Statement statement = SqlUtil.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("result is " + result);
            con.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            flag = 0;
        }
        
        return flag;
    }

    public static List<User> retrieveUserByRole(String role) {
        List<User> users = new LinkedList<>();
        ResultSet result = null;

        try {
            String sql = "SELECT `usermaster`.`userid`, `usermaster`.`username`, `usermaster`.`password`,`usermaster`.`firstname`,`usermaster`.`lastname`,`usermaster`.`email`,`usermaster`.`phoneno`,`usermaster`.`role` FROM `clothing`.`usermaster` where `usermaster`.`role`= '" + role + "' ;";
            Statement statement = SqlUtil.getConnection().createStatement();
            result=statement.executeQuery(sql);
            System.out.println("result is " + result);
            if (result != null) {
                while (result.next()) {
                    System.out.println("user " +  result.getInt(1)); ;
                    users.add(new User(result.getInt(1), result.getString(2), null, result.getString(4), result.getString(5), true, null, result.getString(6), result.getString(7), result.getString(8)));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return users;
    }
    
    
    public static List<User> retrieveUserLike(String keyword) {
        List<User> users = new LinkedList<>();
        ResultSet result = null;

        try {
            String sql = "SELECT `usermaster`.`userid`, `usermaster`.`username`, `usermaster`.`password`,`usermaster`.`firstname`,`usermaster`.`lastname`,`usermaster`.`email`,`usermaster`.`phoneno`,`usermaster`.`role` FROM `clothing`.`usermaster` where  `usermaster`.`role` ='USER' and (`usermaster`.`firstname` like '%" + keyword + "%' or `usermaster`.`lastname` like '%" + keyword + "%') ;";
            Statement statement = SqlUtil.getConnection().createStatement();
            result=statement.executeQuery(sql);
            System.out.println("result is " + result);
            if (result != null) {
                while (result.next()) {
                    users.add(new User(result.getInt(1), result.getString(2), null, result.getString(4), result.getString(5), true, null, result.getString(6), result.getString(7), result.getString(8)));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return users;
    }

    public static boolean enterUserName(String username, String password, String firstName,
            String lastName, String email, String phno, String city, String state, String country, long pincode, String role) {
        try {
            PreparedStatement ps = new com.mysql.jdbc.PreparedStatement((MySQLConnection) SqlUtil.getConnection(), url);
            boolean flag = true;
            int addressId = 0;
            try {
                String sql = "INSERT INTO `clothing`.`address` (`street`,`state`,`city`,`zipcode`) VALUES ('" + city + "','" + state + "','" + country + "'," + pincode + ");";
                Statement statement = SqlUtil.getConnection().createStatement();
                int executeUpdate = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys != null && generatedKeys.next()) {
                    addressId = generatedKeys.getInt(1);
                }

            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
            try {
                String sql = "INSERT INTO `clothing`.`usermaster` (`username`,`password`,`firstname`,`lastname`,`address_id`,`email`,`phoneno`,`role`) VALUES (?,?,?,?,?,?,?,?);";
                ps = SqlUtil.getConnection().prepareStatement(sql);
                ps.setString(3, firstName);
                ps.setString(4, lastName);
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setInt(5, addressId);
                ps.setString(6, email);
                ps.setString(7, phno);
                ps.setString(8, role);
                ps.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
                flag = false;
            }
            return flag;
        } catch (SQLException ex) {
            Logger.getLogger(AuthDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean isUserNameAvailable(String userName) {
        boolean flag = true;
        try {
            PreparedStatement ps = new com.mysql.jdbc.PreparedStatement((MySQLConnection) SqlUtil.getConnection(), url);
            ResultSet result = null;
            flag = true;
            try {
                String sql = "select * from usermaster where username = ?";
                ps = SqlUtil.getConnection().prepareStatement(sql);
                ps.setString(1, userName);
                result = ps.executeQuery();
            } catch (SQLException ex) {
                flag = false;
            }

            try {
                if (result.next()) {
                    flag = false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(AuthDAO.class.getName()).log(Level.SEVERE, null,
                        ex);
            }
            return flag;
        } catch (SQLException ex) {
            Logger.getLogger(AuthDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public static void DB_Close() throws Throwable {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
