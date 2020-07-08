package club.banyuan.dao.impl;

import club.banyuan.dao.AdminDao;
import club.banyuan.entity.Admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Objects;

public class AdminDaoImpl extends BaseDaoImpl implements AdminDao {
    public AdminDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Admin getLoginUser(String userName, String pwd) {
        String sql = "select * from admin where userName = ? and password = ?";
        Object[] params = new Object[]{userName,pwd};
        ResultSet rs = executeQuery(sql, params);
        Admin admin = null;
        try {
            if(rs.next()){
                admin = tableToClass(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }

    @Override
    public Admin tableToClass(ResultSet rs) throws Exception {
        Admin admin = new Admin();
        admin.setId(rs.getInt(1));
        admin.setUserName(rs.getString(2));
        admin.setPassword(rs.getString(3));
        return admin;
    }
}
