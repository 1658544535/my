--拼得客-用户信息表
CREATE TABLE user_pindeke_info (
  id bigint(20) NOT NULL auto_increment COMMENT 'ID',
  user_id bigint(20) default '0' COMMENT '用户ID',
  name varchar(100) default '' COMMENT '真实姓名',
  phone varchar(20) default '' COMMENT '手机号码',
  card_no varchar(20) default '' COMMENT '身份证号码',
  extend_channel varchar(100) default '' COMMENT '推广渠道',
  extend_img1 varchar(50) default '' COMMENT '推广证明（图片1）',
  extend_img2 varchar(50) default '' COMMENT '推广证明（图片2）',
  extend_img3 varchar(50) default '' COMMENT '推广证明（图片3）',
  extend_img4 varchar(50) default '' COMMENT '推广证明（图片4）',
  extend_img5 varchar(50) default '' COMMENT '推广证明（图片5）',
  return_msg text COMMENT '退回原因',
  rebate_price double(20,2) default '0.00' COMMENT '返佣金额',
  withdraw_price double(20,2) default '0.00' COMMENT '提现金额',
  surplu_price double(20,2) default '0.00' COMMENT '剩余金额',
  is_delete int(1) default '0' COMMENT '删除标识（0-否1-是）',
  status int(1) default '0' COMMENT '审核状态（0-审核中1-审核通过2-审核不通过3-冻结）',
  create_by bigint(20) default '0' COMMENT '创建者',
  create_date datetime default NULL COMMENT '创建时间',
  update_by bigint(20) default '0' COMMENT '更新者',
  update_date datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (id),
  KEY IDX_USERID USING BTREE (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='拼得客-用户信息表';

--用户交易记录表
CREATE TABLE user_deal_log (
  id bigint(20) NOT NULL auto_increment COMMENT 'ID',
  user_id bigint(20) default '0' COMMENT '用户ID',
  deal_date datetime default NULL COMMENT '交易时间',
  deal_amount double(20,2) default '0.00' COMMENT '交易金额',
  deal_type int(1) default '0' COMMENT '交易类型（1-收入2-支出）',
  status int(1) default '0' COMMENT '审核状态（0-待审核1-审核通过2-审核不通过3-转账完成）',
  group_id bigint(20) default '0' COMMENT '拼团记录ID（返佣）',
  name varchar(100) default '' COMMENT '真实姓名（提现）',
  type varchar(100) default '' COMMENT '转账方式（提现）',
  type_no varchar(20) default '' COMMENT '转账帐号（提现）',
  order_no varchar(20) default '' COMMENT '交易单号（提现）',
  return_msg text COMMENT '审核不通过原因（提现）',
  over_time datetime default NULL COMMENT '转账完成时间（提现）',
  create_by bigint(20) default '0' COMMENT '创建者',
  create_date datetime default NULL COMMENT '创建时间',
  update_by bigint(20) default '0' COMMENT '更新者',
  update_date datetime default NULL COMMENT '更新时间',
  remark int(1) default '0' COMMENT '备注（1-返佣2-提现）',
  PRIMARY KEY  (id),
  KEY IDX_USERID USING BTREE (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户交易记录表';

ALTER TABLE sys_login
ADD COLUMN is_pindeke  int(1) NULL DEFAULT 0 COMMENT '是否拼得客标识（0-否1-是）' AFTER source;

ALTER TABLE groupon_activity_record
ADD COLUMN invitation_user_id  bigint(20) NULL DEFAULT 0 COMMENT '邀请者（拼得客）用户ID' AFTER update_by,
ADD COLUMN is_rebate  int(1) NULL DEFAULT 0 COMMENT '是否返佣（0-否1-是）' AFTER invitation_user_id,
ADD COLUMN rebate_time  datetime NULL DEFAULT NULL COMMENT '返佣时间' AFTER is_rebate;


#团免券增加拼得客标识
alter table group_free_coupon add invitation_user_id  bigint NULL DEFAULT 0 COMMENT '邀请者（拼得客）用户ID';

INSERT INTO sys_menu (id, name, name_en, level, path, icon, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES (286, '拼得客管理', '', 0, 'url', '/images/menu_icon_10.png', 17, 1, 1, '2016-10-15 10:56:33', 1, '2016-10-15 10:57:36', null, 0);
INSERT INTO sys_menu (id, name, name_en, level, path, icon, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES (287, '拼得客信息管理', '', 286, 'goUserPindekeInfo.do', null, 0, 1, 1, '2016-10-15 11:00:02', 1, '2016-10-15 11:00:02', null, 0);
INSERT INTO sys_menu (id, name, name_en, level, path, icon, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES (288, '拼得客提现管理', '', 286, 'goUserDealLog.do', null, 1, 1, 1, '2016-10-15 11:02:42', 1, '2016-10-15 11:59:57', null, 0);
INSERT INTO sys_menu (id, name, name_en, level, path, icon, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES (291, '拼得客待结算结束团', '', 286, 'goUserEndGroupSettle.do', null, 0, 1, 1, '2016-10-16 15:05:57', 1, '2016-10-16 15:05:57', null, 0);

INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 291);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 288);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 287);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 286);

ALTER TABLE user_order_refund
ADD COLUMN is_refund  int(2) NULL DEFAULT 0 COMMENT '退款状态：0-未退款，1-处理中，2-退款成功，3-退款失败' AFTER phone,
ADD COLUMN out_refund_no  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户退款单号' AFTER is_refund,
ADD COLUMN refund_date  datetime NULL DEFAULT NULL COMMENT '退款时间' AFTER out_refund_no;

ALTER TABLE alipay_order_info
ADD COLUMN source  int(2) NULL DEFAULT 0 COMMENT '来源：0-拼团失败退款 ，1-售后退款' AFTER refund_status;

ALTER TABLE wxpay_order_info
ADD COLUMN source  int(2) NULL DEFAULT 0 COMMENT '来源：0-拼团失败退款 ，1-售后退款' AFTER trade_type;

CREATE TABLE groupon_push (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  product_image varchar(50) DEFAULT '' COMMENT '商品图片',
  address varchar(50) DEFAULT '' COMMENT '地址（省份）',
  name varchar(50) DEFAULT '' COMMENT '用户呢称',
  order_id bigint(20) DEFAULT '0' COMMENT '订单ID',
  attend_id bigint(20) DEFAULT '0' COMMENT '参与ID',
  create_by bigint(20) DEFAULT '0' COMMENT '创建者',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  update_by bigint(20) DEFAULT '0' COMMENT '更新者',
  update_date datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='开团推送';

