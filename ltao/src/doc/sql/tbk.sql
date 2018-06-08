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

/*Table structure for table `sellid` */

DROP TABLE IF EXISTS `sellid`;

CREATE TABLE `sellid` (
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
  `shid` bigint(20) DEFAULT NULL COMMENT '商品id（各平台可能重叠）',
  `sid` int(11) DEFAULT NULL COMMENT '卖家id',
  `from` smallint(6) DEFAULT '0' COMMENT '来源:本地(0-9)/淘宝(10-19)/京东（20-29）',
  `price` float DEFAULT '0' COMMENT '当前价格',
  `oprice` float DEFAULT '0' COMMENT '原价',
  `ticket` int(11) DEFAULT NULL COMMENT '只买一件商品即可使用的最大优惠券面值',
  `area` varchar(15) DEFAULT NULL COMMENT '地址详情',
  `cat` int(11) DEFAULT '0' COMMENT '种类（不分子类）',
  `mimage` varchar(200) DEFAULT NULL COMMENT '主图',
  `images` binary(1) DEFAULT NULL COMMENT '图列表',
  `shopUrl` varchar(200) DEFAULT NULL COMMENT '商品地址',
  `ticketUrl` varchar(200) DEFAULT NULL COMMENT '商品优惠地址（淘客地址）',
  `shortUrl` varchar(50) DEFAULT NULL COMMENT '短连接',
  `sales` int(11) DEFAULT NULL COMMENT '销量(月)',
  `reward` float DEFAULT NULL COMMENT '佣金价格',
  `tickid` varchar(32) DEFAULT NULL COMMENT '卷id',
  `tickDetail` varchar(150) DEFAULT NULL COMMENT '优惠券总量,剩余量,描述',
  `startTime` datetime DEFAULT NULL COMMENT '优惠开始时间',
  `endTime` datetime DEFAULT NULL COMMENT '优惠结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk;

/*Table structure for table `shop_extra` */

DROP TABLE IF EXISTS `shop_extra`;

CREATE TABLE `shop_extra` (
  `shopId` bigint(20) NOT NULL COMMENT '商品表id',
  `areaCode` int(11) DEFAULT NULL COMMENT '地址编码',
  `size` smallint(6) DEFAULT NULL COMMENT '可选size类型',
  `colorUnit` smallint(6) DEFAULT NULL COMMENT '可选颜色集合short',
  PRIMARY KEY (`shopId`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='商品表的附加数据';

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
