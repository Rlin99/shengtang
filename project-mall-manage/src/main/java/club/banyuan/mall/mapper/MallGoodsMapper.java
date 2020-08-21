package club.banyuan.mall.mapper;

import club.banyuan.mall.entity.MallGoods;
import club.banyuan.mall.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallGoodsMapper {
    List<MallGoods> findNewBeeMallGoodsList(PageQueryUtil pageUtil);

    int getTotalNewBeeMallGoods(PageQueryUtil pageUtil);

    int insertSelective(MallGoods record);

    MallGoods selectByPrimaryKey(Long goodsId);

    int updateByPrimaryKeySelective(MallGoods record);

    int batchUpdateSellStatus(@Param("orderIds")Long[] orderIds, @Param("sellStatus") int sellStatus);
}
