INSERT INTO T_BUSHO (deploy_id, deploy_name) VALUES
    (1, '総務部'),
    (2, '営業部'),
    (3, '開発部'),
    (4, '事務部'),
    (5, '人事部')
ON CONFLICT (deploy_id) DO NOTHING;