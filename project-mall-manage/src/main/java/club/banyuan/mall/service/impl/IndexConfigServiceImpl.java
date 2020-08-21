package club.banyuan.mall.service.impl;

import club.banyuan.mall.common.ServiceResultEnum;
import club.banyuan.mall.entity.IndexConfig;
import club.banyuan.mall.mapper.IndexConfigMapper;
import club.banyuan.mall.service.IndexConfigService;
import club.banyuan.mall.util.PageQueryUtil;
import club.banyuan.mall.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexConfigServiceImpl implements IndexConfigService {

    @Autowired
    private IndexConfigMapper indexConfigMapper;
    @Override
    public PageResult getConfigsPage(PageQueryUtil pageUtil) {
        List<IndexConfig> indexConfigs = indexConfigMapper.findIndexConfigList(pageUtil);
        int total = indexConfigMapper.getTotalIndexConfigs(pageUtil);
        PageResult pageResult = new PageResult(indexConfigs, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public String saveIndexConfig(club.banyuan.mall.entity.IndexConfig indexConfig) {
        //todo 判断是否存在该商品
        if (indexConfigMapper.insertSelective(indexConfig) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String updateIndexConfig(club.banyuan.mall.entity.IndexConfig indexConfig) {
        IndexConfig temp = indexConfigMapper.selectByPrimaryKey(indexConfig.getConfigId());
        if(temp == null){
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        if(indexConfigMapper.updateByPrimaryKeySelective(indexConfig) > 0){
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public club.banyuan.mall.entity.IndexConfig getIndexConfigById(Long id) {
        return indexConfigMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean deleteBatch(Long[] ids) {
        if(ids.length < 1){
            return false;
        }
        System.out.println("RRR");
        return indexConfigMapper.deleteBatch(ids) > 0;
    }
}
