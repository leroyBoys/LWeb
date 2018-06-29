/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.5.53 : Database - tbk
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`tbk` /*!40100 DEFAULT CHARACTER SET gbk */;

USE `tbk`;

/*Table structure for table `order_list_taobao` */

DROP TABLE IF EXISTS `order_list_taobao`;

CREATE TABLE `order_list_taobao` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `order_number` varchar(18) NOT NULL COMMENT '订单编号',
  `title` varchar(150) NOT NULL COMMENT '商品名字titile',
  `shop_iid` bigint(15) NOT NULL COMMENT '商品id',
  `num` int(10) NOT NULL COMMENT '商品数量',
  `deal_price` float DEFAULT NULL COMMENT '结算价格',
  `price` float NOT NULL COMMENT '价格',
  `status` tinyint(1) NOT NULL COMMENT '状态订单付款-结算等',
  `yongjin` float NOT NULL,
  `pingtai` varchar(10) NOT NULL,
  `deal_time` datetime DEFAULT NULL COMMENT '结算时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `sername` varchar(30) NOT NULL COMMENT '店铺（卖家名字）',
  `type` tinyint(1) NOT NULL COMMENT '订单类型：天猫/淘宝',
  `yongjin_yugu` float NOT NULL,
  `media_id` int(11) DEFAULT NULL,
  `media_name` varchar(40) DEFAULT NULL,
  `ad_id` int(11) DEFAULT NULL,
  `ad_name` varchar(40) DEFAULT NULL,
  `cate_iiname` varchar(50) DEFAULT NULL COMMENT '淘宝类目名称',
  PRIMARY KEY (`id`),
  KEY `status` (`status`),
  KEY `num_iid` (`shop_iid`,`status`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `seller` */

DROP TABLE IF EXISTS `seller`;

CREATE TABLE `seller` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '卖家表id',
  `from` smallint(6) DEFAULT NULL COMMENT '来源',
  `sid` int(11) DEFAULT NULL COMMENT '卖家id',
  `name` varchar(100) DEFAULT NULL COMMENT '店铺名称',
  `contact` varchar(150) DEFAULT NULL COMMENT '联系方式（旺旺）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Table structure for table `shop` */

DROP TABLE IF EXISTS `shop`;

CREATE TABLE `shop` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '商品表id',
  `shop_iid` bigint(20) DEFAULT NULL COMMENT '商品id（各平台可能重叠）',
  `price` float DEFAULT '0' COMMENT '当前价格',
  `oprice` float DEFAULT '0' COMMENT '原价',
  `area` varchar(15) DEFAULT NULL COMMENT '地址详情',
  `src` varchar(200) DEFAULT NULL COMMENT '主图',
  `shopUrl` varchar(500) DEFAULT NULL COMMENT '商品地址',
  `ticketUrl` varchar(500) DEFAULT NULL COMMENT '商品优惠地址（淘客地址）',
  `shortUrl` varchar(50) DEFAULT NULL COMMENT '短连接',
  `reward` float DEFAULT NULL COMMENT '佣金价格',
  `tickDetail` varchar(300) DEFAULT NULL COMMENT '券id,剩余量,描述',
  `tickDesc` varchar(30) DEFAULT NULL COMMENT '优惠券简单描述',
  `title` varchar(150) DEFAULT NULL COMMENT '商品title',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Table structure for table `shop_extra` */

DROP TABLE IF EXISTS `shop_extra`;

CREATE TABLE `shop_extra` (
  `shopId` int(20) NOT NULL COMMENT '商品表id',
  `areaCode` int(11) DEFAULT NULL COMMENT '地址编码',
  `size` smallint(6) DEFAULT NULL COMMENT '可选size类型',
  `sprice` float DEFAULT NULL COMMENT '排序价格（智能修改）',
  `colorUnit` smallint(6) DEFAULT NULL COMMENT '可选颜色集合short',
  `from` smallint(6) DEFAULT NULL COMMENT '来源:本地(0-9)/淘宝(10-19)/京东（20-29）',
  `cat` int(11) DEFAULT NULL COMMENT '种类（不分子类）',
  `sellid` int(11) DEFAULT NULL COMMENT '卖家id',
  `sales` int(11) DEFAULT NULL COMMENT '销量(月)',
  `startTime` int(20) DEFAULT '0' COMMENT '优惠开始时间时间戳/1000',
  `endTime` int(20) DEFAULT '0' COMMENT '优惠结束时间时间戳/1000',
  `sortNum` int(11) DEFAULT '0' COMMENT '排序编号',
  PRIMARY KEY (`shopId`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='商品表的附加数据';

/*Table structure for table `shop_image` */

DROP TABLE IF EXISTS `shop_image`;

CREATE TABLE `shop_image` (
  `id` int(11) NOT NULL COMMENT '商品表id',
  `src` varchar(300) DEFAULT NULL COMMENT '图片地址',
  `position` smallint(6) DEFAULT NULL COMMENT '位置'
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='商品有关图片存储';

/*Table structure for table `system_admin` */

DROP TABLE IF EXISTS `system_admin`;

CREATE TABLE `system_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `role_group_id` int(11) DEFAULT NULL COMMENT '管理员角色组id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk;

/*Table structure for table `system_cate` */

DROP TABLE IF EXISTS `system_cate`;

CREATE TABLE `system_cate` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '类型id',
  `name` varchar(30) NOT NULL COMMENT '名字',
  `desc` varchar(100) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gbk COMMENT='类目表';

/*Table structure for table `system_cate_map_taobao` */

DROP TABLE IF EXISTS `system_cate_map_taobao`;

CREATE TABLE `system_cate_map_taobao` (
  `cate_id` int(10) unsigned NOT NULL COMMENT 'cateId',
  `cate_id_taobao` int(11) DEFAULT NULL COMMENT '淘宝catid',
  UNIQUE KEY `cate_id_taobao` (`cate_id_taobao`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Table structure for table `system_group_url` */

DROP TABLE IF EXISTS `system_group_url`;

CREATE TABLE `system_group_url` (
  `id` int(11) NOT NULL,
  `role_group_id` int(11) DEFAULT NULL,
  `url_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Table structure for table `system_main` */

DROP TABLE IF EXISTS `system_main`;

CREATE TABLE `system_main` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) DEFAULT NULL COMMENT '名字',
  `desc` varchar(30) DEFAULT NULL COMMENT '备注',
  `tmpId` varchar(30) DEFAULT NULL COMMENT '模板id',
  `type` smallint(6) DEFAULT '0' COMMENT '平台类型：0pc,1微信',
  `isDefault` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否默认主页',
  `sectionId` int(11) DEFAULT '0' COMMENT '区域/频道id（关联，搜索时使用）',
  `mainType` smallint(6) DEFAULT '0' COMMENT '主页类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gbk COMMENT='主页数据';

/*Table structure for table `system_main_data` */

DROP TABLE IF EXISTS `system_main_data`;

CREATE TABLE `system_main_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `main_id` int(10) unsigned NOT NULL COMMENT '主页id',
  `data` text COMMENT '模板数据',
  `startTime` int(11) DEFAULT '0' COMMENT '开始时间',
  `endTime` int(11) DEFAULT '0' COMMENT '结束时间',
  `tmp_type` smallint(6) DEFAULT '0' COMMENT '模板类型',
  `sortNum` int(11) DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=gbk;

/*Table structure for table `system_manager_menu` */

DROP TABLE IF EXISTS `system_manager_menu`;

CREATE TABLE `system_manager_menu` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(20) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `parentId` int(11) DEFAULT '0',
  `icon` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=gbk;

/*Table structure for table `system_menu` */

DROP TABLE IF EXISTS `system_menu`;

CREATE TABLE `system_menu` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL COMMENT '名字',
  `note` varchar(30) DEFAULT NULL COMMENT '备注',
  `url` varchar(200) DEFAULT NULL COMMENT 'pc跳转地址',
  `icon` varchar(100) DEFAULT NULL COMMENT 'pc icon',
  `parent_id` int(11) DEFAULT '0' COMMENT '父类id：0总节点',
  `open` tinyint(1) DEFAULT '0' COMMENT '是否启用',
  `main_id` int(11) DEFAULT '0' COMMENT '主页id',
  `menu_type` tinyint(1) DEFAULT NULL COMMENT '菜单类型:0类目,1:section模块）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='需要展示的类型菜单';

/*Table structure for table `system_role_group` */

DROP TABLE IF EXISTS `system_role_group`;

CREATE TABLE `system_role_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Table structure for table `system_section` */

DROP TABLE IF EXISTS `system_section`;

CREATE TABLE `system_section` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL COMMENT '板块区域名字',
  `note` varchar(20) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gbk COMMENT='板块区域(频道)';

/*Table structure for table `system_section_cate` */

DROP TABLE IF EXISTS `system_section_cate`;

CREATE TABLE `system_section_cate` (
  `sid` int(10) unsigned NOT NULL COMMENT 'selectionId',
  `cid` int(11) DEFAULT NULL COMMENT 'cid',
  UNIQUE KEY `sid` (`sid`,`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='selection 与cate的对应关系，多对多';

/*Table structure for table `system_setting` */

DROP TABLE IF EXISTS `system_setting`;

CREATE TABLE `system_setting` (
  `id` int(20) unsigned NOT NULL,
  `value` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `system_tmp` */

DROP TABLE IF EXISTS `system_tmp`;

CREATE TABLE `system_tmp` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '模板id',
  `image` varchar(100) DEFAULT NULL COMMENT '模板视图',
  `tmp_num` varchar(100) DEFAULT NULL COMMENT '模板编号',
  `tmp_type` smallint(6) DEFAULT NULL COMMENT '模板类型',
  `img_div_id` varchar(40) DEFAULT NULL COMMENT '模板缩略图的div id（拖动按钮）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='模板管理（系统管理员使用）';

/*Table structure for table `system_url` */

DROP TABLE IF EXISTS `system_url`;

CREATE TABLE `system_url` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
