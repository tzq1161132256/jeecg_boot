package org.jeecg.modules.business.trade.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 疗效保险
 * @Author: jeecg-boot
 * @Date:   2019-08-21
 * @Version: V1.0
 */
@Data
@TableName("insurance")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="insurance对象", description="疗效保险")
public class Insurance {
    
	/**项目名称*/
	@Excel(name = "项目名称", width = 15)
    @ApiModelProperty(value = "项目名称")
	private String projectname;
	/**订单号*/
	@Excel(name = "订单号", width = 15)
    @ApiModelProperty(value = "订单号")
	private String orderno;
	/**保单编号*/
	@Excel(name = "保单编号", width = 15)
    @ApiModelProperty(value = "保单编号")
	private String policyno;
	/**申请人姓名(投保人) 敏感加密*/
	@Excel(name = "申请人姓名(投保人) 敏感加密", width = 15)
    @ApiModelProperty(value = "申请人姓名(投保人) 敏感加密")
	private String policyholdername;
	/**申请人手机号码(投保人) */
	@Excel(name = "申请人手机号码(投保人) ", width = 15)
    @ApiModelProperty(value = "申请人手机号码(投保人) ")
	private String policyholdermobile;
	/**申请人身份证号码(投保人)*/
	@Excel(name = "申请人身份证号码(投保人)", width = 15)
    @ApiModelProperty(value = "申请人身份证号码(投保人)")
	private String policyholderidcardno;
	/**份数*/
	@Excel(name = "份数", width = 15)
    @ApiModelProperty(value = "份数")
	private String copies;
	/**区域*/
	@Excel(name = "区域", width = 15)
    @ApiModelProperty(value = "区域")
	private String areaname;
	/**省份*/
	@Excel(name = "省份", width = 15)
    @ApiModelProperty(value = "省份")
	private String provincename;
	/**城市*/
	@Excel(name = "城市", width = 15)
    @ApiModelProperty(value = "城市")
	private String cityname;
	/**区县*/
	@Excel(name = "区县", width = 15)
    @ApiModelProperty(value = "区县")
	private String countyname;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String status;
	/**申请日期*/
	@Excel(name = "申请日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "申请日期")
	private Date applicationdate;
	/**审核日期*/
	@Excel(name = "审核日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "审核日期")
	private Date auditdate;
	/**被保险人姓名*/
	@Excel(name = "被保险人姓名", width = 15)
    @ApiModelProperty(value = "被保险人姓名")
	private String insuredname;
	/**被保险人手机号码*/
	@Excel(name = "被保险人手机号码", width = 15)
    @ApiModelProperty(value = "被保险人手机号码")
	private String insuredmobile;
	/**被保险人身份证号码*/
	@Excel(name = "被保险人身份证号码", width = 15)
    @ApiModelProperty(value = "被保险人身份证号码")
	private String insuredidcardno;
	/**受益人姓名*/
	@Excel(name = "受益人姓名", width = 15)
    @ApiModelProperty(value = "受益人姓名")
	private String beneficiaryname;
	/**受益人手机号码*/
	@Excel(name = "受益人手机号码", width = 15)
    @ApiModelProperty(value = "受益人手机号码")
	private String beneficiarymobile;
	/**受益人身份证号码*/
	@Excel(name = "受益人身份证号码", width = 15)
    @ApiModelProperty(value = "受益人身份证号码")
	private String beneficiaryidcardno;
	/**是否金域扫码*/
	@Excel(name = "是否金域扫码", width = 15)
    @ApiModelProperty(value = "是否金域扫码")
	private String scanflag;
	/**备注1*/
	@Excel(name = "备注1", width = 15)
    @ApiModelProperty(value = "备注1")
	private String remark1;
	/**备注2*/
	@Excel(name = "备注2", width = 15)
    @ApiModelProperty(value = "备注2")
	private String remark2;
	/**最后更新时间*/
	@Excel(name = "最后更新时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最后更新时间")
	private Date datelastupdate;
	/**创建日期*/
	@Excel(name = "创建日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
	private Date createdate;
	/**保险单主键*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "保险单主键")
	private String id;
	/**审核成功时间*/
	@Excel(name = "审核成功时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "审核成功时间")
	private Date auditfaildate;
	/**审核失败时间*/
	@Excel(name = "审核失败时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "审核失败时间")
	private Date auditsuccessdate;
	/**保单生成日期*/
	@Excel(name = "保单生成日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "保单生成日期")
	private Date policydate;
	/**购药日期*/
	@Excel(name = "购药日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "购药日期")
	private Date purchasedate;
	/**药房名称*/
	@Excel(name = "药房名称", width = 15)
    @ApiModelProperty(value = "药房名称")
	private String drugstorename;
	/**医院名称*/
	@Excel(name = "医院名称", width = 15)
    @ApiModelProperty(value = "医院名称")
	private String hospitalname;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String updateBy;
	/**修改时间*/
	@Excel(name = "修改时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
	private Date updateTime;
	/**删除标识0-正常,1-已删除*/
	@Excel(name = "删除标识0-正常,1-已删除", width = 15)
    @ApiModelProperty(value = "删除标识0-正常,1-已删除")
	private Integer delFlag;
}
