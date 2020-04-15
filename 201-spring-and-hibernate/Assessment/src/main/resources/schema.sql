/* Drop Scripts */
drop table property_tax_payment if exists;

drop table residential_property_category if exists;

drop table status if exists;

drop table unit_area_value if exists;

drop table zone if exists;

drop sequence if exists hibernate_sequence;

/* Create Sequence */
create sequence hibernate_sequence start with 10000 increment by 1;

/* Create Tables */
    create table property_tax_payment (
       id bigint not null,
        address_of_property varchar(255),
        year_of_assessment integer,
        build_up_area integer,
        building_constructed_year integer,
        email varchar(255),
        name_of_the_owner varchar(255),
        total_tax_paid double,
        uav_id integer,
        primary key (id)
    );

    
    create table residential_property_category (
       id integer not null,
        category varchar(255),
        description varchar(255),
        primary key (id)
    );

    
    create table status (
       id integer not null,
        name varchar(255),
        primary key (id)
    );

    
    create table unit_area_value (
       id integer not null,
        category_id integer not null,
        status_id integer not null,
        unit_area_value double not null,
        zone_id integer not null,
        primary key (id)
    );

    
    create table zone (
       id integer not null,
        name varchar(255),
        primary key (id)
    );

/* Alter Scripts to add constraint*/
    alter table property_tax_payment 
       add constraint FK_PTP_UAV
       foreign key (uav_id) 
       references unit_area_value;

    
    alter table unit_area_value 
       add constraint FK_UAV_RPC 
       foreign key (category_id) 
       references residential_property_category;

    
    alter table unit_area_value 
       add constraint FK_UAV_S 
       foreign key (status_id) 
       references status;

    
    alter table unit_area_value 
       add constraint FK_UAV_Z 
       foreign key (zone_id) 
       references zone;

