package com.gxlirong.tool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxlirong.tool.domain.dto.ChineseTranslate;
import com.gxlirong.tool.entity.ToolMinecraftModLang;
import com.gxlirong.tool.mapper.ToolMinecraftModLangMapper;
import com.gxlirong.tool.service.ToolMinecraftModLangService;
import com.gxlirong.tool.utils.ChineseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * 我的世界模组表 服务实现类
 * </p>
 *
 * @author lirong
 * @since 2020-03-11
 */
@Service
@Slf4j
public class ToolMinecraftModLangServiceImpl extends ServiceImpl<ToolMinecraftModLangMapper, ToolMinecraftModLang> implements ToolMinecraftModLangService {
    @Autowired
    private ChineseUtils chineseUtils;

    @Override
    public List<ToolMinecraftModLang> getNotChineseList(Long id) {
        return this.list(
                new QueryWrapper<ToolMinecraftModLang>()
                        .eq("mod_id", id)
                        .eq("is_deleted", false)
                        .eq("is_chinese", false)
        );
    }

    @Override
    public List<ToolMinecraftModLang> getChineseList(Long id) {
        return this.list(
                new QueryWrapper<ToolMinecraftModLang>()
                        .eq("mod_id", id)
                        .eq("is_deleted", false)
                        .eq("is_chinese", true)
        );
    }

    @Override
    public boolean createBath(Long id, List<String> enUSStringList, List<String> zhCNStringList) {
        List<ToolMinecraftModLang> toolMinecraftModLangList = this.list(
                new QueryWrapper<ToolMinecraftModLang>()
                        .eq("mod_id", id)
                        .eq("is_deleted", false)
        );

        ArrayList<ToolMinecraftModLang> minecraftModLangList = new ArrayList<>();
        for (String zhCNString : zhCNStringList) {
            if (zhCNString.contains("=")) {
                ToolMinecraftModLang minecraftModLang = new ToolMinecraftModLang();
                minecraftModLang.setModId(id);
                minecraftModLang.setField(zhCNString.substring(0, zhCNString.lastIndexOf("=")));
                for (String enUSString : enUSStringList) {
                    if (enUSString.contains("=") &&
                            enUSString.substring(0, enUSString.lastIndexOf("="))
                                    .equals(
                                            zhCNString.substring(0, zhCNString.lastIndexOf("="))
                                    )
                    )
                        minecraftModLang.setEnLang(enUSString.substring(enUSString.lastIndexOf("=") + 1));
                }
                minecraftModLang.setLang(zhCNString.substring(zhCNString.lastIndexOf("=") + 1));
                minecraftModLang.setIsChinese(false);
                minecraftModLangList.add(minecraftModLang);
            }
        }
        //清除已经保存的数据
        if (toolMinecraftModLangList != null) {
            for (ToolMinecraftModLang toolMinecraftModLang : toolMinecraftModLangList) {
                minecraftModLangList.removeIf(x -> x.getField().equals(toolMinecraftModLang.getField()));
            }
        }
        return this.saveBatch(minecraftModLangList);
    }

    @Override
    public boolean updateBath(List<ToolMinecraftModLang> langList) {
        return this.updateBatchById(langList);
    }

    @Override
    public void chineseLangList(List<ToolMinecraftModLang> langList) throws InterruptedException {
        if (langList != null) {
            for (ToolMinecraftModLang lang : langList) {
                //开始翻译
                ChineseTranslate chineseString = chineseUtils.getChineseString(chineseUtils.stringInsertWhitespace(lang.getLang()));
                String translationString = chineseString.getTrans_result().get(0).getDst();
                //去空格
                translationString = translationString.replace(" ", "");
                //标点符号转小写
                translationString = chineseUtils.toDBC(translationString);
                lang.setLang(translationString);
                lang.setIsChinese(true);
                log.info("{}", translationString);
            }
        }
    }
}
