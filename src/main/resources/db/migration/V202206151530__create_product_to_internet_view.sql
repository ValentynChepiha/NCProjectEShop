CREATE OR REPLACE FORCE EDITIONABLE VIEW "LAB3_CHEPIHAVV_PROTUCT_TO_ONLINE"
    ("ID", "NAME", "BRAND", "PRICE", "COUNT", "DISCOUNT", "ID_GIFT", "NAME_GIFT") AS
  select a.id, a.name, b.name as brand, a.price, a.count, nvl(a.discount, 0) as discount, 
       nvl(a.gift, 0) as id_gift, nvl(a2.name, 'empty')  as name_gift 
    from lab3_chepihavv_product a 
       left join lab3_chepihavv_brand b on a.id_brand = b.id 
       left join lab3_chepihavv_product a2 on a.gift = a2.id
/
