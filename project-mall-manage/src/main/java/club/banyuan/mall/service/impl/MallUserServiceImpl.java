package club.banyuan.mall.service.impl;

import club.banyuan.mall.entity.MallUser;
import club.banyuan.mall.mapper.MallUserMapper;
import club.banyuan.mall.service.MallUserService;
import club.banyuan.mall.util.PageQueryUtil;
import club.banyuan.mall.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MallUserServiceImpl implements MallUserService {

    @Autowired
    private MallUserMapper mallUserMapper;

    @Override
    public PageResult getNewBeeMallUsersPage(PageQueryUtil pageUtil) {
        List<MallUser> mallUserList = mallUserMapper.findMallUserList(pageUtil);
        int total = mallUserMapper.getTotalMallUsers(pageUtil);
        return new PageResult(mallUserList, total, pageUtil.getLimit(), pageUtil.getPage());
    }

    @Override
    public Boolean lockUsers(Integer[] ids, int lockStatus) {
        return mallUserMapper.lockUserBatch(ids,lockStatus) > 0;
    }
}
