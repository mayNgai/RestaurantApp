package com.may.stream.restaurant.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by may on 1/5/2018.
 */
@DatabaseTable(tableName="TblProductTypes")
public class TblProductTypes implements Serializable {
    @DatabaseField(id = true, useGetSet = true)
    private String guid;

    @DatabaseField(useGetSet = true)
    private String success;

    @DatabaseField(useGetSet = true)
    private String message;

    @DatabaseField(useGetSet = true)
    private String product_type_id;

    @DatabaseField(useGetSet = true)
    private String product_type_name;

    @DatabaseField(useGetSet = true)
    private String status;

    @DatabaseField(useGetSet = true)
    private int status_id;

    @DatabaseField(useGetSet = true)
    private String date_create;

    @DatabaseField(useGetSet = true)
    private String last_date;

    @DatabaseField(useGetSet = true)
    private int company_id;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getProduct_type_id() {
        return product_type_id;
    }

    public void setProduct_type_id(String product_type_id) {
        this.product_type_id = product_type_id;
    }

    public String getProduct_type_name() {
        return product_type_name;
    }

    public void setProduct_type_name(String product_type_name) {
        this.product_type_name = product_type_name;
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

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

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
}
