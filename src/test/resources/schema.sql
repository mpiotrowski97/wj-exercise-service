create table exercises
(
    id          varchar(36)  not null primary key,
    category    int          null,
    description varchar(255) null,
    name        varchar(255) null,
    type        int          null,
    video_url   varchar(255) null
);
