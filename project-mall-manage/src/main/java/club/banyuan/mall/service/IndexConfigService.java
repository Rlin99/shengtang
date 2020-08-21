package club.banyuan.mall.service;

import club.banyuan.mall.entity.IndexConfig;
import club.banyuan.mall.util.PageQueryUtil;
import club.banyuan.mall.util.PageResult;

import java.util.List;

public interface IndexConfigService {
    /**
     * 后台分页
     *
     * @param pageUtil
     * @return
     */
    PageResult getConfigsPage(PageQueryUtil pageUtil);
    String saveIndexConfig(IndexConfig indexConfig);

    String updateIndexConfig(IndexConfig indexConfig);

    IndexConfig getIndexConfigById(Long id);
//    /**
//     * 返回固定数量的首页配置商品对象(首页调用)
//     *
//     * @param number
//     * @return
//     */
//    List<NewBeeMallIndexConfigGoodsVO> getConfigGoodsesForIndex(int configType, int number);

    Boolean deleteBatch(Long[] ids);
}
