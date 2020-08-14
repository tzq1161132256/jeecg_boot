package org.jeecg.modules.business.mdm.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;

/**
 * @Description: 药房pos机pad协议管理明细
 * @Author: jeecg-boot
 * @Date:   2019-08-22
 * @Version: V1.0
 */
@Data
@TableName("pharmacyequipment4project")
public class Pharmacyequipment4project implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	@TableId(type = IdType.UUID)
	private String id;
	/**药房id*/
	private String pharmacyequipmentid;
	/**项目名称*/
    @Excel(name = "项目名称", width = 15)
	private String projectname;
	/**协议书状态*/
    @Excel(name = "协议书状态", width = 15)
	private String agreementstatus;
	/**记录生成时间*/
	@Excel(name = "记录生成时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createdate;
	/**记录最后更新时间*/
	@Excel(name = "记录最后更新时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date datelastupdate;
	/**删除标志 Y 删除 N未删除*/
    @Excel(name = "删除标志 Y 删除 N未删除", width = 15)
	private String deleteflag;
	/**协议快递单号*/
    @Excel(name = "协议快递单号", width = 15)
	private String expressno;
	/**协议邮寄日期*/
	@Excel(name = "协议邮寄日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date postdate;
	/**协议收货人*/
    @Excel(name = "协议收货人", width = 15)
	private String consignee;
	/**协议收货人电话*/
    @Excel(name = "协议收货人电话", width = 15)
	private String telphone;
	/**协议经办人*/
    @Excel(name = "协议经办人", width = 15)
	private String operator;
	/**协议备注*/
    @Excel(name = "协议备注", width = 15)
	private String remark;
	/**状态变更时间*/
	@Excel(name = "状态变更时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date statusmodifydate;
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
}
