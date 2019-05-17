package com.tw.baselibs.mvp;

/**
 * @author salecoding
 * @date 2018/6/9 17:12
 * @desc
 */
public interface IView {

    /**
     * 显示进度中
     */
    void showLoading();

    /**
     * 隐藏进度
     */
    void hideLoading();

    /**
     * 显示请求成功
     *
     * @param message
     */
    void showSuccess(String message);

    /**
     * 显示失败
     *
     * @param message
     */
    void showFail(String message);
}
