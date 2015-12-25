package com.example.colin.model;


import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author weatherfish
 *
 */
public class AuthModel implements Serializable {
	private String accessToken;//	访问凭证
	private String accessTokenExpires;//访问凭证失效时间（秒）
	private String refreshToken;//刷新token的凭证
	private String refreshTokenExpires;//刷新凭证失效时间（秒）
    private long catchTime;//获取的时间

	public String getAccessToken() {
		return accessToken;
	}

	public String getAccessTokenExpires() {
		return accessTokenExpires;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public String getRefreshTokenExpires() {
		return refreshTokenExpires;
	}

	public long getCatchTime() {
		return catchTime;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public void setCatchTime(long catchTime) {

		this.catchTime = catchTime;
	}

	@Override
	public String toString() {
		return "AuthModel{" +
				"accessToken='" + accessToken + '\'' +
				", accessTokenExpires='" + accessTokenExpires + '\'' +
				", refreshToken='" + refreshToken + '\'' +
				", refreshTokenExpires='" + refreshTokenExpires + '\'' +
				", catchTime=" + catchTime +
				'}';
	}
}
