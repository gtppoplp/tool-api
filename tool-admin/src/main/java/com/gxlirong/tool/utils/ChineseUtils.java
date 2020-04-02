package com.gxlirong.tool.utils;

import com.gxlirong.tool.common.api.ResultCode;
import com.gxlirong.tool.common.exception.OperationException;
import com.gxlirong.tool.domain.dto.ChineseTranslate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 汉化工具包
 *
 * @author lirong
 */
@Component
@Slf4j
public class ChineseUtils {
    @Autowired
    private HttpUtils httpUtils;

    private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";
    private static final String appid = "20200308000394809";
    private static final String securityKey = "U7NyBJbyqoQ6zpWZg2WK";
    private static final Integer millis = 50;//等待时间1秒(因为百度翻译api个人限制为1秒)
    private static final Integer max = 1000;//最大重试次数

    /**
     * 获得汉化内容
     *
     * @param english 英文字符串
     * @return 汉化响应实体
     */
    public ChineseTranslate getChineseString(String from, String to, String english) throws InterruptedException {
        if (english == null || english.isEmpty()) {
            return null;
        }
        Map<String, String> params = new HashMap<>();
        params.put("q", english);
        params.put("from", from);
        params.put("to", to);
        params.put("appid", appid);
        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);
        // 签名
        String src = appid + english + salt + securityKey; // 加密前的原文
        params.put("sign", DigestUtils.md5DigestAsHex(src.getBytes()));
        //设置url编码方式
        RestTemplate restTemplate = new RestTemplate();
        DefaultUriBuilderFactory uriFactory = new DefaultUriBuilderFactory();
        uriFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);
        restTemplate.setUriTemplateHandler(uriFactory);

        ChineseTranslate chineseTranslate = null;
        int max = ChineseUtils.max;
        String urlWithQueryString = "";
        do {
            Thread.sleep(millis);
            try {
                urlWithQueryString = httpUtils.getUrlWithQueryString(TRANS_API_HOST, params);
                chineseTranslate = restTemplate.getForObject(urlWithQueryString, ChineseTranslate.class);
            } catch (Exception e) {
                urlWithQueryString = ",error:" + e.getMessage();
            }
            if (max == 0) {
                throw new OperationException(ResultCode.MINECRAFT_MOD_CHINESE_MAX_RETRY, urlWithQueryString);
            }
            max--;
        } while (chineseTranslate == null || chineseTranslate.getFrom() == null);
        return chineseTranslate;
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
