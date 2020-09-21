create table quotes_history (
       id varchar(255) not null,
        primary key (id)
) engine=InnoDB;

create table quotes_history_quotes (
   quotes_history_id varchar(255) not null,
   quotes varchar(255),
   quotes_key varchar(255) not null,
   primary key (quotes_history_id, quotes_key)
) engine=InnoDB;

alter table quotes_history_quotes
  add constraint FK_QUOTES_HISTORY_ID_CONSTRAINT
  foreign key (quotes_history_id)
  references quotes_history (id);