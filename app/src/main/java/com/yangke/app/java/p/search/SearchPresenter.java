package com.yangke.app.java.p.search;

import android.text.TextUtils;

import com.yangke.app.java.m.network.Api;
import com.yangke.app.java.m.network.ErrorModule;
import com.yangke.app.java.m.network.RetrofitManager;
import com.yangke.app.java.m.utils.EasyLog;
import com.yangke.app.java.m.vo.SearchResult;
import com.yangke.app.java.p.base.BasePresenter;
import com.yangke.app.java.v.search.SearchResultActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author : yangke on 2021/3/20
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 搜索Presenter
 */
public class SearchPresenter extends BasePresenter<SearchResultActivity> {
    /**
     * @param key 搜索用到的关键字
     */
    public void search(String key, int pageNum) {
        ArrayList<SearchResult> list = new ArrayList<>(25);
        Call<ResponseBody> obs = RetrofitManager.getRetrofit().create(Api.class).searchList(key, pageNum + "");
        addCallRequest(obs);
        obs.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                EasyLog.e("============onResponse============");
                try {
                    String html = response.body().string();
                    Document document = Jsoup.parse(html);
                    Elements c = document.getElementsByClass("table table-bordered table-striped");
                    Elements empty = document.getElementsByClass("panel-body table-responsive table-condensed");
                    String str = empty.select("p").text();
                    //搜索无数据；直接展示空列表
                    if (!TextUtils.isEmpty(str) && str.contains("其它关键词")) {
                        getMvpView().onSuccess("", null);
                        return;
                    }
                    //搜索有数据；正常解析
                    for (Element element : c) {
                        Elements item = element.getElementsByClass("text-left");
                        String name = item.text(); //搜索的名字
                        Elements trs = element.select("table").select("tr");
                        Elements tds = trs.get(1).select("td");
                        String date = tds.get(0).text();
                        String size = tds.get(1).text();
                        String href = tds.select("a").attr("href");
                        list.add(new SearchResult(name, date, size, href, ""));
                    }
                    getMvpView().onSuccess("", list);
                } catch (Exception e) {
                    e.printStackTrace();
                    //搜索有数据，解析错误，应该是网站的结构发生了变化
                    getMvpView().onSuccess(ErrorModule.PARSE_ERROR, list);
                    EasyLog.e("============解析出错============");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                EasyLog.e("============onFailure============");
                getMvpView().onFailed("", "");
            }
        });
    }
}
