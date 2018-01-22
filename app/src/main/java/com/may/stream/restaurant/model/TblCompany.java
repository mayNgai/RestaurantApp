package com.may.stream.restaurant.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by may on 1/9/2018.
 */
@DatabaseTable(tableName="TblCompany")
public class TblCompany implements Serializable {
    @DatabaseField(id = true, useGetSet = true)
    private String guid;

    @DatabaseField(useGetSet = true)
    private String success;

    @DatabaseField(useGetSet = true)
    private String message;

    @SerializedName("company_id")
    @Expose
    @DatabaseField(useGetSet = true)
    private String company_id;

    @SerializedName("company_name")
    @Expose
    @DatabaseField(useGetSet = true)
    private String company_name;

    @SerializedName("date_create")
    @Expose
    @DatabaseField(useGetSet = true)
    private String date_create;

    @SerializedName("last_date")
    @Expose
    @DatabaseField(useGetSet = true)
    private String last_date;

    @SerializedName("status_id")
    @Expose
    @DatabaseField(useGetSet = true)
    private int status_id;

    @SerializedName("status")
    @Expose
    @DatabaseField(useGetSet = true)
    private String status;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
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

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
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

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
