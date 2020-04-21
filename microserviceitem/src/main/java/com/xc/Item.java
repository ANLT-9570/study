package com.xc;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Item {

    private Long id;

    private String title;

    private String pic;

    private String desc;

    private Long price;

    public Item() {
    }

    public Item(long id, String title, String pic, String desc, Long price) {
        this.id = id;
        this.title = title;
        this.pic = pic;
        this.desc = desc;
        this.price = price;
    }
}