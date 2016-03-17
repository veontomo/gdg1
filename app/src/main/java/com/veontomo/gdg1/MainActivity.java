package com.veontomo.gdg1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;
import rx.subjects.PublishSubject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start(View v) {
        final TextView tv = (TextView) findViewById(R.id.name);
        Observable<String> names = Observable.just("Tania", "Nastia");

        Observer<String> greeter = new Observer<String>() {

            @Override
            public void onCompleted() {
               Log.i("GDG1", "No more greetings.");
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
        names.subscribe(greeter);

        PublishSubject<String> subject = PublishSubject.create();
        subject.subscribe(greeter);
        subject.onNext("Nastia");
        try {
            Thread.sleep(1000);
            subject.onNext("Tania 1");
            Thread.sleep(1000);
            subject.onNext("Tania 2");
            Thread.sleep(1000);
            subject.onNext("Tania 3");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
