
--delete from user_ad;
--delete from address;
--delete from product;
--delete from ad;


insert into ad (ad_id, description, url_image, value)
values (nextval('ad_ad_id_seq'), 'Nootbook intel i7', 'https://a-static.mlcdn.com.br/618x463/notebook-lenovo-ideapad-320-intel-celeron-n3350-4gb-1tb-led-156-windows-10/magazineluiza/218313700/ede357e0d01c0b9c8c4a60fe533526fa.jpg', 1600)
,(nextval('ad_ad_id_seq'), 'Celta life 4p 2008', 'https://img.olx.com.br/images/64/644821037823977.jpg', 12000)
,(nextval('ad_ad_id_seq'), 'Tv samsung 42 polegagas em ótimo estado', 'https://www.sanborns.com.mx/imagenes-sanborns-ii/1200/8806088790473_4.jpg', 850)
,(nextval('ad_ad_id_seq'), 'Playstation 4 - 2 anos de uso', 'https://s2.glbimg.com/kVWErTnOwfao3Iq9PmqWKo7d8I4=/0x600/s.glbimg.com/po/tt2/f/original/2015/10/30/playstation-4-funcionalidades-melhores-xbox-one.jpg', 700)
,(nextval('ad_ad_id_seq'), 'Poltrona do papai super confortável', 'https://img1.madeiramadeira.com.br/prd/imperio-estofados/192108/poltrona-do-papai-reclin-vel-gelo-198_desc.jpg', 680);

insert into product (product_id, description, value, ad_id)
values
(nextval('product_product_id_seq'), 'Produto A', 1000, 1),
(nextval('product_product_id_seq'), 'Produto B', 1050, 1),
(nextval('product_product_id_seq'), 'Produto C', 1200, 1),
(nextval('product_product_id_seq'), 'Produto D', 5000, 1),
(nextval('product_product_id_seq'), 'Produto E', 10000, 1),
(nextval('product_product_id_seq'), 'Produto F', 100, 2),
(nextval('product_product_id_seq'), 'Produto G', 69, 2),
(nextval('product_product_id_seq'), 'Produto H', 178, 2),
(nextval('product_product_id_seq'), 'Produto I', 567, 2),
(nextval('product_product_id_seq'), 'Produto J', 897.50, 2),
(nextval('product_product_id_seq'), 'Produto L', 120, 2),
(nextval('product_product_id_seq'), 'Produto M', 1090, 3),
(nextval('product_product_id_seq'), 'Produto N', 245, 3),
(nextval('product_product_id_seq'), 'Produto O', 89, 4),
(nextval('product_product_id_seq'), 'Produto P', 0.55, 5),
(nextval('product_product_id_seq'), 'Produto Q', 1789, 5),
(nextval('product_product_id_seq'), 'Produto R', 25890, 5),
(nextval('product_product_id_seq'), 'Produto S', 105, 5),
(nextval('product_product_id_seq'), 'Produto T', 1989, 5),
(nextval('product_product_id_seq'), 'Produto U', 1.99, 5);


insert into address (address_id, city, country, state, street, number)
values (nextval('address_address_id_seq'), 0, 1, 1, 'Rua A', 'A13')
	  ,(nextval('address_address_id_seq'), 0, 1, 1, 'Rua B', 'A219')
	  ,(nextval('address_address_id_seq'), 1, 1, 1, 'Rua C', 'A22')
	  ,(nextval('address_address_id_seq'), 2, 1, 1, 'Rua D', 'A23')
	  ,(nextval('address_address_id_seq'), 2, 1, 1, 'Rua E', 'A57');

insert into user_ad (user_id, dt_age, full_name, nick_name, email, phone_number, gender, address_id)
values
(nextval('user_ad_user_id_seq'), '1989-04-20', 'Hugo Fernando da Mota', 'iguana', 'hugo.iguanaa@gmail.com', '81986335574', 0, 1)
,(nextval('user_ad_user_id_seq'), '1990-01-11', 'Aline Lúcia Silva dos Santos', 'aline', 'aline-4000@hotmail.com', null, 1, 2)
,(nextval('user_ad_user_id_seq'), '1956-02-10', 'Alaíde Rosa da Mota', 'alaide', 'alaide@hotmail.com', null, 1, 3)
,(nextval('user_ad_user_id_seq'), '1957-06-17', 'Gilson Henrique dos Santos', 'gilson', 'didinho@hotmail.com', null, 0, 4)
,(nextval('user_ad_user_id_seq'), '1955-02-10', 'Marta', 'marta', 'marta@hotmail.com', '819666666', 1, 5);
