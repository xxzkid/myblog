/* */
CREATE TABLE t_article (
  id INTEGER PRIMARY KEY ,
  title varchar(50) NOT NULL,
  fixed_link varchar(255) NOT NULL,
  content TEXT NOT NULL,
  html TEXT NOT NULL,
  is_show INTEGER NOT NULL,
  sort_value INTEGER NOT NULL,
  category_id INTEGER NOT NULL,
  category_name varchar(50) NOT NULL,
  tags TEXT NOT NULL,
  create_user INTEGER NOT NULL,
  create_user_name varchar(50) NOT NULL,
  create_time INTEGER NOT NULL,
  update_user INTEGER NOT NULL,
  update_time INTEGER NOT NULL,
  pv INTEGER NOT NULL
);

CREATE TABLE t_category (
  id INTEGER PRIMARY KEY,
  name varchar(50) NOT NULL,
  alias_name varchar(50) NOT NULL,
  create_user INTEGER NOT NULL,
  create_time INTEGER NOT NULL,
  update_user INTEGER NOT NULL,
  update_time INTEGER NOT NULL
);

CREATE TABLE t_comment (
  id INTEGER PRIMARY KEY,
  nick_name VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  site VARCHAR(255) DEFAULT NULL,
  content TEXT NOT NULL,
  parent_id INTEGER NOT NULL,
  comment_obj INTEGER NOT NULL,
  to_nick_name varchar(50) DEFAULT NULL,
  status INTEGER NOT NULL,
  create_time INTEGER NOT NULL,
  audit_user INTEGER NOT NULL,
  audit_time INTEGER NOT NULL
);

CREATE TABLE t_param (
  param_name VARCHAR(255) NOT NULL,
  param_value VARCHAR(255) NOT NULL,
  create_user INTEGER NOT NULL,
  create_time INTEGER NOT NULL,
  update_user INTEGER NOT NULL,
  update_time INTEGER NOT NULL
);

insert  into `t_param`(`param_name`,`param_value`,`create_user`,`create_time`,`update_user`,`update_time`) values ('upload_url','/uploads/',2,0,2,0);

CREATE TABLE t_resource (
  id INTEGER PRIMARY KEY,
  url VARCHAR(255) NOT NULL,
  create_user INTEGER NOT NULL,
  create_time INTEGER NOT NULL
);

CREATE TABLE t_user (
  id INTEGER PRIMARY KEY,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(64) NOT NULL,
  nick_name VARCHAR(50) NOT NULL,
  create_time INTEGER NOT NULL
);

insert  into `t_user`(`id`,`username`,`password`,`nick_name`,`create_time`) values (2,'admin','03ef9156c24596fba56f3e0e88f7048f','admin',1515310345751);

CREATE TABLE t_user_log (
  id INTEGER PRIMARY KEY,
  user_id INTEGER NOT NULL,
  login_time TEXT NOT NULL,
  login_stat INTEGER NOT NULL,
  login_error_count INTEGER NOT NULL
);