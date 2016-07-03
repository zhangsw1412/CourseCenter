drop database if exists course_center;
create database if not exists course_center default charset utf8 collate utf8_general_ci;
use course_center;
drop table if exists `user`;
create table `user`(
	num int not null auto_increment comment '主键',
	id varchar(20) not null default '' comment '学号/工号',
	password char(40) not null default '' comment '密码',
	name varchar(10) not null default '' comment '姓名',
	gender tinyint(1) unsigned not null default 0 comment '性别：男0女1',
	type tinyint(1) unsigned not null default 0 comment '用户类型：学生0，教师1，教务2',
	valid tinyint(1) unsigned not null default 0 comment '帐户是否有效：有效1，无效0',
	last_login_time int(11) unsigned default null comment '上次登录时间',
	last_login_ip char(15) default null comment '上次登录ip',
	primary key(num),
	key(id)
)engine=InnoDB default charset=utf8 comment='用户表，存放用户信息';

drop table if exists `semester`;
create table `semester`(
	id int not null auto_increment comment '主键',
	school_year int(4) not null default 1900 comment '学年',
	season tinyint(1) not null default 0 comment '季度：秋季学期0，春季学期1，夏季学期2',
	start_date int(11) unsigned not null default 0 comment '开学日期，以unix时间戳形式存储',
	end_date int unsigned not null default 0 comment '学期结束日期，同上',
	weeks tinyint(2) unsigned not null default 16 comment '周数',
	primary key(id)
)engine=InnoDB default charset=utf8 comment='学期表，记录学期信息';

drop table if exists `course`;
create table `course`(
	id int not null auto_increment comment '主键',
	course_code varchar(10) not null default '' comment '课程编码',
	name varchar(15) not null default '' comment '课程名',
	college_id int not null default 1 comment '开课院系，对应于院系表的主键',
	period tinyint(2) not null default 32 comment '学时',
	credit tinyint(2) not null default 1 comment '学分',
	type tinyint(1) not null default 0 comment '课程类型：必修0，选修1',
	team_avaliable tinyint(1) not null default 0 comment '是否允许团队参加：不允许0/允许1',
	primary key(id)
)engine=InnoDB default charset=utf8 comment='课程表，记录课程信息';

drop table if exists `college`;
create table `college`(
	id int not null auto_increment comment '主键',
	college_code varchar(10) not null default '' comment '院系编码',
	name varchar(15) not null default '' comment '院系名称',
	primary key(id)
)engine=InnoDB default charset=utf8 comment='院系表';

drop table if exists `semester_course`;
create table `semester_course`(
	id int not null auto_increment comment '主键',
	semester_id int not null default 1 comment '学期，对应于学期表的主键',
	course_id int not null default 1 comment '课程，对应于课程表的主键',
	teacher varchar(64) not null default '' comment '对应于用户表中的教师id，多个教师之间用;分隔',
	primary key(id)
)engine=InnoDB default charset=utf8 comment='学期-课程中间表';

drop table if exists `course_student`;
create table `course_student`(
	id int not null auto_increment comment '主键',
	semester_course_id int not null default 1 comment '对应学期课程表的主键',
	student_id int not null default 1 comment '对应用户表中学生的主键',
	team_id int not null default 1 comment '对应团队表中的主键',
	primary key(id)
)engine=InnoDB default charset=utf8 comment='选课表';

drop table if exists `team`;
create table `team`(
	id int not null auto_increment comment '主键',
	name varchar(20) not null default '' comment '团队名',
	leader_id int not null default 1 comment '团队负责人，对应用户表中学生的主键',
	max_num tinyint(2) not null default 0 comment '团队人数上限',
	primary key(id)
)engine=InnoDB default charset=utf8 comment='团队表';

drop table if exists `team_student`;
create table `team_student`(
	id int not null auto_increment comment '主键',
	student_id int not null default 1 comment '对应用户表中学生的主键',
	team_id int not null default 1 comment '对应团队表中的主键',
	primary key(id)
)engine=InnoDB default charset=utf8 comment='团队-学生中间表';