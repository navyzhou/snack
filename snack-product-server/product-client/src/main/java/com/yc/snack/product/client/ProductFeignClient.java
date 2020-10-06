package com.yc.snack.product.client;

import java.util.Collections;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.yc.snack.product.dto.CartInfoDTO;
import com.yc.snack.product.dto.ProductInfoDTO;
import com.yc.snack.product.dto.ProductTypeInfoDTO;

/**
 * 商品服务器对外提供的调用接口
 * company 源辰信息
 * @author navy
 * @date 2020年10月4日
 * Email haijunzhou@hnit.edu.cn
 */
// name: 配置需要调用的服务的服务名称
@FeignClient(name="product-server", fallback = ProductFeignClient.ProductFeignClientFallback.class)
public interface ProductFeignClient {
	/**
	 * 根据给定的商品编号获取商品信息
	 * @param gnos
	 * @return
	 */
	@PostMapping("/product/listForGno")
	public List<ProductInfoDTO> findByGno(@RequestParam List<String> gnos);
	
	/**
	 * 根据购物车编号获取商品信息
	 * @param cnos
	 * @return
	 */
	@PostMapping("/product/listForCno")
	public List<ProductInfoDTO> findByCno(@RequestParam List<String> cnos);

	/**
	 * 扣库存的方法
	 * @param list
	 * @return
	 */
	@PostMapping("/product/buckleStock")
	public int buckleStock(@RequestBody List<CartInfoDTO> list);
	
	
	@GetMapping("/product/types")
	public List<ProductTypeInfoDTO> findTypes();
	
	/**
	 * 根据购物车编号删除购物信息
	 * @param cnos
	 * @return
	 */
	@PostMapping("/cart/del")
	public int delCart(@RequestParam List<String> cnos);
	
	/**
	 * 获取购物车信息
	 * @return
	 */
	@PostMapping("/cart/list")
	public List<CartInfoDTO> findCartList(@RequestParam String mno);
	
	/**
	 * 如果产生服务降级，则会自动调用对应的实现方法
	 * company 源辰信息
	 * @author navy
	 * @date 2020年10月4日
	 * Email haijunzhou@hnit.edu.cn
	 */
	@Component
	public static class ProductFeignClientFallback implements ProductFeignClient {

		@Override
		public List<ProductInfoDTO> findByGno(List<String> gnos) {
			return Collections.emptyList();
		}

		@Override
		public List<ProductInfoDTO> findByCno(List<String> cnos) {
			return Collections.emptyList();
		}

		@Override
		public int buckleStock(List<CartInfoDTO> list) {
			return -1;
		}

		@Override
		public List<ProductTypeInfoDTO> findTypes() {
			return Collections.emptyList();
		}

		@Override
		public int delCart(List<String> cnos) {
			return -1;
		}

		@Override
		public List<CartInfoDTO> findCartList(String mno) {
			return Collections.emptyList();
		}
	}
}
