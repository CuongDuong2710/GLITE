package com.glite.popeyes.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.glite.popeyes.navigator.Navigator;
import com.glite.popeyes.view.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Navigator.navigateToLoginActivity(this);
        finish();
    }
}
