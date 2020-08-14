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
public class SmartbiJob003 implements Job {
     /*   定时任务，每月26号  17:30分执行一次  自动发送江西黄庆仁栈订单*/
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info(String.format(" schedule job SmartbiJob003 !  时间:" + DateUtils.getTimestamp()));

        try {
            String scheduleId = "I8a281b090167dc71dc71999e0167dfa84a5b1fe7";

           new SmartbiTools().callJob(scheduleId);



        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }

    }
}
