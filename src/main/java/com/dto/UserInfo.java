package com.dto;

import lombok.Data;

@Data
public class UserInfo {
	private int userSeq;
	private String userId;
	private String userNick;
	private int roles;
	private int imgSeq;
	private String storedImgPath;
	private int writeCnt;
	
}
