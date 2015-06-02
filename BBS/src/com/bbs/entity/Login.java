package com.bbs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * 用户登录实体
 * @author Zhao Yundi
 * @date 2015年6月1日 下午8:40:50
 */

@Entity
@Table(name="t_login")
public class Login {
	private int userId;
	private String nickName;
	private String password;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
