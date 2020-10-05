package com.yc.snack.user.client;

import java.util.Collections;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yc.snack.user.dto.AddrInfoDTO;

@FeignClient(name="user-server", fallback = UserFeignClient.UserFeignClientFallback.class)
public interface UserFeignClient {
	@PostMapping("/addr/list")
	public List<AddrInfoDTO> list(@RequestParam Integer mno);
	
	@PostMapping("/member/loginout")
	public int loginout(@RequestParam String openid);
	
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
		
	}
}
