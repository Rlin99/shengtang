package club.banyuan.mall.service.impl;

import club.banyuan.mall.common.NewBeeMallOrderStatusEnum;
import club.banyuan.mall.common.ServiceResultEnum;
import club.banyuan.mall.entity.MallOrder;
import club.banyuan.mall.entity.MallOrderItem;
import club.banyuan.mall.mapper.MallOrderItemMapper;
import club.banyuan.mall.mapper.MallOrderMapper;
import club.banyuan.mall.service.MallOrderService;
import club.banyuan.mall.util.BeanUtil;
import club.banyuan.mall.util.PageQueryUtil;
import club.banyuan.mall.util.PageResult;
import club.banyuan.mall.vo.NewBeeMallOrderDetailVO;
import club.banyuan.mall.vo.NewBeeMallOrderItemVO;
import club.banyuan.mall.vo.NewBeeMallShoppingCartItemVO;
import club.banyuan.mall.vo.NewBeeMallUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class MallOrderServiceImpl implements MallOrderService {
    @Autowired
    private MallOrderMapper mallOrderMapper;

    @Autowired
    private MallOrderItemMapper mallOrderItemMapper;

    @Override
    public PageResult getNewBeeMallOrdersPage(PageQueryUtil pageUtil) {
        List<MallOrder> mallOrders = mallOrderMapper.findNewBeeMallOrderList(pageUtil);
        int total = mallOrderMapper.getTotalNewBeeMallOrders(pageUtil);
        PageResult pageResult = new PageResult(mallOrders, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    @Transactional
    public String updateOrderInfo(MallOrder newBeeMallOrder) {
        //通过形参中的orderID来查询数据库是否有这条记录，有则可以修改
        MallOrder temp = mallOrderMapper.selectByPrimaryKey(newBeeMallOrder.getOrderId());
        //不为空且orderStatus>=0且状态为出库之前可以修改部分信息
        if (temp != null && temp.getOrderStatus() >= 0 && temp.getOrderStatus() < 3){
            temp.setTotalPrice(newBeeMallOrder.getTotalPrice());
            temp.setUserAddress(newBeeMallOrder.getUserAddress());
            temp.setUpdateTime(new Date());
            if(mallOrderMapper.updateByPrimaryKeySelective(temp) > 0){
                return ServiceResultEnum.SUCCESS.getResult();
            }
            return ServiceResultEnum.DB_ERROR.getResult();
        }
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
    }

    @Override
    @Transactional
    public String checkDone(Long[] ids) {
        //查询所有的订单 判断状态 修改状态和更新时间
        List<MallOrder> orders = mallOrderMapper.selectByPrimaryKeys(Arrays.asList(ids));
        String errorOrderNos = "";
        if(!CollectionUtils.isEmpty(orders)){
            //查询需要修改的订单是否被删除（删除：isdelete=1）和 订单的状态是否是支付成功（支付成功：1）
            for (MallOrder order : orders) {
                if(order.getIsDeleted() == 1){
                    errorOrderNos += order.getOrderNo() + " ";
                    continue;
                }
                if(order.getOrderStatus() != 1){
                    errorOrderNos += order.getOrderNo() + " ";
                }
            }
            if(StringUtils.isEmpty(errorOrderNos)){
                //订单状态正常 可以执行配货完成操作 修改订单状态和更新时间
                if (mallOrderMapper.checkDone(Arrays.asList(ids)) > 0) {
                    return ServiceResultEnum.SUCCESS.getResult();
                } else {
                    return ServiceResultEnum.DB_ERROR.getResult();
                }
            }else {
                //订单此时不可执行出库操作
                if (errorOrderNos.length() > 0 && errorOrderNos.length() < 100) {
                    return errorOrderNos + "订单的状态不是支付成功无法执行出库操作";
                } else {
                    return "你选择了太多状态不是支付成功的订单，无法执行配货完成操作";
                }
            }

        }
        //未查询到数据 返回错误提示
        return ServiceResultEnum.DATA_NOT_EXIST.getResult();
    }

    @Override
    @Transactional
    public String checkOut(Long[] ids) {
        List<MallOrder> orders = mallOrderMapper.selectByPrimaryKeys(Arrays.asList(ids));
        String errorOrderNos = "";
        if(!CollectionUtils.isEmpty(orders)){
            //查询需要修改的订单是否被删除（删除：isdelete=1）和 订单的状态是否是支付成功（支付成功：1）
            for (MallOrder order : orders) {
                if(order.getIsDeleted() == -1){
                    errorOrderNos += order.getOrderNo() + " ";
                    continue;
                }
                if(order.getOrderStatus() != 1){
                    errorOrderNos += order.getOrderNo() + " ";
                }
            }
            if(StringUtils.isEmpty(errorOrderNos)){
                if(mallOrderMapper.checkOut(Arrays.asList(ids)) > 0){
                    return ServiceResultEnum.SUCCESS.getResult();
                }else{
                    return ServiceResultEnum.DB_ERROR.getResult();
                }
            }else {
                if(errorOrderNos.length() > 0 && errorOrderNos.length() < 100){
                    return errorOrderNos + "订单的状态不是支付成功或配货完成无法执行出库操作";
                }else {
                    return "你选择了太多状态不是支付成功或配货完成的订单，无法执行出库操作";
                }
            }
        }
        //未查询到数据 返回错误提示
        return ServiceResultEnum.DATA_NOT_EXIST.getResult();
    }

    @Override
    @Transactional
    public String closeOrder(Long[] ids) {
        List<MallOrder> orders = mallOrderMapper.selectByPrimaryKeys(Arrays.asList(ids));
        String errorOrderNos = "";
        if(!CollectionUtils.isEmpty(orders)){
            for (MallOrder order : orders) {
                //1表示已经关闭
                if(order.getIsDeleted() == 1){
                    errorOrderNos += order.getOrderNo() + " ";
                    continue;
                }
                //已关闭或者已完成无法关闭订单
                if(order.getOrderStatus() == 4 || order.getOrderStatus() < 0){
                    errorOrderNos += order.getOrderNo() + " ";
                }
            }
            if( StringUtils.isEmpty(errorOrderNos) ){
                if(mallOrderMapper.closeOrder(Arrays.asList(ids), NewBeeMallOrderStatusEnum.ORDER_CLOSED_BY_JUDGE.getOrderStatus()) > 0){
                    return ServiceResultEnum.SUCCESS.getResult();
                }else {
                    return ServiceResultEnum.DB_ERROR.getResult();
                }
            }else {
                //订单此时不可执行关闭操作
                if (errorOrderNos.length() > 0 && errorOrderNos.length() < 100) {
                    return errorOrderNos + "订单不能执行关闭操作";
                } else {
                    return "你选择的订单不能执行关闭操作";
                }
            }

        }
        //未查询到数据 返回错误提示
        return ServiceResultEnum.DATA_NOT_EXIST.getResult();
    }

    @Override
    public String saveOrder(NewBeeMallUserVO user, List<NewBeeMallShoppingCartItemVO> myShoppingCartItems) {
        return null;
    }

    @Override
    public NewBeeMallOrderDetailVO getOrderDetailByOrderNo(String orderNo, Long userId) {
        return null;
    }

    @Override
    public MallOrder getNewBeeMallOrderByOrderNo(String orderNo) {
        return null;
    }

    @Override
    public PageResult getMyOrders(PageQueryUtil pageUtil) {
        return null;
    }

    @Override
    public String cancelOrder(String orderNo, Long userId) {
        return null;
    }

    @Override
    public String finishOrder(String orderNo, Long userId) {
        return null;
    }

    @Override
    public String paySuccess(String orderNo, int payType) {
        return null;
    }

    @Override
    public List<NewBeeMallOrderItemVO> getOrderItems(Long id) {
        MallOrder newBeeMallOrder = mallOrderMapper.selectByPrimaryKey(id);
        if (newBeeMallOrder != null) {
            List<MallOrderItem> orderItems = mallOrderItemMapper.selectByOrderId(newBeeMallOrder.getOrderId());
            //获取订单项数据
            if (!CollectionUtils.isEmpty(orderItems)) {
                List<NewBeeMallOrderItemVO> newBeeMallOrderItemVOS = BeanUtil.copyList(orderItems, NewBeeMallOrderItemVO.class);
                return newBeeMallOrderItemVOS;
            }
        }
        return null;
    }
}
