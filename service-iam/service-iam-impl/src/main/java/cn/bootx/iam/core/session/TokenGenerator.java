package cn.bootx.iam.core.session;

import cn.hutool.core.util.RandomUtil;
import org.springframework.stereotype.Component;

/**
 * token 生成器
 *
 * @author network
 */
@Component
public class TokenGenerator {

    private static final int LENGTH = 32;

    /**
     * 随机生成 token字母数字组合
     */
    public String gen() {
        return "Bearer "+RandomUtil.randomString(LENGTH);
    }
}
