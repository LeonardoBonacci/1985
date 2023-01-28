# In production you would almost certainly limit the replication user must be on the follower (slave) machine,
# to prevent other clients accessing the log from other machines. For example, 'replicator'@'follower.acme.com'.
#
# However, this grant is equivalent to specifying *any* hosts, which makes this easier since the docker host
# is not easily known to the Docker container. But don't do this in production.
#
# CREATE USER 'replicator' IDENTIFIED BY 'replpass';
# CREATE USER 'debezium' IDENTIFIED BY 'dbz';
# GRANT REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO 'replicator';
# GRANT SELECT, RELOAD, SHOW DATABASES, REPLICATION SLAVE, REPLICATION CLIENT  ON *.* TO 'debezium';

# Create the database that we'll use to populate data and watch the effect in the binlog
CREATE DATABASE heroes;
# GRANT ALL PRIVILEGES ON heroes.* TO 'mysqluser'@'%';

# Switch to this database
USE heroes;

drop table if exists account;
drop table if exists admin_user;
drop table if exists pool;
drop table if exists user_info;

create table account (
  id bigint not null auto_increment,
  active bit,
  name varchar(255) not null,
  description varchar(255),
  start_amount decimal(19,2) not null,
  pool_id bigint not null,
  pool_name varchar(255) not null,
  pool_account_id varchar(255) not null,
  user_id bigint not null,
  primary key (id)
) engine=InnoDB;

create table admin_user (
  id bigint not null auto_increment,
  bank_details varchar(255) not null,
  primary key (id)
) engine=InnoDB;

create table pool (
  id bigint not null auto_increment,
  active bit,
  name varchar(255) not null,
  description varchar(255),
  type varchar(255) not null,
  admin_id bigint,
  primary key (id)
) engine=InnoDB;

create table user_info (
  id bigint not null auto_increment,
  description varchar(255),
  name varchar(255) not null,
  primary key (id)
) engine=InnoDB;

alter table account add constraint UK2rss7x7ld4945thr39nhjeb34 unique (user_id, pool_id);
alter table account add constraint UKid9pqtbwt9mhqsy1in2ygil36 unique (name, pool_id);
alter table pool add constraint UK_87plupp7fru334mp72lspmq93 unique (name);
alter table user_info add constraint UK_21gcrpxwqst2mvhvq4mo8f6uy unique (name);
alter table account add constraint FKahhj8w2vym0v4le2g8uxvm0c foreign key (pool_id) references pool (id);
alter table account add constraint FKgg82jfndmi8vr41lk36w47tip foreign key (user_id) references user_info (id);
alter table admin_user add constraint FK2fy9gqqridpevj6ncl08lhjei foreign key (id) references user_info (id);
alter table pool add constraint FKddftxskfywv1dne3cbuig8wqa foreign key (admin_id) references admin_user (id);
