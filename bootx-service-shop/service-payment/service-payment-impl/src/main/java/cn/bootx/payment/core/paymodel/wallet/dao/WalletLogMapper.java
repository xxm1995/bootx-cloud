package cn.bootx.payment.core.paymodel.wallet.dao;


import cn.bootx.payment.core.paymodel.wallet.entity.WalletLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 钱包日志
 * @author xxm
 * @date 2020/12/8
 */
@Mapper
public interface WalletLogMapper extends BaseMapper<WalletLog> {
}
