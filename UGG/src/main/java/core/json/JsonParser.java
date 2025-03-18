package core.json;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class JsonParser {
    public static class JsonDoc {
        private final ImmutableList<String> pattern;
        private final ImmutableMap<String, String> patternMap;

        private JsonDoc(List<String> pattern, Map<String, String> patternMap) {
            this.pattern = ImmutableList.copyOf(pattern);
            this.patternMap = ImmutableMap.copyOf(patternMap);
        }

        public int rows() {
            return pattern.size();
        }
        public int columns() {
            return pattern.getFirst().length();
        }
        public char fromPattern(int row, int col) {
            return pattern.get(row).charAt(col);
        }
        public String fromMap(char key) {
            return patternMap.get(String.valueOf(key));
        }
    }

    public static JsonDoc parseFile(URL jsonFileURL) throws IOException {
        Object parsedJson = convertToDocument(jsonFileURL);

        return new JsonDoc(
                JsonPath.read(parsedJson, "$.pattern"),
                JsonPath.read(parsedJson, "$.pattern-map")
        );
    }

    private static Object convertToDocument(URL jsonFileURL) throws IOException {
        String json = readFileToString(jsonFileURL);
        return convertJsonStringToDocument(json);
    }
    private static String readFileToString(URL jsonFileURL) throws IOException {
        return IOUtils.toString(jsonFileURL, StandardCharsets.UTF_8);
    }
    private static Object convertJsonStringToDocument(String json) {
        return Configuration.defaultConfiguration().jsonProvider().parse(json);
    }
}
