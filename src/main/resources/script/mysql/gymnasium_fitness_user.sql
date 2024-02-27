#会员表
DROP TABLE IF EXISTS `gymnasium_fitness_user`;
CREATE TABLE `gymnasium_fitness_user` (
      `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
      `username` varchar(20) NOT NULL COMMENT '用户名',
      `gender` int(2) default 0 NOT NULL COMMENT '性别,0:男,1:女',
      `phone` varchar(12) NOT NULL COMMENT '手机',
      `trainer_id` int(10) default 0 NOT NULL COMMENT '对应教练',
      `current_course` int(150) default 0 NOT NULL COMMENT '当前课程',
      `create_time` datetime NOT NULL COMMENT '创建时间',
      `update_time` datetime NOT NULL COMMENT '更新时间',
      PRIMARY key (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;