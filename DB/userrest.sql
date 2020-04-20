DROP TABLE IF EXISTS `user`;

-- Dumping structure for table userrest.user
CREATE TABLE`user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(150) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `address` varchar(255) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table userrest.user: ~2 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`userId`, `userName`, `age`, `salary`, `address`) VALUES
	(1, 'S Dada', 26, 100000, '1200 St Laurent Blvd'),
	(2, 'N Nazia', 26, 214324, 'Lees Ave');
