
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