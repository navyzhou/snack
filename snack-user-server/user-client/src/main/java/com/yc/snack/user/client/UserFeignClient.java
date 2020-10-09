package com.yc.snack.user.client;

import java.util.Collections;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.yc.snack.user.dto.AddrInfoDTO;

@FeignClient(name="user-server", fallback = UserFeignClient.UserFeignClientFallback.class)
public interface UserFeignClient {
	@PostMapping("/addr/list")
	public List<AddrInfoDTO> list(@RequestParam Integer mno);
	
	@PostMapping("/member/loginout")
	public int loginout(@RequestParam String openid);
	
	/**
	 * 根据地址编号获取收货地址信息
	 * @param ano
	 * @return
	 */
	@PostMapping("/addr/findByAno")
	public AddrInfoDTO findByAno(@RequestParam String ano);
	
	@PostMapping("/addr/add")
	public int add(@RequestBody AddrInfoDTO addrInfo);
	
	@Component
	public static class UserFeignClientFallback implements UserFeignClient {

		@Override
		public List<AddrInfoDTO> list(Integer mno) {
			return Collections.emptyList();
		}

		@Override
		public int loginout(String openid) {
			return 0;
		}

		@Override
		public AddrInfoDTO findByAno(String ano) {
			return new AddrInfoDTO();
		}

		@Override
		public int add(AddrInfoDTO addrInfo) {
			return 0;
		}
	}
}
