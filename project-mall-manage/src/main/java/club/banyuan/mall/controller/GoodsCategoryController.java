package club.banyuan.mall.controller;

import java.util.*;

import club.banyuan.mall.common.NewBeeMallCategoryLevelEnum;
import club.banyuan.mall.common.ServiceResultEnum;
import club.banyuan.mall.service.GoodsCategoryService;
import club.banyuan.mall.util.PageQueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import club.banyuan.mall.entity.GoodsCategory;
import club.banyuan.mall.util.Result;
import club.banyuan.mall.util.ResultGenerator;

@RestController
@RequestMapping("/admin")
public class GoodsCategoryController {

	@Autowired
	private GoodsCategoryService goodsCategoryService;

	@GetMapping("/categories/list")
	public Result<?> list(@RequestParam Map<String, Object> params) {
		if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit")) || StringUtils.isEmpty(params.get("categoryLevel")) || StringUtils.isEmpty(params.get("parentId"))) {
			return ResultGenerator.genFailResult("参数异常！");
		}
		PageQueryUtil pageUtil = new PageQueryUtil(params);
		return ResultGenerator.genSuccessResult(goodsCategoryService.getCategorisPage(pageUtil));
	}

	@GetMapping("/categories/listForSelect")
	public Result<?> listForSelect(@RequestParam("categoryId") Long categoryId) {
		if(categoryId == null || categoryId < 1){
			return ResultGenerator.genFailResult("缺少参数！");
		}
		GoodsCategory category = goodsCategoryService.getGoodsCategoryById(categoryId);
		//既不是一级分类也不是二级分类则为不返回数据
		if (category == null || category.getCategoryLevel() == NewBeeMallCategoryLevelEnum.LEVEL_THREE.getLevel()) {
			return ResultGenerator.genFailResult("参数异常！");
		}
		Map categoryResult = new HashMap(2);
		if (category.getCategoryLevel() == NewBeeMallCategoryLevelEnum.LEVEL_ONE.getLevel()) {
			//如果是一级分类则返回当前一级分类下的所有二级分类，以及二级分类列表中第一条数据下的所有三级分类列表
			//查询一级分类列表中第一个实体的所有二级分类
			List<GoodsCategory> secondLevelCategories = goodsCategoryService.selectByLevelAndParentIdsAndNumber
					(Collections.singletonList(categoryId), NewBeeMallCategoryLevelEnum.LEVEL_TWO.getLevel());
			if (!CollectionUtils.isEmpty(secondLevelCategories)) {
				//查询二级分类列表中第一个实体的所有三级分类
				List<GoodsCategory> thirdLevelCategories = goodsCategoryService.selectByLevelAndParentIdsAndNumber
						(Collections.singletonList(secondLevelCategories.get(0).getCategoryId()), NewBeeMallCategoryLevelEnum.LEVEL_THREE.getLevel());
				categoryResult.put("secondLevelCategories", secondLevelCategories);
				categoryResult.put("thir·dLevelCategories", thirdLevelCategories);
			}
		}
		if (category.getCategoryLevel() == NewBeeMallCategoryLevelEnum.LEVEL_TWO.getLevel()) {
			//如果是二级分类则返回当前分类下的所有三级分类列表
			List<GoodsCategory> thirdLevelCategories = goodsCategoryService.selectByLevelAndParentIdsAndNumber
					(Collections.singletonList(categoryId), NewBeeMallCategoryLevelEnum.LEVEL_THREE.getLevel());
			categoryResult.put("thirdLevelCategories", thirdLevelCategories);
		}
		return ResultGenerator.genSuccessResult(categoryResult);
	}

	@PostMapping("/categories/save")
	public Result<?> save(@RequestBody GoodsCategory goodsCategory) {
		if (Objects.isNull(goodsCategory.getCategoryLevel())
				|| StringUtils.isEmpty(goodsCategory.getCategoryName())
				|| Objects.isNull(goodsCategory.getParentId())
				|| Objects.isNull(goodsCategory.getCategoryRank())) {
			return ResultGenerator.genFailResult("参数异常！");
		}
		String result = goodsCategoryService.saveCategory(goodsCategory);
		if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult(result);
		}
	}

	@PostMapping("/categories/update")
	public Result<?> update(@RequestBody GoodsCategory goodsCategory) {
		if (Objects.isNull(goodsCategory.getCategoryId())
				|| Objects.isNull(goodsCategory.getCategoryLevel())
				|| StringUtils.isEmpty(goodsCategory.getCategoryName())
				|| Objects.isNull(goodsCategory.getParentId())
				|| Objects.isNull(goodsCategory.getCategoryRank())) {
			return ResultGenerator.genFailResult("参数异常！");
		}
		String result = goodsCategoryService.updateGoodsCategory(goodsCategory);
		if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult(result);
		}
	}

	@GetMapping("/categories/info/{id}")
	public Result<?> info(@PathVariable("id") Long id) {
		//
		return ResultGenerator.genSuccessResult();
	}

	@PostMapping("/categories/delete")
	public Result<?> delete(@RequestBody Integer[] ids) {
		if (ids.length < 1) {
			return ResultGenerator.genFailResult("参数异常！");
		}
		if (goodsCategoryService.deleteBatch(ids)) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("删除失败");
		}
	}
}
