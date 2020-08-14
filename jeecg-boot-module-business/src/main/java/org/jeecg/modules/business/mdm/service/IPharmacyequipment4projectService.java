package org.jeecg.modules.business.mdm.service;

import org.jeecg.modules.business.mdm.entity.Pharmacyequipment4project;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 药房pos机pad协议管理明细
 * @Author: jeecg-boot
 * @Date:   2019-08-22
 * @Version: V1.0
 */
public interface IPharmacyequipment4projectService extends IService<Pharmacyequipment4project> {

	public List<Pharmacyequipment4project> selectByMainId(String mainId);
}
