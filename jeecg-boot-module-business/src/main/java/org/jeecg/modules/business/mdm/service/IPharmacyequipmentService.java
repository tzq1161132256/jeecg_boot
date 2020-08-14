package org.jeecg.modules.business.mdm.service;

import org.jeecg.modules.business.mdm.entity.Pharmacyequipment4project;
import org.jeecg.modules.business.mdm.entity.Pharmacyequipment;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 药房pos机pad协议管理
 * @Author: jeecg-boot
 * @Date:   2019-08-22
 * @Version: V1.0
 */
public interface IPharmacyequipmentService extends IService<Pharmacyequipment> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(Pharmacyequipment pharmacyequipment, List<Pharmacyequipment4project> pharmacyequipment4projectList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(Pharmacyequipment pharmacyequipment, List<Pharmacyequipment4project> pharmacyequipment4projectList);
	
	/**
	 * 删除一对多
	 */
	public void delMain(String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain(Collection<? extends Serializable> idList);
	
}
