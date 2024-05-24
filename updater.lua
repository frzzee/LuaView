base = {
    rungame = "com.tgc.sky.android",
    libgame = "libBootloader.so",
    curgame = gg.getTargetInfo()
}
patern = {
    baseimg = 0x20000000,
    playerbase = 0x00,
    baseptr = 0x14D5
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
            
            local alert = gg.alert(text['comp']..'\n\n'..text['screst'])
            
            if alert ~= nil then
                gg.sleep(800)
                configuration();
                else
                pcall({ gg.setVisible(true);os.exit() })
            end
        end
    }
    --main.lua( true )
    
