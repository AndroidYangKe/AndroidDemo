package com.android.yangke.java.m.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * author : yangke on 2021/3/20
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 管理封装网络请求所有Api，所有API都应写在这里
 */
public interface Api {
    //请求一：http://www.btmovi.shop/so/唐人街探案3_rel_2.html
    //请求二：http://www.btmovi.shop/bt/18db41969fc4b68da24d4f37e913c0249ec709ba.html
    /**
     * http://www.btmovi.shop/so/唐人街探案3_rel_2.html
     * 其中base URL为http://www.btmovi.shop/so/
     * @GET("{searchKey}/{page}") Call<ResponseBody> searchList(@Path("searchKey") String searchKey, @Path("page") String page);
     * 可以得到http://www.btmovi.shop/so/唐人街探案3/1
     */
    //@Query 单个参数的key值
    //@QueryMap map集合
    @GET("{searchKey}")
    Call<ResponseBody> searchList(@Path("searchKey") String searchKey);

}
