package com.yp.ticket.cron;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CronTask {

    @Scheduled(cron = "0 0 1 * * ?")
    public void deleteData(){

    }
}
