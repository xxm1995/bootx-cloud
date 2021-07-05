package cn.bootx.starter.idempotency;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**   
* 幂等性处理器redis
* @author xxm  
* @date 2021/1/3 
*/
@ConfigurationProperties(prefix = "bootx.starter.idempotency.redis")
public class IdemRedisProperties {

    private String host  = "localhost";
    private int port = 6379;
    private int database = 0;
    private String password;

    public int getDatabase() {
        return database;
    }

    public IdemRedisProperties setDatabase(int database) {
        this.database = database;
        return this;
    }

    public String getHost() {
        return host;
    }

    public IdemRedisProperties setHost(String host) {
        this.host = host;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public IdemRedisProperties setPassword(String password) {
        this.password = password;
        return this;
    }

    public int getPort() {
        return port;
    }

    public IdemRedisProperties setPort(int port) {
        this.port = port;
        return this;
    }
}
