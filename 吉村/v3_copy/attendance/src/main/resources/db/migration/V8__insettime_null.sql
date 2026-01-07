ALTER TABLE t_user ALTER COLUMN insert_time DROP DEFAULT;
UPDATE t_user
SET insert_time = NULL;
