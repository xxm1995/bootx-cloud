package cn.bootx.starter.snowflake;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
*
* @author xxm
* @date 2020/9/26
*/
@ConfigurationProperties(prefix = "bootx.starter.snowflake")
public class SnowFlakeProperties {

    /**
     * 终端ID
     */
    private long workerId;

    /**
     * 数据中心ID
     */
    private long datacenterId;

    public long getWorkerId() {
        return workerId;
    }

    public SnowFlakeProperties setWorkerId(long workerId) {
        this.workerId = workerId;
        return this;
    }

    public long getDatacenterId() {
        return datacenterId;
    }

    public SnowFlakeProperties setDatacenterId(long datacenterId) {
        this.datacenterId = datacenterId;
        return this;
    }
}
