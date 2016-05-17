insert into authors(first_name,last_name,link_wiki,date_of_birth) values ('Jane','Austen', 'https://en.wikipedia.org/wiki/Jane_Austen' , '1775-12-16');
insert into authors(first_name,last_name,link_wiki,date_of_birth) values ('Joanne', ' Rowling', 'https://en.wikipedia.org/wiki/J._K._Rowling' , '1965-7-31');
insert into authors(first_name,last_name,link_wiki,date_of_birth) values ('Arthur', 'Miller', 'https://en.wikipedia.org/wiki/Arthur_Miller' , '1915-10-17');
insert into authors(first_name,last_name,link_wiki,date_of_birth) values ('Jonathan', 'Swift', 'https://en.wikipedia.org/wiki/Jonathan_Swift' , '1667-11-30');
insert into authors(first_name,last_name,link_wiki,date_of_birth) values ('Miguel ', 'de Cervantes', 'https://en.wikipedia.org/wiki/Miguel_de_Cervantes' , '1547-09-29');
insert into authors(first_name,last_name,link_wiki,date_of_birth) values ('Gregory David', 'Roberts', 'https://en.wikipedia.org/wiki/Gregory_David_Roberts' , '1952-06-21');
insert into authors(first_name,last_name,link_wiki,date_of_birth) values ('Ishmael', 'Beah', 'https://en.wikipedia.org/wiki/Ishmael_Beah' , '1980-11-23');
insert into authors(first_name,last_name,link_wiki,date_of_birth) values ('Hunter S.', 'Thompson', 'https://en.wikipedia.org/wiki/Hunter_S._Thompson' , '1937-07-18');
insert into authors(first_name,last_name,link_wiki,date_of_birth) values ('Lois', 'Lowry', 'https://en.wikipedia.org/wiki/Lois_Lowry' , '1937-03-20');
insert into authors(first_name,last_name,link_wiki,date_of_birth) values ('Suzanne', 'Collins', 'https://en.wikipedia.org/wiki/Suzanne_Collins' , '1962-08-10');


insert into book_category(name) values ('Novel');
insert into book_category(name) values ('Science fiction');
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

insert into books(name,author_id,image_name,book_category_id,release_date,publisher_name,book_abstract,rate) values ('Pride and Prejudice' ,1 , 'PrideandPrejudice.png' , 1,'1813-01-28', 'T. Egerton, Whitehall' , 'The story is based on Jane Austens novel about five sisters - Jane, Elizabeth, Mary, Kitty and Lydia Bennet - in Georgian England. Their lives are turned upside down when a wealthy young man (Mr. Bingley) and his best friend (Mr. Darcy) arrive in their neighborhood',7);

insert into books(name,author_id,image_name,book_category_id,release_date,publisher_name,book_abstract,rate) values ('All My Sons' , 3 , 'all_my_sons.png' , 1,'1947-01-29', 'allright' , 'Joe Keller and Herbert Deever, partners in a machine shop during the war, turned out defective airplane parts, causing the deaths of many men. Deever was sent to prison while Keller escaped punishment and went on to make a lot of money. In a work of tremendous power, a love affair between Kellers son, Chris, and Ann Deever, Herberts daughter, the bitterness of George Keller, who returns from the war to find his father in prison and his fathers partner free, and the reaction of a son to his fathers guilt escalate toward a climax of electrifying intensity.', 8.5);

insert into books(name,author_id,image_name,book_category_id,release_date,publisher_name,book_abstract,rate) values ('Don Quixote' , 5 , 'don_q.png' , 1,'1605-02-01', 'Francisco de Robles' , 'Alonso Quixano, a retired country gentleman in his fifties, lives in an unnamed section of La Mancha with his niece and a housekeeper. He has become obsessed with books of chivalry, and believes their every word to be true, despite the fact that many of the events in them are clearly impossible. Quixano eventually appears to other people to have lost his mind from little sleep and food and because of so much reading.',5);

insert into books(name,author_id,image_name,book_category_id,release_date,publisher_name,book_abstract,rate) values ('Gullivers Travels' , 5 , 'guliver.png' , 32,'1726-10-28', 'Benjamin Motte' , 'From the preeminent prose satirist in the English language, a great classic recounting the four remarkable journeys of ship s surgeon Lemuel Gulliver. For children it remains an enchanting fantasy; for adults, a witty parody of political life in Swift s time and a scathing send-up of manners and morals in 18th-century England.',0);

