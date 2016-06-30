CREATE TABLE `u_user` (
`id`  int NOT NULL AUTO_INCREMENT COMMENT '用户id' ,
`imei`  varchar(255) NOT NULL COMMENT '国际移动设备标识' ,
`imsi`  varchar(255) NOT NULL COMMENT '国际移动用户识别码' ,
`os`  varchar(255) NULL DEFAULT NULL COMMENT '手机操作系统' ,
`brand`  varchar(255) NULL DEFAULT NULL COMMENT '手机品牌' ,
`model`  varchar(255) NULL DEFAULT NULL COMMENT '手机号' ,
`screen`  varchar(255) NULL DEFAULT NULL COMMENT '手机分辨率' ,
PRIMARY KEY (`id`)
)
;



