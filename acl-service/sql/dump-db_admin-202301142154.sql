-- MySQL dump 10.13  Distrib 5.5.62, for Win64 (AMD64)
--
-- Host: localhost    Database: db_admin
-- ------------------------------------------------------
-- Server version	5.7.38-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `acl_permission`
--

DROP TABLE IF EXISTS `acl_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acl_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单主键ID',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID',
  `order_num` int(11) DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `perms` varchar(100) DEFAULT '' COMMENT '权限标识',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acl_permission`
--

LOCK TABLES `acl_permission` WRITE;
/*!40000 ALTER TABLE `acl_permission` DISABLE KEYS */;
INSERT INTO `acl_permission` VALUES (1,'系统管理','system',0,1,'/sys','','M','','2023-01-01 01:01:01','2023-01-01 01:01:01','系统管理目录'),(2,'预约管理','booking',0,2,'/booking','','M','','2023-01-01 01:01:01','2023-01-01 01:01:01','业务管理目录'),(3,'用户管理','user',1,1,'/system/user','system/user/index','C','system:user:list','2023-01-01 01:01:01','2023-01-01 01:01:01','用户管理菜单'),(4,'角色管理','peoples',1,2,'/system/role','system/role/index','C','system:role:list','2023-01-01 01:01:01','2023-01-01 01:01:01','角色管理菜单'),(5,'菜单管理','tree-table',1,3,'/system/menu','system/menu/index','C','system:menu:list','2023-01-01 01:01:01','2023-01-01 01:01:01','菜单管理菜单'),(6,'预约信息','info',2,1,'/booking/info','booking/info','C','','2023-01-01 01:01:01','2023-01-01 01:01:01','部门管理菜单'),(7,'预约统计','statistics',2,2,'/booking/statistics','booking/statistics','C','','2023-01-01 01:01:01','2023-01-01 01:01:01','岗位管理菜单'),(8,'用户新增','#',3,2,'','','F','system:user:add','2023-01-01 01:01:01','2023-01-01 01:01:01','添加用户按钮'),(9,'用户修改','#',3,3,'','','F','system:user:edit','2023-01-01 01:01:01','2023-01-01 01:01:01','修改用户按钮'),(10,'用户删除','#',3,4,'','','F','system:user:delete','2023-01-01 01:01:01','2023-01-01 01:01:01','删除用户按钮'),(11,'分配角色','#',3,5,'','','F','system:user:role','2023-01-01 01:01:01','2023-01-01 01:01:01','分配角色按钮'),(12,'重置密码','#',3,6,'','','F','system:user:resetPwd','2023-01-01 01:01:01','2023-01-01 01:01:01','重置密码按钮'),(13,'角色新增','#',4,2,'','','F','system:role:add','2023-01-01 01:01:01','2023-01-01 01:01:01','添加用户按钮'),(14,'角色修改','#',4,3,'','','F','system:role:edit','2023-01-01 01:01:01','2023-01-01 01:01:01','修改用户按钮'),(15,'角色删除','#',4,4,'',NULL,'F','system:role:delete','2023-01-01 01:01:01','2023-01-01 01:01:01','删除用户按钮'),(16,'分配权限','#',4,5,'','','F','system:role:menu','2023-01-01 01:01:01','2023-01-01 01:01:01','分配权限按钮'),(17,'菜单新增','#',5,2,'',NULL,'F','system:menu:add','2023-01-01 01:01:01','2023-01-01 01:01:01','添加菜单按钮'),(18,'菜单修改','#',5,3,'',NULL,'F','system:menu:edit','2023-01-01 01:01:01','2023-01-01 01:01:01','修改菜单按钮'),(19,'菜单删除','#',5,4,'',NULL,'F','system:menu:delete','2023-01-01 01:01:01','2023-01-01 01:01:01','删除菜单按钮'),(20,'用户查询','#',3,1,'',NULL,'F','system:user:query','2023-01-01 01:01:01','2023-01-01 01:01:01','用户查询按钮'),(21,'角色查询','#',4,1,'',NULL,'F','system:role:query','2023-01-01 01:01:01','2023-01-01 01:01:01','角色查询按钮'),(22,'菜单查询','#',5,1,'',NULL,'F','system:menu:query','2023-01-01 01:01:01','2023-01-01 01:01:01','菜单查询按钮'),(34,'测试','',7,1,'','','F','system:post:test','2023-01-01 01:01:01','2023-01-01 01:01:01',NULL);
/*!40000 ALTER TABLE `acl_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acl_role`
--

DROP TABLE IF EXISTS `acl_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acl_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色主键ID',
  `name` varchar(30) DEFAULT NULL COMMENT '角色名称',
  `code` varchar(100) DEFAULT NULL COMMENT '角色权限字符串',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acl_role`
--

LOCK TABLES `acl_role` WRITE;
/*!40000 ALTER TABLE `acl_role` DISABLE KEYS */;
INSERT INTO `acl_role` VALUES (1,'超级管理员','admin','2023-01-01 01:01:01','2023-01-01 01:01:01','拥有系统最高权限'),(2,'普通角色','common','2023-01-01 01:01:01','2023-01-01 01:01:01','普通角色'),(3,'测试角色','test','2023-01-01 01:01:01','2023-01-01 01:01:01','测试角色');
/*!40000 ALTER TABLE `acl_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acl_role_permission`
--

DROP TABLE IF EXISTS `acl_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acl_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色菜单主键ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=310 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acl_role_permission`
--

LOCK TABLES `acl_role_permission` WRITE;
/*!40000 ALTER TABLE `acl_role_permission` DISABLE KEYS */;
INSERT INTO `acl_role_permission` VALUES (8,2,1),(9,2,2),(10,2,3),(11,2,4),(12,2,5),(13,2,6),(14,2,7),(21,7,1),(22,7,2),(23,7,6),(24,7,7),(25,6,1),(26,6,3),(27,6,9),(28,6,10),(29,19,1),(30,19,3),(31,19,2),(32,19,6),(208,20,1),(209,20,3),(210,20,20),(211,20,8),(212,20,9),(213,20,33),(214,20,10),(215,20,11),(216,20,4),(217,20,21),(218,20,13),(219,20,5),(220,20,22),(221,20,17),(222,20,18),(223,20,2),(224,20,6),(225,20,7),(232,21,1),(233,21,9),(234,21,4),(235,21,21),(236,21,2),(237,21,6),(238,21,7),(263,1,1),(264,1,3),(265,1,20),(266,1,8),(267,1,9),(268,1,33),(269,1,10),(270,1,11),(271,1,12),(272,1,4),(273,1,21),(274,1,13),(275,1,14),(276,1,15),(277,1,16),(278,1,5),(279,1,22),(280,1,17),(281,1,18),(282,1,19),(283,1,2),(284,1,6),(285,1,7),(286,1,34),(287,3,1),(288,3,3),(289,3,20),(290,3,8),(291,3,9),(292,3,33),(293,3,10),(294,3,11),(295,3,12),(296,3,4),(297,3,21),(298,3,13),(299,3,14),(300,3,15),(301,3,16),(302,3,5),(303,3,22),(304,3,17),(305,3,18),(306,3,19),(307,3,2),(308,3,6),(309,3,7);
/*!40000 ALTER TABLE `acl_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acl_user`
--

DROP TABLE IF EXISTS `acl_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acl_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(100) DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `avatar` varchar(255) DEFAULT 'default.jpg' COMMENT '用户头像',
  `email` varchar(100) DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) DEFAULT '' COMMENT '手机号码',
  `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acl_user`
--

LOCK TABLES `acl_user` WRITE;
/*!40000 ALTER TABLE `acl_user` DISABLE KEYS */;
INSERT INTO `acl_user` VALUES (1,'admin','$2a$10$iRe5epdc36jMNCs9pqw3AeMB02a49SFzFcORqwPmldNVNnMTPG5Bu','https://bk-admin.oss-cn-chengdu.aliyuncs.com/2023/01/14/2871e6383a664382b98dd4853e55c402111.jpg','2022114936@qq.com','18385244736','2023-01-14 21:10:19','0','2023-01-01 01:01:01','2023-01-14 18:51:36','备注'),(2,'common','$2a$10$tiArwm0GxChyEP5k0JGzsOuzyY15IKA.ZTl8S2aj3haYlKAfpwfl.','https://bk-admin.oss-cn-chengdu.aliyuncs.com/2023/01/14/709c9d905bf24a85961f6b6e0697bf2d1.jpg','2022114936@qq.com','18281212821','2022-08-24 05:34:39','0','2023-01-01 01:01:01','2023-01-14 15:59:08',NULL),(3,'test','$2a$10$hBuVfId1SStDAfjMlblNCuMo5VToHRCf1s7sfCRKusFQMLBSrLnJm','https://bk-admin.oss-cn-chengdu.aliyuncs.com/2023/01/14/9fba25efd4364020a54e9fd28ab60d0d333.jpg','2022114936@qq.com','13121212121','2023-01-14 16:33:15','0','2023-01-01 01:01:01','2023-01-14 15:59:31',NULL),(9,'张三','$2a$10$rOS9fkYLnUaTLssZ0xd9YeXzue3kG5e9PdvItdsxBV2zPceMhVVfG','https://bk-admin.oss-cn-chengdu.aliyuncs.com/2023/01/14/709c9d905bf24a85961f6b6e0697bf2d1.jpg','2022114936@qq.com','18545648965','2023-01-14 16:37:01','0','2023-01-01 01:01:01',NULL,'');
/*!40000 ALTER TABLE `acl_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acl_user_role`
--

DROP TABLE IF EXISTS `acl_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acl_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户角色主键ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acl_user_role`
--

LOCK TABLES `acl_user_role` WRITE;
/*!40000 ALTER TABLE `acl_user_role` DISABLE KEYS */;
INSERT INTO `acl_user_role` VALUES (2,2,2),(23,3,3),(24,1,1);
/*!40000 ALTER TABLE `acl_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'db_admin'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-14 21:54:41
