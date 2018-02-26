/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 5.6.39 : Database - myblog
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`myblog` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `myblog`;

/*Table structure for table `t_article` */

DROP TABLE IF EXISTS `t_article`;

CREATE TABLE `t_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文章id',
  `title` varchar(50) NOT NULL COMMENT '标题',
  `fixed_link` varchar(255) NOT NULL COMMENT '固定链接',
  `content` text NOT NULL COMMENT '内容',
  `html` text NOT NULL COMMENT '内容html',
  `is_show` int(11) NOT NULL COMMENT '是否显示 1显示 0不显示',
  `sort_value` int(11) NOT NULL COMMENT '排序',
  `category_id` int(11) NOT NULL COMMENT '分类id',
  `category_name` varchar(50) NOT NULL COMMENT '分类名称',
  `tags` text NOT NULL COMMENT '标签',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `create_user_name` varchar(50) NOT NULL COMMENT '创建人名称',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  `update_time` bigint(20) NOT NULL COMMENT '修改时间',
  `pv` bigint(20) NOT NULL COMMENT '访问次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_article` */

/*Table structure for table `t_category` */

DROP TABLE IF EXISTS `t_category`;

CREATE TABLE `t_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `name` varchar(50) NOT NULL COMMENT '分类名称',
  `alias_name` varchar(50) NOT NULL COMMENT '分类别名',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  `update_time` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_category` */

/*Table structure for table `t_comment` */

DROP TABLE IF EXISTS `t_comment`;

CREATE TABLE `t_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `nick_name` varchar(50) NOT NULL COMMENT '昵称',
  `email` varchar(50) NOT NULL COMMENT '邮箱',
  `site` varchar(255) DEFAULT NULL COMMENT '网址',
  `content` text NOT NULL COMMENT '内容',
  `parent_id` bigint(20) NOT NULL COMMENT '父评论id',
  `comment_obj` bigint(20) NOT NULL COMMENT '评论对象',
  `to_nick_name` varchar(50) DEFAULT NULL COMMENT '回复目标人',
  `status` int(11) NOT NULL COMMENT '评论状态',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `audit_user` bigint(20) DEFAULT NULL COMMENT '审核人',
  `audit_time` bigint(20) DEFAULT NULL COMMENT '审核时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_comment` */

/*Table structure for table `t_param` */

DROP TABLE IF EXISTS `t_param`;

CREATE TABLE `t_param` (
  `param_name` varchar(255) NOT NULL COMMENT '参数名称',
  `param_value` varchar(255) NOT NULL COMMENT '参数值',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  `update_time` bigint(20) NOT NULL COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_param` */

insert  into `t_param`(`param_name`,`param_value`,`create_user`,`create_time`,`update_user`,`update_time`) values ('upload_url','/uploads/',2,0,2,0);

/*Table structure for table `t_resource` */

DROP TABLE IF EXISTS `t_resource`;

CREATE TABLE `t_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '资源id',
  `url` varchar(255) NOT NULL COMMENT 'url',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_resource` */

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(50) NOT NULL COMMENT '用户登录名',
  `password` varchar(64) NOT NULL COMMENT '用户密码',
  `nick_name` varchar(50) NOT NULL COMMENT '用户昵称',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`username`,`password`,`nick_name`,`create_time`) values (2,'admin','03ef9156c24596fba56f3e0e88f7048f','admin',1515310345751);

/*Table structure for table `t_user_log` */

DROP TABLE IF EXISTS `t_user_log`;

CREATE TABLE `t_user_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '登录id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `login_time` datetime NOT NULL COMMENT '登录时间',
  `login_stat` int(11) NOT NULL COMMENT '登录总次数',
  `login_error_count` int(11) NOT NULL COMMENT '登录错误次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user_log` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
