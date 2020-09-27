package com.example.sell.controller;

import com.example.sell.converter.OrderFormOrderDtoConverter;
import com.example.sell.dto.OrderDto;
import com.example.sell.eumus.ResultEnum;
import com.example.sell.exception.SellException;
import com.example.sell.form.OrderForm;
import com.example.sell.service.BuyerService;
import com.example.sell.service.OrderService;
import com.example.sell.utils.ResultUtil;
import com.example.sell.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("create")
    public ResultVo<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确,orderForm={}",orderForm);
            throw new SellException(ResultEnum.PORM_ERROR,
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDto orderDto = OrderFormOrderDtoConverter.convert(orderForm);
        if(CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDto dto = orderService.create(orderDto);

        Map<String,String> map = new HashMap<>();
        map.put("orderId",dto.getOrderId());

        return ResultUtil.success(map);
    }
    //订单列表  @RequestParam将请求参数绑定到你控制器的方法参数上
    @GetMapping("list")
    public ResultVo<List<OrderDto>> orderList(@RequestParam("openid") String openid,
                                              @RequestParam(value = "page",defaultValue = "0") Integer page,
                                              @RequestParam(value = "size",defaultValue = "10") Integer size){
        if(StringUtils.isEmpty(openid)){
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PORM_ERROR);
        }
        Page<OrderDto> list = orderService.findList(openid, PageRequest.of(page, size));
        return ResultUtil.success(list.getContent());
    }

    //查询订单详情
    @GetMapping("detail")
    public ResultVo<OrderDto> orderDetail(@RequestParam("openid") String openid,
                                          @RequestParam("orderId") String orderId){

        OrderDto orderDto = buyerService.findOneOrder(openid, orderId);
        return ResultUtil.success(orderDto);
    }

    //取消订单
    @PostMapping("cancel")
    public ResultVo<OrderDto> orderCancel(@RequestParam("openid") String openid,
                                          @RequestParam("orderId") String orderId) {

        buyerService.cancelOrder(openid,orderId);
        return ResultUtil.success();
    }
}



























































