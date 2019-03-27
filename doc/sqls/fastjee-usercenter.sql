/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50547
Source Host           : localhost:3306
Source Database       : fastjee-usercenter

Target Server Type    : MYSQL
Target Server Version : 50547
File Encoding         : 65001

Date: 2018-02-27 08:57:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_resource
-- ----------------------------
DROP TABLE IF EXISTS `tb_resource`;
CREATE TABLE `tb_resource` (
  `id` bigint(1) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL COMMENT '资源编码(规则->菜单名:按钮名 || 菜单名)',
  `name` varchar(255) DEFAULT NULL COMMENT '资源名',
  `type` int(11) DEFAULT NULL COMMENT '类型(0菜单,1按钮,2其他)',
  `url` varchar(255) DEFAULT NULL COMMENT '服务端地址',
  `method` varchar(255) DEFAULT NULL COMMENT '请求方式,GET/POST/DELETE/PUT',
  `priority` int(11) DEFAULT NULL COMMENT '优先级(仅作用于当前级别)',
  `path` varchar(255) DEFAULT NULL COMMENT '前端URL',
  `component` varchar(255) DEFAULT NULL COMMENT '组件地址',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `parentId` bigint(1) DEFAULT NULL COMMENT '父级ID',
  `createAt` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifyAt` varchar(255) DEFAULT NULL,
  `modifyDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1024 DEFAULT CHARSET=utf8 COMMENT='资源信息(菜单&按钮)';

-- ----------------------------
-- Records of tb_resource
-- ----------------------------
INSERT INTO `tb_resource` VALUES ('1', 'GET_USER_INFO', '获取用户信息', '2', '/api/uc/user/info/**', 'GET', '2', null, null, null, null, null, null, null, null);
INSERT INTO `tb_resource` VALUES ('2', 'UPDATE_PWD', '修改当前用户密码', '2', '/api/uc/user/updatePwd/**', 'PUT', '2', null, null, null, null, null, null, null, null);
INSERT INTO `tb_resource` VALUES ('3', 'GET_ALL_MENU', '获取所有菜单', '2', '/api/uc/resource/all/**', 'GET', '2', null, null, null, null, null, null, null, null);
INSERT INTO `tb_resource` VALUES ('1000', 'iview-examples', 'iView控件示例', '0', null, 'NONE', '0', '/examples', null, 'social-buffer', null, null, null, null, null);
INSERT INTO `tb_resource` VALUES ('1001', 'text-editor', '富文本编辑器', '0', null, 'NONE', '0', 'text-editor', null, 'compose', '1000', null, null, null, null);
INSERT INTO `tb_resource` VALUES ('1003', 'image-editor', '图片预览编辑', '0', null, 'NONE', '2', 'image-editor', null, 'crop', '1000', null, null, null, null);
INSERT INTO `tb_resource` VALUES ('1004', 'draggable-list', '可拖拽列表', '0', null, 'NONE', '3', 'draggable-list', null, 'arrow-move', null, null, null, null, null);
INSERT INTO `tb_resource` VALUES ('1005', 'area-linkage', '城市级联', '0', null, 'NONE', '4', 'area-linkage', null, 'ios-more', null, null, null, null, null);
INSERT INTO `tb_resource` VALUES ('1006', 'file-upload', '文件上传', '0', null, 'NONE', '5', 'file-upload', null, 'android-upload', null, null, null, null, null);
INSERT INTO `tb_resource` VALUES ('1007', 'arrow-graph-up-right', '数字渐变', '0', null, 'NONE', '6', 'arrow-graph-up-right', null, 'arrow-graph-up-right', null, null, null, null, null);
INSERT INTO `tb_resource` VALUES ('1008', 'split-pane-page', 'split-pane', '0', null, 'NONE', '7', 'split-pane-page', null, 'ios-pause', null, null, null, null, null);
INSERT INTO `tb_resource` VALUES ('1009', 'artical-publish', '文章发布', '0', null, 'NONE', '8', 'artical-publish', null, 'compose', null, null, null, null, null);
INSERT INTO `tb_resource` VALUES ('1010', 'workflow', '工作流', '0', null, 'NONE', '9', 'workflow', null, 'arrow-swap', null, null, null, null, null);
INSERT INTO `tb_resource` VALUES ('1011', 'dragable-table', '表格可拖拽排序', '0', null, 'NONE', '10', 'dragableTable', null, 'arrow-move', null, null, null, null, null);
INSERT INTO `tb_resource` VALUES ('1012', 'editable-table', '可编辑表格', '0', null, 'NONE', '11', 'editableTable', null, 'edit', null, null, null, null, null);
INSERT INTO `tb_resource` VALUES ('1013', 'searchable-table', '可搜索表格', '0', null, 'NONE', '12', 'searchableTable', null, 'search', null, null, null, null, null);
INSERT INTO `tb_resource` VALUES ('1014', 'exportable-table', '表格导出数据', '0', null, 'NONE', '13', 'exportableTable', null, 'code-download', null, null, null, null, null);
INSERT INTO `tb_resource` VALUES ('1015', 'table-to-image', '表格转图片', '0', null, 'NONE', '14', 'table2image', null, 'images', null, null, null, null, null);
INSERT INTO `tb_resource` VALUES ('1016', 'mutative-router', '动态路由', '0', null, 'NONE', '15', 'mutative-router', null, 'link', null, null, null, null, null);
INSERT INTO `tb_resource` VALUES ('1017', 'argument-page', '', '0', null, 'NONE', '16', 'argument-page', null, 'android-send', null, null, null, null, null);
INSERT INTO `tb_resource` VALUES ('1002', 'md-editor', 'Markdown编辑器', '0', null, 'NONE', '1', 'md-editor', null, 'pound', null, null, null, null, null);
INSERT INTO `tb_resource` VALUES ('1019', 'systemManager', '系统管理', '0', null, 'NONE', '0', '/system', null, 'gear-b', null, null, null, null, null);
INSERT INTO `tb_resource` VALUES ('1020', 'userManager', '用户管理', '0', null, 'NONE', '0', 'userManager', null, 'person-stalker', '1019', null, null, null, null);
INSERT INTO `tb_resource` VALUES ('1021', 'roleManager', '角色管理', '0', null, 'NONE', '1', 'roleManager', null, 'social-buffer', '1019', null, null, null, null);
INSERT INTO `tb_resource` VALUES ('1022', 'resourceManager', '资源管理', '0', null, 'NONE', '2', 'resourceManager', null, 'navicon-round', '1019', null, null, null, null);
INSERT INTO `tb_resource` VALUES ('1023', 'sectionManager', '部门管理', '0', null, 'NONE', '3', 'sectionManager', null, 'network', '1019', null, null, null, null);

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` bigint(1) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL COMMENT '角色编码',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `priority` int(11) DEFAULT NULL COMMENT '优先级',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `createAt` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifyAt` varchar(255) DEFAULT NULL,
  `modifyDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色信息';

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', 'ROLE_ADMIN', '超级管理员', '0', null, null, null, null, null);
INSERT INTO `tb_role` VALUES ('2', 'ROLE_USER', '普通用户', '1', null, null, null, null, null);

-- ----------------------------
-- Table structure for tb_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_resource`;
CREATE TABLE `tb_role_resource` (
  `id` bigint(1) NOT NULL AUTO_INCREMENT,
  `roleId` bigint(1) DEFAULT NULL,
  `resourceId` bigint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role_resource
-- ----------------------------
INSERT INTO `tb_role_resource` VALUES ('1', '1', '1');
INSERT INTO `tb_role_resource` VALUES ('2', '1', '2');
INSERT INTO `tb_role_resource` VALUES ('3', '1', '3');
INSERT INTO `tb_role_resource` VALUES ('13', '1', '1004');
INSERT INTO `tb_role_resource` VALUES ('14', '1', '1005');
INSERT INTO `tb_role_resource` VALUES ('15', '1', '1006');
INSERT INTO `tb_role_resource` VALUES ('4', '1', '1000');
INSERT INTO `tb_role_resource` VALUES ('5', '1', '1001');
INSERT INTO `tb_role_resource` VALUES ('6', '1', '1002');
INSERT INTO `tb_role_resource` VALUES ('7', '1', '1003');
INSERT INTO `tb_role_resource` VALUES ('16', '1', '1007');
INSERT INTO `tb_role_resource` VALUES ('17', '1', '1008');
INSERT INTO `tb_role_resource` VALUES ('18', '1', '1009');
INSERT INTO `tb_role_resource` VALUES ('19', '1', '1010');
INSERT INTO `tb_role_resource` VALUES ('20', '1', '1011');
INSERT INTO `tb_role_resource` VALUES ('21', '1', '1012');
INSERT INTO `tb_role_resource` VALUES ('22', '1', '1013');
INSERT INTO `tb_role_resource` VALUES ('23', '1', '1014');
INSERT INTO `tb_role_resource` VALUES ('24', '1', '1015');
INSERT INTO `tb_role_resource` VALUES ('25', '1', '1016');
INSERT INTO `tb_role_resource` VALUES ('26', '1', '1017');
INSERT INTO `tb_role_resource` VALUES ('27', '1', '1019');
INSERT INTO `tb_role_resource` VALUES ('28', '1', '1020');
INSERT INTO `tb_role_resource` VALUES ('29', '1', '1021');
INSERT INTO `tb_role_resource` VALUES ('30', '1', '1022');
INSERT INTO `tb_role_resource` VALUES ('31', '1', '1023');

-- ----------------------------
-- Table structure for tb_section
-- ----------------------------
DROP TABLE IF EXISTS `tb_section`;
CREATE TABLE `tb_section` (
  `id` bigint(1) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL COMMENT '部门编号',
  `name` varchar(255) DEFAULT NULL COMMENT '部门名称',
  `priority` int(11) DEFAULT NULL COMMENT '优先级',
  `parentId` bigint(1) DEFAULT NULL COMMENT '父级ID',
  `createAt` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifyAt` varchar(255) DEFAULT NULL,
  `modifyDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='部门信息';

-- ----------------------------
-- Records of tb_section
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint(1) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱地址',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `description` varchar(255) DEFAULT NULL COMMENT '描述信息',
  `status` varchar(255) DEFAULT NULL COMMENT '用户状态(0启用,1禁用)',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `sectionId` bigint(20) DEFAULT NULL COMMENT '所属部门',
  `createAt` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifyAt` varchar(255) DEFAULT NULL,
  `modifyDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户信息';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', '超级管理员11111111', 'wenzewoo@gmail.com', 'admin', '$2a$10$oLOvc8f3xkroZn/hOc9rmev7P4Su7FDdgElVdJkyMTfX43bi4TJNy', null, '0', 'http://tva4.sinaimg.cn/crop.0.0.1080.1080.180/80637f49jw8fd5zay70epj20u00u00vw.jpg', '1', null, null, '1:admin', '2018-02-26 05:13:48');

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `id` bigint(1) NOT NULL AUTO_INCREMENT,
  `userId` bigint(1) DEFAULT NULL,
  `roleId` bigint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES ('1', '1', '1');
INSERT INTO `tb_user_role` VALUES ('2', '1', '2');
