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

 Date: 09/02/2018 23:32:53
*/


-- ----------------------------
-- Table structure for image_upload
-- ----------------------------
DROP TABLE IF EXISTS "public"."image_upload";
CREATE TABLE "public"."image_upload" (
  "image_id" int4 NOT NULL DEFAULT nextval('image_upload_image_id_seq'::regclass),
  "image_name" varchar(50) COLLATE "pg_catalog"."default",
  "image_url" varchar(200) COLLATE "pg_catalog"."default",
  "image_upload_date" timestamp(6),
  "image_type" int4,
  "image_download_count" int4
)
;

-- ----------------------------
-- Primary Key structure for table image_upload
-- ----------------------------
ALTER TABLE "public"."image_upload" ADD CONSTRAINT "image_upload_pkey" PRIMARY KEY ("image_id");
