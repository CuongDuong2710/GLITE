package com.glite.popeyes.data.remote.reponse.authen;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HP on 03/08/2016.
 */

public class AuthenData {

    @SerializedName("member")
    private Member member;

    /**
     * @return The member
     */
    public Member getMember() {
        return member;
    }

    /**
     * @param member The member
     */
    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "Data{" +
                "member=" + member +
                '}';
    }
}
