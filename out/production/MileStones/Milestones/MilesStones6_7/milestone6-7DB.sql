CREATE DATABASE `kevin` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;


-- kevin.Photographers definition

CREATE TABLE `Photographers` (
  `PhotographerId` int(11) NOT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `Awarded` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`PhotographerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- kevin.Pictures definition

CREATE TABLE `Pictures` (
  `PictureId` int(11) NOT NULL,
  `Tittle` varchar(100) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `File` varchar(100) DEFAULT NULL,
  `Visits` int(11) DEFAULT NULL,
  `PhotographerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`PictureId`),
  KEY `Pictures_FK` (`PhotographerId`),
  CONSTRAINT `Pictures_FK` FOREIGN KEY (`PhotographerId`) REFERENCES `Photographers` (`PhotographerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO kevin.Photographers (PhotographerId,Name,Awarded) VALUES
	 (1,'Alberto',5),
	 (2,'Andoni',6),
	 (3,'Inaki',10);

INSERT INTO kevin.Pictures (PictureId,Tittle,`Date`,File,Visits,PhotographerId) VALUES
	 (1,'baki','2023-05-05','baki.jpg',54,1),
	 (2,'goku','2023-05-08','goku2.jpg',12736,2),
	 (3,'lufi','2023-06-03','luffi2.jpg',999,3);
