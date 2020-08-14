package org.jeecg.modules.business.trade.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 订单
 * @Author: jeecg-boot
 * @Date:   2019-07-31
 * @Version: V1.0
 */
@Data
@TableName("`order`")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**业务系统中的订单ID*/
	private String orderid;
	/**订单日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date orderdate;
	/**订单类型，送药到家/送药到家*/
	private String ordertype;
	/**订单编码*/
	private String ordercode;
	/**医院名称*/
	private String hospitalname;
	/**医生名称*/
	private String doctorname;
	/**是否项目医生*/
	private String doctorflag;
	/**药店省份*/
	private String drugstoreprovincename;
	/**药店城市*/
	private String drugstorecityname;
	/**药店名称*/
	private String drugstorename;
	/**患者名称*/
	private String patientname;
	/**患者电话*/
	private String patientmobile;
	/**购买人名称*/
	private String buyername;
	/**购买人电话*/
	private String buyermobile;
	/**收货人名称*/
	private String consigneename;
	/**收货人电话*/
	private String consigneemobile;
	/**收货地址*/
	private String consigneeaddress;
	/**记录生成时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createdate;
	/**记录修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date datelastupdate;
	/**订单状态*/
	private String status;
	/**订单配送时间*/
	private String deliverytime;
	/**适应症*/
	private String indication;
	/**适应症分类*/
	private String indicationtype;
	/**主键*/
	@TableId(type = IdType.UUID)
	private String id;
	/**最后更新人*/
	private String updateuser;
	/**项目名称*/
	private String projectname;
	/**患者身份证号码*/
	private String patientidcard;
	/**购买人身份证号码*/
	private String buyeridcard;
	/**药房结算金额*/
	private java.math.BigDecimal amount;
	/**折扣*/
	private java.math.BigDecimal discount;
	/**折后总金额*/
	private java.math.BigDecimal afterdiscountamount;
	/**扣款通道*/
	private String paychannel;
	/**手续费*/
	private java.math.BigDecimal servicecharge;
	/**补贴金额*/
	private java.math.BigDecimal subsidyamount;
	/**到账金额*/
	private java.math.BigDecimal amountaccount;
	/**备注*/
	private String remark;
	/**结算情况*/
	private String settlementstatus;
	/**打款日*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date settlementdate;
	/**药房id*/
	private String drugstoreid;
	/**订单来源（默沙东精准肝愈）WECHAT/PAD*/
	private String ordersource;
	/**是否会员订单(BPM项目)*/
	private String memberorderflag;
	/**用户类型(关爱卡/健康卡/康付卡) (BPM项目)*/
	private String usertype;
	/**药房渠道（康德乐/众协）(BPM项目)*/
	private String drugstorechannel;
	/**药店返点收入(BPM项目)*/
	private java.math.BigDecimal rebate;
	/**净补贴(BPM项目)*/
	private java.math.BigDecimal netsubsidy;
	/**业务区域名称*/
	private String areaname;
	/**标识线下导入的数据*/
	private String remark2;
	/**不通过原因*/
	private String reason;
	/**期望配送日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date expecteddeliverydate;
	/**医院省份*/
	private String hospitalprovincename;
	/**医院城市*/
	private String hospitalcityname;
	/**处方量*/
	private String prescriptionvolume;
	/**是否退款*/
	private String refundflag;
	/**退款原因*/
	private String refundreason;
	/**项目来源名称*/
	private String remark3;
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

}
