package com.space.rabbitmq.zhifubao;

import com.alipay.api.AlipayApiException;
import com.space.rabbitmq.util.AlipayUtil;
import com.space.rabbitmq.model.AlipayBean;
import org.springframework.stereotype.Service;

/* 支付服务 */
@Service(value = "alipayOrderService")
public class PayServiceImpl implements PayService {
    @Override
    public String aliPay(AlipayBean alipayBean) throws AlipayApiException {
        return AlipayUtil.connect(alipayBean);
    }
}