package com.yp.ticket.service.impl;

import com.yp.ticket.dao.FlightMapper;
import com.yp.ticket.model.FlightModel;
import com.yp.ticket.python.AsyExecPython;
import com.yp.ticket.python.ExecPython;
import com.yp.ticket.service.FlightService;
import com.yp.ticket.service.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightMapper flightMapper;

    private ExecPython execPython;

    @Autowired
    private RedisService<FlightModel> redisService;

    @Value("${redis.redisZNPre}")
    private String redisPre;

    @Value("${redis.redisInterPre}")
    private String redisInterPre;

    @Value("${redis.redisRefresh}")
    private String redisRefresh;

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    @PostConstruct
    private void init(){
        execPython = new ExecPython();
    }

    @Override
    public List<FlightModel> getZNFlight(String depCity, String arrCity, String depDate) throws Exception{
        List<FlightModel> flightModels = redisService.lGet(redisPre+depCity+arrCity+depDate, 0, -1);
        if (!ObjectUtils.isEmpty(flightModels)){
            if (ObjectUtils.isEmpty(redisService.get(redisRefresh+depCity+arrCity+depDate))){
                asyExecpython(depCity, arrCity, depDate, false);
//                redisService.expire(redisPre+depCity+arrCity+depDate,0);
                redisService.del(redisPre+depCity+arrCity+depDate);
            }
            return flightModels;
        }
        List<FlightModel> flightLists = flightMapper.getSelectFlight(depCity, arrCity, depDate);
        if (!ObjectUtils.isEmpty(flightLists)){
            redisService.lSet(redisPre+depCity+arrCity+depDate, flightLists, 10 * 60);
//            if (ObjectUtils.isEmpty(redisService.get(redisRefresh+depCity+arrCity+depDate))){
//                asyExecpython(depCity, arrCity, depDate, false);
//            }
            return flightLists;
        }
        execpython(depCity, arrCity, depDate, false);
        List<FlightModel> lists = flightMapper.getSelectFlight(depCity, arrCity, depDate);
        //第一次加载
        if (lists != null && lists.size() != 0){
            redisService.lSet(redisPre+depCity+arrCity+depDate, lists, 10 * 60);
        }
        return lists;
    }

    @Override
    public List<FlightModel> getInterFlight(String depCity, String arrCity, String depDate) throws Exception {
        //第一次加载
        List<FlightModel> flightModels = redisService.lGet(redisInterPre+depCity+arrCity+depDate, 0, -1);
        if (!ObjectUtils.isEmpty(flightModels)){
            if (ObjectUtils.isEmpty(redisService.get(redisRefresh+depCity+arrCity+depDate))){
                asyExecpython(depCity, arrCity, depDate, true);
//                redisService.expire(redisInterPre+depCity+arrCity+depDate,0);
                redisService.del(redisInterPre+depCity+arrCity+depDate);
            }
            return flightModels;
        }
        List<FlightModel> flightLists = flightMapper.getSelectFlight(depCity, arrCity, depDate);
        if (!ObjectUtils.isEmpty(flightLists)){
            redisService.lSet(redisInterPre+depCity+arrCity+depDate, flightLists, 10 * 60);
//            if (ObjectUtils.isEmpty(redisService.get(redisRefresh+depCity+arrCity+depDate))){
//                asyExecpython(depCity, arrCity, depDate, true);
//            }
            return flightLists;
        }
        execpython(depCity, arrCity, depDate, true);
        List<FlightModel> lists = flightMapper.getSelectFlight(depCity, arrCity, depDate);
        if (lists != null && lists.size() != 0){
            redisService.lSet(redisInterPre+depCity+arrCity+depDate, lists, 10 * 60);
        }
        return lists;
    }

    @Override
    public String translate(String content) throws Exception{
        ExecPython execPython = new ExecPython();
        String result = execPython.translate(content);
        return result;
    }

    @Override
    public String identyImg(String path) throws Exception {
        ExecPython execPython = new ExecPython();
        final String result = execPython.identyImg(path);
        return result;
    }


    private void execpython(String depCity, String arrCity, String depDate, boolean flag) throws Exception{
        AsyExecPython asyExecPython = new AsyExecPython(execPython, depCity, arrCity, depDate, flag);
        Future<?> future = executorService.submit(asyExecPython);
        future.get();
        System.out.println("爬结束了亲");
    }

    private void asyExecpython(String depCity, String arrCity, String depDate, boolean flag) throws Exception{
        AsyExecPython asyExecPython = new AsyExecPython(execPython, depCity, arrCity, depDate, flag);
        redisService.set(redisRefresh+depCity+arrCity+depDate,depCity+arrCity+depDate, 5*60);
        executorService.execute(asyExecPython);
        System.out.println("正在异步爬结束了亲");
    }
}
