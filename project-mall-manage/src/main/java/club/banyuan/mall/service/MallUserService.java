package club.banyuan.mall.service;

import club.banyuan.mall.util.PageQueryUtil;
import club.banyuan.mall.util.PageResult;

public interface MallUserService {
    public PageResult getNewBeeMallUsersPage(PageQueryUtil pageUtil);
    public Boolean lockUsers(Integer[] ids, int lockStatus);
}
