

create table if not exists Author (
    id bigint auto_increment primary key,
    au_name char(50) ,
    telephone varchar(20) not null,
    email varchar(50) not null,
    rating varchar(50) not null
    );

create table if not exists Book (
    id bigint auto_increment primary key,
    authorId bigint,
    foreign key (authorId) references Author(id),
    book_name varchar(70) not null,
    binding varchar(50) not null,
    publisher varchar(100) not null,
    publishedYear varchar(4) not null,
    genre varchar(50) not null
    );


-- alter table Book
--     add foreign key (author_key) references Author(id);

