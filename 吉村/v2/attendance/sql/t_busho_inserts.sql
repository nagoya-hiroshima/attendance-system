INSERT INTO t_busho(deploy_id,deploy_name)
VALUES
(1,'営業'),
(2,'総務'),
(3,'開発');
NO CONFLICT (deploy_id)DO NOTHING;