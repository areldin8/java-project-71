package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


public final class TestDiffer {
    private final ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());

    private TestDiffer() throws IOException {
    }

    @Test
    public void testSimpleAddition() {
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();

        map1.put("key1", "value1");
        map2.put("key1", "value1");
        map2.put("key2", "value2");

        List<Map<String, Object>> result = Comparator.compareFiles(map1, map2);

        assertEquals(2, result.size());

        Map<String, Object> addedItem =
                result.stream().filter(m -> m.get("status").equals("added")).findFirst().orElse(null);
        assertNotNull(addedItem);
        assertEquals("key2", addedItem.get("key"));
        assertEquals("value2", addedItem.get("newValue"));
    }

    @Test
    public void testSimpleRemoval() {
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();

        map1.put("key1", "value1");
        map1.put("key2", "value2");
        map2.put("key1", "value1");

        List<Map<String, Object>> result = Comparator.compareFiles(map1, map2);

        assertEquals(2, result.size());

        Map<String, Object> removedItem =
                result.stream().filter(m -> m.get("status").equals("removed")).findFirst().orElse(null);
        assertNotNull(removedItem);
        assertEquals("key2", removedItem.get("key"));
        assertEquals("value2", removedItem.get("oldValue"));
    }

    @Test
    public void testSimpleUpdate() {
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();

        map1.put("key1", "value1");
        map2.put("key1", "value2");

        List<Map<String, Object>> result = Comparator.compareFiles(map1, map2);

        assertEquals(1, result.size());

        Map<String, Object> updatedItem = result.getFirst();
        assertEquals("updated", updatedItem.get("status"));
        assertEquals("key1", updatedItem.get("key"));
        assertEquals("value1", updatedItem.get("oldValue"));
        assertEquals("value2", updatedItem.get("newValue"));
    }

    @Test
    public void testSimpleUnchanged() {
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();

        map1.put("key1", "value1");
        map1.put("key2", "value2");
        map2.put("key1", "value1");
        map2.put("key2", "value2");

        List<Map<String, Object>> result = Comparator.compareFiles(map1, map2);

        assertEquals(2, result.size());

        List<Map<String, Object>> unchangedItems =
                result.stream().filter(m -> m.get("status").equals("unchanged")).toList();
        assertEquals(unchangedItems.size(), 2);
        assertTrue(unchangedItems.stream()
                .anyMatch(m -> m.get("key").equals("key1") && m.get("oldValue").equals("value1")));
        assertTrue(unchangedItems.stream()
                .anyMatch(m -> m.get("key").equals("key2") && m.get("oldValue").equals("value2")));
    }

    @Test
    public void testKeyAdded() throws IOException {
        String yaml1 = "key1: value1\n";
        String yaml2 = "key1: value1\nkey2: value2\n";

        Map<String, Object> map1 = yamlMapper.readValue(yaml1, Map.class);
        Map<String, Object> map2 = yamlMapper.readValue(yaml2, Map.class);

        List<Map<String, Object>> result = Comparator.compareFiles(map1, map2);

        assertEquals(2, result.size());

        List<Map<String, Object>> addedItems = filterByStatus(result, "added");
        assertEquals(1, addedItems.size());
        assertEquals("key2", addedItems.getFirst().get("key"));
        assertEquals("value2", addedItems.getFirst().get("newValue"));
    }

    @Test
    public void testKeyRemoved() throws IOException {
        String yaml1 = "key1: value1\nkey2: value2\n";
        String yaml2 = "key1: value1\n";

        Map<String, Object> map1 = yamlMapper.readValue(yaml1, Map.class);
        Map<String, Object> map2 = yamlMapper.readValue(yaml2, Map.class);

        List<Map<String, Object>> result = Comparator.compareFiles(map1, map2);

        assertEquals(2, result.size());

        List<Map<String, Object>> removedItems = filterByStatus(result, "removed");
        assertEquals(1, removedItems.size());
        assertEquals("key2", removedItems.getFirst().get("key"));
        assertEquals("value2", removedItems.getFirst().get("oldValue"));
    }

    @Test
    public void testKeyUpdated() throws IOException {
        String yaml1 = "key1: value1\n";
        String yaml2 = "key1: value2\n";

        Map<String, Object> map1 = yamlMapper.readValue(yaml1, Map.class);
        Map<String, Object> map2 = yamlMapper.readValue(yaml2, Map.class);

        List<Map<String, Object>> result = Comparator.compareFiles(map1, map2);

        assertEquals(1, result.size());

        List<Map<String, Object>> updatedItems = filterByStatus(result, "updated");
        assertEquals(1, updatedItems.size());
        assertEquals("key1", updatedItems.getFirst().get("key"));
        assertEquals("value1", updatedItems.getFirst().get("oldValue"));
        assertEquals("value2", updatedItems.getFirst().get("newValue"));
    }

    @Test
    public void testKeyUnchanged() throws IOException {
        String yaml1 = "key1: value1\nkey2: value2\n";
        String yaml2 = "key1: value1\nkey2: value2\n";

        Map<String, Object> map1 = yamlMapper.readValue(yaml1, Map.class);
        Map<String, Object> map2 = yamlMapper.readValue(yaml2, Map.class);

        List<Map<String, Object>> result = Comparator.compareFiles(map1, map2);

        assertEquals(2, result.size());

        List<Map<String, Object>> unchangedItems = filterByStatus(result, "unchanged");
        assertEquals(2, unchangedItems.size());
    }

    private List<Map<String, Object>> filterByStatus(List<Map<String, Object>> items, String status) {
        return items.stream().filter(m -> m.get("status").equals(status)).collect(Collectors.toList());
    }
}
