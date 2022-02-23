
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