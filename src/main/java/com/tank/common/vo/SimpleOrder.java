package com.tank.common.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author tank198435163.com
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false, of = {"id"})
public class SimpleOrder {

  private Long id;

  private Long source_id;

  private String order_sn;

  private String order_type;

  private String order_brand;

  private String order_take_type;

  private String channel_key;

  private String operator_status;

  private String channel_order_sn;

  private String channel_order_id;

  private String serial_no;

  private String store_code;

  private String store_name;

  private String warehouse_no;

  private String customer_account;

  private String customer_mobile;

  private Long region_code;

  private Long city_code;

  private Long status;

  private Long payment_status;

  private String order_status;

  private String process_status;

  private String pickup_code;

  private String ticket_no;

  private String ticket_created_at;

  private String delivery_at;

  private String delivery_address;

  private Long is_erp_print_ticket;

  private Long is_printed;

  private Long need_print;

  private Long is_enabled;

  private Long is_repair;

  private Long print_status;

  private String remark;

  private String exception_type;

  private String real_created_at;

  private String version;

  private String deleted_at;

  private String created_at;

  private String updated_at;

  private Long record_status;

  private Long is_xinxiang_member;

  private Long dec_price;

  private String erp_new_trans_no;

  private Long sale_type;

  private Long temperature_zone;

  private String virtual_store_code;

  private String erp_warehouse_code;

  private String parent_channel_order_sn;

  private Long set_type;

  private Long delivery_cycle;

  private String origin_sn;

}
