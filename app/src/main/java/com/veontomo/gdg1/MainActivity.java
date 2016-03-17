package com.veontomo.gdg1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import rx.Observable;
import rx.Observer;
import rx.functions.Func1;
import rx.subjects.PublishSubject;

public class MainActivity extends AppCompatActivity {

    private PublishSubject<String> subject;
    private String[] pool = new String[]{"Tania", "Nastia", "Ланёнок", "Колбаска"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onResume() {
        super.onResume();
        final TextView tv = (TextView) findViewById(R.id.name);
        Observer<String> greeter = new Observer<String>() {

            @Override
            public void onCompleted() {
                Log.i("GDG1", "No more greetings." + " " + System.currentTimeMillis());
            }

            @Override
            public void onError(Throwable e) {
                Log.i("GDG1", "I'd greet somebody more!");
            }

            @Override
            public void onNext(String t) {
                tv.setText(t);
                Log.i("GDG1", t + " " + System.currentTimeMillis());
            }

        };
        subject = PublishSubject.create();
        subject.map(new Func1<String, String>(){
            @Override
            public String call(String s) {
                return s.toUpperCase();
            }
        }).subscribe(greeter);

    }

    public void start(View v) {
        int rnd = new Random().nextInt(pool.length);
        subject.onNext(pool[rnd]);

    }

}
