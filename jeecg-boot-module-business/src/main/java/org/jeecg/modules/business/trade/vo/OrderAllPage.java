package org.jeecg.modules.business.trade.vo;

import java.util.List;
import org.jeecg.modules.business.trade.entity.OrderAll;
import org.jeecg.modules.business.trade.entity.OrderdetailAll;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * @Description: 订单
 * @Author: jeecg-boot
 * @Date:   2019-07-31
 * @Version: V1.0
 */
@Data
public class OrderAllPage {
	
	/**业务系统中的订单ID*/
  	@Excel(name = "业务系统中的订单ID", width = 15)
	private String orderid;
	/**订单日期*/
  	@Excel(name = "订单日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date orderdate;
	/**订单类型，送药到家/送药到家*/
  	@Excel(name = "订单类型，送药到家/送药到家", width = 15)
	private String ordertype;
	/**订单编码*/
  	@Excel(name = "订单编码", width = 15)
	private String ordercode;
	/**医院名称*/
  	@Excel(name = "医院名称", width = 15)
	private String hospitalname;
	/**医生名称*/
  	@Excel(name = "医生名称", width = 15)
	private String doctorname;
	/**是否项目医生*/
  	@Excel(name = "是否项目医生", width = 15)
	private String doctorflag;
	/**药店省份*/
  	@Excel(name = "药店省份", width = 15)
	private String drugstoreprovincename;
	/**药店城市*/
  	@Excel(name = "药店城市", width = 15)
	private String drugstorecityname;
	/**药店名称*/
  	@Excel(name = "药店名称", width = 15)
	private String drugstorename;
	/**患者名称*/
  	@Excel(name = "患者名称", width = 15)
	private String patientname;
	/**患者电话*/
  	@Excel(name = "患者电话", width = 15)
	private String patientmobile;
	/**购买人名称*/
  	@Excel(name = "购买人名称", width = 15)
	private String buyername;
	/**购买人电话*/
  	@Excel(name = "购买人电话", width = 15)
	private String buyermobile;
	/**收货人名称*/
  	@Excel(name = "收货人名称", width = 15)
	private String consigneename;
	/**收货人电话*/
  	@Excel(name = "收货人电话", width = 15)
	private String consigneemobile;
	/**收货地址*/
  	@Excel(name = "收货地址", width = 15)
	private String consigneeaddress;
	/**记录生成时间*/
  	@Excel(name = "记录生成时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createdate;
	/**记录修改时间*/
  	@Excel(name = "记录修改时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date datelastupdate;
	/**订单状态*/
  	@Excel(name = "订单状态", width = 15)
	private String status;
	/**订单配送时间*/
  	@Excel(name = "订单配送时间", width = 15)
	private String deliverytime;
	/**适应症*/
  	@Excel(name = "适应症", width = 15)
	private String indication;
	/**适应症分类*/
  	@Excel(name = "适应症分类", width = 15)
	private String indicationtype;
	/**主键*/
	private String id;
	/**最后更新人*/
  	@Excel(name = "最后更新人", width = 15)
	private String updateuser;
	/**项目名称*/
  	@Excel(name = "项目名称", width = 15)
	private String projectname;
	/**患者身份证号码*/
  	@Excel(name = "患者身份证号码", width = 15)
	private String patientidcard;
	/**购买人身份证号码*/
  	@Excel(name = "购买人身份证号码", width = 15)
	private String buyeridcard;
	/**药房结算金额*/
  	@Excel(name = "药房结算金额", width = 15)
	private java.math.BigDecimal amount;
	/**折扣*/
  	@Excel(name = "折扣", width = 15)
	private java.math.BigDecimal discount;
	/**折后总金额*/
  	@Excel(name = "折后总金额", width = 15)
	private java.math.BigDecimal afterdiscountamount;
	/**扣款通道*/
  	@Excel(name = "扣款通道", width = 15)
	private String paychannel;
	/**手续费*/
  	@Excel(name = "手续费", width = 15)
	private java.math.BigDecimal servicecharge;
	/**补贴金额*/
  	@Excel(name = "补贴金额", width = 15)
	private java.math.BigDecimal subsidyamount;
	/**到账金额*/
  	@Excel(name = "到账金额", width = 15)
	private java.math.BigDecimal amountaccount;
	/**备注*/
  	@Excel(name = "备注", width = 15)
	private String remark;
	/**结算情况*/
  	@Excel(name = "结算情况", width = 15)
	private String settlementstatus;
	/**打款日*/
  	@Excel(name = "打款日", width = 20, format = "yyyy-MM-dd HH:mm:ss")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date settlementdate;
	/**是否清洗主数据*/
  	@Excel(name = "是否清洗主数据", width = 15)
	private String clearflag;
	/**患者id*/
  	@Excel(name = "患者id", width = 15)
	private String patientid;
	/**药房id*/
  	@Excel(name = "药房id", width = 15)
	private String drugstoreid;
	/**是否已有物流信息*/
  	@Excel(name = "是否已有物流信息", width = 15)
	private String logisticsflag;
	/**预计下次购药日期*/
  	@Excel(name = "预计下次购药日期", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date nextpurchasetime;
	/**订单来源（默沙东精准肝愈）WECHAT/PAD*/
  	@Excel(name = "订单来源（默沙东精准肝愈）WECHAT/PAD", width = 15)
	private String ordersource;
	/**是否会员订单(BPM项目)*/
  	@Excel(name = "是否会员订单(BPM项目)", width = 15)
	private String memberorderflag;
	/**用户类型(关爱卡/健康卡/康付卡) (BPM项目)*/
  	@Excel(name = "用户类型(关爱卡/健康卡/康付卡) (BPM项目)", width = 15)
	private String usertype;
	/**药房渠道（康德乐/众协）(BPM项目)*/
  	@Excel(name = "药房渠道（康德乐/众协）(BPM项目)", width = 15)
	private String drugstorechannel;
	/**药店返点收入(BPM项目)*/
  	@Excel(name = "药店返点收入(BPM项目)", width = 15)
	private java.math.BigDecimal rebate;
	/**净补贴(BPM项目)*/
  	@Excel(name = "净补贴(BPM项目)", width = 15)
	private java.math.BigDecimal netsubsidy;
	/**业务区域名称*/
  	@Excel(name = "业务区域名称", width = 15)
	private String areaname;
	/**标识线下导入的数据*/
  	@Excel(name = "标识线下导入的数据", width = 15)
	private String remark2;
	/**用券数量*/
  	@Excel(name = "用券数量", width = 15)
	private Integer ticketcount;
	/**不通过原因*/
  	@Excel(name = "不通过原因", width = 15)
	private String reason;
	/**期望配送日期*/
  	@Excel(name = "期望配送日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date expecteddeliverydate;
	/**医院省份*/
  	@Excel(name = "医院省份", width = 15)
	private String hospitalprovincename;
	/**医院城市*/
  	@Excel(name = "医院城市", width = 15)
	private String hospitalcityname;
	/**处方量*/
  	@Excel(name = "处方量", width = 15)
	private String prescriptionvolume;
	/**是否退款*/
  	@Excel(name = "是否退款", width = 15)
	private String refundflag;
	/**退款原因*/
  	@Excel(name = "退款原因", width = 15)
	private String refundreason;
	/**项目来源名称*/
  	@Excel(name = "项目来源名称", width = 15)
	private String remark3;
	/**购药人id*/
  	@Excel(name = "购药人id", width = 15)
	private String buyerid;
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
	
	@ExcelCollection(name="订单明细")
	private List<OrderdetailAll> orderdetailAllList;
	
}