insert into books(name,author_id,image_name,book_category_id,release_date,publisher_name,book_abstract,rate) values ('Harry Potter and the Sorcerers Stone' , 1 , 'Poter1.png' , 32, '1997-06-26','Bloomsbury' , 'escued from the outrageous neglect of his aunt and uncle, a young boy with a great destiny proves his worth while attending Hogwarts School of Witchcraft and Wizardry.',0);

insert into books(name,author_id,image_name,book_category_id,release_date,publisher_name,book_abstract,rate) values ('Harry Potter and the Chamber of Secrets' , 1 , 'Poter2.png' , 32, '1998-07-02','Bloomsbury' , 'Harry ignores warnings not to return to Hogwarts, only to find the school plagued by a series of mysterious attacks and a strange voice haunting him.',0);

insert into books(name,author_id,image_name,book_category_id,release_date,publisher_name,book_abstract,rate) values ('Harry Potter and the Prisoner of Azkaban' , 1 , 'Poter3.png' , 32, '1999-07-08','Bloomsbury' , 'Its Harrys third year at Hogwarts; not only does he have a new "Defense Against the Dark Arts" teacher, but there is also trouble brewing. Convicted murderer Sirius Black has escaped the Wizards Prison and is coming after Harry.',0);

insert into books(name,author_id,image_name,book_category_id,release_date,publisher_name,book_abstract,rate) values ('Harry Potter and the Goblet of Fire' , 1 , 'Poter4.png' , 32, '2000-07-08','Bloomsbury' , 'Harry finds himself mysteriously selected as an under-aged competitor in a dangerous tournament between three schools of magic.',0);

insert into books(name,author_id,image_name,book_category_id,release_date,publisher_name,book_abstract,rate) values ('Shantaram' , 6 , 'Shantaram.png' , 1, '2003-07-08','Scribe Publications' , 'A convicted Australian bank robber and heroin addict who escaped from Pentridge Prison flees to India. The novel is commended by many for its vivid portrayal of tumultuous life in Bombay',9);

insert into books(name,author_id,image_name,book_category_id,release_date,publisher_name,book_abstract,rate) values ('A Long Way Gone' , 7 , 'Long.png' , 4, '2007-02-13','Farrar, Straus and Giroux' , 'The book is a firsthand account of Beahs time as a child soldier during the civil war in Sierra Leone',8);

insert into books(name,author_id,image_name,book_category_id,release_date,publisher_name,book_abstract,rate) values ('Fear and Loathing in Las Vegas' , 8 , 'Fear.png' , 4, '1971-11-11','Random House' , 'Fear and Loathing in Las Vegas is the best chronicle of drug-soaked, addle-brained, rollicking good times ever committed to the printed page.  It is also the tale of a long weekend road trip that has gone down in the annals of American pop culture as one of the strangest journeys ever undertaken.',7);

insert into books(name,author_id,image_name,book_category_id,release_date,publisher_name,book_abstract,rate) values ('The Giver' , 9 , 'Giver.png' , 2, '1993-02-11','Houghton Mifflin' , 'In a seemingly perfect community, without war, pain, suffering, differences or choice, a young boy is chosen to learn from an elderly man about the true pain and pleasure of the "real" world.',7);

