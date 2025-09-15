DROP TABLE IF EXISTS appointment CASCADE;
DROP TABLE IF EXISTS training CASCADE;
create table if not exists appointment (id uuid not null, begin_date date not null, end_date date not null, number_of_participants integer not null, training_id uuid not null, primary key (id));
create table if not exists training (id uuid not null, description varchar(255) not null, price float(24) not null, speaker varchar(255) not null, primary key (id));
alter table  appointment add constraint FKhsvcl3619he859lf65kheyfwe foreign key (training_id) references training;