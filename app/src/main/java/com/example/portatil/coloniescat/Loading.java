package com.example.portatil.coloniescat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//per fer una mica el friki simulem un carregan base de dades que nomses es un delay
public class Loading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        new Thread(){

            @Override
            public void run() {
                try {
                    Thread.sleep(3500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startMainMenu();
                finish();
            }
        }.start();

    }

    private void startMainMenu(){
        startActivity(new Intent(Loading.this, NavigationCat.class));
    }


}
