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
 * @Description: 金融分期
 * @Author: jeecg-boot
 * @Date:   2019-08-21
 * @Version: V1.0
 */
@Data
@TableName("instalment")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="instalment对象", description="金融分期")
public class Instalment {
    
	/**项目名称*/
	@Excel(name = "项目名称", width = 15)
    @ApiModelProperty(value = "项目名称")
	private String projectname;
	/**申请人*/
	@Excel(name = "申请人", width = 15)
    @ApiModelProperty(value = "申请人")
	private String applicantname;
	/**申请人手机号*/
	@Excel(name = "申请人手机号", width = 15)
    @ApiModelProperty(value = "申请人手机号")
	private String applicantmobile;
	/**申请人身份证号*/
	@Excel(name = "申请人身份证号", width = 15)
    @ApiModelProperty(value = "申请人身份证号")
	private String applicantidcardno;
	/**患者姓名*/
	@Excel(name = "患者姓名", width = 15)
    @ApiModelProperty(value = "患者姓名")
	private String patientname;
	/**患者身份证号*/
	@Excel(name = "患者身份证号", width = 15)
    @ApiModelProperty(value = "患者身份证号")
	private String patientidcard;
	/**申请额度*/
	@Excel(name = "申请额度", width = 15)
    @ApiModelProperty(value = "申请额度")
	private Integer applyauota;
	/**批复额度*/
	@Excel(name = "批复额度", width = 15)
    @ApiModelProperty(value = "批复额度")
	private Integer approveauota;
	/**申请盒数*/
	@Excel(name = "申请盒数", width = 15)
    @ApiModelProperty(value = "申请盒数")
	private Integer applyqty;
	/**批复盒数*/
	@Excel(name = "批复盒数", width = 15)
    @ApiModelProperty(value = "批复盒数")
	private Integer approveqty;
	/**期数*/
	@Excel(name = "期数", width = 15)
    @ApiModelProperty(value = "期数")
	private Integer periods;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String status;
	/**申请时间*/
	@Excel(name = "申请时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "申请时间")
	private Date applydate;
	/**终审时间*/
	@Excel(name = "终审时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "终审时间")
	private Date approvedate;
	/**分期单ID*/
	@Excel(name = "分期单ID", width = 15)
    @ApiModelProperty(value = "分期单ID")
	private String applyid;
	/**金融分期单主键*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "金融分期单主键")
	private String id;
	/**更新时间*/
	@Excel(name = "更新时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
	private Date createdate;
	/**最后更新时间*/
	@Excel(name = "最后更新时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最后更新时间")
	private Date datelastupdate;
	/**审核失败时间*/
	@Excel(name = "审核失败时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "审核失败时间")
	private Date auditfaildate;
	/**审核成功时间*/
	@Excel(name = "审核成功时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "审核成功时间")
	private Date auditsuccessdate;
	/**药房id*/
	@Excel(name = "药房id", width = 15)
    @ApiModelProperty(value = "药房id")
	private String drugstoreid;
	/**资金渠道*/
	@Excel(name = "资金渠道", width = 15)
    @ApiModelProperty(value = "资金渠道")
	private String channel;
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
