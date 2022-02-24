
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