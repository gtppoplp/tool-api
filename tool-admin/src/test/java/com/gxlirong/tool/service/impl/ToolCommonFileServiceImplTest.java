package com.gxlirong.tool.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
class ToolCommonFileServiceImplTest {
//    @Autowired
//    private ToolFileServiceImpl fileService;

    @Test
    void chineseStringList() throws InterruptedException {
        ArrayList<String> StringList = new ArrayList<>();
        StringList.add("tile.minecolonies.blockHutCitizen.name=Citizen's Hut");
        StringList.add("tile.minecolonies.blockHutShepherd.name=Shepherd's Hut");
        StringList.add("tile.minecolonies.blockHutSwineHerder.name=SwineHerders's Hut");
        StringList.add("tile.minecolonies.blockHutCowboy.name=Cowboy's Hut");
        StringList.add("tile.minecolonies.blockHutChickenHerder.name=ChickenHerders's Hut");
//        StringList.add("tile.minecolonies.blockHutBaker.name=Bakery");
//        StringList.add("tile.minecolonies.blockHutBlacksmith.name=Blacksmith");
//        StringList.add("tile.minecolonies.blockHutBuilder.name=Builder's Hut");
//        StringList.add("tile.minecolonies.blockHutFarmer.name=Farm");
//        StringList.add("tile.minecolonies.blockHutLumberjack.name=Lumberjack's Hut");
//        StringList.add("tile.minecolonies.blockHutFisherman.name=Fisherman's Hut");
//        StringList.add("tile.minecolonies.blockHutMiner.name=Mine");
//        StringList.add("tile.minecolonies.blockHutStonemason.name=Stonemason");
//        StringList.add("tile.minecolonies.blockHutTownHall.name=Town Hall");
//        StringList.add("tile.minecolonies.blockHutLibrary.name=Library");
//        StringList.add("tile.minecolonies.blockSubstitution.name=Placeholderblock");
//        StringList.add("tile.minecolonies.blockBarracksTowerSubstitution.name=BarracksTower Placeholder");
//        StringList.add("tile.minecolonies.blockconstructiontape.name=Construction tape");
//        StringList.add("tile.blockHut.messageNoPermission=You don't have permission to open huts in %s.");
//        StringList.add("tile.blockHut.messageNoPermissionPlace=You don't have permission to place hutes in %s.");
//        StringList.add("tile.blockHutTownHall.messageMaxSize=%d has reached max size");
//        StringList.add("tile.blockHutTownHall.messageColonistDead=The colonist %s has died at %d %d %d of %s!");
//        StringList.add("tile.blockHutTownHall.messageInvalidWorld=You can't place a town hall in this world type");
//        StringList.add("tile.blockHutTownHall.messageTooClose=Too close to existing town hall");
//        StringList.add("tile.blockHutTownHall.messageTooCloseToVillage=Too close to existing village");
//        StringList.add("tile.blockHutTownHall.messageTooFar=Too far away from your Colony");
//        StringList.add("tile.blockHut.messageTooFarFromTownHall=You need to be closer to your town hall!");
//        StringList.add("tile.blockHut.messageNoTownHall=You need to place a town hall first!");
//        StringList.add("item.minecolonies.supplyChestDeployer.name=SupplyShip Chest");
//        StringList.add("item.minecolonies.supplyCampDeployer.name=SupplyCamp Chest");
//        StringList.add("item.minecolonies.ancienttome.name=Ancient Tome");
//        StringList.add("item.minecolonies.chiefsword.name=Chief Sword");
//        StringList.add("item.minecolonies.scepterGold.name=Building Tool");
//        StringList.add("item.minecolonies.scepterSteel.name=Scan Tool");
//        StringList.add("item.minecolonies.caliper.name=Caliper");
//        List<String> stringList = fileService.chineseStringList(StringList);
//        log.info("{}", stringList);
    }
}