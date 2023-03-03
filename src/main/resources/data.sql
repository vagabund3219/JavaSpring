delete from Book;
delete from Author;
INSERT INTO Author ( au_name, telephone, email, rating)
VALUES ('Толстой Лев Никола́евич', ' no_phone', 'lev_tolstoi@mail.ru', 'cool');
insert into Author ( au_name, telephone, email, rating)
values ('Пушкин Александр Сергеевич', 'no_phone', 'goonduel@yandex.ru', 'coolest');
insert into Book ( book_name, binding, publisher, publishedYear, genre, authorId)
values ('Война и Мир', 'Мягкий', 'Эксмо', '1867', 'Роман', 1);
insert into Book ( book_name, binding, publisher, publishedYear, genre, authorId)
values ('Капитанская дочка', 'Жесткий', 'Эксмо', '1836', 'Роман', 2);

