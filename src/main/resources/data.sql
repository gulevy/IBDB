insert into authors(first_name,last_name,link_wiki) values ('Gregory David', 'Roberts', 'www.gd.com');

insert into books(name,author_id,image_name,category_id,publisher_name,book_abstract) values ('Shantaram' , 1 , 'shantaram.png' , 1, 'allright' , 'fdfdjfdjfjdjfdjf');
insert into book_category(id,name) values (2,'Novel');

insert into reviews(book_id,user_id,rating,review_comment) values (1,1, 10, 'Exellent Book!!!');
insert into proposals(author_id,book_id,user_id,proposal_date,proposal_status) values (1,3,1,'21/03/2016','pending' );


insert into books(name,author_id,image_name,category_id,publisher_name,book_abstract) values ('All My Sons' , 2 , 'all_my_sons.png' , 1, 'allright' , 'fdfdjfdjfjdjfdjf');
insert into books(name,author_id,image_name,category_id,publisher_name,book_abstract) values ('toy story' , 3 , 'toy_story.png' , 2, 'allright' , 'fdfdjfdjfjdjfdjf');
insert into books(name,author_id,image_name,category_id,publisher_name,book_abstract) values ('pinokyo' , 3 , 'dog.png' , 3, 'allright' , 'fdfdjfdjfjdjfdjf');

insert into users(first_name,last_name,username,password,date_of_birth,gender,points,user_type) values ('Hadar' , 'Shimoni' , 'hs@gmail.com' , '234567','1990-02-01' , 'female' ,10 , 'member' );
insert into users(first_name,last_name,username,password,date_of_birth,gender,points,user_type) values ('dan' , 'kelian' , 'dk@gmail.com' , '123456','1984-02-01' , 'female' ,0 , 'member' );

insert into book_category(id,name) values (1,'Science fiction');
insert into book_category(name) values ('Satire');
insert into book_category(name) values ('Drama');
insert into book_category(name) values ('Action and Adventure');
insert into book_category(name) values ('Romance');
insert into book_category(name) values ('Mystery');
insert into book_category(name) values ('Horror');
insert into book_category(name) values ('Self help');
insert into book_category(name) values ('Health');
insert into book_category(name) values ('Guide');
insert into book_category(name) values ('Travel');
insert into book_category(name) values ('Childrens');
insert into book_category(name) values ('Religion, Spirituality & New Age');
insert into book_category(name) values ('Science');
insert into book_category(name) values ('History');
insert into book_category(name) values ('Math');
insert into book_category(name) values ('Anthology');
insert into book_category(name) values ('Poetry');
insert into book_category(name) values ('Encyclopedias');
insert into book_category(name) values ('Dictionaries');
insert into book_category(name) values ('Comics');
insert into book_category(name) values ('Art');
insert into book_category(name) values ('Cookbooks');
insert into book_category(name) values ('Diaries');
insert into book_category(name) values ('Journals');
insert into book_category(name) values ('Prayer books');
insert into book_category(name) values ('Series');
insert into book_category(name) values ('Trilogy');
insert into book_category(name) values ('Biographies');
insert into book_category(name) values ('Autobiographies');
insert into book_category(name) values ('Fantasy');
