package cn.saintshaga.example.tools;

/**
 * Created by saintshaga on 2019/6/11.
 */
public interface TenantSettingHolder {
    TenantSetting get(String tenantId);
    void clear();
}
