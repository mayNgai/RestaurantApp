package com.may.stream.restaurant.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by may on 1/19/2018.
 */

public class TblDataLogin implements Serializable {
    private String success;
    private String message;
    private List<TblProducts> products;
    private List<TblProductTypes> productTypes;
    private List<TblDiscount> discounts;
    private List<TblTables> tables;
    private List<TblStore> stores;
    private List<TblMember> members;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<TblProducts> getProducts() {
        return products;
    }

    public void setProducts(List<TblProducts> products) {
        this.products = products;
    }

    public List<TblProductTypes> getProductTypes() {
        return productTypes;
    }

    public void setProductTypes(List<TblProductTypes> productTypes) {
        this.productTypes = productTypes;
    }

    public List<TblDiscount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<TblDiscount> discounts) {
        this.discounts = discounts;
    }

    public List<TblTables> getTables() {
        return tables;
    }

    public void setTables(List<TblTables> tables) {
        this.tables = tables;
    }

    public List<TblStore> getStores() {
        return stores;
    }

    public void setStores(List<TblStore> stores) {
        this.stores = stores;
    }

    public List<TblMember> getMembers() {
        return members;
    }

    public void setMembers(List<TblMember> members) {
        this.members = members;
    }
}
