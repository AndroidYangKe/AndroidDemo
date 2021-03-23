package com.android.yangke.java.p.base;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;

/**
 * author : yangke on 2021/3/20
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : MVP架构中所有Presenter的基础类
 */
public class BasePresenter<T> {
    private List<Call> mCallRequests;
    private CompositeDisposable mDisposable;
    /**
     * 数据回调接口
     */
    public T mMvpView;

    /**
     * 封装网络对象和回调，使用泛型替换object，不用类型转换
     *
     * @param mvpView 页面回调对象
     */
    public void attachView(T mvpView) {
        mCallRequests = new ArrayList<>();
        mDisposable = new CompositeDisposable();
        mMvpView = mvpView;
    }

    public void addCallRequest(Call call) {
        if (null != mCallRequests) {
            mCallRequests.add(call);
        }
    }

    public void addDisposable(Disposable disposable) {
        if (null != disposable) {
            mDisposable.add(disposable);
        }
    }

    /**
     * 断开CompositeSubscription引用，没有被处理的请求也将被销毁
     */
    public void detachView() {
        if (null != mDisposable) {
            mDisposable.clear();
        }
        if (null != mCallRequests && !mCallRequests.isEmpty()) {
            for (Call call : mCallRequests) {
                if (!call.isCanceled()) {
                    call.cancel();
                }
            }
        }
    }

    public T getMvpView() {
        return mMvpView;
    }
}
