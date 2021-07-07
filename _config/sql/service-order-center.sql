/*
 Navicat Premium Data Transfer

 Source Server         : bootx
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : 127.0.0.1:3306
 Source Schema         : service-order-center

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 07/07/2021 22:16:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oc_order
-- ----------------------------
DROP TABLE IF EXISTS `oc_order`;
CREATE TABLE `oc_order`  (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `contact_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `contact_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系邮箱',
  `channel_id` bigint(20) NULL DEFAULT NULL COMMENT '渠道',
  `pay_time` datetime(6) NULL DEFAULT NULL COMMENT '支付时间',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '购买用户id',
  `status` int(11) NOT NULL COMMENT '状态',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型',
  `total_amount` decimal(19, 2) NULL DEFAULT NULL COMMENT '总金额',
  `pay_amount` decimal(19, 2) NULL DEFAULT NULL COMMENT '实付金额',
  `coupon_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所用优惠券ids',
  `address_info` json NULL COMMENT '地址参数',
  `invoice_info` json NULL COMMENT '发票参数',
  `addition` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附加参数',
  `device_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备id',
  `source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '来源',
  `business_id` bigint(20) NULL DEFAULT NULL COMMENT '业务id',
  `creator` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `last_modified_time` datetime(6) NULL DEFAULT NULL,
  `last_modifier` bigint(20) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `deleted` bit(1) NOT NULL,
  `tid` bigint(20) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oc_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `oc_order_detail`;
CREATE TABLE `oc_order_detail`  (
  `id` bigint(20) NOT NULL,
  `active_id` bigint(20) NULL DEFAULT NULL COMMENT '活动id',
  `shop_id` bigint(20) NULL DEFAULT NULL COMMENT '店铺id',
  `order_id` bigint(20) NULL DEFAULT NULL COMMENT '订单id',
  `category_id` bigint(20) NULL DEFAULT NULL COMMENT '类目id',
  `goods_id` bigint(20) NULL DEFAULT NULL COMMENT '商品id',
  `sku_id` bigint(20) NULL DEFAULT NULL COMMENT '库存id',
  `goods_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `goods_price` decimal(19, 2) NULL DEFAULT NULL COMMENT '商品价格',
  `num` int(11) NOT NULL COMMENT '数量',
  `total_amount` decimal(19, 2) NULL DEFAULT NULL COMMENT '总价',
  `pay_amount` decimal(19, 2) NULL DEFAULT NULL COMMENT '支付价',
  `state` int(11) NULL DEFAULT NULL COMMENT '状态',
  `addition` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附加参数',
  `creator` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `last_modifier` bigint(20) NULL DEFAULT NULL,
  `last_modified_time` datetime(6) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `deleted` bit(1) NOT NULL,
  `tid` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单明细' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oc_order_strategy_mapping
-- ----------------------------
DROP TABLE IF EXISTS `oc_order_strategy_mapping`;
CREATE TABLE `oc_order_strategy_mapping`  (
  `id` bigint(20) NOT NULL,
  `order_id` bigint(20) NULL DEFAULT NULL COMMENT '订单id',
  `order_detail_id` bigint(20) NULL DEFAULT NULL COMMENT '明细id',
  `strategy_id` bigint(20) NULL DEFAULT NULL COMMENT '策略ID',
  `strategy_type` int(11) NULL DEFAULT NULL COMMENT '策略类型(普通/优惠券ID)',
  `strategy_register_id` bigint(20) NULL DEFAULT NULL COMMENT '策略注册ID',
  `price_change` decimal(19, 2) NULL DEFAULT NULL COMMENT '价格变动',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `coupon_id` bigint(20) NULL DEFAULT NULL,
  `creator` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `last_modifier` bigint(20) NULL DEFAULT NULL,
  `last_modified_time` datetime(6) NULL DEFAULT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) NOT NULL,
  `tid` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单策略映射' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oc_swap_order
-- ----------------------------
DROP TABLE IF EXISTS `oc_swap_order`;
CREATE TABLE `oc_swap_order`  (
  `id` bigint(20) NOT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator` bigint(20) NULL DEFAULT NULL,
  `deleted` bit(1) NOT NULL,
  `last_modified_time` datetime(6) NULL DEFAULT NULL,
  `last_modifier` bigint(20) NULL DEFAULT NULL,
  `tid` bigint(20) NULL DEFAULT NULL,
  `business_id` bigint(20) NULL DEFAULT NULL,
  `channel_id` bigint(20) NULL DEFAULT NULL,
  `device_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `last_order_id` bigint(20) NULL DEFAULT NULL,
  `origin_order_id` bigint(20) NULL DEFAULT NULL,
  `pay_amount` decimal(19, 2) NULL DEFAULT NULL,
  `pay_time` datetime(6) NULL DEFAULT NULL,
  `state` int(11) NOT NULL,
  `version` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKrkeu9v5trh3iqe71y9eyq1q0k`(`origin_order_id`) USING BTREE,
  CONSTRAINT `oc_swap_order_ibfk_1` FOREIGN KEY (`origin_order_id`) REFERENCES `oc_order` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '换货单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oc_swap_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `oc_swap_order_detail`;
CREATE TABLE `oc_swap_order_detail`  (
  `id` bigint(20) NOT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator` bigint(20) NULL DEFAULT NULL,
  `deleted` bit(1) NOT NULL,
  `last_modified_time` datetime(6) NULL DEFAULT NULL,
  `last_modifier` bigint(20) NULL DEFAULT NULL,
  `tid` bigint(20) NULL DEFAULT NULL,
  `last_detail_id` bigint(20) NULL DEFAULT NULL,
  `last_sku_id` bigint(20) NULL DEFAULT NULL,
  `order_id` bigint(20) NULL DEFAULT NULL,
  `origin_detail_id` bigint(20) NULL DEFAULT NULL,
  `pay_amount` decimal(19, 2) NULL DEFAULT NULL,
  `sku_id` bigint(20) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKiuo9rwpivhxa4c0fx2ai7uw5n`(`order_id`) USING BTREE,
  CONSTRAINT `oc_swap_order_detail_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `oc_swap_order` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '换货单明细' ROW_FORMAT = Dynamic;

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
