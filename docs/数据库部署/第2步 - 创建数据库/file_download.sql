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

 Date: 09/02/2018 23:32:44
*/


-- ----------------------------
-- Table structure for file_download
-- ----------------------------
DROP TABLE IF EXISTS "public"."file_download";
CREATE TABLE "public"."file_download" (
  "file_id" int4 NOT NULL DEFAULT nextval('file_download_file_id_seq'::regclass),
  "file_name" varchar(50) COLLATE "pg_catalog"."default",
  "file_url" varchar(200) COLLATE "pg_catalog"."default",
  "file_upload_date" timestamp(6),
  "file_download_count" int4
)
;

-- ----------------------------
-- Primary Key structure for table file_download
-- ----------------------------
ALTER TABLE "public"."file_download" ADD CONSTRAINT "file_download_pkey" PRIMARY KEY ("file_id");
