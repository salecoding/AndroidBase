package com.tw.baselibs.base;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tw.baselibs.R;
import com.tw.baselibs.app.AppConstants;
import com.tw.baselibs.mvp.BasePresenter;
import com.tw.baselibs.mvp.IView;

import java.util.List;

public abstract class BaseFragment<T extends BasePresenter, K extends ViewDataBinding> extends Fragment implements IView {

    protected T mPresenter;

    protected K mViewDataBinding;

    private View mRootView;

    /**
     * 布局ID
     *
     * @return
     */
    protected abstract @LayoutRes
    int getLayoutId();

    protected abstract T createPresenter();

    /**
     * 初始化view
     */
    protected abstract void initView(View view);

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化监听
     */
    protected abstract void initListener();

    protected boolean isViewDataBinding() {
        return false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflaterView(inflater, container);
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = createPresenter();
        attachView();
        initView(mRootView);
        initListener();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        detachView();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showSuccess(String message) {
        ToastUtils.showShort(message);
    }

    @Override
    public void showFail(String message) {
        ToastUtils.showShort(message);
    }

    /**
     * 设置View
     *
     * @param inflater
     * @param container
     */
    private void inflaterView(LayoutInflater inflater, @Nullable ViewGroup container) {
        if (mRootView == null) {
            if (isViewDataBinding()) {
                mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
                if (mViewDataBinding != null) {
                    mRootView = mViewDataBinding.getRoot();
                }
            }
            mRootView = inflater.inflate(getLayoutId(), container, false);
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
    }

    /**
     * @param viewId
     * @param <V>
     * @return
     */
    protected <V extends View> V $(int viewId) {
        return mRootView.findViewById(viewId);
    }

    /**
     * 通过Class跳转界面
     **/
    protected void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    protected void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    protected void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(getContext(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    protected void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getContext(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 设置数据
     *
     * @param adapter
     * @param data
     * @param isRefresh
     */
    protected void setData(BaseQuickAdapter adapter, List data, boolean isRefresh) {
        if (isRefresh) adapter.setNewData(data);
        else adapter.addData(data);
        if (data == null || data.isEmpty() || data.size() < AppConstants.PAGE_SIZE) {
            adapter.loadMoreEnd(false);
        } else {
            adapter.loadMoreComplete();
        }
        if (adapter.getData() == null || adapter.getData().isEmpty())
            adapter.setEmptyView(R.layout.layout_empty_view);
    }

    /**
     * 贴上view
     */
    private void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    /**
     * 分离view
     */
    private void detachView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
