package org.jeecg.modules.business.mdm.entity;

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
 * @Description: ipad领用信息
 * @Author: jeecg-boot
 * @Date:   2019-08-20
 * @Version: V1.0
 */
@Data
@TableName("equipmentapply")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="equipmentapply对象", description="ipad领用信息")
public class Equipmentapply {
    
	/**药房名称*/
	@Excel(name = "药房名称", width = 15)
    @ApiModelProperty(value = "药房名称")
	private String drugstorename;
	/**药房所在省份*/
	@Excel(name = "药房所在省份", width = 15)
    @ApiModelProperty(value = "药房所在省份")
	private String provincename;
	/**药房所在城市*/
	@Excel(name = "药房所在城市", width = 15)
    @ApiModelProperty(value = "药房所在城市")
	private String cityname;
	/**设备类型（ipad、pos、printer）*/
	@Excel(name = "设备类型（ipad、pos、printer）", width = 15)
    @ApiModelProperty(value = "设备类型（ipad、pos、printer）")
	private String equipmenttype;
	/**设备编码*/
	@Excel(name = "设备编码", width = 15)
    @ApiModelProperty(value = "设备编码")
	private String equipmentcode;
	/**设备名称*/
	@Excel(name = "设备名称", width = 15)
    @ApiModelProperty(value = "设备名称")
	private String equipmentname;
	/**设备唯一ID*/
	@Excel(name = "设备唯一ID", width = 15)
    @ApiModelProperty(value = "设备唯一ID")
	private String equipmentid;
	/**remark1*/
	@Excel(name = "remark1", width = 15)
    @ApiModelProperty(value = "remark1")
	private String remark1;
	/**remark2*/
	@Excel(name = "remark2", width = 15)
    @ApiModelProperty(value = "remark2")
	private String remark2;
	/**主键*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "主键")
	private String id;
	/**创建日期*/
	@Excel(name = "创建日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
	private Date createdate;
	/**最后更新日期*/
	@Excel(name = "最后更新日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最后更新日期")
	private Date datelastupdate;
	/**药房id*/
	@Excel(name = "药房id", width = 15)
    @ApiModelProperty(value = "药房id")
	private String drugstoreid;
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
