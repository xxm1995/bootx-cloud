/*
 Navicat Premium Data Transfer

 Source Server         : bootx
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : 127.0.0.1:3306
 Source Schema         : service-sales-center

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 07/07/2021 22:16:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sc_activity
-- ----------------------------
DROP TABLE IF EXISTS `sc_activity`;
CREATE TABLE `sc_activity`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'name',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `strategy_register_id` bigint(20) NOT NULL COMMENT '策略注册id',
  `activity_mutual` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '活动互斥',
  `start_time` datetime(6) NOT NULL COMMENT '有效开始时间',
  `end_time` datetime(6) NOT NULL COMMENT '有效结束时间',
  `creator` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `last_modifier` bigint(20) NULL DEFAULT NULL,
  `last_modified_time` datetime(6) NULL DEFAULT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) NOT NULL,
  `tid` bigint(20) NULL DEFAULT NULL,
  `strategy_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '优惠活动' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sc_check_rule
-- ----------------------------
DROP TABLE IF EXISTS `sc_check_rule`;
CREATE TABLE `sc_check_rule`  (
  `id` bigint(20) NOT NULL,
  `strategy_register_id` bigint(20) NOT NULL COMMENT '关联策略注册id',
  `register_type` int(6) NOT NULL COMMENT '策略类型 策略/优惠券',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '规则名称',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '规则code',
  `rule_type` int(11) NOT NULL COMMENT '规则类型',
  `strategy_id` bigint(20) NULL DEFAULT NULL COMMENT '对应策略id',
  `addition` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '附加对象 json',
  `priority` int(11) NULL DEFAULT NULL COMMENT '优先级',
  `creator` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `last_modifier` bigint(20) NULL DEFAULT NULL,
  `last_modified_time` datetime(6) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `deleted` bit(1) NOT NULL,
  `tid` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '检查规则' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sc_coupon
-- ----------------------------
DROP TABLE IF EXISTS `sc_coupon`;
CREATE TABLE `sc_coupon`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `template_id` bigint(20) NULL DEFAULT NULL COMMENT '模板id',
  `strategy_register_id` bigint(20) NULL DEFAULT NULL COMMENT '策略注册id',
  `start_time` datetime(6) NULL DEFAULT NULL COMMENT '有效开始时间',
  `end_time` datetime(6) NULL DEFAULT NULL COMMENT '有效结束时间',
  `status` int(6) NOT NULL COMMENT '状态',
  `obtain_time` datetime(6) NULL DEFAULT NULL COMMENT '领取时间',
  `used_time` datetime(6) NULL DEFAULT NULL COMMENT '使用时间',
  `order_id` bigint(20) NULL DEFAULT NULL COMMENT '所用订单Id',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `creator` bigint(20) NULL DEFAULT NULL,
  `last_modifier` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `last_modified_time` datetime(6) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `deleted` bit(1) NOT NULL,
  `tid` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKi3jmbif27olgivb918r8jbv9j`(`template_id`) USING BTREE,
  CONSTRAINT `FKi3jmbif27olgivb918r8jbv9j` FOREIGN KEY (`template_id`) REFERENCES `sc_coupon_template` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '优惠券' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sc_coupon_template
-- ----------------------------
DROP TABLE IF EXISTS `sc_coupon_template`;
CREATE TABLE `sc_coupon_template`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `strategy_register_id` bigint(20) NOT NULL COMMENT '策略注册id',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `num` int(11) NOT NULL COMMENT '数量',
  `channel_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '渠道',
  `activity_mutual` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '活动互斥',
  `coupon_mutual` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '优惠券互斥',
  `effective_type` int(8) NOT NULL COMMENT '有效类型',
  `time_type` int(8) NULL DEFAULT NULL COMMENT '时间类型',
  `effective_time` bigint(20) NULL DEFAULT NULL COMMENT '有效时间',
  `start_time` datetime(6) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(6) NULL DEFAULT NULL COMMENT '结束时间',
  `creator` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `last_modifier` bigint(20) NULL DEFAULT NULL,
  `last_modified_time` datetime(6) NULL DEFAULT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) NOT NULL,
  `tid` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '优惠券模板' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sc_match_rule
-- ----------------------------
DROP TABLE IF EXISTS `sc_match_rule`;
CREATE TABLE `sc_match_rule`  (
  `id` bigint(20) NOT NULL,
  `strategy_register_id` bigint(20) NOT NULL COMMENT '关联策略注册id',
  `register_type` int(6) NOT NULL COMMENT '策略类型 策略/优惠券',
  `feature_type` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '特征类型(sku/spu/shop)',
  `feature_point` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '特征点',
  `match_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '匹配类型',
  `creator` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `last_modifier` bigint(20) NULL DEFAULT NULL,
  `last_modified_time` datetime(6) NULL DEFAULT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) NOT NULL,
  `tid` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '匹配检查' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sc_strategy
-- ----------------------------
DROP TABLE IF EXISTS `sc_strategy`;
CREATE TABLE `sc_strategy`  (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '唯一编码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `engine_type` int(1) NOT NULL COMMENT '引擎类型, java/js/groovy',
  `target_type` int(11) NOT NULL COMMENT '目标类型',
  `rule_script` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '规则脚本',
  `show` int(1) NULL DEFAULT NULL COMMENT '是否展示 0不展示，1展示(default)',
  `state` int(1) NOT NULL COMMENT '0 可用 1 不可用',
  `creator` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `last_modifier` bigint(20) NULL DEFAULT NULL,
  `last_modified_time` datetime(6) NULL DEFAULT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) NOT NULL,
  `tid` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '策略' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sc_strategy_check_rule
-- ----------------------------
DROP TABLE IF EXISTS `sc_strategy_check_rule`;
CREATE TABLE `sc_strategy_check_rule`  (
  `id` bigint(20) NOT NULL,
  `register_id` bigint(20) NULL DEFAULT NULL COMMENT '模板id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '规则名称',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '规则code',
  `type` int(11) NOT NULL COMMENT '类型',
  `addition` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附加参数 json',
  `creator` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `last_modifier` bigint(20) NULL DEFAULT NULL,
  `last_modified_time` datetime(6) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `deleted` bit(1) NOT NULL,
  `tid` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '策略规则检查' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sc_strategy_config
-- ----------------------------
DROP TABLE IF EXISTS `sc_strategy_config`;
CREATE TABLE `sc_strategy_config`  (
  `id` bigint(20) NOT NULL,
  `strategy_id` bigint(20) NULL DEFAULT NULL COMMENT '策略id',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数类型',
  `key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '方法参数名',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数名称',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `creator` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `last_modifier` bigint(20) NULL DEFAULT NULL,
  `last_modified_time` datetime(6) NULL DEFAULT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) NOT NULL,
  `tid` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '策略参数定义' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sc_strategy_config_value
-- ----------------------------
DROP TABLE IF EXISTS `sc_strategy_config_value`;
CREATE TABLE `sc_strategy_config_value`  (
  `id` bigint(20) NOT NULL,
  `strategy_id` bigint(20) NULL DEFAULT NULL COMMENT '策略id',
  `strategy_register_id` bigint(20) NULL DEFAULT NULL COMMENT '策略注册id',
  `strategy_config_id` bigint(20) NULL DEFAULT NULL COMMENT '策略定义id',
  `key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '策略参数key',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数值',
  `creator` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `last_modifier` bigint(20) NULL DEFAULT NULL,
  `last_modified_time` datetime(6) NULL DEFAULT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) NOT NULL,
  `tid` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK8h3jh6yj9o9bgb0gd2noht1mw`(`strategy_config_id`) USING BTREE,
  CONSTRAINT `FK8h3jh6yj9o9bgb0gd2noht1mw` FOREIGN KEY (`strategy_config_id`) REFERENCES `sc_strategy_config` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '策略配置参数' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sc_strategy_register
-- ----------------------------
DROP TABLE IF EXISTS `sc_strategy_register`;
CREATE TABLE `sc_strategy_register`  (
  `id` bigint(20) NOT NULL,
  `strategy_id` bigint(20) NULL DEFAULT NULL COMMENT '策略id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '策略注册名称',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `strategy_type` int(11) NULL DEFAULT NULL COMMENT '策略类型(1活动/2优惠券)',
  `creator` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `last_modifier` bigint(20) NULL DEFAULT NULL,
  `last_modified_time` datetime(6) NULL DEFAULT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) NOT NULL,
  `tid` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK44aox50070g4wqwabx7l75twv`(`strategy_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '策略注册' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`  (
  `branch_id` bigint(20) NOT NULL COMMENT 'branch transaction id',
  `xid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'global transaction id',
  `context` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'undo_log context,such as serialization',
  `rollback_info` longblob NOT NULL COMMENT 'rollback info',
  `log_status` int(11) NOT NULL COMMENT '0:normal status,1:defense status',
  `log_created` datetime(6) NOT NULL COMMENT 'create datetime',
  `log_modified` datetime(6) NOT NULL COMMENT 'modify datetime',
  UNIQUE INDEX `ux_undo_log`(`xid`, `branch_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'AT transaction mode undo table' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
