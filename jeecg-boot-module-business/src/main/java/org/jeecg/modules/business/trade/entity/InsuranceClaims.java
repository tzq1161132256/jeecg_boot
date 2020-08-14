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
 * @Description: 保险报销
 * @Author: jeecg-boot
 * @Date:   2019-08-20
 * @Version: V1.0
 */
@Data
@TableName("insurance_claims")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="insurance_claims对象", description="保险报销")
public class InsuranceClaims {
    
	/**主键*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "主键")
	private String id;
	/**订单号*/
	@Excel(name = "订单号", width = 15)
    @ApiModelProperty(value = "订单号")
	private String orderno;
	/**手机*/
	@Excel(name = "手机", width = 15)
    @ApiModelProperty(value = "手机")
	private String mobile;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
	private String name;
	/**申请数量*/
	@Excel(name = "申请数量", width = 15)
    @ApiModelProperty(value = "申请数量")
	private Integer applyqty;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String status;
	/**报销申请日期*/
	@Excel(name = "报销申请日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "报销申请日期")
	private Date applydate;
	/**个人凭证号*/
	@Excel(name = "个人凭证号", width = 15)
    @ApiModelProperty(value = "个人凭证号")
	private String idcard;
	/**赔案号*/
	@Excel(name = "赔案号", width = 15)
    @ApiModelProperty(value = "赔案号")
	private String claimno;
	/**业务系统中更新时间*/
	@Excel(name = "业务系统中更新时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "业务系统中更新时间")
	private Date modifydate;
	/**项目名称*/
	@Excel(name = "项目名称", width = 15)
    @ApiModelProperty(value = "项目名称")
	private String projectname;
	/**理赔金额*/
	@Excel(name = "理赔金额", width = 15)
    @ApiModelProperty(value = "理赔金额")
	private java.math.BigDecimal amount;
	/**处方医院*/
	@Excel(name = "处方医院", width = 15)
    @ApiModelProperty(value = "处方医院")
	private String attr1;
	/**处方医院省份*/
	@Excel(name = "处方医院省份", width = 15)
    @ApiModelProperty(value = "处方医院省份")
	private String attr2;
	/**处方医院城市*/
	@Excel(name = "处方医院城市", width = 15)
    @ApiModelProperty(value = "处方医院城市")
	private String attr3;
	/**理赔日期*/
	@Excel(name = "理赔日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "理赔日期")
	private Date attr4;
	/**发票日期*/
	@Excel(name = "发票日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "发票日期")
	private Date attr5;
	/**attr6*/
	@Excel(name = "attr6", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "attr6")
	private Date attr6;
	/**createdate*/
	@Excel(name = "createdate", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "createdate")
	private Date createdate;
	/**datelastupdate*/
	@Excel(name = "datelastupdate", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "datelastupdate")
	private Date datelastupdate;
	/**药房名称*/
	@Excel(name = "药房名称", width = 15)
    @ApiModelProperty(value = "药房名称")
	private String drugstorename;
	/**药房省份*/
	@Excel(name = "药房省份", width = 15)
    @ApiModelProperty(value = "药房省份")
	private String drugstoreprovincename;
	/**药房城市*/
	@Excel(name = "药房城市", width = 15)
    @ApiModelProperty(value = "药房城市")
	private String drugstorecityname;
	/**实际购药数量*/
	@Excel(name = "实际购药数量", width = 15)
    @ApiModelProperty(value = "实际购药数量")
	private String purchaseqty;
	/**处方量*/
	@Excel(name = "处方量", width = 15)
    @ApiModelProperty(value = "处方量")
	private String prescriptionvolume;
	/**身份证号码*/
	@Excel(name = "身份证号码", width = 15)
    @ApiModelProperty(value = "身份证号码")
	private String idnumber;
	/**性别*/
	@Excel(name = "性别", width = 15)
    @ApiModelProperty(value = "性别")
	private String sex;
	/**用户编码*/
	@Excel(name = "用户编码", width = 15)
    @ApiModelProperty(value = "用户编码")
	private String usercode;
	/**保险申请日期*/
	@Excel(name = "保险申请日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "保险申请日期")
	private Date applydateInsurance;
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
