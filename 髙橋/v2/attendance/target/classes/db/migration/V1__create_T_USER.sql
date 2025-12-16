CREATE TABLE T_USER (
    user_id        BIGSERIAL PRIMARY KEY,
    name           VARCHAR(20) NOT NULL,
    password       VARCHAR(32) NOT NULL,
    deploy_id      INTEGER NOT NULL REFERENCES T_BUSHO(deploy_id),
    work_place     VARCHAR(20),
    mail_address   VARCHAR(100),
    telephone_num  VARCHAR(15),
    emergency_num  VARCHAR(15),

    insert_time    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);