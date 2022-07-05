Insert into LAB3_CHEPIHAVV_PRODUCT (NAME,ID_BRAND,PRICE,COUNT,DISCOUNT,GIFT,ID_STORAGE)
select 'Readme 8A',
       (select a.id from lab3_chepihavv_brand a order by DBMS_RANDOM.value FETCH NEXT 1 ROWS ONLY),
       '5500','49',null,null,
       (select id from lab3_chepihavv_storage where  rownum = 1)
from dual;

Insert into LAB3_CHEPIHAVV_PRODUCT (NAME,ID_BRAND,PRICE,COUNT,DISCOUNT,GIFT,ID_STORAGE)
select 'Asus X545',
       (select a.id from lab3_chepihavv_brand a order by DBMS_RANDOM.value FETCH NEXT 1 ROWS ONLY),
       '19000','66',null,null,
       (select id from lab3_chepihavv_storage where  rownum = 1)
from dual;

Insert into LAB3_CHEPIHAVV_PRODUCT (NAME,ID_BRAND,PRICE,COUNT,DISCOUNT,GIFT,ID_STORAGE)
select 'Candy "Rainbow"',
       (select a.id from lab3_chepihavv_brand a order by DBMS_RANDOM.value FETCH NEXT 1 ROWS ONLY),
       '15','2500',null,null,
       (select id from lab3_chepihavv_storage where  rownum = 1)
from dual;

Insert into LAB3_CHEPIHAVV_PRODUCT (NAME,ID_BRAND,PRICE,COUNT,DISCOUNT,GIFT,ID_STORAGE)
select 'Asus X560',
       (select a.id from lab3_chepihavv_brand a order by DBMS_RANDOM.value FETCH NEXT 1 ROWS ONLY),
       '21000','24',null,null,
       (select id from lab3_chepihavv_storage where  rownum = 1)
from dual;

Insert into LAB3_CHEPIHAVV_PRODUCT (NAME,ID_BRAND,PRICE,COUNT,DISCOUNT,GIFT,ID_STORAGE)
select 'Readme 10C',
       (select a.id from lab3_chepihavv_brand a order by DBMS_RANDOM.value FETCH NEXT 1 ROWS ONLY),
       '7600','34',null,null,
       (select id from lab3_chepihavv_storage where  rownum = 1)
from dual;


commit;

