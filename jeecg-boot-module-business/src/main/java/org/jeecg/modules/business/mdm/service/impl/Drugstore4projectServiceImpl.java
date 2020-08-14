package org.jeecg.modules.business.mdm.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.jeecg.modules.business.mdm.entity.Drugstore4project;
import org.jeecg.modules.business.mdm.mapper.Drugstore4projectMapper;
import org.jeecg.modules.business.mdm.service.IDrugstore4projectService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * @Description: 项目对应的药店
 * @Author: jeecg-boot
 * @Date:   2019-08-20
 * @Version: V1.0
 */
@Service
@DS("multi-datasource1")
public class Drugstore4projectServiceImpl extends ServiceImpl<Drugstore4projectMapper, Drugstore4project> implements IDrugstore4projectService {
    @Override
    public void processDrugstore4project(Drugstore4project drugstore4project) throws Exception {
        if ("FASTPBM20180120@MTH".equals(drugstore4project.getProjectname()) || "FASTPBM20171219@MTH".equals(drugstore4project.getProjectname())  ) {
            drugstore4project.setProjectname("PBM（药康付）");
        }else if("EpMSD".equals(drugstore4project.getProjectname()) || "EPMSD".equals(drugstore4project.getProjectname())){
            drugstore4project.setProjectname("默沙东择必达（精准肝愈）");
        }else if("EpMerck".equals(drugstore4project.getProjectname())){
            // moke
            drugstore4project.setProjectname("默克怡可安（怡可安心）");
        }else if("EpAss".equals(drugstore4project.getProjectname())){
            drugstore4project.setProjectname("罗氏安圣莎（安圣安心）");
        }else if("EpFktn".equals(drugstore4project.getProjectname())){
            drugstore4project.setProjectname("礼来（呋喹替尼）");
        }else if("EpFmd".equals(drugstore4project.getProjectname())){
            drugstore4project.setProjectname("复旦张江复美达（圆梦天使）");
        }else if("EpPerjeta".equals(drugstore4project.getProjectname())){
            drugstore4project.setProjectname("罗氏帕捷特");
        }else if("EpIbrance".equals(drugstore4project.getProjectname())){
            drugstore4project.setProjectname("辉瑞爱博新（博爱新安）");
        }else if("EpTrulicity".equals(drugstore4project.getProjectname())){
            drugstore4project.setProjectname("礼来度易达(易相伴)");
        }else if("EpCosentyx".equals(drugstore4project.getProjectname())){
            drugstore4project.setProjectname("诺华可善挺（净善净美）");
        }else if("EpOpsumit".equals(drugstore4project.getProjectname())){
            drugstore4project.setProjectname("爱可泰隆（傲朴舒）");
        }else if("NCD_INSURANCE_BTP".equals(drugstore4project.getProjectname())){
            drugstore4project.setProjectname("慢病无忧");
        }

        saveOrUpdate(drugstore4project);
    }

    @Override
    public List<Map> getNoSalesDrugstore(Map<String, Object> var1) {
        return baseMapper.getNoSalesDrugstore(var1);
    }
}
