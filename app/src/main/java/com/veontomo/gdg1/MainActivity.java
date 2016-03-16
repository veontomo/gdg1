package com.veontomo.gdg1;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    private void send(@NonNull final TextView tv, String... str){
        Observable.from(str).subscribe(new Action1<String>(){

            @Override
            public void call(String s) {
                tv.setText(s);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onStart(){
        super.onStart();
        TextView tv = (TextView) findViewById(R.id.name);
        while (true){
            send(tv, "Tania");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Log.i("GDG1", e.getMessage());

            }
            send(tv, "Nastia");
        }

    }

}
