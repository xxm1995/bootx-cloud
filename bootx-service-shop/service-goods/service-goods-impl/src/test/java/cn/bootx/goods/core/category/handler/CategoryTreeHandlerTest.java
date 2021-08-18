package cn.bootx.goods.core.category.handler;

import cn.bootx.goods.dto.category.CategoryDto;
import cn.bootx.goods.dto.category.CategoryTreeNode;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
class CategoryTreeHandlerTest {

    @Test
    public void build(){
        List<CategoryDto> dtos = new ArrayList<>();
        dtos.add(new CategoryDto().setId(1L).setPid(0L).setName("路线").setOrdinal(1));
        dtos.add(new CategoryDto().setId(2L).setPid(0L).setName("地点").setOrdinal(2));
        dtos.add(new CategoryDto().setId(3L).setPid(1L).setName("高铁").setOrdinal(1));
        dtos.add(new CategoryDto().setId(4L).setPid(1L).setName("动车").setOrdinal(2));
        dtos.add(new CategoryDto().setId(5L).setPid(2L).setName("午门").setOrdinal(1));

        List<CategoryTreeNode> treeNodes = CategoryTreeHandler.build(dtos);
        log.info(JSONUtil.toJsonStr(treeNodes));
    }
}