package com.yangke.app.java.m.utils;

/**
 * author : yangke on 2021/3/20
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 管理不同页面间参数传递使用的key；注意：必须在key前面加上业务，避免重名；例search_key（用户标识搜索
 * 的关键字），必须在前面加上search_result（搜索结果页），最终产物：search_result_search_key；
 */
public class PageKey {
    public static final String SEARCH_KEY = "search_result_search_key"; //搜索结果页，搜索关键字

}
