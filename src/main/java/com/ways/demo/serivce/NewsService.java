package com.ways.demo.serivce;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/**
 * @Author: HinsWu
 * @Date: 2019/4/15 14:55
 */
public class NewsService {
    public void addNews(String title){
        Record record = new Record().set("title", title);
        Db.save("news", record);
    }
}
