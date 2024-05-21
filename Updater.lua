

    libhook = function()
        local so = gg.getRangesList("libBootloader.so")[1].start
        libso = {address=so,flags=gg.TYPE_DWORD,name="libso"}
        bootloader = libso.address - 0x00
        librange = gg.getRanges()
    end
    
    hookaddress = function()
        gg.setRanges(gg.REGION_C_ALLOC)
        gg.searchNumber("7,301,029,265,827,722,568", gg.TYPE_QWORD, false, gg.SIGN_EQUAL, 0, -1, 0)
        loc_ranges = 4
        if gg.getResultsCount() == 0 then
            loc_ranges = '-1032320'
            gg.setRanges(gg.REGION_OTHER)
            gg.searchNumber("7,301,029,265,827,722,568", gg.TYPE_QWORD, false, gg.SIGN_EQUAL, 0, -1, 0)
            
            if gg.getResultsCount() == 0 then
                gg.alert(text['fail'])
              return os.exit()
            end
          end
        ranges = gg.getRanges()
        c.pointer = gg.getResults(1)[1].address
        gg.clearResults()
       
        gg.searchNumber("0.03333333507~0.033999;1.0F;-1D;1D::269", gg.TYPE_FLOAT, false, gg.SIGN_EQUAL, c.pointer, c.pointer + 0x50FFFFF, 0)
        gg.refineNumber("-1", gg.TYPE_DWORD)
            if gg.getResultsCount() == 0 then
                offsetz.gspd = 0
                gg.alert(text['fail'])
              end
        gtime = gg.getResults(1)[1].address
        toaddrs = {address=c.pointer,flags=gg.TYPE_QWORD,name="address"}
        spdhck = {address=gtime-0xE8,flags=gg.TYPE_FLOAT,name="speedhack"}
        offsetz.gspd = spdhck.address - c.pointer
        caddr = spdhck.address + 0x1FFFFFF
        vaddr = spdhck.address + patern.rng
        gg.addListItems({toaddrs,spdhck})
        gg.clearResults()
      end
    
    drawcandle = function()
        local cn = {}
        gg.clearResults()
        gg.setRanges(ranges)
        gg.searchNumber(1099746509, gg.TYPE_DWORD, false, gg.SIGN_EQUAL, 0, -1, 1)
        if gg.getResultsCount() == 0 then
            gg.alert(text['fail'])
          return os.exit()
        end
        daddr = gg.getResults(1)[1].address
        candle = {address=daddr+0x1D4,flags=gg.TYPE_FLOAT,name="candle"}
        offsetz.candle = candle.address - c.pointer
        paddr = daddr + patern.rng
        gg.addListItems({candle})
        gg.clearResults()
      end
    
    emitterbarn = function()
        local x = {}
        gg.setRanges(ranges)
        gg.searchNumber("h 43 61 6E 64 6C 65 73 70 61 63 65 42 6C 69 7A 7A", gg.TYPE_BYTE, false, gg.SIGN_EQUAL, daddr, daddr + 0x50FFFFF, 0) --:CandlespaceBlizz
        if gg.getResultsCount() == 0 then
            gg.alert(text['fail'])
          return os.exit()
        end
        wx = gg.getResults(1)[1].address
        gg.clearResults()
        gg.searchNumber("32D;32D;32D;32D::13", gg.TYPE_DWORD, false, gg.SIGN_EQUAL, wx, wx + 0x1FFFFF, 0)
        if gg.getResultsCount() == 0 then
            gg.alert(text['fail'])
          return os.exit()
        end
        emt = gg.getResults(4)[4].address
        emitter = {address=emt+0x8,flags=gg.TYPE_DWORD,name="emitbarn"}
        offsetz.autofarm = emitter.address - c.pointer
        gg.addListItems({emitter})
        gg.clearResults()
      end
    
    avatarmod = function() --14D5
        gg.clearResults()
        gg.setRanges(ranges)
        gg.searchNumber('1,099,746,509', gg.TYPE_DWORD, false, gg.SIGN_EQUAL, 0, -1, 0)
        if gg.getResultsCount() == 0 then
            gg.alert(text['fail'])
          return os.exit();
        end
        toplayer = gg.getResults(1)[1].address
        gg.clearResults()
        
        if info.packageName == tgc.live then
            memfrom = toplayer
            memto = toplayer + 0x20000000
        else
        if info.packageName == tgc.beta then
            memfrom = toplayer
            memto = toplayer
          end
        end
        
        local av = {}
        gg.clearResults()
        gg.setRanges(ranges)
        gg.searchNumber("100.0F;5.00403682e-42F;0.1~1.0F;1.0F::65", gg.TYPE_FLOAT, false, gg.SIGN_EQUAL, memfrom, memto + 0x50FFFFF, 0)
        
        if gg.getResultsCount() == 0 then
            gg.alert(text['fail'])
            os.exit()
          end
        gg.refineNumber("5.00403682e-42", gg.TYPE_FLOAT)
        if gg.getResultsCount() == 0 then
            gg.alert(text['fail'])
          return os.exit()
        end
        avamod = gg.getResults(1)[1].address
        avatarx = {address=avamod+0x8,flags=gg.TYPE_FLOAT,name="avatarx"}
        offsetz.avmod = avatarx.address - c.pointer
        gg.toast(text['loadpl']..'...')
        
        gg.clearResults()
        gg.setRanges(ranges)
        gg.searchNumber("1~4.0F;-0.333~9999F;1.25F;1.0F::109", gg.TYPE_FLOAT, false, gg.SIGN_EQUAL, avamod, avamod + 0x50FFFFF, 0)
        if gg.getResultsCount() == 0 then
            gg.alert(text['fail'])
          return os.exit();
        end
        gg.refineNumber(1.25,gg.TYPE_FLOAT)
           if gg.getResultsCount() ~= 0 then
              avwin = gg.getResults(1)[1].address + 0x60
              gg.clearResults()
            else
           gg.alert(text['fail'])
           os.exit()
           end
        avawin = {address=avwin,flags=gg.TYPE_DWORD,name="avatarwing"}
        offsetz.avawin = avawin.address - c.pointer
        capes = avwin - 0x70
        vcape = {address=capes,flags=gg.TYPE_FLOAT,name="viscape"}
        offsetz.viscape = vcape.address - c.pointer
        gg.addListItems({avatarx,avawin})
        gg.clearResults()
      end
    
    rendermap = function()
        local mp = {}
        gg.clearResults()
        gg.setRanges(ranges)
        gg.searchNumber("h 52 65 73 6F 75 72 63 65 73 2F 48 61 70 74 69 63", gg.TYPE_BYTE, false, gg.SIGN_EQUAL, c.pointer - 0x50FFFFF, c.pointer, 0)
        gg.refineNumber(":R", gg.TYPE_BYTE)
        if gg.getResultsCount() == 0 then
            gg.alert(text['fail'])
          return os.exit();
        end
        rsc = gg.getResults(gg.getResultsCount())
        if gg.getResultsCount() > 1 then
            for i, v in ipairs(rsc) do
                table.insert(mp, {address=v.address+0x20,flags=gg.TYPE_BYTE,name=""..i..""})
            end
            gg.loadResults(mp)
            gg.refineNumber(0,gg.TYPE_BYTE)
            if gg.getResultsCount() ~= 0 then
                maps = gg.getResults(1)[1].address - 0x20
              else
            gg.alert(text['fail'])
            os.exit();
            end
          end
        maps = rsc[1].address + 0x87
        tmp = {address=maps,flags=gg.TYPE_BYTE,name="maps"}
        offsetz.level = c.pointer - tmp.address
        gg.addListItems({tmp})
        gg.clearResults()
      end
    
    getportal = function()
        local h = {}
        gg.setRanges(ranges)
        gg.searchNumber("h 41 70 31 37 49 6E 74 72 6F", gg.TYPE_BYTE, false, gg.SIGN_EQUAL, daddr - 0x50FFFFF, daddr, 1)
        gg.refineNumber("h 41", gg.TYPE_BYTE, false, gg.SIGN_EQUAL, daddr - 0x50FFFFF, daddr, 1)
        if gg.getResultsCount() == 0 then
            offsetz.portal = 0x00
            gg.alert(text['fail'])
          return os.exit();
        end
        port = gg.getResults(1)[1].address
        if info.packageName == tgc.live then
            ptoptr = port - patern.ptr
            portal = {address=ptoptr+0xD0,flags=gg.TYPE_DWORD,name="portal"}
        else
        if info.packageName == tgc.beta then
            ptoptr = port - patern.ptr
            portal = {address=ptoptr-0xD0,flags=gg.TYPE_DWORD,name="portal"}
          end
        end
        offsetz.portal = portal.address - c.pointer
        gg.addListItems({portal})
        gg.clearResults()
     end
        
        
    drawdaily = function()
        local day = {}
        gg.clearResults()
        gg.setRanges(ranges)
        gg.searchNumber("1.0F;1.0F;1D;2D;3D;4D:97", gg.TYPE_DWORD, false, gg.SIGN_EQUAL, c.pointer, c.pointer + 0x1FFFFFF, 0)
        if gg.getResultCount() == 0 then
            gg.alert(text['fail']..'\n\n'..text['try'])
          return os.exit();
        end
        gg.refineNumber(1,gg.TYPE_DWORD)
            if gg.getResultsCount() ~= 0 then
                dy = gg.getResults(gg.getResultsCount())
                daily = dy[1].address
              else
              gg.alert(text['fail'])
              os.exit();
            end
        dailies = {address=daily,flags=gg.TYPE_DWORD,name="daily"}
        gg.clearResults()
        quest = 0
        while true do
        quest = quest + 1
        if getadd(dailies.address + quest * 8, gg.TYPE_DWORD) == quest + 1 then
                gg.toast(text['draw'] .. "" ..quest)
              else
              gg.toast(text['draw'] .. "" ..quest)
              --gg.sleep(200)
            break
           end
         end
        offsetz.daily = dailies.address - c.pointer
        cday = quest
        gg.addListItems({dailies})
       end
 
    drawplant = function()
        local pn = {}
        gg.clearResults()
        gg.setRanges(ranges)
        gg.searchNumber("h 00 40 9C 45 00 00 7A 43 00 00 C8 42",  gg.TYPE_BYTE, false, gg.SIGN_EQUAL, c.pointer, c.pointer + 0xFFFFFFF, 0)
        gg.refineNumber(122, gg.TYPE_BYTE)
        if gg.getResultsCount() == 0 then
            --gg.alert(text['fail'])
          return os.exit()
        end
        topn = gg.getResults(gg.getResultsCount())
        plant = topn[1].address + 0x6B42
        splant = {address=plant,flags=gg.TYPE_FLOAT,name="plant"}
        offsetz.plant = splant.address - c.pointer
        gg.addListItems({splant})
        gg.clearResults()
      end
    
    unlclothes = function() --no idea actually
        gg.clearResults()
        gg.setRanges(gg.REGION_CODE_APP)
        gg.searchNumber("-6250626897976293408", gg.TYPE_QWORD, false, gg.SIGN_EQUAL, 0, -1, 0)
        if gg.getResultsCount() == 0 then
            offsetz.cloth = 0x00
            --gg.toast(text['fail'])
          return
        end
        ch = gg.getResults(2)
        cth = ch[2].address
        ctadd = {address=cth,flags=gg.TYPE_DWORD,name="clothes"}
        offsetz.cloth = ctadd.address - libso.address
        gg.addListItems({ctadd})
        gg.clearResults()
      end
      
    energywng = function()
        gg.clearResults()
        gg.setRanges(gg.REGION_CODE_APP)
        gg.searchNumber("8.59771529e-21F;8.52988648e-21F;8.53898546e-21F::9", gg.TYPE_FLOAT, false, gg.SIGN_EQUAL, 0, -1, 0)
        gg.refineNumber(8.59771529e-21, gg.TYPE_FLOAT)
        if gg.getResultsCount() == 0 then
            offsetz.energy = 0x00
            --gg.toast(text['fail'])
          return
        end
        avn = gg.getResults(1)[1].address
        ewn = {address=avn,flags=gg.TYPE_DWORD,name="avatarenergy"}
        offsetz.energy = ewn.address - libso.address
        gg.addListItems({ewn})
        gg.clearResults()
      end
        
    rellabilities = function()
        gg.clearResults()
        gg.setRanges(gg.REGION_C_DATA)
        gg.searchNumber("h 6B 45 6E 61 62 6C 65 41 6C 6C 52 65 6C 61 74 69 6F 6E 73 68 69 70 41 62 69 6C 69 74 69 65 73", gg.TYPE_BYTE, false, gg.SIGN_EQUAL, libso.address - 0x50FFFFF, libso.address + 0x50FFFFF, 1)
        gg.refineNumber("h 6B", gg.TYPE_BYTE)
        if gg.getResultsCount() == 0 then
            offsetz.friends = 0x00
            --gg.toast(text['fail'])
          return
        end
        fc = gg.getResults(gg.getResultsCount())
        gg.clearResults()
        gg.setRanges(gg.REGION_C_BSS)
        jumpToAddr(fc,0)
        if gg.getResultsCount() ~= 0 then
            friend = gg.getResults(1)[1].address - 0x8
            sfriend = {address=friend,flags=gg.TYPE_DWORD,name="friendship"}
            offsetz.friends = sfriend.address - libso.address
            gg.addListItems({sfriend})
            gg.clearResults()
          else
          offsetz.friends = 0x00
          --gg.alert(text['fail'])
          os.exit();
        end
      end
    
    disablehud = function()
        gg.clearResults()
        gg.setRanges(gg.REGION_C_DATA)
        gg.searchNumber(":kDisable_Hud", gg.TYPE_BYTE, false, gg.SIGN_EQUAL, libso.address - 0x50FFFFF, libso.address + 0x50FFFFF, 1)
        gg.refineNumber(":k", gg.TYPE_BYTE)
        if gg.getResultsCount() == 0 then
            offsetz.hud = 0x00
            gg.toast(text['fail'])
          return
        end
        hd = gg.getResults(gg.getResultsCount())
        gg.clearResults()
        gg.setRanges(gg.REGION_C_BSS)
        jumpToAddr(hd, 0)
        if gg.getResultsCount() ~= 0 then
            dhud = gg.getResults(1)[1].address
            dishud = {address=dhud-0x484,flags=gg.TYPE_DWORD,name="disablehud"}
            offsetz.hud = dishud.address - libso.address
            gg.addListItems({dishud})
            gg.clearResults()
          else
          offsetz.hud = 0x00
          --gg.toast(text['fail'])
        end
       end
    
    autoplays = function()
        gg.clearResults()
        gg.setRanges(gg.REGION_C_DATA)
        gg.searchNumber(":kInstrumentAutoPlaySheets", gg.TYPE_BYTE, false, gg.SIGN_EQUAL, libso.address - 0x50FFFFF, libso.address + 0x50FFFFF, 1)
        gg.refineNumber(":k", gg.TYPE_BYTE)
        if gg.getResultsCount() == 0 then
            offsetz.sheets = 0x00
            --gg.toast(text['fail'])
          return
        end
        sht = gg.getResults(gg.getResultsCount())
        gg.clearResults()
        gg.setRanges(gg.REGION_C_BSS)
        jumpToAddr(sht,0)
        if gg.getResultsCount() ~= 0 then
            play = gg.getResults(1)[1].address
            playsht = {address=play-0x8,flags=gg.TYPE_DWORD,name="autoplaysheets"}
            offsetz.sheets = playsht.address - libso.address
            gg.addListItems({playsht})
            gg.clearResults()
         else
         offsetz.sheets = 0x00
         --gg.toast(text['fail'])
        end
      end
      
    skipcutscene = function()
        gg.clearResults()
        gg.setRanges(gg.REGION_C_DATA)
        gg.searchNumber("h 6B 41 75 74 6F 53 6B 69  70 41 6C 6C 54 69 6D 65  6C 69 6E 65 73", gg.TYPE_BYTE, false, gg.SIGN_EQUAL, 0, -1, 0)
        gg.refineNumber("h 6B", gg.TYPE_BYTE)
        if gg.getResultsCount() == 0 then
            --gg.toast(text['fail'])
          return os.exit();
        end
        cts = gg.getResultsCount()
        gg.clearResults()
        
        gg.setRanges(gg.REGION_C_BSS)
        jumpToAddr(cts,0)
        skip = gg.getResults(1)[1].address
        cutscene = {address=skip-0x8,flags=gg.TYPE_DWORD,name="timeline"}
        offsetz.cutscene = cutscene.address - libso.address
        gg.addListItems({cutscene})
        gg.clearResults()
     end
     
     
    dataEncode = function()
    local jar = {}
    zone = os.date("*t")
    for i = 1, 1 do
        table.insert(jar, {
            --//file generated by json.lua
            script = {
                label = "LazyFarm",
                code = string.format("%d", info.versionCode),
                modified = zone['day'] .. '/' .. zone['month'] .. '/' .. zone['year'],
                author = "akaxel",
                link = "https://gameguardian.net/forum/profile/1389268-yorrue/",
            },     
            game = {
                package = info.packageName,
                version = info.versionName,
                label = info.label .. ' [' ..title.. ']',
            },
            offsets = {
                avmod = "0x" ..string.format("%x", offsetz.avmod),
                avawin = "0x" ..string.format("%x", offsetz.avawin),
                daily = "0x" ..string.format("%x", offsetz.daily),
                level = "0x" ..string.format("%x", offsetz.level),
                portal = "0x" ..string.format("%x", offsetz.portal),
                speed = "0x" ..string.format("%x", offsetz.gspd),
                embarn = "0x" ..string.format("%x", offsetz.autofarm),
                candle = "0x" ..string.format("%x", offsetz.candle),
                plant = "0x" ..string.format("%x", offsetz.plant),
                energy = "0x" ..string.format("%x", offsetz.energy),
                cloth = "0x" ..string.format("%x", offsetz.cloth),
                viscape = "0x" ..string.format("%x", offsetz.viscape),
                cutscene = "0x" ..string.format("%x", offsetz.cutscene),
            },
            other = {
                stringdefs = 'English',
                daycount = string.format("%d", cday),
                nativecode = string.format("%d", loc_ranges),
            }
        })
      end
      jsons = io.open('/sdcard/Android/Akaxel/akx-' .. title .. 'farm.json', 'w')
      jsons:write(json.encode(jar))
      jsons:close()
    end
    
    dataUpdate = function()
        --gg.toast(text['hook'])
        libhook()
        hookaddress()
        --gg.toast(text['loading'])
        drawcandle()
        --gg.toast(text['loading'])
        emitterbarn()
        --gg.toast(text['loading'])
        avatarmod()
        unlclothes()
        skipcutscene()
        energywng()
        --gg.toast(text['loading'])
        rendermap()
        getportal()
        --gg.toast(text['loading'])
        drawplant()
        drawdaily()
        --dataEncode()
        gg.clearResults()
        --gg.alert(text['comp']..'\n\n'..text['screst'])
        gg.setVisible(true)
        os.exit();
    end
    
    