insert into books(name,author_id,image_name,book_category_id,release_date,publisher_name,book_abstract,rate) values ('The Hunger Games' , 10 , 'Hunger.png' , 1, '2008-06-01','	Scholastic' , 'Katniss Everdeen voluntarily takes her younger sister's place in the Hunger Games, a televised competition in which two teenagers from each of the twelve Districts of Panem are chosen at random to fight to the death.',8);

insert into users(first_name,last_name,user_name,password,date_of_birth,gender,points,user_type,image_name) values ('Hadar' , 'Shimoni' , 'hadar.shimoni@gmail.com' , '1234','1985-07-12' , 'female' ,100 , 'administrator' ,'2d5d278.png');
insert into users(first_name,last_name,user_name,password,date_of_birth,gender,points,user_type,image_name) values ('Guy' , 'Levy' , 'guy.le23@gmail.com' , '1234','1984-03-01' , 'male' ,3000 , 'administrator','366bfa7.png' );
insert into users(first_name,last_name,user_name,password,date_of_birth,gender,points,user_type,image_name) values ('admin' , 'ibdb' , 'admin@ibdb.com' , '1234','1982-02-01' , 'male' ,1 , 'administrator' ,'anonymous.png');
insert into users(first_name,last_name,user_name,password,date_of_birth,gender,points,user_type,image_name) values ('Boaz' , 'Shafrir' , 'boaz@gmail.com' , '1234','1985-04-11' , 'male' ,5 , 'member' ,'anonymous.png');
insert into users(first_name,last_name,user_name,password,date_of_birth,gender,points,user_type,image_name) values ('Dana' , 'Diamant' , 'dana@ibdb.com' , '1234','1980-01-23' , 'female' ,5 , 'member' ,'anonymous.png');
insert into users(first_name,last_name,user_name,password,date_of_birth,gender,points,user_type,image_name) values ('Hava' , 'Adir' , 'hava@ibdb.com' , '1234','1945-10-14' , 'female' ,10 , 'member' ,'anonymous.png');
insert into users(first_name,last_name,user_name,password,date_of_birth,gender,points,user_type,image_name) values ('Dror' , 'Hod' , 'dror@ibdb.com' , '1234','1979-12-6' , 'male' ,30 , 'member' ,'anonymous.png');
insert into users(first_name,last_name,user_name,password,date_of_birth,gender,points,user_type,image_name) values ('Yoav' , 'Lev' , 'yoav@ibdb.com' , '1234','1981-06-04' , 'male' ,180 , 'member' ,'anonymous.png');
insert into users(first_name,last_name,user_name,password,date_of_birth,gender,points,user_type,image_name) values ('Roy' , 'Sendovsky' , 'roy@ibdb.com' , '1234','1993-08-16' , 'male' ,70 , 'member' ,'anonymous.png');
insert into users(first_name,last_name,user_name,password,date_of_birth,gender,points,user_type,image_name) values ('Adi' , 'Fereda' , 'adi@ibdb.com' , '1234','1970-09-12' , 'female' ,0 , 'member' ,'anonymous.png');

insert into proposals(book_id,user_id,proposal_date,proposal_status) values (1,1,'17/03/2016','approved' );
insert into proposals(book_id,user_id,proposal_date,proposal_status) values (2,3,'18/04/2016','approved' );
insert into proposals(book_id,user_id,proposal_date,proposal_status) values (3,2,'1/05/2016','approved' );
insert into proposals(book_id,user_id,proposal_date,proposal_status) values (4,4,'7/03/2016','approved' );
insert into proposals(book_id,user_id,proposal_date,proposal_status) values (5,5,'2/04/2016','approved' );
insert into proposals(book_id,user_id,proposal_date,proposal_status) values (6,2,'3/05/2016','approved' );
insert into proposals(book_id,user_id,proposal_date,proposal_status) values (7,1,'30/03/2016','approved' );
insert into proposals(book_id,user_id,proposal_date,proposal_status) values (8,9,'28/04/2016','approved' );
insert into proposals(book_id,user_id,proposal_date,proposal_status) values (9,3,'6/05/2016','approved' );
insert into proposals(book_id,user_id,proposal_date,proposal_status) values (10,2,'23/05/2016','approved' );
insert into proposals(book_id,user_id,proposal_date,proposal_status) values (2,1,'27/03/2016','denied' );
insert into proposals(book_id,user_id,proposal_date,proposal_status) values (5,1,'3/02/2016','denied' );
insert into proposals(book_id,user_id,proposal_date,proposal_status) values (9,2,'25/01/2016','denied' );

insert into reviews(book_id,user_id,rating,review_comment) values (2,2, 10, 'Exellent Book!!!');
insert into reviews(book_id,user_id,rating,review_comment) values (2,1, 9, 'Amazing !!!');
insert into reviews(book_id,user_id,rating,review_comment) values (5,1, 5, 'The story is good but too long');
insert into reviews(book_id,user_id,rating,review_comment) values (3,2, 10, 'The best book I read!!!');
insert into reviews(book_id,user_id,rating,review_comment) values (4,1, 7.5, 'Good story !!!');
insert into reviews(book_id,user_id,rating,review_comment) values (6,1, 5, 'I read better books of this author!!!');
insert into reviews(book_id,user_id,rating,review_comment) values (1,2, 10, 'Love it!!!');
insert into reviews(book_id,user_id,rating,review_comment) values (2,1, 7.5, 'Amazing !!!');
insert into reviews(book_id,user_id,rating,review_comment) values (5,1, 4, 'I didnt relate to the writing style');
insert into reviews(book_id,user_id,rating,review_comment) values (7,2, 10, 'Perfect story, I strongly recommend to read it!!!');
insert into reviews(book_id,user_id,rating,review_comment) values (9,1, 7.5, 'Good !!!');
insert into reviews(book_id,user_id,rating,review_comment) values (8,1, 6, 'Average book!!!');
