package com.ways.demo.controller;

import com.jfinal.core.Controller;
import com.ways.demo.serivce.NewsService;

/**
 * @Author: HinsWu
 * @Date: 2019/4/15 14:59
 */
public class NewsController extends Controller {
    private NewsService newsService = new NewsService();

    public void addNews(){
        System.out.println("进入。。。");
        newsService.addNews("测试值");
        System.out.println("执行完成！");
        renderJsp("index.html");
    }
}
