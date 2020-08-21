package club.banyuan.mall.mapper;

import club.banyuan.mall.entity.MallUser;
import club.banyuan.mall.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallUserMapper {
    List<MallUser> findMallUserList(PageQueryUtil pageUtil);
    int lockUserBatch(@Param("ids") Integer[] ids, @Param("lockStatus") int lockStatus);
    int getTotalMallUsers(PageQueryUtil pageUtil);
}
