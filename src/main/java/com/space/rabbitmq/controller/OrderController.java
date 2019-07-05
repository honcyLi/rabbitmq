package com.space.rabbitmq.controller;

import com.alipay.api.AlipayApiException;
import com.space.rabbitmq.model.AlipayBean;
import com.space.rabbitmq.zhifubao.PayService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/* 订单接口 */
@RestController()
@RequestMapping("order")
public class OrderController {
    @Resource
    private PayService payService;//调用支付服务​

    /*阿里支付*/
    @RequestMapping( "/alipay")
    public String alipay(String out_trade_no,String subject,String total_amount,String body) throws AlipayApiException {
       AlipayBean a = new AlipayBean();
        a.setBody("主体");
        a.setOut_trade_no("编号");
        a.setSubject("技术学习");
        a.setOut_trade_no("1234567890");
        a.setTotal_amount(new StringBuffer().append("0.01"));
        return  payService.aliPay(a);
    }
}