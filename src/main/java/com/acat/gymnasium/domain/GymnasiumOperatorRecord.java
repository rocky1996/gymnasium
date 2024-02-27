package com.acat.gymnasium.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Date;

/**
 * #操作表
 * DROP TABLE IF EXISTS `gymnasium_operator_record`;
 * CREATE TABLE `gymnasium_operator_record` (
 *       `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
 *       `operator` int(2) NOT NULL COMMENT '当前操作，0:买课,1:续课,2:扣课',
 *       `user_id` int(10) default 0 NOT NULL COMMENT '会员id',
 *       `create_time` datetime NOT NULL COMMENT '创建时间',
 *       `update_time` datetime NOT NULL COMMENT '更新时间',
 *       PRIMARY key (`id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "gymnasium_operator_record")
public class GymnasiumOperatorRecord {

    private static final long serialVersionUID = 2L;

    @javax.persistence.Id
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "operator")
    private Integer operator;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}
