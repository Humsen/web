alter table blog_details add column blog_category INT; 
alter table code_library add column code_category INT; 

--初始化为0，默认分类所有文章
UPDATE blog_details SET blog_category = 0;
UPDATE code_library SET code_category = 0;