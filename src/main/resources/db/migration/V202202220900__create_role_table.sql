
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