package com.yangke.app.java.m.vo;

/**
 * author : yangke on 2021/3/20
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 首页搜索产品结果
 */
public class SearchResult extends HttpResult<String> {
    public String searchName; //搜索出来的文件名
    public String createDate; //创建日期
    public String fileSize;   //文件大小
    public String href;       //文件链接
    public String p;          //预留参数

    public SearchResult(String searchName, String createDate, String fileSize, String href, String p) {
        this.searchName = searchName;
        this.createDate = createDate;
        this.fileSize = fileSize;
        this.href = href;
        this.p = p;
    }
}
