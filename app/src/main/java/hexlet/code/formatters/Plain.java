package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Plain {
    public static String casePlain(List<Map<String, Object>> differences) {
        StringBuilder result = new StringBuilder();
        for (Map<String, Object> diffs : differences) {
            String key = diffs.get("key").toString();
            String status = diffs.get("status").toString();
            switch (status) {
                case ConstantsFormat.STATUS_REMOVED ->
                        result.append(String.format("Property '%s' was removed%n", key));
                case ConstantsFormat.STATUS_ADDED ->
                        result.append(String.format("Property %s was added with value: %s%n",
                        complexValue(key), complexValue(diffs.get("newValue"))));
                case ConstantsFormat.STATUS_UPDATED ->
                        result.append(String.format("Property %s was updated. From %s to %s%n",
                        complexValue(key), complexValue(diffs.get("oldValue")), complexValue(diffs.get("newValue"))));
                default -> result.append("");
            }
        }
        return result.toString().trim();

    }

    public static String complexValue(Object data) {
        if ((data instanceof Map) || (data instanceof ArrayList<?>)) {
            return "[complex value]";
        } else if (data instanceof String) {
            return "'" + data + "'";
        } else if (data == null) {
            return null;
        }
        return data.toString();
    }
}

