CREATE DATABASE IF NOT EXISTS `apple` default charset utf8 COLLATE utf8_general_ci;

use apple;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_uuid` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `age` int(11) NULL DEFAULT NULL,
  `sex` int(11) NULL DEFAULT NULL COMMENT '1.男 2女',
  `status` int(11) NULL DEFAULT NULL COMMENT '1.启用 2禁用',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `role` int(11) NULL DEFAULT NULL COMMENT '1.普通用户 2.管理员',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `modify_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_apple_farmer
-- ----------------------------
DROP TABLE IF EXISTS `t_apple_farmer`;
CREATE TABLE `t_apple_farmer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `location` int(11) NULL DEFAULT NULL COMMENT '地址',
  `phone` int(11) NULL DEFAULT NULL COMMENT '电话号码',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态(1待审核 2完成)',
  `create_user` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `create_user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `additional_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;


-- ----------------------------
-- Table structure for t_apple_merchant
-- ----------------------------
DROP TABLE IF EXISTS `t_apple_merchant`;
CREATE TABLE `t_apple_merchant`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `location` int(11) NULL DEFAULT NULL COMMENT '地址',
  `phone` int(11) NULL DEFAULT NULL COMMENT '电话号码',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态(1待审核 2完成)',
  `create_user` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `create_user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `additional_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_apple_labor
-- ----------------------------
DROP TABLE IF EXISTS `t_apple_labor`;
CREATE TABLE `t_apple_labor`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `location` varchar(255) NULL DEFAULT NULL COMMENT '地址',
  `type` int(11) NULL DEFAULT NULL COMMENT '1, 供方 2, 需方',
  `phone` int(11) NULL DEFAULT NULL COMMENT '电话号码',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态(1待审核 2完成)',
  `create_user` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `create_user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `limit_start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `limit_end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `additional_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_apple_supply
-- ----------------------------
DROP TABLE IF EXISTS `t_apple_supply`;
CREATE TABLE `t_apple_supply`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `location` varchar(255) NULL DEFAULT NULL COMMENT '地址',
  `type` int(11) NULL DEFAULT NULL COMMENT '1, 供方 2, 需方',
  `phone` int(11) NULL DEFAULT NULL COMMENT '电话号码',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态(1待审核 2完成)',
  `create_user` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `create_user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `limit_start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `limit_end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `additional_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;


SET FOREIGN_KEY_CHECKS = 1;