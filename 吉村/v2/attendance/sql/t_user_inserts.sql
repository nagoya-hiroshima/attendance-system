-- ======================================
-- 注意: テーブルにデータを追加する際の実行順序
-- 1. t_busho を先に実行（部署データ）
-- 2. t_user を次に実行（ユーザデータ、外部キー deploy_id を参照）
-- ======================================
INSERT INTO t_user(user_id,name,password,deploy_id,work_place,mail_address,telephone_num,emergency_num)
VALUES
(123456,'山田太郎','truepassword',1,'リモート','user@gmail.com','01234567890','09876543210');
ON CONFLICT (user_id)DO NOTHING;