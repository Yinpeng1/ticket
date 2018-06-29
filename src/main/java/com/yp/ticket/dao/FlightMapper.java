package com.yp.ticket.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yp.ticket.model.FlightModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Mapper
@Component
public interface FlightMapper extends BaseMapper<FlightModel> {

    @Select("select * from qunaer_flight_info where dep_city = #{depCity} and arr_city= #{arrCity} and dep_date = #{depDate} order by ticket_price asc")
    List<FlightModel> getSelectFlight(@Param("depCity")String depCity, @Param("arrCity")String arrCity, @Param("depDate")String depDate);

    @Select("select * from qunaer_flight_info where id = #{id}")
    FlightModel selectById(Integer id);

    @Select("delete from qunaer_flight_info where Date(dep_date) < CURDATE()")
    void delet();
}
