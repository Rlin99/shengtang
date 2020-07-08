package club.banyuan.dao.impl;

import club.banyuan.dao.UserDao;
import club.banyuan.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public int add(User user) {
        String sql = "insert into user values(null,?,?,?,?,?,?)";
        Object[] params = new Object[]{
                user.getUserName(),user.getPassword(),user.getIdCard(),user.getPhone(),
                user.getAddress(),user.getPostCode()
        };
        int i = executeInsert(sql, params);
        return i;
    }

    @Override
    public User getLoginUser(String userName, String pwd) {
        String sql = "select * from user where userName = ? and password = ?";
        Object[] params = new Object[]{userName,pwd};
        ResultSet rs = executeQuery(sql, params);
        User user = null;
        try {
            if(rs.next()){
                user = tableToClass(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Boolean selectLoginName(String userName) {
        return null;
    }

    @Override
    public User tableToClass(ResultSet rs) throws Exception {
        User user = new User();
        user.setId(rs.getInt(1));
        user.setUserName(rs.getString(2));
        user.setPassword(rs.getString(3));
        user.setIdCard(rs.getString(4));
        user.setPhone(rs.getString(5));
        user.setAddress(rs.getString(6));
        user.setPostCode(rs.getString(7));
        return user;
    }
}
