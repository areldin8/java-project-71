package hexlet.code;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Comparator {
    private static final String STATUS_REMOVED = "removed";
    private static final String STATUS_ADDED = "added";
    private static final String STATUS_UPDATED = "updated";
    private static final String STATUS_UNCHANGED = "unchanged";

    public static List<Map<String, Object>> compareFiles(Map<String, Object> map1, Map<String, Object> map2) {
        List<Map<String, Object>> result = new ArrayList<>();
        Set<String> keysSet = new TreeSet<>(map1.keySet());
        keysSet.addAll(map2.keySet());

        for (String key : keysSet) {
            Map<String, Object> map = new LinkedHashMap<>();
            if (map1.containsKey(key) && !map2.containsKey(key)) {
                map.put("key", key);
                map.put("oldValue", map1.get(key));
                map.put("status", STATUS_REMOVED);
            } else if (!map1.containsKey(key) && map2.containsKey(key)) {
                map.put("key", key);
                map.put("newValue", map2.get(key));
                map.put("status", STATUS_ADDED);
            } else if (map1.containsKey(key) && map2.containsKey(key)) {
                Object value1 = map1.get(key);
                Object value2 = map2.get(key);
                if (!Objects.equals(value1, value2)) {
                    map.put("key", key);
                    map.put("oldValue", value1);
                    map.put("newValue", value2);
                    map.put("status", STATUS_UPDATED);
                } else {
                    map.put("key", key);
                    map.put("oldValue", value1);
                    map.put("status", STATUS_UNCHANGED);
                }
            }
            result.add(map);
        }
        return result;
    }
}


