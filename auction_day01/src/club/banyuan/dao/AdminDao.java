package club.banyuan.dao;

import club.banyuan.entity.Admin;

public interface AdminDao extends IBaseDao{
    public Admin getLoginUser(String userName, String pwd);
}
