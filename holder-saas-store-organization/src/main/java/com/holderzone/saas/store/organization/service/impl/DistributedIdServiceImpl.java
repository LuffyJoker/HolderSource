package com.holderzone.saas.store.organization.service.impl;

import com.holderzone.framework.exception.BusinessException;
import com.holderzone.saas.store.organization.service.DistributedIdService;
import com.holderzone.sdk.util.BatchIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mr.Q
 * @date 2019/12/25 0:31
 * desc：
 */
@Slf4j
@Service
public class DistributedIdServiceImpl implements DistributedIdService {

    private static final String TAG_ORGANIZATION = "organization";

    private static final String TAG_POINT_ITEM = "kds/point_item";

    private static final String TAG_DST_AREA = "kds/dst_area";

    private static final String TAG_DST_ITEM = "kds/dst_item";

    private static final String TAG_KITCHEN_ITEM = "kds/kitchen_item";

    private static final String TAG_KITCHEN_ATTR = "kds/kitchen_attr";

    private static final String TAG_STORE_BRAND = "store_brand";

    private static final String TAG_PRINT_PRINT_RECORD = "kds/print_record";

    private final RedisTemplate redisTemplate;

    @Autowired
    public DistributedIdServiceImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Long rawId(String tag) {
        try {
            return BatchIdGenerator.getGuid(redisTemplate, tag);
        } catch (IOException e) {
            throw new BusinessException("生成Guid失败：" + e.getMessage());
        }
    }

    @Override
    public String nextId(String tag) {
        return String.valueOf(rawId(tag));
    }

    @Override
    public List<String> nextBatchId(String tag, long count) {
        return BatchIdGenerator.batchGetGuids(redisTemplate, tag, count)
                .stream().map(String::valueOf).collect(Collectors.toList());
    }

    @Override
    public String nextOrganizationGuid() {
        return nextId(TAG_ORGANIZATION);
    }

    @Override
    public List<String> nextBatchPointItemGuid(long count) {
        return nextBatchId(TAG_POINT_ITEM, count);
    }

    @Override
    public String nextStoreBrandGuid() {
        return nextId(TAG_STORE_BRAND);
    }

    @Override
    public List<String> nextBatchDstAreaGuid(long count) {
        return nextBatchId(TAG_DST_AREA, count);
    }

    @Override
    public List<String> nextBatchDstItemGuid(long count) {
        return nextBatchId(TAG_DST_ITEM, count);
    }

    @Override
    public String nextPrintRecordGuid() {
        return nextId(TAG_PRINT_PRINT_RECORD);
    }

    @Override
    public List<String> nextBatchPrintRecordGuid(long count) {
        return nextBatchId(TAG_PRINT_PRINT_RECORD, count);
    }

    @Override
    public String nextBrandGuid() {
        return nextId(TAG_KITCHEN_ITEM);
    }

    @Override
    public List<String> nextBatchKitchenItemGuid(long count) {
        return nextBatchId(TAG_KITCHEN_ITEM, count);
    }

    @Override
    public List<String> nextBatchKitchenAttrGuid(long count) {
        return nextBatchId(TAG_KITCHEN_ATTR, count);
    }
}

