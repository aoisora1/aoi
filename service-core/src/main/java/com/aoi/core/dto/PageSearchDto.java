package com.aoi.core.dto;

/**
 * @ClassName PageSearchDto
 * @Description 分页查询dto
 * @Author 86184
 * @Date 2024/10/3 12:58
 */
public class PageSearchDto {
    private int page;
    private int size;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
