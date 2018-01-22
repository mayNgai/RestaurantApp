package com.may.stream.restaurant.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by may on 1/5/2018.
 */
@DatabaseTable(tableName="TblAuthen")
public class TblAuthen implements Serializable {
    @DatabaseField(id = true, useGetSet = true)
    private String guid;

    @DatabaseField(useGetSet = true)
    private String authen_id;

    @DatabaseField(useGetSet = true)
    private String authen_name;

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

    public String getAuthen_name() {
        return authen_name;
    }

    public void setAuthen_name(String authen_name) {
        this.authen_name = authen_name;
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

    public String getAuthen_id() {
        return authen_id;
    }

    public void setAuthen_id(String authen_id) {
        this.authen_id = authen_id;
    }


}
