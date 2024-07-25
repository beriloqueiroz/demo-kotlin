create table if not exists packages (
     package_id BIGINT AUTO_INCREMENT,
     created_at varchar(255) not null,
     updated_at varchar(255) not null,
     last_status varchar(255) not null,
     primary key (package_id)
);

create table if not exists package_events (
    event_id BIGINT AUTO_INCREMENT,
    tracker_type varchar(255) not null,
    tracking_code varchar(255) not null,
    description varchar(255) not null,
    origin varchar(255) not null,
    destionation varchar(255) not null,
    package_id bigint not null,
    primary key (event_id)
);
