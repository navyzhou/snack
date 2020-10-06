package com.yc.snack.order.message;

import java.util.List;
import java.util.Map;

import org.assertj.core.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.druid.support.json.JSONUtils;

@Component
public class ProductStockInfoReceiver {
	private final static Logger log = LoggerFactory.getLogger(ProductStockInfoReceiver.class);
	private final static String PRODUCT_STOCK = "productStocks";
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@SuppressWarnings("unchecked")
	@RabbitListener(queuesToDeclare = @Queue("productStock"))
	public void process(String stock) {
		log.info(stock);
		List<Map<String, Object>> list = (List<Map<String, Object>>) JSONUtils.parse(stock);
		list.forEach( map -> {
			redisTemplate.opsForHash().put(PRODUCT_STOCK, Strings.formatIfArgs("p_%s", map.get("gno")), map.get("stock"));
		});
	}
}
