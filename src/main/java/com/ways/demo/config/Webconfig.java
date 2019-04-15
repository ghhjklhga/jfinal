package com.ways.demo.config;

import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import com.ways.demo.controller.IndexController;
import com.ways.demo.controller.NewsController;
import com.ways.demo.dao.News;

/**
 * @Author: HinsWu
 * @Date: 2019/4/15 15:05
 */
public class Webconfig extends JFinalConfig {

    @Override
    public void configConstant(Constants constants) {
        //设置编码格式
        constants.setEncoding("UTF-8");
        //设置为开发模式（如果为fals，jfinal会缓存页面，导致开发时修改页面后不能立即呈现）
        constants.setDevMode(true);
        //jfinal支持很多类型的页面，这里设置为jsp，FreeMarker也支持）
        constants.setViewType(ViewType.JSP);
        constants.setBaseViewPath("/WEB-INF/webpage");
    }

    @Override
    public void configRoute(Routes routes) {
        //统一设置映射访问路径  类似于spring mvc的@RequestMapping
        routes.add("/", IndexController.class);
        routes.add("/news", NewsController.class);
    }

    @Override
    public void configPlugin(Plugins plugins) {
        PropKit.use("jdbc.properties");
        final String URL =PropKit.get("jdbcUrl");
        final String USERNAME = PropKit.get("user");
        final String PASSWORD =PropKit.get("password");
        final Integer INITIALSIZE = PropKit.getInt("initialSize");
        final Integer MIDIDLE = PropKit.getInt("minIdle");
        final Integer MAXACTIVEE = PropKit.getInt("maxActivee");

        DruidPlugin druidPlugin = new DruidPlugin(URL,USERNAME,PASSWORD);
        druidPlugin.set(INITIALSIZE,MIDIDLE,MAXACTIVEE);
        druidPlugin.setFilters("stat,wall");
        plugins.add(druidPlugin);

        //实体映射
        ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(druidPlugin);
        activeRecordPlugin.addMapping("news","id", News.class);
        plugins.add(activeRecordPlugin);

    }

    @Override
    public void configInterceptor(Interceptors interceptors) {

    }

    @Override
    public void configHandler(Handlers handlers) {

    }

    public static void main(String[] args) {
        // eclipse 下的启动方式
//        JFinal.start("src/main/java/config", 8080, "/");
        JFinal.start("src/main/java/webapp", 8080, "/",5);
        // IDEA 下的启动方式
        // JFinal.start("src/main/webapp", 80, "/");
    }

}
