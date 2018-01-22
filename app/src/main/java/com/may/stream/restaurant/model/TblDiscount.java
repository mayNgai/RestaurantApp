package com.may.stream.restaurant.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by may on 1/5/2018.
 */
@DatabaseTable(tableName="TblDiscount")
public class TblDiscount implements Serializable {
    @DatabaseField(id = true, useGetSet = true)
    private String guid;

    @DatabaseField(useGetSet = true)
    private String success;

    @DatabaseField(useGetSet = true)
    private String message;

    @DatabaseField(useGetSet = true)
    private String discount_id;

    @DatabaseField(useGetSet = true)
    private String discount_name;

    @DatabaseField(useGetSet = true)
    private String percent;

    @DatabaseField(useGetSet = true)
    private String date_create;

    @DatabaseField(useGetSet = true)
    private String last_date;

    @DatabaseField(useGetSet = true)
    private String expired_date;

    @DatabaseField(useGetSet = true)
    private int company_id;


    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getDiscount_id() {
        return discount_id;
    }

    public void setDiscount_id(String discount_id) {
        this.discount_id = discount_id;
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

    public String getExpired_date() {
        return expired_date;
    }

    public void setExpired_date(String expired_date) {
        this.expired_date = expired_date;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getDiscount_name() {
        return discount_name;
    }

    public void setDiscount_name(String discount_name) {
        this.discount_name = discount_name;
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

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }
}
