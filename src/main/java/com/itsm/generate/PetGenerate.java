package com.itsm.generate;

import com.itsm.jdbc.JdbcUtils;
import com.itsm.jdbc.MysqlJdbcUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tom on 16/7/13.
 */
public class PetGenerate extends AbstractGenerate{
    public String getOutPath() {
        return "/work/001_code/github/java/pet-hospital/fionapet-business/";
    }

    public String getPackagePath() {
        return "/com/fionapet/business/";
    }

    public String getPackage() {
        return "com.fionapet.business.";
    }

    public String getArtifactId() {
        return "fionapet-business-";
    }

    public Map<String, String> tableAndDescs() {
        Map<String, String> tableAndDescs = new HashMap<String, String>();
        tableAndDescs.put("t_app_config", "应用配置");
        tableAndDescs.put("t_check_process_sheet", "检查处理单据");
        tableAndDescs.put("t_approve_status_entity", "审批状态实体");
        tableAndDescs.put("t_warehouse_outrecord_detail", "出库记录明细");
        tableAndDescs.put("t_warehouse_outrecord", "出库记录");
        tableAndDescs.put("t_warehouse_moverecord_detail", "移库记录明细");
        tableAndDescs.put("t_warehouse_moverecord", "移库记录");
        tableAndDescs.put("t_warehouse_inventory", "仓库存货清单");
        tableAndDescs.put("t_warehouse_inrecord_detail", "进库记录明细");
        tableAndDescs.put("t_warehouse_inrecord", "进库记录");
        tableAndDescs.put("t_warehouse_backrecord_detail", "退回记录明细");
        tableAndDescs.put("t_warehouse_backrecord", "退回记录");
        tableAndDescs.put("t_vip_input_tem", "VIP信息");
        tableAndDescs.put("t_store_direct_sell_detail", "仓库直销明细");
        tableAndDescs.put("t_store_direct_sell", "仓库直销主表");
        tableAndDescs.put("t_service_detail", "服务明细");
        tableAndDescs.put("t_service", "服务主表");
        tableAndDescs.put("t_serial_number", "序列号");
        tableAndDescs.put("t_reward_point_log", "序列号");
        tableAndDescs.put("t_rewardpoint_exchange_detail", "赔偿交换细节");
        tableAndDescs.put("t_rewardpoint_exchange", "赔偿交换");
        tableAndDescs.put("t_return_commodity_detail", "返回商品明细");
        tableAndDescs.put("t_return_commodity", "返回商品主表");
        tableAndDescs.put("t_prompt", "急诊表");
        tableAndDescs.put("t_prepay_money", "提前付费表");
        tableAndDescs.put("t_phone_message", "电话信息表");
        tableAndDescs.put("t_micro_sms_config", "消息提醒配置表");
        tableAndDescs.put("t_medic_vaccine", "育苗表");
        tableAndDescs.put("t_medic_supplies", "医生供应量表");
        tableAndDescs.put("t_medic_register_record", "医生登记记录明细");
        tableAndDescs.put("t_medic_prescription_detail", "医生处方明细");
        tableAndDescs.put("t_medic_prescription", "医生处方明细");
        tableAndDescs.put("t_medic_medictreat_record", "医生处理记录");
        tableAndDescs.put("t_medic_chemical_examtype_detail", "医生化验实例类型细节表");
        tableAndDescs.put("t_medic_chemical_examtype", "医生化验实例类型细节表");
        return tableAndDescs;
    }

    public JdbcUtils getJdbcUtils() {
        return new MysqlJdbcUtils();
    }
}
