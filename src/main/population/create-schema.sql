
    create table `accepted_auditor` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `administrator` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `announcements` (
       `id` integer not null,
        `version` integer not null,
        `addinfo` varchar(255),
        `creationdate` datetime(6),
        `text` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `anonymous` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `application` (
       `id` integer not null,
        `version` integer not null,
        `creation_moment` datetime(6),
        `final_mode` bit not null,
        `justification` varchar(255),
        `qualification` varchar(255),
        `ref` varchar(255),
        `skill` varchar(255),
        `statement` varchar(255),
        `status` varchar(255),
        `updated_status_moment` datetime(6),
        `job_id` integer not null,
        `worker_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `audit_record` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `creation_moment` datetime(6),
        `status` varchar(255),
        `title` varchar(255),
        `auditor_id` integer not null,
        `job_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `auditor` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `firm` varchar(255),
        `statement` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `authenticated` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `banner` (
       `id` integer not null,
        `version` integer not null,
        `banner` varchar(255),
        `slogan` varchar(255),
        `url` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `challenge` (
       `id` integer not null,
        `version` integer not null,
        `bronze` varchar(255),
        `bronzereward_amount` double precision,
        `bronzereward_currency` varchar(255),
        `deadline` datetime(6),
        `description` varchar(255),
        `gold` varchar(255),
        `goldreward_amount` double precision,
        `goldreward_currency` varchar(255),
        `silver` varchar(255),
        `silverreward_amount` double precision,
        `silverreward_currency` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `commercial` (
       `id` integer not null,
        `version` integer not null,
        `banner` varchar(255),
        `slogan` varchar(255),
        `url` varchar(255),
        `card_id` integer,
        `sponsor_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `company_records` (
       `id` integer not null,
        `version` integer not null,
        `ceoname` varchar(255),
        `description` varchar(255),
        `email` varchar(255),
        `incorporated` bit,
        `name` varchar(255),
        `phone` varchar(255),
        `sector` varchar(255),
        `stars` integer,
        `web` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `credit_card` (
       `id` integer not null,
        `version` integer not null,
        `cvv` varchar(255),
        `deadline` varchar(255),
        `number` varchar(255),
        `owner_name` varchar(255),
        `sponsor_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `descriptor` (
       `id` integer not null,
        `version` integer not null,
        `description` varchar(255),
        `job_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `duty` (
       `id` integer not null,
        `version` integer not null,
        `description` varchar(255),
        `percent` integer,
        `title` varchar(255),
        `descriptor_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `employer` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `company` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `investor_record` (
       `id` integer not null,
        `version` integer not null,
        `name` varchar(255),
        `sector` varchar(255),
        `stars` integer,
        `statement` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `job` (
       `id` integer not null,
        `version` integer not null,
        `deadline` datetime(6),
        `description` varchar(255),
        `more_info` varchar(255),
        `reference` varchar(255),
        `salary_amount` double precision,
        `salary_currency` varchar(255),
        `status` varchar(255),
        `title` varchar(255),
        `employer_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `message` (
       `id` integer not null,
        `version` integer not null,
        `accepted` bit,
        `body` varchar(255),
        `creation_moment` datetime(6),
        `tags` varchar(255),
        `title` varchar(255),
        `thread_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `message_thread` (
       `id` integer not null,
        `version` integer not null,
        `creation_moment` datetime(6),
        `title` varchar(255),
        `administrator_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `message_thread_authenticated` (
       `id` integer not null,
        `version` integer not null,
        `thread_id` integer,
        `user_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `non_commercial` (
       `id` integer not null,
        `version` integer not null,
        `banner` varchar(255),
        `slogan` varchar(255),
        `url` varchar(255),
        `jingle` varchar(255),
        `sponsor_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `offer` (
       `id` integer not null,
        `version` integer not null,
        `creationmoment` datetime(6),
        `deadline` datetime(6),
        `description` varchar(255),
        `reward_amount` double precision,
        `reward_currency` varchar(255),
        `ticker` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `provider` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `company` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `request` (
       `id` integer not null,
        `version` integer not null,
        `creation_moment` datetime(6),
        `dead_line` datetime(6),
        `reward_amount` double precision,
        `reward_currency` varchar(255),
        `text` varchar(255),
        `ticker` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `spamlist` (
       `id` integer not null,
        `version` integer not null,
        `idiom` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `spamword` (
       `id` integer not null,
        `version` integer not null,
        `spamword` varchar(255),
        `spamlist_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `sponsor` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `organisation` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `user_account` (
       `id` integer not null,
        `version` integer not null,
        `enabled` bit not null,
        `identity_email` varchar(255),
        `identity_name` varchar(255),
        `identity_surname` varchar(255),
        `password` varchar(255),
        `username` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `worker` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `qualifications` varchar(255),
        `skills` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `hibernate_sequence` (
       `next_val` bigint
    ) engine=InnoDB;

    insert into `hibernate_sequence` values ( 1 );
create index IDX5tgkkxnpohjsc4p10bs80cpme on `application` (`ref` asc);
create index IDX2q2747fhp099wkn3j2yt05fhs on `application` (`status` asc);
create index IDX618is0hf6jk8mhi0qeume2hqw on `application` (`creation_moment` desc);

    alter table `application` 
       add constraint UK_sqi7i8b9wxmiu57ftr95ssexh unique (`ref`);

    alter table `job` 
       add constraint UK_7jmfdvs0b0jx7i33qxgv22h7b unique (`reference`);
create index IDXq2o9psuqfuqmq59f0sq57x9uf on `offer` (`deadline`);
create index IDXcp4664f36sgqsd0ihmirt0w0 on `offer` (`ticker`);

    alter table `offer` 
       add constraint UK_iex7e8fs0fh89yxpcnm1orjkm unique (`ticker`);
create index IDX9ita8hqkxsxlcd2hsrtgslqm0 on `request` (`dead_line`);
create index IDXq4d9sbuyxv92qtwohvgbai27y on `request` (`dead_line`, `ticker`);

    alter table `request` 
       add constraint UK_9mxq3powq8tqctclj0fbi2nih unique (`ticker`);
create index IDX2insomc4a40jprju8tmgcvmig on `spamword` (`spamword`);

    alter table `user_account` 
       add constraint UK_castjbvpeeus0r8lbpehiu0e4 unique (`username`);

    alter table `accepted_auditor` 
       add constraint FK_pp83hmdgaolbxl9p2weha53fy 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `administrator` 
       add constraint FK_2a5vcjo3stlfcwadosjfq49l1 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `anonymous` 
       add constraint FK_6lnbc6fo3om54vugoh8icg78m 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `application` 
       add constraint `FKoa6p4s2oyy7tf80xwc4r04vh6` 
       foreign key (`job_id`) 
       references `job` (`id`);

    alter table `application` 
       add constraint `FKmbjdoxi3o93agxosoate4sxbt` 
       foreign key (`worker_id`) 
       references `worker` (`id`);

    alter table `audit_record` 
       add constraint `FKdcrrgv6rkfw2ruvdja56un4ji` 
       foreign key (`auditor_id`) 
       references `auditor` (`id`);

    alter table `audit_record` 
       add constraint `FKlbvbyimxf6pxvbhkdd4vfhlnd` 
       foreign key (`job_id`) 
       references `job` (`id`);

    alter table `auditor` 
       add constraint FK_clqcq9lyspxdxcp6o4f3vkelj 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `authenticated` 
       add constraint FK_h52w0f3wjoi68b63wv9vwon57 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `commercial` 
       add constraint `FKnbdymiyaaubi8jpb2afo6p8x2` 
       foreign key (`card_id`) 
       references `credit_card` (`id`);

    alter table `commercial` 
       add constraint `FK2jw28sba4n2gi3xdkdqqhm870` 
       foreign key (`sponsor_id`) 
       references `sponsor` (`id`);

    alter table `credit_card` 
       add constraint `FK31l5hvh7p1nx1aw6v649gw3rc` 
       foreign key (`sponsor_id`) 
       references `sponsor` (`id`);

    alter table `descriptor` 
       add constraint `FKgfulfilmwi4hhaquiu7fr5g0g` 
       foreign key (`job_id`) 
       references `job` (`id`);

    alter table `duty` 
       add constraint `FK3cc3garl37bl7gswreqwr7pj4` 
       foreign key (`descriptor_id`) 
       references `descriptor` (`id`);

    alter table `employer` 
       add constraint FK_na4dfobmeuxkwf6p75abmb2tr 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `job` 
       add constraint `FK3rxjf8uh6fh2u990pe8i2at0e` 
       foreign key (`employer_id`) 
       references `employer` (`id`);

    alter table `message` 
       add constraint `FKq851een84mnkrhyssa05q7je` 
       foreign key (`thread_id`) 
       references `message_thread` (`id`);

    alter table `message_thread` 
       add constraint `FKqs5ayrsbpr4dn7sufcl1x2pqv` 
       foreign key (`administrator_id`) 
       references `user_account` (`id`);

    alter table `message_thread_authenticated` 
       add constraint `FK5hkl2eosfv1vpc97uhxqj988q` 
       foreign key (`thread_id`) 
       references `message_thread` (`id`);

    alter table `message_thread_authenticated` 
       add constraint `FKga1oyn9oxkdor5spjyt2rlaur` 
       foreign key (`user_id`) 
       references `user_account` (`id`);

    alter table `non_commercial` 
       add constraint `FKqo73ln7f61vbg9r4a06esfujd` 
       foreign key (`sponsor_id`) 
       references `sponsor` (`id`);

    alter table `provider` 
       add constraint FK_b1gwnjqm6ggy9yuiqm0o4rlmd 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `spamword` 
       add constraint `FKrk7poykhk0ukf2dm6oqv3rejm` 
       foreign key (`spamlist_id`) 
       references `spamlist` (`id`);

    alter table `sponsor` 
       add constraint FK_20xk0ev32hlg96kqynl6laie2 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `worker` 
       add constraint FK_l5q1f33vs2drypmbdhpdgwfv3 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);
