package org.jeecg.modules.business.mdm.mapper;

import java.util.List;
import org.jeecg.modules.business.mdm.entity.Pharmacyequipment4project;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 药房pos机pad协议管理明细
 * @Author: jeecg-boot
 * @Date:   2019-08-22
 * @Version: V1.0
 */
public interface Pharmacyequipment4projectMapper extends BaseMapper<Pharmacyequipment4project> {

	public boolean deleteByMainId(String mainId);
    
	public List<Pharmacyequipment4project> selectByMainId(String mainId);
}
