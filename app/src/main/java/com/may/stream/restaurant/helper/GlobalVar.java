package com.may.stream.restaurant.helper;

import com.may.stream.restaurant.model.TblCompany;
import com.may.stream.restaurant.model.TblDiscount;
import com.may.stream.restaurant.model.TblMember;
import com.may.stream.restaurant.model.TblProductTypes;
import com.may.stream.restaurant.model.TblProducts;
import com.may.stream.restaurant.model.TblStore;
import com.may.stream.restaurant.model.TblTables;
import com.may.stream.restaurant.until.TaskController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by may on 1/9/2018.
 */

public class GlobalVar {

    public static boolean logOut(){
        boolean success = true;
        try {
            TaskController taskController = new TaskController();
            List<TblMember> memberList = taskController.getAllMember();
            List<TblCompany> companyList = taskController.getAllCompany();
            List<TblProducts> products = taskController.getAllProducts();
            List<TblProductTypes> types = taskController.getAllProductTypes();
            List<TblDiscount> discounts = taskController.getAllDiscount();
            List<TblTables> tables = taskController.getAllTables();
            List<TblStore> stores = taskController.getAllStore();
            if(companyList.size()>0)
                success = taskController.deleteCompany(companyList);
            if(memberList.size()>0)
                success = taskController.deleteMember(memberList);
            if(products.size()>0)
                success = taskController.deleteProduct(products);
            if(types.size()>0)
                success = taskController.deleteProductType(types);
            if(discounts.size()>0)
                success = taskController.deleteDiscount(discounts);
            if(tables.size()>0)
                success = taskController.deleteTable(tables);
            if(stores.size()>0)
                success = taskController.deleteStore(stores);
        }catch (Exception e){
            e.printStackTrace();
        }
        return success;
    }

    public static List<TblCompany> getCompany(){
        List<TblCompany> list = new ArrayList<TblCompany>();
        try {
            TaskController taskController = new TaskController();
            list = taskController.getAllCompany();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public static List<TblMember> getMember(){
        List<TblMember> listMembers = new ArrayList<TblMember>();
        try {
            TaskController taskController = new TaskController();
            listMembers = taskController.getAllMember();
        }catch (Exception e){
            e.printStackTrace();
        }
        return listMembers;
    }

    public static List<TblProductTypes> getProductTypes(){
        List<TblProductTypes> list = new ArrayList<TblProductTypes>();
        try {
            TaskController taskController = new TaskController();
            list = taskController.getAllProductTypes();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public static List<TblProducts> getProducts(){
        List<TblProducts> list = new ArrayList<TblProducts>();
        try {
            TaskController taskController = new TaskController();
            list = taskController.getAllProducts();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public static List<TblDiscount> getDiscounts(){
        List<TblDiscount> list = new ArrayList<TblDiscount>();
        try {
            TaskController taskController = new TaskController();
            list = taskController.getAllDiscount();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public static List<TblTables> getTables(){
        List<TblTables> list = new ArrayList<TblTables>();
        try {
            TaskController taskController = new TaskController();
            list = taskController.getAllTables();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public static boolean isTextValid(String text) {
        //TODO: Replace this with your own logic
        return text.length()>0;
    }

    public static boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    public static boolean isPhoneNumberValid(String phone) {
        //TODO: Replace this with your own logic
        return phone.length()==10;
    }

    public static boolean isPassWordValid(String pass) {
        //TODO: Replace this with your own logic
        return (pass.length()>7&&pass.length()<=20);
    }

    public static boolean isConfirmPassWordValid(String pass , String confirm) {
        //TODO: Replace this with your own logic
        return (pass.equals(confirm));
    }

}
