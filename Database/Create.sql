CREATE DATABASE IF NOT EXISTS votingsystem-um DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE votingsystem-um;

CREATE TABLE IF NOT EXISTS candidate (
  id int(11) NOT NULL AUTO_INCREMENT,
  name mediumtext,
  votecount int(10) UNSIGNED DEFAULT '0',
  PRIMARY KEY (id)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS ticket (
  id int(11) NOT NULL AUTO_INCREMENT,
  value varchar(45) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY value_UNIQUE (value)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `user` (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(255) NOT NULL,
  password varchar(1024) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;