package org.jeecg.modules.business.mdm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.business.mdm.entity.Drugstore4project;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 项目对应的药店
 * @Author: jeecg-boot
 * @Date:   2019-08-20
 * @Version: V1.0
 */
public interface Drugstore4projectMapper extends BaseMapper<Drugstore4project> {
    List<Map> getNoSalesDrugstore(@Param("cm") Map<String, Object> var1);
}
