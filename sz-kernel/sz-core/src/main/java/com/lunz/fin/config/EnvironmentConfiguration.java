package com.lunz.fin.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Liuruixia
 * @Description: 处理环境初始化配置
 * @date 2019/07/17
 */
@Slf4j
@Component
public class EnvironmentConfiguration {
    @Autowired
    private Environment env;
    private static final String IS_DEV = "dev";

    public boolean isDev = false;

    @PostConstruct
    private void init() {
        try {
            if (env != null && (IS_DEV.equals(env.getActiveProfiles()[0]))) {
                isDev = true;
            }
        } catch (Exception ex) {
            log.error("获取环境变量失败：");
            log.error(ex.getMessage());
        }
    }
}
