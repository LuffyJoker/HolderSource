package com.holderzone.holder.saas.store.item.constant;

import com.holderzone.framework.util.JacksonUtils;

/**
 * @author Mr.Q
 * @date 2019/12/29 21:35
 * desc：
 */
public class Constant {

    public static final Integer FALSE = 0;

    public static final Integer TRUE = 1;

    public static final String SAVE_BY_STORE = "（自建）";

    public static final String MSG_TO_ANDROID_SUBJECT = "商品服务消息";

    public static final String MSG_TO_ANDROID_CONTENT = JacksonUtils.writeValueAsString(1);

    public static final String STORE_CANNOT_BE_NULL = "门店不能为空";

    public static final String BRAND_CANNOT_BE_NULL = "品牌不能为空";

    public static final String WRONG_PARAMS = "参数错误";

    public static final String DEFAULT_GROUP = "默认分组";

    public static final String DEFAULT_NUM_CANNOT_GT_PICK_NUM = "分组下默认选择的商品规格数不能大于分组的可选数";

    public static final String PICK_NUM_GT_ACTUAL_NUM = "分组的可选商品数大于实际可选数";

    public static final String NO_TYPE_WITHIN_STORES = "当前门店列表下无商品分类数据";

    public static final String SYSTEM_ERROR = "系统异常";

    public static final String WRONG_ITEM_STRUCTURE = "商品结构异常";

    public static final String EMPTY = "";

    public static final String OP_FAIL ="操作失败";

    public static final String DUPLICATE_UPC ="商品条码已存在";

    public static final String DUPLICATE_CODE ="商品编号已存在";

    public static final String DUPLICATE_RETAIL_CODE ="商品货号已存在";

    public static final String DUPLICATE_ITEM_NAME ="该商品名称已存在";

    public static final String TIME_CONFLICT = "时间冲突";

}

