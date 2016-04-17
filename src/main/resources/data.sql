insert into authors(first_name,last_name,link_wiki,date_of_birth) values ('yossi', 'Roberts', 'www.gd.com' , '1985-05-01');
insert into authors(first_name,last_name,link_wiki,date_of_birth) values ('Amir', 'Roberts', 'www.gd.com' , '1965-07-01');
insert into authors(first_name,last_name,link_wiki,date_of_birth) values ('Gregory David', 'Roberts', 'www.gd.com' , '1966-02-01');


insert into reviews(book_id,user_id,rating,review_comment) values (1,1, 10, 'Exellent Book!!!');

insert into book_category(id,name) values (2,'Novel');
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


insert into books(name,author_id,image_name,book_category_id,release_date,publisher_name,book_abstract) values ('Shantaram' ,1 , 'shantaram.png' , 1,'1990-02-01', 'allright' , 'fdfdjfdjfjdjfdjf');
insert into books(name,author_id,image_name,book_category_id,release_date,publisher_name,book_abstract) values ('All My Sons' , 1 , 'all_my_sons.png' , 1,'1990-02-01', 'allright' , 'fdfdjfdjfjdjfdjf');
insert into books(name,author_id,image_name,book_category_id,release_date,publisher_name,book_abstract) values ('toy story' , 1 , 'toy_story.png' , 2, '1990-02-01','allright' , 'fdfdjfdjfjdjfdjf');
insert into books(name,author_id,image_name,book_category_id,release_date,publisher_name,book_abstract) values ('pinokyo' , 2 , 'dog.png' , 3, '1990-02-01','allright' , 'fdfdjfdjfjdjfdjf');

insert into users(first_name,last_name,username,password,date_of_birth,gender,points,user_type) values ('Hadar' , 'Shimoni' , 'hadar@gmail.com' , '1234','1985-02-01' , 'female' ,100 , 'administrator' );
insert into users(first_name,last_name,username,password,date_of_birth,gender,points,user_type) values ('Guy' , 'levy' , 'guy.le23@gmail.com' , '1234','1984-03-01' , 'male' ,100 , 'administrator' );
insert into users(first_name,last_name,username,password,date_of_birth,gender,points,user_type) values ('admin' , 'ibdb' , 'admin@ibdb.com' , '1234','1984-02-01' , 'male' ,100 , 'administrator' );

insert into proposals(book_id,user_id,proposal_date,proposal_status) values (3,1,'21/03/2016','pending' );
