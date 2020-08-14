package org.jeecg.modules.business.mdm.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.jeecg.modules.business.mdm.entity.Pharmacyequipment4project;
import org.jeecg.modules.business.mdm.mapper.Pharmacyequipment4projectMapper;
import org.jeecg.modules.business.mdm.service.IPharmacyequipment4projectService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 药房pos机pad协议管理明细
 * @Author: jeecg-boot
 * @Date:   2019-08-22
 * @Version: V1.0
 */
@Service
@DS("multi-datasource1")
public class Pharmacyequipment4projectServiceImpl extends ServiceImpl<Pharmacyequipment4projectMapper, Pharmacyequipment4project> implements IPharmacyequipment4projectService {
	
	@Autowired
	private Pharmacyequipment4projectMapper pharmacyequipment4projectMapper;
	
	@Override
	public List<Pharmacyequipment4project> selectByMainId(String mainId) {
		return pharmacyequipment4projectMapper.selectByMainId(mainId);
	}
}
