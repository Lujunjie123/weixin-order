package com.example.sell.controller;

import com.example.sell.dto.OrderDto;
import com.example.sell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("list")
    //这里不能默认为0
    public String list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                       @RequestParam(value = "size",defaultValue = "10")Integer size,
                        Model model){
        //注意：这里不减1 默认第一页开始不显示第一页
        PageRequest request = PageRequest.of(page - 1, size);
        Page<OrderDto> orderDtoPage = orderService.findList(request);
        model.addAttribute("orderDtoPage",orderDtoPage);
//        orderDtoPage.getTotalPages()
        return "order/list";
    }
}































