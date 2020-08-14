package org.jeecg.modules.business.mdm.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.jeecg.modules.business.mdm.entity.Pharmacyequipment;
import org.jeecg.modules.business.mdm.entity.Pharmacyequipment4project;
import org.jeecg.modules.business.mdm.mapper.Pharmacyequipment4projectMapper;
import org.jeecg.modules.business.mdm.mapper.PharmacyequipmentMapper;
import org.jeecg.modules.business.mdm.service.IPharmacyequipmentService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 药房pos机pad协议管理
 * @Author: jeecg-boot
 * @Date:   2019-08-22
 * @Version: V1.0
 */
@Service
@DS("multi-datasource1")
public class PharmacyequipmentServiceImpl extends ServiceImpl<PharmacyequipmentMapper, Pharmacyequipment> implements IPharmacyequipmentService {

	@Autowired
	private PharmacyequipmentMapper pharmacyequipmentMapper;
	@Autowired
	private Pharmacyequipment4projectMapper pharmacyequipment4projectMapper;
	
	@Override
	@Transactional
	public void saveMain(Pharmacyequipment pharmacyequipment, List<Pharmacyequipment4project> pharmacyequipment4projectList) {
		pharmacyequipmentMapper.insert(pharmacyequipment);
		for(Pharmacyequipment4project entity:pharmacyequipment4projectList) {
			//外键设置
			entity.setPharmacyequipmentid(pharmacyequipment.getId());
			pharmacyequipment4projectMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void updateMain(Pharmacyequipment pharmacyequipment,List<Pharmacyequipment4project> pharmacyequipment4projectList) {
		pharmacyequipmentMapper.updateById(pharmacyequipment);
		
		//1.先删除子表数据
		pharmacyequipment4projectMapper.deleteByMainId(pharmacyequipment.getId());
		
		//2.子表数据重新插入
		for(Pharmacyequipment4project entity:pharmacyequipment4projectList) {
			//外键设置
			entity.setPharmacyequipmentid(pharmacyequipment.getId());
			pharmacyequipment4projectMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		pharmacyequipment4projectMapper.deleteByMainId(id);
		pharmacyequipmentMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			pharmacyequipment4projectMapper.deleteByMainId(id.toString());
			pharmacyequipmentMapper.deleteById(id);
		}
	}
	
}
