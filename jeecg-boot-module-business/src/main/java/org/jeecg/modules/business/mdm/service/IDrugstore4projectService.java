package org.jeecg.modules.business.mdm.service;

import org.jeecg.modules.business.mdm.entity.Drugstore4project;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @Description: 项目对应的药店
 * @Author: jeecg-boot
 * @Date:   2019-08-20
 * @Version: V1.0
 */
public interface IDrugstore4projectService extends IService<Drugstore4project> {
public void processDrugstore4project(Drugstore4project drugstore4project) throws Exception;
    List<Map> getNoSalesDrugstore(Map<String, Object> var1);
}
