package com.hello.helloSpring.common.bean;

import java.util.List;

import lombok.Data;

@Data
public class MemberTestBean {

	private String name;
	private int age;
	private boolean isStudent;
	private AddressBean address;
	private List<String> languages;
	
}// class
