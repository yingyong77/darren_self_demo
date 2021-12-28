package com.darren.demo.utils.spring.context;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

/**
 * @author : darren
 * @date : 2021/12/28
 */
public class ContextLoader {

    public static ServletContext servletContext;
    public static WebApplicationContext webApplicationContext;

    /**
     * 从 ServerContextEvent 中初始化自定义 Context 对象
     */
    private void initContext(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        servletContext = context;
        webApplicationContext = WebApplicationContextUtils
                .getWebApplicationContext(context);
    }


    /**
     * 从spring配置文件中得到Bean的实例
     *
     * @param beanName 名称
     * @return Bean 实例
     */
    public static Object getSpringBean(String beanName) {
        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        return wac.getBean(beanName);
    }

    /**
     * 从spring配置文件中得到Bean的实例
     *
     * @param clz bean类
     * @return Bean 实例
     */
    public static <T> T getSpringBean(Class<T> clz) {
        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        if (wac != null) {
            return wac.getBean(clz);
        }

        return null;
    }


    /**
     * 从spring配置文件中得到Bean的实例
     *
     * @param clz bean类
     * @return Bean 实例
     */
    public static <T> T getSpringBean(String name, Class<T> clz) {
        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        if (wac != null) {
            return wac.getBean(name, clz);
        }

        return null;
    }

}
