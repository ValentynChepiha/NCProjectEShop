
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
