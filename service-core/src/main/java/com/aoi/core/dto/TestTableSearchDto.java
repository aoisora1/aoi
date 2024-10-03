package com.aoi.core.dto;

/**
 * @ClassName TestTableSearchDto
 * @Description TODO
 * @Author 86184
 * @Date 2024/10/3 12:59
 */
public class TestTableSearchDto extends PageSearchDto {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
