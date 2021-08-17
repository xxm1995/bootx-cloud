package cn.bootx.common.lock.factory;


import cn.bootx.common.lock.enums.LockType;
import cn.bootx.common.lock.service.LockService;
import cn.bootx.common.lock.service.impl.*;
import cn.hutool.extra.spring.SpringUtil;

import java.util.EnumMap;

/**
 * 锁处理工厂类
 *
 * @author xianzhi.chen@hand-china.com 2019年1月14日下午7:04:13
 */
public class LockServiceFactory {

    private static final EnumMap<LockType, Class<?>> serviceMap = new EnumMap<>(LockType.class);

    static {
        serviceMap.put(LockType.REENTRANT, ReentrantLockServiceImpl.class);
        serviceMap.put(LockType.FAIR, FairLockServiceImpl.class);
        serviceMap.put(LockType.READ, ReadLockServiceImpl.class);
        serviceMap.put(LockType.WRITE, WriteLockServiceImpl.class);
        serviceMap.put(LockType.RED, RedLockServiceImpl.class);
    }

    /**
     * 根据类型进行不同的锁实现
     *
     * @param lockType 锁类
     * @return LockService
     */
    public LockService getLock(LockType lockType) {
        return (LockService) SpringUtil.getBean(serviceMap.get(lockType));
    }
}
