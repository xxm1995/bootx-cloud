package cn.bootx.salescenter.core.match.convert;

import cn.bootx.salescenter.core.match.entity.MatchRuleConfig;
import cn.bootx.salescenter.dto.match.MatchRuleDto;
import cn.bootx.salescenter.param.strategy.MatchRuleParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* 转换
* @author xxm
* @date 2021/5/19
*/
@Mapper
public interface MatchRuleConvert {
    MatchRuleConvert INSTANCE = Mappers.getMapper(MatchRuleConvert.class);

    @Mappings({})
    MatchRuleConfig convert(MatchRuleDto in);

    @Mappings({})
    MatchRuleConfig convert(MatchRuleParam in);

    @Mappings({})
    MatchRuleDto convert(MatchRuleConfig in);
}