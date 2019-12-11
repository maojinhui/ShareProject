package com.mao.shareproject.base.mvp;

import org.reactivestreams.Subscriber;

import java.lang.ref.WeakReference;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class BasePresenter<V extends BaseView> {

    protected WeakReference<V> mView;

    /**
     * 如果在请求过程中，UI层destroy了怎么办，不及时取消订阅，可能会造成内存泄漏。
     * 因此，CompositeDisposable可以对我们订阅的请求进行统一管理
     */

    private CompositeDisposable mCompositeDisposable;

    protected   void attachView(V view){
        mView=new WeakReference<>(view);
    }

    public void detachView(){
        mView.clear();
        mView=null;
        onUnSubscribe();
    }

    protected boolean isAttached(){
        return mView!=null;
    }


    //RxJava取消注册，以避免内存泄露
    private void onUnSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
            mCompositeDisposable.dispose();
        }
    }

    /**
     * 注册rxjava
     * @param observable
     * @param observer
     */
    protected void addSubscription(Observable observable, DisposableObserver observer) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(observer);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer);
    }


    /**
     * 注册rxjava
     * 背压策略: 如果上游发射的很快而下游处理的很慢,将会产生很多下游没来得及处理的数据，这些数据既不会丢失，
     * 也不会被垃圾回收机制回收，而是存放在一个异步缓存池中，如果缓存池中的数据一直得不到处理，越积越多，
     * 最后就会造成内存溢出，这便是Rxjava中的背压问题。
     *
     *
     * @param flowable
     * @param subscriber
     */
    public void addSubscription(Flowable flowable , Subscriber subscriber){
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(subscriber);
    }


}
