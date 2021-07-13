package com.callor.gallery.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {

	private String m_userid;
	private String m_password;
	private String re_password;
	private String m_nick;
	private String m_tel;
	// int는 기본값이 0으로 setting됨.
	// impl에서 설정하기
	private int m_level;
	
}
