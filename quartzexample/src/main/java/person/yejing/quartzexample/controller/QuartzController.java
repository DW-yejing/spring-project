package person.yejing.quartzexample.controller;

import com.alibaba.fastjson.JSON;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import person.yejing.quartzexample.model.ApplicationJob;
import person.yejing.quartzexample.model.YjJob;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class QuartzController {
    public static Map<String, ApplicationJob> jobMap = new ConcurrentHashMap<>();

    /**
     * 新增定时任务
     */
    @RequestMapping("/add")
    public String addJob(String cronExpression) throws Exception{
        String id = UUID.randomUUID().toString();
        // 获取调度机
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        // 生成触发器
        CronScheduleBuilder cronScheduleBuilder =CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(id).build();
        // 生成任务
        JobDetail jobDetail = JobBuilder.newJob(YjJob.class).withIdentity(id).build();
        scheduler.scheduleJob(jobDetail, trigger);
        ApplicationJob applicationJob = new ApplicationJob(id, cronExpression, "0");
        jobDetail.getJobDataMap().put(id, applicationJob);
        jobMap.put(id, applicationJob);
        return JSON.toJSONString(applicationJob);
    }

    @PostConstruct
    public String addJob1() throws Exception{
        String cronExpression = "0/1 * * * * ?";
        String id = UUID.randomUUID().toString();
        // 获取调度机
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        // 生成触发器
        CronScheduleBuilder cronScheduleBuilder =CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(id).build();
        // 生成任务
        JobDetail jobDetail = JobBuilder.newJob(YjJob.class).withIdentity(id).build();
        scheduler.scheduleJob(jobDetail, trigger);
        ApplicationJob applicationJob = new ApplicationJob(id, cronExpression, "0");
        jobDetail.getJobDataMap().put(id, applicationJob);
        jobMap.put(id, applicationJob);
        return JSON.toJSONString(applicationJob);
    }

    /**
     * 查看定时任务
     */
    @RequestMapping("/list")
    public String listJob(){
        return JSON.toJSONString(jobMap);
    }

    /**
     * 修改定时任务
     */
    @RequestMapping("/update")
    public String updateJob(String jobName){
        return null;
    }

    /**
     * 删除定时任务
     */
    @RequestMapping("/remove")
    public String removeJob(String jobName){
        return null;
    }

    /**
     * 执行任务
     */
    @RequestMapping("/run")
    public String doJob(String id){
        ApplicationJob applicationJob = jobMap.get(id);
        if(applicationJob == null){
            return "未发现任务";
        }

        return null;
    }

    /**
     * 暂停任务
     */
    @RequestMapping("/standby")
    public String standByJob(String id){
        return null;
    }
}
