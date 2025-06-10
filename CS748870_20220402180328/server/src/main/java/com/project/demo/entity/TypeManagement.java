package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *类型管理：(TypeManagement)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "TypeManagement")
public class TypeManagement implements Serializable {

    //TypeManagement编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_management_id")
    private Integer type_management_id;
    // 景点类型
    @Basic
    private String scenic_spot_type;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
