package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *路线申请：(RouteApplication)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "RouteApplication")
public class RouteApplication implements Serializable {

    //RouteApplication编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_application_id")
    private Integer route_application_id;
    // 景点名称
    @Basic
    private String name_of_scenic_spot;
    // 申请人
    @Basic
    private Integer applicant;
    // 姓名
    @Basic
    private String full_name;
    // 联系电话
    @Basic
    private String contact_number;
    // 审批状态
    @Basic
    private String approval_status;
    // 路线信息
    @Basic
    private String route_information;
    // 回复信息
    @Basic
    private String reply_message;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
