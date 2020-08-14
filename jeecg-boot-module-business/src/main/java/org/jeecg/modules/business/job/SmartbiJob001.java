package org.jeecg.modules.business.job;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.util.DateUtils;
import org.jeecg.modules.business.comm.service.SmartbiTools;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by hang.zhou on 2019/9/2.
 */
@Slf4j
public class SmartbiJob001 implements Job {
    /*   定时任务，每周5 17:30分执行一次  自动发送礼来爱优特订单报表*/
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info(String.format(" schedule job SmartbiJob001 !  时间:" + DateUtils.getTimestamp()));

        try {
            String scheduleId = "I8a281b090167fb58fb5805f20167fe7033af33b1";

           new SmartbiTools().callJob(scheduleId);



        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }

    }
}
