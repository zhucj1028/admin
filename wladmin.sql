/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : localhost:3306
 Source Schema         : wladmin

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 06/04/2021 13:33:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作人名称',
  `operation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作内容',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 106 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES (1, 'admin', '删除用户', '2021-03-27 15:32:44');
INSERT INTO `log` VALUES (2, 'test', '日志管理', '2021-03-27 15:35:17');
INSERT INTO `log` VALUES (3, 'admin', '查询用户', '2021-03-27 15:35:37');
INSERT INTO `log` VALUES (4, NULL, '返回用户列表', '2021-03-27 16:36:35');
INSERT INTO `log` VALUES (5, NULL, '查看登录方式', '2021-03-27 16:36:55');
INSERT INTO `log` VALUES (6, NULL, '返回用户列表', '2021-03-27 16:41:44');
INSERT INTO `log` VALUES (7, NULL, '查看登录方式', '2021-03-27 16:48:47');
INSERT INTO `log` VALUES (8, NULL, '返回用户列表', '2021-03-27 16:48:53');
INSERT INTO `log` VALUES (9, NULL, '返回用户列表', '2021-03-27 16:48:56');
INSERT INTO `log` VALUES (10, NULL, '返回用户列表', '2021-03-27 16:49:38');
INSERT INTO `log` VALUES (11, NULL, '返回用户列表', '2021-03-27 16:49:40');
INSERT INTO `log` VALUES (12, NULL, '返回用户列表', '2021-03-27 16:49:41');
INSERT INTO `log` VALUES (13, NULL, '返回用户列表', '2021-03-27 16:49:41');
INSERT INTO `log` VALUES (14, NULL, '返回用户列表', '2021-03-27 16:50:06');
INSERT INTO `log` VALUES (15, NULL, '查看登录方式', '2021-03-27 20:41:03');
INSERT INTO `log` VALUES (16, NULL, '查看登录方式', '2021-03-28 18:31:21');
INSERT INTO `log` VALUES (17, NULL, '查看日志', '2021-03-28 18:31:22');
INSERT INTO `log` VALUES (18, NULL, '查看日志', '2021-03-28 18:31:38');
INSERT INTO `log` VALUES (19, NULL, '查看日志', '2021-03-28 18:31:42');
INSERT INTO `log` VALUES (20, NULL, '查看登录方式', '2021-03-28 18:32:07');
INSERT INTO `log` VALUES (21, NULL, '查看日志', '2021-03-28 18:32:49');
INSERT INTO `log` VALUES (22, NULL, '查看日志', '2021-03-28 18:56:59');
INSERT INTO `log` VALUES (23, NULL, '查看登录方式', '2021-03-28 18:57:06');
INSERT INTO `log` VALUES (24, NULL, '查看日志', '2021-03-28 18:57:29');
INSERT INTO `log` VALUES (25, NULL, '查看登录方式', '2021-03-28 18:58:59');
INSERT INTO `log` VALUES (26, NULL, '查看日志', '2021-03-28 18:59:00');
INSERT INTO `log` VALUES (27, NULL, '查看日志', '2021-03-28 19:06:16');
INSERT INTO `log` VALUES (28, NULL, '查看登录方式', '2021-03-28 19:13:46');
INSERT INTO `log` VALUES (29, NULL, '查看日志', '2021-03-28 19:13:48');
INSERT INTO `log` VALUES (30, NULL, '查看登录方式', '2021-03-28 19:14:49');
INSERT INTO `log` VALUES (31, NULL, '查看日志', '2021-03-28 19:14:50');
INSERT INTO `log` VALUES (32, NULL, '查看登录方式', '2021-03-28 19:16:55');
INSERT INTO `log` VALUES (33, NULL, '查看日志', '2021-03-28 19:16:56');
INSERT INTO `log` VALUES (34, NULL, '查看日志', '2021-03-28 19:19:59');
INSERT INTO `log` VALUES (35, NULL, '查看日志', '2021-03-28 19:26:09');
INSERT INTO `log` VALUES (36, NULL, '查看日志', '2021-03-28 19:28:44');
INSERT INTO `log` VALUES (37, NULL, '查看日志', '2021-03-28 19:36:14');
INSERT INTO `log` VALUES (38, NULL, '查看日志', '2021-03-28 19:39:14');
INSERT INTO `log` VALUES (39, NULL, '查看日志', '2021-03-28 19:39:15');
INSERT INTO `log` VALUES (40, NULL, '查看登录方式', '2021-03-28 19:41:56');
INSERT INTO `log` VALUES (41, NULL, '查看日志', '2021-03-28 19:41:57');
INSERT INTO `log` VALUES (42, NULL, '查看登录方式', '2021-03-28 20:21:20');
INSERT INTO `log` VALUES (43, NULL, '查询菜单列表', '2021-03-28 20:21:23');
INSERT INTO `log` VALUES (44, NULL, '查询用户列表', '2021-03-28 20:21:27');
INSERT INTO `log` VALUES (45, NULL, '返回用户列表', '2021-03-28 20:21:29');
INSERT INTO `log` VALUES (46, NULL, '返回所有角色', '2021-03-28 20:21:29');
INSERT INTO `log` VALUES (47, NULL, '查询用户列表', '2021-03-28 20:24:33');
INSERT INTO `log` VALUES (48, NULL, '查看角色详情', '2021-03-28 20:24:36');
INSERT INTO `log` VALUES (49, NULL, '获取角色对应的资源', '2021-03-28 20:24:36');
INSERT INTO `log` VALUES (50, NULL, '查看角色详情', '2021-03-28 20:24:38');
INSERT INTO `log` VALUES (51, NULL, '获取角色对应的资源', '2021-03-28 20:24:38');
INSERT INTO `log` VALUES (52, NULL, '获取角色对应的资源', '2021-03-28 20:24:46');
INSERT INTO `log` VALUES (53, NULL, '查看角色详情', '2021-03-28 20:24:47');
INSERT INTO `log` VALUES (54, NULL, '查看角色详情', '2021-03-28 20:24:52');
INSERT INTO `log` VALUES (55, NULL, '获取角色对应的资源', '2021-03-28 20:24:53');
INSERT INTO `log` VALUES (56, NULL, '查询用户列表', '2021-03-28 20:29:56');
INSERT INTO `log` VALUES (57, NULL, '查看角色详情', '2021-03-28 20:29:58');
INSERT INTO `log` VALUES (58, NULL, '获取角色对应的资源', '2021-03-28 20:29:58');
INSERT INTO `log` VALUES (59, NULL, '查询用户列表', '2021-03-28 20:36:39');
INSERT INTO `log` VALUES (60, NULL, '查看角色详情', '2021-03-28 20:36:41');
INSERT INTO `log` VALUES (61, NULL, '获取角色对应的资源', '2021-03-28 20:36:41');
INSERT INTO `log` VALUES (62, NULL, '查询用户列表', '2021-03-28 20:37:33');
INSERT INTO `log` VALUES (63, NULL, '查看角色详情', '2021-03-28 20:37:35');
INSERT INTO `log` VALUES (64, NULL, '获取角色对应的资源', '2021-03-28 20:37:35');
INSERT INTO `log` VALUES (65, NULL, '查询用户列表', '2021-03-28 21:13:25');
INSERT INTO `log` VALUES (66, NULL, '查看角色详情', '2021-03-28 21:13:27');
INSERT INTO `log` VALUES (67, NULL, '查询用户列表', '2021-03-28 21:13:32');
INSERT INTO `log` VALUES (68, NULL, '查看角色详情', '2021-03-28 21:13:33');
INSERT INTO `log` VALUES (69, NULL, '查看角色详情', '2021-03-28 21:13:36');
INSERT INTO `log` VALUES (70, NULL, '返回用户列表', '2021-03-28 21:15:51');
INSERT INTO `log` VALUES (71, NULL, '返回所有角色', '2021-03-28 21:15:51');
INSERT INTO `log` VALUES (72, NULL, '返回用户列表', '2021-03-28 21:16:01');
INSERT INTO `log` VALUES (73, NULL, '查看登录方式', '2021-03-28 21:16:24');
INSERT INTO `log` VALUES (74, NULL, '查看日志', '2021-03-28 21:16:26');
INSERT INTO `log` VALUES (75, NULL, '返回用户列表', '2021-03-28 21:17:00');
INSERT INTO `log` VALUES (76, NULL, '返回所有角色', '2021-03-28 21:17:00');
INSERT INTO `log` VALUES (77, NULL, '返回所有角色', '2021-03-28 21:22:45');
INSERT INTO `log` VALUES (78, NULL, '返回用户列表', '2021-03-28 21:22:45');
INSERT INTO `log` VALUES (79, NULL, '返回所有角色', '2021-03-28 21:23:28');
INSERT INTO `log` VALUES (80, NULL, '返回用户列表', '2021-03-28 21:23:28');
INSERT INTO `log` VALUES (81, NULL, '返回所有角色', '2021-03-28 21:23:54');
INSERT INTO `log` VALUES (82, NULL, '返回用户列表', '2021-03-28 21:23:54');
INSERT INTO `log` VALUES (83, NULL, '返回所有角色', '2021-03-28 21:24:44');
INSERT INTO `log` VALUES (84, NULL, '返回用户列表', '2021-03-28 21:24:44');
INSERT INTO `log` VALUES (85, NULL, '返回用户列表', '2021-03-28 21:24:52');
INSERT INTO `log` VALUES (86, NULL, '返回用户列表', '2021-03-28 21:24:55');
INSERT INTO `log` VALUES (87, NULL, '查询用户列表', '2021-03-28 21:25:00');
INSERT INTO `log` VALUES (88, NULL, '查看日志', '2021-03-28 21:25:02');
INSERT INTO `log` VALUES (89, NULL, '查看登录方式', '2021-03-28 21:25:09');
INSERT INTO `log` VALUES (90, NULL, '查看日志', '2021-03-28 21:25:23');
INSERT INTO `log` VALUES (91, NULL, '返回用户列表', '2021-03-28 21:25:29');
INSERT INTO `log` VALUES (92, NULL, '返回所有角色', '2021-03-28 21:25:29');
INSERT INTO `log` VALUES (93, NULL, '查询用户列表', '2021-03-28 21:25:32');
INSERT INTO `log` VALUES (94, NULL, '查询菜单列表', '2021-03-28 21:25:39');
INSERT INTO `log` VALUES (95, NULL, '查询用户列表', '2021-03-28 21:25:42');
INSERT INTO `log` VALUES (96, NULL, '查询菜单列表', '2021-03-28 21:26:05');
INSERT INTO `log` VALUES (97, NULL, '查询用户列表', '2021-03-28 21:26:07');
INSERT INTO `log` VALUES (98, NULL, '查询用户列表', '2021-03-28 21:26:31');
INSERT INTO `log` VALUES (99, NULL, '查询用户列表', '2021-03-28 21:26:58');
INSERT INTO `log` VALUES (100, NULL, '返回所有角色', '2021-03-28 21:27:05');
INSERT INTO `log` VALUES (101, NULL, '返回用户列表', '2021-03-28 21:27:05');
INSERT INTO `log` VALUES (102, NULL, '查询用户列表', '2021-03-28 21:27:35');
INSERT INTO `log` VALUES (103, NULL, '查询用户列表', '2021-03-28 21:27:42');
INSERT INTO `log` VALUES (104, NULL, '查询用户列表', '2021-03-28 21:29:21');
INSERT INTO `log` VALUES (105, NULL, '查询用户列表', '2021-03-28 21:29:23');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` int(11) NULL DEFAULT NULL,
  `display` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, '系统管理', '', 0, 0);
