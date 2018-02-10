-- 清空数据库
DELETE FROM user_info;

-- 增加密码长度至32位
ALTER TABLE user_info alter COLUMN user_password type character varying(32);

-- 重置自增id
ALTER SEQUENCE user_info_user_id_seq RESTART WITH 1;

-- 插入超级管理员用户
INSERT INTO user_info (user_id, user_name, user_nick_name, user_password, user_email) VALUES
(0, 'husen', '何明胜', '7c5c8c3ac9fda2953de063d1f069c7f7', 'husen@hemingsheng.cn');

-- 插入保留管理账户
INSERT INTO user_info (user_name, user_password, user_email) VALUES
('super_admin', '2f0b3214c90231b9ad9f341bf1df8035', 'he_mingsheng@qq.com'),
('admin', 'c19380b712c30d00881bc9cfb3c74050', '940706904@qq.com');