package com.tw.baselibs.mvp;

import android.app.Activity;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author salecoding
 * @date 2018/6/9 17:14
 * @desc
 */
public abstract class BasePresenter<M extends IModel, V extends IView> implements IPresenter<V> {

    private WeakReference<V> mViewRef;
    private M mModel;

    private CompositeDisposable compositeDisposable;

    public BasePresenter() {
        this.mModel = createModel();
    }

    /**
     * 获取 Model
     *
     * @return
     */
    protected abstract M createModel();

    /**
     * 绑定 View
     *
     * @param mView
     */
    @Override
    public void attachView(V mView) {
        mViewRef = new WeakReference(mView);
    }

    /**
     * 解除绑定
     */
    @Override
    public void detachView() {
        unDispose();
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    /**
     * 将 {@link Disposable} 添加到 {@link CompositeDisposable} 中统一管理
     * 可在 {@link Activity#onDestroy()} 中使用 {@link #unDispose()} 停止正在执行的 RxJava 任务，避免内存泄漏(框架已自行处理)
     *
     * @param disposable
     */
    public void addDispose(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);// 将所有 Disposable 放入集中处理
    }

    /**
     * 停止集合中正在执行的 RxJava 任务
     */
    public void unDispose() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();// 保证 Activity 结束时取消所有正在执行的订阅
        }
    }

    public V getView() {
        return mViewRef.get();
    }

    public M getModel() {
        return this.mModel;
    }
}
