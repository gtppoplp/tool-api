package com.gxlirong.tool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxlirong.tool.common.api.ResultCode;
import com.gxlirong.tool.common.exception.OperationException;
import com.gxlirong.tool.domain.dto.ChineseTranslate;
import com.gxlirong.tool.domain.dto.ToolMinecraftModLangPostParam;
import com.gxlirong.tool.entity.ToolMinecraftModLang;
import com.gxlirong.tool.mapper.ToolMinecraftModLangMapper;
import com.gxlirong.tool.service.ToolMinecraftModLangService;
import com.gxlirong.tool.utils.ChineseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    /**
     * 我的世界模组lang 获得未汉化列表
     *
     * @param id id
     * @return List<ToolMinecraftModLang>
     */
    @Override
    public List<ToolMinecraftModLang> getNotChineseList(Long id) {
        return this.list(
                new QueryWrapper<ToolMinecraftModLang>()
                        .eq("mod_id", id)
                        .eq("is_deleted", false)
                        .eq("is_chinese", false)
        );
    }

    /**
     * 我的世界模组lang字段批量创建
     *
     * @param id             模组标识
     * @param enUSStringList enUSStringList
     * @param zhCNStringList zhCNStringList
     * @return boolean
     */
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

    /**
     * 汉化字段
     *
     * @param lang lang
     * @throws InterruptedException InterruptedException
     */
    @Override
    public void chineseLang(ToolMinecraftModLang lang) throws InterruptedException {
        //开始翻译
        ChineseTranslate chineseString = chineseUtils.getChineseString("auto", "zh", chineseUtils.stringInsertWhitespace(lang.getLang()));
        if (chineseString != null) {
            String translationString = chineseString.getTrans_result().get(0).getDst();
            //去空格
            translationString = translationString.replace(" ", "");
            //标点符号转小写
            translationString = chineseUtils.toDBC(translationString);
            lang.setLang(translationString);
            log.info("{}", translationString);
        }
        lang.setIsChinese(true);
    }

    /**
     * 我的世界模组 - lang内容所有列表
     *
     * @param id id
     * @return List<ToolMinecraftModLang>
     */
    @Override
    public List<ToolMinecraftModLang> getList(Long id) {
        return this.list(
                new QueryWrapper<ToolMinecraftModLang>()
                        .eq("mod_id", id)
                        .eq("is_deleted", false)
                        .orderByDesc("is_chinese")
        );
    }

    /**
     * 我的世界模组 - 修改lang内容
     *
     * @param id                        id
     * @param minecraftModTypePostParam minecraftModTypePostParam
     * @return boolean
     */
    @Override
    public boolean update(Long id, ToolMinecraftModLangPostParam minecraftModTypePostParam) {
        ToolMinecraftModLang minecraftModLang = this.getOne(
                new QueryWrapper<ToolMinecraftModLang>().eq("id", id)
        );
        if (minecraftModLang == null) {
            throw new OperationException(ResultCode.MINECRAFT_MOD_LANG_NONE);
        }
        minecraftModLang.setIsChinese(true);
        BeanUtils.copyProperties(minecraftModTypePostParam, minecraftModLang);
        return this.updateById(minecraftModLang);
    }
}
