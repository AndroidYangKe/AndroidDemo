package com.android.yangke.java.m.utils;

/**
 * author : yangke on 2021/3/20
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 管理不同页面间参数传递使用的key；注意：必须在key前面加上业务，避免重名；例search_key（用户标识搜索
 * 的关键字），必须在前面加上search_result（搜索结果页），最终产物：search_result_search_key；
 */
public class PageKey {
    public static final String SEARCH = "search_result_search_key"; //搜索结果页，搜索关键字
    public static final String APK_URL = "key_apk_url";             //分享，apk url
    public static final String USED_COUNT = "used_count";           //用户使用免费次数
    public static final String ALL_COUNT = "all_count";             //可用次数（包含分享获取的次数）
    public static final String SELECT_POS = "dialog_select_pos";    //列表默认选中位置

    public static class RequestCode {
        public static final int WRITE_SD = 1000;          //请求sd卡权限
    }

}
