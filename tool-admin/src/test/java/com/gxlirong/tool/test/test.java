package com.gxlirong.tool.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class test {

    /**
     * 遍历节点获得指定key值
     * 如果是列表,则用逗号隔开返回值
     *
     * @param node 节点
     * @param key  key
     * @return string
     */
    public String jsonLeaf(JsonNode node, String key) {
        //如果是值
        if (node.isValueNode() && key == null) {
            return node.toString();
        }
        //如果是对象
        if (node.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> it = node.fields();
            StringBuilder string = new StringBuilder();
            while (it.hasNext()) {
                Map.Entry<String, JsonNode> entry = it.next();
                if (entry.getKey().equals(key)) {
                    string.append(jsonLeaf(entry.getValue(), null));
                } else {
                    string.append(jsonLeaf(entry.getValue(), key));
                }
            }
            return string.toString().replaceAll("\"(\\w+)\"", "$1");
        }
        //如果是数组则输出
        if (node.isArray()) {
            StringBuilder string = new StringBuilder();
            for (JsonNode jsonNode : node) {
                String s = jsonLeaf(jsonNode, key);
                if (s != null && !s.equals("")) {
                    string.append(s).append(",");
                }
            }
            if (string.length() != 0) {
                return string.substring(0, string.length() - 1);
            }
        }
        return "";
    }

    //@Ignore
    @Test
    public void test() throws JsonProcessingException {
        String string = "[\n" +
                "\t{\n" +
                "\t  \"modid\": \"uncraftingtable\",\n" +
                "\t  \"name\": \"Uncrafting Table\",\n" +
                "\t  \"description\": \"�2Trade XP to recycle your old useless items into raw materials!\",\n" +
                "\t  \"version\": \"1.8-beta6\",\n" +
                "\t  \"mcversion\": \"1.12\",\n" +
                "\t  \"url\": \"https://minecraft.curseforge.com/projects/jglrxavpoks-uncrafting-table/\",\n" +
                "\t  \"authorList\": [\"ejglrxavpok\",\"aJordy141\",\"acrazysnailboy\"],\n" +
                "\t  \"credits\": \"\",\n" +
                "\t  \"logoFile\": \"/logo.png\",\n" +
                "\t  \"screenshots\": [],\n" +
                "\t  \"dependencies\": []\n" +
                "\t}\n" +
                "]\n";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode root = objectMapper.readTree(string);
        String authorList = this.jsonLeaf(root, "modid");
        System.out.println(authorList);
    }
}
