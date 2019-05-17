package com.tw.baselibs.base;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tw.baselibs.R;
import com.tw.baselibs.app.AppConstants;
import com.tw.baselibs.mvp.BasePresenter;
import com.tw.baselibs.mvp.IView;

import java.util.List;

public abstract class BaseActivity<T extends BasePresenter, K extends ViewDataBinding> extends AppCompatActivity implements IView {

    protected T mPresenter;

    protected K mViewDataBinding;

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
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化监听
     */
    protected abstract void initListener();

    /**
     * 是否全屏
     *
     * @return
     */
    protected boolean isFullScreen() {
        return false;
    }

    protected boolean isViewDataBinding() {
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isFullScreen()) {
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        }
        if (isViewDataBinding())
            mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        else
            setContentView(getLayoutId());
        mPresenter = createPresenter();
        attachView();
        initView();
        initListener();
        initData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                } else {
                    finish();
                }
                break;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
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
     * @param viewId
     * @param <V>
     * @return
     */
    protected <V extends View> V $(int viewId) {
        return findViewById(viewId);
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
        intent.setClass(this, cls);
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
        intent.setClass(this, cls);
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
