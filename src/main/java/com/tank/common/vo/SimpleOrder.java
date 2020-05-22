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

  private int region_code;

  private String city_code;

  private int status;

  private int payment_status;

  private String order_status;

  private String process_status;

  private String pickup_code;

  private String ticket_no;

  private String ticket_created_at;

  private String delivery_at;

  private String delivery_address;

  private int is_erp_print_ticket;

  private int is_printed;

  private int need_print;

  private int is_enabled;

  private int is_repair;

  private int print_status;

  private String remark;

  private String exception_type;

  private String real_created_at;

  private String version;

  private String deleted_at;

  private String created_at;

  private String updated_at;

  private int record_status;

  private int is_xinxiang_member;

  private int dec_price;

  private String erp_new_trans_no;

  private int sale_type;

  private int temperature_zone;

  private String virtual_store_code;

  private String erp_warehouse_code;

  private String parent_channel_order_sn;

  private int set_type;

  private int delivery_cycle;

  private String origin_sn;

}
