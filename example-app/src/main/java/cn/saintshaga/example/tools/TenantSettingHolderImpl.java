package cn.saintshaga.example.tools;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by saintshaga on 2019/6/11.
 */
public class TenantSettingHolderImpl implements TenantSettingHolder {
    private ConcurrentHashMap<String, TenantSetting> map = new ConcurrentHashMap<>();

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    @Autowired
    private TenantSettingDao dao;

    @Override
    public TenantSetting get(@NonNull String tenantId) {
        lock.readLock().lock();
        try {
            return map.computeIfAbsent(tenantId, key -> dao.get(key));
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void clear() {
        lock.writeLock().lock();
        try {
            map.clear();
        } finally {
            lock.writeLock().unlock();
        }
    }


}
