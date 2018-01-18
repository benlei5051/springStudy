package org.andy.beans.configuration.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: andy
 * @Date: 2017/10/27 13:13
 * @Description:
 */
@Component
public class TrainingProperties {

    @Value("${file_path:/home/default_PATH}")
    private String filePath;

    @Value("${random_number}")
    private Integer number;

    @Value("${servers.port:888}")
    private String serverPort;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TrainingProperties{");
        sb.append("filePath='").append(filePath).append('\'');
        sb.append(", number=").append(number);
        sb.append(", serverPort='").append(serverPort).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
