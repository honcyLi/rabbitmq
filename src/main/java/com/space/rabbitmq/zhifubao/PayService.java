package com.space.rabbitmq.zhifubao;

import com.alipay.api.AlipayApiException;
import com.space.rabbitmq.model.AlipayBean;

/*支付服务*/
public interface PayService {
    /*支付宝*/
    String aliPay(AlipayBean alipayBean) throws AlipayApiException;
}

