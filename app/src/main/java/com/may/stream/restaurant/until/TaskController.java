package com.may.stream.restaurant.until;

import android.app.Activity;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.may.stream.restaurant.helper.DatabaseHelper;
import com.may.stream.restaurant.model.TblAuthen;
import com.may.stream.restaurant.model.TblCompany;
import com.may.stream.restaurant.model.TblDiscount;
import com.may.stream.restaurant.model.TblMember;
import com.may.stream.restaurant.model.TblOrder;
import com.may.stream.restaurant.model.TblProductTypes;
import com.may.stream.restaurant.model.TblProducts;
import com.may.stream.restaurant.model.TblStore;
import com.may.stream.restaurant.model.TblTables;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by may on 1/5/2018.
 */

public class TaskController {
    protected static DatabaseHelper databaseHelper = null;
    private RuntimeExceptionDao<TblCompany, String> tblCompanyRuntimeDao;
    private RuntimeExceptionDao<TblMember, String> tblMemberRuntimeDao;
    private RuntimeExceptionDao<TblStore, String> tblStoreRuntimeDao;
    private RuntimeExceptionDao<TblTables, String> tblTablesRuntimeDao;
    private RuntimeExceptionDao<TblProductTypes, String> tblProductTypeRuntimeDao;
    private RuntimeExceptionDao<TblProducts, String> tblProductsRuntimeDao;
    private RuntimeExceptionDao<TblOrder, String> tblOrderRuntimeDao;
    private RuntimeExceptionDao<TblDiscount, String> tblDiscountsRuntimeDao;
    private RuntimeExceptionDao<TblAuthen, String> tblAuthenRuntimeDao;
    private Activity _activity;

