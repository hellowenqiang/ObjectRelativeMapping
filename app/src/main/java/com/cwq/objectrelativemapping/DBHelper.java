package com.cwq.objectrelativemapping;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/29.
 */
public class DBHelper extends OrmLiteSqliteOpenHelper {
    /**
     * 声明数据库名称的常量
     */
    private static final String DB_NAME="bank.db";
    /**
     * 1.声明一个私有的静态的本类类型的对象
     */
    private static DBHelper instance;

    /**
     * 实例化存放简单类名和类对应Dao对象的Map对象.
     * Map<简单类名,Dao>对象
     */
    private Map<String,Dao> daoMap=new HashMap<>();

    /**
     * 返回Dao 对象
     * @return
     * @throws SQLException
     */
    public synchronized Dao getDao(Class clazz) throws SQLException{
        //得到当前类的简单类名
        String simpleName=clazz.getSimpleName();

        Dao dao=null;
        if(daoMap.containsKey(simpleName)){
            dao=daoMap.get(simpleName);
        }else{
            dao=super.getDao(clazz);
            daoMap.put(simpleName,dao);
        }


        return dao;

    }

    /**
     * 关闭dao 对象
     */
    public void close(){
        super.close();
        for(Dao dao:daoMap.values()){
            dao=null;
        }

    }


    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }


    /**
     * 3.通过共有的静态方法返回本类类型的对象
     * @return
     */
    public static DBHelper getInstance(Context context){
        if(instance==null){
            synchronized (DBHelper.class){
                if(instance==null){
                    instance=new DBHelper(context);
                }
            }
        }

        return instance;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        //创建表t_user
        try {
            TableUtils.createTable(connectionSource, User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        //删除表,如果有简单错误信息则忽略
        try {
            TableUtils.dropTable(connectionSource,User.class,true);
            onCreate(sqLiteDatabase,connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
