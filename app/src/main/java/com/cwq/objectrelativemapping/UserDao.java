package com.cwq.objectrelativemapping;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * 专门对User类对应表中数据做增删改查操作
 */
public class UserDao {
    private Dao<User,Integer> userDao;
    private  DBHelper dbHelper;

    public UserDao(Context context){
          dbHelper=DBHelper.getInstance(context);
        try {
              this.userDao=dbHelper.getDao(User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean add(User user){
        //this.userDao.create(user):返回本次插入操作受影响的行数
        try {
            int count=this.userDao.create(user);
             return count>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 修改用户
     * @param user
     * @return
     */
    public boolean update(User user){
        try {
            //本次修改操作受影响的行数
            int count= this.userDao.update(user);

            return count>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    /**
     * 本次删除操作是否成功
     * @param id
     * @return
     */
    public boolean delete(int id){
        try {
            //本次删除操作受影响的行数
            int count=this.userDao.deleteById(id);
            return count>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    /**
     * 根据指定的id查询用户对象
     * @param id
     * @return
     */
    public User query(int id){
        try {
            //根据指定的id值返回用户对象
            User user= this.userDao.queryForId(id);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 查询所有用户对象
     * @return
     */
    public List<User> query(){
        try {
            List<User> users=  this.userDao.queryForAll();
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    /**
     * 根据姓名进行模糊查询
     * @param condition 查询姓名的模糊查询条件
     * @return
     */
    public List<User> queryLike(String condition){
        try {
            List<User> users= this.userDao.queryBuilder().where().like("name","%"+condition+"%").query();
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    /**
     * 关闭Dao 对象
     */
    public void close(){
        this.dbHelper.close();
    }
}
