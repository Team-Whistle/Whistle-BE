package com.feature.user.domain;

import lombok.*;

import javax.persistence.*;

/**
 * Created on July, 2023
 *
 * @author uihyeon1229
 */
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	private String userNm;

	private String userMobno;

	private String userEmail;

	private String userStCd;

	@Enumerated(EnumType.STRING)
	private UserRole userType;

	private String snsTypeCd;

	private String gender;

	private String regDtm;

	private String updDtm;

	private String photo_path;

	private String photo_url;

	private String password;

	@Builder
	public User (String userEmail){
		this.userEmail = userEmail;
	}
}
