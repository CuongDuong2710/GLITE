package com.glite.popeyes.data.local;

import com.glite.popeyes.data.remote.reponse.authen.Member;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Brian
 * @date: 07/09/2016
 */

@Singleton
public class AuthManager {

    private static final String PREF_KEY_MEMBER = "pref_key_member";

    private final Gson mGson;
    private final PreferenceHelper mSharedHelper;

    @Inject
    AuthManager(Gson gson, PreferenceHelper preferenceHelper) {
        this.mGson = gson;
        this.mSharedHelper = preferenceHelper;
    }

    public void putMember(Member member) {
        final String jsonMember = mGson.toJson(member);
        mSharedHelper.putString(PREF_KEY_MEMBER, jsonMember);
    }

    public Member getMember() {
        final String jsonMember = mSharedHelper.getString(PREF_KEY_MEMBER, null);
        if (jsonMember == null) {
            return null;
        }
        return mGson.fromJson(jsonMember, Member.class);
    }


}
