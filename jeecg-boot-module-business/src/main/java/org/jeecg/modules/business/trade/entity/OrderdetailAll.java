package org.jeecg.modules.business.trade.entity;

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
 * @Description: 订单明细
 * @Author: jeecg-boot
 * @Date:   2019-07-31
 * @Version: V1.0
 */
@Data
@TableName("orderdetail_all")
public class OrderdetailAll implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**业务系统中订单明细ID*/
    @Excel(name = "业务系统中订单明细ID", width = 15)
	private String orderdetailid;
	/**BI系统中的订单ID*/
	private String orderid;
	/**药品名称*/
    @Excel(name = "药品名称", width = 15)
	private String medname;
	/**销量*/
    @Excel(name = "销量", width = 15)
	private Integer qty;
	/**适应症*/
    @Excel(name = "适应症", width = 15)
	private String indication;
	/**适应症分类*/
    @Excel(name = "适应症分类", width = 15)
	private String indicationtype;
	/**记录生成时间*/
	@Excel(name = "记录生成时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createdate;
	/**最后更新时间*/
	@Excel(name = "最后更新时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date datelastupdate;
	/**订单编码*/
    @Excel(name = "订单编码", width = 15)
	private String ordercode;
	/**主键*/
	@TableId(type = IdType.UUID)
	private String id;
	/**规格*/
    @Excel(name = "规格", width = 15)
	private String spec;
	/**单位*/
    @Excel(name = "单位", width = 15)
	private String unit;
	/**厂家*/
    @Excel(name = "厂家", width = 15)
	private String manufactor;
	/**药品编码*/
    @Excel(name = "药品编码", width = 15)
	private String medcode;
	/**单价*/
    @Excel(name = "单价", width = 15)
	private java.math.BigDecimal unitprice;
	/**补贴金额 （BMS项目）*/
    @Excel(name = "补贴金额 （BMS项目）", width = 15)
	private java.math.BigDecimal subsidyamount;
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
}
