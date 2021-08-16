package cn.bootx.engine.shop.handler.mybatis;
//
//import cn.bootx.common.core.code.CommonCode;
//import cn.bootx.starter.headerholder.HeaderHolder;
//import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
//import lombok.AllArgsConstructor;
//import org.apache.ibatis.reflection.MetaObject;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//
///**
//* mp字段填充
//* @author xxm
//* @date 2020/4/8 11:11
//*/
//@Component
//@AllArgsConstructor
//public class MpMetaObjectHandler implements MetaObjectHandler  {
//
//    private final HeaderHolder headerHolder;
//
//    @Override
//    public void insertFill(MetaObject metaObject) {
//        LocalDateTime nowDate = LocalDateTime.now();
//        this.strictInsertFill(metaObject, CommonCode.CREATOR, Long.class, -1L);
//        this.strictInsertFill(metaObject, CommonCode.CREATE_TIME, LocalDateTime.class, nowDate);
//        this.strictInsertFill(metaObject, CommonCode.LAST_MODIFIER, Long.class, -1L);
//        this.strictInsertFill(metaObject, CommonCode.LAST_MODIFIED_TIME, LocalDateTime.class, nowDate);
//        this.strictInsertFill(metaObject, CommonCode.VERSION, Integer.class, 1);
//    }
//
//
//    @Override
//    public void updateFill(MetaObject metaObject) {
//        LocalDateTime nowDate = LocalDateTime.now();
//
//        this.strictUpdateFill(metaObject, CommonCode.LAST_MODIFIER, Long.class, -1L);
//        this.strictUpdateFill(metaObject, CommonCode.LAST_MODIFIED_TIME, LocalDateTime.class, nowDate);
//    }
//}
