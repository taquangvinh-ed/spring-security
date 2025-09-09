create table users(username varchar(50) not null primary key,password varchar(500) not null,enabled boolean not null);
create table authorities (username varchar(50) not null,authority varchar(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authorities (username,authority);

INSERT INTO "public"."users" (username, password, enabled) VALUES ('user', '{noop}Eazybank@12345', true) ON CONFLICT DO NOTHING;
INSERT INTO "public"."authorities" VALUES ('user', 'read')

INSERT INTO "public"."users"  VALUES ('admin', '{bcrypt}$2a$12$kihGxlx32BMSR.yIuqmh3O01JwO5UjAdn6926dm6/nhr/09sftUba', true) ON CONFLICT DO NOTHING;
INSERT INTO "authorities" VALUES ('admin', 'admin');

CREATE TABLE "customer" (
  "id" BIGSERIAL,
  "email" varchar(45) NOT NULL,
  "pwd" varchar(200) NOT NULL,
  "role" varchar(45) NOT NULL,
  PRIMARY KEY ("id")
);

INSERT  INTO "customer" ("email", "pwd", "role") VALUES ('happy@example.com', '{noop}EazyBytes@12345', 'read');
INSERT  INTO "customer" ("email", "pwd", "role") VALUES ('admin@example.com', '{bcrypt}$2a$12$88.f6upbBvy0okEa7OfHFuorV29qeK.sVbB9VQ6J6dWM1bW6Qef8m', 'admin');