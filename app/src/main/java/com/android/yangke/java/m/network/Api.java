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
    //http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=1&limit=20&page=1 油闷大虾串'
    //@Query 单个参数的key值
    //@QueryMap map集合
    @GET("{searchKey}/{page}")
    Call<ResponseBody> searchList(@Path("searchKey") String searchKey, @Path("page") String page);

}