    private void getConnectDatabaseHelper() {
        try {
            _activity = ApplicationController.getAppActivity();
            databaseHelper  = DatabaseHelper.getHelper(_activity);
            tblCompanyRuntimeDao = databaseHelper.getTblCompany();
            tblMemberRuntimeDao = databaseHelper.getTblMember();
            tblStoreRuntimeDao = databaseHelper.getTblStore();
            tblTablesRuntimeDao = databaseHelper.getTblTables();
            tblProductTypeRuntimeDao = databaseHelper.getTblProductTypes();
            tblProductsRuntimeDao = databaseHelper.getTblProducts();
            tblOrderRuntimeDao = databaseHelper.getTblOrder();
            tblDiscountsRuntimeDao = databaseHelper.getTblDiscount();
            tblAuthenRuntimeDao = databaseHelper.getTblAuthen();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*************insert**********************/
    public boolean createMember(TblMember member){
        try {
            getConnectDatabaseHelper();
            List<TblMember> list = new ArrayList<TblMember>();
            list = getAllMember();
            if(list.size()>0){
                updateMember(member);

            }else {
                member.setGuid(UUID.randomUUID().toString());
                tblMemberRuntimeDao.create(member);
            }

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean createCompany(TblCompany company){
        try {
            getConnectDatabaseHelper();
            List<TblCompany> list = new ArrayList<TblCompany>();
            list = getAllCompany();
            if(list.size()>0){
                updateCompany(company);

            }else {
                company.setGuid(UUID.randomUUID().toString());
                tblCompanyRuntimeDao.create(company);
            }

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean createStore(List<TblStore> storeList){
        try {
            getConnectDatabaseHelper();
            List<TblStore> list = new ArrayList<TblStore>();
            list = getAllStore();
            if(list.size()>0){
                deleteStore(list);
            }else {
                for(TblStore s : storeList){
                    s.setGuid(UUID.randomUUID().toString());
                    tblStoreRuntimeDao.create(s);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean createProductType(TblProductTypes productTypes){
        try {
            getConnectDatabaseHelper();
            productTypes.setGuid(UUID.randomUUID().toString());
            tblProductTypeRuntimeDao.create(productTypes);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean createProductType(List<TblProductTypes> productTypes){
        try {
            getConnectDatabaseHelper();
            List<TblProductTypes> list = new ArrayList<TblProductTypes>();
            list = getAllProductTypes();
            if(list.size()>0){
               // deleteStore(list);
            }else {
                for(TblProductTypes t : productTypes){
                    t.setGuid(UUID.randomUUID().toString());
                    tblProductTypeRuntimeDao.create(t);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean createProduct(TblProducts product){
        try {
            getConnectDatabaseHelper();
            product.setGuid(UUID.randomUUID().toString());
            tblProductsRuntimeDao.create(product);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean createProduct(List<TblProducts> product){
        try {
            getConnectDatabaseHelper();
            List<TblProducts> list = new ArrayList<TblProducts>();
            list = getAllProducts();
            if(list.size()>0){
                // deleteStore(list);
            }else {
                for(TblProducts t : product){
                    t.setGuid(UUID.randomUUID().toString());
                    tblProductsRuntimeDao.create(t);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean createDiscount(TblDiscount discount){
        try {
            getConnectDatabaseHelper();
            discount.setGuid(UUID.randomUUID().toString());
            tblDiscountsRuntimeDao.create(discount);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean createDiscount(List<TblDiscount> discounts){
        try {
            getConnectDatabaseHelper();
            List<TblDiscount> list = new ArrayList<TblDiscount>();
            list = getAllDiscount();
            if(list.size()>0){
                // deleteStore(list);
            }else {
                for(TblDiscount t : discounts){
                    t.setGuid(UUID.randomUUID().toString());
                    tblDiscountsRuntimeDao.create(t);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean createTable(TblTables tables){
        try {
            getConnectDatabaseHelper();
            tables.setGuid(UUID.randomUUID().toString());
            tblTablesRuntimeDao.create(tables);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean createTable(List<TblTables> tables){
        try {
            getConnectDatabaseHelper();
            List<TblTables> list = new ArrayList<TblTables>();
            list = getAllTables();
            if(list.size()>0)
                deleteTable(list);

            for(TblTables t : tables){
                t.setGuid(UUID.randomUUID().toString());
                tblTablesRuntimeDao.create(t);
            }

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /*************update**********************/
    public boolean updateMember(TblMember member){
        try {
            getConnectDatabaseHelper();
            tblMemberRuntimeDao.update(member);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateCompany(TblCompany company){
        try {
            getConnectDatabaseHelper();
            tblCompanyRuntimeDao.update(company);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateProductType(TblProductTypes productTypes){
        try {
            getConnectDatabaseHelper();
            UpdateBuilder<TblProductTypes, String> update = tblProductTypeRuntimeDao
                    .updateBuilder();
            update.updateColumnValue("product_type_name", productTypes.getProduct_type_name());
            update.where().eq("product_type_id", productTypes.getProduct_type_id());
            update.update();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateProduct(TblProducts products){
        try {
            getConnectDatabaseHelper();
            UpdateBuilder<TblProducts, String> update = tblProductsRuntimeDao
                    .updateBuilder();
            update.updateColumnValue("product_name", products.getProduct_name());
            update.updateColumnValue("price", products.getPrice());
            update.where().eq("product_id", products.getProduct_id());
            update.update();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateDiscount(TblDiscount discount){
        try {
            getConnectDatabaseHelper();
            UpdateBuilder<TblDiscount, String> update = tblDiscountsRuntimeDao
                    .updateBuilder();
            update.updateColumnValue("discount_name", discount.getDiscount_name());
            update.updateColumnValue("percent", discount.getPercent());
            update.where().eq("discount_id", discount.getDiscount_id());
            update.update();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /*************select**********************/

    public List<TblMember> getMember(TblMember member){
        List<TblMember> list = new ArrayList<TblMember>();
        try {
            getConnectDatabaseHelper();
            list = tblMemberRuntimeDao.queryBuilder().where().eq("authen", member.getAuthen()).query();

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List<TblMember> getAllMember(){
        List<TblMember> list = new ArrayList<TblMember>();
        try {
            getConnectDatabaseHelper();
            list = tblMemberRuntimeDao.queryForAll();

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List<TblCompany> getAllCompany(){
        List<TblCompany> list = new ArrayList<TblCompany>();
        try {
            getConnectDatabaseHelper();
            list = tblCompanyRuntimeDao.queryForAll();

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List<TblStore> getAllStore(){
        List<TblStore> list = new ArrayList<TblStore>();
        try {
            getConnectDatabaseHelper();
            list = tblStoreRuntimeDao.queryForAll();

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List<TblProductTypes> getAllProductTypes(){
        List<TblProductTypes> list = new ArrayList<TblProductTypes>();
        try {
            getConnectDatabaseHelper();
            list = tblProductTypeRuntimeDao.queryForAll();

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List<TblProducts> getAllProducts(){
        List<TblProducts> list = new ArrayList<TblProducts>();
        try {
            getConnectDatabaseHelper();
            list = tblProductsRuntimeDao.queryForAll();

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List<TblTables> getAllTables(){
        List<TblTables> list = new ArrayList<TblTables>();
        try {
            getConnectDatabaseHelper();
            list = tblTablesRuntimeDao.queryForAll();

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List<TblDiscount> getAllDiscount(){
        List<TblDiscount> list = new ArrayList<TblDiscount>();
        try {
            getConnectDatabaseHelper();
            list = tblDiscountsRuntimeDao.queryForAll();

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    /*************delete**********************/
    public boolean deleteMember(List<TblMember> member){
        try {
            getConnectDatabaseHelper();
            tblMemberRuntimeDao.delete(member);

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteCompany(List<TblCompany> companyList){
        try {
            getConnectDatabaseHelper();
            tblCompanyRuntimeDao.delete(companyList);

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteStore(List<TblStore> storeList){
        try {
            getConnectDatabaseHelper();
            tblStoreRuntimeDao.delete(storeList);

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteProductType(List<TblProductTypes> productTypes){
        try {
            getConnectDatabaseHelper();
            tblProductTypeRuntimeDao.delete(productTypes);

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteProductType(TblProductTypes productTypes){
        try {
            getConnectDatabaseHelper();
            DeleteBuilder<TblProductTypes, String> delete = tblProductTypeRuntimeDao.deleteBuilder();
            delete.where().eq("product_type_id", productTypes.getProduct_type_id());
            delete.delete();

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteProduct(TblProducts product){
        try {
            getConnectDatabaseHelper();
            DeleteBuilder<TblProducts, String> delete = tblProductsRuntimeDao.deleteBuilder();
            delete.where().eq("product_id", product.getProduct_id());
            delete.delete();

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteProduct(List<TblProducts> products){
        try {
            getConnectDatabaseHelper();
            tblProductsRuntimeDao.delete(products);

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteDiscount(TblDiscount discount){
        try {
            getConnectDatabaseHelper();
            DeleteBuilder<TblDiscount, String> delete = tblDiscountsRuntimeDao.deleteBuilder();
            delete.where().eq("discount_id", discount.getDiscount_id());
            delete.delete();

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteDiscount(List<TblDiscount> discount){
        try {
            getConnectDatabaseHelper();
            tblDiscountsRuntimeDao.delete(discount);

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteTable(List<TblTables> tables){
        try {
            getConnectDatabaseHelper();
            tblTablesRuntimeDao.delete(tables);

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
