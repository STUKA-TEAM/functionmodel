# Host: 127.0.0.1  (Version: 5.5.32)
# Date: 2013-11-07 00:26:08
# Generator: MySQL-Front 5.3  (Build 4.43)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "lottery_activity"
#

DROP TABLE IF EXISTS `lottery_activity`;
CREATE TABLE `lottery_activity` (
  `LotteryId` int(11) NOT NULL AUTO_INCREMENT COMMENT '抽奖活动ID',
  `LotteryName` varchar(50) NOT NULL DEFAULT '' COMMENT '抽奖活动名称',
  `LotterySummary` varchar(500) NOT NULL DEFAULT '' COMMENT '抽奖活动介绍',
  `LotteryPicture` varchar(100) DEFAULT NULL COMMENT '抽奖活动背景图片地址',
  `StartDate` date NOT NULL DEFAULT '0000-00-00' COMMENT '抽奖活动起始日期',
  `EndDate` date NOT NULL DEFAULT '0000-00-00' COMMENT '抽奖活动结束日期',
  `ChanceNum` int(11) NOT NULL DEFAULT '0' COMMENT '最大抽奖次数',
  `LotteryStatus` int(11) NOT NULL DEFAULT '0' COMMENT '抽奖活动状态，约束(0,1,2)',
  PRIMARY KEY (`LotteryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "lottery_activity"
#


#
# Structure for table "lottery_lucky_record"
#

DROP TABLE IF EXISTS `lottery_lucky_record`;
CREATE TABLE `lottery_lucky_record` (
  `LuckyId` int(11) NOT NULL AUTO_INCREMENT COMMENT '中奖记录ID',
  `PrizeId` int(11) NOT NULL DEFAULT '0' COMMENT '奖项ID',
  `OpenId` int(11) NOT NULL DEFAULT '0' COMMENT '用户ID',
  PRIMARY KEY (`LuckyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "lottery_lucky_record"
#


#
# Structure for table "lottery_prize"
#

DROP TABLE IF EXISTS `lottery_prize`;
CREATE TABLE `lottery_prize` (
  `PrizeId` int(11) NOT NULL AUTO_INCREMENT COMMENT '奖项ID',
  `LotteryId` int(11) NOT NULL DEFAULT '0' COMMENT '抽奖活动ID',
  `PrizeName` varchar(10) NOT NULL DEFAULT '' COMMENT '奖项名称',
  `PrizeContent` varchar(100) NOT NULL DEFAULT '' COMMENT '奖项内容',
  `LuckyNum` int(11) NOT NULL DEFAULT '0' COMMENT '中奖人数',
  `LuckyPercent` decimal(12,5) NOT NULL DEFAULT '0.00000' COMMENT '中奖概率',
  PRIMARY KEY (`PrizeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "lottery_prize"
#


#
# Structure for table "lottery_record"
#

DROP TABLE IF EXISTS `lottery_record`;
CREATE TABLE `lottery_record` (
  `RecordId` int(11) NOT NULL AUTO_INCREMENT COMMENT '抽奖记录ID',
  `LotteryId` int(11) NOT NULL DEFAULT '0' COMMENT '抽奖活动ID',
  `OpenId` int(11) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `LotteryResult` int(11) NOT NULL DEFAULT '0' COMMENT '抽奖结果（是否中奖）约束：(0,1)',
  PRIMARY KEY (`RecordId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "lottery_record"
#


#
# Structure for table "order_dishes"
#

DROP TABLE IF EXISTS `order_dishes`;
CREATE TABLE `order_dishes` (
  `DishId` int(11) NOT NULL AUTO_INCREMENT COMMENT '餐馆名称',
  `DishName` varchar(20) NOT NULL DEFAULT '' COMMENT '菜品名称',
  `Price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '菜品单价',
  `Discribe` varchar(500) NOT NULL DEFAULT '' COMMENT '菜品描述',
  `Picture` varchar(100) DEFAULT NULL COMMENT '菜品图片',
  PRIMARY KEY (`DishId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "order_dishes"
#


#
# Structure for table "order_menu"
#

DROP TABLE IF EXISTS `order_menu`;
CREATE TABLE `order_menu` (
  `MenuId` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `Date` int(11) NOT NULL DEFAULT '0' COMMENT '周X（例周六：X=6），约束（1,2,3,4,5,6,7）',
  `Dish` int(11) NOT NULL DEFAULT '0' COMMENT '可选菜品ID',
  PRIMARY KEY (`MenuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "order_menu"
#


#
# Structure for table "order_store"
#

DROP TABLE IF EXISTS `order_store`;
CREATE TABLE `order_store` (
  `StoreId` int(11) NOT NULL AUTO_INCREMENT COMMENT '订餐餐馆ID',
  `StoreName` varchar(20) NOT NULL DEFAULT '' COMMENT '餐馆名称',
  PRIMARY KEY (`StoreId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "order_store"
#


#
# Structure for table "order_user"
#

DROP TABLE IF EXISTS `order_user`;
CREATE TABLE `order_user` (
  `UserId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户在数据库中的ID',
  `OpenId` varchar(30) NOT NULL DEFAULT '' COMMENT '微信返回的用户ID',
  `Address` varchar(30) NOT NULL DEFAULT '' COMMENT '订餐配送地址',
  `Phone` varchar(11) NOT NULL DEFAULT '' COMMENT '配送电话',
  PRIMARY KEY (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "order_user"
#


#
# Structure for table "order_user_order"
#

DROP TABLE IF EXISTS `order_user_order`;
CREATE TABLE `order_user_order` (
  `OrderId` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `UserId` int(11) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `Date` date NOT NULL DEFAULT '0000-00-00' COMMENT '订单日期',
  `DishId` int(11) NOT NULL DEFAULT '0' COMMENT '菜品ID',
  `DishNum` int(11) NOT NULL DEFAULT '0' COMMENT '菜品份数',
  `Price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '单个菜品总价',
  `State` int(11) NOT NULL DEFAULT '0' COMMENT '订餐状态',
  PRIMARY KEY (`OrderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "order_user_order"
#


#
# Structure for table "order_wiki"
#

DROP TABLE IF EXISTS `order_wiki`;
CREATE TABLE `order_wiki` (
  `Wiki` varchar(500) NOT NULL DEFAULT '',
  `WikiId` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`WikiId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "order_wiki"
#


#
# Structure for table "recommend_store"
#

DROP TABLE IF EXISTS `recommend_store`;
CREATE TABLE `recommend_store` (
  `StoreId` int(11) NOT NULL AUTO_INCREMENT COMMENT '分店ID',
  `StoreName` varchar(20) NOT NULL DEFAULT '' COMMENT '分店名',
  `Address` varchar(30) NOT NULL DEFAULT '' COMMENT '分店地址',
  `Coordinate` varchar(50) NOT NULL DEFAULT '' COMMENT '坐标',
  PRIMARY KEY (`StoreId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "recommend_store"
#


#
# Structure for table "song_shopsentence"
#

DROP TABLE IF EXISTS `song_shopsentence`;
CREATE TABLE `song_shopsentence` (
  `SentenceId` int(11) NOT NULL AUTO_INCREMENT,
  `Sentence` varchar(500) NOT NULL DEFAULT '' COMMENT '商户寄语',
  PRIMARY KEY (`SentenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "song_shopsentence"
#


#
# Structure for table "vote_activity"
#

DROP TABLE IF EXISTS `vote_activity`;
CREATE TABLE `vote_activity` (
  `VoteId` int(11) NOT NULL AUTO_INCREMENT COMMENT '投票活动ID',
  `VoteTitle` varchar(50) NOT NULL DEFAULT '' COMMENT '投票活动标题',
  `VoteSummary` varchar(500) NOT NULL DEFAULT '' COMMENT '投票活动描述',
  `VotePicture` varchar(100) DEFAULT NULL COMMENT '投票活动背景图片地址',
  `StartDate` date NOT NULL DEFAULT '0000-00-00' COMMENT '投票活动起始时间',
  `EndDate` date NOT NULL DEFAULT '0000-00-00' COMMENT '投票活动结束时间',
  `IsMultiChoice` int(11) NOT NULL DEFAULT '0' COMMENT '投票是否为多选类型，约束(0,1)',
  `MaxChoice` int(11) NOT NULL DEFAULT '0' COMMENT '（如果为多选）最大选择数',
  `EnableAdvice` int(11) NOT NULL DEFAULT '0' COMMENT '投票是否可留言，约束(0,1)',
  `VoteStatus` int(11) NOT NULL DEFAULT '0' COMMENT '投票活动状态，约束(0,1,2)',
  PRIMARY KEY (`VoteId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "vote_activity"
#


#
# Structure for table "vote_advice"
#

DROP TABLE IF EXISTS `vote_advice`;
CREATE TABLE `vote_advice` (
  `AdviceId` int(11) NOT NULL AUTO_INCREMENT COMMENT '留言ID',
  `VoteId` int(11) NOT NULL DEFAULT '0' COMMENT '投票活动ID',
  `OpenId` int(11) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `AdviceContent` varchar(500) NOT NULL DEFAULT '' COMMENT '留言内容',
  PRIMARY KEY (`AdviceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "vote_advice"
#


#
# Structure for table "vote_item"
#

DROP TABLE IF EXISTS `vote_item`;
CREATE TABLE `vote_item` (
  `ItemId` int(11) NOT NULL AUTO_INCREMENT COMMENT '投票选项ID',
  `VoteId` int(11) NOT NULL DEFAULT '0' COMMENT '投票活动ID',
  `ItemImage` varchar(100) DEFAULT NULL COMMENT '投票选项图片地址',
  `ItemDesc` varchar(100) DEFAULT NULL COMMENT '投票选项文本描述',
  PRIMARY KEY (`ItemId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "vote_item"
#


#
# Structure for table "vote_item_choice"
#

DROP TABLE IF EXISTS `vote_item_choice`;
CREATE TABLE `vote_item_choice` (
  `ChoiceId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户选择ID',
  `ItemId` int(11) NOT NULL DEFAULT '0' COMMENT '投票选项ID',
  `OpenId` int(11) NOT NULL DEFAULT '0' COMMENT '用户ID',
  PRIMARY KEY (`ChoiceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "vote_item_choice"
#


#
# Structure for table "weather_city"
#

DROP TABLE IF EXISTS `weather_city`;
CREATE TABLE `weather_city` (
  `RecordId` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `UserOpenID` varchar(30) NOT NULL COMMENT '微信服务器返回的用户ID',
  `City` varchar(10) NOT NULL DEFAULT '' COMMENT '城市名',
  PRIMARY KEY (`RecordId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "weather_city"
#

