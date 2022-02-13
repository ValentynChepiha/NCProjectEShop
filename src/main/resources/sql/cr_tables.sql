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


drop trigger "BI_LAB3_CHEPIHAVV_LOCATION";
drop sequence "LAB3_CHEPIHAVV_LOCATION_SEQ";
drop table LAB3_CHEPIHAVV_LOCATION  cascade constraints;

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