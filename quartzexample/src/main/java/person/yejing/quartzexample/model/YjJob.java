package person.yejing.quartzexample.model;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@Slf4j
public class YjJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        ApplicationJob applicationJob = (ApplicationJob) jobDataMap.get("id");
        log.info(JSON.toJSONString(applicationJob));
    }
}
