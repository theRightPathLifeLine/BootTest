package com.line.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.line.thirdparty.mastercontrol.ConnectMaster;
import com.line.thirdparty.mastercontrol.worker.HeartBeatWork;

/**
 * Created by admin on 2019/1/3.
 */
@Component
public class SchedulerTask {
    /**
     * @Scheduled(fixedRate = 6000) ：上一次开始执行时间点之后6秒再执行
     * @Scheduled(fixedDelay = 6000) ：上一次执行完毕时间点之后6秒再执行
     * @Scheduled(initialDelay=1000, fixedRate=6000) ：第一次延迟1秒后执行，之后按fixedRate的规则每6秒执行一次
     */
	@Autowired
	private ConnectMaster connectMaster;
	

    private int count=0;

    @Scheduled(cron="*/10 * * * * ?")
    private void process(){
        System.out.println("this is scheduler task runing  "+(count++));
    }

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(initialDelay=10000, fixedRate=10000)
    public void reportCurrentTime() {
        System.out.println("现在时间：" + dateFormat.format(new Date()));
        HeartBeatWork connectWork = new HeartBeatWork();
        connectMaster.excuteConcentWorker(connectWork, 3);
    }
}
