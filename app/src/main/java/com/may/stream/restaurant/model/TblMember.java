package com.may.stream.restaurant.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by may on 1/5/2018.
 */
@DatabaseTable(tableName="TblMember")
public class TblMember implements Serializable {

    @DatabaseField(id = true, useGetSet = true)
    private String guid;

    @DatabaseField( useGetSet = true)
    private String success;

    @DatabaseField( useGetSet = true)
    private String message;

    @SerializedName("member_id")
    @Expose
    @DatabaseField(useGetSet = true)
    private String member_id;

    @SerializedName("first_name")
    @Expose
    @DatabaseField(useGetSet = true)
    private String first_name;

    @SerializedName("last_name")
    @Expose
    @DatabaseField(useGetSet = true)
    private String last_name;

    @SerializedName("birth_date")
    @Expose
    @DatabaseField(useGetSet = true)
    private String birth_date;

    @SerializedName("date_create")
    @Expose
    @DatabaseField(useGetSet = true)
    private String date_create;

    @SerializedName("last_date")
    @Expose
    @DatabaseField(useGetSet = true)
    private String last_date;

    @SerializedName("tel")
    @Expose
    @DatabaseField(useGetSet = true)
    private String tel;

    @SerializedName("user_name")
    @Expose
    @DatabaseField(useGetSet = true)
    private String user_name;

    @SerializedName("password")
    @Expose
    @DatabaseField(useGetSet = true)
    private String password;

    @SerializedName("email")
    @Expose
    @DatabaseField(useGetSet = true)
    private String email;

    @SerializedName("authen")
    @Expose
    @DatabaseField(useGetSet = true)
    private String authen;

    @SerializedName("company_id")
    @Expose
    @DatabaseField(useGetSet = true)
    private int company_id;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getDate_create() {
        return date_create;
    }

    public void setDate_create(String date_create) {
        this.date_create = date_create;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthen() {
        return authen;
    }

    public void setAuthen(String authen) {
        this.authen = authen;
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
