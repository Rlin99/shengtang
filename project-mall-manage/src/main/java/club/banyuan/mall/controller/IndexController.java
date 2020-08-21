package club.banyuan.mall.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import club.banyuan.mall.common.NewBeeMallCategoryLevelEnum;
import club.banyuan.mall.entity.AdminUser;
import club.banyuan.mall.entity.GoodsCategory;
import club.banyuan.mall.entity.MallGoods;
import club.banyuan.mall.service.AdminUserService;
import club.banyuan.mall.service.GoodsCategoryService;
import club.banyuan.mall.service.MallGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import club.banyuan.mall.common.IndexConfigTypeEnum;

@Controller
@RequestMapping("/admin")
public class IndexController {

	@Autowired
	private GoodsCategoryService goodsCategoryService;
	@Autowired
	private AdminUserService adminUserService;

	@Autowired
	private MallGoodsService mallGoodsService;

	@GetMapping({ "/login" })
	public String login() {
		return "admin/login";
	}
	
	@GetMapping({"", "/", "/index", "/index.html"})
    public String index(HttpServletRequest request) {
        request.setAttribute("path", "index");
        return "admin/index";
    }

	@PostMapping("/login")
	public String login(@RequestParam("userName") String userName,
						@RequestParam("password") String password,
						@RequestParam("verifyCode") String verifyCode,
						HttpSession session){
		if(StringUtils.isEmpty(verifyCode)){
			session.setAttribute("errorMsg", "验证码不能为空！");
			return "admin/login";
		}
		if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)){
			session.setAttribute("errorMsg", "用户名或密码不能为空！");
			return "admin/login";
		}
		String kaptchaCode = session.getAttribute("verifyCode") + "";
		if(StringUtils.isEmpty(kaptchaCode) || !verifyCode.equals(kaptchaCode)){
			session.setAttribute("errorMsg", "验证码错误");
			return "admin/login";
		}

		AdminUser adminUser = adminUserService.login(userName, password);
		if(adminUser != null){
			session.setAttribute("loginUser", adminUser.getNickName());
			session.setAttribute("loginUserId", adminUser.getAdminUserId());
			//session过期时间设置为7200秒 即两小时
			session.setMaxInactiveInterval(60 * 60 * 2);
			return "redirect:/admin/index";
		}else {
			session.setAttribute("errorMsg", "登陆失败，请联系作者获得测试账号");
			return "admin/login";
		}
	}

	@GetMapping("/profile")
	public String profile(HttpServletRequest request) {
//		Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
//        AdminUser adminUser = adminUserService.getUserDetailById(loginUserId);
//        if (adminUser == null) {
//            return "admin/login";
//        }
		request.setAttribute("path", "profile");
//        request.setAttribute("loginUserName", adminUser.getLoginUserName());
//        request.setAttribute("nickName", adminUser.getNickName());
		return "admin/profile";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("loginUserId");
		request.getSession().removeAttribute("loginUser");
		request.getSession().removeAttribute("errorMsg");
		return "admin/login";
	}

	@GetMapping("/carousels")
	public String carouselPage(HttpServletRequest request) {
		request.setAttribute("path", "newbee_mall_carousel");
		return "admin/newbee_mall_carousel";
	}

	@GetMapping("/categories")
	public String categoriesPage(HttpServletRequest request, @RequestParam("categoryLevel") Byte categoryLevel,
			@RequestParam("parentId") Long parentId, @RequestParam("backParentId") Long backParentId) {
		if (categoryLevel == null || categoryLevel < 1 || categoryLevel > 3) {
			return "error/error_5xx";
		}
		request.setAttribute("path", "newbee_mall_category");
		request.setAttribute("parentId", parentId);
		request.setAttribute("backParentId", backParentId);
		request.setAttribute("categoryLevel", categoryLevel);
		return "admin/newbee_mall_category";
	}

	@GetMapping("/goods")
	public String goodsPage(HttpServletRequest request) {
		request.setAttribute("path", "newbee_mall_goods");
		return "admin/newbee_mall_goods";
	}

	@GetMapping("/goods/edit")
	public String edit(HttpServletRequest request) {
		request.setAttribute("path", "edit");
		 //查询所有的一级分类
        List<GoodsCategory> firstLevelCategories = goodsCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(0L), NewBeeMallCategoryLevelEnum.LEVEL_ONE.getLevel());
        if (!CollectionUtils.isEmpty(firstLevelCategories)) {
            //查询一级分类列表中第一个实体的所有二级分类
            List<GoodsCategory> secondLevelCategories = goodsCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(firstLevelCategories.get(0).getCategoryId()), NewBeeMallCategoryLevelEnum.LEVEL_TWO.getLevel());
            if (!CollectionUtils.isEmpty(secondLevelCategories)) {
                //查询二级分类列表中第一个实体的所有三级分类
                List<GoodsCategory> thirdLevelCategories = goodsCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(secondLevelCategories.get(0).getCategoryId()), NewBeeMallCategoryLevelEnum.LEVEL_THREE.getLevel());
                request.setAttribute("firstLevelCategories", firstLevelCategories);
                request.setAttribute("secondLevelCategories", secondLevelCategories);
                request.setAttribute("thirdLevelCategories", thirdLevelCategories);
                request.setAttribute("path", "goods-edit");
                return "admin/newbee_mall_goods_edit";
            }
        }
		return "error/error_5xx";
	}

	@GetMapping("/goods/edit/{goodsId}")
	public String edit(HttpServletRequest request, @PathVariable("goodsId") Long goodsId) {
		request.setAttribute("path", "edit");
        MallGoods newBeeMallGoods = mallGoodsService.getNewBeeMallGoodsById(goodsId);
        if (newBeeMallGoods == null) {
            return "error/error_400";
        }
        if (newBeeMallGoods.getGoodsCategoryId() > 0) {
            if (newBeeMallGoods.getGoodsCategoryId() != null || newBeeMallGoods.getGoodsCategoryId() > 0) {
                //有分类字段则查询相关分类数据返回给前端以供分类的三级联动显示
                GoodsCategory currentGoodsCategory = goodsCategoryService.getGoodsCategoryById(newBeeMallGoods.getGoodsCategoryId());
                //商品表中存储的分类id字段为三级分类的id，不为三级分类则是错误数据
                if (currentGoodsCategory != null && currentGoodsCategory.getCategoryLevel() == NewBeeMallCategoryLevelEnum.LEVEL_THREE.getLevel()) {
                    //查询所有的一级分类
                    List<GoodsCategory> firstLevelCategories = goodsCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(0L), NewBeeMallCategoryLevelEnum.LEVEL_ONE.getLevel());
                    //根据parentId查询当前parentId下所有的三级分类
                    List<GoodsCategory> thirdLevelCategories = goodsCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(currentGoodsCategory.getParentId()), NewBeeMallCategoryLevelEnum.LEVEL_THREE.getLevel());
                    //查询当前三级分类的父级二级分类
                    GoodsCategory secondCategory = goodsCategoryService.getGoodsCategoryById(currentGoodsCategory.getParentId());
                    if (secondCategory != null) {
                        //根据parentId查询当前parentId下所有的二级分类
                        List<GoodsCategory> secondLevelCategories = goodsCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(secondCategory.getParentId()), NewBeeMallCategoryLevelEnum.LEVEL_TWO.getLevel());
                        //查询当前二级分类的父级一级分类
                        GoodsCategory firestCategory = goodsCategoryService.getGoodsCategoryById(secondCategory.getParentId());
                        if (firestCategory != null) {
                            //所有分类数据都得到之后放到request对象中供前端读取
                            request.setAttribute("firstLevelCategories", firstLevelCategories);
                            request.setAttribute("secondLevelCategories", secondLevelCategories);
                            request.setAttribute("thirdLevelCategories", thirdLevelCategories);
                            request.setAttribute("firstLevelCategoryId", firestCategory.getCategoryId());
                            request.setAttribute("secondLevelCategoryId", secondCategory.getCategoryId());
                            request.setAttribute("thirdLevelCategoryId", currentGoodsCategory.getCategoryId());
                        }
                    }
                }
            }
        }
        if (newBeeMallGoods.getGoodsCategoryId() == 0) {
            //查询所有的一级分类
            List<GoodsCategory> firstLevelCategories = goodsCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(0L), NewBeeMallCategoryLevelEnum.LEVEL_ONE.getLevel());
            if (!CollectionUtils.isEmpty(firstLevelCategories)) {
                //查询一级分类列表中第一个实体的所有二级分类
                List<GoodsCategory> secondLevelCategories = goodsCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(firstLevelCategories.get(0).getCategoryId()), NewBeeMallCategoryLevelEnum.LEVEL_TWO.getLevel());
                if (!CollectionUtils.isEmpty(secondLevelCategories)) {
                    //查询二级分类列表中第一个实体的所有三级分类
                    List<GoodsCategory> thirdLevelCategories = goodsCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(secondLevelCategories.get(0).getCategoryId()), NewBeeMallCategoryLevelEnum.LEVEL_THREE.getLevel());
                    request.setAttribute("firstLevelCategories", firstLevelCategories);
                    request.setAttribute("secondLevelCategories", secondLevelCategories);
                    request.setAttribute("thirdLevelCategories", thirdLevelCategories);
                }
            }
        }
        request.setAttribute("goods", newBeeMallGoods);
		request.setAttribute("path", "goods-edit");
		return "admin/newbee_mall_goods_edit";
	}

	@GetMapping("/indexConfigs")
	public String indexConfigsPage(HttpServletRequest request, @RequestParam("configType") int configType) {
		IndexConfigTypeEnum indexConfigTypeEnum = IndexConfigTypeEnum.getIndexConfigTypeEnumByType(configType);
		if (indexConfigTypeEnum.equals(IndexConfigTypeEnum.DEFAULT)) {
			return "error/error_5xx";
		}
		request.setAttribute("path", indexConfigTypeEnum.getName());
		request.setAttribute("configType", configType);
		return "admin/newbee_mall_index_config";
	}

	@GetMapping("/orders")
	public String ordersPage(HttpServletRequest request) {
		request.setAttribute("path", "orders");
		return "admin/newbee_mall_order";
	}

	@GetMapping("/users")
	public String usersPage(HttpServletRequest request) {
		request.setAttribute("path", "users");
		return "admin/newbee_mall_user";
	}
}
