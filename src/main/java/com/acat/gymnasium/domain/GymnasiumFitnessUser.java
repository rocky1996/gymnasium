package com.acat.gymnasium.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Date;

/**
 * #会员表
 * DROP TABLE IF EXISTS `gymnasium_fitness_user`;
 * CREATE TABLE `gymnasium_fitness_user` (
 *       `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
 *       `username` varchar(20) NOT NULL COMMENT '用户名',
 *       `gender` int(2) default 0 NOT NULL COMMENT '性别,0:男,1:女',
 *       `phone` varchar(12) NOT NULL COMMENT '手机',
 *       `trainer_id` int(10) default 0 NOT NULL COMMENT '对应教练',
 *       `current_course` int(150) default 0 NOT NULL COMMENT '当前课程',
 *       `create_time` datetime NOT NULL COMMENT '创建时间',
 *       `update_time` datetime NOT NULL COMMENT '更新时间',
 *       PRIMARY key (`id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "gymnasium_fitness_user")
public class GymnasiumFitnessUser {

    private static final long serialVersionUID = 2L;

    @javax.persistence.Id
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "phone")
    private String phone;

    @Column(name = "trainer_id")
    private Integer trainerId;

    @Column(name = "current_course")
    private Integer currentCourse;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}
