drop trigger "BI_LAB3_CHEPIHAVV_USER";
drop sequence "LAB3_CHEPIHAVV_USER_SEQ";
drop table LAB3_CHEPIHAVV_USER  cascade constraints;

create table LAB3_CHEPIHAVV_USER(
                                    "ID" number,
                                    "LOGIN" varchar2(50) not null,
                                    "PASSWORD" varchar2(50) not null,
                                    "AUTHORITY" varchar2(50) not null,
                                    constraint  "LAB3_CHEPIHAVV_USER_PK" primary key ("ID"),
                                    constraint "LAB3_CHEPIHAVV_USER_UK" UNIQUE ("LOGIN")
);
/

CREATE sequence "LAB3_CHEPIHAVV_USER_SEQ";
/

CREATE trigger "BI_LAB3_CHEPIHAVV_USER"
    before insert on "LAB3_CHEPIHAVV_USER"
    for each row
begin
    if :NEW."ID" is null then
        select "LAB3_CHEPIHAVV_USER_SEQ".nextval into :NEW."ID" from dual;
    end if;
end;
/
commit;


insert into  "LAB3_CHEPIHAVV_USER"(login, password, authority) VALUES('user', 'user', 'USER');

commit;



drop trigger "BI_LAB3_CHEPIHAVV_BRAND";
drop sequence "LAB3_CHEPIHAVV_BRAND_SEQ";
drop table "LAB3_CHEPIHAVV_BRAND"  cascade constraints;


create table "LAB3_CHEPIHAVV_BRAND" (
                                        "ID"    NUMBER,
                                        "NAME"   VARCHAR2(128) NOT NULL,
                                        "COUNTRY" VARCHAR2(128) NOT NULL,
                                        constraint  "LAB3_CHEPIHAVV_BRAND_PK" primary key ("ID")
);
/

CREATE sequence "LAB3_CHEPIHAVV_BRAND_SEQ";
/

CREATE trigger "BI_LAB3_CHEPIHAVV_BRAND"
    before insert on "LAB3_CHEPIHAVV_BRAND"
    for each row
begin
    if :NEW."ID" is null then
        select "LAB3_CHEPIHAVV_BRAND_SEQ".nextval into :NEW."ID" from dual;
    end if;
end;
/

commit;

insert into  "LAB3_CHEPIHAVV_BRAND"(NAME, COUNTRY) VALUES('Xiaomi', 'China');
insert into  "LAB3_CHEPIHAVV_BRAND"(NAME, COUNTRY) VALUES('Tesla', 'USA');
insert into  "LAB3_CHEPIHAVV_BRAND"(NAME, COUNTRY) VALUES('Konti', 'Ukraine');

commit;


drop trigger "BI_LAB3_CHEPIHAVV_STORAGE";
drop sequence "LAB3_CHEPIHAVV_STORAGE_SEQ";
drop table LAB3_CHEPIHAVV_STORAGE  cascade constraints;

drop trigger "BI_LAB3_CHEPIHAVV_CLIENT";
drop sequence "LAB3_CHEPIHAVV_CLIENT_SEQ";
drop table LAB3_CHEPIHAVV_CLIENT  cascade constraints;

drop trigger "BI_LAB3_CHEPIHAVV_LOCATION";
drop sequence "LAB3_CHEPIHAVV_LOCATION_SEQ";
drop table LAB3_CHEPIHAVV_LOCATION  cascade constraints;
/

create table LAB3_CHEPIHAVV_LOCATION(
                                        "ID" number,
                                        "NAME" varchar2(64) not null,
                                        "ADDRESS" varchar2(255) not null,
                                        constraint  "LAB3_CHEPIHAVV_LOCATION_PK" primary key ("ID")
);
/

CREATE sequence "LAB3_CHEPIHAVV_LOCATION_SEQ";
/

CREATE trigger "BI_LAB3_CHEPIHAVV_LOCATION"
    before insert on "LAB3_CHEPIHAVV_LOCATION"
    for each row
begin
    if :NEW."ID" is null then
        select "LAB3_CHEPIHAVV_LOCATION_SEQ".nextval into :NEW."ID" from dual;
    end if;
end;
/
commit;



create table LAB3_CHEPIHAVV_STORAGE(
                                       "ID" number,
                                       "NAME" varchar2(64) not null,
                                       "ID_LOCATION" number not null,
                                       constraint "LAB3_CHEPIHAVV_STORAGE_PK" primary key ("ID"),
                                       CONSTRAINT LAB3_CHEPIHAVV_STORAGE_LOCATION_FK
                                           FOREIGN KEY (ID_LOCATION)
                                               REFERENCES LAB3_CHEPIHAVV_LOCATION("ID")
);
/

CREATE sequence "LAB3_CHEPIHAVV_STORAGE_SEQ";
/

CREATE trigger "BI_LAB3_CHEPIHAVV_STORAGE"
    before insert on "LAB3_CHEPIHAVV_STORAGE"
    for each row
begin
    if :NEW."ID" is null then
        select "LAB3_CHEPIHAVV_STORAGE_SEQ".nextval into :NEW."ID" from dual;
    end if;
end;
/
commit;



create table LAB3_CHEPIHAVV_CLIENT(
                                      "ID" number,
                                      "NAME" varchar2(128) not null,
                                      "EMAIL" varchar2(64) not null,
                                      "PHONE" varchar2(32) not null,
                                      "ID_LOCATION" number not null,
                                      constraint "LAB3_CHEPIHAVV_CLIENT_PK" primary key ("ID"),
                                      CONSTRAINT LAB3_CHEPIHAVV_CLIENT_LOCATION_FK
                                          FOREIGN KEY (ID_LOCATION)
                                              REFERENCES LAB3_CHEPIHAVV_LOCATION("ID")
);
/

CREATE sequence "LAB3_CHEPIHAVV_CLIENT_SEQ";
/

CREATE trigger "BI_LAB3_CHEPIHAVV_CLIENT"
    before insert on "LAB3_CHEPIHAVV_CLIENT"
    for each row
begin
    if :NEW."ID" is null then
        select "LAB3_CHEPIHAVV_CLIENT_SEQ".nextval into :NEW."ID" from dual;
    end if;
end;
/
commit;