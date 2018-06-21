/**  文章目录 */
INSERT INTO article_category(category_id, category_name, create_date, category_delete) VALUES (0, '所有文章', '2017-12-12 18:11:34', 0);

/** 博客demo */
INSERT INTO blog_details(blog_id, blog_title, blog_author, blog_date, blog_read, blog_summary, blog_html_content, blog_md_content, blog_label, blog_delete, blog_category) VALUES (1, '第一篇博客', 'husen', '2018-02-10 20:22:36', 0, '博客demo', '这是测试博客的第一篇文章', '这是测试博客的第一篇文章', '博客 测试', 0, 0);

/** 代码demo */
INSERT INTO code_library(code_id, code_title, code_author, code_date, code_read, code_summary, code_html_content, code_md_content, code_label, code_delete, code_category) VALUES (1, '第一篇代码', 'husen', '2018-02-10 20:25:38', 0, '代码demo', '这是测试代码的第一篇文章', '这是测试代码的第一篇文章', '代码 测试', 0, 0);

/** 版本特性 */
INSERT INTO release_feature(release_id, release_author, release_date, release_number, release_content) VALUES (0, '何明胜', '2017-09-30 16:20:29', 'V1.0.3', '<p>1、主页显示版本特性、最近更新及其他栏目导航</p>

<p>2、实现发表博客、查看博客、浏览量统计、选择每页显示的博客数量等</p>

<p>3、实现上传代码至代码库、查看代码组件、浏览量统计、分页显示等</p>

<p>4、实现留言、回复留言、分页浏览功能</p>

<p>5、实现下载、下载统计、联系站长发送邮件等基本功能</p>

<p>6、实现登录注册功能、网站单日访问量与总访问量统计、实现在线人数统计</p>');

/** 用户信息 初始密码均为123123 */
INSERT INTO user_info(user_id, user_name, user_password, user_email, user_phone, user_age, user_sex, user_address, user_head_url, user_nick_name) VALUES (0, 'husen', 'cf5fe8a4669d7300ddac03170796e012', 'husen@hemingsheng.cn', NULL, NULL, NULL, NULL, NULL, '何明胜');
INSERT INTO user_info(user_id, user_name, user_password, user_email, user_phone, user_age, user_sex, user_address, user_head_url, user_nick_name) VALUES (1, 'super_admin', '2f0b3214c90231b9ad9f341bf1df8035', 'he_mingsheng@qq.com', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO user_info(user_id, user_name, user_password, user_email, user_phone, user_age, user_sex, user_address, user_head_url, user_nick_name) VALUES (2, 'admin', 'c19380b712c30d00881bc9cfb3c74050', '940706904@qq.com', NULL, NULL, NULL, NULL, NULL, NULL);

/** 网站访问统计 */
INSERT INTO visit_total(visit_id, visit_date, visit_count) VALUES (0, '2017-09-29', 0);