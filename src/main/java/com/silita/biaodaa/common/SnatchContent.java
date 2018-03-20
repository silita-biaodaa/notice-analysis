package com.silita.biaodaa.common;

import java.util.Set;

public class SnatchContent {

    public static Set<String> APTITUDE_SET = null;

    public static final String ZF_CG="zf";//政府采购网站标识

    public static final String CAI_GOU_TYPE = "0";  // 采购类型

    public static final String PU_TONG_TYPE = "1";  // 普通类型


    public static final String OTHER_TYPE="0"; // 其他公告类型

    public static final String ZHAO_BIAO_TYPE="1"; // 招标类型

    public static final String ZHONG_BIAO_TYPE="2"; // 中标类型

    public static final String DL_ZHAO_BIAO_TYPE = "4"; // 代理招标类型

    public static final String DL_ZHONG_BIAO_TYPE = "5"; // 代理中标类型

    public static final String PRE_QUALIFICATION = "6"; // 资格预审公告

   /*
      目前249中snatchurl的otherType字段值为：
      1：补充  2：答疑  3：流标  4：澄清
      5：延期  6：更正  7：废标  8：终止

      11-18 对应 snatchurl 的 otherType 1-8
    */


    public static final String BU_CHONG_TYPE = "11"; // 补充公告类型

    public static final String DA_YI_TYPE = "12"; // 答疑类型

    public static final String LIU_BIAO_TYPE = "13"; // 流标类型

    public static final String CHENG_QING_TYPE = "14"; // 澄清类型

    public static final String YAN_QI_TYPE = "15"; // 延期公告类型

    public static final String GENG_ZHENG_TYPE = "16"; // 更正/变更 公告类型

    public static final String FEI_BIAO_TYPE = "17"; // 废标类型

    public static final String ZHONG_ZHI_TYPE = "18"; // 终止公告

    public static final String XIU_GAI_TYPE = "19"; // 修改公告

    public static final String KONG_ZHI_JIA_TYPE = "20"; // 招标控制价类型

    public static final String ZI_SHENG_JIE_GUO_TYPE = "21"; //资审结果

    public static final String ZI_GE_YU_SHEN_TYPE = "22"; //资格预审

    public static final String RU_WEI_TYPE = "23";  // 入围公示

    public static final String ZAN_TING_TYPE = "24"; // 暂停公告




    public static final String ZHONG_BIAO_BU_CHONG_TYPE = "51";  // 中标补充/更改

    public static final String HE_TONG_TYPE = "52"; //合同公告







   public static final String PROVINCE = "0";  // 省

   public static final String CITY = "1";  // 市

   public static final String COUNTY = "2";  // 县


}
