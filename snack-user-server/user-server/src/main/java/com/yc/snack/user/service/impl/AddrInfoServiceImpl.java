package com.yc.snack.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.snack.user.dto.AddrInfoDTO;
import com.yc.snack.user.mapper.IAddrInfoMapper;
import com.yc.snack.user.service.IAddrInfoService;

/**
 * company 源辰信息
 * @author navy
 * @date 2020年10月5日
 * Email haijunzhou@hnit.edu.cn
 */
@Service
public class AddrInfoServiceImpl implements IAddrInfoService{
	@Autowired
	private IAddrInfoMapper addrInfoMapper;
		
	@Override
	public List<AddrInfoDTO> findByMno(Integer mno) {
		return addrInfoMapper.findByMno(mno);
	}

	@Override
	public AddrInfoDTO findByAno(String ano) {
		return addrInfoMapper.findByAno(ano);
	}

	@Override
	public int add(AddrInfoDTO addrInfo) {
		return addrInfoMapper.add(addrInfo);
	}

}
