package cn.saintshaga.text.service;

/**
 * Created by huang on 18-12-26.
 */
public class DBTextService implements TextService {
    private TextCacheService service;

    public DBTextService() {}

    public DBTextService(TextCacheService service) {
        this.service = service;
    }

    @Override
    public String name() {
        if(service == null) {
            return "db";
        }
        return "db-" + service.cache();
    }
}
