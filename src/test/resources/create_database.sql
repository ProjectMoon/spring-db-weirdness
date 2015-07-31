-- Creates the usage database and all related tables.
-- Also see usage_procedures.sql, which creates the stored procedures.
create database if not exists testdb character set utf8 collate utf8_unicode_ci;

-- aggregated usage
create table if not exists testdb.aggregated_usage (
	id bigint not null auto_increment,

	resource_uuid varchar(100) not null, -- the id of the resource
	raw_event_id bigint not null,
	sid varchar(36) not null,
	resource_type varchar(50) not null,
	aggregation_type varchar(20) not null,
	state varchar(10) not null, -- ONGOING or FINISHED
	aggregation_start timestamp not null,
	aggregation_end timestamp, -- intentionally nullable
	specific_info_id bigint,

	primary key (id)
);
