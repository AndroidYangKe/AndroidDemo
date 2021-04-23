package com.android.yangke.java.p.search;

import com.android.yangke.java.m.network.Api;
import com.android.yangke.java.m.network.ErrorModule;
import com.android.yangke.java.m.network.RetrofitManager;
import com.android.yangke.java.m.utils.LogUtil;
import com.android.yangke.java.m.utils.MathUtil;
import com.android.yangke.java.m.vo.SearchResult;
import com.android.yangke.java.p.base.BasePresenter;
import com.android.yangke.java.v.search.SearchResultActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
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
public class SearchResultPresenter extends BasePresenter<SearchResultActivity> {
    /**
     * @param key 搜索用到的关键字
     */
    public void search(String key, int pageNum) {
        ArrayList<SearchResult> list = new ArrayList<>(25);
        Call<ResponseBody> obs = RetrofitManager.getRetrofit().create(Api.class).searchList("so/" + key + "_rel_" + pageNum + ".html");
        addCallRequest(obs);
        obs.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                LogUtil.e("============onResponse============");
                try {
                    String html = response.body().string();
                    Document document = Jsoup.parse(html);
                    Elements root = document.select("div[class=search-list col-md-8]");
                    Elements itemList = root.select("div[class=search-item]");
                    //粗略获取最大的pageSize
                    Elements ul = root.select("ul[class=pagination]");
                    Elements li = ul.select("li");
                    int maxPageNum = 1;
                    for(Element el : li) {
                        String pageStr = el.text();
                        if (MathUtil.isNumeric(pageStr)) {
                            int page = Integer.parseInt(pageStr);
                            if (page > 1) {
                                maxPageNum = page;
                            }
                        }
                    }

                    //搜索无数据；直接展示空列表
//                    if (!TextUtils.isEmpty(empty) && empty.contains("其它关键词")) {
//                        getMvpView().onSuccess("", null, "");
//                        return;
//                    }
                    //搜索有数据；正常解析
                    for (Element element : itemList) {
                        String name = element.select("h3").select("a").text();
                        String href = element.select("h3").select("a").attr("href");
                        Elements itemBar = element.select("div[class=item-bar]");
                        Elements span = itemBar.select("span");
                        String time = span.get(1).text();
                        String size = span.get(2).text();
                        list.add(new SearchResult(name, time, size, href, ""));
                    }
                    getMvpView().onSuccess("list", list, String.valueOf(maxPageNum));
                } catch (Exception e) {
                    e.printStackTrace();
                    //搜索有数据，解析错误，应该是网站的结构发生了变化
                    getMvpView().onSuccess(ErrorModule.PARSE_ERROR, list, "");
                    LogUtil.e("============search 解析出错============");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                LogUtil.e("============search onFailure============");
                getMvpView().onFailed("", "");
            }
        });
    }

    /**
     * 获取磁力链接
     *
     * @param url 列表页解析出的url后缀
     */
    public void requestGetHref(String url) {
        Call<ResponseBody> obs = RetrofitManager.getRetrofit().create(Api.class).searchList(url);
        addCallRequest(obs);
        obs.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String html = response.body().string();
                    Document document = Jsoup.parse(html);
                    Element p = document.select("div[class=fileDetail]").select("p").get(5);
                    Element mLink = p.getElementById("m_link");
                    String href = mLink.attr("value");
                    getMvpView().onSuccess("", null, href);
                } catch (IOException e) {
                    e.printStackTrace();
                    LogUtil.e("============requestGetHref 解析出错============");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                LogUtil.e("============requestGetHref onFailure============");
                getMvpView().onFailed("", "");
            }
        });
    }
}
