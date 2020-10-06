package com.yc.snack.user.mapper;

import java.util.List;

import com.yc.snack.user.dto.AddrInfoDTO;

public interface IAddrInfoMapper {
	public List<AddrInfoDTO> findByMno(Integer mno);
	
	public AddrInfoDTO findByAno(String ano);
}
