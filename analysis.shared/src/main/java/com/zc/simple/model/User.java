package com.zc.simple.model;

import java.io.Serializable;

public class User implements Serializable{

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 2534019221434557704L;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.id
	 * @mbg.generated  Wed Nov 16 16:40:32 CST 2016
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.user_name
	 * @mbg.generated  Wed Nov 16 16:40:32 CST 2016
	 */
	private String userName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.age
	 * @mbg.generated  Wed Nov 16 16:40:32 CST 2016
	 */
	private Integer age;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.id
	 * @return  the value of user.id
	 * @mbg.generated  Wed Nov 16 16:40:32 CST 2016
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.id
	 * @param id  the value for user.id
	 * @mbg.generated  Wed Nov 16 16:40:32 CST 2016
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.user_name
	 * @return  the value of user.user_name
	 * @mbg.generated  Wed Nov 16 16:40:32 CST 2016
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.user_name
	 * @param userName  the value for user.user_name
	 * @mbg.generated  Wed Nov 16 16:40:32 CST 2016
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.age
	 * @return  the value of user.age
	 * @mbg.generated  Wed Nov 16 16:40:32 CST 2016
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.age
	 * @param age  the value for user.age
	 * @mbg.generated  Wed Nov 16 16:40:32 CST 2016
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", age=" + age + "]";
	}
}