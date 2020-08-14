package org.jeecg.modules.business.insurance.entity;

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
 * @Description: 华夏保险
 * @Author: jeecg-boot
 * @Date:   2019-08-19
 * @Version: V1.0
 */
@Data
@TableName("insurance_huaxia")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="insurance_huaxia对象", description="华夏保险")
public class InsuranceHuaxia {
    
	/**保单号*/
	@Excel(name = "保单号", width = 15)
    @ApiModelProperty(value = "保单号")
	private String policyno;
	/**生效时间*/
	@Excel(name = "生效时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "生效时间")
	private Date effectivetime;
	/**有效性 */
	@Excel(name = "有效性 ", width = 15)
    @ApiModelProperty(value = "有效性 ")
	private String policytype;
	/**生日*/
	@Excel(name = "生日", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "生日")
	private Date birth;
	/**ext1*/
	@Excel(name = "ext1", width = 15)
    @ApiModelProperty(value = "ext1")
	private String ext1;
	/**ext2*/
	@Excel(name = "ext2", width = 15)
    @ApiModelProperty(value = "ext2")
	private String ext2;
	/**ext3*/
	@Excel(name = "ext3", width = 15)
    @ApiModelProperty(value = "ext3")
	private String ext3;
	/**ext4*/
	@Excel(name = "ext4", width = 15)
    @ApiModelProperty(value = "ext4")
	private String ext4;
	/**ext5*/
	@Excel(name = "ext5", width = 15)
    @ApiModelProperty(value = "ext5")
	private String ext5;
	/**ext6*/
	@Excel(name = "ext6", width = 15)
    @ApiModelProperty(value = "ext6")
	private String ext6;
	/**ext7*/
	@Excel(name = "ext7", width = 15)
    @ApiModelProperty(value = "ext7")
	private String ext7;
	/**ext8*/
	@Excel(name = "ext8", width = 15)
    @ApiModelProperty(value = "ext8")
	private String ext8;
	/**主键ID*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "主键ID")
	private String id;
	/**创建时间*/
	@Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
	private Date createdate;
	/**最后更新时间*/
	@Excel(name = "最后更新时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最后更新时间")
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
