package cn.bootx.starter.spring;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
* @author xxm
* @date 2020/4/9 13:50
*/
@ConfigurationProperties(prefix = "bootx.starter.spring")
@Data
@Accessors(chain = true)
public class SpringProperties {

    private Executor executor = new Executor();
	/**
	* @author xxm
	* @date 2021/6/11
	*/
	@Data
	@Accessors(chain = true)
	public static class Executor {
        private boolean enabled = true;

        /**
         * 线程池维护线程的最少数量
         */
        private int corePoolSize = 5;

        /**
         * 线程池维护线程的最大数量
         */
        private int maxPoolSize = 10;

        /**
         * 缓存队列
         */
        private int queueCapacity = 20;

        /**
         * 保持活动秒数
         */
        private int keepAliveSeconds = 60;
    }
}
