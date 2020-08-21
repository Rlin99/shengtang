package club.banyuan.mall.controller;

import java.util.Map;
import java.util.Objects;

import club.banyuan.mall.common.ServiceResultEnum;
import club.banyuan.mall.entity.Carousel;
import club.banyuan.mall.service.MallCarouselService;
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
public class CarouselController {

	@Autowired
	private MallCarouselService mallCarouselService;

	@GetMapping("/carousels/list")
	public Result<?> list(@RequestParam Map<String, Object> params) {
		if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
			return ResultGenerator.genFailResult("参数异常！");
		}
		PageQueryUtil pageUtil = new PageQueryUtil(params);
		return ResultGenerator.genSuccessResult(mallCarouselService.getCarouselPage(pageUtil));
	}

	@PostMapping("/carousels/save")
	public Result<?> save(@RequestBody Carousel carousel) {
		if (StringUtils.isEmpty(carousel.getCarouselUrl())
				|| Objects.isNull(carousel.getCarouselRank())) {
			return ResultGenerator.genFailResult("参数异常！");
		}
		String result = mallCarouselService.saveCarousel(carousel);
		if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult(result);
		}
	}

	@PostMapping("/carousels/update")
	public Result<?> update(@RequestBody Carousel carousel) {
		if (Objects.isNull(carousel.getCarouselId())
				|| StringUtils.isEmpty(carousel.getCarouselUrl())
				|| Objects.isNull(carousel.getCarouselRank())) {
			return ResultGenerator.genFailResult("参数异常！");
		}
		String result = mallCarouselService.updateCarousel(carousel);
		if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult(result);
		}
	}

	@GetMapping("/carousels/info/{id}")
	public Result<?> info(@PathVariable("id") Integer id) {
		Carousel carousel = mallCarouselService.getCarouselById(id);
		if (carousel == null) {
			return ResultGenerator.genFailResult(ServiceResultEnum.DATA_NOT_EXIST.getResult());
		}
		return ResultGenerator.genSuccessResult(carousel);
	}

	@PostMapping("/carousels/delete")
	public Result<?> delete(@RequestBody Integer[] ids) {
		if(ids.length < 1){
			return ResultGenerator.genFailResult("参数异常");
		}
		if(mallCarouselService.deleteBatch(ids)){
			return ResultGenerator.genSuccessResult();
		}else {
			return ResultGenerator.genFailResult("删除失败");
		}

	}
}
