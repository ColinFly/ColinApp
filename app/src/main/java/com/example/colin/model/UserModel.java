package com.example.colin.model;


import java.io.Serializable;

/**
 */
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;


    private String userId;

    private String userName;

    private String sex;
    
    private String email;
    
    private String phone;

    private String birthday;

    private String weight;

    private String height;

    private String avatar;

    private String city;

    private String loginTime;


    @Override
    public String toString() {
        return "UserInfo [userId=" + userId + ", userName=" + userName
                +  ", sex=" + sex
                + ", birthday=" + birthday + ", weight=" + weight + ", height="
                + height + ", avatar=" + avatar + ", city=" + city
                +  "]";
    }
    
    public String getEmail() {
		return email;
	}
    
    public void setEmail(String email) {
		this.email = email;
	}
    
    public String getPhone() {
		return phone;
	}
    
    public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
}
