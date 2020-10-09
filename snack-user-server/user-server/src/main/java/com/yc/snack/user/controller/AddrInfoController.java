package com.yc.snack.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yc.snack.user.dto.AddrInfoDTO;
import com.yc.snack.user.service.IAddrInfoService;

@RestController
@RequestMapping("/addr")
public class AddrInfoController {
	@Autowired
	private IAddrInfoService addrInfoService;
	
	@PostMapping("/list")
	public List<AddrInfoDTO> list(@RequestParam Integer mno) {
		return addrInfoService.findByMno(mno);
	}
	
	@PostMapping("findByAno")
	public AddrInfoDTO findByAno(@RequestParam String ano) {
		return addrInfoService.findByAno(ano);
	}
	
	@PostMapping("/add")
	public int add(@RequestBody AddrInfoDTO addrInfo) {
		return addrInfoService.add(addrInfo);
	}
}
