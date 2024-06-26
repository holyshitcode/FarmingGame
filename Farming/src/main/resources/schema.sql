-- item 테이블
drop table if exists item CASCADE;
create table item
(
    id        bigint generated by default as identity,
    item_name varchar(10),
    price     integer,
    description  varchar(255),
    upload_file_id bigint,  -- 외래키 추가
    primary key (id),
    foreign key (upload_file_id) references upload_file(id)  -- 외래키 제약 조건 추가
);

-- upload_file 테이블
drop table if exists upload_file CASCADE;
create table upload_file
(
    id        bigint generated by default as identity,
    upload_file_name varchar(255),
    store_file_name varchar(255),
    primary key(id)
);
drop table if exists member cascade;
create table member
(
    id bigint generated by default as identity,
    login_id varchar(20),
    name varchar(50),
    password varchar(20),
    primary key (id)
);

