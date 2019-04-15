package com.ways.demo.controller;

import com.jfinal.core.Controller;

/**
 * @Author: HinsWu
 * @Date: 2019/4/15 15:03
 */
public class IndexController extends Controller {
    public void index(){
        renderText("Hello World！");
    }
}
