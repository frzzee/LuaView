base = {
    rungame = "com.tgc.sky.android",
    libgame = "libBootloader.so",
    curgame = gg.getTargetInfo()
}
patern = {
    baseimg = 0x20000000,
    playerbase = 0x00,
    baseptr = 0x14D5,
    f2k = 0x300000,
    f5ty = 0x50FFFFF,
    f2f = 0xFFFFFFF,
    f1k = 0x1FFFFFF
}
    main = {
        lua = function()
            local gameinfo = {}
            gameinfo.ispackage = base.curgame.packageName
            
            if gameinfo.ispackage ~= base.rungame then
                pcall({ gg.setVisible(true);os.exit() })
                return false
            end
            
            gameinfo.img = gg.getRangesList(base.libgame)
            
            if #gameinfo.img == 0 then
                pcall({ gg.setVisible(true);os.exit() })
                return false
            end
            
            gameinfo.baseimg = gameinfo.img[1].start
            
            pcall(
                {
                    gg.toast(text['hook']);
                    libhook();
                    hookaddress();
                    gg.toast(text['loading']);
                    drawcandle();
                    gg.toast(text['loading']);
                    emitterbarn();
                    gg.toast(text['loading']);
                    avatarmod();
                    unlclothes();
                    skipcutscene();
                    --autoplays();
                    --disablehud();
                    --ellabilities();
                    energywng();
                    gg.toast(text['loading']);
                    rendermap();
                    getportal();
                    gg.toast(text['loading']);
                    drawplant();
                    drawdaily();
                }
            )
            dataEncode()
            gg.clearResults()
            
            if gg.toast(text['comp']) == gg.sleep(800) then
                gg.clearResults()
                -- process resume;
            end
        end
    }
    --main.lua( true )
    
