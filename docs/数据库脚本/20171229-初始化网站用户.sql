-- 清空数据库
DELETE FROM user_info;

-- 增加密码长度至32位
ALTER TABLE user_info alter COLUMN user_password type character varying(32);

-- 插入初始用户
INSERT INTO user_info (user_id, user_name, user_password, user_email) VALUES
(0, 'husen', '7c5c8c3ac9fda2953de063d1f069c7f7', 'husen@hemingsheng.cn'),
(1, 'super_admin', '2f0b3214c90231b9ad9f341bf1df8035', 'he_mingsheng@qq.com'),
(2, 'admin', 'c19380b712c30d00881bc9cfb3c74050', '940706904@qq.com');

-- 插入husen用户昵称
UPDATE user_info SET user_nick_name = '何明胜' WHERE user_id = 0;