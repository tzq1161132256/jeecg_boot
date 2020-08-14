package org.jeecg.modules.business.trade.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.jeecg.modules.business.trade.entity.Order;
import org.jeecg.modules.business.trade.entity.OrderAll;
import org.jeecg.modules.business.trade.entity.Prescription;
import org.jeecg.modules.business.trade.mapper.PrescriptionMapper;
import org.jeecg.modules.business.trade.service.IOrderAllService;
import org.jeecg.modules.business.trade.service.IOrderService;
import org.jeecg.modules.business.trade.service.IPrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 处方信息表
 * @Author: jeecg-boot
 * @Date:   2019-08-09
 * @Version: V1.0
 */
@Service
@DS("multi-datasource1")
public class PrescriptionServiceImpl extends ServiceImpl<PrescriptionMapper, Prescription> implements IPrescriptionService {
    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderAllService orderAllService;

    @Override
    @Transactional
    public void processPrescription(Prescription prescription) throws Exception {

        String beginTime = "1970-01-01 08:00:00";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date2 = format.parse(beginTime);
        if(prescription.getPrescriptiondate().getTime() == date2.getTime() ) {
            prescription.setPrescriptiondate(null);
        }

        /**  根据订单编码+项目名称，判断是否存在。
         * */
        QueryWrapper<Prescription> wrapper = new QueryWrapper<>();
        wrapper.eq("ordercode",prescription.getOrdercode());
        wrapper.eq("projectname",prescription.getProjectname());
        Prescription prescription_tmp = getOne(wrapper,true);
        if(prescription_tmp == null ) {
            prescription.setUpdateTime(new Date());
            prescription.setCreateTime(new Date());
            save(prescription);
        }else {
            prescription.setCreateTime(prescription_tmp.getCreateTime());
            prescription.setUpdateTime(new Date());
            UpdateWrapper updateWrapper = new UpdateWrapper();
            updateWrapper.eq("ordercode",prescription.getOrdercode());
            updateWrapper.eq("projectname",prescription.getProjectname());
            update(prescription,updateWrapper);
        }

        // 更新正式订单表中的处方信息
        Order order = new Order();
        order.setHospitalname(prescription.getHospitalname());
        order.setDoctorname(prescription.getDoctorname());
        order.setOrdercode(prescription.getOrdercode());
      //  order.setProjectname(prescription.getProjectname());
        order.setUpdateTime(prescription.getUpdateTime());
        orderService.updateOrder4Prescription(order);

        // 更新全量订单表中的处方信息
        OrderAll orderAll = new OrderAll();
        orderAll.setHospitalname(prescription.getHospitalname());
        orderAll.setDoctorname(prescription.getDoctorname());
        orderAll.setOrdercode(prescription.getOrdercode());
        // orderAll.setProjectname(prescription.getProjectname());
        orderAll.setUpdateTime(prescription.getUpdateTime());
        orderAllService.updateOrderAll4Prescription(orderAll);
    }
}
