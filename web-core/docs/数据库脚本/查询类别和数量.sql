SELECT
	category_name,
	COUNT (*) AS COUNT
FROM
	article_category
LEFT JOIN blog_details ON category_id = blog_category
WHERE
	blog_category IS NOT NULL
GROUP BY
	category_name;