package club.banyuan.mall.controller;

import java.util.Map;
import java.util.Objects;

import club.banyuan.mall.common.ServiceResultEnum;
import club.banyuan.mall.entity.IndexConfig;
import club.banyuan.mall.service.IndexConfigService;
import club.banyuan.mall.util.PageQueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import club.banyuan.mall.util.Result;
import club.banyuan.mall.util.ResultGenerator;

@RestController
@RequestMapping("/admin")
public class GoodsIndexConfigController {

	@Autowired
	private IndexConfigService indexConfigService;

	@GetMapping("/indexConfigs/list")
	public Result<?> list(@RequestParam Map<String, Object> params) {
		if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
			return ResultGenerator.genFailResult("参数异常！");
		}
		PageQueryUtil pageUtil = new PageQueryUtil(params);
		return ResultGenerator.genSuccessResult(indexConfigService.getConfigsPage(pageUtil));
	}

	@PostMapping("/indexConfigs/save")
	public Result<?> save(@RequestBody IndexConfig indexConfig) {
		if (Objects.isNull(indexConfig.getConfigType())
				|| StringUtils.isEmpty(indexConfig.getConfigName())
				|| Objects.isNull(indexConfig.getConfigRank())) {
			return ResultGenerator.genFailResult("参数异常！");
		}
		String result = indexConfigService.saveIndexConfig(indexConfig);
		return ResultGenerator.genSuccessResult();
	}

	@PostMapping("/indexConfigs/update")
	public Result<?> update(@RequestBody IndexConfig indexConfig) {
		if (Objects.isNull(indexConfig.getConfigType())
				|| Objects.isNull(indexConfig.getConfigId())
				|| StringUtils.isEmpty(indexConfig.getConfigName())
				|| Objects.isNull(indexConfig.getConfigRank())) {
			return ResultGenerator.genFailResult("参数异常！");
		}
		String result = indexConfigService.updateIndexConfig(indexConfig);
		if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult(result);
		}
	}

	@GetMapping("/indexConfigs/info/{id}")
	public Result<?> info(@PathVariable("id") Long id) {
		IndexConfig config = indexConfigService.getIndexConfigById(id);
		if (config == null) {
			return ResultGenerator.genFailResult("未查询到数据");
		}
		return ResultGenerator.genSuccessResult(config);
	}

	@PostMapping("/indexConfigs/delete")
	public Result<?> delete(@RequestBody Long[] ids) {
		if (ids.length < 1) {
			return ResultGenerator.genFailResult("参数异常！");
		}
		if (indexConfigService.deleteBatch(ids)) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("删除失败");
		}
	}
}
