## 数据库修改脚本 ## ## 商品规格

## 订单表增加 订单来源和平团是否成功状态
ALTER TABLE user_order ADD source INT(2) DEFAULT '0' COMMENT '来源，1-普通拼团2-团免3-猜价';
ALTER TABLE user_order ADD source_id bigint DEFAULT '0' NOT NULL COMMENT '团购参与id';
ALTER TABLE user_order ADD is_success INT(2) DEFAULT '0' COMMENT '类型，1-拼团成功2-拼团失败';
ALTER TABLE user_order ADD group_num INT DEFAULT 0 COMMENT 'N人团人数';
ALTER TABLE user_order ADD group_date DATETIME COMMENT '成团时间';

## 订单表增加 订单来源和平团是否成功状态
ALTER TABLE focus_setting ADD start_time DATETIME COMMENT '开始时间';
ALTER TABLE focus_setting ADD end_time DATETIME COMMENT '结束时间';
ALTER TABLE focus_setting MODIFY type INT(2) DEFAULT '0' COMMENT '类型，1-首页2-猜价';
ALTER TABLE focus_setting MODIFY param_type INT(2) DEFAULT '0' COMMENT '参数类型(0-无;1-商品;2-普通拼团;3-猜价格;)';

## 用户优惠券表
ALTER TABLE user_coupon ADD product_id bigint DEFAULT 0 COMMENT '指定商品ID';
## 团免券表
CREATE TABLE group_free_coupon (
	id bigint NOT NULL auto_increment COMMENT 'ID',
	user_id bigint DEFAULT '0' NOT NULL COMMENT '用户id',
	status TINYINT(1) unsigned DEFAULT '0' NOT NULL COMMENT '激活状态，0未激活，1已激活',
	active_time DATETIME COMMENT '激活时间',
	invalid_time DATETIME COMMENT '失效时间',
	used TINYINT(1) unsigned DEFAULT '0' NOT NULL COMMENT '是否已使用，0否1是',
	last_use_time DATETIME COMMENT '上次使用时间',
	source INT(10) unsigned DEFAULT '0' COMMENT '来源',
	version INT(2) DEFAULT '0',
	create_date DATETIME DEFAULT NULL COMMENT '创建时间',
	create_by bigint DEFAULT 0 COMMENT '创建人',
	update_date DATETIME DEFAULT NULL COMMENT '修改时间',
	update_by bigint DEFAULT 0 COMMENT '修改人',
	PRIMARY KEY (id),
	INDEX IDX_USERID (user_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='团免券';
##添加唯一约束
alter table group_free_coupon add unique(user_id);

## 团免券领取设置表
CREATE TABLE group_free_coupon_setting (
	id bigint NOT NULL auto_increment COMMENT 'ID',
	invalid_time DATETIME COMMENT '失效时间',
	num INT DEFAULT 0 COMMENT '可领取人数',
	surplus_num INT DEFAULT 0 COMMENT '剩余人数',
	status TINYINT(1) unsigned DEFAULT '0' NOT NULL COMMENT '状态，0-未审核1-审核通过',
	version INT(2) DEFAULT 0,
	create_date DATETIME DEFAULT NULL COMMENT '创建时间',
	create_by bigint DEFAULT 0 COMMENT '创建人',
	update_date DATETIME DEFAULT NULL COMMENT '修改时间',
	update_by bigint DEFAULT 0 COMMENT '修改人',
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='团免券领取设置';

## 团购记录表
CREATE TABLE groupon_activity_record (
	id bigint NOT NULL auto_increment COMMENT 'ID',
	activity_type INT(2) NOT NULL DEFAULT 0 COMMENT '活动类型,1-普通团2-团免',
	activity_id bigint DEFAULT '0' NOT NULL COMMENT '活动id',
	begin_time DATETIME COMMENT '开始时间',
	end_time DATETIME COMMENT '结束时间',
	user_id bigint DEFAULT '0' NOT NULL COMMENT '团长用户id',
	num INT DEFAULT 0 COMMENT '拼团人数',
	surplus_num INT DEFAULT 0 COMMENT '剩余人数',
	status INT unsigned DEFAULT '0' NOT NULL COMMENT '状态，1-成功2-失败',
	version INT(2) DEFAULT 0,
	create_date DATETIME DEFAULT NULL COMMENT '创建时间',
	create_by bigint DEFAULT 0 COMMENT '创建人',
	update_date DATETIME DEFAULT NULL COMMENT '修改时间',
	update_by bigint DEFAULT 0 COMMENT '修改人',
	PRIMARY KEY (id),
	INDEX IDX_USERID (user_id),
	INDEX IDX_ACTIVITY_TYPE (activity_type),
	INDEX IDX_ACTIVITYID (activity_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='团购记录';

## 团购用户记录表
CREATE TABLE groupon_user_record (
	id bigint NOT NULL auto_increment COMMENT 'ID',
	activity_type INT(2) NOT NULL DEFAULT 0 COMMENT '活动类型,1-普通团2-团免3-猜价',
	activity_id bigint DEFAULT '0' NOT NULL COMMENT '活动id',
	attend_id bigint DEFAULT '0' NOT NULL COMMENT '参与团购记录id',
	user_id bigint DEFAULT '0' NOT NULL COMMENT '参与用户id',
	status INT unsigned DEFAULT '0' NOT NULL COMMENT '状态，0-普通1-团免2-猜价中奖',
	is_head TINYINT(1) unsigned DEFAULT '0' COMMENT '是否团长，0-否1-是',
	attend_time DATETIME COMMENT '参与时间',
	coupon_id bigint DEFAULT 0 COMMENT '团免券ID',
	price DOUBLE(20,2) DEFAULT 0.00 COMMENT '猜价价格', 
	prize INT unsigned DEFAULT '0' COMMENT '中奖，1-一等奖2-二等奖3-三等奖',
	version INT(2) DEFAULT 0,
	create_date DATETIME DEFAULT NULL COMMENT '创建时间',
	create_by bigint DEFAULT 0 COMMENT '创建人',
	update_date DATETIME DEFAULT NULL COMMENT '修改时间',
	update_by bigint DEFAULT 0 COMMENT '修改人',
	PRIMARY KEY (id),
	INDEX IDX_USERID (user_id),
	INDEX IDX_ACTIVITY_TYPE (activity_type),
	INDEX IDX_ACTIVITYID (activity_id),
	INDEX IDX_ATTENDID (attend_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='团购用户记录';

## 活动表
CREATE TABLE groupon_activity(
	id bigint NOT NULL AUTO_INCREMENT COMMENT '编号id',
	product_id bigint NOT NULL DEFAULT 0 COMMENT '商品ID',
	begin_time DATETIME COMMENT '开始时间',
	end_time DATETIME COMMENT '结束时间',
	num INT DEFAULT 0 COMMENT '拼团人数',
	price DOUBLE(20,2) DEFAULT 0.00 COMMENT '拼团价格',
	is_default TINYINT(1) unsigned DEFAULT '0' NOT NULL COMMENT '是否默认，0-否1-是',
	price_min VARCHAR(20) DEFAULT '' COMMENT '猜价价格区间起',
	price_max VARCHAR(20) DEFAULT '' COMMENT '猜价价格区间止',
	status TINYINT(1) unsigned DEFAULT '0' NOT NULL COMMENT '审核状态，0-未审核1-审核通过',
	activity_status INT unsigned DEFAULT '0' NOT NULL COMMENT '活动状态，0-未开始1-活动中2-活动结束3-已开奖',
	type INT unsigned DEFAULT 0 COMMENT '活动类型，1-普通拼团2-团免3-猜价',
	sorting INT(10) DEFAULT '0' COMMENT '排序',
	title VARCHAR(150) COMMENT '标题',
	banner VARCHAR(30) COMMENT 'banner',
	is_delete TINYINT(1) unsigned DEFAULT 0 COMMENT '删除状态，0-否1-是',
	create_by bigint DEFAULT '0' COMMENT '创建者',
	create_date DATETIME COMMENT '创建时间',
	update_by bigint DEFAULT '0' COMMENT '更新者',
	update_date DATETIME COMMENT '更新时间',
	PRIMARY KEY (id),
	INDEX IDX_PRODUCTID (product_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='团购活动表';

## 首页团购推荐表
CREATE TABLE groupon_recommend(
	id bigint NOT NULL AUTO_INCREMENT COMMENT '编号id',
	activity_id bigint default 0 NOT NULL COMMENT '团购活动ID', 
	product_id bigint default 0 NOT NULL COMMENT '商品ID', 
	sorting INT(10) DEFAULT '0' COMMENT '排序',
	create_by bigint DEFAULT '0' COMMENT '创建者',
	create_date DATETIME COMMENT '创建时间',
	update_by bigint DEFAULT '0' COMMENT '更新者',
	update_date DATETIME COMMENT '更新时间',
	PRIMARY KEY (id),
	INDEX IDX_ACTIVITYID (activity_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='首页团购推荐';

DROP TABLE COUPON;
CREATE TABLE coupon(
	coupon_id INT unsigned NOT NULL AUTO_INCREMENT COMMENT '优惠券id',
	name VARCHAR(200) NOT NULL COMMENT '优惠券名称',
	type SMALLINT(1) unsigned DEFAULT '0' NOT NULL COMMENT '类型，1满m减n金额，2直减金额',
	status TINYINT(1) unsigned DEFAULT '1' NOT NULL COMMENT '状态，0禁用，1启用',
	valid_stime INT(10) unsigned DEFAULT '0' NOT NULL COMMENT '有效期开始时间',
	valid_etime INT(10) unsigned DEFAULT '0' NOT NULL COMMENT '有效期结束时间',
	create_time INT(10) unsigned DEFAULT '0' NOT NULL COMMENT '创建时间',
	content text NOT NULL COMMENT 'json格式数据，1为{om:订单金额,m:扣减金额}，2{m:扣减金额}',
	sheet_num INT(10) unsigned DEFAULT 0 NOT NULL COMMENT '优惠券数',
	surplus_num INT(10) unsigned DEFAULT 0 NOT NULL COMMENT '可领取数',
	is_product TINYINT(1) unsigned DEFAULT 0 COMMENT '是否指定商品，0-否1-是',
	product_id bigint DEFAULT 0 COMMENT '商品ID',
	is_delete TINYINT(1) unsigned DEFAULT 0 COMMENT '删除状态，0-否1-是',
	caption VARCHAR(300) DEFAULT '' COMMENT '优惠券说明',
	channel INT DEFAULT '0' COMMENT '0-普通',
	PRIMARY KEY (coupon_id),
	INDEX valid_time (valid_stime, valid_etime)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='优惠券表';

set foreign_key_checks=0;     //关闭外键检查
DROP TABLE product_sku_link;
CREATE TABLE product_sku_link (
	Id INT NOT NULL AUTO_INCREMENT,
	product_id bigint DEFAULT 0 COMMENT '产品编号',
	sku_color VARCHAR(100) DEFAULT '' COMMENT '颜色',
	sku_format VARCHAR(100) DEFAULT '' COMMENT '款式',
	image VARCHAR(30) DEFAULT '' COMMENT 'sku图片',
	stock_num INT DEFAULT '0' COMMENT '库存总量',
	stock INT DEFAULT '0' COMMENT '库存',
	sorting INT DEFAULT'0' COMMENT '排序',
	status INT(2) DEFAULT '0' COMMENT '状态,0未审核1已审核',
	type INT(2) DEFAULT '0' COMMENT'0-普通;1-活动',
	price DOUBLE(20,2) DEFAULT '0.00' COMMENT 'sku价格',
	activity_id bigint DEFAULT '0'COMMENT '活动编号id',
	business_code VARCHAR(100) COMMENT '商家编码',
	create_by bigint COMMENT '创建者',
	create_date DATETIME COMMENT '创建时间',
	update_by bigint COMMENT '更新者',
	update_date DATETIME COMMENT'更新时间',
	remarks VARCHAR(255) COMMENT '备注信息',
	version INT(2) COMMENT '版本：0.中文版1.英文版',
	PRIMARY KEY (Id),
	FOREIGN KEY (product_id) REFERENCES product (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT ='产品SKU表';
set foreign_key_checks=1;  //打开外键检查

ALTER TABLE product
ADD COLUMN image_small  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '产品主图片名称（商品缩略图）' AFTER sale_type;
ADD COLUMN image_main  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '产品主图片名称（banner图）' AFTER image_small;
MODIFY COLUMN image  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '产品主图片名称（高清缩略图）' AFTER ladder_price;

## 发货订单导入表
CREATE TABLE delivery_order_import (
    id bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
	user_id bigint(20) default '0' COMMENT '用户id',
	batch_no varchar(30) default '' COMMENT '批次号（当前时间戳）',
	order_no varchar(50) default '' COMMENT '订单号',
	logistics_name varchar(100) default '' COMMENT '快递公司',
	logistics_no varchar(50) default '' COMMENT '快递单号',
    status int(2) default '0' COMMENT '状态（0失败，1成功）',
	remarks varchar(255) default '' COMMENT '备注（记录导入失败原因）',
	create_date datetime default NULL COMMENT '创建时间',
	create_by bigint(20) default '0' COMMENT '创建人',
	update_date datetime default NULL COMMENT '修改时间',
	update_by bigint(20) default '0' COMMENT '修改人',
    PRIMARY KEY  (id),
    INDEX IDX_USERID (user_id),
    INDEX IDX_ORDERNO (order_no) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='发货订单导入表';

INSERT INTO sys_menu (id, name, name_en, level, path, icon, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES (280, '团免活动', '', 275, 'goFreeGroupActivityList.do', null, 0, 1, 1, '2016-09-23 10:18:40', 1, '2016-09-23 10:18:40', null, 0);

INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 280);

INSERT INTO sys_menu (id, name, name_en, level, path, icon, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES (281, '团免券列表', '', 154, 'goGroupFreeCoupon.do', null, 0, 1, 1, '2016-09-23 14:43:31', 1, '2016-09-23 14:43:31', null, 0);

INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 281);

--新增新建商品menu--
INSERT INTO sys_menu (id, name, name_en, level, path, icon, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES (283, '新建商品', 'goproductAdd.do?productPojo.userId=3', 16, 'goproductAdd.do?productPojo.userId=3', null, 0, 1, 1, '2016-10-05 13:52:23', 1, '2016-10-05 13:52:23', null, 0);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 283);

--隐藏商品信息管理menu--
update sys_menu set status = 1 where id = 37
##是否领取优惠券
ALTER TABLE groupon_user_record ADD COLUMN is_rec_coupon TINYINT(1) DEFAULT 0 COMMENT '是否领取优惠卷(0否1是)';
## 用户来源
alter table sys_login add source int(2) default 0 comment '用户来源：1-ios,2-android,3-weixin';
#优惠券来源ID
alter table user_coupon add source_id bigint default 0 comment '来源ID';

ALTER TABLE user_order_refund
MODIFY COLUMN images  varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '图片1' AFTER version,
ADD COLUMN images2  varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '图片2' AFTER images,
ADD COLUMN images3  varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '图片3' AFTER images2;
#是否删除
ALTER TABLE user_collect ADD COLUMN is_delete INT(1) NULL DEFAULT 0 COMMENT '0-未删除;1-已删除';
#手机号码
ALTER TABLE user_order_refund ADD COLUMN phone VARCHAR(50) NULL COMMENT '下订单的人手机号码';

#订单退款
alter table user_order add is_refund int(2) default 0 comment '退款状态：0-未退款，1-处理中，2-退款成功，3-退款失败';
alter table user_order add out_refund_no varchar(50) comment '商户退款单号';
alter table user_order add refund_date datetime comment '退款时间';
alter table  user_order add index IDX_REFUND_NO(out_refund_no);

alter table wxpay_order_info add out_refund_no varchar(50) comment '商户退款单号';
alter table wxpay_order_info add refund_id varchar(50) comment '微信退款单号';
alter table wxpay_order_info add refund_fee varchar(20)  comment '退款金额';
alter table wxpay_order_info add refund_reason varchar(500)  comment '退款原因';
alter table wxpay_order_info add refund_status varchar(30) COMMENT '交易状态(PROCESSING;SUCCESS;FAIL)';
alter table  wxpay_order_info add index IDX_REFUND_NO(out_refund_no);

alter table alipay_order_info add out_refund_no varchar(50) comment '商户退款单号';
alter table alipay_order_info add refund_fee varchar(20)  comment '退款金额';
alter table alipay_order_info add refund_reason varchar(500)  comment '退款原因';
alter table alipay_order_info add refund_status varchar(30) COMMENT '交易状态(PROCESSING;SUCCESS;FAIL)';
alter table  alipay_order_info add index IDX_REFUND_NO(out_refund_no);

INSERT INTO sys_menu (id, name, name_en, level, path, icon, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES (284, '拼团失败退款管理', '', 275, 'goRefundUserOrder.do', null, 10, 1, 1, '2016-10-11 10:18:40', 1, '2016-10-11 10:18:40', null, 0);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 284);
#是否弹窗标记
ALTER TABLE groupon_user_record ADD is_alert INT(1) default 0 COMMENT '是否已弹窗(0否1是)';
#商品简述字段扩大
alter table product modify product_sketch varchar(1500) COMMENT '产品简述';

## 专题分类表
CREATE TABLE special_type(
    id bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    name varchar(255) default '' COMMENT '名称',
	status_display int(1) default '0' COMMENT '显示状态：0-隐藏 1-显示',
  status int(1) default '0' COMMENT '审核状态：0-未审核 1-已审核',
    sorting int(11) default 0 COMMENT '排序',
	create_date datetime default NULL COMMENT '创建时间',
	create_by bigint(20) default '0' COMMENT '创建人',
	update_date datetime default NULL COMMENT '修改时间',
	update_by bigint(20) default '0' COMMENT '修改人',
    PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专题分类表';
## 专题表
CREATE TABLE special(
    id bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    title varchar(255) default '' COMMENT '标题',
    image varchar(255) default '' COMMENT '专题顶部图',
    special_type_id bigint default '0' COMMENT '专题分类id',
    status int(2) default '0' COMMENT '审核状态（0未审核，1已审核）',
    sorting int(11) default 0 COMMENT '排序',
    is_delete int(2) DEFAULT 0 COMMENT '删除标记0正常1删除',
	create_date datetime default NULL COMMENT '创建时间',
	create_by bigint(20) default '0' COMMENT '创建人',
	update_date datetime default NULL COMMENT '修改时间',
	update_by bigint(20) default '0' COMMENT '修改人',
    PRIMARY KEY  (id),
    INDEX IDX_SPECIALTYPEID (special_type_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专题表';
## 专题商品表
CREATE TABLE special_goods(
    id bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    special_id bigint default '0' COMMENT '专题id',
    activity_id bigint default '0' COMMENT '团购活动表id',
    product_id bigint default '0' COMMENT '商品id',
    status int(2) default '0' COMMENT '审核状态（0未审核，1已审核）',
    sorting int(11) default 0 COMMENT '排序',
	create_date datetime default NULL COMMENT '创建时间',
	create_by bigint(20) default '0' COMMENT '创建人',
	update_date datetime default NULL COMMENT '修改时间',
	update_by bigint(20) default '0' COMMENT '修改人',
    PRIMARY KEY  (id),
    INDEX IDX_SPECIALID (special_id),
    INDEX IDX_PRODUCTID (product_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专题商品表';

## 专区表
CREATE TABLE zones(
    id bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    title varchar(255) default '' COMMENT '标题',
    image varchar(255) default '' COMMENT '专题顶部图',
    type int default '0' COMMENT '专区类型：1-7.7专区',
    status int(2) default '0' COMMENT '审核状态（0未审核，1已审核）',
    sorting int(11) default 0 COMMENT '排序',
    is_delete int(2) DEFAULT 0 COMMENT '删除标记0正常1删除',
	create_date datetime default NULL COMMENT '创建时间',
	create_by bigint(20) default '0' COMMENT '创建人',
	update_date datetime default NULL COMMENT '修改时间',
	update_by bigint(20) default '0' COMMENT '修改人',
    PRIMARY KEY  (id),
    INDEX IDX_TYPE (type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专区表';
## 专区商品表
CREATE TABLE zone_goods(
    id bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    zone_id bigint default '0' COMMENT '专区id',
    activity_id bigint default '0' COMMENT '团购活动表id',
    product_id bigint default '0' COMMENT '商品id',
    status int(2) default '0' COMMENT '审核状态（0未审核，1已审核）',
    sorting int(11) default 0 COMMENT '排序',
	create_date datetime default NULL COMMENT '创建时间',
	create_by bigint(20) default '0' COMMENT '创建人',
	update_date datetime default NULL COMMENT '修改时间',
	update_by bigint(20) default '0' COMMENT '修改人',
    PRIMARY KEY  (id),
    INDEX IDX_ZONEID (zone_id),
    INDEX IDX_ACTIVITYID (activity_id),
    INDEX IDX_PRODUCTID (product_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专区商品表';

#微信支付区分APP支付和公众号支付
alter table wxpay_order_info add trade_type varchar(30) default '' comment '交易类型：APP,JSAPI';

INSERT INTO sys_menu (id, name, name_en, level, path, icon, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES (285, '专题模块', '', 275, 'goSpecial.do', null, 3, 1, 1, '2016-10-14 21:40:37', 1, '2016-10-14 21:40:37', null, 0);
INSERT INTO sys_menu (id, name, name_en, level, path, icon, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES (292, '77专区', '', 275, 'goZones.do', null, 4, 1, 1, '2016-10-18 10:13:59', 1, '2016-10-18 10:13:59', null, 0);


ALTER TABLE group_free_coupon_setting
ADD COLUMN surplus_toys  int(11) NULL DEFAULT 0 COMMENT '剩余玩具' AFTER update_by;

UPDATE sys_dict SET name = "待发货" WHERE id = 6;


alter table groupon_activity add limit_num int default 0 comment '活动数量';
alter table groupon_activity add surplus_num int default 0 comment '剩余数量';
alter table groupon_activity modify type INT(10) unsigned DEFAULT 0 COMMENT '活动类型，1-普通拼团2-团免3-猜价5-0.1抽奖6-限时秒杀';
##修改参与状态
alter table groupon_user_record modify status INT(10) unsigned DEFAULT 0 NOT NULL COMMENT '状态，0-普通1-团免2-猜价中奖3-抽奖4-限时秒杀';
alter table groupon_activity_record modify activity_type INT(2) DEFAULT 0 NOT NULL COMMENT '活动类型,1-普通团2-团免3-猜价5-0.1抽奖6-限时秒杀';


## 限时秒杀表
CREATE TABLE seckill(
    id bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
	begin_time DATETIME COMMENT '开始时间',
    end_time DATETIME COMMENT '结束时间',
    type int default '0' COMMENT '活动类型：1-限时秒杀',
    status int(2) default '0' COMMENT '审核状态（0未审核，1已审核）',
    sorting int(11) default 0 COMMENT '排序',
    is_delete int(2) DEFAULT 0 COMMENT '删除标记0正常1删除',
	create_date datetime default NULL COMMENT '创建时间',
	create_by bigint(20) default '0' COMMENT '创建人',
	update_date datetime default NULL COMMENT '修改时间',
	update_by bigint(20) default '0' COMMENT '修改人',
    PRIMARY KEY  (id),
    INDEX IDX_TYPE (type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='限时秒杀表';
## 限时秒杀商品表
CREATE TABLE seckill_goods(
    id bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    seckill_id bigint default '0' COMMENT '秒杀表id',
    activity_id bigint default '0' COMMENT '团购活动表id',
    product_id bigint default '0' COMMENT '商品id',
    status int(2) default '0' COMMENT '审核状态（0未审核，1已审核）',
    sorting int(11) default 0 COMMENT '排序',
	create_date datetime default NULL COMMENT '创建时间',
	create_by bigint(20) default '0' COMMENT '创建人',
	update_date datetime default NULL COMMENT '修改时间',
	update_by bigint(20) default '0' COMMENT '修改人',
    PRIMARY KEY  (id),
    INDEX IDX_SECKILLID (seckill_id),
    INDEX IDX_ACTIVITYID (activity_id),
    INDEX IDX_PRODUCTID (product_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀商品表';

## 活动商品评论
CREATE TABLE activity_product_comment(
    id bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
	user_id bigint default 0 comment '用户ID',
	type int default '0' COMMENT '活动类型：1-0.1抽奖',
    activity_id bigint default '0' COMMENT '团购活动表id',
    product_id bigint default '0' COMMENT '商品id',
	content varchar(1500) default '' comment '评论内容',
	img1 varchar(30) default '' comment '评论图片1',
	img2 varchar(30) default '' comment '评论图片2',
	img3 varchar(30) default '' comment '评论图片3',
	img4 varchar(30) default '' comment '评论图片4',
	img5 varchar(30) default '' comment '评论图片5',
	img6 varchar(30) default '' comment '评论图片6',
	img7 varchar(30) default '' comment '评论图片7',
	img8 varchar(30) default '' comment '评论图片8',
	img9 varchar(30) default '' comment '评论图片9',
    status int(2) default '1' COMMENT '审核状态（0未审核，1已审核）',
    sorting int(11) default 0 COMMENT '排序',
	create_date datetime default NULL COMMENT '创建时间',
	create_by bigint(20) default '0' COMMENT '创建人',
	update_date datetime default NULL COMMENT '修改时间',
	update_by bigint(20) default '0' COMMENT '修改人',
    PRIMARY KEY  (id),
    INDEX IDX_ACTIVITYID (activity_id),
    INDEX IDX_PRODUCTID (product_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动商品评论表';

#首页图标增加类型字段
alter table home_page_icon add type int default 0 comment '类型：1-猜价格2-专区3-限时秒杀4-0.1抽奖';

insert into sys_menu (id, name, name_en, level, path, icon, sorting, status, create_by, create_date, update_by, update_date, remarks, version) values('293','0.1夺宝','','275','goDraw.do',NULL,'6','1','1','2016-10-26 09:28:47','1','2016-10-26 11:44:34',NULL,'0');

insert into sys_menu (id, name, name_en, level, path, icon, sorting, status, create_by, create_date, update_by, update_date, remarks, version) values('294','掌上秒杀','','275','goSeckill.do',NULL,'5','1','1','2016-10-26 09:39:43','1','2016-10-26 09:39:43',NULL,'0');

INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 294);

##增加团参与ID
alter table activity_product_comment add attend_id bigint default 0 comment '团参与ID';
##支付方式字典
INSERT INTO sys_dict (type, name, name_en, value, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES ('pay_method_type', '微信公众号', 'weixin', '8', 5, 1, 1, '2015-12-29 11:29:24', 1, '2015-12-29 11:29:24', null, 0);

UPDATE sys_menu SET NAME = "0.1夺宝" WHERE id = 293
UPDATE sys_menu SET NAME = "掌上秒杀" WHERE id = 294


#修改77专区路径
UPDATE sys_menu SET path = "goZones.do?type=1" WHERE id = 292
#新增菜单
insert into sys_menu (id, name, name_en, level, path, icon, sorting, status, create_by, create_date, update_by, update_date, remarks, version) values('296','新品专区','','275','goZones.do?type=2',NULL,'4','1','1','2016-11-14 14:37:32','1','2016-11-14 14:42:21',NULL,'0');


## 商品当日销售量记录表
CREATE TABLE product_sell (
    id bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
	product_id bigint default 0 comment '商品ID',
	product_name varchar(255) default '' comment '商品名称',
	product_image varchar(255) default '' comment '商品图片',
	price double(20,2) default 0.00 comment '价格（单独购价格）',
	sell_number int(11) default 0 comment '商品销量',
	day_sell int(11) default 0 comment '当日销售量',
	create_date datetime default NULL COMMENT '创建时间',
	create_by bigint(20) default '0' COMMENT '创建人',
	update_date datetime default NULL COMMENT '修改时间',
	update_by bigint(20) default '0' COMMENT '修改人',
    PRIMARY KEY  (id),
    INDEX IDX_PRODUCTID (product_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品当日销售量记录表';

##新增字段--当日销售量记录表
alter table product_sell add product_type1 varchar(100) default '' comment '一级分类';
alter table product_sell add product_type_ids varchar(255) default '' comment '二级分类';
alter table product_sell add status int(2) default '0' COMMENT '审核状态（0未审核，1已审核）';

##商品表老数据导入商品当日销售量记录表
INSERT INTO product_sell (product_id,product_name,product_image,price,sell_number,create_date,create_by,update_date,update_by,product_type1,product_type_ids,status) SELECT id,product_name,image,distribution_price,sell_number,create_date,create_by,update_date,update_by,product_type1,product_type_ids,status FROM product;

#新增菜单
insert into sys_menu (id, name, name_en, level, path, icon, sorting, status, create_by, create_date, update_by, update_date, remarks, version) values('298','拼团信息导出','','81','grouponExcel.do',NULL,'0','1','1','2016-11-25 10:17:21','1','2016-11-25 10:17:21',NULL,'0');

#新增菜单
INSERT INTO sys_menu (id, NAME, name_en, LEVEL, path, icon, sorting, STATUS, create_by, create_date, update_by, update_date, remarks, VERSION) VALUES('297','免费抽奖','','275','goFreeDrawList.do',NULL,'8','1','1','2016-11-21 16:03:57','1','2016-11-21 17:19:10',NULL,'0');
## 秒杀是否上首页
alter table seckill_goods add is_home int(2) default 0 comment '是否首页显示，0-不显示 1-显示';





########################################################## pindehao 1.5 ###############################################################
## 消息模板表
CREATE TABLE notice_template (
  id bigint NOT NULL auto_increment COMMENT 'ID',
  type int default '0' NOT NULL  COMMENT '模板类型1-支付成功2-开团成功3-拼团成功4-抽奖未中奖5-抽奖已中奖6-发货7-退款',
  title varchar(500) default '' COMMENT '模板标题',
  template varchar(3000) default '' COMMENT '模板信息',
  is_delete int(1) default '0' NOT NULL COMMENT '删除标识（0-否1-是）',
  status int(1) default '0' NOT NULL COMMENT '审核状态（0-未审核1-审核通过）',
  create_by bigint default '0' NOT NULL COMMENT '创建者',
  create_date datetime default NULL COMMENT '创建时间',
  update_by bigint default '0' NOT NULL COMMENT '更新者',
  update_date datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (id),
  INDEX IDX_TYPE (type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息模板表';

## 订单消息表
CREATE TABLE user_order_notice (
  id bigint NOT NULL auto_increment COMMENT 'ID',
  user_id bigint default '0' NOT NULL COMMENT '用户id',
  type int default '0' NOT NULL COMMENT '模板类型1-支付成功2-开团成功3-拼团成功4-抽奖未中奖5-抽奖已中奖6-发货7-退款',
  title varchar(500) default '' COMMENT '模板标题',
  content varchar(5000) default '' COMMENT '信息内容（ type：1-商品详情页，2-订单详情页，3-团详情页，4-首页，5-普通拼团，6-猜价格，7-免费抽奖，8-专题，9-专题分类，10-77专区，11-0.1夺宝）',
  is_delete int(1) default '0'  NOT NULL COMMENT '删除标识（0-否1-是）',
  version int default '0'  NOT NULL COMMENT '版本',
  create_by bigint(20) default '0' NOT NULL COMMENT '创建者',
  create_date datetime default NULL COMMENT '创建时间',
  update_by bigint(20) default '0'  NOT NULL COMMENT '更新者',
  update_date datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (id),
  INDEX IDX_TYPE (type),
  INDEX IDX_USERID(user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单消息表';

## 消息模板初始化
INSERT INTO notice_template (id, type, title, template, is_delete, status, create_by, create_date, update_by, update_date) VALUES (1, 1, '支付成功通知', '{"title":"支付成功通知","img":"/upfiles/product/20160929110349688601.jpg","name":"任选一本 阳光宝贝安全知识我知道儿童校园安全故事书籍","info":"您好，您有一笔订单支付成功。","pname":"任选一本 阳光宝贝安全知识我知道儿童校园安全故事书籍","price":"20.00","remark":"如有疑问，请联系客服！","lname":"订单详情","type":"1","param":""}', 0, 1, 1, '2016-11-30 15:00:00', 1, '2016-11-30 15:00:00');
INSERT INTO notice_template (id, type, title, template, is_delete, status, create_by, create_date, update_by, update_date) VALUES (2, 2, '开团成功通知', '{"title":"开团成功通知","img":"/upfiles/product/20160929110349688601.jpg","name":"任选一本 阳光宝贝安全知识我知道儿童校园安全故事书籍","info":"开团成功提醒","price":"20.00","pname":"任选一本 阳光宝贝安全知识我知道儿童校园安全故事书籍","address":"广州市从化区沿江南路","orderNo":"1480490774185040042","remark":"您已开团成功，赶紧邀请小伙伴们加入吧！","lname":"订单详情","type":"1","param":""}', 0, 1, 1, '2016-11-30 15:00:00', 1, '2016-11-30 15:00:00');
INSERT INTO notice_template (id, type, title, template, is_delete, status, create_by, create_date, update_by, update_date) VALUES (3, 3, '拼团成功通知', '{"title":"拼团成功通知","img":"/upfiles/product/20160929110349688601.jpg","name":"任选一本 阳光宝贝安全知识我知道儿童校园安全故事书籍","info":"恭喜您，拼团成功！我们会尽快为您发货！","price":"20.00","orderNo":"1480490774185040042","remark":"如有疑问，请联系客服！","lname":"订单详情","type":"1","param":""}', 0, 1, 1, '2016-11-30 15:00:00', 1, '2016-11-30 15:00:00');
INSERT INTO notice_template (id, type, title, template, is_delete, status, create_by, create_date, update_by, update_date) VALUES (4, 4, '抽奖未中奖通知', '{"title":"抽奖结果通知","img":"/upfiles/product/20160929110349688601.jpg","name":"任选一本 阳光宝贝安全知识我知道儿童校园安全故事书籍","info":"您没有中奖，您的款项正在退款中","pname":"任选一本 阳光宝贝安全知识我知道儿童校园安全故事书籍","price":"20.00","drawDate":"2016-11-30 10:00:00","drawStatus":"未中奖","remark":"如有疑问，请联系客服！","lname":"订单详情","type":"1","param":""}', 0, 1, 1, '2016-11-30 15:00:00', 1, '2016-11-30 15:00:00');
INSERT INTO notice_template (id, type, title, template, is_delete, status, create_by, create_date, update_by, update_date) VALUES (5, 5, '抽奖中奖通知', '{"title":"抽奖结果通知","img":"/upfiles/product/20160929110349688601.jpg","name":"任选一本 阳光宝贝安全知识我知道儿童校园安全故事书籍","info":"【恭喜您！中奖啦】您参与的活动开奖啦","pname":"任选一本 阳光宝贝安全知识我知道儿童校园安全故事书籍","price":"20.00","drawDate":"2016-11-30 10:00:00","drawStatus":"一等奖，获得该商品","remark":"赶紧点击领取商品>>","lname":"订单详情","type":"1","param":""}', 0, 1, 1, '2016-11-30 15:00:00', 1, '2016-11-30 15:00:00');
INSERT INTO notice_template (id, type, title, template, is_delete, status, create_by, create_date, update_by, update_date) VALUES (6, 6, '商品发货通知', '{"title":"商品发货通知","img":"/upfiles/product/20160929110349688601.jpg","name":"任选一本 阳光宝贝安全知识我知道儿童校园安全故事书籍","info":"您好，您的商品已发货","express":"顺丰快递","expressNo":"107998935964","pname":"任选一本 阳光宝贝安全知识我知道儿童校园安全故事书籍","num":"1","remark":"请注意查收！","lname":"订单详情","type":"1","param":""}', 0, 1, 1, '2016-11-30 15:00:00', 1, '2016-11-30 15:00:00');
INSERT INTO notice_template (id, type, title, template, is_delete, status, create_by, create_date, update_by, update_date) VALUES (7, 7, '退款通知', '{"title":"退款通知","img":"/upfiles/product/20160929110349688601.jpg","name":"任选一本 阳光宝贝安全知识我知道儿童校园安全故事书籍","info":"您好，您有一笔退款","price":"10.00","refundItem":"拼团失败","refundReason":"拼团失败","refundDate":"2016-11-30 10:00:00","orderNo":"1480490774185040042","remark":"如有疑问，请联系客服！","lname":"订单详情","type":"1","param":""}', 0, 1, 1, '2016-11-30 15:00:00', 1, '2016-11-30 15:00:00');
INSERT INTO notice_template (id, type, title, template, is_delete, status, create_by, create_date, update_by, update_date) VALUES (8, 8, '拼团失败通知', '{"title":"拼团失败通知","img":"/upfiles/product/20160929110349688601.jpg","name":"任选一本 阳光宝贝安全知识我知道儿童校园安全故事书籍","info":"拼团失败提醒","orderNo":"1480490774185040042","pname":"任选一本 阳光宝贝安全知识我知道儿童校园安全故事书籍","price":"10.00","refundPrice":"10.00","remark":"如有疑问，请联系客服！","lname":"订单详情","type":"2","param":""}', 0, 1, 1, '2016-11-30 15:00:00', 1, '2016-11-30 15:00:00');
INSERT INTO notice_template (id, type, title, template, is_delete, status, create_by, create_date, update_by, update_date) VALUES (9, 9, '参团成功通知', '{"title":"参团成功通知","img":"/upfiles/product/20160929110349688601.jpg","name":"任选一本 阳光宝贝安全知识我 知道儿童校园安全故事书籍","info":"参团成功提醒","price":"20.00","pname":"任选一本 阳光宝贝安全知识我知道儿童校园安全故事书籍","address":"广州市从化区沿江南路","orderNo":"1480490774185040042","remark":"您已参团成功，赶紧邀请小伙伴们加入吧！","lname":"订单详情","type":"2","param":""}', 0, 1, 1, '2016-11-30 15:00:00', 1, '2016-11-30 15:00:00');


#新增菜单

insert into sys_menu (id, name, name_en, level, path, icon, sorting, status, create_by, create_date, update_by, update_date, remarks, version) values('299','消息系统','','0','url','/images/menu_icon_10.png','20','1','1','2016-12-01 08:45:55','1','2016-12-01 08:45:55',NULL,'0');
insert into sys_menu (id, name, name_en, level, path, icon, sorting, status, create_by, create_date, update_by, update_date, remarks, version) values('300','消息模板','','299','goNoticeTemplate.do',NULL,'4','1','1','2016-12-01 08:51:41','1','2016-12-01 08:51:41',NULL,'0');

#新增菜单
insert into sys_menu (id, name, name_en, level, path, icon, sorting, status, create_by, create_date, update_by, update_date, remarks, version) values('303','版本更新管理','','20','goEditVersionControl.do',NULL,'0','1','1','2016-12-01 14:37:42','1','2016-12-01 14:39:20',NULL,'0');

##版本控制
CREATE TABLE version_control(
    id bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    channel INT(1) DEFAULT 0 NOT NULL COMMENT '渠道：1-IOS,2-ANDROID',
    state INT(1) DEFAULT 0 NOT NULL COMMENT '开启状态：0-未开启1-开启',
    last_version DOUBLE(6,1) DEFAULT 0.0 NOT NULL COMMENT '最新版本',
    force_version DOUBLE(6,1) DEFAULT 0.0 NOT NULL COMMENT '强制更新版本',
    sketch VARCHAR(1000) COMMENT '更新语',
    create_by bigint DEFAULT 0 NOT NULL COMMENT '创建者',
    create_date DATETIME COMMENT '创建时间',
    update_by bigint DEFAULT 0 NOT NULL COMMENT '更新者',
    update_date DATETIME COMMENT '更新时间',
    PRIMARY KEY (id),
    INDEX IDX_CHANNEL (channel)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='版本控制表';

INSERT INTO version_control (id, channel, state, last_version, force_version, sketch, create_by, create_date, update_by, update_date) VALUES (1, 1, 0, 0.0, 0.0, '拼得好 ，拼得好', 1, '2016-12-01 00:00:00', 1, '2016-12-01 16:13:51');
INSERT INTO version_control (id, channel, state, last_version, force_version, sketch, create_by, create_date, update_by, update_date) VALUES (2, 2, 0, 0.0, 0.0, '拼得好 ，拼得好', 1, '2016-12-01 00:00:00', 1, '2016-12-01 15:46:39');

##增加中奖团数
alter table groupon_activity add prize_num int(11) default 0 comment '中奖团数';

##增加 加入中奖列表字段
alter table groupon_activity_record add is_prize int(1) default 0 comment '加入中奖列表（0否1是）';

## 新增 商品限购表
CREATE TABLE product_restriction (
  id bigint(20) NOT NULL auto_increment COMMENT 'ID',
  user_id bigint(20) default '0' COMMENT '用户ID',
  activity_id bigint(20) default '0' COMMENT '活动ID',
  product_id bigint(20) default '0' COMMENT '商品ID',
  buy_num int(10) default '0' COMMENT '当前购买数量',
  max_num int(10) default '0' COMMENT '最大购买数量',
  create_by bigint(20) default '0' COMMENT '创建者',
  create_date datetime default NULL COMMENT '创建时间',
  update_by bigint(20) default '0' COMMENT '更新者',
  update_date datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品限购表';

CREATE TABLE user_coupon_flag (
  id bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  user_id bigint(20) DEFAULT NULL COMMENT '用户id',
  gain_date datetime DEFAULT NULL COMMENT '获取时间',
  PRIMARY KEY (id),
  KEY IDX_USER_ID (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='优惠券领取记录表';

## 修改 商品表
ALTER TABLE product
ADD COLUMN max_num  int(10) NULL DEFAULT 0 COMMENT '最大购买数量' AFTER image_main;

ALTER TABLE sku_attribute ADD dict_value BIGINT(11) DEFAULT '0' COMMENT '字典id';
ALTER TABLE product_sku_link ADD sku_color_id BIGINT(11) DEFAULT '0' COMMENT 'sku_attr_id';
ALTER TABLE product_sku_link ADD sku_format_id BIGINT(11) DEFAULT '0' COMMENT 'sku_attr_id';
ALTER TABLE product ADD format_value INT(11) DEFAULT '0' COMMENT '颜色(字典)';
ALTER TABLE product ADD color_value INT(11) DEFAULT '0' COMMENT '格式(字典)';
ALTER TABLE product ADD spec_type INT(2) DEFAULT '0' COMMENT '规格(1一种2两种)';

##sku属性字典

INSERT INTO sys_dict (id, type, name, name_en, value, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES(NULL,'sku_type','口味','','1','1','1','1','2016-11-29 15:19:03','1','2016-11-29 15:19:03',NULL,'0');
INSERT INTO sys_dict (id, type, name, name_en, value, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES(NULL,'sku_type','款式','','2','1','1','1','2016-11-29 15:19:03','1','2016-11-29 15:19:03',NULL,'0');
INSERT INTO sys_dict (id, type, name, name_en, value, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES(NULL,'sku_type','颜色','','3','1','1','1','2016-11-29 15:19:03','1','2016-11-29 15:19:03',NULL,'0');
INSERT INTO sys_dict (id, type, name, name_en, value, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES(NULL,'sku_type','尺码','','4','1','1','1','2016-11-29 15:19:03','1','2016-11-29 15:19:03',NULL,'0');
INSERT INTO sys_dict (id, type, name, name_en, value, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES(NULL,'sku_type','尺寸','','5','1','1','1','2016-11-29 15:19:03','1','2016-11-29 15:19:03',NULL,'0');
INSERT INTO sys_dict (id, type, name, name_en, value, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES(NULL,'sku_type','容量','','6','1','1','1','2016-11-29 15:19:03','1','2016-11-29 15:19:03',NULL,'0');
INSERT INTO sys_dict (id, type, name, name_en, value, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES(NULL,'sku_type','器型','','7','1','1','1','2016-11-29 15:19:03','1','2016-11-29 15:19:03',NULL,'0');
INSERT INTO sys_dict (id, type, name, name_en, value, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES(NULL,'sku_type','花型','','8','1','1','1','2016-11-29 15:19:03','1','2016-11-29 15:19:03',NULL,'0');
INSERT INTO sys_dict (id, type, name, name_en, value, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES(NULL,'sku_type','香型','','9','1','1','1','2016-11-29 15:19:03','1','2016-11-29 15:19:03',NULL,'0');
INSERT INTO sys_dict (id, type, name, name_en, value, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES(NULL,'sku_type','功效','','10','1','1','1','2016-11-29 15:19:03','1','2016-11-29 15:19:03',NULL,'0');
INSERT INTO sys_dict (id, type, name, name_en, value, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES(NULL,'sku_type','型号','','11','1','1','1','2016-11-29 15:19:03','1','2016-11-29 15:19:03',NULL,'0');
INSERT INTO sys_dict (id, type, name, name_en, value, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES(NULL,'sku_type','套餐','','12','1','1','1','2016-11-29 15:19:03','1','2016-11-29 15:19:03',NULL,'0');
INSERT INTO sys_dict (id, type, name, name_en, value, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES(NULL,'sku_type','运营商','','13','1','1','1','2016-11-29 15:19:03','1','2016-11-29 15:19:03',NULL,'0');
INSERT INTO sys_dict (id, type, name, name_en, value, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES(NULL,'sku_type','材质','','14','1','1','1','2016-11-29 15:19:03','1','2016-11-29 15:19:03',NULL,'0');
INSERT INTO sys_dict (id, type, name, name_en, value, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES(NULL,'sku_type','成份','','15','1','1','1','2016-11-29 15:19:03','1','2016-11-29 15:19:03',NULL,'0');
INSERT INTO sys_dict (id, type, name, name_en, value, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES(NULL,'sku_type','版本','','16','1','1','1','2016-11-29 15:19:03','1','2016-11-29 15:19:03',NULL,'0');
INSERT INTO sys_dict (id, type, name, name_en, value, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES(NULL,'sku_type','色号','','17','1','1','1','2016-11-29 15:19:03','1','2016-11-29 15:19:03',NULL,'0');
INSERT INTO sys_dict (id, type, name, name_en, value, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES(NULL,'sku_type','货号','','18','1','1','1','2016-11-29 15:19:03','1','2016-11-29 15:19:03',NULL,'0');
INSERT INTO sys_dict (id, type, name, name_en, value, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES(NULL,'sku_type','类别','','19','1','1','1','2016-11-29 15:19:03','1','2016-11-29 15:19:03',NULL,'0');
INSERT INTO sys_dict (id, type, name, name_en, value, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES(NULL,'sku_type','属性','','20','1','1','1','2016-11-29 15:19:03','1','2016-11-29 15:19:03',NULL,'0');
INSERT INTO sys_dict (id, type, name, name_en, value, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES(NULL,'sku_type','适用年龄','','21','1','1','1','2016-11-29 15:19:03','1','2016-11-29 15:19:03',NULL,'0');
INSERT INTO sys_dict (id, type, name, name_en, value, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES(NULL,'sku_type','重量','','22','1','1','1','2016-11-29 15:19:03','1','2016-11-29 15:19:03',NULL,'0');
INSERT INTO sys_dict (id, type, name, name_en, value, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES(NULL,'sku_type','适用人群','','23','1','1','1','2016-11-29 15:19:03','1','2016-11-29 15:19:03',NULL,'0');
INSERT INTO sys_dict (id, type, name, name_en, value, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES(NULL,'sku_type','组合','','24','1','1','1','2016-11-29 15:19:03','1','2016-11-29 15:19:03',NULL,'0');
INSERT INTO sys_dict (id, type, name, name_en, value, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES(NULL,'sku_type','品类','','25','1','1','1','2016-11-29 15:19:03','1','2016-11-29 15:19:03',NULL,'0');
INSERT INTO sys_dict (id, type, name, name_en, value, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES(NULL,'sku_type','度数','','26','1','1','1','2016-11-29 15:19:03','1','2016-11-29 15:19:03',NULL,'0');

##根据商品id和规格添加sku_attribute
INSERT INTO sku_attribute(product_id,attribute,VALUE,dict_value,STATUS,create_by,update_by) SELECT DISTINCT product_id,'类别',sku_color,19,1,1,1 FROM product_sku_link;
INSERT INTO sku_attribute(product_id,attribute,VALUE,dict_value,STATUS,create_by,update_by) SELECT DISTINCT product_id,'型号',sku_format,11,1,1,1 FROM product_sku_link;

##修改sku
UPDATE product_sku_link psl SET psl.sku_color_id = (SELECT id FROM sku_attribute sa WHERE sa.product_id = psl.product_id AND psl.sku_color = sa.value AND sa.dict_value = 19)
UPDATE product_sku_link psl SET psl.sku_format_id = (SELECT id FROM sku_attribute sa WHERE sa.product_id = psl.product_id AND psl.sku_format = sa.value AND sa.dict_value = 11)

##把老的sku设置为2种规格,设置color_value和format_value
UPDATE product SET color_value = 19,format_value=11,norm_type=2


##增加 活动表字段
alter table groupon_activity add rebate_ratio double(20,2) default 0.00 comment '返佣比例';
alter table groupon_activity add restriction_num int(2) default 0 comment '限购数量';

##增加 订单表字段
alter table user_order add rebate_price double(20,2) default '0.00' COMMENT '返佣金额';
alter table user_order add is_rebate int(2) default '0' COMMENT '是否返佣(1是0否)';
alter table user_order add rebate_time datetime default NULL COMMENT '返佣时间';
alter table user_order add pdk_uid bigint(20) default NULL COMMENT '拼得客user_id';

##增加 拼得客字段
alter table user_pindeke_info add freezing_price double(20,2) default '0.00' COMMENT '冻结金额';


##增加菜单
insert into sys_menu (id, name, name_en, level, path, icon, sorting, status, create_by, create_date, update_by, update_date, remarks, version) values('304','拼得客任务商品列表','','286','goPindekeTaskProductList.do',NULL,'0','1','1','2016-12-22 14:03:44','1','2016-12-22 14:03:44',NULL,'0');
insert into sys_role_menu (role_id, menu_id) values('1','304');

##增加-商品表字段
alter table product add limit_num int(11) default 0 COMMENT '商品数量';
alter table product add surplus_num int(11) default 0 COMMENT '剩余数量';

##增加-交易记录表
alter table user_deal_log add surplus_price double(20,2) default 0.0 COMMENT '剩余金额';
alter table user_deal_log add false_date DATETIME DEFAULT NULL COMMENT '审核不通过时间';

##sys_login表添加2字段
ALTER TABLE sys_login
ADD COLUMN balance  double(20,2) NULL DEFAULT 0.00 COMMENT '用户余额' AFTER is_pindeke,
ADD COLUMN total_balance  double(20,2) NULL DEFAULT 0.00 COMMENT '钱包总额' AFTER balance;

##新增user_redeem_code表
CREATE TABLE user_redeem_code (
  code varchar(20) NOT NULL default '' COMMENT '兑换码',
  price double(10,2) default '0.00' COMMENT '金额',
  valid_stime datetime default NULL COMMENT '有效期开始时间',
  valid_etime datetime default NULL COMMENT '有效期结束时间',
  status int(1) unsigned default '1' COMMENT '审核状态（0-未审核1-已审核）',
  gen_time datetime default NULL COMMENT '生成时间',
  user_id bigint(20) unsigned default '0' COMMENT '使用用户ID',
  used int(1) unsigned default '0' COMMENT '使用状态（0-未使用1-已使用）',
  use_time datetime default NULL COMMENT '使用时间',
  PRIMARY KEY  (code),
  KEY idx_user_id USING BTREE (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户兑换码表';

##添加菜单
INSERT INTO sys_menu (id, name, name_en, level, path, icon, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES (306, '兑换券管理', '', 305, 'goUserRedeemCode.do', null, 0, 1, 1, '2016-12-27 11:58:24', 1, '2016-12-27 11:58:24', null, 0);
INSERT INTO sys_menu (id, name, name_en, level, path, icon, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES (305, '钱包功能', '', 0, 'url', '/images/menu_icon_10.png', 13, 1, 1, '2016-12-27 11:52:20', 1, '2016-12-27 11:52:20', null, 0);

##添加角色菜单
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 306);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 305);


##增加-团记录表
alter table groupon_activity_record add pdk_uid bigint(20) COMMENT '拼得客uid';

##增加-sku_link
alter table product_sku_link add is_delete int(2) default '0' COMMENT '是否删除(1是0否)';
##增加-sku_att
ALTER TABLE sku_attribute ADD is_delete INT(2) DEFAULT '0' COMMENT '是否删除(1是0否)';

## 支付宝红包口令表
CREATE TABLE ali_red_envelope (
  id bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  invite_code varchar(30) NOT NULL COMMENT '邀请码',
  passwd_img1 varchar(50) NOT NULL COMMENT '红包口令图片1',
  passwd_img2 varchar(50) NOT NULL COMMENT '红包口令图片2',
  attend_id bigint(20) NOT NULL DEFAULT 0 COMMENT '参团id',
  header_id bigint(20) NOT NULL DEFAULT 0 COMMENT '团长用户id',
  versions int NOT NULL DEFAULT 0 COMMENT '版本更新',
  create_date datetime DEFAULT NULL COMMENT '生成时间',
  update_date datetime DEFAULT NULL COMMENT '更新时间',
  is_used int(2) default 0 comment '是否使用(1是0否)',
  order_id bigint(20) default 0 comment '订单id',
  PRIMARY KEY (id),
  KEY IDX_INVITE_CODE (invite_code),
  KEY IDX_ATTEND_ID (attend_id),
  KEY IDX_ORDER_ID (order_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付宝红包口令表';

alter table user_order add invite_code varchar(30) COMMENT '邀请码';



##增加菜单
insert into sys_menu (id, name, name_en, level, path, icon, sorting, status, create_by, create_date, update_by, update_date, remarks, version) values('307','红包口令管理','','0','url','/images/menu_icon_10.png','14','1','1','2017-01-07 08:46:01','1','2017-01-07 08:46:36',NULL,'0');
insert into sys_menu (id, name, name_en, level, path, icon, sorting, status, create_by, create_date, update_by, update_date, remarks, version) values('308','红包口令','','307','goAliRedEnvelope.do',NULL,'0','1','1','2017-01-07 08:49:56','1','2017-01-07 08:49:56',NULL,'0');
##添加菜单
INSERT INTO sys_menu (id, name, name_en, level, path, icon, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES (306, '兑换券管理', '', 305, 'goUserRedeemCode.do', null, 0, 1, 1, '2016-12-27 11:58:24', 1, '2016-12-27 11:58:24', null, 0);
INSERT INTO sys_menu (id, name, name_en, level, path, icon, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES (305, '钱包功能', '', 0, 'url', '/images/menu_icon_10.png', 13, 1, 1, '2016-12-27 11:52:20', 1, '2016-12-27 11:52:20', null, 0);

##添加角色菜单
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 306);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 305);

INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 307);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 308);


##增加-product
alter table product add faraway text COMMENT '偏远地区';
##增加-sys_area
ALTER TABLE sys_area ADD is_often INT(2) DEFAULT 0 COMMENT '是否常用地址';

##增加字段
ALTER TABLE user_order ADD is_handle INT(2) DEFAULT 0 COMMENT '是否待处理订单（0-否1-是）';
ALTER TABLE user_order ADD cs_remarks varchar(255) default '' COMMENT '客户订单留言';
alter table user_order ADD auto_rec_time datetime default NULL COMMENT '自动收货时间';

##添加菜单
INSERT INTO sys_menu (id, name, name_en, level, path, icon, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES (312, '订单查询', '', 17, 'goQueryOrder.do?b=1&os', null, 0, 1, 1, '2017-01-16 14:35:43', 1, '2017-01-16 14:52:20', null, 0);
INSERT INTO sys_menu (id, name, name_en, level, path, icon, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES (311, '待处理订单列表', '', 17, 'order.do?os=8', null, 1, 1, 1, '2017-01-16 14:36:53', 1, '2017-01-16 14:54:54', null, 0);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 311);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 312);


##新增拼得客当月销售额记录表
CREATE TABLE pindeke_month_sale (
  id bigint(20) NOT NULL auto_increment COMMENT 'id',
  user_id bigint(20) default '0' COMMENT '用户id',
  type int unsigned default '0' NOT NULL COMMENT '类型 :1为排行榜2为30天销售额',
  section_time varchar(20) default '' COMMENT '区间时间',
  total double(20,2) default 0.00 COMMENT '累计销售金额',
  is_settle int(2) default '0' COMMENT '是否已返佣奖励(1是0否)',
  settle_amt double(20,2) default 0.00 COMMENT '奖励金额',
  ranking int(2) default '0' COMMENT '排名',
  inviter_id bigint(20) default '0' COMMENT '邀请者id',
  create_by bigint(20) default '0' COMMENT '创建者',
  create_date datetime default NULL COMMENT '创建时间',
  update_by bigint(20) default '0' COMMENT '更新者',
  update_date datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (id),
  KEY IDX_USER_ID (user_id),
  INDEX IDX_INVITER_ID(inviter_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='拼得客当月销售额记录表';

INSERT INTO sys_menu (id, name, name_en, level, path, icon, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES (309, '拼得客排行榜', '', 286, 'goPindekeMonthSale.do', null, 5, 1, 1, '2017-01-11 16:18:28', 1, '2017-01-11 16:18:28', null, 0);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 309);

INSERT INTO sys_menu (id, name, name_en, level, path, icon, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES (310, '拼得客邀请用户列表', '', 286, 'goPindekeMonthSaleInviter.do', null, 6, 1, 1, '2017-01-12 11:32:42', 1, '2017-01-12 11:32:42', null, 0);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 310);



##增加拼得客编号字段
alter table user_pindeke_info add pindeke_number varchar(30) default NULL COMMENT '拼得客编号';

#user_pindeke_info新增invitation_code字段
ALTER TABLE user_pindeke_info
ADD COLUMN invitation_code  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分享邀请码' AFTER update_date;

##增加订单字段
alter table user_order add rebate_ratio double(20,2) default 0.00 COMMENT '返佣比例';

#################################### 商家中心功能 ##########################################
##增加-订单表
alter table user_order add seller_deduct double(20,2) default '0.00' COMMENT '商家扣款';
alter table user_order add platform_msg varchar(500) default '' COMMENT '平台留言';
alter table user_order add order_profit double(20,2) default '0.00' COMMENT '订单利润';
alter table user_order add seller_goods_price double(20,2) default '0.00' COMMENT '商家货款';

## 订单表增加 订单来源和平团是否成功状态
ALTER TABLE user_certificates_photo ADD image4_begin_date date default NULL COMMENT '身份证有效开始时间';
ALTER TABLE user_certificates_photo ADD image4_end_date date default NULL COMMENT '身份证有效结束时间';
ALTER TABLE user_certificates_photo ADD image2_no varchar(30) default '' COMMENT '组织机构代码号';
ALTER TABLE user_certificates_photo ADD image3_no varchar(30) default '' COMMENT '纳税人识别号';
ALTER TABLE user_certificates_photo ADD image5_no varchar(30) default '' COMMENT '统一社会信用代码';
ALTER TABLE user_certificates_photo ADD image2_begin_date date default NULL COMMENT '组织机构证有效开始时间';
ALTER TABLE user_certificates_photo ADD image2_end_date date default NULL COMMENT '组织机构证有效结束时间';
ALTER TABLE user_certificates_photo ADD qc_image1 varchar(100) default '' COMMENT '质检报告图片1';
ALTER TABLE user_certificates_photo ADD qc_image2 varchar(100) default '' COMMENT '质检报告图片2';
ALTER TABLE user_certificates_photo ADD qc_image3 varchar(100) default '' COMMENT '质检报告图片3';
ALTER TABLE user_certificates_photo ADD qc_image4 varchar(100) default '' COMMENT '质检报告图片4';
ALTER TABLE user_certificates_photo ADD qc_image5 varchar(100) default '' COMMENT '质检报告图片5';
ALTER TABLE user_certificates_photo ADD qc_image6 varchar(100) default '' COMMENT '质检报告图片6';
ALTER TABLE user_certificates_photo ADD bl_image1 varchar(100) default '' COMMENT '品牌授权证明图片1';
ALTER TABLE user_certificates_photo ADD bl_image2 varchar(100) default '' COMMENT '品牌授权证明图片2';
ALTER TABLE user_certificates_photo ADD bl_image3 varchar(100) default '' COMMENT '品牌授权证明图片3';
ALTER TABLE user_certificates_photo ADD bl_image4 varchar(100) default '' COMMENT '品牌授权证明图片4';
ALTER TABLE user_certificates_photo ADD bl_image5 varchar(100) default '' COMMENT '品牌授权证明图片5';
ALTER TABLE user_certificates_photo ADD bl_image6 varchar(100) default '' COMMENT '品牌授权证明图片6';
ALTER TABLE user_certificates_photo ADD bl_image7 varchar(100) default '' COMMENT '品牌授权证明图片7';
ALTER TABLE user_certificates_photo ADD bl_image8 varchar(100) default '' COMMENT '品牌授权证明图片8';
ALTER TABLE user_certificates_photo ADD bl_image9 varchar(100) default '' COMMENT '品牌授权证明图片9';
ALTER TABLE user_certificates_photo ADD bl_image10 varchar(100) default '' COMMENT '品牌授权证明图片10';
ALTER TABLE user_certificates_photo ADD image8 varchar(100) default '' COMMENT '开户许可证';

##新增商家银行账户表
CREATE TABLE seller_bank (
  id bigint(20) NOT NULL auto_increment COMMENT 'id',
  user_id bigint(20) default '0' COMMENT '用户id',
  bank_name varchar(100) default '' COMMENT '银行名',
  province int(11) default NULL COMMENT '省',
  city int(11) default NULL COMMENT '城市',
  area int(11) default NULL COMMENT '区域',
  bank_branch varchar(100) default '' COMMENT '开户支行',
  bank_card_no varchar(50) default '' COMMENT '银行卡号',
  user_name varchar(50) default '' COMMENT '开户人姓名',
  phone varchar(50) default '' COMMENT '手机号',
  status int(1) unsigned default '1' COMMENT '审核状态（0-未审核1-审核成功2-审核失败）',
  create_date datetime default NULL COMMENT '创建时间',
  create_by bigint(20) default '0' COMMENT '创建人',
  update_date datetime default NULL COMMENT '修改时间',
  update_by bigint(20) default '0' COMMENT '修改人',
  PRIMARY KEY  (`id`),
  KEY `IDX_USER_ID` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商家银行账户表';

##新增字段
ALTER TABLE user_manufacturer_withdraw add number varchar(30) default '' COMMENT '编号';
ALTER TABLE user_manufacturer_withdraw add withdrawal_fee double(20,2) default 0.00 COMMENT '提现手续费';
ALTER TABLE user_manufacturer_withdraw add bank_name varchar(100) default '' COMMENT '银行名';
ALTER TABLE user_manufacturer_withdraw add bank_card_no varchar(50) default '' COMMENT '银行卡号';
ALTER TABLE user_manufacturer_withdraw add user_name varchar(50) default '' COMMENT '开户人姓名';

##新增菜单
INSERT INTO sys_menu (id, name, name_en, level, path, icon, sorting, status, create_by, create_date, update_by, update_date, remarks, version) VALUES (312, '商家账户审核', '', 174, 'goSellerBank.do', null, 5, 1, 1, '2017-01-24 09:17:18', 1, '2017-01-24 09:44:44', null, 0);
