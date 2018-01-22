package com.may.stream.restaurant.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by may on 1/5/2018.
 */
@DatabaseTable(tableName="TblOrder")
public class TblOrder implements Serializable {
    @DatabaseField(id = true, useGetSet = true)
    private String guid;

    @DatabaseField(useGetSet = true)
    private String order_id;

    @DatabaseField(useGetSet = true)
    private String product_name;

    @DatabaseField(useGetSet = true)
    private String product_id;

    @DatabaseField(useGetSet = true)
    private String amount;

    @DatabaseField(useGetSet = true)
    private String price;

    @DatabaseField(useGetSet = true)
    private String table_id;

    @DatabaseField(useGetSet = true)
    private String date_create;

    @DatabaseField(useGetSet = true)
    private String last_date;

    @DatabaseField(useGetSet = true)
    private String status;

    @DatabaseField(useGetSet = true)
    private int status_id;

    @DatabaseField(useGetSet = true)
    private int company_id;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTable_id() {
        return table_id;
    }

    public void setTable_id(String table_id) {
        this.table_id = table_id;
    }

    public String getDate_create() {
        return date_create;
    }

    public void setDate_create(String date_create) {
        this.date_create = date_create;
    }

    public String getLast_date() {
        return last_date;
    }

    public void setLast_date(String last_date) {
        this.last_date = last_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }
}
