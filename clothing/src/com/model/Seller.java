/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.util.Map;
import java.util.Objects;

/**
 *
 * @author shruti
 */
public class Seller {

    private User user;
    private String company;

    public Seller() {
    }

    public Seller(User user, String company) {
        this.user = user;
        this.company = company;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.user);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Seller other = (Seller) obj;
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Seller{" + "user=" + user + ", company=" + company + '}';
    }

}
