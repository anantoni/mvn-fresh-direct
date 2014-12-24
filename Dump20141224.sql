CREATE DATABASE  IF NOT EXISTS `fd_schema` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `fd_schema`;
-- MySQL dump 10.13  Distrib 5.6.19, for linux-glibc2.5 (x86_64)
--
-- Host: 127.0.0.1    Database: fd_schema
-- ------------------------------------------------------
-- Server version	5.5.40-0ubuntu1

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
-- Table structure for table `ADDRESSES`
--

DROP TABLE IF EXISTS `ADDRESSES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ADDRESSES` (
  `address_id` int(11) NOT NULL AUTO_INCREMENT,
  `town_id` int(11) NOT NULL,
  `street_id` int(11) NOT NULL,
  `street_number` int(11) DEFAULT NULL,
  `post_code` mediumtext,
  PRIMARY KEY (`address_id`),
  KEY `fk_towns_has_streets_streets1_idx` (`street_id`),
  KEY `fk_towns_has_streets_towns_idx` (`town_id`),
  CONSTRAINT `fk_towns_has_streets_streets1` FOREIGN KEY (`street_id`) REFERENCES `STREETS` (`street_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_towns_has_streets_towns` FOREIGN KEY (`town_id`) REFERENCES `TOWNS` (`town_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ADDRESSES`
--

LOCK TABLES `ADDRESSES` WRITE;
/*!40000 ALTER TABLE `ADDRESSES` DISABLE KEYS */;
INSERT INTO `ADDRESSES` VALUES (7,13,8,6,'13451'),(8,14,9,137,'16121'),(9,15,10,122,'16189'),(10,16,11,134,'15199'),(11,13,13,121,'13451'),(12,13,14,16,'13451');
/*!40000 ALTER TABLE `ADDRESSES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CREDIT_CARDS`
--

DROP TABLE IF EXISTS `CREDIT_CARDS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CREDIT_CARDS` (
  `credit_card_id` int(11) NOT NULL AUTO_INCREMENT,
  `credit_card_number` bigint(20) NOT NULL,
  PRIMARY KEY (`credit_card_id`),
  UNIQUE KEY `unique_credit_card_id` (`credit_card_id`),
  UNIQUE KEY `unique_credit_card_number` (`credit_card_number`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CREDIT_CARDS`
--

LOCK TABLES `CREDIT_CARDS` WRITE;
/*!40000 ALTER TABLE `CREDIT_CARDS` DISABLE KEYS */;
INSERT INTO `CREDIT_CARDS` VALUES (6,6911111111),(3,6934668446),(2,6944275531),(1,6970236358),(4,6971817697),(5,6999999999);
/*!40000 ALTER TABLE `CREDIT_CARDS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `FULL_USER_PROFILE`
--

DROP TABLE IF EXISTS `FULL_USER_PROFILE`;
/*!50001 DROP VIEW IF EXISTS `FULL_USER_PROFILE`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `FULL_USER_PROFILE` AS SELECT 
 1 AS `user_id`,
 1 AS `login_name`,
 1 AS `first_name`,
 1 AS `last_name`,
 1 AS `email`,
 1 AS `credit_limit`,
 1 AS `current_balance`,
 1 AS `town_name`,
 1 AS `street_name`,
 1 AS `street_number`,
 1 AS `post_code`,
 1 AS `password`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `MANAGERS`
--

DROP TABLE IF EXISTS `MANAGERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MANAGERS` (
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_managers_users` FOREIGN KEY (`user_id`) REFERENCES `USERS` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MANAGERS`
--

LOCK TABLES `MANAGERS` WRITE;
/*!40000 ALTER TABLE `MANAGERS` DISABLE KEYS */;
INSERT INTO `MANAGERS` VALUES (22),(23);
/*!40000 ALTER TABLE `MANAGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ORDERED_WITH_CREDIT_CARD`
--

DROP TABLE IF EXISTS `ORDERED_WITH_CREDIT_CARD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ORDERED_WITH_CREDIT_CARD` (
  `order_id` int(11) NOT NULL DEFAULT '0',
  `credit_card_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`credit_card_id`,`order_id`),
  KEY `lnk_ORDERED_WITH_CREDIT_CARD_ORDERS` (`order_id`),
  CONSTRAINT `lnk_ORDERED_WITH_CREDIT_CARD_CREDIT_CARDS` FOREIGN KEY (`credit_card_id`) REFERENCES `CREDIT_CARDS` (`credit_card_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `lnk_ORDERED_WITH_CREDIT_CARD_ORDERS` FOREIGN KEY (`order_id`) REFERENCES `ORDERS` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ORDERED_WITH_CREDIT_CARD`
--

LOCK TABLES `ORDERED_WITH_CREDIT_CARD` WRITE;
/*!40000 ALTER TABLE `ORDERED_WITH_CREDIT_CARD` DISABLE KEYS */;
INSERT INTO `ORDERED_WITH_CREDIT_CARD` VALUES (3,2),(5,1),(6,3),(7,1),(8,1),(9,2),(10,3),(12,4),(13,4),(14,5),(15,6),(16,4);
/*!40000 ALTER TABLE `ORDERED_WITH_CREDIT_CARD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ORDERS`
--

DROP TABLE IF EXISTS `ORDERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ORDERS` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_date` date NOT NULL,
  `customer_id` int(11) NOT NULL,
  `total_cost` int(11) NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `fk_orders_users1_idx` (`customer_id`),
  CONSTRAINT `fk_orders_users1` FOREIGN KEY (`customer_id`) REFERENCES `USERS` (`user_id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ORDERS`
--

LOCK TABLES `ORDERS` WRITE;
/*!40000 ALTER TABLE `ORDERS` DISABLE KEYS */;
INSERT INTO `ORDERS` VALUES (3,'2014-12-18',20,204),(5,'2014-12-18',20,504),(6,'2014-12-18',20,531),(7,'2014-12-18',20,392),(8,'2014-12-20',22,4943),(9,'2014-12-20',23,4966),(10,'2014-12-20',21,1635),(12,'2014-12-21',25,64),(13,'2014-12-21',25,171),(14,'2014-12-21',25,254),(15,'2014-12-21',25,144),(16,'2014-12-22',25,66);
/*!40000 ALTER TABLE `ORDERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ORDER_DETAILS`
--

DROP TABLE IF EXISTS `ORDER_DETAILS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ORDER_DETAILS` (
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `order_quantity` int(11) NOT NULL DEFAULT '1',
  `order_sum` int(11) NOT NULL,
  PRIMARY KEY (`order_id`,`product_id`),
  KEY `fk_order_details_products1_idx` (`product_id`),
  CONSTRAINT `fk_order_details_orders1` FOREIGN KEY (`order_id`) REFERENCES `ORDERS` (`order_id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_order_details_products1` FOREIGN KEY (`product_id`) REFERENCES `PRODUCTS` (`product_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ORDER_DETAILS`
--

LOCK TABLES `ORDER_DETAILS` WRITE;
/*!40000 ALTER TABLE `ORDER_DETAILS` DISABLE KEYS */;
INSERT INTO `ORDER_DETAILS` VALUES (6,574,10,200),(6,575,8,64),(6,576,7,77),(6,578,3,66),(6,583,4,100),(6,594,4,24),(7,1,41,164),(7,574,6,120),(7,575,3,24),(7,589,2,34),(7,604,2,50),(8,393,2,108),(8,394,5,1530),(8,401,5,1385),(8,403,5,1920),(9,393,7,378),(9,394,5,1530),(9,395,7,259),(9,396,6,516),(9,398,2,674),(9,400,3,357),(9,402,2,290),(9,405,2,626),(9,416,6,336),(10,1,9,36),(10,3,3,15),(10,5,11,44),(10,6,8,48),(10,8,40,80),(10,9,1,4),(10,338,16,48),(10,340,21,588),(10,341,20,520),(10,351,18,126),(10,352,9,126),(12,501,1,64),(13,772,1,79),(13,773,1,92),(14,551,4,200),(14,554,2,54),(15,500,2,144),(16,578,3,66);
/*!40000 ALTER TABLE `ORDER_DETAILS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PREVIOUS_DEGREE_SUPPLIERS`
--

DROP TABLE IF EXISTS `PREVIOUS_DEGREE_SUPPLIERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PREVIOUS_DEGREE_SUPPLIERS` (
  `supplier_id` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PREVIOUS_DEGREE_SUPPLIERS`
--

LOCK TABLES `PREVIOUS_DEGREE_SUPPLIERS` WRITE;
/*!40000 ALTER TABLE `PREVIOUS_DEGREE_SUPPLIERS` DISABLE KEYS */;
INSERT INTO `PREVIOUS_DEGREE_SUPPLIERS` VALUES (2),(4),(9),(11),(12),(13),(14),(18),(24),(27),(28),(29),(33),(37),(38),(6),(22),(8);
/*!40000 ALTER TABLE `PREVIOUS_DEGREE_SUPPLIERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PRODUCTS`
--

DROP TABLE IF EXISTS `PRODUCTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PRODUCTS` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(512) DEFAULT NULL,
  `list_price` int(10) NOT NULL,
  `product_group` int(11) DEFAULT NULL,
  `available_quantity` int(11) DEFAULT NULL,
  `procurement_level` int(11) DEFAULT '0',
  `procurement_quantity` int(11) DEFAULT NULL,
  `procurement_level_reached` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `fk_products_product_groups1_idx` (`product_group`),
  CONSTRAINT `fk_products_product_groups1` FOREIGN KEY (`product_group`) REFERENCES `PRODUCT_GROUPS` (`product_group_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=822 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PRODUCTS`
--

LOCK TABLES `PRODUCTS` WRITE;
/*!40000 ALTER TABLE `PRODUCTS` DISABLE KEYS */;
INSERT INTO `PRODUCTS` VALUES (1,'Just FreshDirect Milk','Organic Whole Milk',4,2,40,10,40,0),(2,'Farmland Special Request Milk','Skim Plus Milk',4,2,200,15,100,0),(3,'Horizon Organic Milk','2% Milk',5,2,97,10,50,0),(4,'Horizon Organic Milk','Whole Milk',5,2,110,10,60,0),(5,'Silk Milk','Pure Almond All Natural Almond Milk, Unsweetened',4,2,119,10,60,0),(6,'Organic Valley Milk','Nonfat',6,2,152,10,80,0),(7,'Nature\'s Yoke Eggs','Natural Cage-Free Large Brown Eggs',3,2,70,10,30,0),(8,'Giroux\'s Poultry Farms Eggs','Grade A Large Farm Fresh White Eggs',2,2,10,10,40,1),(9,'Handsome Brook Farm Eggs','Cage-Free Pasture-Raised Grade A Large Eggs',4,2,49,10,40,0),(10,'Just FreshDirect Eggs','Organic Large Brown Eggs',4,2,50,10,40,0),(338,'Abbaye du Mont des Cats Cheese','Prepackaged Cheese',3,2,361,30,162,0),(339,'Abertam Cheese','Prepackaged Cheese',10,2,108,30,366,0),(340,'Ackawi Cheese','Prepackaged Cheese',28,2,421,30,449,0),(341,'Acorn Cheese','Prepackaged Cheese',26,2,238,30,282,0),(342,'Allgauer Emmentaler Cheese','Prepackaged Cheese',9,2,229,30,135,0),(343,'Anejo Enchilado Cheese','Prepackaged Cheese',20,2,262,30,342,0),(344,'Anthoriro Cheese','Prepackaged Cheese',26,2,176,30,471,0),(345,'Ardi Gasna Cheese','Prepackaged Cheese',21,2,475,30,368,0),(346,'Asiago Cheese','Prepackaged Cheese',6,2,224,30,313,0),(347,'Balaton Cheese','Prepackaged Cheese',25,2,314,30,350,0),(348,'Barry\'s Bay Cheddar Cheese','Prepackaged Cheese',3,2,336,30,279,0),(349,'Basing Cheese','Prepackaged Cheese',24,2,434,30,135,0),(350,'Bavarian Bergkase Cheese','Prepackaged Cheese',2,2,218,30,185,0),(351,'Beauvoorde Cheese','Prepackaged Cheese',7,2,288,30,312,0),(352,'Berkswell Cheese','Prepackaged Cheese',14,2,128,30,95,0),(353,'Blue Cheese','Prepackaged Cheese',25,2,131,30,363,0),(354,'Boeren Leidenkaas Cheese','Prepackaged Cheese',11,2,325,30,486,0),(355,'Bra Cheese','Prepackaged Cheese',29,2,439,30,500,0),(356,'Buffalo Cheese','Prepackaged Cheese',18,2,343,30,387,0),(357,'Cabrales Cheese','Prepackaged Cheese',27,2,302,30,163,0),(358,'Caerphilly Cheese','Prepackaged Cheese',21,2,118,30,151,0),(359,'Cairnsmore Cheese','Prepackaged Cheese',9,2,398,30,416,0),(360,'Canestrato Cheese','Prepackaged Cheese',9,2,274,30,169,0),(361,'Castellano Cheese','Prepackaged Cheese',5,2,260,30,433,0),(362,'Castelleno Cheese','Prepackaged Cheese',22,2,485,30,343,0),(363,'Castelmagno Cheese','Prepackaged Cheese',11,2,467,30,441,0),(364,'Castigliano Cheese','Prepackaged Cheese',23,2,276,30,255,0),(365,'Comte Cheese','Prepackaged Cheese',20,2,169,30,110,0),(366,'Coolea Cheese','Prepackaged Cheese',24,2,181,30,494,0),(367,'Coquetdale Cheese','Prepackaged Cheese',24,2,160,30,322,0),(368,'Corleggy Cheese','Prepackaged Cheese',21,2,381,30,85,0),(369,'Cotherstone Cheese','Prepackaged Cheese',12,2,233,30,304,0),(370,'Cotija Cheese','Prepackaged Cheese',20,2,300,30,116,0),(371,'Coverdale Cheese','Prepackaged Cheese',26,2,467,30,349,0),(372,'Crayeux de Roncq Cheese','Prepackaged Cheese',24,2,335,30,184,0),(373,'Crottin de Chavignol Cheese','Prepackaged Cheese',26,2,157,30,333,0),(374,'Curworthy Cheese','Prepackaged Cheese',7,2,495,30,160,0),(375,'Cwmtawe Pecorino Cheese','Prepackaged Cheese',4,2,450,30,477,0),(376,'Denhany Dorset Drum Cheese','Prepackaged Cheese',3,2,230,30,149,0),(377,'Derby Cheese','Prepackaged Cheese',29,2,111,30,108,0),(378,'Doolin Cheese','Prepackaged Cheese',28,2,107,30,209,0),(379,'Dorset Blue Vinney Cheese','Prepackaged Cheese',28,2,425,30,374,0),(380,'Double Worcester Cheese','Prepackaged Cheese',28,2,446,30,380,0),(381,'Dry Jack Cheese','Prepackaged Cheese',12,2,310,30,307,0),(382,'Duddleswell Cheese','Prepackaged Cheese',6,2,437,30,373,0),(383,'Dunlop Cheese','Prepackaged Cheese',17,2,465,30,282,0),(384,'Duroblando Cheese','Prepackaged Cheese',29,2,232,30,53,0),(385,'Emmental Cheese','Prepackaged Cheese',25,2,479,30,368,0),(386,'Etorki Cheese','Prepackaged Cheese',11,2,335,30,355,0),(387,'Feta Cheese','Prepackaged Cheese',22,2,177,30,136,0),(388,'Gouda Cheese','Prepackaged Cheese',5,2,428,30,258,0),(389,'Graviera Cheese','Prepackaged Cheese',11,2,384,30,432,0),(390,'Mozzarella Cheese','Prepackaged Cheese',11,2,438,30,98,0),(391,'Parmegiana Cheese','Prepackaged Cheese',29,2,166,30,125,0),(392,'Pecorino Romano Cheese','Prepackaged Cheese',16,2,428,30,296,0),(393,'air conditioner','Household Appliance',54,7,87,15,94,0),(394,'alarm clock','Household Appliance',306,7,81,15,50,0),(395,'answering machine','Household Appliance',37,7,44,15,68,0),(396,'BBQ grill','Household Appliance',86,7,67,15,67,0),(397,'barbecue grill','Household Appliance',136,7,62,15,46,0),(398,'blender','Household Appliance',337,7,63,15,87,0),(399,'blowdryer','Household Appliance',257,7,98,15,70,0),(400,'burglar alarm','Household Appliance',119,7,78,15,67,0),(401,'calculator','Household Appliance',277,7,76,15,84,0),(402,'camera','Household Appliance',145,7,90,15,54,0),(403,'can opener','Household Appliance',384,7,77,15,49,0),(404,'CD player','Household Appliance',395,7,83,15,57,0),(405,'ceiling fan','Household Appliance',313,7,69,15,57,0),(406,'cell phone','Household Appliance',310,7,85,15,70,0),(407,'clock','Household Appliance',339,7,74,15,73,0),(408,'clothes dryer','Household Appliance',61,7,70,15,67,0),(409,'clothes washer','Household Appliance',161,7,65,15,73,0),(410,'coffee grinder','Household Appliance',288,7,94,15,89,0),(411,'coffee maker','Household Appliance',290,7,52,15,44,0),(412,'computer','Household Appliance',176,7,61,15,75,0),(413,'convection oven','Household Appliance',88,7,68,15,61,0),(414,'copier','Household Appliance',183,7,78,15,42,0),(415,'crock pot','Household Appliance',319,7,67,15,59,0),(416,'curling iron','Household Appliance',56,7,61,15,64,0),(417,'dishwasher','Household Appliance',208,7,51,15,96,0),(418,'doorbell','Household Appliance',73,7,60,15,46,0),(419,'dryer','Household Appliance',341,7,70,15,54,0),(420,'edger','Household Appliance',399,7,62,15,43,0),(421,'electric blanket','Household Appliance',212,7,78,15,75,0),(422,'electric drill','Household Appliance',247,7,85,15,42,0),(423,'electric fan','Household Appliance',332,7,82,15,99,0),(424,'electric guitar','Household Appliance',147,7,97,15,85,0),(425,'electric keyboard','Household Appliance',207,7,55,15,75,0),(426,'electric pencil sharpener','Household Appliance',78,7,99,15,100,0),(427,'electric razor','Household Appliance',204,7,84,15,49,0),(428,'electric toothbrush','Household Appliance',295,7,64,15,52,0),(429,'espresso maker','Household Appliance',109,7,71,15,45,0),(430,'fan','Household Appliance',54,7,63,15,97,0),(431,'fax machine','Household Appliance',66,7,84,15,49,0),(432,'fire alarm','Household Appliance',100,7,79,15,66,0),(433,'fire extinguisher','Household Appliance',209,7,78,15,87,0),(434,'fireplace','Household Appliance',225,7,68,15,68,0),(435,'flashlight','Household Appliance',46,7,78,15,75,0),(436,'flatscreen TV','Household Appliance',177,7,100,15,69,0),(437,'food processor','Household Appliance',311,7,60,15,61,0),(438,'freezer','Household Appliance',330,7,58,15,69,0),(439,'furnace','Household Appliance',210,7,66,15,65,0),(440,'garage door','Household Appliance',68,7,100,15,57,0),(441,'garbage disposal','Household Appliance',220,7,72,15,95,0),(442,'GPS','Household Appliance',46,7,79,15,72,0),(443,'grill','Household Appliance',141,7,95,15,56,0),(444,'hair clippers','Household Appliance',43,7,55,15,52,0),(445,'hair dryer','Household Appliance',361,7,71,15,73,0),(446,'headphones','Household Appliance',79,7,61,15,69,0),(447,'heater','Household Appliance',319,7,92,15,45,0),(448,'hood','Household Appliance',397,7,63,15,89,0),(449,'hot plate','Household Appliance',248,7,99,15,75,0),(450,'humidifier','Household Appliance',111,7,96,15,76,0),(451,'ice cream maker','Household Appliance',306,7,55,15,62,0),(452,'iron','Household Appliance',72,7,79,15,89,0),(453,'juice maker','Household Appliance',156,7,86,15,62,0),(454,'kerosene heater','Household Appliance',312,7,77,15,41,0),(455,'lamp','Household Appliance',229,7,63,15,56,0),(456,'lantern','Household Appliance',165,7,93,15,75,0),(457,'laptop','Household Appliance',396,7,61,15,56,0),(458,'lawn mower','Household Appliance',388,7,71,15,61,0),(459,'leaf blower','Household Appliance',357,7,90,15,70,0),(460,'light','Household Appliance',43,7,84,15,82,0),(461,'microwave oven','Household Appliance',95,7,56,15,40,0),(462,'mixer','Household Appliance',57,7,55,15,66,0),(463,'mousetrap','Household Appliance',229,7,70,15,58,0),(464,'MP3 player','Household Appliance',104,7,91,15,44,0),(465,'oven','Household Appliance',363,7,84,15,97,0),(466,'percolator','Household Appliance',100,7,98,15,45,0),(467,'pressure cooker','Household Appliance',139,7,84,15,54,0),(468,'printer','Household Appliance',94,7,89,15,95,0),(469,'radio','Household Appliance',161,7,89,15,77,0),(470,'range','Household Appliance',351,7,75,15,69,0),(471,'record player','Household Appliance',222,7,80,15,99,0),(472,'refrigerator','Household Appliance',63,7,68,15,54,0),(473,'rotisserie','Household Appliance',268,7,85,15,55,0),(474,'scale','Household Appliance',270,7,98,15,72,0),(475,'scanner','Household Appliance',285,7,69,15,45,0),(476,'sewing machine','Household Appliance',210,7,90,15,94,0),(477,'smoke detector','Household Appliance',283,7,70,15,89,0),(478,'stapler','Household Appliance',327,7,60,15,62,0),(479,'stereo','Household Appliance',194,7,79,15,58,0),(480,'stove','Household Appliance',351,7,96,15,83,0),(481,'telephone','Household Appliance',261,7,99,15,59,0),(482,'television','Household Appliance',221,7,53,15,93,0),(483,'timer','Household Appliance',80,7,69,15,81,0),(484,'toaster','Household Appliance',266,7,92,15,64,0),(485,'toaster oven','Household Appliance',90,7,51,15,83,0),(486,'torch','Household Appliance',296,7,58,15,88,0),(487,'trash compactor','Household Appliance',274,7,78,15,69,0),(488,'trimmer','Household Appliance',112,7,52,15,61,0),(489,'TV','Household Appliance',101,7,92,15,60,0),(490,'vacuum cleaner','Household Appliance',343,7,63,15,92,0),(491,'vaporizer','Household Appliance',392,7,67,15,60,0),(492,'VCR','Household Appliance',47,7,85,15,84,0),(493,'video camera','Household Appliance',244,7,81,15,60,0),(494,'video game machine','Household Appliance',396,7,52,15,50,0),(495,'waffle iron','Household Appliance',145,7,54,15,68,0),(496,'walkie-talkie','Household Appliance',57,7,53,15,53,0),(497,'washing machine','Household Appliance',264,7,61,15,59,0),(498,'watch','Household Appliance',399,7,66,15,87,0),(499,'water heater','Household Appliance',64,7,58,15,96,0),(500,'HTH 3-in-1 Chlorinating Skimmer Tablets','Chemical Product',72,3,63,15,83,0),(501,'HTH Chlorinating Granules','Chemical Product',64,3,85,15,66,0),(502,'HTH Shock N Swim','Chemical Product',58,3,92,15,81,0),(503,'HTH Stabilizer & Conditioner','Chemical Product',106,3,71,15,49,0),(504,'HTH Super Shock N Swim','Chemical Product',77,3,62,15,71,0),(505,'Kem Tek All in One Chlorinating Granules','Chemical Product',20,3,79,15,48,0),(506,'Kem Tek Cal Chlor Chlorinating Granules','Chemical Product',29,3,79,15,93,0),(507,'Kem Tek Chlorine Stabilizer','Chemical Product',50,3,82,15,86,0),(508,'Kem Tek Kemklor II Chlorinating Liquid','Chemical Product',87,3,52,15,100,0),(509,'Kem Tek Swimming Pool 1 inch Chlorinating Tab','Chemical Product',23,3,80,15,52,0),(510,'Kem Tek Swimming Pool 3 inch Chlorinating Tab','Chemical Product',102,3,53,15,76,0),(511,'Kem Tek Swimming Pool Chlorinating Sticks','Chemical Product',40,3,88,15,83,0),(512,'Kem Tek Swimming Pool Floating Chlorinator','Chemical Product',33,3,74,15,59,0),(513,'Bayer Triple Action All Purpose Plant Food RT','Chemical Product',77,3,74,15,45,0),(514,'Bonide Perennial Plant Food','Chemical Product',124,3,87,15,74,0),(515,'Bonide Plant Starter 5-20-10','Chemical Product',126,3,70,15,99,0),(516,'Miracle Gro Lawn Food 36-6-6','Chemical Product',42,3,75,15,94,0),(517,'Miracle-Gro Water Soluble Lawn Food 36-6-6-11','Chemical Product',126,3,86,15,67,0),(518,'Scotts EcoSense Natural Lawn Fertilizer 10-1-','Chemical Product',45,3,52,15,47,0),(519,'Scotts Enriched Lawn Soil with Scotts Starter','Chemical Product',118,3,60,15,90,0),(520,'Scotts Lawn Fertilizer 27-3-4','Chemical Product',33,3,70,15,85,0),(521,'Scotts LawnPro Step 1 for Seeding 18-23-4-08/','Chemical Product',36,3,94,15,83,0),(522,'Scotts LawnPro Super TurfBuilder Lawn Fertili','Chemical Product',59,3,87,15,79,0),(523,'Scotts Patchmaster Lawn Repair Mix Grass Seed','Chemical Product',112,3,85,15,77,0),(524,'Scotts Pure Premium Bermudagrass Mix 1-7-4','Chemical Product',86,3,86,15,97,0),(525,'Scotts Pure Premium Zoysiagrass Seed','Chemical Product',56,3,76,15,42,0),(526,'Scotts Starter Fertilizer','Chemical Product',91,3,96,15,89,0),(527,'Scotts Turf Builder Lawn Fertilizer 16-2-3 an','Chemical Product',110,3,70,15,67,0),(528,'Scotts Turf Builder Lawn Fertilizer 22-2-2 an','Chemical Product',85,3,77,15,64,0),(529,'Scotts Turf Builder Lawn Fertilizer 27-3-4 wi','Chemical Product',62,3,72,15,49,0),(530,'Scotts Turf Builder Lawn Fertilizer 29-3-4 wi','Chemical Product',88,3,66,15,75,0),(531,'Scotts Turf Builder Plus 2 Lawn Fertilizer 28','Chemical Product',109,3,72,15,55,0),(532,'Scotts Turf Builder Plus HALTS Lawn Fertilize','Chemical Product',27,3,54,15,62,0),(533,'Scotts Turf Builder Pro Lawn Fertilizer 29-3-','Chemical Product',85,3,89,15,92,0),(534,'Scotts Turf Builder Pro Lawn Fertilizer 31-3-','Chemical Product',72,3,95,15,64,0),(535,'Scotts Turf Builder with Halts Crabgrass Prev','Chemical Product',88,3,94,15,61,0),(536,'Scotts Turf Builder with Moss Control','Chemical Product',53,3,87,15,54,0),(537,'Scotts Turf Builder with Moss Control-09/04/2','Chemical Product',55,3,65,15,58,0),(538,'Scotts Turf Builder with Moss Control-12/16/2','Chemical Product',109,3,86,15,53,0),(539,'Scotts Turf Builder with Plus 2 Weed Control-','Chemical Product',87,3,92,15,79,0),(540,'Scotts Wintercare Fall Lawn Fertilizer 22-3-1','Chemical Product',99,3,87,15,59,0),(541,'Scotts Wintercare Fall Lawn Fertilizer 22-4-1','Chemical Product',35,3,59,15,63,0),(542,'Scotts Wintercare Plus 2 Fall Lawn Fertiliz 2','Chemical Product',29,3,85,15,45,0),(543,'Scotts Wintercare Plus 2 Fall Lawn Fertiliz 2','Chemical Product',119,3,80,15,88,0),(544,'Scotts Wintercare Plus 2 Fall Lawn Fertilizer','Chemical Product',125,3,100,15,67,0),(545,'Scotts Wintercare Pro Fall Lawn Fertilizer 24','Chemical Product',71,3,92,15,43,0),(546,'Scotts Winterizer Fall Lawn Fertilizer 22-3-1','Chemical Product',101,3,75,15,82,0),(547,'Scotts Winterizer with Plus 2 Weed Control-10','Chemical Product',68,3,60,15,85,0),(548,'BP Select Motor Oil 5W20','Industrial Product',93,4,61,15,62,0),(549,'Castrol GTX High Mileage Motor Oil 10W30','Industrial Product',82,4,71,15,75,0),(550,'Castrol GTX Motor Oil 10W30-08/04/2010','Industrial Product',84,4,77,15,65,0),(551,'Castrol GTX SynBlend Premium Synthetic Blend ','Industrial Product',50,4,53,15,80,0),(552,'Chevron Supreme Motor Oil 5W20','Industrial Product',69,4,87,15,72,0),(553,'Chevron Supreme Synthetic Motor Oils 10W30, 5','Industrial Product',70,4,96,15,83,0),(554,'Mobil 1 Extended Performance Motor Oil 10W30','Industrial Product',27,4,65,15,54,0),(555,'Mobil 1 High Mileage Motor Oil, 10W30','Industrial Product',42,4,50,15,86,0),(556,'Mobil 1 Motor Oil 0W20','Industrial Product',23,4,83,15,56,0),(557,'Pennzoil Ultra Class Full Synthetic Motor Oil','Industrial Product',86,4,72,15,65,0),(558,'Pennzoil Ultra Euro 5W-40 Full Synthetic Moto','Industrial Product',68,4,61,15,89,0),(559,'Quaker State Motorcycle 4 Cycle 20W50 Motor O','Industrial Product',30,4,80,15,48,0),(560,'Castrol GT LMA Brake Fluid-07/16/2007','Industrial Product',44,4,80,15,81,0),(561,'Castrol GT LMA Brake Fluid-Old Product','Industrial Product',82,4,91,15,45,0),(562,'Gumout DOT 3 Brake Fluid','Industrial Product',50,4,56,15,61,0),(563,'Gunk DOT 5 Silicone Brake Fluid','Industrial Product',44,4,73,15,52,0),(564,'Pennzoil Super Heavy Duty DOT 4 and DOT 3 Bra','Industrial Product',32,4,66,15,91,0),(565,'Prestone Heavy Duty Brake Fluid-Old Product','Industrial Product',28,4,82,15,86,0),(566,'Prestone Synthetic Brake Fluid','Industrial Product',39,4,94,15,91,0),(567,'Prestone Synthetic DOT 4','Industrial Product',23,4,87,15,78,0),(568,'Prestone Synthetic Hi Temp Brake Fluid','Industrial Product',52,4,64,15,66,0),(569,'Pyroil DOT 3 Brake Fluid','Industrial Product',24,4,92,15,54,0),(570,'STP Heavy Duty Brake Fluid, DOT 3-06/01/2011','Industrial Product',17,4,52,15,69,0),(571,'Wagner DOT 4 Brake Fluid','Industrial Product',56,4,64,15,77,0),(572,'Wagner Premium Brake Fluid-11/26/2002','Industrial Product',19,4,61,15,71,0),(573,'Wagner Premium Brake Fluid-Old Product','Industrial Product',47,4,76,15,98,0),(574,'Achoccha','Vegetable or Fruit',20,1,198,50,138,0),(575,'Amaranth','Vegetable or Fruit',8,1,283,50,134,0),(576,'Angelica','Vegetable or Fruit',11,1,355,50,197,0),(577,'Anise','Vegetable or Fruit',12,1,373,50,282,0),(578,'Apple','Vegetable or Fruit',22,1,361,50,122,0),(579,'Arrowroot','Vegetable or Fruit',16,1,263,50,139,0),(580,'Arrugula','Vegetable or Fruit',7,1,318,50,266,0),(581,'Artichoke, globe','Vegetable or Fruit',6,1,333,50,229,0),(582,'Artichoke, Jerusalem','Vegetable or Fruit',9,1,293,50,191,0),(583,'Asparagus','Vegetable or Fruit',25,1,269,50,192,0),(584,'Atemoya','Vegetable or Fruit',13,1,259,50,120,0),(585,'Avocado','Vegetable or Fruit',2,1,332,50,165,0),(586,'Balsam Apple','Vegetable or Fruit',7,1,208,50,249,0),(587,'Balsam Pear','Vegetable or Fruit',18,1,229,50,219,0),(588,'Bambara groundnut','Vegetable or Fruit',12,1,349,50,207,0),(589,'Bamboo','Vegetable or Fruit',17,1,284,50,134,0),(590,'Banana and Plantains','Vegetable or Fruit',3,1,357,50,269,0),(591,'Barbados Cherry','Vegetable or Fruit',6,1,394,50,111,0),(592,'Beans','Vegetable or Fruit',18,1,363,50,217,0),(593,'Beet','Vegetable or Fruit',5,1,332,50,179,0),(594,'Blackberry','Vegetable or Fruit',6,1,365,50,237,0),(595,'Blueberry','Vegetable or Fruit',14,1,263,50,114,0),(596,'Bok Choy, see Chinese Cabbage','Vegetable or Fruit',5,1,373,50,289,0),(597,'Boniato (Tropical Sweetpotato)','Vegetable or Fruit',13,1,272,50,255,0),(598,'Broccoli','Vegetable or Fruit',6,1,217,50,284,0),(599,'Broccoli, Chinese','Vegetable or Fruit',15,1,397,50,125,0),(600,'Broccoli, Raab','Vegetable or Fruit',15,1,394,50,190,0),(601,'Brussels sprouts','Vegetable or Fruit',16,1,321,50,181,0),(602,'Bunch Grape','Vegetable or Fruit',14,1,315,50,152,0),(603,'Burdock','Vegetable or Fruit',17,1,383,50,137,0),(604,'Cabbage','Vegetable or Fruit',25,1,231,50,197,0),(605,'Cabbage, sea-kale','Vegetable or Fruit',16,1,346,50,100,0),(606,'Cabbage, swamp','Vegetable or Fruit',14,1,231,50,279,0),(607,'Calabaza (Tropical Pumpkin)','Vegetable or Fruit',19,1,218,50,131,0),(608,'Cantaloupes and Muskmelons','Vegetable or Fruit',5,1,338,50,132,0),(609,'Capers','Vegetable or Fruit',20,1,254,50,238,0),(610,'Carambola (Star Fruit)','Vegetable or Fruit',4,1,302,50,174,0),(611,'Cardoon','Vegetable or Fruit',7,1,343,50,204,0),(612,'Carrot','Vegetable or Fruit',21,1,386,50,192,0),(613,'Cassava','Vegetable or Fruit',8,1,335,50,215,0),(614,'Cauliflower','Vegetable or Fruit',2,1,239,50,173,0),(615,'Celeriac','Vegetable or Fruit',22,1,385,50,218,0),(616,'Celery','Vegetable or Fruit',14,1,241,50,218,0),(617,'Celtuce','Vegetable or Fruit',2,1,266,50,248,0),(618,'Chard','Vegetable or Fruit',8,1,290,50,204,0),(619,'Chaya','Vegetable or Fruit',17,1,306,50,239,0),(620,'Chayote','Vegetable or Fruit',3,1,244,50,108,0),(621,'Chicory','Vegetable or Fruit',2,1,343,50,219,0),(622,'Chinese Cabbage (Bok Choy)','Vegetable or Fruit',5,1,327,50,230,0),(623,'Chinese Jujube','Vegetable or Fruit',22,1,298,50,269,0),(624,'Chives','Vegetable or Fruit',17,1,275,50,149,0),(625,'Chrysanthemum','Vegetable or Fruit',24,1,300,50,187,0),(626,'Chufa','Vegetable or Fruit',19,1,321,50,287,0),(627,'Cilantro','Vegetable or Fruit',23,1,361,50,205,0),(628,'Citron','Vegetable or Fruit',3,1,292,50,114,0),(629,'Coconut Palm','Vegetable or Fruit',6,1,216,50,129,0),(630,'Collards','Vegetable or Fruit',17,1,354,50,196,0),(631,'Comfrey','Vegetable or Fruit',18,1,242,50,260,0),(632,'Corn salad','Vegetable or Fruit',25,1,246,50,274,0),(633,'Corn','Vegetable or Fruit',21,1,233,50,293,0),(634,'Cuban Sweet Potato','Vegetable or Fruit',4,1,387,50,252,0),(635,'Cucumber','Vegetable or Fruit',7,1,234,50,107,0),(636,'Cucumber, Armenian','Vegetable or Fruit',4,1,369,50,259,0),(637,'Cucmber, Chinese','Vegetable or Fruit',8,1,208,50,289,0),(638,'Cushcush','Vegetable or Fruit',5,1,241,50,204,0),(639,'Daikon --see Radish, Chinese','Vegetable or Fruit',18,1,210,50,252,0),(640,'Dandelion','Vegetable or Fruit',24,1,227,50,266,0),(641,'Dasheen (also called taro, eddoe, malanga)','Vegetable or Fruit',10,1,325,50,102,0),(642,'Dill','Vegetable or Fruit',24,1,207,50,280,0),(643,'Eggplant','Vegetable or Fruit',19,1,295,50,120,0),(644,'Endive','Vegetable or Fruit',8,1,274,50,198,0),(645,'Eugenia','Vegetable or Fruit',20,1,387,50,145,0),(646,'Fennel','Vegetable or Fruit',8,1,258,50,189,0),(647,'Fig','Vegetable or Fruit',18,1,212,50,151,0),(648,'Galia Muskmelon','Vegetable or Fruit',24,1,339,50,262,0),(649,'Garbanzo','Vegetable or Fruit',17,1,377,50,258,0),(650,'Garlic','Vegetable or Fruit',5,1,224,50,292,0),(651,'Gherkin, West Indian','Vegetable or Fruit',14,1,392,50,161,0),(652,'Ginger','Vegetable or Fruit',18,1,396,50,208,0),(653,'Ginseng','Vegetable or Fruit',1,1,313,50,141,0),(654,'Gourds','Vegetable or Fruit',13,1,374,50,136,0),(655,'Grape','Vegetable or Fruit',19,1,262,50,168,0),(656,'Guar','Vegetable or Fruit',7,1,320,50,151,0),(657,'Guava','Vegetable or Fruit',19,1,308,50,291,0),(658,'Hanover Salad','Vegetable or Fruit',17,1,303,50,138,0),(659,'Horseradish','Vegetable or Fruit',18,1,368,50,282,0),(660,'Horseradish tree','Vegetable or Fruit',3,1,333,50,110,0),(661,'Huckleberry','Vegetable or Fruit',6,1,308,50,101,0),(662,'Ice Plant','Vegetable or Fruit',12,1,311,50,271,0),(663,'Jaboticaba','Vegetable or Fruit',3,1,292,50,246,0),(664,'Jackfruit','Vegetable or Fruit',8,1,256,50,131,0),(665,'Jicama','Vegetable or Fruit',6,1,320,50,196,0),(666,'Jojoba','Vegetable or Fruit',2,1,336,50,177,0),(667,'Kale','Vegetable or Fruit',7,1,201,50,199,0),(668,'Kangkong','Vegetable or Fruit',9,1,350,50,224,0),(669,'Kohlrabi','Vegetable or Fruit',19,1,284,50,108,0),(670,'Leek','Vegetable or Fruit',4,1,323,50,232,0),(671,'Lentils','Vegetable or Fruit',1,1,384,50,276,0),(672,'Lettuce','Vegetable or Fruit',11,1,383,50,186,0),(673,'Longan','Vegetable or Fruit',13,1,289,50,230,0),(674,'Loquat','Vegetable or Fruit',6,1,313,50,112,0),(675,'Lovage','Vegetable or Fruit',14,1,398,50,167,0),(676,'Luffa Gourd','Vegetable or Fruit',6,1,382,50,241,0),(677,'Lychee','Vegetable or Fruit',21,1,248,50,237,0),(678,'Macadamia','Vegetable or Fruit',13,1,316,50,187,0),(679,'Malanga','Vegetable or Fruit',25,1,304,50,210,0),(680,'Mamey Sapote','Vegetable or Fruit',13,1,338,50,140,0),(681,'Mango','Vegetable or Fruit',14,1,269,50,179,0),(682,'Martynia','Vegetable or Fruit',3,1,372,50,101,0),(683,'Melon, casaba','Vegetable or Fruit',5,1,206,50,287,0),(684,'Melon, honeydew','Vegetable or Fruit',20,1,200,50,289,0),(685,'Momordica','Vegetable or Fruit',4,1,331,50,103,0),(686,'Muscadine Grape','Vegetable or Fruit',10,1,281,50,143,0),(687,'Mushroom','Vegetable or Fruit',1,1,278,50,247,0),(688,'Muskmelons and Cantaloupes','Vegetable or Fruit',14,1,312,50,278,0),(689,'Mustard','Vegetable or Fruit',19,1,382,50,182,0),(690,'Mustard collard','Vegetable or Fruit',15,1,218,50,211,0),(691,'Mustard, potherb','Vegetable or Fruit',6,1,284,50,103,0),(692,'Naranjillo','Vegetable or Fruit',10,1,271,50,162,0),(693,'Nasturtium','Vegetable or Fruit',9,1,238,50,298,0),(694,'Nectarine','Vegetable or Fruit',5,1,325,50,189,0),(695,'Okra','Vegetable or Fruit',24,1,262,50,108,0),(696,'Onion','Vegetable or Fruit',15,1,398,50,175,0),(697,'Orach','Vegetable or Fruit',14,1,377,50,142,0),(698,'Oranges, see Citrus','Vegetable or Fruit',18,1,229,50,118,0),(699,'Oriental Persimmon, see Persimmon','Vegetable or Fruit',12,1,226,50,198,0),(700,'Papaya','Vegetable or Fruit',2,1,334,50,194,0),(701,'Paprika','Vegetable or Fruit',11,1,398,50,279,0),(702,'Parsley','Vegetable or Fruit',17,1,298,50,105,0),(703,'Parsley root','Vegetable or Fruit',6,1,377,50,267,0),(704,'Parsnip','Vegetable or Fruit',22,1,239,50,146,0),(705,'Passion Fruit','Vegetable or Fruit',22,1,210,50,221,0),(706,'Peach, Plum and Nectarine Production','Vegetable or Fruit',14,1,259,50,143,0),(707,'Peas','Vegetable or Fruit',9,1,275,50,134,0),(708,'Peanut Production','Vegetable or Fruit',23,1,263,50,154,0),(709,'Pear','Vegetable or Fruit',17,1,320,50,245,0),(710,'Pecan','Vegetable or Fruit',17,1,335,50,299,0),(711,'Pepper','Vegetable or Fruit',19,1,271,50,166,0),(712,'Persimmon','Vegetable or Fruit',19,1,373,50,166,0),(713,'Pimiento','Vegetable or Fruit',19,1,332,50,227,0),(714,'Pineapple','Vegetable or Fruit',14,1,264,50,178,0),(715,'Pineapple Guava','Vegetable or Fruit',9,1,368,50,130,0),(716,'Pitaya','Vegetable or Fruit',17,1,211,50,222,0),(717,'Plum','Vegetable or Fruit',6,1,246,50,184,0),(718,'Pokeweed','Vegetable or Fruit',14,1,206,50,120,0),(719,'Pomegranate','Vegetable or Fruit',7,1,382,50,203,0),(720,'Potato','Vegetable or Fruit',9,1,243,50,228,0),(721,'Potato, sweet','Vegetable or Fruit',11,1,228,50,263,0),(722,'Pumpkin','Vegetable or Fruit',6,1,250,50,300,0),(723,'Purslane','Vegetable or Fruit',12,1,375,50,284,0),(724,'Radicchio','Vegetable or Fruit',10,1,275,50,276,0),(725,'Radish','Vegetable or Fruit',15,1,371,50,297,0),(726,'Radish, Chinese','Vegetable or Fruit',11,1,244,50,140,0),(727,'Rakkyo','Vegetable or Fruit',20,1,309,50,248,0),(728,'Rampion','Vegetable or Fruit',14,1,280,50,266,0),(729,'Raspberry','Vegetable or Fruit',23,1,335,50,107,0),(730,'Rhubarb','Vegetable or Fruit',11,1,350,50,299,0),(731,'Romaine Lettuce','Vegetable or Fruit',15,1,240,50,222,0),(732,'Roselle','Vegetable or Fruit',21,1,331,50,248,0),(733,'Rutabaga','Vegetable or Fruit',22,1,289,50,204,0),(734,'Saffron','Vegetable or Fruit',21,1,322,50,116,0),(735,'Salsify','Vegetable or Fruit',19,1,328,50,183,0),(736,'Sapodilla','Vegetable or Fruit',19,1,277,50,139,0),(737,'Sarsaparilla','Vegetable or Fruit',5,1,397,50,287,0),(738,'Sassafrass','Vegetable or Fruit',12,1,226,50,158,0),(739,'Scorzonera','Vegetable or Fruit',13,1,309,50,267,0),(740,'Sea kale','Vegetable or Fruit',15,1,274,50,128,0),(741,'Seagrape','Vegetable or Fruit',2,1,349,50,158,0),(742,'Shallot','Vegetable or Fruit',8,1,369,50,192,0),(743,'Skirret','Vegetable or Fruit',5,1,347,50,233,0),(744,'Smallage','Vegetable or Fruit',21,1,339,50,215,0),(745,'Sorrel, garden','Vegetable or Fruit',21,1,221,50,229,0),(746,'Southern pea','Vegetable or Fruit',19,1,356,50,100,0),(747,'Soybeans','Vegetable or Fruit',7,1,246,50,168,0),(748,'Spinach','Vegetable or Fruit',4,1,231,50,245,0),(749,'Spinach, Malabar','Vegetable or Fruit',23,1,302,50,109,0),(750,'Spinach, New Zealand','Vegetable or Fruit',25,1,329,50,115,0),(751,'Spondias','Vegetable or Fruit',13,1,233,50,281,0),(752,'Squash','Vegetable or Fruit',22,1,275,50,253,0),(753,'Strawberries','Vegetable or Fruit',22,1,240,50,126,0),(754,'Sugar Apple','Vegetable or Fruit',23,1,234,50,128,0),(755,'Swamp Cabbage','Vegetable or Fruit',9,1,218,50,277,0),(756,'Sweet Basil','Vegetable or Fruit',14,1,352,50,151,0),(757,'Sweet Corn','Vegetable or Fruit',19,1,294,50,279,0),(758,'Sweet potato','Vegetable or Fruit',7,1,247,50,242,0),(759,'Swiss Chard','Vegetable or Fruit',21,1,254,50,224,0),(760,'Tomatillo','Vegetable or Fruit',16,1,312,50,235,0),(761,'Tomato','Vegetable or Fruit',9,1,350,50,229,0),(762,'Tomato, husk','Vegetable or Fruit',23,1,219,50,259,0),(763,'Tomato, tree','Vegetable or Fruit',13,1,345,50,268,0),(764,'Truffles','Vegetable or Fruit',11,1,242,50,119,0),(765,'Turnip','Vegetable or Fruit',15,1,380,50,223,0),(766,'Upland cress','Vegetable or Fruit',21,1,230,50,176,0),(767,'Water celery','Vegetable or Fruit',18,1,395,50,199,0),(768,'Waterchestnut','Vegetable or Fruit',4,1,345,50,122,0),(769,'Watercress','Vegetable or Fruit',16,1,266,50,262,0),(770,'Watermelon','Vegetable or Fruit',20,1,227,50,242,0),(771,'Yams','Vegetable or Fruit',9,1,294,50,216,0),(772,'Loctite PL Landscape Block Adhesive','Gardening Product',79,6,67,10,60,0),(773,'Loctite PL VOC Compliant Landscape Block Adhe','Gardening Product',92,6,62,10,83,0),(774,'PL Landscape Block & Paver Adhesive','Gardening Product',47,6,87,10,57,0),(775,'Quikrete Bonding Adhesive No. 9902','Gardening Product',41,6,68,10,58,0),(776,'Quikrete Concrete Bonding Adhesive','Gardening Product',50,6,70,10,74,0),(777,'Sakrete Concrete Glue','Gardening Product',62,6,88,10,44,0),(778,'SikaBond Construction Adhesive','Gardening Product',92,6,67,10,46,0),(779,'Henry Company HE 128 Equine Fence Paint and P','Gardening Product',100,6,61,10,75,0),(780,'Miracle Gro Garden Soil for Roses 0.10-0.05-0','Gardening Product',82,6,75,10,77,0),(781,'Miracle-Gro Garden Soil for Roses 0.15-0.10-0','Gardening Product',92,6,71,10,89,0),(782,'Scotts Rose & Bloom Slow Release Plant Food 1','Gardening Product',82,6,56,10,71,0),(783,'Scotts Rose Planting Soil with Bone Meal and ','Gardening Product',49,6,64,10,59,0),(784,'Quikrete Fast-Setting Concrete Mix No. 1004-0','Gardening Product',46,6,93,10,70,0),(785,'Quikrete Fence Post Mix No. 1005','Gardening Product',40,6,51,10,55,0),(786,'Sakrete Fence Post Concrete','Gardening Product',56,6,69,10,64,0),(787,'Sakrete Light Weight Fence Post Concrete','Gardening Product',77,6,91,10,90,0),(788,'Hormel Chili Cook-Off Series Texas Brand Chil','Canned Food',43,5,76,30,99,0),(789,'Hormel Chili Cook-Off Series White Chicken Ch','Canned Food',6,5,97,30,83,0),(790,'Hunt\'s Chili Kit, 44.8 oz','Canned Food',43,5,62,30,99,0),(791,'Skyline Chili Original Chili, 10.5 oz, (Pack ','Canned Food',8,5,60,30,75,0),(792,'Stagg Chili Con Carne with Beans, 108 oz','Canned Food',37,5,53,30,54,0),(793,'Augason Farms Emergency Food Southwest Chili ','Canned Food',10,5,81,30,68,0),(794,'Hormel Chili No Beans, 108 oz','Canned Food',41,5,83,30,55,0),(795,'TABASCO Original Homestyle, 7 Spice Chili Sta','Canned Food',20,5,55,30,88,0),(796,'Dynasty Thai Chili Garlic Paste 6.5oz Pack of','Canned Food',3,5,83,30,47,0),(797,'Frontera White Bean Chili Starter, 25 oz, (Pa','Canned Food',6,5,54,30,65,0),(798,'Amys Chili 14.7oz Pack of 12','Canned Food',41,5,65,30,97,0),(799,'Brooks: Beans Hot In Chili Sauce Chili, 15.5 ','Canned Food',38,5,84,30,60,0),(800,'Chef Boyardee: In Tomato & Meat Sauce 15 Oz B','Canned Food',41,5,53,30,54,0),(801,'Dole Sliced Yellow Cling Peaches In Light Syr','Canned Food',9,5,87,30,69,0),(802,'Dole Mandarin Oranges in 100% Juice, 4 oz, 4 ','Canned Food',45,5,73,30,82,0),(803,'Oregon Fruit Products Pitted Red Tart Cherrie','Canned Food',23,5,63,30,58,0),(804,'Del Monte Mandarin Orange No Sugar Added Frui','Canned Food',17,5,88,30,82,0),(805,'Dole Pineapple Tidbits in 100% Pineapple Juic','Canned Food',14,5,64,30,65,0),(806,'Libby\'s Cut Green Beans, 14.5 oz, 4ct','Canned Food',25,5,82,30,50,0),(807,'Libby\'s Whole Kernel Sweet Corn, 15 oz, 4ct','Canned Food',41,5,60,30,51,0),(808,'Great Value: Corned Beef Hash, 15 Oz','Canned Food',11,5,58,30,73,0),(809,'Green Giant Cut Green Beans, 14.5 oz (Pack of','Canned Food',46,5,96,30,58,0),(810,'Libby\'s French Style Green Beans, 14.5 oz, 4c','Canned Food',3,5,69,30,90,0),(811,'Hunt\'s Petite Diced Tomatoes, 14.5 oz, 6 coun','Canned Food',24,5,77,30,89,0),(812,'Maruchan Instant Lunch: Flavor 12 Ct Chicken,','Canned Food',18,5,78,30,45,0),(813,'Cookies Papadopoulou','Cookies',5,5,100,30,50,0),(814,'Cookies Alatini','Cookies',4,5,150,13,41,0),(815,'Feta Hpeirou','Feta Cheese from Hpeiros 0.75kg',8,2,80,23,65,0),(816,'Onion','Vegetable',5,1,400,55,321,0),(817,'Microwave Oven','Household Appliance',343,7,300,321,212,0),(818,'Klinex','Chlorine',23,4,320,123,200,0),(819,'Moroccan Bananas','Bananas',32,1,50,21,21,0),(820,'Coca Cola','Coke',3,1,43,21,21,0),(821,'TestProduct','Test Descr',4,4,16,7,11,0);
/*!40000 ALTER TABLE `PRODUCTS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PRODUCT_GROUPS`
--

DROP TABLE IF EXISTS `PRODUCT_GROUPS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PRODUCT_GROUPS` (
  `product_group_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  PRIMARY KEY (`product_group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PRODUCT_GROUPS`
--

LOCK TABLES `PRODUCT_GROUPS` WRITE;
/*!40000 ALTER TABLE `PRODUCT_GROUPS` DISABLE KEYS */;
INSERT INTO `PRODUCT_GROUPS` VALUES (1,'V'),(2,'M'),(3,'C'),(4,'I'),(5,'B'),(6,'G'),(7,'H');
/*!40000 ALTER TABLE `PRODUCT_GROUPS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `STREETS`
--

DROP TABLE IF EXISTS `STREETS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `STREETS` (
  `street_id` int(11) NOT NULL AUTO_INCREMENT,
  `street_name` varchar(45) NOT NULL,
  PRIMARY KEY (`street_id`),
  UNIQUE KEY `street_name_UNIQUE` (`street_name`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `STREETS`
--

LOCK TABLES `STREETS` WRITE;
/*!40000 ALTER TABLE `STREETS` DISABLE KEYS */;
INSERT INTO `STREETS` VALUES (15,''),(14,'Alexandras'),(9,'Manolidi'),(8,'Paioniou'),(10,'Panepistimiou'),(13,'Papagou'),(11,'Syntagmatos'),(12,'Vasiliss Sofias');
/*!40000 ALTER TABLE `STREETS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SUPPLIERS`
--

DROP TABLE IF EXISTS `SUPPLIERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SUPPLIERS` (
  `supplier_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `supplier_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `supplied_quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`supplier_id`,`product_id`),
  KEY `fk_suppliers_products1_idx` (`product_id`),
  CONSTRAINT `fk_suppliers_products1` FOREIGN KEY (`product_id`) REFERENCES `PRODUCTS` (`product_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SUPPLIERS`
--

LOCK TABLES `SUPPLIERS` WRITE;
/*!40000 ALTER TABLE `SUPPLIERS` DISABLE KEYS */;
INSERT INTO `SUPPLIERS` VALUES (1,1,'Supplier1','supplier1@hotmail.com',30),(2,1,'Supplier2','supplier2@hotmail.com',50),(2,2,'Supplier2','supplier2@hotmail.com',80),(3,2,'Supplier3','supplier3@hotmail.com',70),(3,3,'Supplier3','supplier3@hotmail.com',130),(3,820,'Supplier3','supplier3@hotmail.com',43),(4,3,'Supplier4','supplier5@hotmail.com',60),(4,4,'Supplier4','supplier4@hotmail.com',110),(5,4,'Supplier5','supplier5@hotmail.com',80),(5,5,'Supplier5','supplier5@hotmail.com',140),(6,5,'Supplier6','supplier6@hotmail.com',50),(6,6,'Suplier6','supplier6@hotmail..com',150),(7,6,'Supplier7','supplier7@hotmail.com',80),(7,7,'Supplier7','supplier8@hotmail.com',150),(8,7,'Supplier8','supplier8@hotmail.com',100),(9,677,'Supplier9','Supplier@hotmail.com',23),(9,813,'Supplier9','Supplier9@hotmail.com',223),(9,814,'Supplier9','Supplier9@hotmail.com',22),(9,815,'Supplier9','Supplier9@hotmail.com',11),(9,816,'Supplier9','Supplier9@hotmail.com',11),(9,817,'Supplier9','Supplier9@hotmail.com',111),(9,818,'Supplier9','Supplier9@hotmail.com',11),(9,819,'Supplier9','Supplier9@hotmail.com',111),(9,820,'Supplier9','supplier9@hotmail.com',150),(10,812,'Supplier10','Supplier10@hotmail.com',11),(10,813,'Supplier10','Supplier10@hotmail.com',11),(11,799,'Supplier11','Supplier11@hotmail.com',111),(11,800,'Supplier11','Supplier11@hotmail.com',11),(11,814,'Supplier11','Supplier11@hotmail.com',11),(11,820,'Supplier11','Supplier11@hotmail.com',11),(12,799,'Supplier12','Supplier12@hotmail.com',123),(12,819,'Supplier12','Supplier12@hotmail.com',123),(12,820,'Supplier12','Supplier12@hotmail.com',12),(13,820,'Supplier13','Supplier13@hotmail.com',123),(14,819,'Supplier14','Supplier14@hotmail.com',11),(14,820,'Supplier14','Supplier14@hotmail.com',11),(15,813,'Supplier15','Supplier15@hotmail.com',32),(15,814,'Supplier15','Supplier15@hotmail.com',32),(16,766,'Supplier16','Supplier16@hotmail.com',21),(16,799,'Supplier16','Supplier16@hotmail.com',32),(16,810,'Supplier16','Supplier16@hotmail.com',22),(17,755,'Supplier17','Supplier17@hotmail.com',22),(18,813,'Supplier18','Supplier18@hotmail.com',11),(18,820,'Supplier18','Supplier18@hotmail.com',22),(19,766,'Supplier19','Supplier19@hotmail.com',111),(19,813,'Supplier19','Supplier19@hotmail.com',11),(20,733,'Supplier20','Supplier20@hotmail.com',44),(20,745,'Supplier20','Supplier20@hotmail.com',21),(21,722,'Supplier21','Supplier21@hotmail.com',123),(21,814,'Supplier21','Supplier21@hotmail.com',21),(22,722,'Supplier22','Supplier22@hotmail.com',21),(23,777,'Supplier23','Supplier23@hotmail.com',22),(24,778,'Supplier24','Supplier24@hotmail.com',24),(24,820,'Supplier24','Supplier24@hotmail.com',24),(25,778,'Supplier25','Supplier@hotmail.com',25),(25,788,'Supplier25','Supplier@hotmail.com',33),(25,799,'Supplier25','Supplier@hotmail.com',1),(25,811,'Supplier25','Supplier@hotmail.com',1),(25,812,'Supplier25','Supplier@hotmail.com',2),(26,813,'Supplier26','Supplier@hotmail.com',33),(27,811,'Supplier27','Supplier@hotmail.com',3),(27,820,'Supplier27','Supplier@hotmail.com',3),(28,811,'Supplier28','Supplier@hotmail.com',22),(28,820,'Supplier28','Supplier@hotmail.com',2),(29,820,'Supplier29','Supplier@hotmail.com',33),(30,811,'Supplier30','Supplier@hotmail.com',3),(31,813,'Supplier31','Supplier@hotmail.com',23),(32,811,'Supplier32','Supplier@hotmail.com',21),(33,809,'Supplier33','Supplier@hotmail.com',22),(33,820,'Supplier33','Supplier@hotmail.com',21),(34,814,'Supplier34','Supplier@hotmail.com',12),(35,813,'Supplier35','Supplier@hotmail.com',32),(36,799,'Supplier36','Supplier@hotmail.com',23),(37,820,'Supplier37','Supplier@hotmail.com',32),(38,811,'Supplier38','Supplier@hotmail.com',32),(38,820,'Supplier38','Supplier@hotmail.com',23),(39,813,'Supplier39','Supplier@hotmail.com',23),(39,821,'Supplier39','Supplier@hotmail.com',16),(40,811,'Supplier40','Supplier@hotmail.com',5),(40,813,'Supplier40','Supplier@hotmail.com',132),(40,821,'Supplier40','Supplier@hotmail.com',12);
/*!40000 ALTER TABLE `SUPPLIERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TOWNS`
--

DROP TABLE IF EXISTS `TOWNS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TOWNS` (
  `town_id` int(11) NOT NULL AUTO_INCREMENT,
  `town_name` varchar(45) NOT NULL,
  PRIMARY KEY (`town_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TOWNS`
--

LOCK TABLES `TOWNS` WRITE;
/*!40000 ALTER TABLE `TOWNS` DISABLE KEYS */;
INSERT INTO `TOWNS` VALUES (13,'Kamatero'),(14,'Kaisariani'),(15,'Athens'),(16,'Thessaloniki'),(17,'Patra'),(18,'Peristeri'),(19,'');
/*!40000 ALTER TABLE `TOWNS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USERS`
--

DROP TABLE IF EXISTS `USERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USERS` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `email` varchar(60) NOT NULL,
  `first_name` varchar(40) DEFAULT NULL,
  `last_name` varchar(40) DEFAULT NULL,
  `address_id` int(11) NOT NULL,
  `credit_limit` bigint(20) DEFAULT '0',
  `current_balance` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `login_name_UNIQUE` (`login_name`),
  KEY `fk_users_addresses1_idx` (`address_id`),
  CONSTRAINT `fk_users_addresses1` FOREIGN KEY (`address_id`) REFERENCES `ADDRESSES` (`address_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USERS`
--

LOCK TABLES `USERS` WRITE;
/*!40000 ALTER TABLE `USERS` DISABLE KEYS */;
INSERT INTO `USERS` VALUES (20,'anantoni','BlackCat13','a.antoniadis1989@gmail.com','Anastasios','Antoniadis',7,2000,1735),(21,'anantoniadis','BlackCat13','anastasios.antoniadis@gmail.com','Anastasios','Antoniadis',8,2000,1635),(22,'admin','123456789','admin@fd.com','Alex','Delis',9,5000,4943),(23,'manager','123456789','manager@fd.com','Konstantinos','Papadopoulos',10,5000,4966),(24,'lifeispeachy','BlackCat13','nick@hotmail.com','Nikos','Argyropoulos',10,2000,0),(25,'lenakatr','metallica','lena@hotmail.com','Eleni','Katrantzi',12,2000,699),(26,'Customer1','metallica','customer1@hotmail.com','Anastasios','Antoniadis',7,2000,0);
/*!40000 ALTER TABLE `USERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'fd_schema'
--
/*!50003 DROP PROCEDURE IF EXISTS `bestSellingProducts` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `bestSellingProducts`()
BEGIN
	SELECT p.product_id, p.name AS product_name, description, list_price, pg.name AS product_group, 
		   available_quantity, procurement_level, procurement_quantity, procurement_level_reached, SUM(o.order_sum) AS order_sum
    FROM ORDER_DETAILS AS o, PRODUCTS AS p, PRODUCT_GROUPS AS pg
	WHERE o.product_id = p.product_id AND p.product_group = product_group_id
    GROUP BY o.product_id
    ORDER BY SUM(o.order_sum) DESC
    LIMIT 1; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `daysGreaterThan10k` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `daysGreaterThan10k`(IN selected_month INT, IN selected_year INT)
BEGIN
	SELECT DAY(o.order_date) AS found_day
	FROM ORDERS AS o
	WHERE YEAR(o.order_date) = selected_year AND MONTH(o.order_date) = selected_month
    GROUP BY o.order_date
    HAVING SUM(o.total_cost) > 10000;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `mostExpensiveProductPerGroup` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `mostExpensiveProductPerGroup`()
BEGIN
	SELECT p1.product_id as product_id, p1.name AS product_name, p1.description AS description, p1.list_price AS list_price, p1.available_quantity As available_quantity,
		   p1.procurement_quantity AS procurement_quantity, p1.procurement_level AS procurement_level, p1.procurement_level_reached As procurement_level_reached
    FROM PRODUCTS AS p1, PRODUCT_GROUPS AS pg
    WHERE p1.product_group = pg.product_group_id AND
		  p1.list_price = 
		  (SELECT MAX(p2.list_price) AS max_list_price
			FROM PRODUCTS as p2 
			WHERE p1.product_group = p2.product_group)
	GROUP BY p1.product_group;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `mostPopularPostCodes` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `mostPopularPostCodes`(IN lim INT)
BEGIN
	SELECT a.post_code
    FROM USERS AS u, ADDRESSES AS a, ORDERS AS o
    WHERE a.address_id = u.address_id AND
			u.user_id = o.customer_id
    GROUP BY a.post_code
    ORDER BY COUNT(DISTINCT o.order_id) DESC
    LIMIT lim;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `mostPopularProducts` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `mostPopularProducts`(IN cur_date Date, IN lim INT)
BEGIN
	SELECT od.product_id AS product_id,
			p.name AS product_name,
            p.description AS description,
            p.list_price AS list_price,
            p.available_quantity AS available_quantity,
            p.procurement_level AS procurement_level,
            p.procurement_quantity AS procurement_quantity,
            p.procurement_level_reached AS procurement_level_reached,
            SUM(od.order_quantity) AS order_quantity,
            o.order_date AS order_date
    FROM PRODUCTS AS p, ORDERS AS o, ORDER_DETAILS AS od
    WHERE p.product_id = od.product_id
		AND o.order_id = od.order_id
        AND WEEK(cur_date) = WEEK(o.order_date)
        AND YEAR(cur_date) = YEAR(o.order_date)
	GROUP BY od.product_id
    ORDER BY order_quantity DESC
    LIMIT lim;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `mostPopularSuppliers` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `mostPopularSuppliers`(IN lim INT)
BEGIN
	SELECT supplier_id, supplier_name, email, SUM(s.supplied_quantity) AS total_amount_supplied
    FROM SUPPLIERS AS s
    GROUP BY s.supplier_id
    ORDER BY SUM(s.supplied_quantity) DESC
    LIMIT lim;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `neverOrderedProducts` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `neverOrderedProducts`()
BEGIN
	SELECT *
    FROM PRODUCTS AS p
	WHERE p.product_id NOT IN(
		SELECT DISTINCT o.product_id
		FROM ORDER_DETAILS AS o);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `orderSumMinMaxPerProduct` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `orderSumMinMaxPerProduct`()
BEGIN
	SELECT p.product_id as product_id, p.name AS product_name, description, list_price, available_quantity, procurement_level, procurement_quantity, 
			procurement_level_reached, MIN(o.order_sum) AS min_order_sum, MAX(o.order_sum) AS max_order_sum
    FROM PRODUCTS AS p, ORDER_DETAILS AS o
    WHERE p.product_id = o.product_id
    GROUP BY p.product_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `productsNotOrderedInMonthOfYear` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `productsNotOrderedInMonthOfYear`(IN selected_month INT, IN selected_year INT)
BEGIN
	SELECT *
    FROM PRODUCTS AS p
	WHERE p.product_id NOT IN(
		SELECT DISTINCT od.product_id
		FROM ORDER_DETAILS AS od, ORDERS AS o
        WHERE od.order_id = o.order_id AND MONTH(o.order_date) = selected_month AND YEAR(o.order_date) = selected_year);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `searchProducts` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `searchProducts`(IN product_name VARCHAR(45), IN product_description VARCHAR(512), IN product_group_name VARCHAR(40), IN supplier_name VARCHAR(45), IN order_choice VARCHAR(50))
BEGIN
	SET @s = 'SELECT * FROM PRODUCTS as p, PRODUCT_GROUPS AS pg';
				
	IF supplier_name IS NOT NULL THEN
		SET @s = CONCAT(@s, ', SUPPLIERS AS s');
	END IF;	
	
	SET @s = CONCAT(@s, ' WHERE p.product_group = pg.product_group_id');
	
	IF supplier_name IS NOT NULL THEN
		SET @s = CONCAT(@s, ' AND p.product_id = s.product_id AND s.supplier_name = ', QUOTE(supplier_name));
	END IF;
	IF product_group_name IS NOT NULL THEN
		SET @s = CONCAT(@s, ' AND pg.name = ', QUOTE(product_group_name));
	END IF;
	IF product_name IS NOT NULL THEN
		SET @s = CONCAT(@s, ' AND p.name LIKE \'%', product_name, '%\'');
	END IF;
	IF product_description IS NOT NULL THEN
		SET @s = CONCAT(@s, ' AND p.description LIKE \'%', product_description, '%\'');
	END IF;
	IF order_choice IS NOT NULL THEN
		SET @s = CONCAT(@s, order_choice);
	END IF;
	
	PREPARE stmt FROM @s;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sixDegreesOfSeparation` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sixDegreesOfSeparation`(IN supplier_id1 INT, IN supplier_id2 INT, OUT degree INT)
BEGIN
	DECLARE found INT;
	DROP TABLE IF EXISTS CURRENT_DEGREE_SUPPLIERS;
    DROP TABLE IF EXISTS PREVIOUS_DEGREE_SUPPLIERS;
    
    SET degree = 0;
	IF supplier_id1 != supplier_id2 THEN
		CREATE TABLE CURRENT_DEGREE_SUPPLIERS AS ( 
			SELECT s2.supplier_id
			FROM SUPPLIERS AS s1, SUPPLIERS AS s2
			WHERE s1.product_id = s2.product_id AND s1.supplier_id = supplier_id1 AND s1.supplier_id != s2.supplier_id
			GROUP BY s1.supplier_id, s2.supplier_id);
        SET found = EXISTS(SELECT * FROM CURRENT_DEGREE_SUPPLIERS AS d1 WHERE d1.supplier_id = supplier_id2);
        SET degree = 1;
        
        IF found != 1 THEN
			CREATE TABLE PREVIOUS_DEGREE_SUPPLIERS AS (SELECT * FROM CURRENT_DEGREE_SUPPLIERS);
			REPEAT 
                TRUNCATE CURRENT_DEGREE_SUPPLIERS;
				INSERT INTO CURRENT_DEGREE_SUPPLIERS (
					SELECT DISTINCT(s2.supplier_id)
					FROM PREVIOUS_DEGREE_SUPPLIERS AS d1, SUPPLIERS AS s1, SUPPLIERS AS s2
					WHERE d1.supplier_id = s1.supplier_id AND s1.product_id = s2.product_id AND s1.supplier_id != s2.supplier_id AND s2.supplier_id NOT IN (SELECT * FROM PREVIOUS_DEGREE_SUPPLIERS));
                
                SET degree = degree + 1;
                SET found = EXISTS(SELECT * FROM CURRENT_DEGREE_SUPPLIERS AS d1 WHERE d1.supplier_id = supplier_id2);
                TRUNCATE PREVIOUS_DEGREE_SUPPLIERS;
                INSERT INTO PREVIOUS_DEGREE_SUPPLIERS (SELECT * FROM CURRENT_DEGREE_SUPPLIERS);
			UNTIL degree > 6 OR found = 1 
            END REPEAT;
		END IF;            
	END IF;
    DROP TABLE IF EXISTS CURRENT_DEGREE_SUPPLIERS;
    DROP TABLE IF EXISTS PREVIOUS_ORDER_SUPPLIERS;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `suggestProducts` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `suggestProducts`( IN product_id INT )
BEGIN
	SELECT p.product_id as product_id, p.name AS product_name, p.description AS description, p.list_price AS list_price, p.available_quantity AS available_quantity, 
			p.procurement_level AS procurement_level, p.procurement_quantity AS procurement_quantity, p.procurement_level_reached AS procurement_level_reached, COUNT(*) AS times_ordered
	FROM ORDER_DETAILS AS od1, ORDERS AS o1, ORDERS AS o2, ORDER_DETAILS AS od2, PRODUCTS AS p
	WHERE o1.customer_id = o2.customer_id
			AND o1.order_id = od1.order_id
			AND o2.order_id = od2.order_id
			AND od1.product_id != od2.product_id
			AND od2.product_id = p.product_id
			AND od1.product_id = product_id
			GROUP BY od2.product_id
			ORDER BY times_ordered DESC;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `topClients` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `topClients`(IN lim INT)
BEGIN
	SELECT o.customer_id AS customer_id, SUM(o.total_cost) AS total_cost
    FROM ORDERS AS o
    GROUP BY o.customer_id
    ORDER BY SUM(o.total_cost) DESC
    LIMIT lim;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `FULL_USER_PROFILE`
--

/*!50001 DROP VIEW IF EXISTS `FULL_USER_PROFILE`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `FULL_USER_PROFILE` AS select `u`.`user_id` AS `user_id`,`u`.`login_name` AS `login_name`,`u`.`first_name` AS `first_name`,`u`.`last_name` AS `last_name`,`u`.`email` AS `email`,`u`.`credit_limit` AS `credit_limit`,`u`.`current_balance` AS `current_balance`,`t`.`town_name` AS `town_name`,`s`.`street_name` AS `street_name`,`a`.`street_number` AS `street_number`,`a`.`post_code` AS `post_code`,`u`.`password` AS `password` from (((`USERS` `u` join `ADDRESSES` `a`) join `TOWNS` `t`) join `STREETS` `s`) where ((`u`.`address_id` = `a`.`address_id`) and (`a`.`town_id` = `t`.`town_id`) and (`a`.`street_id` = `s`.`street_id`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-12-24  5:08:29
