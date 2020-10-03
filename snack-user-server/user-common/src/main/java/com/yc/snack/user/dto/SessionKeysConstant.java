package com.yc.snack.user.dto;

/**
 * Session常量表
 * company 源辰信息
 * @author navy
 * @date 2020年10月3日
 * Email haijunzhou@hnit.edu.cn
 */
public interface SessionKeysConstant {
	String CURRENTBACKLOGINACCOUNT = "currentBackLogin"; // 存放后台登录用户信息的
	String CURRENTMEMBERACCOUNT = "currentMember"; // 存放前台登录的会员账号信息
	String VCODE = "vcode"; // 图像验证码
	String EMIALVCODE = "emailCode"; // 邮箱验证码
}
