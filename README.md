# springboot-rabbitmq
springboot整合rabbitmq
rabbitmq的5种模式：
rabbitmq多种模式:
    基本消息模型：生产者–>队列–>消费者      一对一
    work消息模型：生产者–>队列–>多个消费者共同消费  一对多
    订阅模型-Fanout：广播，将消息交给所有绑定到交换机的队列，每个消费者都会收到同一条消息
    订阅模型-Direct：定向，把消息交给符合指定 rotingKey 的队列
    订阅模型-Topic：通配符，把消息交给符合routing pattern（路由模式） 的队列
但是其实3、4、5这三种都属于订阅模型，只不过进行路由的方式不同

支付宝支付接口测试