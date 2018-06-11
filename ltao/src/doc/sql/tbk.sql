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

/*Table structure for table `cate` */

DROP TABLE IF EXISTS `cate`;

CREATE TABLE `cate` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '类型id',
  `name` varchar(30) NOT NULL COMMENT '名字',
  `desc` varchar(100) DEFAULT NULL COMMENT '描述',
  `data` binary(1) DEFAULT NULL COMMENT '详细数据:from-cate映射',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='类目表';

/*Table structure for table `menu_cate` */

DROP TABLE IF EXISTS `menu_cate`;

CREATE TABLE `menu_cate` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `show_name` varchar(20) DEFAULT NULL COMMENT '展示的名字',
  `section_id` int(11) DEFAULT NULL COMMENT '跳转的模块id',
  `icons` varchar(300) DEFAULT NULL COMMENT 'icon地址:pcicon,weixin,等',
  `parent_id` int(11) DEFAULT '0' COMMENT '父类id：0总节点',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='需要展示的类型菜单';

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

/*Table structure for table `section` */

DROP TABLE IF EXISTS `section`;

CREATE TABLE `section` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '板块区域名字',
  `desc` varchar(100) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='板块区域';

/*Table structure for table `section_cate` */

DROP TABLE IF EXISTS `section_cate`;

CREATE TABLE `section_cate` (
  `sid` int(10) unsigned NOT NULL COMMENT 'selectionId',
  `cid` int(11) DEFAULT NULL COMMENT 'cid'
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='selection 与cate的对应关系，多对多';

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
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品表id',
  `shop_iid` bigint(20) DEFAULT NULL COMMENT '商品id（各平台可能重叠）',
  `price` float DEFAULT '0' COMMENT '当前价格',
  `oprice` float DEFAULT '0' COMMENT '原价',
  `area` varchar(15) DEFAULT NULL COMMENT '地址详情',
  `mimage` varchar(200) DEFAULT NULL COMMENT '主图',
  `images` binary(1) DEFAULT NULL COMMENT '图列表',
  `shopUrl` varchar(500) DEFAULT NULL COMMENT '商品地址',
  `ticketUrl` varchar(500) DEFAULT NULL COMMENT '商品优惠地址（淘客地址）',
  `shortUrl` varchar(50) DEFAULT NULL COMMENT '短连接',
  `reward` float DEFAULT NULL COMMENT '佣金价格',
  `tickDetail` varchar(300) DEFAULT NULL COMMENT '券id,剩余量,描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000000 DEFAULT CHARSET=gbk;

/*Table structure for table `shop_extra` */

DROP TABLE IF EXISTS `shop_extra`;

CREATE TABLE `shop_extra` (
  `shopId` bigint(20) NOT NULL COMMENT '商品表id',
  `areaCode` int(11) DEFAULT NULL COMMENT '地址编码',
  `size` smallint(6) DEFAULT NULL COMMENT '可选size类型',
  `sprice` float DEFAULT NULL COMMENT '排序价格（智能修改）',
  `colorUnit` smallint(6) DEFAULT NULL COMMENT '可选颜色集合short',
  `from` smallint(6) DEFAULT NULL COMMENT '来源:本地(0-9)/淘宝(10-19)/京东（20-29）',
  `cat` int(11) DEFAULT NULL COMMENT '种类（不分子类）',
  `sellid` int(11) DEFAULT NULL COMMENT '卖家id',
  `sales` int(11) DEFAULT NULL COMMENT '销量(月)',
  `startTime` int(20) DEFAULT NULL COMMENT '优惠开始时间时间戳/1000',
  `endTime` int(20) DEFAULT NULL COMMENT '优惠结束时间时间戳/1000',
  PRIMARY KEY (`shopId`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='商品表的附加数据';

/*Table structure for table `system_cache_data` */

DROP TABLE IF EXISTS `system_cache_data`;

CREATE TABLE `system_cache_data` (
  `id` int(11) NOT NULL COMMENT 'cacheKey',
  `data` binary(1) DEFAULT NULL COMMENT 'datas',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
