/*메모리 데이터베이스 - 테이블 생성*/
drop table if exists item CASCADE;
 create table item
 (
    id        bigint generated by default as identity,
    item_name varchar(10),
    price     integer,
    quantity  integer,
    primary key (id)
 );