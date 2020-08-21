package club.banyuan.mall.service.impl;

import club.banyuan.mall.common.ServiceResultEnum;
import club.banyuan.mall.entity.MallGoods;
import club.banyuan.mall.mapper.MallGoodsMapper;
import club.banyuan.mall.service.MallGoodsService;
import club.banyuan.mall.util.PageQueryUtil;
import club.banyuan.mall.util.PageResult;
import club.banyuan.mall.util.RedisUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;
import sun.net.www.ParseUtil;

import java.util.*;

@Service
public class MallGoodsServiceImpl implements MallGoodsService {

    @Autowired
    private MallGoodsMapper mallGoodsMapper;

    @Autowired
    private RedisUtil redisUtil;

    private static final String GOOD_LIST_KEY = "good:list";

    @Override
    public PageResult getNewBeeMallGoodsPage(Map<String, Object> params) {
        //用分页工具分页
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        //从redis中检查是否存在商品集合
        Long total = redisUtil.getTotalZset(GOOD_LIST_KEY);
        if(total != 0){
            //存在，直接从Redis中进行数据分页
            return findPageDataByCache(pageUtil, GOOD_LIST_KEY);
        }else {
            //不存在，从数据库取得所有商品信息
            PageQueryUtil pageUtilAll = new PageQueryUtil(params);
            pageUtilAll.setLimit(null);
            pageUtilAll.put("start", null);
            List<MallGoods> goodsList = mallGoodsMapper.findNewBeeMallGoodsList(pageUtilAll);
            //存入Redis中
            saveCache(GOOD_LIST_KEY,goodsList,60 * 60 * 24);
            //从redis中进行分页
            return findPageDataByCache(pageUtil, GOOD_LIST_KEY);
        }
    }

    @Override
    public String saveNewBeeMallGoods(MallGoods goods) {
        redisUtil.del(GOOD_LIST_KEY);
        return mallGoodsMapper.insertSelective(goods) > 0 ? ServiceResultEnum.SUCCESS.getResult() : ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public MallGoods getNewBeeMallGoodsById(Long id) {
        return mallGoodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public String updateNewBeeMallGoods(MallGoods goods) {
        MallGoods temp = mallGoodsMapper.selectByPrimaryKey(goods.getGoodsId());
        if (temp == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        goods.setUpdateTime(new Date());
        if (mallGoodsMapper.updateByPrimaryKeySelective(goods) > 0) {
            redisUtil.del(GOOD_LIST_KEY);
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public Boolean batchUpdateSellStatus(Long[] ids, int sellStatus) {
        redisUtil.del(GOOD_LIST_KEY);
        return mallGoodsMapper.batchUpdateSellStatus(ids, sellStatus) > 0;
    }

    //将数据保存至Redis
    public void saveCache(final String key, List<MallGoods> list, final Integer timeout){
        if(list.size() > 0){
            for(int i = 0; i < list.size(); i++){
                //将每一个商品对象进行序列化，存入到redis中
                String setValue = JSON.toJSONString(list.get(i));
                redisUtil.zset(key,Double.valueOf(i),setValue);
            }
        }
        redisUtil.expire(key, timeout);
    }

    //从Redis中进行分页读取数据
    public PageResult findPageDataByCache(PageQueryUtil pageUtil, final String key){
        List<MallGoods> goodsList = new ArrayList<>();
        //从redis中获得集合中的商品数量
        Long total = redisUtil.getTotalZset(key);
        if(total > 0){
            Integer beginIndex = (pageUtil.getPage()-1) * pageUtil.getLimit();
            Integer endIndex = beginIndex + pageUtil.getLimit() - 1;
            //从redis中分页读取所需数据
            Set<Object> cacheDataForRow =redisUtil.zrange(key, beginIndex, endIndex);
            for (Object o : cacheDataForRow) {
                String goosJson = o.toString();
                //将每一个对象进行反序列化成商品对象
                MallGoods mallGoods = JSONArray.parseObject(goosJson, MallGoods.class);
                goodsList.add(mallGoods);
            }
        }
        PageResult pageResult = new PageResult(goodsList, total.intValue(), pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
}
