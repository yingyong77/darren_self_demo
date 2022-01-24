package com.darren.demo.boot.start;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@Slf4j
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        这里是tomcat外部配置启动
        return application.sources(Object.class);
    }

    /**
     * @param servletContext Servlet容器上下文
     * @return void
     * @Description 启动设置
     * @author XuYingan
     * @date 3:52 PM 11/16/2020
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        onPreStartup(servletContext);
        super.onStartup(servletContext);
    }

    /**
     * @param servletContext Servlet容器上下文
     * @return void
     * @Description 前置启动设置
     * @author XuYingan
     * @date 3:52 PM 11/16/2020
     */
    private void onPreStartup(ServletContext servletContext) {
        log.info("[ServletInitializer] Log4j2: Initializing...");
        processLog4j2(servletContext);
    }

    /**
     * @param servletContext Servlet容器上下文
     * @return void
     * @Description 设置Log4j2:可通过Bean注入,但是和SpringServlet都是最高优先级没法区分
     * @author XuYingan
     * @date 3:56 PM 11/16/2020
     */
    private void processLog4j2(ServletContext servletContext) {
//        servletContext.addFilter("log4jServletFilter", Log4jServletFilter.class);
//        servletContext.setInitParameter("log4j.stop.timeout", "5");
//        servletContext.addListener(Log4jServletContextListener.class);
    }


}
