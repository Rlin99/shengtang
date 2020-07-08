package club.banyuan.service.Impl;

import club.banyuan.dao.AdminDao;
import club.banyuan.dao.impl.AdminDaoImpl;
import club.banyuan.entity.Admin;
import club.banyuan.service.AdminServiceDao;
import club.banyuan.util.JdbcUtils;

import java.sql.SQLException;

public class AdminServiceDaoImpl implements AdminServiceDao {
    @Override
    public Admin login(String userName, String password) throws SQLException {
        AdminDao adminDao = new AdminDaoImpl(JdbcUtils.getConnection());
        return adminDao.getLoginUser(userName, password);
    }
}
