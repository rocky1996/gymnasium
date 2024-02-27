#操作表
DROP TABLE IF EXISTS `gymnasium_operator_record`;
CREATE TABLE `gymnasium_operator_record` (
      `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
      `operator` int(2) NOT NULL COMMENT '当前操作，0:买课,1:续课,2:扣课',
      `user_id` int(10) default 0 NOT NULL COMMENT '会员id',
      `create_time` datetime NOT NULL COMMENT '创建时间',
      `update_time` datetime NOT NULL COMMENT '更新时间',
      PRIMARY key (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;