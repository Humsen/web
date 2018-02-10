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

 Date: 09/02/2018 23:33:12
*/


-- ----------------------------
-- Table structure for release_feature
-- ----------------------------
DROP TABLE IF EXISTS "public"."release_feature";
CREATE TABLE "public"."release_feature" (
  "release_id" int4 NOT NULL DEFAULT nextval('release_feature_release_id_seq'::regclass),
  "release_author" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
  "release_date" timestamp(6) NOT NULL,
  "release_number" varchar(20) COLLATE "pg_catalog"."default",
  "release_content" text COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON TABLE "public"."release_feature" IS '每次版本更新的新版特性，都存在数据库里';

-- ----------------------------
-- Primary Key structure for table release_feature
-- ----------------------------
ALTER TABLE "public"."release_feature" ADD CONSTRAINT "release_feature_pkey" PRIMARY KEY ("release_id");
