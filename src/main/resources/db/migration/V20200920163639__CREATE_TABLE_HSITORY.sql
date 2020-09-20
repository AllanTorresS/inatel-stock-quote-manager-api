create table history
(
    id varchar(255) not null,
    primary key (id)
) engine=InnoDB;

create table history_quotes
(
    history_id varchar(255) not null,
    quotes     varchar(255),
    quotes_key varchar(255) not null,
    primary key (history_id, quotes_key)
) engine=InnoDB;


alter table history_quotes
    add constraint FK_HISTORY_ID_CONSTRAINT
    foreign key (history_id)
    references history (id)