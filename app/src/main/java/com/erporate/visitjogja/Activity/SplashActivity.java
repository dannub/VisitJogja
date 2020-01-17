package com.erporate.visitjogja.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.erporate.visitjogja.Adapter.WisataAdapter;
import com.erporate.visitjogja.DBqueries;
import com.erporate.visitjogja.R;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        int splash = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);

                this.finish();
            }
            void  finish(){
                SplashActivity.this.finish();
            }
        }, splash);
        MainActivity.currentUser = FirebaseAuth.getInstance().getCurrentUser();

    }

}
