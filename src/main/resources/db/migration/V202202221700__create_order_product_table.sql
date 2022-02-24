
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