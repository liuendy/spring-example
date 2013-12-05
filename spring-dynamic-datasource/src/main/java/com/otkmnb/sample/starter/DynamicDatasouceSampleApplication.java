package com.otkmnb.sample.starter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

import com.otkmnb.sample.config.DynamicDatasourceSampleConfig;

/**
 * <p>
 * このクラスは、動的にデータソースを変更するサンプルアプリケーションを起動します。
 * </p>
 * @author OHTAKE Manabu(manabu2783 at hotmail.com)
 */
public class DynamicDatasouceSampleApplication {
    
    private static final Logger L = LoggerFactory.getLogger(DynamicDatasouceSampleApplication.class);

    /**
     * アプリケーションを起動するメインクラスです。
     * @param args
     */
    public static void main(String[] args) {
        L.debug("アプリケーションを起動します。");
        SpringApplication.run(DynamicDatasourceSampleConfig.class, args);
    }
    
}
