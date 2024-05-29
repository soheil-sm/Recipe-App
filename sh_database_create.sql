create table category (id bigint not null auto_increment, description varchar(255), primary key (id)) engine=InnoDB;
create table ingredient (amount decimal(38,2), id bigint not null auto_increment, recipe_id bigint, uom_id bigint, description varchar(255), primary key (id)) engine=InnoDB;
create table notes (id bigint not null auto_increment, recipe_notes longtext, primary key (id)) engine=InnoDB;
create table recipe (cook_time integer, prep_time integer, servings integer, id bigint not null auto_increment, notes_id bigint, description varchar(255), source varchar(255), url varchar(255), difficulty enum ('EASY','MODERATE','KIND_OF_HARD','HARD'), directions tinytext, image tinyblob, primary key (id)) engine=InnoDB;
create table recipe_category (category_id bigint not null, recipe_id bigint not null, primary key (category_id, recipe_id)) engine=InnoDB;
create table unit_of_measure (id bigint not null auto_increment, description varchar(255), primary key (id)) engine=InnoDB;
alter table recipe add constraint UK_61xwuqykaisawug77hnwnhg3i unique (notes_id);
alter table ingredient add constraint FKj0s4ywmqqqw4h5iommigh5yja foreign key (recipe_id) references recipe (id);
alter table ingredient add constraint FK6iv5l89qmitedn5m2a71kta2t foreign key (uom_id) references unit_of_measure (id);
alter table recipe add constraint FK37al6kcbdasgfnut9xokktie9 foreign key (notes_id) references notes (id);
alter table recipe_category add constraint FKqsi87i8d4qqdehlv2eiwvpwb foreign key (category_id) references category (id);
alter table recipe_category add constraint FKcqlqnvfyarhieewfeayk3v25v foreign key (recipe_id) references recipe (id);
