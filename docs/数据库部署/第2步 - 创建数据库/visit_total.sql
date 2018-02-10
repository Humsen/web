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

 Date: 09/02/2018 23:33:31
*/


-- ----------------------------
-- Table structure for visit_total
-- ----------------------------
DROP TABLE IF EXISTS "public"."visit_total";
CREATE TABLE "public"."visit_total" (
  "visit_id" int4 NOT NULL DEFAULT nextval('visit_total_visit_id_seq'::regclass),
  "visit_date" date,
  "visit_count" int4
)
;
COMMENT ON TABLE "public"."visit_total" IS '第一行为总访问量，其余为每天访问量';

-- ----------------------------
-- Primary Key structure for table visit_total
-- ----------------------------
ALTER TABLE "public"."visit_total" ADD CONSTRAINT "visit_total_pkey" PRIMARY KEY ("visit_id");
