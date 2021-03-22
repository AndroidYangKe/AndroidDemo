package com.yangke.app.java.v.base;

/**
 * author : yangke on 2021/3/20
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : MVP中通用的View抽象；所有功能简单的页面都应该继承自IBaseView
 */
public interface IBaseView<T> {

    /**
     * 网络正常时触发此函数；例：网络正常请求、正确解析数据；
     *
     * @param flag 网络标识；可以做网络请求的唯一标识
     * @param t 返回的数据；可做网络请求成功的数据标识；
     * @param str 预留的String参数
     */
    void onSuccess(String flag, T t, String str);

    /**
     * 网络异常时触发此函数；例：无网络、数据解析异常；
     *
     * @param flag 网络标识；可以做网络请求的唯一标识
     * @param obj 返回的数据；如果是错误msg可当Toast msg
     */
    void onFailed(String flag, Object obj);
}
