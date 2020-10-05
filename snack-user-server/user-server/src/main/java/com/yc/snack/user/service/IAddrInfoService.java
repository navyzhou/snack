package com.yc.snack.user.service;

import java.util.List;

import com.yc.snack.user.dto.AddrInfoDTO;

public interface IAddrInfoService {
	public List<AddrInfoDTO> findByMno(Integer mno);
}
