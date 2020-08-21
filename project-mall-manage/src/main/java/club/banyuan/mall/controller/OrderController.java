package club.banyuan.mall.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import club.banyuan.mall.common.ServiceResultEnum;
import club.banyuan.mall.entity.MallOrder;
import club.banyuan.mall.service.MallOrderService;
import club.banyuan.mall.util.PageQueryUtil;
import club.banyuan.mall.vo.NewBeeMallOrderItemVO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import club.banyuan.mall.dto.Order;
import club.banyuan.mall.util.Result;
import club.banyuan.mall.util.ResultGenerator;

@RestController
@RequestMapping("/admin")
public class OrderController {

	@Autowired
	private MallOrderService orderService;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@GetMapping("/orders/list")
	public Result<?> list(@RequestParam Map<String, Object> params) {
		if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
			return ResultGenerator.genFailResult("参数异常！");
		}
		PageQueryUtil pageUtil = new PageQueryUtil(params);
		return ResultGenerator.genSuccessResult(orderService.getNewBeeMallOrdersPage(pageUtil));
	}

	@PostMapping("/orders/update")
	public Result<?> update(@RequestBody MallOrder newBeeMallOrder) {
		if (Objects.isNull(newBeeMallOrder.getTotalPrice())
				|| Objects.isNull(newBeeMallOrder.getOrderId())
				|| newBeeMallOrder.getOrderId() < 1
				|| newBeeMallOrder.getTotalPrice() < 1
				|| StringUtils.isEmpty(newBeeMallOrder.getUserAddress())) {
			return ResultGenerator.genFailResult("参数异常！");
		}
		String result =orderService.updateOrderInfo(newBeeMallOrder);
		if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult(result);
		}
	}

	@GetMapping("/order-items/{id}")
	public Result<?> info(@PathVariable("id") Long id) {
		List<NewBeeMallOrderItemVO> orderItems = orderService.getOrderItems(id);
		if (!CollectionUtils.isEmpty(orderItems)) {
			return ResultGenerator.genSuccessResult(orderItems);
		}
		return ResultGenerator.genFailResult(ServiceResultEnum.DATA_NOT_EXIST.getResult());
	}

	//配货完成
	@PostMapping("/orders/checkDone")
	public Result<?> checkDone(@RequestBody Long[] ids) {
		if (ids.length < 1) {
			return ResultGenerator.genFailResult("参数异常！");
		}
		String result = orderService.checkDone(ids);
		if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
			for (Long id : ids) {
				String s = "订单号为：" + id.toString() + " 的订单已经配货完成！";
				rabbitTemplate.convertAndSend("direct","info",s);
			}
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult(result);
		}
	}

	//出库
	@PostMapping("/orders/checkOut")
	public Result<?> checkOut(@RequestBody Long[] ids) {
		if (ids.length < 1) {
			return ResultGenerator.genFailResult("参数异常！");
		}
		String result = orderService.checkOut(ids);
		if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
			for (Long id : ids) {
				String s = "订单号为：" + id + "的订单已经出库！";
				rabbitTemplate.convertAndSend("direct","info",s);
			}
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult(result);
		}
	}

	@PostMapping("/orders/close")
	public Result<?> closeOrder(@RequestBody Long[] ids) {
		if (ids.length < 1) {
			return ResultGenerator.genFailResult("参数异常！");
		}
		String result = orderService.closeOrder(ids);
		if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
			for (Long id : ids) {
				String s = "订单号为：" + id + "的订单已经关闭！";
				rabbitTemplate.convertAndSend("direct","info",s);
			}
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult(result);
		}
	}
}
