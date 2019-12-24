package com.holderzone.saas.store.organization.service;

import java.util.List;

/**
 * @author Mr.Q
 * @date 2019/12/25 0:31
 * desc：
 */
public interface DistributedIdService {

    Long rawId(String tag);

    String nextId(String tag);

    List<String> nextBatchId(String tag, long count);

    String nextOrganizationGuid();

    List<String> nextBatchPointItemGuid(long count);

    String nextStoreBrandGuid();

    List<String> nextBatchDstAreaGuid(long count);

    List<String> nextBatchDstItemGuid(long count);

    String nextPrintRecordGuid();

    List<String> nextBatchPrintRecordGuid(long count);

    String nextBrandGuid();

    List<String> nextBatchKitchenItemGuid(long count);

    List<String> nextBatchKitchenAttrGuid(long count);
}
