/*
 Navicat Premium Data Transfer

 Source Server         : bootx
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : 127.0.0.1:3306
 Source Schema         : service-goods-center

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 07/07/2021 22:15:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gc_category
-- ----------------------------
DROP TABLE IF EXISTS `gc_category`;
CREATE TABLE `gc_category`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类目名称',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '上级类目id',
  `ordinal` int(11) NULL DEFAULT NULL COMMENT '序号',
  `is_leaf` bit(1) NULL DEFAULT NULL COMMENT '是否叶节点',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `creator` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `last_modifier` bigint(20) NULL DEFAULT NULL,
  `last_modified_time` datetime(6) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `deleted` bit(1) NOT NULL,
  `tid` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '类目' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for gc_category_attr_def
-- ----------------------------
DROP TABLE IF EXISTS `gc_category_attr_def`;
CREATE TABLE `gc_category_attr_def`  (
  `id` bigint(20) NOT NULL,
  `cid` bigint(20) NULL DEFAULT NULL COMMENT '所属类目id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `type` int(11) NOT NULL COMMENT '所属类型',
  `dict_id` bigint(20) NULL DEFAULT NULL COMMENT '数据字典id',
  `is_sku` bit(1) NOT NULL COMMENT '是否 SKU 属性',
  `is_display` bit(1) NOT NULL COMMENT '是否显示属性',
  `is_search` bit(1) NOT NULL COMMENT '是否搜索属性',
  `is_required` bit(1) NOT NULL COMMENT '是否必须',
  `is_multiple` bit(1) NOT NULL COMMENT '是否多选',
  `ordinal` int(11) NOT NULL COMMENT '序号',
  `state` int(11) NOT NULL COMMENT '状态',
  `field_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '属性名称',
  `field_length` int(11) NULL DEFAULT NULL COMMENT '属性长度',
  `field_point_length` int(11) NULL DEFAULT NULL COMMENT '小数点长度',
  `field_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '属性类型',
  `field_default` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字段默认值',
  `query_compare_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字段查询时的比较方式',
  `is_key` bit(1) NULL DEFAULT NULL COMMENT '是否主键',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `target_type` int(11) NOT NULL COMMENT '目标类型 sku/spu',
  `creator` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `last_modifier` bigint(20) NULL DEFAULT NULL,
  `last_modified_time` datetime(6) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `deleted` bit(1) NOT NULL,
  `tid` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '类目定义' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for gc_goods
-- ----------------------------
DROP TABLE IF EXISTS `gc_goods`;
CREATE TABLE `gc_goods`  (
  `id` bigint(20) NOT NULL,
  `cid` bigint(20) NULL DEFAULT NULL COMMENT '类目id',
  `cname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类目名称',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `packing` bit(1) NOT NULL COMMENT '是否是打包品',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `addition` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附加参数',
  `state` int(11) NOT NULL COMMENT '状态',
  `banner_uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `display_price` decimal(19, 2) NULL DEFAULT NULL,
  `goods_type` int(11) NULL DEFAULT NULL,
  `main_uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sale_off_time` datetime(6) NULL DEFAULT NULL,
  `sale_on_time` datetime(6) NULL DEFAULT NULL,
  `sale_state` int(11) NULL DEFAULT NULL,
  `shop_id` bigint(20) NULL DEFAULT NULL,
  `attr_def_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `attr_value_displays` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `attr_values` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `display_lower_price` decimal(19, 2) NULL DEFAULT NULL,
  `display_upper_price` decimal(19, 2) NULL DEFAULT NULL,
  `creator` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `last_modifier` bigint(20) NULL DEFAULT NULL,
  `last_modified_time` datetime(6) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `deleted` bit(1) NOT NULL,
  `tid` bigint(20) NULL DEFAULT NULL,
  `out_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品spu' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for gc_goods_attr
-- ----------------------------
DROP TABLE IF EXISTS `gc_goods_attr`;
CREATE TABLE `gc_goods_attr`  (
  `id` bigint(20) NOT NULL,
  `cid` bigint(20) NULL DEFAULT NULL COMMENT '类目 id',
  `goods_id` bigint(20) NULL DEFAULT NULL COMMENT '商品 id',
  `attr_def_id` bigint(20) NULL DEFAULT NULL COMMENT '属性定义 id',
  `attr_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '属性值',
  `attr_value_display` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '显示值',
  `tid` bigint(20) NOT NULL,
  `attr_values_display` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `attr_value_displays` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for gc_goods_packing
-- ----------------------------
DROP TABLE IF EXISTS `gc_goods_packing`;
CREATE TABLE `gc_goods_packing`  (
  `id` bigint(20) NOT NULL,
  `goods_id` bigint(20) NULL DEFAULT NULL COMMENT '打包品',
  `packed_goods_id` bigint(20) NULL DEFAULT NULL COMMENT '被打包品',
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator` bigint(20) NULL DEFAULT NULL,
  `last_modified_time` datetime(6) NULL DEFAULT NULL,
  `last_modifier` bigint(20) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `deleted` bit(1) NOT NULL,
  `tid` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品打包关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for gc_goods_sku
-- ----------------------------
DROP TABLE IF EXISTS `gc_goods_sku`;
CREATE TABLE `gc_goods_sku`  (
  `id` bigint(20) NOT NULL,
  `cid` bigint(20) NULL DEFAULT NULL COMMENT '所属类目id',
  `goods_id` bigint(20) NULL DEFAULT NULL COMMENT '所属商品 id',
  `shop_id` bigint(20) NULL DEFAULT NULL COMMENT '所属商户',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '外部编码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'SKU 名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `packing` bit(1) NOT NULL COMMENT '是否打包品',
  `addition` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附加信息',
  `business_id` bigint(20) NULL DEFAULT NULL COMMENT '业务id',
  `attr_def_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成此商品 SKU 的属性定义的 id 拼接串',
  `attr_values` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '属性值id拼接串',
  `attr_value_displays` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '示值拼接串',
  `is_unlimited` bit(1) NOT NULL COMMENT '是否无限库存',
  `capacity` int(11) NULL DEFAULT NULL COMMENT '初始库存',
  `locked` int(11) NOT NULL COMMENT '预占库存',
  `sold` int(11) NOT NULL COMMENT '已用库存',
  `available` int(11) NULL DEFAULT NULL COMMENT '可用库存',
  `sale_state` int(11) NULL DEFAULT NULL COMMENT '销售状态',
  `sale_off_time` datetime(6) NULL DEFAULT NULL COMMENT '上架时间',
  `sale_on_time` datetime(6) NULL DEFAULT NULL COMMENT '下架时间',
  `price` decimal(19, 2) NULL DEFAULT NULL COMMENT '价格',
  `state` int(11) NOT NULL COMMENT '状态',
  `creator` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `last_modifier` bigint(20) NULL DEFAULT NULL,
  `last_modified_time` datetime(6) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `deleted` bit(1) NOT NULL,
  `tid` bigint(20) NULL DEFAULT NULL,
  `out_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品sku' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for gc_goods_sku_attr
-- ----------------------------
DROP TABLE IF EXISTS `gc_goods_sku_attr`;
CREATE TABLE `gc_goods_sku_attr`  (
  `id` bigint(20) NOT NULL,
  `cid` bigint(20) NULL DEFAULT NULL COMMENT '类目 id',
  `goods_id` bigint(20) NULL DEFAULT NULL COMMENT '商品 id',
  `sku_id` bigint(20) NULL DEFAULT NULL COMMENT 'SKU id',
  `attr_def_id` bigint(20) NULL DEFAULT NULL COMMENT '属性定义 id',
  `attr_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '属性值',
  `attr_value_display` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '显示值',
  `tid` bigint(20) NOT NULL,
  `attr_values_display` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `attr_value_displays` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'sku属性' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for gc_goods_sku_packing
-- ----------------------------
DROP TABLE IF EXISTS `gc_goods_sku_packing`;
CREATE TABLE `gc_goods_sku_packing`  (
  `id` bigint(20) NOT NULL,
  `goods_id` bigint(20) NULL DEFAULT NULL COMMENT '打包品',
  `packed_goods_id` bigint(20) NULL DEFAULT NULL COMMENT '被打包品',
  `goods_sku_id` bigint(20) NULL DEFAULT NULL COMMENT '打包SKU',
  `packed_sku_id` bigint(20) NULL DEFAULT NULL COMMENT '被打包SKU',
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator` bigint(20) NULL DEFAULT NULL,
  `last_modified_time` datetime(6) NULL DEFAULT NULL,
  `last_modifier` bigint(20) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `deleted` bit(1) NOT NULL,
  `tid` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'sku打包关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for gc_sku_price_type
-- ----------------------------
DROP TABLE IF EXISTS `gc_sku_price_type`;
CREATE TABLE `gc_sku_price_type`  (
  `id` bigint(20) NOT NULL,
  `tid` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator` bigint(20) NULL DEFAULT NULL,
  `deleted` bit(1) NOT NULL,
  `last_modified_time` datetime(6) NULL DEFAULT NULL,
  `last_modifier` bigint(20) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `promotion_price` decimal(19, 2) NULL DEFAULT NULL,
  `sku_id` bigint(20) NULL DEFAULT NULL,
  `sku_price_type_id` bigint(20) NULL DEFAULT NULL,
  `state` int(11) NOT NULL,
  `vip_price` decimal(19, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
