package com.example.colin.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.colin.R;
import com.example.colin.base.BaseFragment;

import java.util.List;

import butterknife.ButterKnife;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by colin on 15-12-15.
 */
public class SchedualFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedual, container, false);
        ButterKnife.inject(this, view);
//        myObservable.subscribe(mySubscriber);
//        firstWay();
//        secondWay();
//        thirdWay();
//        forthWay();
        fifthWay();
        return view;

    }

    private void fifthWay() {

    }


    //from可接收一个集合作为输入
    private void forthWay() {
        String[] urls = {"url1", "url2", "url3"};
        Observable.from(urls).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
    }

    //用map处理数据
    private void thirdWay() {
        Observable.just("Hello,world!")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + "-colin";
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                });
    }

    //只接受想要的对象
    private void firstWay() {
        Observable<String> myObservable = Observable.just("Hello, world!");
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        };
        myObservable.subscribe(onNextAction);
    }

    //just只发你想发的，然后接收
    private void secondWay() {
        Observable.just("Hello,world!").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
    }


    //被观察者
//    Observable<String> myObservable=Observable.create(new Observable.OnSubscribe<String>() {
//        @Override
//        public void call(Subscriber<? super String> subscriber) {
//            subscriber.onNext("Hello, world!");//在onNext中传递参数
//            subscriber.onCompleted();
//        }
//    });

    //观察者
//    Subscriber<String> mySubscriber=new Subscriber<String>() {
//        @Override
//        public void onCompleted() {
//        }
//
//        @Override
//        public void onError(Throwable e) {
//
//        }
//
//        @Override
//        public void onNext(String s) {
//            System.out.println(s);
//
//        }
//    };

    //    @OnClick({})
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//
//        }
//    }
}