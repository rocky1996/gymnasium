#教練表
DROP TABLE IF EXISTS `gymnasium_trainer`;
CREATE TABLE `gymnasium_trainer` (
     `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
     `username` varchar(20) NOT NULL COMMENT '用户名',
     `password` varchar(20) NOT NULL COMMENT '密码',
     `trainer_nickname` varchar(20) NOT NULL COMMENT '教练昵称',
     `trainer_introduction` varchar(200) NOT NULL COMMENT '教练介绍',
     `role` int(2) NOT NULL default 1 COMMENT '0:管理员,1:普通教练',
     `create_time` datetime NOT NULL COMMENT '创建时间',
     `update_time` datetime NOT NULL COMMENT '更新时间',
     PRIMARY key (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;