package com.may.stream.restaurant.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.may.stream.restaurant.model.TblAuthen;
import com.may.stream.restaurant.model.TblCompany;
import com.may.stream.restaurant.model.TblDiscount;
import com.may.stream.restaurant.model.TblMember;
import com.may.stream.restaurant.model.TblOrder;
import com.may.stream.restaurant.model.TblProductTypes;
import com.may.stream.restaurant.model.TblProducts;
import com.may.stream.restaurant.model.TblStore;
import com.may.stream.restaurant.model.TblTables;

/**
 * Created by may on 1/5/2018.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "restaurant.db";
    private static final int DATABASE_VERSION = 1;
    private RuntimeExceptionDao<TblCompany, String> tblCompany;
    private RuntimeExceptionDao<TblMember, String> tblMember;
    private RuntimeExceptionDao<TblAuthen, String> tblAuthen;
    private RuntimeExceptionDao<TblDiscount, String> tblDiscount;
    private RuntimeExceptionDao<TblOrder, String> tblOrder;
    private RuntimeExceptionDao<TblProducts, String> tblProducts;
    private RuntimeExceptionDao<TblProductTypes, String> tblProductTypes;
    private RuntimeExceptionDao<TblTables, String> tblTables;
    private RuntimeExceptionDao<TblStore, String> tblStore;
    private static DatabaseHelper instance;
    public static synchronized DatabaseHelper getHelper(Context context) {
        if (instance == null)
            instance = new DatabaseHelper(context);

        return instance;
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, TblCompany.class);
            TableUtils.createTable(connectionSource, TblMember.class);
            TableUtils.createTable(connectionSource, TblAuthen.class);
            TableUtils.createTable(connectionSource, TblDiscount.class);
            TableUtils.createTable(connectionSource, TblOrder.class);
            TableUtils.createTable(connectionSource, TblProducts.class);
            TableUtils.createTable(connectionSource, TblProductTypes.class);
            TableUtils.createTable(connectionSource, TblTables.class);
            TableUtils.createTable(connectionSource, TblStore.class);
        }catch (Exception e){
            e.printStackTrace();
            Log.i("DatabaseHelper.onCreate",(e.getMessage() != null) ? e.getMessage() : "error");
        }

    }

    @SuppressLint("LongLogTag")
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            onDropTable(connectionSource);
            TableUtils.createTable(connectionSource, TblCompany.class);
            TableUtils.createTable(connectionSource, TblMember.class);
            TableUtils.createTable(connectionSource, TblAuthen.class);
            TableUtils.createTable(connectionSource, TblDiscount.class);
            TableUtils.createTable(connectionSource, TblOrder.class);
            TableUtils.createTable(connectionSource, TblProducts.class);
            TableUtils.createTable(connectionSource, TblProductTypes.class);
            TableUtils.createTable(connectionSource, TblTables.class);
            TableUtils.createTable(connectionSource, TblStore.class);
        }catch (Exception e){
            e.printStackTrace();
            Log.i("DatabaseHelper.onUpgrade",(e.getMessage() != null) ? e.getMessage() : "error");
        }
        onCreate(db, connectionSource);

    }

    @SuppressLint("LongLogTag")
    public void onDropTable(ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), "dropTable");
            TableUtils.dropTable(connectionSource, TblCompany.class, true);
            TableUtils.dropTable(connectionSource, TblMember.class, true);
            TableUtils.dropTable(connectionSource, TblAuthen.class, true);
            TableUtils.dropTable(connectionSource, TblDiscount.class, true);
            TableUtils.dropTable(connectionSource, TblOrder.class, true);
            TableUtils.dropTable(connectionSource, TblProducts.class, true);
            TableUtils.dropTable(connectionSource, TblProductTypes.class, true);
            TableUtils.dropTable(connectionSource, TblTables.class, true);
            TableUtils.dropTable(connectionSource, TblStore.class, true);
        }catch (Exception e){
            e.printStackTrace();
            Log.i("DatabaseHelper.onDropTable",(e.getMessage() != null) ? e.getMessage() : "error");
        }
    }

    public RuntimeExceptionDao<TblCompany, String> getTblCompany() {
        if (tblCompany == null) {
            tblCompany = getRuntimeExceptionDao(TblCompany.class);
        }
        return tblCompany;
    }

    public RuntimeExceptionDao<TblMember, String> getTblMember() {
        if (tblMember == null) {
            tblMember = getRuntimeExceptionDao(TblMember.class);
        }
        return tblMember;
    }

    public RuntimeExceptionDao<TblAuthen, String> getTblAuthen() {
        if (tblAuthen == null) {
            tblAuthen = getRuntimeExceptionDao(TblAuthen.class);
        }
        return tblAuthen;
    }

    public RuntimeExceptionDao<TblDiscount, String> getTblDiscount() {
        if (tblDiscount == null) {
            tblDiscount = getRuntimeExceptionDao(TblDiscount.class);
        }
        return tblDiscount;
    }

    public RuntimeExceptionDao<TblOrder, String> getTblOrder() {
        if (tblOrder == null) {
            tblOrder = getRuntimeExceptionDao(TblOrder.class);
        }
        return tblOrder;
    }

    public RuntimeExceptionDao<TblProducts, String> getTblProducts() {
        if (tblProducts == null) {
            tblProducts = getRuntimeExceptionDao(TblProducts.class);
        }
        return tblProducts;
    }

    public RuntimeExceptionDao<TblProductTypes, String> getTblProductTypes() {
        if (tblProductTypes == null) {
            tblProductTypes = getRuntimeExceptionDao(TblProductTypes.class);
        }
        return tblProductTypes;
    }

    public RuntimeExceptionDao<TblTables, String> getTblTables() {
        if (tblTables == null) {
            tblTables = getRuntimeExceptionDao(TblTables.class);
        }
        return tblTables;
    }

    public RuntimeExceptionDao<TblStore, String> getTblStore() {
        if (tblStore == null) {
            tblStore = getRuntimeExceptionDao(TblStore.class);
        }
        return tblStore;
    }
}
