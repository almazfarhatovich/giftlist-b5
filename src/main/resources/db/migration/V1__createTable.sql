alter table if exists bookings drop constraint if exists FKeyog2oic85xg7hsu2je2lx3s6;
alter table if exists bookings_gifts drop constraint if exists FKaifbhysikkcojs4r57sni8kb8;
alter table if exists bookings_gifts drop constraint if exists FKtkwa93e9261fs2esd09jqw9hj;
alter table if exists bookings_wishes drop constraint if exists FKhqe8grm4m8mh0iohsgd6rswa5;
alter table if exists bookings_wishes drop constraint if exists FK7wmru0f6ebe1e2gmginrj7dn;
alter table if exists complaints drop constraint if exists FK77231ld2tocbhx6klwaojw1qw;
alter table if exists complaints drop constraint if exists FK47ogpaug148d857rxgeffaf5s;
alter table if exists complaints drop constraint if exists FKa7d8c4tetxx3mnqaj73wlkqy8;
alter table if exists friends drop constraint if exists FK1t6y979j76dre2026t39gimk7;
alter table if exists friends drop constraint if exists FKlh21lfp7th1y1tn9g63ihkda9;
alter table if exists gifts drop constraint if exists FK645vi6gl7kj7n9xiwt3etdvch;
alter table if exists gifts drop constraint if exists FK8a13qahts004vfuyytaapqyce;
alter table if exists gifts drop constraint if exists FKaa2kh6uw64k5vujegh15o7qp3;
alter table if exists gifts drop constraint if exists FKhp2w6t8lhr03xuh8cab3tlosn;
alter table if exists gifts drop constraint if exists FK9wvjf7q1m4bjdql130h43d1j8;
alter table if exists holidays drop constraint if exists FK4e2a1pjumo9ugc2dhwb4d8ec4;
alter table if exists notifications drop constraint if exists FK9y21adhxn0ayjhfocscqox7bh;
alter table if exists notifications_types drop constraint if exists FK8iuumoaaj65407mvnbky3fs9d;
alter table if exists notifications_types drop constraint if exists FK6ss6dtxxyb0qmo33ka4libqx9;
alter table if exists request_to_friends drop constraint if exists FKqvtsi7bpjyodq09oyn49l73uu;
alter table if exists request_to_friends drop constraint if exists FKne1xp3jyu52a8y8lvkrk31wcf;
alter table if exists sub_categories drop constraint if exists FKjwy7imy3rf6r99x48ydq45otw;
alter table if exists types drop constraint if exists FKb279qr9uf9ettm5v5djjd665h;
alter table if exists types drop constraint if exists FKlv8w4sns7ya8yxeyen5r7lfu5;
alter table if exists types drop constraint if exists FKe9jhleoesq8lrvbefjx4o7hh;
alter table if exists types drop constraint if exists FK1miyt36uewbbvm9q4j6up646m;
alter table if exists users drop constraint if exists FKa4pav0806byoi689xgfobjqic;
alter table if exists wishes drop constraint if exists FKq5urq9dgya7ssh29b7ya2egl4;
alter table if exists wishes drop constraint if exists FK7bouwxl23ytlt6jw844ddcin8;
alter table if exists wishes drop constraint if exists FKn3v2tj3598yochbtikh3ao07v;
alter table if exists wishes drop constraint if exists FKh4fwumji30i8f8lt9gnhqxjy7;
drop table if exists bookings cascade;
drop table if exists bookings_gifts cascade;
drop table if exists bookings_wishes cascade;
drop table if exists categories cascade;
drop table if exists complaints cascade;
drop table if exists friends cascade;
drop table if exists gifts cascade;
drop table if exists holidays cascade;
drop table if exists mailing_lists cascade;
drop table if exists notifications cascade;
drop table if exists notifications_types cascade;
drop table if exists request_to_friends cascade;
drop table if exists sub_categories cascade;
drop table if exists types cascade;
drop table if exists users cascade;
drop table if exists users_info cascade;
drop table if exists wishes cascade;
drop sequence if exists booking_seq;
drop sequence if exists category_seq;
drop sequence if exists complaint_seq;
drop sequence if exists gift_seq;
drop sequence if exists holiday_seq;
drop sequence if exists mailing_list_seq;
drop sequence if exists notification_seq;
drop sequence if exists sub_category_seq;
drop sequence if exists type_seq;
drop sequence if exists user_seq;
drop sequence if exists user_info_seq;
drop sequence if exists wish_seq;
create sequence booking_seq start 1 increment 1;
create sequence category_seq start 1 increment 1;
create sequence complaint_seq start 1 increment 1;
create sequence gift_seq start 7 increment 1;
create sequence holiday_seq start 7 increment 1;
create sequence mailing_list_seq start 1 increment 1;
create sequence notification_seq start 1 increment 1;
create sequence sub_category_seq start 1 increment 1;
create sequence type_seq start 1 increment 1;
create sequence user_seq start 7 increment 1;
create sequence user_info_seq start 7 increment 1;
create sequence wish_seq start 7 increment 1;
create table bookings (id int8 not null, user_id int8, primary key (id));
create table bookings_gifts (booking_id int8 not null, gifts_id int8 not null);
create table bookings_wishes (booking_id int8 not null, wishes_id int8 not null);
create table categories (id int8 not null, name varchar(255), primary key (id));
create table complaints (id int8 not null, text varchar(4000), from_user_id int8, gift_id int8, wishes_id int8, primary key (id));
create table friends (user_id int8 not null, friends_id int8 not null);
create table gifts (id int8 not null, created_at date, description varchar(4000), is_block boolean, name varchar(255), photo varchar(255), status varchar(255), booking_id int8, category_id int8, from_user_id int8, sub_category_id int8, user_id int8, primary key (id));
create table holidays (id int8 not null, holiday_date date, is_block boolean, name varchar(255), photo varchar(255), user_id int8, primary key (id));
create table mailing_lists (id int8 not null, email varchar(255), primary key (id));
create table notifications (id int8 not null, created_at date, is_read boolean, text varchar(255), user_id int8, primary key (id));
create table notifications_types (notification_id int8 not null, types_id int8 not null);
create table request_to_friends (user_id int8 not null, request_to_friends_id int8 not null);
create table sub_categories (id int8 not null, name varchar(255), category_id int8, primary key (id));
create table types (id int8 not null, type_status varchar(255), gift_id int8, holiday_id int8, user_id int8, wish_id int8, primary key (id));
create table users (id int8 not null, email varchar(255), first_name varchar(255), is_block boolean, last_name varchar(255), password varchar(255), photo varchar(255), role varchar(255), user_info_id int8, primary key (id));
create table users_info (id int8 not null, city varchar(255), clothing_size varchar(255), date_of_birth date, facebook_link varchar(255), hobby varchar(4000), important_note varchar(4000), instagram_link varchar(255), phone_number varchar(255), shoe_size int4 not null, telegram_link varchar(255), vk_link varchar(255), primary key (id));
create table wishes (id int8 not null, created_at timestamp, description varchar(4000), is_block boolean, is_hidden boolean, wish_date date, wish_link varchar(255), wish_name varchar(255), wish_photo varchar(255), booking_id int8, from_user_id int8, holiday int8, user_id int8, primary key (id));
alter table if exists bookings_gifts add constraint UK_lg6pkfxj8gyonurbkvwwuy245 unique (gifts_id);
alter table if exists bookings_wishes add constraint UK_efunivux2jsobtv5qrvpdhyo0 unique (wishes_id);
alter table if exists notifications_types add constraint UK_59p1y4i45cddqc6pconuranyf unique (types_id);
alter table if exists users add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email);
alter table if exists users add constraint UK_r53o2ojjw4fikudfnsuuga336 unique (password);
alter table if exists bookings add constraint FKeyog2oic85xg7hsu2je2lx3s6 foreign key (user_id) references users;
alter table if exists bookings_gifts add constraint FKaifbhysikkcojs4r57sni8kb8 foreign key (gifts_id) references gifts;
alter table if exists bookings_gifts add constraint FKtkwa93e9261fs2esd09jqw9hj foreign key (booking_id) references bookings;
alter table if exists bookings_wishes add constraint FKhqe8grm4m8mh0iohsgd6rswa5 foreign key (wishes_id) references wishes;
alter table if exists bookings_wishes add constraint FK7wmru0f6ebe1e2gmginrj7dn foreign key (booking_id) references bookings;
alter table if exists complaints add constraint FK77231ld2tocbhx6klwaojw1qw foreign key (from_user_id) references users;
alter table if exists complaints add constraint FK47ogpaug148d857rxgeffaf5s foreign key (gift_id) references gifts;
alter table if exists complaints add constraint FKa7d8c4tetxx3mnqaj73wlkqy8 foreign key (wishes_id) references wishes;
alter table if exists friends add constraint FK1t6y979j76dre2026t39gimk7 foreign key (friends_id) references users;
alter table if exists friends add constraint FKlh21lfp7th1y1tn9g63ihkda9 foreign key (user_id) references users;
alter table if exists gifts add constraint FK645vi6gl7kj7n9xiwt3etdvch foreign key (booking_id) references bookings;
alter table if exists gifts add constraint FK8a13qahts004vfuyytaapqyce foreign key (category_id) references categories;
alter table if exists gifts add constraint FKaa2kh6uw64k5vujegh15o7qp3 foreign key (from_user_id) references users;
alter table if exists gifts add constraint FKhp2w6t8lhr03xuh8cab3tlosn foreign key (sub_category_id) references sub_categories;
alter table if exists gifts add constraint FK9wvjf7q1m4bjdql130h43d1j8 foreign key (user_id) references users;
alter table if exists holidays add constraint FK4e2a1pjumo9ugc2dhwb4d8ec4 foreign key (user_id) references users;
alter table if exists notifications add constraint FK9y21adhxn0ayjhfocscqox7bh foreign key (user_id) references users;
alter table if exists notifications_types add constraint FK8iuumoaaj65407mvnbky3fs9d foreign key (types_id) references types;
alter table if exists notifications_types add constraint FK6ss6dtxxyb0qmo33ka4libqx9 foreign key (notification_id) references notifications;
alter table if exists request_to_friends add constraint FKqvtsi7bpjyodq09oyn49l73uu foreign key (request_to_friends_id) references users;
alter table if exists request_to_friends add constraint FKne1xp3jyu52a8y8lvkrk31wcf foreign key (user_id) references users;
alter table if exists sub_categories add constraint FKjwy7imy3rf6r99x48ydq45otw foreign key (category_id) references categories;
alter table if exists types add constraint FKb279qr9uf9ettm5v5djjd665h foreign key (gift_id) references gifts;
alter table if exists types add constraint FKlv8w4sns7ya8yxeyen5r7lfu5 foreign key (holiday_id) references holidays;
alter table if exists types add constraint FKe9jhleoesq8lrvbefjx4o7hh foreign key (user_id) references users;
alter table if exists types add constraint FK1miyt36uewbbvm9q4j6up646m foreign key (wish_id) references wishes;
alter table if exists users add constraint FKa4pav0806byoi689xgfobjqic foreign key (user_info_id) references users_info;
alter table if exists wishes add constraint FKq5urq9dgya7ssh29b7ya2egl4 foreign key (booking_id) references bookings;
alter table if exists wishes add constraint FK7bouwxl23ytlt6jw844ddcin8 foreign key (from_user_id) references users;
alter table if exists wishes add constraint FKn3v2tj3598yochbtikh3ao07v foreign key (holiday) references holidays;
alter table if exists wishes add constraint FKh4fwumji30i8f8lt9gnhqxjy7 foreign key (user_id) references users;
