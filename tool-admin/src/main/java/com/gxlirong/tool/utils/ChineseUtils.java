package com.gxlirong.tool.utils;

import com.gxlirong.tool.domain.dto.ChineseTranslate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 汉化工具包
 *
 * @author lirong
 */
@Component
public class ChineseUtils {

    public ChineseTranslate getChineseString(String english) {
        return new RestTemplate().getForObject("http://fanyi.youdao.com/translate?&doctype=json&type=AUTO&i=" + english, ChineseTranslate.class);

    }

    /**
     * 转半角的函数(DBC case)
     * 任意字符串
     * 半角字符串
     * 全角空格为12288，半角空格为32
     * 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
     *
     * @param input 输入字符串
     * @return 字符串
     */
    public String toDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    /**
     * 大写字符串之前自动插入空格
     *
     * @param input 字符串
     * @return 字符串
     */
    public String stringInsertWhitespace(String input) {
        return input.replaceAll("([^_])([A-Z])", "$1 $2");
    }
}
