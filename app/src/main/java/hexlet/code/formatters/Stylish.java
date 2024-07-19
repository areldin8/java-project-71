package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {

    public static String caseStylish(List<Map<String, Object>> differences) {
        StringBuilder result = new StringBuilder("{\n");
        for (Map<String, Object> diffs : differences) {
            switch (diffs.get("status").toString()) {
                case constants.STATUS_REMOVED -> result.append("  - ").append(diffs.get("key")).append(": ")
                        .append(diffs.get("oldValue")).append("\n");
                case constants.STATUS_ADDED -> result.append("  + ").append(diffs.get("key")).append(": ")
                        .append(diffs.get("newValue")).append("\n");
                case constants.STATUS_UNCHANGED -> result.append("    ").append(diffs.get("key")).append(": ")
                        .append(diffs.get("oldValue")).append("\n");
                default -> {
                    result.append("  - ").append(diffs.get("key")).append(": ")
                            .append(diffs.get("oldValue")).append("\n");
                    result.append("  + ").append(diffs.get("key")).append(": ")
                            .append(diffs.get("newValue")).append("\n");
                }
            }
        }
        result.append("}");
        return result.toString();
    }
}
