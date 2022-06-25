Insert into LAB3_CHEPIHAVV_STORAGE (NAME,ID_LOCATION)
select 'Test storage 1',
       id from lab3_chepihavv_location where name = 'storage address' and rownum = 1;


commit;

