package org.andy.file.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: andy
 * @Date: 2017/9/21 10:09
 * @Description:
 */
@ConfigurationProperties("storage")
public class StorageProperties {
    /**
     * the file location
     */
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
