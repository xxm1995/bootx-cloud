package cn.bootx.common.snowflake;


import cn.hutool.core.util.IdUtil;

/**
* 雪花id
* @author xxm
* @date 2020/9/26
*/
public class SnowFlakeId {

    public SnowFlakeId(long workerId, long datacenterId) {
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    /**
     * 终端ID
     */
    private final long workerId;

    /**
     * 数据中心ID
     */
    private final long datacenterId;

    public long nextId(){
        return IdUtil.getSnowflake(workerId,datacenterId).nextId();
    }

    public String nextIdStr(){
        return IdUtil.getSnowflake(workerId,datacenterId).nextIdStr();
    }
}
