package cn.bootx.starter.headerholder;

/**
 * 请求头获取工具类
 * @author xxm
 * @date 2020/4/14 15:23
 */
public interface HeaderHolder {

    String findAccessToken();

    String getAccessToken();

    String findJwtToken();

    String getJwtToken();

    String getOpToken();

    Long findTid();

    Long getTid();

    Long findOperatorId();

    Long getOperatorId();

    Long findChannelId();

    Long getChannelId();

    String getHeader(String name);
}

