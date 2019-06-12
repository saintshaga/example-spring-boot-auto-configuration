package cn.saintshaga.example.tools;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by saintshaga on 2019/6/11.
 */
public class TenantSettingHolderImpl implements TenantSettingHolder {
    private ConcurrentHashMap<String, TenantSetting> map = new ConcurrentHashMap<>();

    @Autowired
    private TenantSettingDao dao;

    @Override
    public TenantSetting get(@NonNull String tenantId) {
        return map.computeIfAbsent(tenantId, key -> dao.get(key));
    }

    @Override
    public void clear() {
        map.clear();
    }


}
