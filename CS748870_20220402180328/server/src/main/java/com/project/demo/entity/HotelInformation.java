package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *酒店信息：(HotelInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "HotelInformation")
public class HotelInformation implements Serializable {

    //HotelInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_information_id")
    private Integer hotel_information_id;
    // 酒店名称
    @Basic
    private String hotel_name;
    // 酒店电话
    @Basic
    private String hotel_telephone;
    // 封面图片
    @Basic
    private String cover_photo_;
    // 附近景点
    @Basic
    private String nearby_scenic_spots;
    // 酒店地址
    @Basic
    private String hotel_address;
    // 酒店简介
    @Basic
    private String hotel_profile;
    // 点击数
    @Basic
    private Integer hits;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
