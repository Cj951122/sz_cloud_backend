package com.lunz.fin.config.web;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping()
@RestController
@Api(tags = "0.主页")
public class HomeController {

    @Value("${server.port}")
    private String port;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${spring.profiles.active}")
    private String env;


    @GetMapping("/")
    public String index(){
        return "<style type=\"text/css\">*{ padding: 0; margin: 0; } div{ padding: 4px 48px;} a{color:#2E5CD5;cursor:" +
                "pointer;text-decoration: none} a:hover{text-decoration:underline; } body{ background: #fff; font-family:" +
                "\"Century Gothic\",\"Microsoft yahei\"; color: #333;font-size:18px;} h1{ font-size: 100px; font-weight: normal;" +
                "margin-bottom: 12px; } p{ line-height: 1.6em; font-size: 42px }</style><div style=\"padding: 24px 48px;\">" +
                "<p> 银保贷SAAS " +
                "<br/><span style=\"font-size:30px\">端口："+port+"</span>" +
                "<br/><span style=\"font-size:30px\">服务名称："+applicationName+"</span>" +
                "<br/><span style=\"font-size:30px\">服务描述：配置服务模块</span>" +
                "<br/><span style=\"font-size:40px\">当前环境："+env+"</span>" +
                "</p></div> ";
    }

}