INSERT INTO `permission` VALUES (2, '权限管理', '', 0, 0);
INSERT INTO `permission` VALUES (3, '资源管理', 'res', 2, 0);
INSERT INTO `permission` VALUES (6, '角色管理', 'role', 2, 0);
INSERT INTO `permission` VALUES (8, '用户管理', 'admin', 2, 0);
INSERT INTO `permission` VALUES (19, '日志管理', 'log', 1, 0);
INSERT INTO `permission` VALUES (20, '登录数据', 'logins', 1, 0);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `display` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '管理员', '0');
INSERT INTO `role` VALUES (2, '普通用户', '0');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `permission_id` int(11) NOT NULL,
  `rid` int(11) NOT NULL,
  PRIMARY KEY (`permission_id`, `rid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (1, 1);
INSERT INTO `role_permission` VALUES (1, 2);
INSERT INTO `role_permission` VALUES (2, 1);
INSERT INTO `role_permission` VALUES (3, 1);
INSERT INTO `role_permission` VALUES (6, 1);
INSERT INTO `role_permission` VALUES (8, 1);
INSERT INTO `role_permission` VALUES (19, 1);
INSERT INTO `role_permission` VALUES (19, 2);
INSERT INTO `role_permission` VALUES (20, 1);
INSERT INTO `role_permission` VALUES (20, 2);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `login` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录方式',
  `display` int(11) NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '小王', '123', '微信', 0, '18388880000');
INSERT INTO `user` VALUES (2, 'admin', '123', '微信', 0, '18388880001');
INSERT INTO `user` VALUES (3, 'test', '123', 'QQ', 0, '18388880002');
INSERT INTO `user` VALUES (5, '小李', '123', '微信', 0, '18388880004');
INSERT INTO `user` VALUES (6, '小张', '123', 'GitHub', 0, '18388880005');
INSERT INTO `user` VALUES (7, '小朱', '123', 'GitHub', 0, '18388880006');
INSERT INTO `user` VALUES (8, 'Tom', '123', 'QQ', 0, '18388880007');
INSERT INTO `user` VALUES (9, 'test1', '123', 'GitHub', 0, '18388880008');
INSERT INTO `user` VALUES (10, 'Jerry', '123', '微博', 0, '17317792543');
INSERT INTO `user` VALUES (11, '朱迪', '123', 'GitHub', 0, '13919533246');
INSERT INTO `user` VALUES (12, '老王', '123', 'Google', 0, '17785266325');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_id` int(11) NOT NULL,
  `rid` int(11) NOT NULL,
  PRIMARY KEY (`user_id`, `rid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (2, 1);
INSERT INTO `user_role` VALUES (3, 2);
INSERT INTO `user_role` VALUES (4, 1);
INSERT INTO `user_role` VALUES (4, 2);
INSERT INTO `user_role` VALUES (5, 2);
INSERT INTO `user_role` VALUES (6, 2);
INSERT INTO `user_role` VALUES (7, 2);
INSERT INTO `user_role` VALUES (8, 2);
INSERT INTO `user_role` VALUES (9, 2);
INSERT INTO `user_role` VALUES (10, 2);
INSERT INTO `user_role` VALUES (11, 2);
INSERT INTO `user_role` VALUES (12, 2);
INSERT INTO `user_role` VALUES (13, 2);
INSERT INTO `user_role` VALUES (14, 2);
INSERT INTO `user_role` VALUES (15, 2);
INSERT INTO `user_role` VALUES (16, 2);
INSERT INTO `user_role` VALUES (17, 2);
INSERT INTO `user_role` VALUES (18, 2);
INSERT INTO `user_role` VALUES (19, 2);
INSERT INTO `user_role` VALUES (20, 2);
INSERT INTO `user_role` VALUES (21, 2);
INSERT INTO `user_role` VALUES (22, 2);
INSERT INTO `user_role` VALUES (23, 2);
INSERT INTO `user_role` VALUES (24, 2);
INSERT INTO `user_role` VALUES (25, 2);
INSERT INTO `user_role` VALUES (26, 2);
INSERT INTO `user_role` VALUES (27, 2);
INSERT INTO `user_role` VALUES (28, 2);

SET FOREIGN_KEY_CHECKS = 1;
