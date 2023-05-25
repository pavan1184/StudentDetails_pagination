insert into User(id, first_name, last_name, age)
values(1001, 'kondepalli','PavanKumar',23);

insert into Cart(id, user_id, price, qty)
values (1000, 1001, 0 , 0);

insert into Product(product_id, product_name, product_price, genre, authour, publication)
values
(1011, 'Full Stack Java', 1500, '1st', 'James Gosling', 'APK publication'),
(1012, 'Aws', 900, '5nd', 'Rakesh', 'RUPA publication'),
(1013, 'C', 950, '4th', 'Dennis Ritchie', '24BY7 publication'),
(1014, 'Cpp', 1200, '2th', 'Bjarne Stroustrup', 'S.CHAND publication');

insert into Product(product_id, product_name, product_price, type, brand, design)
values (1016, 'Blazer', 500, 'Single Breasted', 'One Click', 'Argyle Single Breasted Casual Men Full Sleeve Blazer (Blue)');