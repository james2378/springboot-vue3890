package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *景点信息：(ScenicSpotInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ScenicSpotInformation")
public class ScenicSpotInformation implements Serializable {

    //ScenicSpotInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scenic_spot_information_id")
    private Integer scenic_spot_information_id;
    // 景点名称
    @Basic
    private String name_of_scenic_spot;
    // 景点类型
    @Basic
    private String scenic_spot_type;
    // 开放时间
    @Basic
    private String opening_hours;
    // 景点电话
    @Basic
    private String scenic_spot_telephone;
    // 景点地址
    @Basic
    private String scenic_spot_address;
    // 封面图片
    @Basic
    private String cover_photo_;
    // 推荐酒店
    @Basic
    private String recommended_hotel;
    // 路线推荐
    @Basic
    private String route_recommendation;
    // 景点简介
    @Basic
    private String introduction_to_scenic_spots;
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
