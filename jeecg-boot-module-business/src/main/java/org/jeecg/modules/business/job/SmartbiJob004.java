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
public class SmartbiJob004 implements Job {
    /*   定时任务，每周五  16:30分执行  自动发送万赛维保险出单汇总报表*/
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info(String.format(" schedule job SmartbiJob004 !  时间:" + DateUtils.getTimestamp()));

        try {
            String scheduleId = "I8a281b090167fb57fb57bfdd0167fce5f1180b8a";

           new SmartbiTools().callJob(scheduleId);



        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }

    }
}
