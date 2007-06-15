-- MySQL dump 10.9
--
-- Host: localhost    Database: serg
-- ------------------------------------------------------
-- Server version	4.1.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Address`
--

DROP TABLE IF EXISTS `Address`;
CREATE TABLE `Address` (
  `id` bigint(20) NOT NULL auto_increment,
  `_street` varchar(255) default NULL,
  `_city` varchar(255) default NULL,
  `_phone` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Address`
--


/*!40000 ALTER TABLE `Address` DISABLE KEYS */;
LOCK TABLES `Address` WRITE;
INSERT INTO `Address` VALUES (1,'Mekelweg','Delft','015'),(2,'Ringwade 1','Nieuwegein','030');
UNLOCK TABLES;
/*!40000 ALTER TABLE `Address` ENABLE KEYS */;

--
-- Table structure for table `Person`
--

DROP TABLE IF EXISTS `Person`;
CREATE TABLE `Person` (
  `id` bigint(20) NOT NULL auto_increment,
  `_fullname` varchar(255) default NULL,
  `_email` varchar(255) default NULL,
  `_homepage` varchar(255) default NULL,
  `_photo` varchar(255) default NULL,
  `_user_id` bigint(20) default NULL,
  `_address_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK8E488775120B4A1B` (`_address_id`),
  KEY `FK8E488775E962099` (`_user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Person`
--


/*!40000 ALTER TABLE `Person` DISABLE KEYS */;
LOCK TABLES `Person` WRITE;
INSERT INTO `Person` VALUES (1,'Arie van Deursen','A.vanDeursen@tudelft.nl','http://www.st.ewi.tudelft.nl/~arie/','http://www.st.ewi.tudelft.nl/~arie/pictures/arie-in-delft-klein.jpg',NULL,1),(2,'Jos Warmer','jos@ordina.nl','http://www.klasse.nl/who/cv-jos.html','http://www.klasse.nl/who/images/jos.gif',NULL,2),(3,'Eelco Visser','visser@acm.org','http://www.eelcovisser.net','/img/eelcovisser.jpg',1,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Person` ENABLE KEYS */;

--
-- Table structure for table `Publication`
--

DROP TABLE IF EXISTS `Publication`;
CREATE TABLE `Publication` (
  `DTYPE` varchar(31) NOT NULL default '',
  `id` bigint(20) NOT NULL auto_increment,
  `_title` varchar(255) default NULL,
  `_year` int(11) NOT NULL default '0',
  `_abstract` mediumtext,
  `_pdf` varchar(255) default NULL,
  `_number` int(11) default NULL,
  `_document` mediumtext,
  `_preprintof_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK23254A0C32096F48` (`_preprintof_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Publication`
--


/*!40000 ALTER TABLE `Publication` DISABLE KEYS */;
LOCK TABLES `Publication` WRITE;
INSERT INTO `Publication` VALUES ('Publication',1,'Model-Driven Software Evolution: A Research Agenda',2006,'Software systems need to evolve, and systems built using model-driven approaches are no exception.  What complicates model-driven engineering is that it requires multiple dimensions of evolution. In regular evolution, the modeling language is used to make the changes. In meta-model evolution, changes are required to the modeling notation.  In platform evolution, the code generators and application framework change to reflect new requirements on the target platform. Finally, in abstraction evolution, new modeling languages are added to the set of (modeling) languages to reflect increased understanding of a technical or business domain.  While MDE has been optimized for regular evolution, presently little or no support exists for metamodel, platform and abstraction evolution. In this paper, we analyze the problems raised by the evolution of model-based software systems and identify challenges to be addressed by research in this area.','',NULL,NULL,NULL),('Publication',2,'Domain-Specific Language Engineering',2007,'The goal of domain-specific languages (DSLs) is to increase the productivity of software engineers by abstracting from low-level boilerplate code. Introduction of DSLs in the software development process requires a smooth workflow for the production of DSLs themselves. This tutorial gives an overview of all aspects of DSL engineering: domain analysis, language design, syntax definition, code generation, deployment, and evolution, discussing research challenges on the way. The concepts are illustrated with DSLs for web applications built using several DSLs for DSL engineering: SDF for syntax definition, Stratego/XT for code generation, and Nix for software deployment.','dsle.pdf',NULL,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Publication` ENABLE KEYS */;

--
-- Table structure for table `Publication_Person`
--

DROP TABLE IF EXISTS `Publication_Person`;
CREATE TABLE `Publication_Person` (
  `Publication_id` bigint(20) NOT NULL default '0',
  `_authors_id` bigint(20) NOT NULL default '0',
  KEY `FK1E7A1508E43FA5E4` (`_authors_id`),
  KEY `FK1E7A1508FF0F387C` (`Publication_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Publication_Person`
--


/*!40000 ALTER TABLE `Publication_Person` DISABLE KEYS */;
LOCK TABLES `Publication_Person` WRITE;
INSERT INTO `Publication_Person` VALUES (1,1),(1,2),(1,3),(2,3),(2,2);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Publication_Person` ENABLE KEYS */;

--
-- Table structure for table `Publication_ResearchProject`
--

DROP TABLE IF EXISTS `Publication_ResearchProject`;
CREATE TABLE `Publication_ResearchProject` (
  `Publication_id` bigint(20) NOT NULL default '0',
  `_projects_id` bigint(20) NOT NULL default '0',
  PRIMARY KEY  (`Publication_id`,`_projects_id`),
  KEY `FKF139ECABC8176F81` (`_projects_id`),
  KEY `FKF139ECABFF0F387C` (`Publication_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Publication_ResearchProject`
--


/*!40000 ALTER TABLE `Publication_ResearchProject` DISABLE KEYS */;
LOCK TABLES `Publication_ResearchProject` WRITE;
INSERT INTO `Publication_ResearchProject` VALUES (1,1),(2,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Publication_ResearchProject` ENABLE KEYS */;

--
-- Table structure for table `ResearchProject`
--

DROP TABLE IF EXISTS `ResearchProject`;
CREATE TABLE `ResearchProject` (
  `id` bigint(20) NOT NULL auto_increment,
  `_fullname` varchar(255) default NULL,
  `_acronym` varchar(255) default NULL,
  `_description` mediumtext,
  `_proposal_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK20D0E4DE1069D037` (`_proposal_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ResearchProject`
--


/*!40000 ALTER TABLE `ResearchProject` DISABLE KEYS */;
LOCK TABLES `ResearchProject` WRITE;
INSERT INTO `ResearchProject` VALUES (1,'Model-Driven Software Evolution','MoDSE','The promise of model-driven engineering (MDE) is that the development and maintenance effort can be reduced by working at the model instead of the code level. Models define what is variable in a system, and code generators produce the functionality that is common in the application domain. The problem with model-driven engineering is that it can lead to a lock-in in the abstractions and generator technology adopted at project initiation. Software systems need to evolve, and systems built using model-driven approaches are no exception. What complicates model-driven engineering is that it requires multiple dimensions of evolution. In regular evolution, the modeling language is used to make the changes. In meta-model evolution, changes are required to the modeling notation. In platform evolution, the code generators and application framework change to reflect new requirements on the target platform. Finally, in abstraction evolution, new modeling languages are added to the set of (modeling) languages to reflect increased understanding of a technical or business domain. While MDE has been optimized for regular evolution, presently little or no support exists for metamodel, platform and abstraction evolution. It is this gap that this project proposes to address. The first fundamental premise of this proposal is that evolution should be a continuous process. Software development is a continuous search for recurring patterns, which can be captured using domain-specific modeling languages. After developing a number of systems using a particular meta-model, new patterns may be recognized that can be captured in a higher-level or richer meta-model. The second premise is that reengineering of legacy systems to the model-driven paradigm should be a special case of this continuous evolution, and should be performed incrementally. The goal of this project is to develop a systematic approach to model-driven software evolution. This approach includes methods, techniques, and underlying tool support. We will develop a prototype programming environment that assists software engineers with the introduction, development, and maintenance of models and domain-specific languages.',1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `ResearchProject` ENABLE KEYS */;

--
-- Table structure for table `ResearchProject_Person`
--

DROP TABLE IF EXISTS `ResearchProject_Person`;
CREATE TABLE `ResearchProject_Person` (
  `ResearchProject_id` bigint(20) NOT NULL default '0',
  `_members_id` bigint(20) NOT NULL default '0',
  PRIMARY KEY  (`ResearchProject_id`,`_members_id`),
  KEY `FK262409F615C7A21C` (`ResearchProject_id`),
  KEY `FK262409F6EDCBEF73` (`_members_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ResearchProject_Person`
--


/*!40000 ALTER TABLE `ResearchProject_Person` DISABLE KEYS */;
LOCK TABLES `ResearchProject_Person` WRITE;
INSERT INTO `ResearchProject_Person` VALUES (1,1),(1,2),(1,3);
UNLOCK TABLES;
/*!40000 ALTER TABLE `ResearchProject_Person` ENABLE KEYS */;

--
-- Table structure for table `ResearchProject_Publication`
--

DROP TABLE IF EXISTS `ResearchProject_Publication`;
CREATE TABLE `ResearchProject_Publication` (
  `ResearchProject_id` bigint(20) NOT NULL default '0',
  `_publications_id` bigint(20) NOT NULL default '0',
  PRIMARY KEY  (`ResearchProject_id`,`_publications_id`),
  KEY `FKEE4C302BDFCE62C2` (`_publications_id`),
  KEY `FKEE4C302B15C7A21C` (`ResearchProject_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ResearchProject_Publication`
--


/*!40000 ALTER TABLE `ResearchProject_Publication` DISABLE KEYS */;
LOCK TABLES `ResearchProject_Publication` WRITE;
INSERT INTO `ResearchProject_Publication` VALUES (1,1),(1,2);
UNLOCK TABLES;
/*!40000 ALTER TABLE `ResearchProject_Publication` ENABLE KEYS */;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (
  `id` bigint(20) NOT NULL auto_increment,
  `_username` varchar(255) default NULL,
  `_password` varchar(255) default NULL,
  `_person_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK285FEBD6EBC899` (`_person_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `User`
--


/*!40000 ALTER TABLE `User` DISABLE KEYS */;
LOCK TABLES `User` WRITE;
INSERT INTO `User` VALUES (1,'EelcoVisser','foo',3);
UNLOCK TABLES;
/*!40000 ALTER TABLE `User` ENABLE KEYS */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(30) NOT NULL default '',
  `password` varchar(10) default NULL,
  `name` varchar(40) default NULL,
  `email` varchar(40) default NULL,
  `url` varchar(40) default NULL,
  PRIMARY KEY  (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--


/*!40000 ALTER TABLE `user` DISABLE KEYS */;
LOCK TABLES `user` WRITE;
INSERT INTO `user` VALUES ('EelcoVisser','foo!','Eelco Visser','visser@acm.org',NULL),('DaveClarke','bar','Dave Clarke',NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

