local traceback = {}
local void = {}
local quest_count = 0
local baselib = "libBootloader.so"
local base = {
    game = {
        package = gg.getTargetInfo().packageName
    }
}
local offset = {
    speed = 0x1b0,
    autowax = 0x4200, --// 0x42c8,
    dayquest = 0x59f0a4,
    avatar = 0x17d80,
    curlevel = 0x230,
    realms = 0x179c8,
    candle = 0x20d38,
    plant = 0x13ea8,
    teleport = 0x67AF0, --0x58AA38,
    statue = 0x670c8
}
local void = {
    speedhack = nil,
    autowax = nil,
    wingbuff = nil,
    dayquest = nil,
    avatar = nil,
    curlevel = nil,
    realms = nil,
    getcandle = nil,
    burncandle = nil,
    burnplant = nil,
    portalmp = nil,
    beacon = nil,
    teleport = nil,
    statue = nil,
    timeline = nil,
    minspeed = nil
}
gg.setVisible(false)

local anti_load = function(code)
    local load_lenght = 0
    local take_code = function(Code)
        local chiper = load_lenght + 1
        load_lenght = chiper
        return code[load_lenght]
    end
    return take_code
end local code = {" "," "," "} assert(load(anti_load(code)))()

function error(msg)
    gg.toast(msg);os.exit()
end

function getvalues(a, b)
    local c = {}
    c[1] = {address = a, flags = b}
    c = gg.getValues(c)
    return tonumber(c[1].value)
end

function setvalues(a, b, c, d)
    local v = {
        {address = a, flags = b, value = c, freeze = d}
    }
    if d then
        gg.addListItems(v)
    else
        gg.removeListItems(v)
    end
    return gg.setValues(v)
end

function setstring(data)
    local values = {}
    
    for k, v in ipairs(data) do
    	local _address = v[1]
    	local _bytes = gg.bytes(v[2])
    	local _len = v[3]
    	
    	if #_bytes < _len then
    		local _len2 = _len - #_bytes
    		for i = 1, _len2 do
    			table.insert(_bytes,0)
    		end
    	end
    	
    	for i = 1, _len do
    	    table.insert(values, {
    	        address = _address + (i - 1), 
    	        flags = 1,
    	        value = _bytes[i]
    	    })
        end
    end
    gg.setValues(values)
end

function getstring(get_str, get_len)
    set_str = ''
    for i = 0, get_len do
        values = {
            [1] = {address = get_str + i, flags = 1}
        }
        is_value = gg.getValues(values)
        if tonumber(is_value[1].value) < 1 then
            break
        end
        set_str = set_str .. string.char(tonumber(is_value[1].value))
    end
    return set_str
end

function dif(from, to)
    local set = from - to
    local get = string.format("0x%x", set)
    if from < to then
        set = to - from
        get = string.format("-0x%x", set)
    end
    return get
end

function insert_tab(vname, vaddrs, vpoint, vflags, paddrs, add)
    local isParams = {}
    local bool = add
    table.insert(traceback, {
        address = vaddrs, flags = vflags, name = vname, pointer = vpoint, vars = paddrs
    })
    return traceback
end

function addList(a, f, n)
    local values = {
        {address = a, flags = f, name = n}
    }
    return gg.addListItems(values)
end

function rev(strs, len)
    local char = getstring(strs, len)
    char = char:gsub("%_", "0")
    char = char:gsub("[^0-9A-Za-z///' ]", "")
    char = char:gsub("0", "_")
    char = char:gsub("%s+", "")
    return char
end

