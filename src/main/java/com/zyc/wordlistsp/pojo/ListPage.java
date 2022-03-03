package com.zyc.wordlistsp.pojo;

import lombok.AllArgsConstructor;

import java.util.List;
@AllArgsConstructor
public class ListPage<T> {
    //todo 为分页导航增加start和end信息

    int curPage;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    int pageSize;
    List<T> pageContent;
    int limitation;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getPageContent() {
        return pageContent;
    }

    public void setPageContent(List<T> pageContent) {
        this.pageContent = pageContent;
    }

    public int getLimitation() {
        return limitation;
    }

    public void setLimitation(int limitation) {
        this.limitation = limitation;
    }

    @Override
    public String toString() {
        return "ListPage{" +
            "curPage=" + curPage +
            ", pageSize=" + pageSize +
            ", pageContent=" + pageContent +
            '}';
    }
}
