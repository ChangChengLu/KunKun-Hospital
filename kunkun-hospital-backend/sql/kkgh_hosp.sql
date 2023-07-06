CREATE TABLE `hospital_info` (
            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
            `hospName` varchar(100) DEFAULT NULL COMMENT '医院名称',
            `hospCode` varchar(30) DEFAULT NULL COMMENT '医院编号',
            `apiUrl` varchar(100) DEFAULT NULL COMMENT 'api基础路径',
            `signKey` varchar(50) DEFAULT NULL COMMENT '签名秘钥',
            `contactName` varchar(20) DEFAULT NULL COMMENT '联系人',
            `contactPhone` varchar(11) DEFAULT NULL COMMENT '联系人手机',
            `status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '状态',
            `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
            `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
            `isDelete` tinyint(3) NOT NULL DEFAULT '0' COMMENT '逻辑删除(1:已删除，0:未删除)',
            PRIMARY KEY (`id`),
            UNIQUE KEY `uk_hospCode` (`hospCode`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='医院设置表';