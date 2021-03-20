package com.yangke.app.java.p.search;

import com.yangke.app.java.m.network.Api;
import com.yangke.app.java.m.network.RetrofitManager;
import com.yangke.app.java.m.utils.EasyLog;
import com.yangke.app.java.p.base.BasePresenter;
import com.yangke.app.java.v.MainActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author : yangke on 2021/3/20
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   :
 */
public class SearchPresenter extends BasePresenter<MainActivity> {
    /**
     * @param key 搜索用到的关键字
     */
    public void search(String key, int pageNum) {
        Call<ResponseBody> obs = RetrofitManager.getRetrofit().create(Api.class).searchList(key, pageNum + "");
        addCallRequest(obs);
        obs.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String html = response.body().string();
                    Document document = Jsoup.parse(html);
//                    EasyLog.e("onFailure:"+document);
                    Elements elements = document.select("wrapp");
                    Elements c = document.getElementsByClass("table table-bordered table-striped");
                    for (Element element : c) {
                        Elements item = element.getElementsByClass("text-left");
                        String name = item.text(); //搜索的名字
//                        EasyLog.e(name);

                        Elements es = element.getAllElements();
                        for (Element e : es) {
                            EasyLog.e(e.text());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                EasyLog.e("onFailure:" + t.getMessage());
            }
        });
    }
}
