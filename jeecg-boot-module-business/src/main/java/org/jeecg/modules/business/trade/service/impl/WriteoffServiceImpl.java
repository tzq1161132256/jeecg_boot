package org.jeecg.modules.business.trade.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.jeecg.modules.business.trade.entity.Order;
import org.jeecg.modules.business.trade.entity.Orderdetail;
import org.jeecg.modules.business.trade.entity.Writeoff;
import org.jeecg.modules.business.trade.mapper.WriteoffMapper;
import org.jeecg.modules.business.trade.service.IOrderAllService;
import org.jeecg.modules.business.trade.service.IOrderService;
import org.jeecg.modules.business.trade.service.IWriteoffService;
import org.jeecg.modules.business.trade.vo.OrderPage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 慢病核销对账单
 * @Author: jeecg-boot
 * @Date:   2019-08-12
 * @Version: V1.0
 */
@Service
@DS("multi-datasource1")
public class WriteoffServiceImpl extends ServiceImpl<WriteoffMapper, Writeoff> implements IWriteoffService {
    @Autowired
    IOrderAllService orderAllService;


    @Override
    public void processWriteoff(Writeoff writeoff) throws Exception {

        String beginTime = "1970-01-01 08:00:00";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date2 = format.parse(beginTime);
        if(writeoff.getOrderdate().getTime() == date2.getTime() ) {
            writeoff.setOrderdate(null);
        }

        // 判断药店省份 是上海 还是 湖北，药品的单价不一样，上海 70.5  湖北 62
        if("湖北".equals(writeoff.getDrugstoreprovincename())){
            writeoff.setUnitprice(new BigDecimal("62"));
        } else  {
            writeoff.setUnitprice(new BigDecimal("70.5"));
        }
        writeoff.setCreateTime(new Date());
        writeoff.setUpdateTime(new Date());
        saveOrUpdate(writeoff);

        // 将核销单插入订单表
        OrderPage order = new OrderPage();
        BeanUtils.copyProperties(writeoff, order);

        order.setId(order.getProjectname() +"_"+writeoff.getId());
        order.setAmount(writeoff.getUnitprice());
        order.setDiscount(new BigDecimal(1));
        order.setAfterdiscountamount(writeoff.getUnitprice());
        order.setOrderdate(order.getSettlementdate());
        order.setOrdercode(order.getRemark()+"_"+writeoff.getId());

        Orderdetail orderdetail = new Orderdetail();
        orderdetail.setOrderdetailid(order.getId());
        orderdetail.setId(order.getId());
        orderdetail.setOrderid(order.getId());
        orderdetail.setQty(1);
        orderdetail.setMedname("拜唐苹");
        orderdetail.setUnitprice(order.getAmount());
        orderdetail.setSpec("50mg*30片");
        List<Orderdetail> orderdetailList = new ArrayList<>();
        orderdetailList.add(orderdetail);
        order.setOrderdetailList(orderdetailList);

        orderAllService.processOrderAll(order);

    }
}
