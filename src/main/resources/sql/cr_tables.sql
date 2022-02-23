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


insert into  "LAB3_CHEPIHAVV_USER"(login, password, authority) VALUES('user', 'user', 'ROLE_USER');
insert into  "LAB3_CHEPIHAVV_USER"(login, password, authority) VALUES('admin', 'admin', 'ROLE_ADMIN');


drop trigger "BI_LAB3_CHEPIHAVV_USER_ROLE";
drop sequence "LAB3_CHEPIHAVV_USER_ROLE_SEQ";
drop table "LAB3_CHEPIHAVV_USER_ROLE";
/



create table LAB3_CHEPIHAVV_USER_ROLE(
                                         "ID" number,
                                         "NAME" varchar2(32) not null,
                                         constraint "LAB3_CHEPIHAVV_USER_ROLE_PK" primary key ("ID")
);
/

CREATE sequence "LAB3_CHEPIHAVV_USER_ROLE_SEQ";
/

CREATE trigger "BI_LAB3_CHEPIHAVV_USER_ROLE"
    before insert on "LAB3_CHEPIHAVV_USER_ROLE"
    for each row
begin
    if :NEW."ID" is null then
        select "LAB3_CHEPIHAVV_USER_ROLE_SEQ".nextval into :NEW."ID" from dual;
    end if;
end;
/
commit;


insert into LAB3_CHEPIHAVV_USER_ROLE("NAME") values ('ROLE_USER');
insert into LAB3_CHEPIHAVV_USER_ROLE("NAME") values ('ROLE_ADMIN');
insert into LAB3_CHEPIHAVV_USER_ROLE("NAME") values ('ROLE_OFF');


commit;

drop trigger "BI_LAB3_CHEPIHAVV_ORDER_PRODUCTS";
drop sequence "LAB3_CHEPIHAVV_ORDER_PRODUCTS_SEQ";
drop table "LAB3_CHEPIHAVV_ORDER_PRODUCTS"   cascade constraints;

drop trigger "BI_LAB3_CHEPIHAVV_ORDER";
drop sequence "LAB3_CHEPIHAVV_ORDER_SEQ";
drop table "LAB3_CHEPIHAVV_ORDER"   cascade constraints;

drop trigger "BI_LAB3_CHEPIHAVV_PRODUCT";
drop sequence "LAB3_CHEPIHAVV_PRODUCT_SEQ";
drop table LAB3_CHEPIHAVV_PRODUCT  cascade constraints;

drop trigger "BI_LAB3_CHEPIHAVV_STORAGE";
drop sequence "LAB3_CHEPIHAVV_STORAGE_SEQ";
drop table LAB3_CHEPIHAVV_STORAGE  cascade constraints;

drop trigger "BI_LAB3_CHEPIHAVV_CLIENT";
drop sequence "LAB3_CHEPIHAVV_CLIENT_SEQ";
drop table LAB3_CHEPIHAVV_CLIENT  cascade constraints;

drop trigger "BI_LAB3_CHEPIHAVV_LOCATION";
drop sequence "LAB3_CHEPIHAVV_LOCATION_SEQ";
drop table LAB3_CHEPIHAVV_LOCATION  cascade constraints;

drop trigger "BI_LAB3_CHEPIHAVV_BRAND";
drop sequence "LAB3_CHEPIHAVV_BRAND_SEQ";
drop table "LAB3_CHEPIHAVV_BRAND"  cascade constraints;
/



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



create table LAB3_CHEPIHAVV_PRODUCT(
                                       "ID" number,
                                       "NAME" varchar2(128) not null,
                                       "ID_BRAND" number not null,
                                       "PRICE" FLOAT not null,
                                       "COUNT" NUMBER not null,
                                       "DISCOUNT" float,
                                       "GIFT" number,
                                       "ID_STORAGE" number not null,
                                       constraint "LAB3_CHEPIHAVV_PRODUCT_PK" primary key ("ID"),
                                       CONSTRAINT LAB3_CHEPIHAVV_PRODUCT_BRAND_FK
                                           FOREIGN KEY (ID_BRAND)
                                               REFERENCES LAB3_CHEPIHAVV_BRAND("ID"),
                                       CONSTRAINT LAB3_CHEPIHAVV_PRODUCT_STORAGE_FK
                                           FOREIGN KEY (ID_STORAGE)
                                               REFERENCES LAB3_CHEPIHAVV_STORAGE("ID")
);
/

CREATE sequence "LAB3_CHEPIHAVV_PRODUCT_SEQ";
/

CREATE trigger "BI_LAB3_CHEPIHAVV_PRODUCT"
    before insert on "LAB3_CHEPIHAVV_PRODUCT"
    for each row
begin
    if :NEW."ID" is null then
        select "LAB3_CHEPIHAVV_PRODUCT_SEQ".nextval into :NEW."ID" from dual;
    end if;
end;
/
commit;


create table LAB3_CHEPIHAVV_ORDER(
                                     "ID" number,
                                     "D_ORDER" DATE not null,
                                     "ID_CLIENT" number not null,
                                     constraint "LAB3_CHEPIHAVV_ORDER_PK" primary key ("ID"),
                                     CONSTRAINT LAB3_CHEPIHAVV_ORDER_CLIENT_FK
                                         FOREIGN KEY (ID_CLIENT)
                                             REFERENCES LAB3_CHEPIHAVV_CLIENT("ID")
);
/

CREATE sequence "LAB3_CHEPIHAVV_ORDER_SEQ";
/

CREATE trigger "BI_LAB3_CHEPIHAVV_ORDER"
    before insert on "LAB3_CHEPIHAVV_ORDER"
    for each row
begin
    if :NEW."ID" is null then
        select "LAB3_CHEPIHAVV_ORDER_SEQ".nextval into :NEW."ID" from dual;
    end if;
end;
/
commit;



create table LAB3_CHEPIHAVV_ORDER_PRODUCTS(
                                              "ID" number,
                                              "ID_ORDER" number not null,
                                              "ID_PRODUCT" number not null,

                                              "ID_GIFT" number not null,
                                              "DISCOUNT" FLOAT,
                                              "COUNT" number,

                                              constraint "LAB3_CHEPIHAVV_ORDER_PRODUCTS_PK" primary key ("ID"),

                                              CONSTRAINT LAB3_CHEPIHAVV_ORDER_PRODUCTS_ORDER_FK
                                                  FOREIGN KEY (ID_ORDER)
                                                      REFERENCES LAB3_CHEPIHAVV_ORDER("ID"),

                                              CONSTRAINT LAB3_CHEPIHAVV_ORDER_PRODUCTS_PRODUCT_FK
                                                  FOREIGN KEY (ID_PRODUCT)
                                                      REFERENCES LAB3_CHEPIHAVV_PRODUCT("ID")
);
/

CREATE sequence "LAB3_CHEPIHAVV_ORDER_PRODUCTS_SEQ";
/

CREATE trigger "BI_LAB3_CHEPIHAVV_ORDER_PRODUCTS"
    before insert on "LAB3_CHEPIHAVV_ORDER_PRODUCTS"
    for each row
begin
    if :NEW."ID" is null then
        select "LAB3_CHEPIHAVV_ORDER_PRODUCTS_SEQ".nextval into :NEW."ID" from dual;
    end if;
end;
/
commit;


