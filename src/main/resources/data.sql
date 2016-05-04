insert into authors(author_id,first_name,last_name,link_wiki,date_of_birth) values (0,'yossi', 'Roberts', 'www.gd.com' , '1985-05-01');
insert into authors(author_id,first_name,last_name,link_wiki,date_of_birth) values (1,'Amir', 'Roberts', 'www.gd.com' , '1965-07-01');
insert into authors(author_id,first_name,last_name,link_wiki,date_of_birth) values (2,'Gregory David', 'Roberts', 'www.gd.com' , '1966-02-01');
insert into authors(author_id,first_name,last_name,link_wiki,date_of_birth) values (3,'Arthur', 'Miller', 'https://en.wikipedia.org/wiki/Arthur_Miller' , '1915-10-17');



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
insert into books(name,author_id,image_name,book_category_id,release_date,publisher_name,book_abstract) values ('All My Sons' , 3 , 'all_my_sons.png' , 1,'1990-02-01', 'allright' , 'Joe Keller and Herbert Deever, partners in a machine shop during the war, turned out defective airplane parts, causing the deaths of many men. Deever was sent to prison while Keller escaped punishment and went on to make a lot of money. In a work of tremendous power, a love affair between Kellers son, Chris, and Ann Deever, Herberts daughter, the bitterness of George Keller, who returns from the war to find his father in prison and his fathers partner free, and the reaction of a son to his fathers guilt escalate toward a climax of electrifying intensity.');
insert into books(name,author_id,image_name,book_category_id,release_date,publisher_name,book_abstract) values ('toy story' , 1 , 'toy_story.png' , 2, '1990-02-01','allright' , 'fdfdjfdjfjdjfdjf');
insert into books(name,author_id,image_name,book_category_id,release_date,publisher_name,book_abstract) values ('pinokyo' , 2 , 'dog.png' , 3, '1990-02-01','allright' , 'fdfdjfdjfjdjfdjf');

insert into users(first_name,last_name,user_name,password,date_of_birth,gender,points,user_type,image_name) values ('Hadar' , 'Shimoni' , 'hadar@gmail.com' , '1234','1985-02-01' , 'female' ,100 , 'administrator' ,'2d5d278.png');
insert into users(first_name,last_name,user_name,password,date_of_birth,gender,points,user_type,image_name) values ('Guy' , 'levy' , 'guy.le23@gmail.com' , '1234','1984-03-01' , 'male' ,100 , 'administrator','366bfa7.png' );
insert into users(first_name,last_name,user_name,password,date_of_birth,gender,points,user_type,image_name) values ('admin' , 'ibdb' , 'admin@ibdb.com' , '1234','1984-02-01' , 'male' ,100 , 'administrator' ,'anonymous.png');
insert into users(first_name,last_name,user_name,password,date_of_birth,gender,points,user_type,image_name) values ('boaz' , 'shafrir' , 'boaz@gmail.com' , '1234','1985-02-01' , 'male' ,100 , 'member' ,'anonymous.png');


insert into proposals(book_id,user_id,proposal_date,proposal_status) values (1,1,'21/03/2016','pending' );
insert into reviews(book_id,user_id,rating,review_comment) values (1,1, 10, 'Exellent Book!!!');
insert into reviews(book_id,user_id,rating,review_comment) values (2,2, 10, 'Exellent Book!!!');
insert into reviews(book_id,user_id,rating,review_comment) values (2,1, 7.5, 'Amazing !!!');

