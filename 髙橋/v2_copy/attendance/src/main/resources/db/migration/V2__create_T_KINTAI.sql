CREATE TABLE T_KINTAI (
    attendance_id BIGSERIAL PRIMARY KEY,
    user_id       INTEGER NOT NULL REFERENCES T_USER(user_id),
    clockin_time  TIMESTAMP NOT NULL,
    clockout_time TIMESTAMP
);