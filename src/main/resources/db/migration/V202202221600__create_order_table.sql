
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