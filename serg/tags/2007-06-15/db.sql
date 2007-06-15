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
INSERT INTO `Address` VALUES (1,'Mekelweg 4','Delft','+31 (015) 27 87088'),(2,'Ringwade 1','Nieuwegein','030'),(3,'Mekelweg 4','Delft','015'),(4,'','',''),(5,'','',''),(6,'','',''),(7,'','',''),(10,'','',''),(13,'','',''),(12,'','','');
UNLOCK TABLES;
/*!40000 ALTER TABLE `Address` ENABLE KEYS */;

--
-- Table structure for table `Blog`
--

DROP TABLE IF EXISTS `Blog`;
CREATE TABLE `Blog` (
  `id` bigint(20) NOT NULL auto_increment,
  `_title` varchar(255) default NULL,
  `_author_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK1FA3C2FC050EA3` (`_author_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Blog`
--


/*!40000 ALTER TABLE `Blog` DISABLE KEYS */;
LOCK TABLES `Blog` WRITE;
INSERT INTO `Blog` VALUES (1,'Transformations and Abstractions',3),(2,'Modeling with Software Factories',NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Blog` ENABLE KEYS */;

--
-- Table structure for table `BlogComment`
--

DROP TABLE IF EXISTS `BlogComment`;
CREATE TABLE `BlogComment` (
  `id` bigint(20) NOT NULL auto_increment,
  `_text` mediumtext,
  `_author_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK2315843DFC050EA3` (`_author_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `BlogComment`
--


/*!40000 ALTER TABLE `BlogComment` DISABLE KEYS */;
LOCK TABLES `BlogComment` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `BlogComment` ENABLE KEYS */;

--
-- Table structure for table `BlogEntry`
--

DROP TABLE IF EXISTS `BlogEntry`;
CREATE TABLE `BlogEntry` (
  `id` bigint(20) NOT NULL auto_increment,
  `_title` varchar(255) default NULL,
  `_created` datetime default NULL,
  `_entry` mediumtext,
  `_intro` mediumtext,
  `_body` mediumtext,
  `_blog_id` bigint(20) default NULL,
  `_category_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKB03A7DD0160CFD39` (`_blog_id`),
  KEY `FKB03A7DD028C03839` (`_category_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `BlogEntry`
--


/*!40000 ALTER TABLE `BlogEntry` DISABLE KEYS */;
LOCK TABLES `BlogEntry` WRITE;
INSERT INTO `BlogEntry` VALUES (1,'WebDSL rocks!','2007-01-06 00:06:00','only the text areas should be larger','but textareas should be a tad larger ... and now they are! It is even possible to include /wiki style markup/ in text. For instance, if I include a text between asterixes, as in *foo*, it should end up as bold text. But why do I get these strike through texts?\r\n\r\nOk, I don\'t get them anymore. It is also possible to define lists\r\n\r\n# first item\r\n# second item','wow, I can write more in the body of a blog entry! cool!',1,NULL),(2,'Global Variables','2007-01-26 00:04:00','should I really explain that?!','During on of our chats on current affairs, Martin mentioned that Lennart Kats had proposed to introduce global variables in Stratego. My first reaction was of course outrage. My second reaction was to immediately add it to the compiler. The proposal was not to add some sort of C style global variables, but rather to provide better syntax for a programming pattern that was already well established (although considered somewhat improper, at least by me).','',1,NULL),(3,'Janine kan het ook','2007-06-06 00:00:00',NULL,'hopen we','moet dat nou',NULL,NULL),(4,'foo bars','2007-06-07 00:00:00',NULL,'foo foo','bars',NULL,NULL),(5,'title here','2007-06-07 00:00:00',NULL,'foo','bar',NULL,NULL),(6,'title here','2007-06-07 07:51:11',NULL,'','',NULL,NULL),(7,'demo for martin','2007-06-07 00:00:00',NULL,'adfa','asdfa',1,NULL),(8,'for jonathan','2007-01-07 00:47:00',NULL,'foo','bar',1,NULL),(9,'demo for lennart','2007-01-08 00:28:00',NULL,'this ','that',1,NULL),(10,'title here','2007-06-08 13:34:55',NULL,'','',1,NULL),(11,'demo for steffen','2007-01-08 00:32:00',NULL,'lsajdf','asdf',1,NULL),(12,'Model-Driven Software Evolution: A Research Agenda','2007-01-26 00:02:00',NULL,'Software systems need to evolve, and systems built using model driven approaches are no exception. What complicates model driven engineering is that it requires multiple dimensions of evolution. In regular evolution, the modeling language is used to make the changes. In meta model evolution, changes are required to the modeling notation. In platform evolution, the code generators and application framework change to reflect new requirements on the target platform. Finally, in abstraction evolution, new modeling languages are added to the set of (modeling) languages to reflect increased understanding of a technical or business domain. While MDE has been optimized for regular evolution, presently little or no support exists for metamodel, platform and abstraction evolution. In this paper, we analyze the problems raised by the evolution of model based software systems and identify challenges to be addressed by research in this area.\r\n\r\n','As reads the abstract of the paper that Arie van Deursen, Jos Warmer, and I just submitted as final version to the MoDSE\'07 workshop to be held 20 March in Amsterdam (affiliated with CSMR\'07). The paper is a slight rewrite of the research proposal that gave us the funding for the MoDSE project that we are starting up at Delft University of Technology. We believe the proposal provides a good analysis of the challenges that are raised by the introduction of model based engineering in software development.',1,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `BlogEntry` ENABLE KEYS */;

--
-- Table structure for table `BlogEntry_BlogComment`
--

DROP TABLE IF EXISTS `BlogEntry_BlogComment`;
CREATE TABLE `BlogEntry_BlogComment` (
  `BlogEntry_id` bigint(20) NOT NULL default '0',
  `_comments_id` bigint(20) NOT NULL default '0',
  KEY `FKEBDE284E2B3CA026` (`_comments_id`),
  KEY `FKEBDE284E7C0B65C` (`BlogEntry_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `BlogEntry_BlogComment`
--


/*!40000 ALTER TABLE `BlogEntry_BlogComment` DISABLE KEYS */;
LOCK TABLES `BlogEntry_BlogComment` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `BlogEntry_BlogComment` ENABLE KEYS */;

--
-- Table structure for table `Blog_BlogEntry`
--

DROP TABLE IF EXISTS `Blog_BlogEntry`;
CREATE TABLE `Blog_BlogEntry` (
  `Blog_id` bigint(20) NOT NULL default '0',
  `_entries_id` bigint(20) NOT NULL default '0',
  KEY `FK6FFAD193897D5678` (`Blog_id`),
  KEY `FK6FFAD193DF6E457B` (`_entries_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Blog_BlogEntry`
--


/*!40000 ALTER TABLE `Blog_BlogEntry` DISABLE KEYS */;
LOCK TABLES `Blog_BlogEntry` WRITE;
INSERT INTO `Blog_BlogEntry` VALUES (1,1),(1,2),(1,12);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Blog_BlogEntry` ENABLE KEYS */;

--
-- Table structure for table `Blog_Category`
--

DROP TABLE IF EXISTS `Blog_Category`;
CREATE TABLE `Blog_Category` (
  `Blog_id` bigint(20) NOT NULL default '0',
  `_categories_id` bigint(20) NOT NULL default '0',
  KEY `FKA1B1C8FB897D5678` (`Blog_id`),
  KEY `FKA1B1C8FBBCB2261B` (`_categories_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Blog_Category`
--


/*!40000 ALTER TABLE `Blog_Category` DISABLE KEYS */;
LOCK TABLES `Blog_Category` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `Blog_Category` ENABLE KEYS */;

--
-- Table structure for table `Category`
--

DROP TABLE IF EXISTS `Category`;
CREATE TABLE `Category` (
  `id` bigint(20) NOT NULL auto_increment,
  `_name` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Category`
--


/*!40000 ALTER TABLE `Category` DISABLE KEYS */;
LOCK TABLES `Category` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `Category` ENABLE KEYS */;

--
-- Table structure for table `Colloquium`
--

DROP TABLE IF EXISTS `Colloquium`;
CREATE TABLE `Colloquium` (
  `id` bigint(20) NOT NULL auto_increment,
  `_mailinglist` varchar(255) default NULL,
  `_contact_id` bigint(20) default NULL,
  `_name` varchar(255) default NULL,
  `_description` mediumtext,
  `_group_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK20DDBF1A4604E30C` (`_contact_id`),
  KEY `FK20DDBF1A5B9833A0` (`_group_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Colloquium`
--


/*!40000 ALTER TABLE `Colloquium` DISABLE KEYS */;
LOCK TABLES `Colloquium` WRITE;
INSERT INTO `Colloquium` VALUES (1,'https://mailman.st.ewi.tudelft.nl/listinfo/modse-colloquium',3,'MoDSE Colloquium','The MoDSE colloquium is a series of in depth lectures in the context ofthe NWO JAQUARD project on Model Driven Software Evolution. Note that the lectures start at 10:30 and are scheduled to last until 12:30 (with a break somewhere in the middle). This should allow time for an in depth exploration of the topic as well as discussion. You are welcome to join us for lunch afterwards in the canteen. I would appreciate it if you could let me know whether or not you plan to attend so that I can arrange for coffee in more or less accurate quantities. ',1),(2,'',13,'SERG Colloquium','The SERG group meets (at least) once in the two weeks to learn about and exchange ideas on recent research carried out by the group\'s researchers (Faculty members, Postdocs, PhD students). Occasionally researchers from other organizations are invited to present their latest work.',1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Colloquium` ENABLE KEYS */;

--
-- Table structure for table `Colloquium_Presentation`
--

DROP TABLE IF EXISTS `Colloquium_Presentation`;
CREATE TABLE `Colloquium_Presentation` (
  `Colloquium_id` bigint(20) NOT NULL default '0',
  `_talks_id` bigint(20) NOT NULL default '0',
  `_presentations_id` bigint(20) default NULL,
  KEY `FKE2CD83FEE2000F8` (`Colloquium_id`),
  KEY `FKE2CD83F5E05A98A` (`_talks_id`),
  KEY `FKE2CD83FCB97BDF8` (`_presentations_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Colloquium_Presentation`
--


/*!40000 ALTER TABLE `Colloquium_Presentation` DISABLE KEYS */;
LOCK TABLES `Colloquium_Presentation` WRITE;
INSERT INTO `Colloquium_Presentation` VALUES (2,0,1),(1,0,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Colloquium_Presentation` ENABLE KEYS */;

--
-- Table structure for table `Colloquium_ResearchGroup`
--

DROP TABLE IF EXISTS `Colloquium_ResearchGroup`;
CREATE TABLE `Colloquium_ResearchGroup` (
  `Colloquium_id` bigint(20) NOT NULL default '0',
  `_groups_id` bigint(20) NOT NULL default '0',
  KEY `FKCD91591FEE2000F8` (`Colloquium_id`),
  KEY `FKCD91591F86EEDD4D` (`_groups_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Colloquium_ResearchGroup`
--


/*!40000 ALTER TABLE `Colloquium_ResearchGroup` DISABLE KEYS */;
LOCK TABLES `Colloquium_ResearchGroup` WRITE;
INSERT INTO `Colloquium_ResearchGroup` VALUES (2,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Colloquium_ResearchGroup` ENABLE KEYS */;

--
-- Table structure for table `Colloquium_ResearchProject`
--

DROP TABLE IF EXISTS `Colloquium_ResearchProject`;
CREATE TABLE `Colloquium_ResearchProject` (
  `Colloquium_id` bigint(20) NOT NULL default '0',
  `_projects_id` bigint(20) NOT NULL default '0',
  KEY `FK8AB285B9C8176F81` (`_projects_id`),
  KEY `FK8AB285B9EE2000F8` (`Colloquium_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Colloquium_ResearchProject`
--


/*!40000 ALTER TABLE `Colloquium_ResearchProject` DISABLE KEYS */;
LOCK TABLES `Colloquium_ResearchProject` WRITE;
INSERT INTO `Colloquium_ResearchProject` VALUES (1,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Colloquium_ResearchProject` ENABLE KEYS */;

--
-- Table structure for table `Conference`
--

DROP TABLE IF EXISTS `Conference`;
CREATE TABLE `Conference` (
  `id` bigint(20) NOT NULL auto_increment,
  `_fullname` varchar(255) default NULL,
  `_acronym` varchar(255) default NULL,
  `_booktitle` varchar(255) default NULL,
  `_place` varchar(255) default NULL,
  `_year` int(11) NOT NULL default '0',
  `_month` varchar(255) default NULL,
  `_url` varchar(255) default NULL,
  `_acceptance` int(11) NOT NULL default '0',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Conference`
--


/*!40000 ALTER TABLE `Conference` DISABLE KEYS */;
LOCK TABLES `Conference` WRITE;
INSERT INTO `Conference` VALUES (1,'Generative Programming and Component Engineering','GPCE\'07','Generative Programming and Component Engineering','Salzburg, Austria',2007,'October','http://www.gpce.org/07',32);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Conference` ENABLE KEYS */;

--
-- Table structure for table `Conference_Person`
--

DROP TABLE IF EXISTS `Conference_Person`;
CREATE TABLE `Conference_Person` (
  `Conference_id` bigint(20) NOT NULL default '0',
  `_editors_id` bigint(20) NOT NULL default '0',
  KEY `FK43F6C7D8E0F7E4C6` (`_editors_id`),
  KEY `FK43F6C7D835DC35B8` (`Conference_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Conference_Person`
--


/*!40000 ALTER TABLE `Conference_Person` DISABLE KEYS */;
LOCK TABLES `Conference_Person` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `Conference_Person` ENABLE KEYS */;

--
-- Table structure for table `FlickrImage`
--

DROP TABLE IF EXISTS `FlickrImage`;
CREATE TABLE `FlickrImage` (
  `id` bigint(20) NOT NULL auto_increment,
  `_photoid` varchar(255) default NULL,
  `_title` varchar(255) default NULL,
  `_username` varchar(255) default NULL,
  `_photourl` varchar(255) default NULL,
  `_square` varchar(255) default NULL,
  `_thumbnail` varchar(255) default NULL,
  `_small` varchar(255) default NULL,
  `_medium` varchar(255) default NULL,
  `_large` varchar(255) default NULL,
  `_original` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `FlickrImage`
--


/*!40000 ALTER TABLE `FlickrImage` DISABLE KEYS */;
LOCK TABLES `FlickrImage` WRITE;
INSERT INTO `FlickrImage` VALUES (1,'533515146','shadowed','eelcovisser','http://www.flickr.com/photos/eelcovisser/533515146/','http://farm2.static.flickr.com/1316/533515146_faa91c3e41_s.jpg','http://farm2.static.flickr.com/1316/533515146_faa91c3e41_t.jpg','http://farm2.static.flickr.com/1316/533515146_faa91c3e41_m.jpg','http://farm2.static.flickr.com/1316/533515146_faa91c3e41.jpg','http://farm2.static.flickr.com/1316/533515146_faa91c3e41_b.jpg','http://farm2.static.flickr.com/1316/533515146_e88394902a_o.jpg');
UNLOCK TABLES;
/*!40000 ALTER TABLE `FlickrImage` ENABLE KEYS */;

--
-- Table structure for table `Issue`
--

DROP TABLE IF EXISTS `Issue`;
CREATE TABLE `Issue` (
  `DTYPE` varchar(31) NOT NULL default '',
  `id` bigint(20) NOT NULL auto_increment,
  `_title` varchar(255) default NULL,
  `_description` mediumtext,
  `_type` varchar(255) default NULL,
  `_due` datetime default NULL,
  `_priority` int(11) NOT NULL default '0',
  `_assigned_id` bigint(20) default NULL,
  `_status` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK43AB8B93F907B40` (`_assigned_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Issue`
--


/*!40000 ALTER TABLE `Issue` DISABLE KEYS */;
LOCK TABLES `Issue` WRITE;
INSERT INTO `Issue` VALUES ('Project',1,'Stratego/XT','StrategoXT is a language and toolset for program transformation. The Stratego language provides rewrite rules for expressing basic transformations, programmable rewriting strategies for controlling the application of rules, concrete syntax for expressing the patterns of rules in the syntax of the object language, and dynamic rewrite rules for expressing context sensitive transformations, thus supporting the development of transformation components at a high level of abstraction.\r\n\r\nThe XT toolset offers a collection of extensible, reusable transformation tools, such as powerful parser and pretty printer generators and grammar engineering tools. Stratego/XT supports the development of program transformation infrastructure, domain specific languages, compilers, program generators, and a wide range of meta programming tasks. ','','2007-01-10 00:29:00',0,3,NULL),('Issue',2,'libstrc','Compiler library for the Stratego compiler','','2007-01-10 00:35:00',10,3,NULL),('Project',3,'WebDSL','Design and implement a (collection) of domain specific languages for the high level implementation of web applications with a rich data model.\r\n','',NULL,0,3,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Issue` ENABLE KEYS */;

--
-- Table structure for table `Issue_Issue`
--

DROP TABLE IF EXISTS `Issue_Issue`;
CREATE TABLE `Issue_Issue` (
  `Issue_id` bigint(20) NOT NULL default '0',
  `_issues_id` bigint(20) NOT NULL default '0',
  PRIMARY KEY  (`Issue_id`,`_issues_id`),
  KEY `FKCC08A5B3804F4FDC` (`_issues_id`),
  KEY `FKCC08A5B3F7A276DC` (`Issue_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Issue_Issue`
--


/*!40000 ALTER TABLE `Issue_Issue` DISABLE KEYS */;
LOCK TABLES `Issue_Issue` WRITE;
INSERT INTO `Issue_Issue` VALUES (1,2);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Issue_Issue` ENABLE KEYS */;

--
-- Table structure for table `Issue_Person`
--

DROP TABLE IF EXISTS `Issue_Person`;
CREATE TABLE `Issue_Person` (
  `Issue_id` bigint(20) NOT NULL default '0',
  `_assigned_id` bigint(20) NOT NULL default '0',
  PRIMARY KEY  (`Issue_id`,`_assigned_id`),
  KEY `FKC03839BB3F907B40` (`_assigned_id`),
  KEY `FKC03839BBF7A276DC` (`Issue_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Issue_Person`
--


/*!40000 ALTER TABLE `Issue_Person` DISABLE KEYS */;
LOCK TABLES `Issue_Person` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `Issue_Person` ENABLE KEYS */;

--
-- Table structure for table `Journal`
--

DROP TABLE IF EXISTS `Journal`;
CREATE TABLE `Journal` (
  `id` bigint(20) NOT NULL auto_increment,
  `_fullname` varchar(255) default NULL,
  `_acronym` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Journal`
--


/*!40000 ALTER TABLE `Journal` DISABLE KEYS */;
LOCK TABLES `Journal` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `Journal` ENABLE KEYS */;

--
-- Table structure for table `News`
--

DROP TABLE IF EXISTS `News`;
CREATE TABLE `News` (
  `id` bigint(20) NOT NULL auto_increment,
  `_title` varchar(255) default NULL,
  `_text` mediumtext,
  `_date` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `News`
--


/*!40000 ALTER TABLE `News` DISABLE KEYS */;
LOCK TABLES `News` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `News` ENABLE KEYS */;

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
  `_blog_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK8E488775120B4A1B` (`_address_id`),
  KEY `FK8E488775E962099` (`_user_id`),
  KEY `FK8E488775160CFD39` (`_blog_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Person`
--


/*!40000 ALTER TABLE `Person` DISABLE KEYS */;
LOCK TABLES `Person` WRITE;
INSERT INTO `Person` VALUES (1,'Arie van Deursen','A.vanDeursen@tudelft.nl','http://www.st.ewi.tudelft.nl/~arie/','/img/arie-in-delft-klein.jpg',1,1,NULL),(2,'Jos Warmer','jos@ordina.nl','http://www.klasse.nl/who/cv-jos.html','/img/jos.gif',NULL,2,NULL),(3,'Eelco Visser','visser@acm.org','http://www.eelcovisser.net','/img/eelcovisser.jpg',1,1,1),(4,'Martin Bravenboer','martin.bravenboer@gmail.com','http://martin.bravenboer.name','/img/bravenboer.jpg',NULL,3,NULL),(5,'Joost Visser','joost@sig.nl','','',NULL,4,NULL),(6,'Lennart Kats','lennart@cs.uu.nl','','',NULL,5,NULL),(7,'Eelco Dolstra','eelco@cs.uu.nl','','',NULL,6,NULL),(8,'Eric Bouwer','eric@cs.uu.nl','','',NULL,7,NULL),(11,'Gerardo Geest','gerardo@tudelft.nl','','',NULL,10,NULL),(14,'Sander Mak','sander@cs.uu.nl','','',NULL,13,NULL),(13,'Ali Mesbah','ali','','',NULL,12,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Person` ENABLE KEYS */;

--
-- Table structure for table `Presentation`
--

DROP TABLE IF EXISTS `Presentation`;
CREATE TABLE `Presentation` (
  `id` bigint(20) NOT NULL auto_increment,
  `_title` varchar(255) default NULL,
  `_date` datetime default NULL,
  `_time` datetime default NULL,
  `_end` datetime default NULL,
  `_Venue` varchar(255) default NULL,
  `_abstract` mediumtext,
  `_speaker_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK3356533A9C0B26CD` (`_speaker_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Presentation`
--


/*!40000 ALTER TABLE `Presentation` DISABLE KEYS */;
LOCK TABLES `Presentation` WRITE;
INSERT INTO `Presentation` VALUES (1,'Domain-Specific Language Engineering. Part II','2007-01-14 00:06:00',NULL,NULL,'Bordewijkzaal (19.130)','The goal of domain specific languages (DSLs) is to increase the productivity of software engineers by abstracting from low level boilerplate code. Introduction of DSLs in the software development process requires a smooth workflow for the production of DSLs themselves. This tutorial gives an overview of all aspects of DSL engineering: domain analysis, language design, syntax definition, code generation, deployment, and evolution, discussing research challenges on the way. The concepts are illustrated with DSLs for web applications built using several DSLs for DSL engineering: SDF for syntax definition, StrategoXT for code generation, and Nix for software deployment.',3);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Presentation` ENABLE KEYS */;

--
-- Table structure for table `Presentation_ResearchProject`
--

DROP TABLE IF EXISTS `Presentation_ResearchProject`;
CREATE TABLE `Presentation_ResearchProject` (
  `Presentation_id` bigint(20) NOT NULL default '0',
  `_projects_id` bigint(20) NOT NULL default '0',
  KEY `FKF6BED9D9C8176F81` (`_projects_id`),
  KEY `FKF6BED9D9E324A578` (`Presentation_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Presentation_ResearchProject`
--


/*!40000 ALTER TABLE `Presentation_ResearchProject` DISABLE KEYS */;
LOCK TABLES `Presentation_ResearchProject` WRITE;
INSERT INTO `Presentation_ResearchProject` VALUES (1,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Presentation_ResearchProject` ENABLE KEYS */;

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
  `_subtitle` varchar(255) default NULL,
  `_pages` varchar(255) default NULL,
  `_impact` int(11) default NULL,
  `_conference_id` bigint(20) default NULL,
  `_journal_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK23254A0C32096F48` (`_preprintof_id`),
  KEY `FK23254A0C72A07B9` (`_conference_id`),
  KEY `FK23254A0CEBACDF3B` (`_journal_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Publication`
--


/*!40000 ALTER TABLE `Publication` DISABLE KEYS */;
LOCK TABLES `Publication` WRITE;
INSERT INTO `Publication` VALUES ('Publication',1,'Model-Driven Software Evolution: A Research Agenda',2007,'Software systems need to evolve, and systems built using model driven approaches are no exception.  What complicates model driven engineering is that it requires multiple dimensions of evolution. In regular evolution, the modeling language is used to make the changes. In meta model evolution, changes are required to the modeling notation.  In platform evolution, the code generators and application framework change to reflect new requirements on the target platform. Finally, in abstraction evolution, new modeling languages are added to the set of (modeling) languages to reflect increased understanding of a technical or business domain.  While MDE has been optimized for regular evolution, presently little or no support exists for metamodel, platform and abstraction evolution. In this paper, we analyze the problems raised by the evolution of model based software systems and identify challenges to be addressed by research in this area.','foo.pdf',NULL,NULL,NULL,'',NULL,NULL,NULL,NULL),('Publication',2,'Domain-Specific Language Engineering',2007,'The goal of domain-specific languages (DSLs) is to increase the productivity of software engineers by abstracting from low-level boilerplate code. Introduction of DSLs in the software development process requires a smooth workflow for the production of DSLs themselves. This tutorial gives an overview of all aspects of DSL engineering: domain analysis, language design, syntax definition, code generation, deployment, and evolution, discussing research challenges on the way. The concepts are illustrated with DSLs for web applications built using several DSLs for DSL engineering: SDF for syntax definition, Stratego/XT for code generation, and Nix for software deployment.','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('Publication',3,'Grammar Engineering Support for Precedence Rule Recovery and Compatibility Checking',2007,'A wide range of parser generators are used to generate parsers for programming languages. The grammar formalisms\r\nthat come with parser generators provide different approaches for defining operator precedence. Some generators (e.g.\r\nYACC) support precedence declarations, others require the grammar to be unambiguous, thus encoding the precedence\r\nrules. Even if the grammar formalism provides precedence rules, a particular grammar might not use it. The result is\r\ngrammar variants implementing the same language. For the C language, the GNU Compiler uses YACC with precedence\r\nrules, the C Transformers uses SDF without priorities, while the SDF library does use priorities. For PHP, Zend uses YACC\r\nwith precedence rules, whereas PHP front uses SDF with priority and associativity declarations.\r\nThe variance between grammars raises the question if the precedence rules of one grammar are compatible with those of\r\nanother. This is usually not obvious, since some languages have complex precedence rules. Also, for some parser generators\r\nthe semantics of precedence rules is defined operationally, which makes it hard to reason about their effect on the defined\r\nlanguage. We present a method and tool for comparing the precedence rules of different grammars and parser generators.\r\nAlthough it is undecidable whether two grammars define the same language, this tool provides support for comparing and\r\nrecovering precedence rules, which is especially useful for reliable migration of a grammar from one grammar formalism to\r\nanother. We evaluate our method by the application to non trivial mainstream programming languages, such as PHP and C.','http://swerl.tudelft.nl/twiki/pub/Main/TechnicalReports/TUD-SERG-2007-004.pdf',NULL,NULL,NULL,'',NULL,NULL,NULL,NULL),('Publication',4,'Transformations for Abstractions',2005,'The transformation language Stratego provides highlevel\r\nabstractions for implementation of a wide range of\r\ntransformations. Our aim is to integrate transformation in\r\nthe software development process and make it available to\r\nprogrammers. This requires the transformations provided\r\nby the programming environment to be extensible. This paper\r\npresents a case study in the implementation of extensible\r\nprogramming environments using Stratego, by developing a\r\nsmall collection of language extensions and several typical\r\ntransformations for these languages.','http://www.cs.uu.nl/research/techreps/repo/CS-2005/2005-034.pdf',NULL,NULL,NULL,'',NULL,NULL,NULL,NULL),('Publication',5,'DSL Interaction',2008,'If you have more than one DSL, they need to interact!','notyet.pdf',NULL,NULL,NULL,'',NULL,NULL,NULL,NULL),('InProceedings',7,'Preventing Injection Attacks with Syntax Embeddings',2007,'Software written in one language often needs to construct sentences in another language, such as SQL queries, XML output, or shell command invocations. This is almost always done using unhygienic string manipulation, the concatenation of constants and client supplied strings. A client can then supply specially crafted input that causes the constructed sentence to be interpreted in an unintended way, leading to an injection attack. We describe a more natural style of programming that yields code that is impervious to injections by construction. Our approach embeds the grammars of the guest languages (e.g., SQL) into that of the host language (e.g., Java) and automatically generates code that maps the embedded language to constructs in the host language that reconstruct the embedded sentences, adding escaping functions where appropriate. This approach is generic, meaning that it can be applied with relative ease to any combination of host and guest languages.','',NULL,NULL,NULL,'A Host and Guest Language Independent Approach','',NULL,1,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Publication` ENABLE KEYS */;

--
-- Table structure for table `Publication_Person`
--

DROP TABLE IF EXISTS `Publication_Person`;
CREATE TABLE `Publication_Person` (
  `Publication_id` bigint(20) NOT NULL default '0',
  `_authors_id` bigint(20) NOT NULL default '0',
  `authors_id` bigint(20) default NULL,
  KEY `FK1E7A1508E43FA5E4` (`_authors_id`),
  KEY `FK1E7A1508FF0F387C` (`Publication_id`),
  KEY `FK1E7A1508A3615045` (`authors_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Publication_Person`
--


/*!40000 ALTER TABLE `Publication_Person` DISABLE KEYS */;
LOCK TABLES `Publication_Person` WRITE;
INSERT INTO `Publication_Person` VALUES (1,2,NULL),(1,3,NULL),(2,3,NULL),(7,4,NULL),(2,11,NULL),(1,1,NULL),(5,14,NULL),(4,3,NULL),(3,4,NULL),(3,3,NULL),(3,8,NULL),(7,7,NULL),(7,3,NULL);
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
INSERT INTO `Publication_ResearchProject` VALUES (1,1),(1,2),(2,1),(3,1),(3,2),(4,2),(5,1),(7,3);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Publication_ResearchProject` ENABLE KEYS */;

--
-- Table structure for table `ResearchGroup`
--

DROP TABLE IF EXISTS `ResearchGroup`;
CREATE TABLE `ResearchGroup` (
  `id` bigint(20) NOT NULL auto_increment,
  `_acronym` varchar(255) default NULL,
  `_fullname` varchar(255) default NULL,
  `_mission` mediumtext,
  `_logo` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ResearchGroup`
--


/*!40000 ALTER TABLE `ResearchGroup` DISABLE KEYS */;
LOCK TABLES `ResearchGroup` WRITE;
INSERT INTO `ResearchGroup` VALUES (1,'SERG','Software Engineering Research Group','Software engineering is concerned with methods and techniques for\r\nbuilding high quality software systems. This not only includes\r\nsoftware construction, but also requirements analysis, design, system integration, testing, deployment, and making changes to software systems after their first release.\r\n\r\nThe mission of the Delft Software Engineering Research Group is \r\n\r\n# to develop a deep understanding of how people build and evolve software systems \r\n# to develop novel methods, techniques and tools that advance the way in which software is built and adjusted and \r\n# to offer students an education that prepares them to take a leading role in complex software development projects. ','img/serg-logo-color-smaller.png'),(2,'MPL','MetaProgramming Lab','To do cool meta programming stuff.','');
UNLOCK TABLES;
/*!40000 ALTER TABLE `ResearchGroup` ENABLE KEYS */;

--
-- Table structure for table `ResearchGroup_Colloquium`
--

DROP TABLE IF EXISTS `ResearchGroup_Colloquium`;
CREATE TABLE `ResearchGroup_Colloquium` (
  `ResearchGroup_id` bigint(20) NOT NULL default '0',
  `_colloquia_id` bigint(20) NOT NULL default '0',
  PRIMARY KEY  (`ResearchGroup_id`,`_colloquia_id`),
  KEY `FKCBC5FCB5B966F8DC` (`ResearchGroup_id`),
  KEY `FKCBC5FCB54A1143F2` (`_colloquia_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ResearchGroup_Colloquium`
--


/*!40000 ALTER TABLE `ResearchGroup_Colloquium` DISABLE KEYS */;
LOCK TABLES `ResearchGroup_Colloquium` WRITE;
INSERT INTO `ResearchGroup_Colloquium` VALUES (1,1),(1,2);
UNLOCK TABLES;
/*!40000 ALTER TABLE `ResearchGroup_Colloquium` ENABLE KEYS */;

--
-- Table structure for table `ResearchGroup_News`
--

DROP TABLE IF EXISTS `ResearchGroup_News`;
CREATE TABLE `ResearchGroup_News` (
  `ResearchGroup_id` bigint(20) NOT NULL default '0',
  `_news_id` bigint(20) NOT NULL default '0',
  KEY `FK97064CCE85616399` (`_news_id`),
  KEY `FK97064CCEB966F8DC` (`ResearchGroup_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ResearchGroup_News`
--


/*!40000 ALTER TABLE `ResearchGroup_News` DISABLE KEYS */;
LOCK TABLES `ResearchGroup_News` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `ResearchGroup_News` ENABLE KEYS */;

--
-- Table structure for table `ResearchGroup_Person`
--

DROP TABLE IF EXISTS `ResearchGroup_Person`;
CREATE TABLE `ResearchGroup_Person` (
  `ResearchGroup_id` bigint(20) NOT NULL default '0',
  `_members_id` bigint(20) NOT NULL default '0',
  PRIMARY KEY  (`ResearchGroup_id`,`_members_id`),
  KEY `FKF20DCA90B966F8DC` (`ResearchGroup_id`),
  KEY `FKF20DCA90EDCBEF73` (`_members_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ResearchGroup_Person`
--


/*!40000 ALTER TABLE `ResearchGroup_Person` DISABLE KEYS */;
LOCK TABLES `ResearchGroup_Person` WRITE;
INSERT INTO `ResearchGroup_Person` VALUES (1,1),(1,3),(1,4),(2,3),(2,4);
UNLOCK TABLES;
/*!40000 ALTER TABLE `ResearchGroup_Person` ENABLE KEYS */;

--
-- Table structure for table `ResearchGroup_ResearchProject`
--

DROP TABLE IF EXISTS `ResearchGroup_ResearchProject`;
CREATE TABLE `ResearchGroup_ResearchProject` (
  `ResearchGroup_id` bigint(20) NOT NULL default '0',
  `_projects_id` bigint(20) NOT NULL default '0',
  PRIMARY KEY  (`ResearchGroup_id`,`_projects_id`),
  KEY `FK2705F023C8176F81` (`_projects_id`),
  KEY `FK2705F023B966F8DC` (`ResearchGroup_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ResearchGroup_ResearchProject`
--


/*!40000 ALTER TABLE `ResearchGroup_ResearchProject` DISABLE KEYS */;
LOCK TABLES `ResearchGroup_ResearchProject` WRITE;
INSERT INTO `ResearchGroup_ResearchProject` VALUES (1,1),(1,2),(1,3),(2,1),(2,2);
UNLOCK TABLES;
/*!40000 ALTER TABLE `ResearchGroup_ResearchProject` ENABLE KEYS */;

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
INSERT INTO `ResearchProject` VALUES (1,'Model-Driven Software Evolution','MoDSE','The promise of model driven engineering (MDE) is that the development and maintenance effort can be reduced by working at the model instead of the code level. Models define what is variable in a system, and code generators produce the functionality that is common in the application domain. \r\n\r\nThe problem with model driven engineering is that it can lead to a lock in in the abstractions and generator technology adopted at project initiation. Software systems need to evolve, and systems built using model driven approaches are no exception. What complicates model driven engineering is that it requires multiple dimensions of evolution. In regular evolution, the modeling language is used to make the changes. In meta model evolution, changes are required to the modeling notation. In platform evolution, the code generators and application framework change to reflect new requirements on the target platform. Finally, in abstraction evolution, new modeling languages are added to the set of (modeling) languages to reflect increased understanding of a technical or business domain. While MDE has been optimized for regular evolution, presently little or no support exists for metamodel, platform and abstraction evolution. It is this gap that this project proposes to address. \r\n\r\nThe first fundamental premise of this proposal is that evolution should be a continuous process. Software development is a continuous search for recurring patterns, which can be captured using domain specific modeling languages. After developing a number of systems using a particular meta model, new patterns may be recognized that can be captured in a higher level or richer meta model. \r\n\r\nThe second premise is that reengineering of legacy systems to the model driven paradigm should be a special case of this continuous evolution, and should be performed incrementally. The goal of this project is to develop a systematic approach to model driven software evolution. This approach includes methods, techniques, and underlying tool support. We will develop a prototype programming environment that assists software engineers with the introduction, development, and maintenance of models and domain specific languages.',1),(2,'Transformations for Abstractions','TFA','The project is about techniques at the intersection of two areas of software engineering. (1) In order to automate software engineering we would like to automate the process of producing programs by means of automatic transformations, thereby computing with programs as we do with other data. (2) In order to improve the expressivity of programming languages to the concepts and notations of specific application domains, we would would like to extend general purpose languages with domain specific abstractions. Combining these desiderata leads to the need to extend transformations for new domain specific abstractions.\r\n\r\nThe goal of this project is to develop a systematic approach to the extension of general purpose languages with domain specific abstractions, integrating those abstractions in the syntax and transformations of the programming environment. This requires research into the following issues:\r\n\r\n#strategies for the definition of domain abstractions\r\n\r\n#mechanisms for open extensibility of transformations\r\n\r\n#methods and patterns for design of open transformations\r\n\r\n#constraints for independent extensibility of transformations\r\n\r\n#derivation of transformation extensions from definitions of abstractions\r\n\r\nWe approach this goal by analyzing a variety of existing domain specific languages and transformations, developing generic extensibility mechanisms, and validating these first in an in vitro setting and then in a programming environment for the Java language. The project builds on a solid body of work from the StrategoXT project that will allow us to concentrate on the core of the problem, rather than being distracted by infrastructural issues. ',4),(3,'Capturing Timeline Variability with Transparent Configuration Environments','TraCE','Managing the variability in software systems is rapidly becoming an important factor in software development. Instead of developing and deploying a fixed one of a kind system, it is now common to develop a family of systems whose members differ with respect to functionality or technical facilities offered. Variability is represented conceptually by variation points that identify a set of variants for which a choice or decision can be made at a particular moment, binding time, in the development and deployment cycle of a software system. Depending on the needs of the system\'s stakeholders, it may be desirable to allow these decisions to be made at several moments in time. This timeline variability is an extra dimension to variability that is often ignored.\r\n\r\nVariability decisions are realized via configuration mechanisms, which are closely tied to specific moments in the deployment cycle. As a result, the variability in a system is mostly not orthogonal to the timeline, and often appears to have been designed in an ad hoc fashion. Providing several moments at which a decision can be made is particularly hard to implement and thus seldom provided. Also, the collection of mechanisms often leads to a complex configuration interface.\r\n\r\nThe goal of this project is to study the modeling and realization of variability in modern software systems with an emphasis on a more general and generic treatment of timing issues for variant addition/removal and binding of variation points, the related effects and the opportunities for optimization. The vehicle for the research is the development of a framework for transparent configuration environments which present a uniform interface to a variety of underlying configuration mechanisms, thus closing the current gap between variability at the conceptual and implementation levels. The realization of such environments requires:\r\n\r\n# models of variability that explicitly include timing issues and can cope with timeline variability\r\n\r\n# component composition mechanisms that include composition of variability in components\r\n\r\n# component integration mechanisms that cater for optimization of compositions\r\n\r\n# methods and techniques for variability discovery in existing systems.\r\n\r\nNew in this project is the focus on timeline variability and the integrated treatment of variability spanning several areas including configuration management, language technology, and software reuse. The approach we take is application driven, i.e. drawing inspiration from and validating ideas against existing software, and tool oriented, i.e. developing tools to support transparent specification and realization of timeline variability.\r\n\r\n',NULL);
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
INSERT INTO `ResearchProject_Person` VALUES (1,1),(1,2),(1,3),(2,3),(2,4),(3,3),(3,4),(3,7);
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

