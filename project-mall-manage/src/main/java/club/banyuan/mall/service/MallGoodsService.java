package club.banyuan.mall.service;

import club.banyuan.mall.entity.MallGoods;
import club.banyuan.mall.util.PageQueryUtil;
import club.banyuan.mall.util.PageResult;

import java.util.Map;

public interface MallGoodsService {
    /**
     * 后台分页
     *
     * @param
     * @return
     */
    PageResult getNewBeeMallGoodsPage(Map<String, Object> params);

    /**
     * 添加商品
     *
     * @param goods
     * @return
     */
    String saveNewBeeMallGoods(MallGoods goods);
    /**
     * 获取商品详情
     *
     * @param id
     * @return
     */
    MallGoods getNewBeeMallGoodsById(Long id);
    /**
     * 修改商品信息
     *
     * @param goods
     * @return
     */
    String updateNewBeeMallGoods(MallGoods goods);
    /**
     * 批量修改销售状态(上架下架)
     *
     * @param ids
     * @return
     */
    Boolean batchUpdateSellStatus(Long[] ids,int sellStatus);

}
