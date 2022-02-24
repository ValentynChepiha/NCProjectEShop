
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
