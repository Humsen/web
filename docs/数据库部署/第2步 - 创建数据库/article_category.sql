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

 Date: 09/02/2018 23:32:07
*/


-- ----------------------------
-- Table structure for article_category
-- ----------------------------
DROP TABLE IF EXISTS "public"."article_category";
CREATE TABLE "public"."article_category" (
  "category_id" int4 NOT NULL DEFAULT nextval('article_category_category_id_seq'::regclass),
  "category_name" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "create_date" timestamp(6),
  "category_delete" int4 NOT NULL
)
;

-- ----------------------------
-- Primary Key structure for table article_category
-- ----------------------------
ALTER TABLE "public"."article_category" ADD CONSTRAINT "article_category_pkey" PRIMARY KEY ("category_id");
