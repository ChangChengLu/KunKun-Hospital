-- 医院信息表
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

-- 用户表
create table if not exists user
(
    id           bigint auto_increment comment 'id' primary key,
    userAccount  varchar(256)                           not null comment '账号',
    userPassword varchar(512)                           not null comment '密码',
    phone        varchar(11)                            not null default '' comment '手机号',
    unionId      varchar(256)                           null comment '微信开放平台id',
    mpOpenId     varchar(256)                           null comment '公众号openId',
    userName     varchar(256)                           null comment '用户姓名',
    nickName     varchar(256)                           null comment '用户昵称',
    userAvatar   varchar(1024)                          null comment '用户头像',
    userProfile  varchar(512)                           null comment '用户简介',
    certificatesType varchar(3) DEFAULT NULL COMMENT '证件类型',
    certificatesNo varchar(30) DEFAULT NULL COMMENT '证件编号',
    certificatesUrl varchar(200) DEFAULT NULL COMMENT '证件路径',
    authStatus tinyint(3) NOT NULL DEFAULT '0' COMMENT '认证状态（0：未认证 1：认证中 2：认证成功 -1：认证失败）',
    userStatus tinyint(3) NOT NULL DEFAULT '1' COMMENT '状态（0：锁定 1：正常）',
    userRole     varchar(256) default 'user'            not null comment '用户角色：user/admin/ban',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除',
    index idx_unionId (unionId)
) comment '用户' collate = utf8mb4_unicode_ci;