package com.teemo.service.ui;


import android.util.SparseArray;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Teemo on 2017/10/12.
 *
 * @author Teemo
 * @date 2017/10/12 17:13
 */

public abstract class BaseAction<T extends IBaseConstruct.IBaseView> implements IBaseConstruct.IBaseAction<T> {

    private T mView;

    @Inject
    protected boolean debugMode;

    /**
     * subscription collections
     */
    private final CompositeDisposable mCompositeSubscription = new CompositeDisposable();
    private final SparseArray<Disposable> mSubscriptionMap = new SparseArray<>(4);

    protected BaseAction() {
    }

    public void takeView(T view) {
        mView = view;
    }

    public void dropView() {
//        mView = null;
        mSubscriptionMap.clear();
        if (!mCompositeSubscription.isDisposed()) {
            mCompositeSubscription.dispose();
        }
    }

    @Override
    public T getView() {
        return mView;
    }

    public void registerSubscription(Disposable subscription) {
        mCompositeSubscription.add(subscription);
    }

    public void registerSubscription(int key, Disposable subscription) {
        unRegisterSubscription(key);
        mSubscriptionMap.put(key, subscription);
        mCompositeSubscription.add(subscription);
    }

    public void unRegisterSubscription(int key) {
        //key不存在或者为null，也可以通过
        Disposable d = mSubscriptionMap.get(key);
        if (d != null) {
            mCompositeSubscription.remove(d);
        }
        mSubscriptionMap.remove(key);
    }

}
