

Insert into LAB3_CHEPIHAVV_CLIENT (NAME,EMAIL,PHONE,ID_LOCATION)
select 'Test client','test.client@gmail.com','+38(050)999-99-99',
       id from lab3_chepihavv_location where name = 'client address' and rownum = 1;

commit;

