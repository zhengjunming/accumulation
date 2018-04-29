package com.mins.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author <a href="http://minsming.com">小铭</a>
 * Date: 2018/4/29
 * No struggle, talent how to match the willfulness.
 * Description: 文件存储实体类
 */
@Data
@ConfigurationProperties("storage")
public class StorageProperties {

    private String location = "resources";
}
