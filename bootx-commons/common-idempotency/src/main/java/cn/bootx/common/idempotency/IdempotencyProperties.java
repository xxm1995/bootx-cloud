package cn.bootx.common.idempotency;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**   
* 幂等性参数
* @author xxm  
* @date 2021/1/2 
*/
@ConfigurationProperties(prefix = "bootx.starter.idempotency")
public class IdempotencyProperties {

    /** 作为客户端时是否验证幂等性 */
    private boolean enableConsumer = true;
    /** 作为服务端时是否验证幂等性 */
    private boolean enableProvider = true;
    /** 超时时间, 重试 */
    private int timeoutOfOpToken = 3 * 10 * 1000;
    /** 是否启用独立redis */
    private boolean aloneRedis = false;

    public int getTimeoutOfOpToken() {
        return timeoutOfOpToken;
    }

    public IdempotencyProperties setTimeoutOfOpToken(int timeoutOfOpToken) {
        this.timeoutOfOpToken = timeoutOfOpToken;
        return this;
    }

    public boolean isEnableConsumer() {
        return enableConsumer;
    }

    public IdempotencyProperties setEnableConsumer(boolean enableConsumer) {
        this.enableConsumer = enableConsumer;
        return this;
    }

    public boolean isEnableProvider() {
        return enableProvider;
    }

    public IdempotencyProperties setEnableProvider(boolean enableProvider) {
        this.enableProvider = enableProvider;
        return this;
    }

    public boolean isAloneRedis() {
        return aloneRedis;
    }

    public IdempotencyProperties setAloneRedis(boolean aloneRedis) {
        this.aloneRedis = aloneRedis;
        return this;
    }
}