function get_pointer(vars, bool)
    local array = {}
    local pointer = 0
    local m_from = 0
    local m_limit = -1
    local p_from = 0
    local p_limit = -1
    local backup = false
    gg.setRanges(8|16)
    
    if not(bool) then
        gg.toast("scanning " .. #traceback + 1)
    end
    if string.find(base.game.package, "artdeell") ~= nil then
        local m_from = libstate
        local m_limit = libstate + 0x1FFFFFF
        local backup = true
        gg.setRanges(gg.REGION_OTHER)
    end
    
    gg.searchNumber(vars, 1, false, 536870912, m_from, m_limit, 0)
    if gg.getResultsCount() == 0 then
        return 0 --// error("search for \"" .. vars .. "\" has null results")
    end
    setstate = gg.getResults(1)[1].address
    results = gg.getResults(gg.getResultsCount())
    gg.clearResults()
    
    if backup then
        p_from = setstate
        p_limit = setstate + 0x1FFFFFF
    end
    
    gg.loadResults(results);gg.searchPointer(0, p_from, p_limit, 1)
    if gg.getResultsCount() ~= 0 then
        table.insert(array, {address = gg.getResults(1)[1].address - 0x8, flags = 32})
    else
        return 0 --// error("fail to hooking class pointer for \"" .. vars .. "\"")
    end
    gg.clearResults()
    
    pointer = array[1].address
    
    return pointer
end

function set_pointer(vars, offset, bool)
    local array = {}
    local pointer = 0
    local m_from = 0
    local m_limit = -1
    local p_from = 0
    local p_limit = -1
    local backup = false
    gg.setRanges(8|16)
    
    if not(bool) then
        gg.toast("scanning " .. #traceback + 1)
    end
    if string.find(base.game.package, "artdeell") ~= nil then
        local m_from = libstate
        local m_limit = libstate + 0x1FFFFFF
        local backup = true
        gg.setRanges(gg.REGION_OTHER)
    end
    
    gg.searchNumber(vars, 1, false, 536870912, m_from, m_limit, 0)
    if gg.getResultsCount() == 0 then
        return error("search for \"" .. vars .. "\" has null results")
    end
    setstate = gg.getResults(1)[1].address
    results = gg.getResults(gg.getResultsCount())
    gg.clearResults()
    
    if backup then
        p_from = setstate
        p_limit = setstate + 0x1FFFFFF
    end
    
    gg.loadResults(results);gg.searchPointer(0, p_from, p_limit, 1)
    if gg.getResultsCount() ~= 0 then
        table.insert(array, {address = gg.getResults(1)[1].address + offset, flags = 32})
    else
        error("fail to hooking class pointer for \"" .. vars .. "\"")
    end
    gg.clearResults()
    
    pointer = array[1].address
    
    return pointer
end

function xa_scanner(strings, flags, mark, offset, bool)
    local params = {}
    gg.toast("scanning " .. #traceback + 1)
    
    gg.setRanges(16384)
    
    gg.searchNumber(strings, flags, false, 536870912, 0, -1, 0)
    if gg.getResultsCount() == 0 then
        return 0
    end
    
    get_xa = gg.getResults(mark)[mark].address + offset
    
    if bool then
        table.insert(params, {address = get_xa, flags = flags})
    end
    gg.clearResults()

    return get_xa
end

function scanfrom(pointer, flags, target)
    local state = pointer + 0x00
    while (getvalues(state, flags) ~= target) do
        --// gg.toast("scanning(" .. pointer .. ")[" .. #traceback + 1 .. "].address")
        state = state + 0x4
        if (state - pointer) >= 0x1ffff then
            return 0
        end
    end
    return state
end

function setup_main(bool)
    local debug = bool
    
    gg.toast("Loading")
    
    get_speed = getvalues(get_pointer(":g_quality"), 32)
    
    if scanfrom(get_speed + offset.speed, 4, -1) ~= 0 then
        void.speedhack = get_speed + 0xd8
        insert_tab("speedhack", void.speedhack, get_speed, 16, void.speedhack, debug)
        addList(void.speedhack, 16, "speedhack")
    end
    
    get_autowax = getvalues(get_pointer(":g_simpleUi"), 32)
    
    if scanfrom(get_autowax + offset.autowax, 4, 4) ~= 0 then
        void.autowax = scanfrom(get_autowax + offset.autowax, 4, 4) + 0x41c
        insert_tab("autowax", void.autowax - 0x41c, get_autowax, 4, void.autowax, debug)
        addList(void.autowax, 4, "autowax")
    end
    
    get_portal = getvalues(get_pointer(":g_netProfiler"), 32)
    
    if scanfrom(get_portal + offset.realms, 4, 1936617283) ~= 0 then
        get_realms = scanfrom(get_portal + offset.realms, 4, 1936617283)
        void.wingbuff = getvalues(get_realms + 0x18, 32) + 0xe0
        insert_tab("wingbuffs", get_realms, get_portal, 4, void.wingbuff, debug)
        addList(void.wingbuff, 4, "wingbuff")
    end
    
    get_avatar = getvalues(get_pointer(":g_netProfiler"), 32)
    
    if scanfrom(get_avatar + offset.avatar, 4, 1635017078) ~= 0 then
        get_pos = scanfrom(get_avatar + offset.avatar, 4, 1635017078)
        void.avatar = getvalues(get_pos + 0x18, 32)
        insert_tab("avatar", get_pos, get_avatar, 16, void.avatar, debug)
        addList(void.avatar, 16, "pos")
    end
    
    get_candle = getvalues(get_pointer(":g_timelineBarn"), 32)
    
    if scanfrom(get_candle - offset.candle, 4, 1) ~= 0 then 
        get_candles = scanfrom(get_candle - offset.candle, 4, 1)
        void.getcandle = getvalues(get_candles + 0x30, 32)
        insert_tab("candle", get_candles, get_candle, 4, void.getcandle, debug)
        addList(void.getcandle, 4, "candle")
    end
    
    get_plant = getvalues(get_pointer(":g_debugModule"), 32)
    
    if scanfrom(get_plant - offset.plant, 4, 3) ~= 0 then
        void.burnplant = scanfrom(get_plant - offset.plant, 4, 3) + 0xC
        insert_tab("plant", void.burnplant - 0xC, get_plant, 4, void.burnplant, debug)
        addList(void.burnplant, 16, "plant")
    end
    
    get_maps = getvalues(get_pointer(":g_resourceManager"), 32)
    
    if getvalues(get_maps + offset.curlevel, 4) ~= 0 then
        void.curlevel = get_maps + offset.curlevel
        insert_tab("leveldata", void.curlevel, get_maps, 4, void.curlevel, debug)
        addList(void.curlevel, 1, "leveldata")
    end
    
    get_beacon = getvalues(get_pointer(":g_idPoolBarn"), 32)
    
    if scanfrom(get_beacon - offset.teleport, 4, 4) ~= 0 then
        void.beacon = scanfrom(get_beacon - offset.teleport, 4, 4)
        insert_tab("levelportal", void.beacon, get_beacon, 4, void.beacon, debug)
        addList(void.beacon, 16, "teleport")
    end
    
    if void.avatar ~= nil then
        get_quest = getvalues(void.avatar - 0x20, 32) + 0x8
        void.dayquest = getvalues(get_quest, 32) + 0x20
        addList(void.dayquest, 4, "daily")
    end
    
    if void.dayquest ~= nil then
        for i = 1, 500 do
            data = void.dayquest + (i-1) * 0x8
            if getvalues(data + 0x8, 4) - getvalues(data, 4) ~= 1 then
                break
            end
            quest_count = i
        end
    end
    if void.getcandle ~= nil then
        if getvalues(void.getcandle + 0xC, 4) ~= 1099746509 then
            void.burncandle = nil
        else
            is_candle = getvalues(void.getcandle + 0x38, 32)
            void.burncandle = is_candle + 0x128
        end
    end
end

function lib_search(debug)
    get_minspeed = get_pointer(":minYSpeed")
    
    if get_minspeed ~= 0 then
        void.minspeed = get_minspeed + 0x8
        insert_tab("minspeed", void.minspeed, libstate, 32, void.minspeed, debug)
        addList(void.minspeed, 32, "minspeed")
    end
    
    get_portalimg = set_pointer(":ChangeLevelWithFade", -0x8c)
    
    if get_portalimg ~= 0 then
        void.portalimg = get_portalimg + 0x00
        insert_tab("portalimg", void.portalimg, libstate, 32, void.portalimg, debug)
        addList(get_portalimg, 4, "portal")
    end
    
    get_plight = set_pointer(":kAncestorType_Whale", -0x6c)
    
    if get_plight ~= 0 then
        void.lightplayer = get_plight + 0x00
        insert_tab("light_player", void.lightplayer, libstate, 32, void.lightplayer, debug)
        addList(get_plight, 4, "light_player")
    end
    
    get_uiwater = set_pointer(":kNewLensDistortionSoftDisable", 0x00)
    
    if get_uiwater ~= 0 then
        void.uibutton = get_uiwater - 0xc
        void.water = get_uiwater - 0x10
        insert_tab("uibutton", void.uibutton, libstate, 32, void.uibutton, debug)
        insert_tab("water", void.water, libstate, 32, void.water, debug)
        addList(void.uibutton, 4, "ui button")
        addList(void.water, 4, "water")
    end
    
    get_scene = set_pointer(":kAutoSkipAllTimelines", -0x8)
    
    if get_scene ~= 0 then
        void.scene = get_scene + 0x00
        insert_tab("scene", void.scene, libstate, 32, void.scene, debug)
        addList(void.scene, 4, "cutscene")
    end
    
    get_wind = xa_scanner("4366525316508704736", 32, 1, 0xC, debug)
    
    if get_wind ~= 0 then
        void.windbarrier = get_wind + 0x00
        insert_tab("windbar", void.windbarrier, libstate, 32, void.windbarrier, debug)
        addList(void.windbarrier, 4, "wind_bar")
    end
    
    get_absorb = xa_scanner("2170770379738507873", 32, 2, -0x14, debug)
    
    if get_absorb ~= 0 then
        void.absorb = get_absorb + 0x00
        insert_tab("wax_absorb", void.absorb, libstate, 32, void.absorb, debug)
        addList(void.absorb, 4, "wax_absorb")
    end
    
    get_portalbar = xa_scanner("-486236613962104664", 32, 1, 0x00, debug)
    
    if get_portalbar == 0 then
        get_portalbar = xa_scanner("-486236611260768225", 32, 1, 0x00, debug)
        if get_portalbar ~= 0 then
            void.portalbar = get_portalbar + 0x00
        end
    else
        void.portalbar = get_portalbar + 0x00
    end
    addList(void.portalbar, 4, "portal_bar")
    insert_tab("portal_bar", void.portalbar, libstate, 32, void.portalbar, debug)
    
    get_session = xa_scanner("-486363744977288984", 32, 1, 0x00, debug)
    
    if get_session == 0 then
        get_session = xa_scanner("-486363742292729825", 32, 1, 0x00, debug)
        if get_session ~= 0 then
            void.session = get_session + 0x00
        end
    else
        void.session = get_session + 0x00
    end
    addList(void.session, 4, "void.session")
    insert_tab("session", void.session, libstate, 32, void.session, debug)
    
    get_progress = xa_scanner("1441151907488862216", 32, 1, -0x38, debug)
    
    if get_progress ~= 0 then
        void.progress = get_progress + 0x00
        insert_tab("progress", void.progress, libstate, 32, void.progress, debug)
        addList(void.progress, 4, "progress")
    end
    
    gg.setRanges(8|16)
    gg.searchNumber(":undulationScale", 1, false, 536870912, 0, -1, 0)
    if gg.getResultsCount() ~= 0 then
        results = gg.getResults(gg.getResultsCount())
        gg.clearResults()
        
        gg.loadResults(results);gg.searchPointer(0)
        if gg.getResultsCount() == 0 then
            void.cloud = 0
        else
            fresults = gg.getResults(gg.getResultsCount())
            for i, v in ipairs(fresults) do
                if getvalues(fresults[i].address + 0x54, 16) == 2.5 then
                    cloud_scale = fresults[i].address + 0x50
                end
            end
            void.cloud = cloud_scale + 0x00
        end
    else
        void.cloud = 0
    end
    addList(void.cloud, 4, "cloud")
    gg.clearResults()
end

function get_level_list(bool)
    local Level_Data = {}
    local debug = bool
    local level_data = getvalues(get_pointer(":g_timelineBarn"), 32)
    local str_level = scanfrom(level_data - 0x9e0, 4, 1667590211)
    local is_string = getvalues(str_level - 0x18, 32)
    insert_tab("core_map", str_level, level_data, 4, is_string, debug)
    if str_level == 0 then
        return gg.toast("attempt to concate nil value")
    end
    
    for i = 1, 150 do
        get_str = is_string + (i-1) * 0x18
        if getvalues(get_str, 4) == 0 then
            break
        end
        core_map = rev(get_str, 26)
        if #gg.bytes(core_map) == 0 then
            core_map = rev(getvalues(get_str + 0x10, 32), 26)
        end
        table.insert(Level_Data, core_map)
    end
    --// substring
    for i = 1, #Level_Data do
        if string.len(Level_Data[i]) < 4 then
            table.remove(Level_Data, i)
        end
    end
    for i = 1, #Level_Data do
        if Level_Data[i] == "MainStreet_Cafe_Wonderland" then
            table.remove(Level_Data, i)
        end
    end
    if debug then
        table.insert(global_var, {"core_map", dif(level_data, str_level)})
    end
    return Level_Data
end

function draw_vendor(array)
    for i = 1, 600 do
        vdata = v_entity.vendor + (i - 1) * 0xe0
        if getvalues(vdata, 4) == 0 then
            break
        end
        v_name = rev(vdata, 26)
        if #gg.bytes(v_name) == 0 then
            v_name = rev(getvalues(vdata + 0x10, 32), 26)
        end
        table.insert(array, {name = v_name, address = vdata + 0x70})
    end
end

function get_level_portal(strings, bool)
    local debug = bool
    if strings == nil then
        return gg.toast("attempt to concate nil value")
    end
    if not(debug) then gg.toast("Load Maps") end
    map_scene = strings + 0x234
    if getvalues(map_scene, 4) ~= 1768445706 then
        if getvalues(map_scene, 4) ~= 1634484746 then
            while getvalues(map_scene, 4) ~= 1768445706 do
                map_scene = map_scene + 0x4
                if (map_scene - strings) >= 0x1ffff then
                    return gg.toast("unexpected long ranges from " .. strings)
                end
                if getvalues(map_scene, 4) == 1634484746 then
                    break
                end
            end
        end
    end
    
    switch_map = map_scene + 0x8
    if getvalues(switch_map, 4) == 0 then
        while getvalues(switch_map, 4) == 0 do
            switch_map = switch_map + 0x4
            if (switch_map - map_scene) >= 0x1ffff then
                return gg.toast("unexpected long ranges from " .. map_scene)
            end
        end
    end
    
    v_portalmp = map_scene - 0x44
    v_teleport = switch_map
    
    if debug then
        insert_tab("portal_map", v_portalmp, strings, 4, v_portalmp, debug)
        table.insert(global_var, {"portal_map", dif(v_teleport, strings)})
    end
end

function draw_maps(strings, bool)
    get_level_portal(strings, bool)
    return get_level_list(bool)
end

function teleport_to(maps)
    local set_level = v_portalmp + 0x00
    local fade = v_teleport + 0x4
    local change = {}
    setstring({
        {set_level + 0x3ED8, maps, 24},
        {set_level + 0x3EF4, 4, #maps, false}
    })
    change = {
    	{address = set_level - 0x3C, flags = 16, value = 80000},
    	{address = set_level - 0x28, flags = 16, value = 80000},
    	{address = set_level - 0x14, flags = 16, value = 80000},
    	{address = set_level + 0x4, flags = 4, value = 1},
    	{address = set_level + 0x2C, flags = 32, value = 49},
    	{address = set_level + 0x34, flags = 32, value = 24},
    	{address = set_level + 0x3C, flags = 32, value = set_level + 0x3ED8},
    	{address = fade, flags = 4, value = 999}
    }
    return gg.setValues(change)
end

function load_burn(array, array2)
    local bool = false
    if v_main.candle ~= nil then
        for i = 1, 1000 do
            candle = v_main.candle + (i-1) * 0x1d0
            table.insert(array, {address = candle, flags = 16})
        end
    end
    if v_main.plant ~= nil then
        for i = 1, 1000 do
            plants = v_main.plant + (i-1) * 0x8
            table.insert(array2, {address = plants, flags = 16})
        end
    end
    return bool
end

function drawOutfit(table1, table2)
    for i = 1, 5000 do
        data = v_avatar.outfit + (i-1) * 0x730
        if getvalues(data, 4) == 0 then
            break
        end
        table.insert(table1, {address = data + 0x2d8, flags = 4, value = getvalues(data + 0x2d8, 4)})
        table.insert(table2, {address = data + 0x2d8, flags = 4, value = 16842752})
    end
    return table1, table2
end

function drawEmotes(table1, table2)
    local emote_list = v_avatar.emote + 0x1F8
    for i = 1, 150 do
        v_emote = emote_list + (i-1) * 0x30
        if getvalues(v_emote, 4) == 0 then
            break
        end
        table.insert(table1, {address = v_emote + 0x20, flags = 4, value = getvalues(v_emote + 0x20, 4)})
        table.insert(table2, {address = v_emote + 0x20, flags = 4, value = 7})
    end
    return table1, table2
end

function unlockOutfit(array)
    return gg.setValues(array)
end

function unlockEmote(array, bool)
    if bool then
        setvalues(v_avatar.progress, 4, 1384120352)
    else
        setvalues(v_avatar.progress, 4, 446629856)
    end
    return gg.setValues(array)
end

function request_api(bool)
    setup_main(bool)
    lib_search(bool)
    v_main = {
        autowax = void.autowax + 0x00,
        pos = void.avatar + 0x00,
        speedhack = void.speedhack + 0x00,
        wingbuff = void.wingbuff + 0x00,
        daily = void.dayquest + 0x00,
        candle = void.burncandle + 0x00,
        plant = void.burnplant + 0x00,
        teleport = void.beacon + 0x00,
        curmap = void.curlevel + 0x00,
        wpoint = void.avatar + 0xB313D8 --// 3747 Q
    }
    v_avatar = {
        spell = void.avatar + 0x2530,
        spell_slot = void.avatar + 0x2530 + 0x3848,
        wings = void.avatar + 0x5668,
        energy = void.avatar + 0x5594,
        body = void.avatar + 0x7c04,
        outfit = getvalues(void.avatar + 0x7c04 - 0x3c, 32) + 0x5ED0,
        emote = void.avatar - 0x18DA28,
        afk = void.avatar + 0xa7e0,
        oxygen = void.avatar + 0x55b0,
        run = void.minspeed - 0x4,
        wdis = void.minspeed - 0x14,
        swim = void.minspeed + 0x75c, --//0.64999997616
        dive = void.minspeed + 0x754, --//1.27499997616
        jump = void.minspeed + 0x740,
        progress = void.progress + 0x00,
        wingdmg = void.avatar + 0x566C
    }
    v_world = {
        hide_cloud = void.cloud + 0x00,
        hide_water = void.water + 0x00,
        hide_ui = void.uibutton + 0x00,
        rev_portal = void.portalimg - 0x8,
        rev_barrier = void.portalbar + 0x00,
        rev_wind = void.windbarrier + 0x00,
        lightplayer = void.lightplayer - 0x4,
        scene = void.scene + 0x00,
        absorb = void.absorb + 0x00,
        session = void.session + 0x00
    }
    v_entity = {
        player = void.avatar + 0x29090,
        spirit = void.avatar + 0x171410,
        closet = v_main.wpoint + 0x136E0,
        dyecolor = v_main.wpoint + 0xE0F30,
        vendor = void.dayquest + 0x3B25F0
    }
    v_vars = {
        --// avatar
        {wenergy, v_avatar.energy, 16, 999, 0, true},
        {oxygen, v_avatar.oxygen, 16, 1, 1, true},
        {fakeafk, v_avatar.afk, 16, 999, 0, true},
        {fastrun, v_avatar.run, 16, 50, 3.5, false},
        {fastswim, v_avatar.swim, 16, 30, 0.64999997616, false},
        {fastdive, v_avatar.dive, 16, 30, 1.27499997616, false},
        {ljump, v_avatar.jump, 16, 30, 1, false},
        {wdistance, v_avatar.wdis, 16, 50, 2, false},
        --// world
        {hcloud, v_world.hide_cloud, 4, 0, 1, false},
        {hwater, v_world.hide_water, 4, 16842752, 0, false},
        {huibutton, v_world.hide_ui, 4, 1, 0, false},
        {rportal, v_world.rev_portal, 4, 1, 0, false},
        {rbarrier, v_world.rev_barrier, 4, 505873376, 872415400, false},
        {rwind, v_world.rev_wind, 4, 505873376, 1847778369, false},
        {dscene, v_world.scene, 4, 1, 0, false},
        {abswax, v_world.absorb, 4, -721215457, 1409292620, false},
        {lightplayer, v_world.lightplayer, 4, 0, 1, false},
        {session, v_world.session, 4, -721215457, 889192680, false}
    }
    --// print(draw_maps(v_main.teleport, false))
end

function switchVar()
    local sw = {
        --// avatar
        {wenergy, v_avatar.energy, 16, 999, 0, true},
        {oxygen, v_avatar.oxygen, 16, 1, 1, true},
        {fakeafk, v_avatar.afk, 16, 999, 0, true},
        {fastrun, v_avatar.run, 16, 50, 3.5, false},
        {fastswim, v_avatar.swim, 16, 30, 0.64999997616, false},
        {fastdive, v_avatar.dive, 16, 30, 1.27499997616, false},
        {ljump, v_avatar.jump, 16, 30, 1, false},
        {wdistance, v_avatar.wdis, 16, 50, 2, false},
        --// world
        {hcloud, v_world.hide_cloud, 4, 0, 1, false},
        {hwater, v_world.hide_water, 4, 16842752, 0, false},
        {huibutton, v_world.hide_ui, 4, 1, 0, false},
        {rportal, v_world.rev_portal, 4, 1, 0, false},
        {rbarrier, v_world.rev_barrier, 4, 505873376, 872415400, false},
        {rwind, v_world.rev_wind, 4, 505873376, 1847778369, false},
        {dscene, v_world.scene, 4, 1, 0, false},
        {abswax, v_world.absorb, 4, -721215457, 1409292620, false},
        {lightplayer, v_world.lightplayer, 4, 0, 1, false},
        {session, v_world.session, 4, -721215457, 889192680, false}
    }
    return sw
end

function burn_wax(cnd, pln)
    local switchBurn = {}
    for i, v in ipairs(cnd) do
        table.insert(switchBurn, {address = v.address, flags = 16, value = 1})
    end
    for i, v in ipairs(pln) do
        if getvalues(v.address + 0x4, 4) == 0 then
            table.insert(switchBurn, {address = v.address, flags = 16, value = 0})
        end
    end
    gg.setValues(switchBurn)
end

if string.find(gg.getTargetInfo().packageName, "sky") ~= nil then
    local libso = gg.getRangesList(baselib)
    if #libso == 0 then
        os.exit(print(baselib .. " missing"))
    end
    libstate = libso[1].start;
    request_api(true)
else
    toast.error("wrong package")
    os.exit(print("wrong package"))
end
