package com.holderzone.holder.saas.store.dto.constant.organization;

/**
 * @author Mr.Q
 * @date 2019/12/25 22:55
 * desc：
 */
public interface MqConstant {

    // 商户后台同步门店增、删、改到云端
    String MERCHANT_SYNC_ORGANIZATION_TOPIC = "merchant-organization-topic";

    String MERCHANT_SYNC_ORGANIZATION_TAG = "merchant-organization-tag";

    // 云端同步门店新增、修改到商户后台
    String CLOUD_SYNC_ORGANIZATION_GROUP = "merchant-organization-group";

    String CLOUD_SYNC_ORGANIZATION_TOPIC = "holder-saas-store-topic";

    String CLOUD_SYNC_ORGANIZATION_TAG = "holder-saas-store-tag";

    String DOWNSTREAM_WARE_HOUSE_TOPIC = "downstream-ware-house-topic";

    String DOWNSTREAM_WARE_HOUSE_CREATE_TAG = "downstream-ware-house-create-tag";

    String DOWNSTREAM_WARE_HOUSE_UPDATE_TAG = "downstream-ware-house-update-tag";

    String DOWNSTREAM_WARE_HOUSE_GROUP = "downstream-ware-house-group";

    String DOWNSTREAM_CONTEXT = "downstream-context";

    String DOWNSTREAM_STORE_TOPIC = "downstream-store-topic";

    String DOWNSTREAM_STORE_CREATE_TAG = "downstream-store-create-tag";

    String DOWNSTREAM_STORE_UPDATE_TAG = "downstream-store-update-tag";

    String DOWNSTREAM_DEVICE_TOPIC = "downstream-device-topic";

    String DOWNSTREAM_DEVICE_BIND_TAG = "downstream-device-bind-tag";

    String DOWNSTREAM_DEVICE_UNBIND_TAG = "downstream-device-unbind-tag";

    String DOWNSTREAM_BRAND_CREATE_TOPIC = "downstream-brand-create-topic";

    String DOWNSTREAM_BRAND_CREATE_TAG = "downstream-brand-create-tag";

    String CLOUD_SYNC_DEVICE_TOPIC = "holder-saas-device-topic";

    String DEVICE_UNBIND_DOWNLOAD_TAG = "device-unbind-download-tag";

    String DEVICE_UNBIND_UPLOAD_TAG = "device-unbind-upload-tag";

    String DEVICE_BIND_DOWNLOAD_TAG = "device-bind-download-tag";

    String DEVICE_BIND_UPLOAD_TAG = "device-bind-upload-tag";

    String DEVICE_UNBIND_GROUP = "device-unbind-download-group";

    String ENTERPRISE_CREATE_TOPIC = "enterprise-init-data-topic";

    String ENTERPRISE_CREATE_TAG = "enterprise-init-data-tag";

    String ENTERPRISE_CREATE_GROUP = "enterprise-init-data-store-group";
}

