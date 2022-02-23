
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
