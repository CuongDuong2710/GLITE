package com.glite.popeyes.data.remote.request.auth;

/**
 * @author Brian
 * @date: 31/08/2016
 */

public final class RegisterRequest {

    public String email;
    public String password;
    public String firstname;
    public String lastname;
    public String gender;
    public String dob;
    public String phone;

    public RegisterRequest(String email, String password, String firstname,
                           String lastname, String gender, String dob, String phone) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.dob = dob;
        this.phone = phone;
    }

    public static RegisterRequest create(String email, String password, String firstname,
                                         String lastname, String gender, String dob, String phone) {
        return new RegisterRequest(email, password, firstname, lastname, gender, dob, phone);
    }
}
