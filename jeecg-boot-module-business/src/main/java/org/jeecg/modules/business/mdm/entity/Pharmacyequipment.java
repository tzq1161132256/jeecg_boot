package org.jeecg.modules.business.mdm.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.jeecg.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 药房pos机pad协议管理
 * @Author: jeecg-boot
 * @Date:   2019-08-22
 * @Version: V1.0
 */
@Data
@TableName("pharmacyequipment")
public class Pharmacyequipment implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**id*/
	@TableId(type = IdType.UUID)
	private String id;
	/**药房id*/
	private String pharmacyid;
	/**药房code*/
	private String pharmacycode;
	/**药房名称*/
	private String pharmacyname;
	/**药企对应的药房名称*/
	private String pharmacyname4partner;
	/**pos协议状态*/
	@Dict(dicCode = "pos_protocol_status")
	private String posagreementstatus;
	/**pos状态*/
	@Dict(dicCode = "pos_status")
	private String posstatus;
	/**pad状态*/
	@Dict(dicCode = "pad_status")
	private String padstatus;
	/**记录生成时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createdate;
	/**记录更新时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date datelastupdate;
	/**删除标志 Y 删除 N未删除*/
	private String deleteflag;
	/**pos协议快递单号*/
	private String posagreementexpressno;
	/**pos协议邮寄日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date posagreementpostdate;
	/**pos协议收货人*/
	private String posagreementconsignee;
	/**pos协议收货人电话*/
	private String posagreementtelphone;
	/**pos协议项目名称*/
	private String posagreementproject;
	/**pos协议经办人*/
	private String posagreementoperator;
	/**pos协议备注*/
	private String posagreementremark;
	/**pos快递单号*/
	private String posexpressno;
	/**pos邮寄日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date pospostdate;
	/**pos收货人*/
	private String posconsignee;
	/**pos收货人电话*/
	private String postelphone;
	/**pos项目名称*/
	private String posproject;
	/**pos经办人*/
	private String posoperator;
	/**pos备注*/
	private String posremark;
	/**pad协议快递单号*/
	private String padexpressno;
	/**pad协议邮寄日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date padpostdate;
	/**pad协议收货人*/
	private String padconsignee;
	/**pad协议收货人电话*/
	private String padtelphone;
	/**pad协议项目名称*/
	private String padproject;
	/**pad协议经办人*/
	private String padoperator;
	/**pad协议备注*/
	private String padremark;
	/**创建人*/
	private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**修改人*/
	private String updateBy;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	/**删除标识0-正常,1-已删除*/
	private Integer delFlag;

	@TableField(exist = false)
	private Integer countPosapply;

	@TableField(exist = false)
	private Integer countPadapply;

	@TableField(exist = false)
	private Integer countProject;
}
