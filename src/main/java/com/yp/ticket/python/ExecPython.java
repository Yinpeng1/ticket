package com.yp.ticket.python;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;


public class ExecPython{

    public void testXieChengInter(String depCity, String arrCity, String depdate) throws Exception{
        System.out.println("正在爬取携程的航班信息，请稍后。。。");
//        String[] args1 = new String[] { "python", "C:\\Users\\pyin\\PycharmProjects\\Test\\flight\\xiechengflight.py", depCity, arrCity, depdate};
        String[] args1 = new String[] { "python", "/opt/flight/bin/xiechengflight.py", depCity, arrCity, depdate};
        try {
            Process pr = Runtime.getRuntime().exec(args1);
            pr.waitFor(35,TimeUnit.SECONDS);
//            pr.waitFor();
            System.out.println("携程爬取结束...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testXieCheng(String depCity, String arrCity, String depdate) throws Exception{
        System.out.println("正在爬取携程的航班信息，请稍后。。。");
//        String[] args1 = new String[] { "python", "C:\\Users\\pyin\\PycharmProjects\\Test\\flight\\xiechengflight.py", depCity, arrCity, depdate};
        String[] args1 = new String[] { "python", "/opt/flight/bin/xiechengflight.py", depCity, arrCity, depdate};
        try {
            Process pr = Runtime.getRuntime().exec(args1);
            pr.waitFor();
            System.out.println("携程爬取结束...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testQuNaEr(String depCity, String arrCity, String depdate) throws Exception{
        System.out.println("正在爬取去哪儿的航班信息，请稍后。。。");
//        String[] args1 = new String[] { "python", "C:\\Users\\pyin\\PycharmProjects\\Test\\flight\\qunaerFlight.py", depCity, arrCity, depdate};
        String[] args1 = new String[] { "python", "/opt/flight/bin/qunaerFlight.py", depCity, arrCity, depdate};
        try {
            Process pr = Runtime.getRuntime().exec(args1);
            pr.waitFor();
            System.out.println("去哪儿爬取结束");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testTuNiu(String depCity, String arrCity, String depdate) throws Exception{
        System.out.println("正在爬取途牛的航班信息，请稍后。。。");
//        String[] args1 = new String[] { "python", "C:\\Users\\pyin\\PycharmProjects\\Test\\flight\\tuNiuInterFlight2.py", depCity, arrCity, depdate};
        String[] args1 = new String[] { "python", "/opt/flight/bin/tuNiuInterFlight.py", depCity, arrCity, depdate};
        try {
            Process pr = Runtime.getRuntime().exec(args1);
            pr.waitFor(50,TimeUnit.SECONDS);
//            pr.waitFor();
            System.out.println("途牛爬取结束");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String translate(String content) throws Exception{
        System.out.println("正在获取相关翻译结果。。。");
        String[] args1 = new String[] { "python", "/opt/flight/bin/fanyi.py", content};
//        String[] args1 = new String[] { "python", "C:\\Users\\pyin\\PycharmProjects\\Test\\testApi\\fanyi.py", content};
        InputStreamReader ir = null;
        BufferedReader in = null;
        String reStr = "";
        try {
            Process pr = Runtime.getRuntime().exec(args1);
            pr.waitFor();
            ir = new InputStreamReader(pr.getInputStream());
            in = new BufferedReader(ir);

            String line = "";
            while ((line = in.readLine()) != null) {
                reStr += line;
            }

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
            if (ir != null){
                ir.close();
            }
        }
        System.out.println("获取结束。。。");
        return reStr;
    }

    public String identyImg(String path) throws Exception{
        System.out.println("正在识别图片，请稍后。。。");
        String[] args1 = new String[] { "python", "C:\\Users\\pyin\\PycharmProjects\\Test\\identyImage\\translate.py", path};
        InputStreamReader ir = null;
        BufferedReader in = null;
        String reStr = "";
        try{
            Process pr = Runtime.getRuntime().exec(args1);
            pr.waitFor();
            ir = new InputStreamReader(pr.getInputStream());
            in = new BufferedReader(ir);

            String line = "";
            while ((line = in.readLine()) != null) {
                reStr += line;
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
            if (ir != null){
                ir.close();
            }
        }
        System.out.println("识别结束。。。" );
        final String subStr = reStr;
        final String sourceArr[] = subStr.split("\\\\"); // 分割拿到形如 xE9 的16进制数据
        final byte[] byteArr = new byte[sourceArr.length - 1];
        for (int i = 1; i < sourceArr.length; i++) {
            try {
                Integer hexInt = Integer.decode("0" + sourceArr[i]);
                byteArr[i - 1] = hexInt.byteValue();
            } catch (Exception e){
            }
        }
        String newStr = new String(byteArr, "UTF-8");
//        System.out.println(new String(byteArr, "UTF-8"));
        return newStr;
    }

//    public static void main(String[] args) throws Exception{
//        String s = "因为不存在客户问题处理中\ufffd\u0000所有的沟通都必须经过管理上层\ufffd\u0000上层则充当了客\u0000户关系中心\ufffd\u0000并把复杂问题通过垂直指挥链分配给各个职能部门的管理者\ufffd\u0000由于信息必\u0000须经过多个管理层的传递\ufffd\u0000对客户需求的反应就变得迟钝而且容易失真\ufffd\u0000如果问题的解\u0000决与协调需要通过职能部门\ufffd\u0000那么要取得一致同意的决定则还需要加上从产品设计到实\u0000际投产的时间\ufffd\u0000所有的权衡分析都必须通过由\ufffd\u0000管理层领导的委员会来完成。\u0000\u0000传统组织结构中的项目容易延时\ufffd\u0000没有高层管理的长期干预就不可能按时完成所有\u0000的项目和任务\ufffd\u0000不能保证高质量和资源的有效利用\ufffd\u0000从产品设计到实际投产的时间多得\u0000不可估计\ufffd\u0000职能部门的经理首先参与那些对他们自己及其下眉更有利的任务\ufffd\u0000非正式组\u0000\u0000织的需求和正式组织一样具有优先考虑权。";
//        String t = s.replace("\ufffd\u0000","");
//        System.out.println(t);
//        String t = s.substring(2, s.length()-1);
//        String source = "\\xE9\\xBB\\x84\\xE8\\x8A\\xB1\\xE6\\xA2\\xA8\\xE5\\xAE\\xB6\\xE5\\x85\\xB7\\xE8\\xBD\\xAC\\xE8\\xAE\\xA9";
//        String sourceArr[] = t.split("\\\\"); // 分割拿到形如 xE9 的16进制数据
//        byte[] byteArr = new byte[sourceArr.length - 1];
//        for (int i = 1; i < sourceArr.length; i++) {
//            Integer hexInt = Integer.decode("0" + sourceArr[i]);
//            byteArr[i - 1] = hexInt.byteValue();
//        }
//        System.out.println(new String(byteArr, "UTF-8"));
//    }
}
