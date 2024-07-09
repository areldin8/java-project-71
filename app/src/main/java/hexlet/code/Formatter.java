package hexlet.code;

import java.util.List;
import java.util.Map;

import static hexlet.code.formatters.Json.caseJson;
import static hexlet.code.formatters.Plain.casePlain;
import static hexlet.code.formatters.Stylish.caseStylish;

public class Formatter {

    public static String formatStyle(
            List<Map<String, Object>> differences, String format) throws Exception {
        switch (format) {
            case "stylish":
                return caseStylish(differences);
            case "plain":
                return casePlain(differences);
            case "json":
                return caseJson(differences);
            default:
                throw new IllegalArgumentException("Format " + format + " is not correct.");
        }
    }
}
