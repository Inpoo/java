/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.1.49-community : Database - db_forum
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_forum` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_forum`;

/*Table structure for table `t_reply` */

DROP TABLE IF EXISTS `t_reply`;

CREATE TABLE `t_reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `modifyTime` datetime DEFAULT NULL,
  `publishTime` datetime DEFAULT NULL,
  `topicId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `content` text,
  `title` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1undp9lie20lt1d4hspci5qj6` (`topicId`),
  KEY `FK_bcwob7cyou0ovxk8qjo62b18b` (`userId`),
  CONSTRAINT `FK_1undp9lie20lt1d4hspci5qj6` FOREIGN KEY (`topicId`) REFERENCES `t_topic` (`id`),
  CONSTRAINT `FK_bcwob7cyou0ovxk8qjo62b18b` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

/*Data for the table `t_reply` */

insert  into `t_reply`(`id`,`modifyTime`,`publishTime`,`topicId`,`userId`,`content`,`title`) values (7,NULL,'2015-01-28 21:40:56',1,9,NULL,NULL),(8,NULL,'2015-01-28 21:40:56',1,5,NULL,NULL),(9,NULL,'2015-01-28 21:40:56',1,8,NULL,NULL),(13,NULL,'2015-03-26 16:43:08',1,8,NULL,NULL),(14,NULL,'2015-03-26 16:43:39',1,8,NULL,NULL),(15,NULL,'2015-03-26 17:34:39',1,8,NULL,NULL),(16,NULL,'2015-03-26 17:39:47',1,8,NULL,NULL),(17,NULL,'2015-03-26 17:40:05',1,8,NULL,NULL),(18,NULL,'2015-03-26 17:40:30',1,8,NULL,NULL),(19,NULL,'2015-03-26 17:40:38',1,8,NULL,NULL),(23,NULL,'2015-03-26 18:16:15',1,8,'[色]和的风格和的风格和的风格和的风格和对方',NULL),(24,NULL,'2015-03-26 18:25:29',22,8,'[颤栗][色]是他告诉高峰时段分公司的高峰时段个',NULL),(25,NULL,'2015-03-26 18:28:23',22,8,'时段分公司股份等个蛋糕他让他有人提议[发呆][奋斗][酷]',NULL),(26,NULL,'2015-03-26 18:29:01',22,8,'[不开心]时段分公司的风格的说法个',NULL),(27,NULL,'2015-03-26 18:30:36',22,8,'是的高峰时段分公司的风格是个地方分公司的[怒]',NULL),(28,NULL,'2015-03-26 18:38:00',22,8,'很快就看见空间和空间和空间和空间和空间和[调皮]',NULL),(29,NULL,'2015-03-26 18:41:12',22,8,'但他个号是的风格和风格和的风格和发到个[衰]',NULL),(30,NULL,'2015-03-26 18:41:28',22,8,'是的共和党发很高的风格和的风格和对方个号[西瓜]',NULL),(31,NULL,'2015-03-26 18:44:45',22,5,'和对方个号是个似的敢死队[鄙视]',NULL),(32,NULL,'2015-03-26 18:57:00',1,5,'号是的风格和的风格和的风格和对方很高的[作]',NULL),(41,NULL,'2015-04-02 08:58:30',6,2,'的规范地方改变对方改变对方改变对方改变',NULL),(42,NULL,'2015-04-03 16:53:59',38,9,'看见好看就很快就会[颤栗]',NULL),(43,NULL,'2015-04-03 16:54:11',38,9,'[撇嘴]ihgkjbkjkljlkmnlkmlk',NULL);

/*Table structure for table `t_section` */

DROP TABLE IF EXISTS `t_section`;

CREATE TABLE `t_section` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `masterId` int(11) DEFAULT NULL,
  `zoneId` int(11) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7mwn2g1iyo47m5jkx36bqp5j4` (`masterId`),
  KEY `FK_9xsyefu9pvk24whfn7tc5xi1f` (`zoneId`),
  CONSTRAINT `FK_7mwn2g1iyo47m5jkx36bqp5j4` FOREIGN KEY (`masterId`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FK_9xsyefu9pvk24whfn7tc5xi1f` FOREIGN KEY (`zoneId`) REFERENCES `t_zone` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `t_section` */

insert  into `t_section`(`id`,`name`,`masterId`,`zoneId`,`logo`) values (1,'jsp专区',2,1,'images/logo/20150319072154.jpg'),(7,'servlet',2,1,'images/logo/20150319072205.jpg'),(8,'CSS',5,1,'images/logo/20150319072926.jpg'),(9,'html5',8,1,'images/logo/20150319072419.jpg'),(10,'jquery',5,1,'images/logo/20150319072746.jpg'),(11,'Android游戏开发',9,2,'images/logo/20150319062119.jpg'),(12,'struts',5,3,'images/logo/20150325072142.jpg'),(13,'struts2',9,3,'images/logo/20150325072209.jpg'),(14,'hibernate',8,3,'images/logo/20150325072240.jpg'),(15,'spring',5,3,'images/logo/20150325072259.jpg'),(16,'springMVC',9,3,'images/logo/20150325072326.jpg'),(17,'MyBatis3',5,3,'images/logo/20150325072352.jpg');

/*Table structure for table `t_topic` */

DROP TABLE IF EXISTS `t_topic`;

CREATE TABLE `t_topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text,
  `modifyTime` datetime DEFAULT NULL,
  `publishTime` datetime DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `sectionId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `good` int(11) NOT NULL,
  `top` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_c3cdrgmbpi1ducxxgwuhh7wxv` (`sectionId`),
  KEY `FK_4ptwcmiyvt0jjt1qse4wmywoa` (`userId`),
  CONSTRAINT `FK_4ptwcmiyvt0jjt1qse4wmywoa` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FK_c3cdrgmbpi1ducxxgwuhh7wxv` FOREIGN KEY (`sectionId`) REFERENCES `t_section` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

/*Data for the table `t_topic` */

insert  into `t_topic`(`id`,`content`,`modifyTime`,`publishTime`,`title`,`sectionId`,`userId`,`good`,`top`) values (1,'帖子1','2015-03-26 18:57:00','2015-01-28 21:30:23','帖子1',1,2,1,0),(6,'帖子2','2015-04-02 08:58:30','2015-01-28 20:25:23','帖子2',7,5,1,0),(8,'帖子内容3',NULL,NULL,'帖子3',1,2,0,1),(9,'帖子内容4',NULL,NULL,'帖子4',1,2,1,1),(22,'测试帖子[哈哈]','2015-03-26 18:44:45','2015-03-25 00:36:22','我的帖子',1,2,0,0),(23,'[长睫毛]哈哈',NULL,'2015-03-25 00:37:15','另一个帖子',7,2,0,0),(25,'[呕吐]呕吐',NULL,'2015-03-25 09:13:39','测试帖子2',7,2,0,0),(26,'的说法个的说法个的发',NULL,'2015-03-25 09:49:13','CSS帖子测试',8,9,0,0),(27,'',NULL,'2015-03-26 08:59:18','servlet帖子',7,8,0,0),(33,'<p>* {<br />\r\n&nbsp;&nbsp;&nbsp; margin: 0;<br />\r\n&nbsp;&nbsp;&nbsp; padding: 0;<br />\r\n}<br />\r\n/*--块状盒子模型--*/<br />\r\n.clearfix {<br />\r\n&nbsp;&nbsp;&nbsp; zoom: 1;<br />\r\n&nbsp;&nbsp;&nbsp; display: block;<br />\r\n}<br />\r\n.clearfix:after {<br />\r\n&nbsp;&nbsp;&nbsp; clear: both;<br />\r\n&nbsp;&nbsp;&nbsp; content: &quot;.&quot;;<br />\r\n&nbsp;&nbsp;&nbsp; display: block;<br />\r\n&nbsp;&nbsp;&nbsp; height: 0;<br />\r\n&nbsp;&nbsp;&nbsp; line-height: 0;<br />\r\n&nbsp;&nbsp;&nbsp; visibility: hidden;<br />\r\n}</p>\r\n\r\n<p>li {<br />\r\n&nbsp;&nbsp;&nbsp; list-style: none;<br />\r\n}<br />\r\n.facebox ul {<br />\r\n&nbsp;&nbsp;&nbsp; background: #fff;<br />\r\n&nbsp;&nbsp;&nbsp; margin: 0px 0px;<br />\r\n&nbsp;&nbsp;&nbsp; padding: 0;<br />\r\n}</p>\r\n',NULL,'2015-03-26 09:52:47','样式',7,8,0,0),(38,'<p>就看会比较好空间变化快结婚</p>\r\n','2015-04-03 16:54:11','2015-04-03 16:53:12','王二麻子的帖子',1,17,0,0),(42,'<p>的法国和地方和肌肤的更好交付给</p>\r\n','2015-04-03 17:00:39','2015-04-03 17:00:39','王二小的帖子',7,18,0,0),(44,'<p>是的法规和对方过后的法国和对方过后</p>\r\n','2015-04-03 17:13:24','2015-04-03 17:13:24','大壮的帖子',7,20,0,0),(45,'<p>山东分公司的法国的双方各</p>\r\n','2015-04-03 17:20:56','2015-04-03 17:20:56','徐福公司的分公司',1,20,0,0);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(20) DEFAULT NULL,
  `face` varchar(255) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `nickName` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `regTime` datetime DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `trueName` varchar(20) DEFAULT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`email`,`face`,`mobile`,`nickName`,`password`,`regTime`,`sex`,`trueName`,`type`) values (1,'1013560420@qq.com','images/user/20150312105239.jpg','13056069036','大斌','123456','2015-03-12 22:52:39','男','大斌',2),(2,'zhangsan@163.com','images/user/20150403032506.gif','13056069036','张三丰','12345678','2015-03-12 22:52:39','男','大斌',1),(5,'赵六的邮箱','images/user/hehe.gif','789654','赵六','555','2015-03-13 00:12:43','男','赵六',1),(8,'王五的邮箱','images/user/20150313083008.jpg','888','王五','888','2015-03-13 08:30:08','男','王五',1),(9,'李四的邮箱','images/user/20150313083226.jpg','999','李四','999','2015-03-13 08:32:26','男','李四',1),(15,'jiabin@sohu.com','images/user/20150402094758.jpg','88888888','火云邪神','dabin88888888','2015-04-02 21:47:58','男','大斌',1),(17,'haha@sina.com','images/user/20150403045215.gif','123456','王二麻子','12345678','2015-04-03 16:52:15','男','王二麻子',1),(18,'hehe@sohu.com','','123456','王二小','12345678','2015-04-03 16:57:14','女','王二小',1),(20,'dazhuang@168.com','','123456','大壮','12345678','2015-04-03 17:12:56','男','大壮',1);

/*Table structure for table `t_zone` */

DROP TABLE IF EXISTS `t_zone`;

CREATE TABLE `t_zone` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_zone` */

insert  into `t_zone`(`id`,`name`,`description`) values (1,'Java web','欢迎来到Java web的世界！'),(2,'Android4','欢迎来到Android4的世界！'),(3,'常用框架','常用Web框架');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
