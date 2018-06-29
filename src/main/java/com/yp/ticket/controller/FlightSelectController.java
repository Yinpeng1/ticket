package com.yp.ticket.controller;

import com.yp.ticket.result.ResultEntity;
import com.yp.ticket.result.Results;
import com.yp.ticket.model.FlightModel;
import com.yp.ticket.service.FlightService;
import com.yp.ticket.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
public class FlightSelectController {

    @Autowired
    private FlightService flightService;

    /**
     * 查询国内航班
     * @param depCity
     * @param arrCity
     * @param depDate
     * @return
     */
    @RequestMapping(value = "/get_zn_flight")
    public ResultEntity getZNFlight(String depCity, String arrCity, String depDate){
        if (StringUtils.isEmpty(depCity) || StringUtils.isEmpty(arrCity) || StringUtils.isEmpty(depDate)) {
            return Results.newErrorResultEntity(800001,"参数错误");
        }
        List<FlightModel> flightModels = null;
        try {
            flightModels = flightService.getZNFlight(depCity,arrCity,depDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Results.newListResultEntity(flightModels);
    }

    /**
     * 查询国际航班
     * @param depCity
     * @param arrCity
     * @param depDate
     * @return
     */
    @RequestMapping(value = "/get_inter_flight")
    public ResultEntity getInterFlight(String depCity, String arrCity, String depDate){
        if (StringUtils.isEmpty(depCity) || StringUtils.isEmpty(arrCity) || StringUtils.isEmpty(depDate)) {
            return Results.newErrorResultEntity(800001,"参数错误");
        }
        List<FlightModel> flightModels = null;
        try {
            flightModels = flightService.getInterFlight(depCity,arrCity,depDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Results.newListResultEntity(flightModels);
    }

    /**
     * 英文翻译
     * @param content
     * @return
     */
    @RequestMapping(value = "/translate")
    public ResultEntity getFanYiResult(String content){
        if (StringUtils.isEmpty(content)) {
            return Results.newErrorResultEntity(800001,"参数错误");
        }
        String result = null;
        try {
            result = flightService.translate(content);
        } catch (Exception e){
            e.printStackTrace();
        }
        return Results.newSingleResultEntity(result);
    }

    @PostMapping(value = "/identityImg")
    public ResultEntity translateByImage(@RequestParam("image") MultipartFile image){
        String localPath = "C:/img";
        if (FileUtils.upload(image, localPath, image.getOriginalFilename())){
            // 上传成功
            try {
                 final String result = flightService.identyImg(localPath + "/" + image.getOriginalFilename());
                 return Results.newSingleResultEntity(result);
            } catch (Exception e){
                e.printStackTrace();
                return Results.newErrorResultEntity(800002,"识别错误");
            }
        }else {
            return Results.newErrorResultEntity(800002,"识别错误");
        }
    }
}
