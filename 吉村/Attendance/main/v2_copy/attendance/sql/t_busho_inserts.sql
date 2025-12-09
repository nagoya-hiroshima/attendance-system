-- ======================================
-- 注意: テーブルにデータを追加する際の実行順序
-- 1. t_busho を先に実行（部署データ）
-- 2. t_user を次に実行（ユーザデータ、外部キー deploy_id を参照）
-- ======================================

INSERT INTO t_busho(deploy_id,deploy_name)
VALUES
(1,'営業'),
(2,'総務'),
(3,'開発');
NO CONFLICT (deploy_id)DO NOTHING;