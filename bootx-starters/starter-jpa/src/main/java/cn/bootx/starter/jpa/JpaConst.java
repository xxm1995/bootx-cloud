package cn.bootx.starter.jpa;

/**   
* jpa常量
* @author xxm  
* @date 2020/9/28 
*/
public interface JpaConst {
    /** 雪花ID生成策略 */
  String SNOWFLAKE_ID_STRATEGY = "SnowflakeId";
  String SNOWFLAKE_ID_STRATEGY_CLASS = "cn.bootx.starter.jpa.SnowflakeIdStrategy";
}
