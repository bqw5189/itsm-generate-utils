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
        tableAndDescs.put("t_warehouse","仓库信息表");
//
//        tableAndDescs.put("t_pet", "宠物");
//        tableAndDescs.put("t_pet_small_race", "宠物品种");
//        tableAndDescs.put("t_gest", "会员");
//        tableAndDescs.put("t_user_dict_detail", "数据字典 字典明细项");
//        tableAndDescs.put("t_app_config", "应用配置");
//        tableAndDescs.put("t_check_process_sheet", "检查处理单据");
//        tableAndDescs.put("t_approve_status_entity", "审批状态实体");
//
//
//
//        tableAndDescs.put("t_warehouse_outrecord_detail", "出库记录明细");
//        tableAndDescs.put("t_warehouse_outrecord", "出库记录");
//        tableAndDescs.put("t_warehouse_moverecord_detail", "移库记录明细");
//        tableAndDescs.put("t_warehouse_moverecord", "移库记录");
//        tableAndDescs.put("t_warehouse_inventory", "仓库存货清单");
//        tableAndDescs.put("t_warehouse_inrecord_detail", "进库记录明细");
//        tableAndDescs.put("t_warehouse_inrecord", "进库记录");
//        tableAndDescs.put("t_warehouse_backrecord_detail", "退回记录明细");
//        tableAndDescs.put("t_warehouse_backrecord", "退回记录");
//        tableAndDescs.put("t_vip_input_tem", "VIP信息");
//        tableAndDescs.put("t_store_direct_sell_detail", "仓库直销明细");
//        tableAndDescs.put("t_store_direct_sell", "仓库直销主表");
//        tableAndDescs.put("t_service_detail", "服务明细");
//        tableAndDescs.put("t_service", "服务主表");
//        tableAndDescs.put("t_serial_number", "序列号");
//        tableAndDescs.put("t_reward_point_log", "序列号");
//        tableAndDescs.put("t_rewardpoint_exchange_detail", "赔偿交换细节");
//        tableAndDescs.put("t_rewardpoint_exchange", "赔偿交换");
//        tableAndDescs.put("t_return_commodity_detail", "返回商品明细");
//        tableAndDescs.put("t_return_commodity", "返回商品主表");
//        tableAndDescs.put("t_prompt", "急诊表");
//        tableAndDescs.put("t_prepay_money", "提前付费表");
//        tableAndDescs.put("t_phone_message", "电话信息表");
//        tableAndDescs.put("t_micro_sms_config", "消息提醒配置表");
//        tableAndDescs.put("t_medic_vaccine", "育苗表");
//        tableAndDescs.put("t_medic_supplies", "医生供应量表");
//        tableAndDescs.put("t_medic_register_record", "医生登记记录明细");
//        tableAndDescs.put("t_medic_prescription_detail", "医生处方明细");
//        tableAndDescs.put("t_medic_prescription", "医生处方");
//        tableAndDescs.put("t_base_medic_prescription_detail", "处方明细");
//        tableAndDescs.put("t_base_medic_prescription", "处方");
//        tableAndDescs.put("t_in_hospital_prescription_detail", "住院处方明细");
//        tableAndDescs.put("t_in_hospital_prescription", "住院处方");
//        tableAndDescs.put("t_medic_medictreat_record", "医生处理记录");
//        tableAndDescs.put("t_medic_chemical_examtype_detail", "医生化验实例类型细节表");
//        tableAndDescs.put("t_medic_chemical_examtype", "医生化验实例类型细节表");
//
//        tableAndDescs.put("t_medic_appliance", "医生器具表");
//        tableAndDescs.put("t_itemtype_supplies", "供应量表");
//        tableAndDescs.put("t_itemtype_chemicalexam", "类型化验实例表");
//        tableAndDescs.put("t_item_single_detail", "类型化验实例明细表");
//        tableAndDescs.put("t_item_count_change_reason", "种类数量更改原因表");
//        tableAndDescs.put("t_item_count", "种类数量更改原因表");
//        tableAndDescs.put("t_inventory_log", "存货清单日志表");
//        tableAndDescs.put("t_input_money_record", "收款记录表");
//        tableAndDescs.put("t_in_hospital_record_detail", "医院记录明细表");
//        tableAndDescs.put("t_in_hospital_record", "医院记录表");
//        tableAndDescs.put("t_gest_paid_record", "顾客影像记录表");
//        tableAndDescs.put("t_foster_record_detail", "养育记录明细表");
//        tableAndDescs.put("t_foster_record", "养育记录主表");
//        tableAndDescs.put("t_foster_health", "养育健康状况表");
//        tableAndDescs.put("t_finance_settle_accounts_detail", "资金账户明细表");
//        tableAndDescs.put("t_finance_settle_accounts", "资金账号表");
//        tableAndDescs.put("t_fifo_log", "进库出库日志");
//        tableAndDescs.put("t_expenses_expend", "付出费用表");
//        tableAndDescs.put("t_enterprise", "企业信息表");
//        tableAndDescs.put("t_check_warehouse_detail", "检查仓库明细表");
//        tableAndDescs.put("t_check_warehouse", "仓库检查主表");
//
//         tableAndDescs.put("t_base_medic_prescription" , "处方");
//         tableAndDescs.put("t_base_medic_prescription_detail" , "处方明细");
//         tableAndDescs.put("t_busines_cate" , "业务类型表");
//         tableAndDescs.put("t_chemical_exam_cate" , "化验样例类型");
//
//         tableAndDescs.put("t_dealer" , "代理商");
//         tableAndDescs.put("t_dict_type" , "字典类型");
//         tableAndDescs.put("t_dict_type_detail" , "字典类型明细");
//         tableAndDescs.put("t_expenses_cate" , "费用类型");
//         tableAndDescs.put("t_gest_level" , "会员等级管理");
//         tableAndDescs.put("t_images" , "图片信息");
//         tableAndDescs.put("t_item_cate" , "商品服务种类");
//         tableAndDescs.put("t_item_discount_rate" , "业务类型打折率");
//         tableAndDescs.put("t_item_type" , "商品类型");
//         tableAndDescs.put("t_medic_chemical_exam_detail" , "化验单明细");
//         tableAndDescs.put("t_medic_chemicalexam" , "化验单明细");
//         tableAndDescs.put("t_medic_vedio_exam" , "医生影像样例");
////         tableAndDescs.put("t_member_level" , "仓库检查主表");
////         tableAndDescs.put("t_menu_button" , "仓库检查主表");
////         tableAndDescs.put("t_menu_fun_module" , "仓库检查主表");
//         tableAndDescs.put("t_persons" , "宠物主人信息");
//         tableAndDescs.put("t_persons_appointment" , "宠物主人");
//         tableAndDescs.put("t_persons_cm_app" , "宠物主人");
//         tableAndDescs.put("t_pet_race" , "宠物种类");
//         tableAndDescs.put("t_prescription_template" , "处方模版");
//         tableAndDescs.put("t_prescription_template_detail" , "处方模版明细");
//         tableAndDescs.put("t_prescription_template_type" , "处方模版类型");
//         tableAndDescs.put("t_trprescription_template" , "药方模版");
//         tableAndDescs.put("t_trprescription_template_detail" , "药方模版明细");
//         tableAndDescs.put("t_trprescription_template_type" , "药方模版类型");
//         tableAndDescs.put("t_user_dict" , "用户字典");

        return tableAndDescs;
    }

    public JdbcUtils getJdbcUtils() {
        return new MysqlJdbcUtils();
    }
}
