package cn.bootx.common.mybatisplus.handler;

import cn.bootx.common.snowflake.SnowFlakeId;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import lombok.RequiredArgsConstructor;

/**
* 雪花id生成器
* @author xxm
* @date 2021/8/18
*/
@RequiredArgsConstructor
public class SnowflakeIdentifierGenerator implements IdentifierGenerator {
    private final SnowFlakeId snowFlakeId;

    @Override
    public Number nextId(Object entity) {
        return snowFlakeId.nextId();
    }
}
