package com.glite.popeyes.data.remote.request.auth;

/**
 * @author Brian
 * @date: 07/09/2016
 */

public final class SocialAuthRequest {

    public String provider;
    public String token;
    public String email;
    public String phone_number;
    public String birthday;
    public String picture;
    public String gender;
    public String first_name;
    public String last_name;

    public SocialAuthRequest() {
    }

    public SocialAuthRequest(String provider, String token, String email, String phone_number,
                             String birthday, String picture, String gender, String first_name,
                             String last_name) {
        this.provider = provider;
        this.token = token;
        this.email = email;
        this.phone_number = phone_number;
        this.birthday = birthday;
        this.picture = picture;
        this.gender = gender;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public static SocialAuthRequest create(String provider, String token, String email, String phone_number,
                                           String birthday, String picture, String gender, String first_name,
                                           String last_name) {
        return new SocialAuthRequest(provider, token, email, phone_number, birthday,
                picture, gender, first_name, last_name);
    }

    @Override
    public String toString() {
        return "SocialAuthRequest{" +
                "provider='" + provider + '\'' +
                ", token='" + token + '\'' +
                ", email='" + email + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", birthday='" + birthday + '\'' +
                ", picture='" + picture + '\'' +
                ", gender='" + gender + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                '}';
    }
}
