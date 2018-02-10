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

 Date: 09/02/2018 23:32:36
*/


-- ----------------------------
-- Table structure for code_library
-- ----------------------------
DROP TABLE IF EXISTS "public"."code_library";
CREATE TABLE "public"."code_library" (
  "code_id" int4 NOT NULL DEFAULT nextval('code_library_code_id_seq'::regclass),
  "code_title" varchar(50) COLLATE "pg_catalog"."default",
  "code_author" varchar(30) COLLATE "pg_catalog"."default",
  "code_date" timestamp(6),
  "code_read" int4,
  "code_summary" varchar(255) COLLATE "pg_catalog"."default",
  "code_html_content" text COLLATE "pg_catalog"."default",
  "code_md_content" text COLLATE "pg_catalog"."default",
  "code_label" varchar(50) COLLATE "pg_catalog"."default",
  "code_delete" int4,
  "code_category" int4
)
;

-- ----------------------------
-- Primary Key structure for table code_library
-- ----------------------------
ALTER TABLE "public"."code_library" ADD CONSTRAINT "code_library_pkey" PRIMARY KEY ("code_id");
