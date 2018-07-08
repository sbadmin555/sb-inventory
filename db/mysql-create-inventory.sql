    drop database if exists sb_inventory;
    create database sb_inventory default character set utf8 default collate utf8_bin;
    use sb_inventory;

    create table PRODUCT (
        OBJECTID varchar(36) not null unique,
        DESCRIPTION varchar(1024),
        NAME varchar(128) collate utf8_general_ci,
        PRODUCTTYPE integer not null,
        UNITPRICE integer,
        UNITINSTOCK integer,
        CREATEDTIME bigint,
        LASTMODIFIED bigint,
        DISCONTINUED bit,
        category_OBJECTID varchar(36) ,
        supplier_OBJECTID varchar(36) ,
        primary key (OBJECTID)
    ) ENGINE=InnoDB ROW_FORMAT=COMPRESSED;

    create table CATEGORY (
        OBJECTID varchar(36) not null unique,
        DESCRIPTION varchar(1024),
        NAME varchar(128) collate utf8_general_ci,
        CREATEDTIME bigint,
        LASTMODIFIED bigint,
        primary key (OBJECTID)
    ) ENGINE=InnoDB ROW_FORMAT=COMPRESSED;

    create table SUPPLIER (
        OBJECTID varchar(36) not null unique,
        DESCRIPTION varchar(1024),
        NAME varchar(128) collate utf8_general_ci,
        CREATEDTIME bigint,
        LASTMODIFIED bigint,
        COMPANYNAME varchar(128),
        CONTACTNAME varchar(128),
        ADDRESS varchar(1024),
        POSTALCODE varchar(10),
        COUNTRY integer,
        PHONE varchar(24),
        primary key (OBJECTID)
    ) ENGINE=InnoDB ROW_FORMAT=COMPRESSED;

    alter table PRODUCT
        add index FK_CATEGORYID_PRODUCT (category_OBJECTID),
        add constraint FK_CATEGORYID_SERVER
        foreign key (category_OBJECTID)
        references CATEGORY (OBJECTID);

    alter table PRODUCT
        add index FK_SUPPLIERID_PRODUCT (supplier_OBJECTID),
        add constraint FK_SUPPLIERID_PRODUCT
        foreign key (supplier_OBJECTID)
        references SUPPLIER (OBJECTID);
