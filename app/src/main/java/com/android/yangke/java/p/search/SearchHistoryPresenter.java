package com.android.yangke.java.p.search;

import android.content.Context;
import android.text.TextUtils;

import com.android.yangke.java.m.utils.SPUtil;

/**
 * author : yangke on 2021/3/25
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 首页搜索历史; 后期如需实现网络再继承BasePresenter
 */
public class SearchHistoryPresenter {
    private final String SEARCH_HISTORY = "home_search_history";

    /**
     * 保存搜索记录
     */
    public void save(String keyword, Context ctx) {
        String oldText = SPUtil.getString(ctx, SEARCH_HISTORY);
        // 利用StringBuilder.append新增内容，逗号便于读取内容时用逗号拆分开
        StringBuilder builder = new StringBuilder(oldText);
        builder.append(keyword + ",");

        // 判断搜索内容是否已经存在于历史文件，已存在则不重复添加
        if (!oldText.contains(keyword + ",")) {
            SPUtil.putString(ctx, SEARCH_HISTORY, builder.toString());
        }
    }

    /**
     * @param maxSize 最大保存数量
     */
    public String[] getHistoryList(Context ctx, int maxSize) {
        String history = SPUtil.getString(ctx, SEARCH_HISTORY);
        String[] historyArray = history.split(",");
        if (historyArray.length <= 0 || (historyArray.length > 0 && TextUtils.isEmpty(historyArray[0]))) {
            return null;
        }

        if(historyArray.length > 0) {
            historyArray = aryReverse(historyArray);
        }
        if (historyArray.length > maxSize) {
            String[] newArrays = new String[maxSize];
            System.arraycopy(historyArray, 0, newArrays, 0, maxSize);
        }
        return historyArray;
    }

    /**
     * 数组反转
     */
    private String[] aryReverse(String[] arr) {
        int n = arr.length;
        String[] b = new String[n];
        int j = n;
        for (int i = 0; i < n; i++) {
            b[j - 1] = arr[i];
            j = j - 1;
        }
        return b;
    }

    /**
     * 清除搜索记录
     */
    public void cleanHistory(Context ctx) {
        SPUtil.clearPreference(ctx, SEARCH_HISTORY, SEARCH_HISTORY);
    }
}
