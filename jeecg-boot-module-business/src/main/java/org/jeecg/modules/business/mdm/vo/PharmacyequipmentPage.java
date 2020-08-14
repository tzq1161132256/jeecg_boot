package org.jeecg.modules.business.mdm.vo;

import java.util.List;
import org.jeecg.modules.business.mdm.entity.Pharmacyequipment;
import org.jeecg.modules.business.mdm.entity.Pharmacyequipment4project;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * @Description: 药房pos机pad协议管理
 * @Author: jeecg-boot
 * @Date:   2019-08-22
 * @Version: V1.0
 */
@Data
public class PharmacyequipmentPage {
	
	/**id*/
	private String id;
	/**药房id*/
  	@Excel(name = "药房id", width = 15)
	private String pharmacyid;
	/**药房code*/
  	@Excel(name = "药房code", width = 15)
	private String pharmacycode;
	/**药房名称*/
  	@Excel(name = "药房名称", width = 15)
	private String pharmacyname;
	/**药企对应的药房名称*/
  	@Excel(name = "药企对应的药房名称", width = 15)
	private String pharmacyname4partner;
	/**pos协议状态*/
  	@Excel(name = "pos协议状态", width = 15)
	private String posagreementstatus;
	/**pos状态*/
  	@Excel(name = "pos状态", width = 15)
	private String posstatus;
	/**pad状态*/
  	@Excel(name = "pad状态", width = 15)
	private String padstatus;
	/**记录生成时间*/
  	@Excel(name = "记录生成时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createdate;
	/**记录更新时间*/
  	@Excel(name = "记录更新时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date datelastupdate;
	/**删除标志 Y 删除 N未删除*/
  	@Excel(name = "删除标志 Y 删除 N未删除", width = 15)
	private String deleteflag;
	/**pos协议快递单号*/
  	@Excel(name = "pos协议快递单号", width = 15)
	private String posagreementexpressno;
	/**pos协议邮寄日期*/
  	@Excel(name = "pos协议邮寄日期", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date posagreementpostdate;
	/**pos协议收货人*/
  	@Excel(name = "pos协议收货人", width = 15)
	private String posagreementconsignee;
	/**pos协议收货人电话*/
  	@Excel(name = "pos协议收货人电话", width = 15)
	private String posagreementtelphone;
	/**pos协议项目名称*/
  	@Excel(name = "pos协议项目名称", width = 15)
	private String posagreementproject;
	/**pos协议经办人*/
  	@Excel(name = "pos协议经办人", width = 15)
	private String posagreementoperator;
	/**pos协议备注*/
  	@Excel(name = "pos协议备注", width = 15)
	private String posagreementremark;
	/**pos快递单号*/
  	@Excel(name = "pos快递单号", width = 15)
	private String posexpressno;
	/**pos邮寄日期*/
  	@Excel(name = "pos邮寄日期", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date pospostdate;
	/**pos收货人*/
  	@Excel(name = "pos收货人", width = 15)
	private String posconsignee;
	/**pos收货人电话*/
  	@Excel(name = "pos收货人电话", width = 15)
	private String postelphone;
	/**pos项目名称*/
  	@Excel(name = "pos项目名称", width = 15)
	private String posproject;
	/**pos经办人*/
  	@Excel(name = "pos经办人", width = 15)
	private String posoperator;
	/**pos备注*/
  	@Excel(name = "pos备注", width = 15)
	private String posremark;
	/**pad协议快递单号*/
  	@Excel(name = "pad协议快递单号", width = 15)
	private String padexpressno;
	/**pad协议邮寄日期*/
  	@Excel(name = "pad协议邮寄日期", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date padpostdate;
	/**pad协议收货人*/
  	@Excel(name = "pad协议收货人", width = 15)
	private String padconsignee;
	/**pad协议收货人电话*/
  	@Excel(name = "pad协议收货人电话", width = 15)
	private String padtelphone;
	/**pad协议项目名称*/
  	@Excel(name = "pad协议项目名称", width = 15)
	private String padproject;
	/**pad协议经办人*/
  	@Excel(name = "pad协议经办人", width = 15)
	private String padoperator;
	/**pad协议备注*/
  	@Excel(name = "pad协议备注", width = 15)
	private String padremark;
	/**创建人*/
  	@Excel(name = "创建人", width = 15)
	private String createBy;
	/**创建时间*/
  	@Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**修改人*/
  	@Excel(name = "修改人", width = 15)
	private String updateBy;
	/**修改时间*/
  	@Excel(name = "修改时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	/**删除标识0-正常,1-已删除*/
  	@Excel(name = "删除标识0-正常,1-已删除", width = 15)
	private Integer delFlag;
	
	@ExcelCollection(name="药房pos机pad协议管理明细")
	private List<Pharmacyequipment4project> pharmacyequipment4projectList;
	
}
