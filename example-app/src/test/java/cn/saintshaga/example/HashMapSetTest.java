package cn.saintshaga.example;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

import static junit.framework.Assert.assertNull;

/**
 * Created by huang on 19-3-28.
 */
public class HashMapSetTest {

    @Test
    public void hashSetCanHaveNullValue() {
        Set<String> set = Sets.newHashSet();
        set.add(null);
    }

    @Test
    public void linkedHashSetCanHaveNullValue() {
        Set<String> set = Sets.newLinkedHashSet();
        set.add(null);
    }

    @Test(expected = NullPointerException.class)
    public void treeHashSetCantHaveNullValue() {
        Set<String> set= Sets.newTreeSet();
        set.add(null);
    }

    @Test
    public void hashMapCanHaveNullKeyAndValue() {
        Map<String, String> map = Maps.newHashMap();
        map.put(null, null);
        map.get(null);
    }

    @Test
    public void linkedHashMapCanHaveNullKeyAndValue() {
        Map<String, String> map = Maps.newLinkedHashMap();
        map.put(null, null);
        map.get(null);
    }

    @Test(expected = NullPointerException.class)
    public void treeMapCantHaveNullKey() {
        Map<String, String> map = Maps.newTreeMap();
        map.put(null, "asdf");
    }

    @Test
    public void treeMapCanHaveNullValue() {
        Map<String, String> map = Maps.newTreeMap();
        map.put("asdf", null);
        assertNull(map.get("asdf"));
    }

    @Test(expected = NullPointerException.class)
    public void concurrentHashMapCantHaveNullKey() {
        Map<String, String> map = Maps.newConcurrentMap();
        map.put(null, "asdf");
    }

    @Test(expected = NullPointerException.class)
    public void concurrentHashMapCantHaveNullValue() {
        Map<String, String> map = Maps.newConcurrentMap();
        map.put("asdf", null);
    }
}
