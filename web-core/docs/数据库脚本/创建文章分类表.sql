CREATE TABLE article_category
(  
  category_id serial PRIMARY KEY NOT NULL,  
  category_name VARCHAR(50) NOT NULL,
	create_date TIMESTAMP,
  category_delete INT NOT NULL
); 

/** 插入初始化参数 **/
INSERT INTO article_category (category_id, category_name, create_date, category_delete)
VALUES (0, '所有文章', LOCALTIMESTAMP (0), 0);