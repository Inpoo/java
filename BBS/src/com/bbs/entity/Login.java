package com.bbs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * �û���¼ʵ��
 * @author Zhao Yundi
 * @date 2015��6��1�� ����8:40:50
 */

@Entity
@Table(name="t_login")
public class Login {
	private int userId;
	private int nickName;
	private int userPassword;
	@Column(name="user_id")
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Column(name="user_name")
	public int getNickName() {
		return nickName;
	}
	public void setNickName(int nickName) {
		this.nickName = nickName;
	}
	@Column(name="user_password")
	public int getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(int userPassword) {
		this.userPassword = userPassword;
	}
}
