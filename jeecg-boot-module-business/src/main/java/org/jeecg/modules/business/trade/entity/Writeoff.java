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
 * @Description: 慢病核销对账单
 * @Author: jeecg-boot
 * @Date:   2019-08-12
 * @Version: V1.0
 */
@Data
@TableName("writeoff")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="writeoff对象", description="慢病核销对账单")
public class Writeoff {
    
	/**主键*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "主键")
	private String id;
	/**药店id*/
	@Excel(name = "药店id", width = 15)
    @ApiModelProperty(value = "药店id")
	private String drugstoreid;
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
	/**项目名称*/
	@Excel(name = "项目名称", width = 15)
    @ApiModelProperty(value = "项目名称")
	private String projectname;
	/**优惠券码*/
	@Excel(name = "优惠券码", width = 15)
    @ApiModelProperty(value = "优惠券码")
	private String remark;
	/**购药人姓名*/
	@Excel(name = "购药人姓名", width = 15)
    @ApiModelProperty(value = "购药人姓名")
	private String buyername;
	/**购药人手机号*/
	@Excel(name = "购药人手机号", width = 15)
    @ApiModelProperty(value = "购药人手机号")
	private String buyermobile;
	/**订单日期*/
	@Excel(name = "订单日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "订单日期")
	private Date orderdate;
	/**订单号*/
	@Excel(name = "订单号", width = 15)
    @ApiModelProperty(value = "订单号")
	private String ordercode;
	/**核销日期*/
	@Excel(name = "核销日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "核销日期")
	private Date settlementdate;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String status;
	/**药品名称*/
	@Excel(name = "药品名称", width = 15)
    @ApiModelProperty(value = "药品名称")
	private String medname;
	/**单价*/
	@Excel(name = "单价", width = 15)
    @ApiModelProperty(value = "单价")
	private java.math.BigDecimal unitprice;
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
