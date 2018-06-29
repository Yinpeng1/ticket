package com.yp.ticket.python;


import com.yp.ticket.dao.FlightMapper;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyExecPython implements Runnable {

    private ExecutorService executorService = Executors.newCachedThreadPool();
    private CountDownLatch countDownLatch = new CountDownLatch(2);
    private ExecPython execPython;
    private String depCity;
    private String arrCity;
    private String depDate;
    private boolean flag;

    public AsyExecPython(ExecPython execPython, String depCity, String arrCity, String depDate, boolean flag){
        this.execPython = execPython;
        this.depCity = depCity;
        this.arrCity = arrCity;
        this.depDate = depDate;
        this.flag = flag;
    }

    @Override
    public void run() {
        try {
            if (flag){
//                execPython.testXieCheng(depCity, arrCity, depDate);
                executorService.execute(() ->{
                    try{
                        execPython.testXieChengInter(depCity, arrCity, depDate);
                        countDownLatch.countDown();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                });
                executorService.execute(() -> {
                    try{
                        execPython.testTuNiu(depCity, arrCity, depDate);
                        countDownLatch.countDown();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                });
                countDownLatch.await();
            } else {
                executorService.execute(() -> {
                    try{
                        execPython.testXieCheng(depCity, arrCity, depDate);
                        countDownLatch.countDown();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                });
                executorService.execute(() ->{
                    try{
                        execPython.testQuNaEr(depCity, arrCity, depDate);
                        countDownLatch.countDown();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                });
                countDownLatch.await();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

//    public void execPythonGetDate(){
//        ExecPython execPython = new ExecPython();
//        AsyExecPython asyExecPython = new AsyExecPython(execPython, depCity, arrCity, depDate);
//        ExecutorService es = Executors.newFixedThreadPool(1);
//        Future<?> future = es.submit(asyExecPython);
//        try {
//            future.get();
//            System.out.println("爬结束了亲");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//    }

//    public static void main(String[] args) {
//        ExecPython execPython = new ExecPython();
//        String depCity = "北京";
//        String arrCity= "洛杉矶";
//        String depDate = "2018-10-10";
//        AsyExecPython newThreadExecPython = new AsyExecPython(execPython, depCity, arrCity, depDate);
//        ExecutorService es = Executors.newFixedThreadPool(1);
//        Future<?> future = es.submit(newThreadExecPython);
//        try {
//            future.get();
//            System.out.println("爬结束了亲");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//    }
}
