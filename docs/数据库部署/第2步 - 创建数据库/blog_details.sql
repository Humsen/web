/*
 Navicat Premium Data Transfer

 Source Server         : 腾讯云
 Source Server Type    : PostgreSQL
 Source Server Version : 90317
 Source Host           : 115.159.64.86:5432
 Source Catalog        : web
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 90317
 File Encoding         : 65001

 Date: 09/02/2018 23:32:19
*/


-- ----------------------------
-- Table structure for blog_details
-- ----------------------------
DROP TABLE IF EXISTS "public"."blog_details";
CREATE TABLE "public"."blog_details" (
  "blog_id" int4 NOT NULL DEFAULT nextval('blog_details_blog_id_seq'::regclass),
  "blog_title" varchar(255) COLLATE "pg_catalog"."default",
  "blog_author" varchar(255) COLLATE "pg_catalog"."default",
  "blog_date" timestamp(6),
  "blog_read" int4,
  "blog_summary" varchar(255) COLLATE "pg_catalog"."default",
  "blog_html_content" text COLLATE "pg_catalog"."default",
  "blog_md_content" text COLLATE "pg_catalog"."default",
  "blog_label" varchar(50) COLLATE "pg_catalog"."default",
  "blog_delete" int4,
  "blog_category" int4
)
;

-- ----------------------------
-- Primary Key structure for table blog_details
-- ----------------------------
ALTER TABLE "public"."blog_details" ADD CONSTRAINT "blog_details_pkey" PRIMARY KEY ("blog_id");
