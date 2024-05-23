print([[
╭━━━╮━╮╭━╮╭━━━╮╭━╮╭━╮╭━━━╮╭━╮
┃┄┃┄┃┄╰╯╭╯┃┄┃┄┃╰╮╰╯╭╯┃┄┃┄┃┃┄┃
┃┄┄┄┃┄╭╮╰╮┃┄┄┄┃╭╯╭╮╰╮┃┄╭━╯┃┄╰━╮
╰━┻━╯━╯╰━╯╰━┻━╯╰━╯╰━╯╰━━━╯╰━━━╯
╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱
]])
--[[
":AvatarModule" = gg.getResults(1) 
":CollectibleBarn" = gg.getResults(3)
":PickupEmitterBarn" = gg.getResults(2) + 0x434

netease posx = nentity + 0xCBCDD64
netease ":StarFragmentBarn" = gg.getResults(2)
]]

--{x=-10.26539516449,y=489.33358764648,z=2.03506374359} rainbow floating island dayhub
--getCandle = fbarn + 0x1F0 == 1.0 (i * 0x1C0)

--
-- json.lua
--
-- Copyright (c) 2020 rxi
--
-- Permission is hereby granted, free of charge, to any person obtaining a copy of
-- this software and associated documentation files (the "Software"), to deal in
-- the Software without restriction, including without limitation the rights to
-- use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
-- of the Software, and to permit persons to whom the Software is furnished to do
-- so, subject to the following conditions:
--
-- The above copyright notice and this permission notice shall be included in all
-- copies or substantial portions of the Software.
--
-- THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
-- IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
-- FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
-- AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
-- LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
-- OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
-- SOFTWARE.

tickid = {
    {1444818677;2072642187};
    {1444818677;2072642168};
    {1444818677;2072642216};
    {1444818677;2072641657};
    {1444818677;2072641951};
}
home = {
     {"Candle Space", "CandleSpace"},
     {"Aviary Village", "MainStreet"},
}
dawn = {
    {"Isle Of Dawn", "Dawn"},
    {"Trials Cave", "DawnCave"},
    {"Water Trial", "Dawn_TrialsWater"},
    {"Earth Trial", "Dawn_TrialsEarth"},
    {"Air Trial", "Dawn_TrialsAir"},
    {"Fire Trial", "Dawn_TrialsFire"},
}
day = {
    {"Butterfly Field", "Prairie_ButterflyFields"},
    {"Bird Nest", "Prairie_NestAndKeeper"},
    {"Sanctuary Islands", "Prairie_Island"},
    {"Prairie Cave", "Prairie_Cave"},
    {"Wildlife Park","Prairie_WildLifePark"},
    {"Prairie Village", "Prairie_Village"},
    {"Orea Place", "DayHubCave"},
    {"Prairie Temple", "DayEnd"},
}
rain = {
    {"Hidden Forest", "Rain"},
    {"Forest Clearing", "RainForest"},
    {"Elevated Clearing", "RainShelter"},
    {"Forest Caves", "Rain_Cave"},
    {"Forest Boneyard", "RainMid"},
    {"Forest Temple", "RainEnd"},
    {"Treehouse", "Rain_BaseCamp"},
    {"Wind Paths", "Skyway"},
}
aviary = {
    {"Aviary Village", "MainStreet"},
    {"Aviary Shop", "MainStreet_ShopOutfits"},
    {"Spell Shop", "MainStreet_ShopSpells"},
    {"Flying Intro", "MainStreetFlyingIntro"},
    {"Shop Prop", "MainStreet_ShopProps"},
    {"Apartment", "MainStreet_Apartment"},
    {"Cinammon Cafe", "MainStreet_Cafe"},
    {"Sound Bath", "MainStreet_Soundbath"},
    {"Concerto", "MainStreet_ConcertHall"},
}
sunset = {
    {"Valley Triumph", "Sunset"},
    {"Valley Citadel", "Sunset_Citadel"},
    {"Valley Fly Race", "Sunset_FlyRace"},
    {"Valley Sky Race", "SunsetRace"},
    {"Valley Race End", "SunsetEnd"},
    {"Hermit Valley", "Sunset_YetiPark"},
    {"Dream Village", "SunsetVillage"},
    {"Dream Theater", "Sunset_Theater"},
    {"Music Shop", "SunsetVillage_MusicShop"},
    {"Colosseum", "SunsetColosseum"},
    {"Valley Temple", "SunsetEnd2"},
    {"Cinema", "Event_Cinema"},
}
dusk = {
    {"Wasteland Lobby", "DuskStart"},
    {"Golden Wasteland", "Dusk"},
    {"Abyss Area", "Dusk_Triangle"},
    {"Abyss End", "Dusk_TriangleEnd"},
    {"Graveyard", "DuskGraveyard"},
    {"Forgotten Ark", "DuskOasis"},
    {"Crab Fields", "Dusk_CrabField"},
    {"Battlefield", "DuskMid"},
    {"Wasteland Temple", "DuskEnd"},
}
night = {
    {"Vault Knowledge", "Night"},
    {"Vault 4th Floor", "Night2"},
    {"Night Shelter", "Night_Shelter"},
    {"Vault End", "NightEnd"},
    {"Vault Archive", "NightArchive"},
    {"Starlight Desert", "NightDesert"},
    {"Starlight Beach", "NightDesert_Beach"},
    {"Jar Cave", "Night_JarCave"},
    {"Infinite Desert", "Night_InfiniteDesert"},
    {"Painted World", "Night_PaintedWorld"},
}
other = {
    {"Tgc Office", "TGCOffice"},
    {"Prince Planets", "NightDesert_Planets"},
    {"Shattering Area", "StormEvent_VoidSpace"},
    {"Mischief Area", "Event_DaysOfMischief"},
    {"Nintendo Area", "Nintendo_CandleSpace"},
}
aurora = {
    {"Exhale In Hale", "Event_Arr_ExhaleInhale"},
    {"Eyes Of Child", "Event_Arr_EyesOfAChild"},
    {"Runaway", "Event_Arr_Runaway"},
    {"Soft Inside", "Event_Arr_SoftInside"},
    {"The Seed", "Event_Arr_TheSeed"},
    {"Warrior", "Event_Arr_Warrior"},
}
eye = {
    {"Eden Lobby", "StormStart"},
    {"Eden Middle", "Storm"},
    {"Eden Finale", "StormEnd"},
    {"Orbit Middle", "OrbitMid"},
    {"Orbit Finale", "OrbitEnd"},
    {"Heaven Area", "CandleSpaceEnd"},
    {"Credits End", "Credits"},
}
local json = { _version = "0.1.2" }
gg.require('101.1', 16142)
jsonsave = '/sdcard/Android/Akaxel'
settdir = jsonsave.. '/akx-setting.lua'
locatdir = jsonsave.. '/akx-shareloc.lua'
-------------------------------------------------------------------------------
-- Encode
-------------------------------------------------------------------------------

local encode

local escape_char_map = {
  [ "\\" ] = "\\",
  [ "\"" ] = "\"",
  [ "\b" ] = "b",
  [ "\f" ] = "f",
  [ "\n" ] = "n",
  [ "\r" ] = "r",
  [ "\t" ] = "t",
}

local escape_char_map_inv = { [ "/" ] = "/" }
for k, v in pairs(escape_char_map) do
  escape_char_map_inv[v] = k
end


local function escape_char(c)
  return "\\" .. (escape_char_map[c] or string.format("u%04x", c:byte()))
end


local function encode_nil(val)
  return "null"
end


local function encode_table(val, stack, ml)
  local res = {}
  stack = stack or {}

  -- Circular reference?
  if stack[val] then error("circular reference") end

  stack[val] = true

  if rawget(val, 1) ~= nil or next(val) == nil then
    -- Treat as array -- check keys are valid and it is not sparse
    local n = 0
    for k in pairs(val) do
      if type(k) ~= "number" then
        error("invalid table: mixed or invalid key types")
      end
      n = n + 1
    end
    if n ~= #val then
      error("invalid table: sparse array")
    end
    -- Encode
    for i, v in ipairs(val) do
      table.insert(res, encode(v, stack, ml + 1))
    end
    stack[val] = nil
    return "[" .. table.concat(res, ", ") .. "]"

  else
    -- Treat as an object
    for k, v in pairs(val) do
      if type(k) ~= "string" then
        error("invalid table: mixed or invalid key types")
      end
      table.insert(res, encode(k, stack, ml + 1) .. " : " .. encode(v, stack, ml + 1))
    end
    stack[val] = nil
    return "{\n"..string.rep("\t", ml) .. table.concat(res, ",\n"..string.rep('\t', ml)) ..'\n'..string.rep("\t", ml - 1).."}"
  end
end


local function encode_string(val)
  return '"' .. val:gsub('[%z\1-\31\\"]', escape_char) .. '"'
end


local function encode_number(val)
  -- Check for NaN, -inf and inf
  if val ~= val or val <= -math.huge or val >= math.huge then
    error("unexpected number value '" .. tostring(val) .. "'")
  end
  return string.format("%.14g", val)
end


local type_func_map = {
  [ "nil"     ] = encode_nil,
  [ "table"   ] = encode_table,
  [ "string"  ] = encode_string,
  [ "number"  ] = encode_number,
  [ "boolean" ] = tostring,
}


encode = function(val, stack, ml)
  local t = type(val)
  local f = type_func_map[t]
  if f then
    return f(val, stack, ml)
  end
  error("unexpected type '" .. t .. "'")
end


function json.encode(val)
  return ( encode(val, nil, 1) )
end


-------------------------------------------------------------------------------
-- Decode
-------------------------------------------------------------------------------

local parse

local function create_set(...)
  local res = {}
  for i = 1, select("#", ...) do
    res[ select(i, ...) ] = true
  end
  return res
end

local space_chars   = create_set(" ", "\t", "\r", "\n")
local delim_chars   = create_set(" ", "\t", "\r", "\n", "]", "}", ",")
local escape_chars  = create_set("\\", "/", '"', "b", "f", "n", "r", "t", "u")
local literals      = create_set("true", "false", "null")

local literal_map = {
  [ "true"  ] = true,
  [ "false" ] = false,
  [ "null"  ] = nil,
}


local function next_char(str, idx, set, negate)
  for i = idx, #str do
    if set[str:sub(i, i)] ~= negate then
      return i
    end
  end
  return #str + 1
end


local function decode_error(str, idx, msg)
  local line_count = 1
  local col_count = 1
  for i = 1, idx - 1 do
    col_count = col_count + 1
    if str:sub(i, i) == "\n" then
      line_count = line_count + 1
      col_count = 1
    end
  end
  error( string.format("%s at line %d col %d", msg, line_count, col_count) )
end


local function codepoint_to_utf8(n)
  -- http://scripts.sil.org/cms/scripts/page.php?site_id=nrsi&id=iws-appendixa
  local f = math.floor
  if n <= 0x7f then
    return string.char(n)
  elseif n <= 0x7ff then
    return string.char(f(n / 64) + 192, n % 64 + 128)
  elseif n <= 0xffff then
    return string.char(f(n / 4096) + 224, f(n % 4096 / 64) + 128, n % 64 + 128)
  elseif n <= 0x10ffff then
    return string.char(f(n / 262144) + 240, f(n % 262144 / 4096) + 128,
                       f(n % 4096 / 64) + 128, n % 64 + 128)
  end
  error( string.format("invalid unicode codepoint '%x'", n) )
end


local function parse_unicode_escape(s)
  local n1 = tonumber( s:sub(1, 4),  16 )
  local n2 = tonumber( s:sub(7, 10), 16 )
   -- Surrogate pair?
  if n2 then
    return codepoint_to_utf8((n1 - 0xd800) * 0x400 + (n2 - 0xdc00) + 0x10000)
  else
    return codepoint_to_utf8(n1)
  end
end


local function parse_string(str, i)
  local res = ""
  local j = i + 1
  local k = j

  while j <= #str do
    local x = str:byte(j)

    if x < 32 then
      decode_error(str, j, "control character in string")

    elseif x == 92 then -- `\`: Escape
      res = res .. str:sub(k, j - 1)
      j = j + 1
      local c = str:sub(j, j)
      if c == "u" then
        local hex = str:match("^[dD][89aAbB]%x%x\\u%x%x%x%x", j + 1)
                 or str:match("^%x%x%x%x", j + 1)
                 or decode_error(str, j - 1, "invalid unicode escape in string")
        res = res .. parse_unicode_escape(hex)
        j = j + #hex
      else
        if not escape_chars[c] then
          decode_error(str, j - 1, "invalid escape char '" .. c .. "' in string")
        end
        res = res .. escape_char_map_inv[c]
      end
      k = j + 1

    elseif x == 34 then -- `"`: End of string
      res = res .. str:sub(k, j - 1)
      return res, j + 1
    end

    j = j + 1
  end

  decode_error(str, i, "expected closing quote for string")
end


local function parse_number(str, i)
  local x = next_char(str, i, delim_chars)
  local s = str:sub(i, x - 1)
  local n = tonumber(s)
  if not n then
    decode_error(str, i, "invalid number '" .. s .. "'")
  end
  return n, x
end


local function parse_literal(str, i)
  local x = next_char(str, i, delim_chars)
  local word = str:sub(i, x - 1)
  if not literals[word] then
    decode_error(str, i, "invalid literal '" .. word .. "'")
  end
  return literal_map[word], x
end


local function parse_array(str, i)
  local res = {}
  local n = 1
  i = i + 1
  while 1 do
    local x
    i = next_char(str, i, space_chars, true)
    -- Empty / end of array?
    if str:sub(i, i) == "]" then
      i = i + 1
      break
    end
    -- Read token
    x, i = parse(str, i)
    res[n] = x
    n = n + 1
    -- Next token
    i = next_char(str, i, space_chars, true)
    local chr = str:sub(i, i)
    i = i + 1
    if chr == "]" then break end
    if chr ~= "," then decode_error(str, i, "expected ']' or ','") end
  end
  return res, i
end


local function parse_object(str, i)
  local res = {}
  i = i + 1
  while 1 do
    local key, val
    i = next_char(str, i, space_chars, true)
    -- Empty / end of object?
    if str:sub(i, i) == "}" then
      i = i + 1
      break
    end
    -- Read key
    if str:sub(i, i) ~= '"' then
      decode_error(str, i, "expected string for key")
    end
    key, i = parse(str, i)
    -- Read ':' delimiter
    i = next_char(str, i, space_chars, true)
    if str:sub(i, i) ~= ":" then
      decode_error(str, i, "expected ':' after key")
    end
    i = next_char(str, i + 1, space_chars, true)
    -- Read value
    val, i = parse(str, i)
    -- Set
    res[key] = val
    -- Next token
    i = next_char(str, i, space_chars, true)
    local chr = str:sub(i, i)
    i = i + 1
    if chr == "}" then break end
    if chr ~= "," then decode_error(str, i, "expected '}' or ','") end
  end
  return res, i
end


local char_func_map = {
  [ '"' ] = parse_string,
  [ "0" ] = parse_number,
  [ "1" ] = parse_number,
  [ "2" ] = parse_number,
  [ "3" ] = parse_number,
  [ "4" ] = parse_number,
  [ "5" ] = parse_number,
  [ "6" ] = parse_number,
  [ "7" ] = parse_number,
  [ "8" ] = parse_number,
  [ "9" ] = parse_number,
  [ "-" ] = parse_number,
  [ "t" ] = parse_literal,
  [ "f" ] = parse_literal,
  [ "n" ] = parse_literal,
  [ "[" ] = parse_array,
  [ "{" ] = parse_object,
}


parse = function(str, idx)
  local chr = str:sub(idx, idx)
  local f = char_func_map[chr]
  if f then
    return f(str, idx)
  end
  decode_error(str, idx, "unexpected character '" .. chr .. "'")
end


function json.decode(str)
  if type(str) ~= "string" then
    error("expected argument of type string, got " .. type(str))
  end
  local res, idx = parse(str, next_char(str, 1, space_chars, true))
  idx = next_char(str, idx, space_chars, true)
  if idx <= #str then
    decode_error(str, idx, "trailing garbage")
  end
  return res
end

-------------------------------------------------------------------------------
----- GG MainCode
-------------------------------------------------------------------------------
local author = "akaxel"
local url = "https://gameguardian.net/forum/profile/1389268-yorrue/"
local c = {}
local pointer = 0x00
local info = gg.getTargetInfo()
local codenum = 1.0
local pickid = nil
loc_ranges = 0
langcode = 44
cday = 0
on = " ◆ "
off = " ◇ "
switch = on
unl = off
ucl = off
scn = off
vcp = off
local current = {
    version = info.versionName,
    package = info.packageName
}
tgc = {
    live = "com.tgc.sky.android",
    beta = "com.tgc.sky.android.test.gold"
}
patern = {
    univ = "-3255450993", --//:UnnamedSocket hmm??
    daily = "0~1",
    embarn = "-768833570",
    avmod = "1.0",
    gtime = "1.0",
    barn = "1.0"
}
class = { 
    hud = ":HudLowRes",
    system = ":system_quality_high",
    avmod = ":AvatarModule",
    daily = ":CollectibleBarn",
    embarn = ":PickupEmitterBarn",
    gtime = ":GameTime",
    fbarn = ":FireworkBarn",
    level = ":Resources/Persistent.lua"
}
g = {
    version = info.versionName,
    pack = info.packageName,
    code = info.versionCode
}
offsetz = {
    autofarm = 0x00,
    daily = 0x00,
    avmod = 0x00,
    avawin = 0x00,
    gspd = 0x00,
    level = 0x00,
    plant = 0x00,
    candle = 0x00,
    cloth = 0x00,
    energy = 0x00,
    hud = 0x00,
    sheets = 0x00,
    friends = 0x00,
    cutscene = 0x00
}
settings = {
    crspeed = 1000,
    langdef = 'English'
}
saveset = {
    crspeed = 1000
}
shareloc = {
}

    getadd = function(add,flag)
    local a = {
    		[1] = {address = add, flags = flag}
    	}
    
    	b = gg.getValues(a)
	    return tonumber(b[1].value)
    end
    valset = function(address,flags,value)
    local n = {}
        n[1] = {}
        n[1].address = address
        n[1].flags = flags
        n[1].value = value
        gg.setValues(n)
    end
    freezeval = function(add, flags, val, frz)
        local n = {}
        n[1] = {}
        n[1].address = add
        n[1].flags = flags
        n[1].value = val
        n[1].freeze = frz
        if n[1].freeze == true then
            gg.addListItems(n)
            else
        gg.removeListItems(n)
        end
    end
    writeString = function(data)
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
    			table.insert(values, {address = _address + (i - 1), flags = gg.TYPE_BYTE, value = _bytes[i]})
    		end
    	  end
    	gg.setValues(values)
      end
    jumpToAddr = function(madr,fadr)
    local count = gg.getResultsCount()
        if gg.getResults(count) ~= 0 then
            gg.loadResults(madr)
            gg.searchPointer(fadr)
          else
            return gg.alert(text['fail'])
         end
      end
    doubleJump = function(ak,xl)
        --//Yip Yip
    end
    removeList = function()
        --//Time to clean up
    end
      
    maincode = function()
        gg.clearResults()
        local lib = gg.getRangesList("libBootloader.so")[1].start
        boot = {address=lib,flags=gg.TYPE_QWORD,name="bootloader"}
        bootloader = boot.address
        gg.clearResults()
        gg.setRanges(gg.REGION_C_ALLOC)
        gg.searchNumber("7,301,029,265,827,722,568", gg.TYPE_QWORD, false, gg.SIGN_EQUAL, 0, -1 ,1)
        if gg.getResultsCount() == 0 then
            gg.setRanges(gg.REGION_OTHER)
            gg.searchNumber("7,301,029,265,827,722,568", gg.TYPE_QWORD, false, gg.SIGN_EQUAL, 0, -1 ,1)
        end
        c.pointer = gg.getResults(1)[1].address
        gg.addListItems({{address=c.pointer,flags=gg.TYPE_QWORD,name="game"}})
        gg.clearResults()
        
        if gg.getListItems() == nil then
            pcall({ gg.toast(text['fail']), os.exit() })
        end          
      end
         
-------------------------------------------------------------------------------
----- Teleport Variable
-------------------------------------------------------------------------------

    getLevel = function()
    --//current maps
    cmap = c.pointer - jsonObj.offsets['level']
    local c = ""
    local c1 = ""
        for i = 0, 23 do
            c1 = getadd(cmap + i, gg.TYPE_BYTE)
    
            if c1 == 47 then
                break
            end
    
            c = c..string.char(c1)
        end
        return c
    end
    getCords = function()
    avatarpose = c.pointer + jsonObj.offsets['avmod']
    	local coords = {
    		z = avatarpose + 0x0,
    		y = avatarpose + 0x4,
    		x = avatarpose + 0x8
    	}
    	return coords
    end
    setpos = function(px,py,pz)
    local var = {
    		{
    			address = coords['x'],
    			flags = 16,
    			value = px
    		},
    		{
    			address = coords['y'],
    			flags = 16,
    			value = py
    		},
    		{
    			address = coords['z'],
    			flags = 16,
    			value = pz
    		}
    	}
    	gg.setValues(var)
    end
    getCrun = function()
        local array = {};
        local bool;
        local map = getLevel();
            for i, v in ipairs(crpoints) do
                if v.map == map then
                    bool = true;
                    table.insert(array, {v.x, v.y, v.z});
                end
            end
            return array, bool;
        end
        
    getnames = function()
        local user = {}
    
        for i, v in ipairs(akxloc) do
            table.insert(user, v.name)
        end
    
        return user
    end
    
    get_wings = function(map)
        local family = nil
    
        for i, v in ipairs(wingpos) do
            if v.map == map then
                family = v.family
                break
            end
        end
    
        return family
    end
    make_wings = function(map)
        local m = {}
    
        for i, v in ipairs(wingpos) do
            if v.map == map then
                table.insert(m, v)
            end
        end
    
        return m
    end
    get_statue = function(map)
        local family = nil
    
        for i, v in ipairs(statuepos) do
            if v.map == map then
                family = v.name
                break
            end
        end
    
        return family
    end
    find_statue = function(map)
        local m = {}
    
        for i, v in ipairs(statuepos) do
            if v.map == map then
                table.insert(m, v)
            end
        end
    
        return m
    end
    make_posit = function(map)
        local points = {}
    
        for i, v in ipairs(esplist) do
            if v.map == map then
                table.insert(points, v)
            end
        end
    
        return points
    end
    get_posit = function(list)
        local nm = {}
    
        for i, v in ipairs(list) do
            table.insert(nm, v.name)
        end
    
        return nm
    end
    get_cpos = function(list, name)
        for i, v in ipairs(list) do
            if v.name == name then
                return {x = v.x, y = v.y, z = v.z}
            end
        end
    
        return nil
    end
    getAction = function()
    	actionmenu = gg.choice({
    		"N",
    		"E",
    		"W",
    		"S"
    	}, nil, "action:")
    
    	if actionmenu == 1 then
    		return -1
    	elseif actionmenu == 2 then
    		return 1
    	elseif actionmenu == 3 then
    		return 0
    	elseif actionmenu == 4 then
    		return -999
    	end
    end
    DoPoints = function(points)
    	local b = false
    	local stopped = false
    	local bckp_values = gg.getValues({
    		{address = coords.x, flags = gg.TYPE_FLOAT},
    		{address = coords.y, flags = gg.TYPE_FLOAT},
    		{address = coords.z, flags = gg.TYPE_FLOAT},
    	})
    
    	local i = 1
    	local t = 0
    	local st = 250
    	local endt = 60000
    
    	while i <= #points do
    		--gg.toast(tostring(i).." / "..#points)	
    	
    		gg.setValues({
    			{address = coords['x'], flags = gg.TYPE_FLOAT, value = points[i].x},
    			{address = coords['y'], flags = gg.TYPE_FLOAT, value = points[i].y},
    			{address = coords['z'], flags = gg.TYPE_FLOAT, value = points[i].z}
    		})
    	
    		if not(points[i].e == true) and not(i == #points) then
    			while true do
    				if gg.isVisible(true) then
    					gg.setVisible(false)
    					a = 1
    					break
    				else
    					gg.sleep(st)
    					t = t + st
    
    					if t >= endt then
    						gg.toast("Manual mode")
    						break
    					end
    				end
    			end
    
    			while t >= endt do
    				if gg.isVisible(true) then
    					gg.setVisible(false)
    
    					a = getAction()
    
    					if a ~= nil then
    						break
    					end
    				end
    			end
    
    			if a == -999 then
    				gg.setValues(bckp_values)
    				i = #points + 1
    				stopped = true
    			elseif a == 0 then
    				stopped = true
    				i = #points + 1
    			else
    				i = i + a
    				if a == -1 then
    					i = clamp(i, 1, #points)
    					if points[i].e == true then
    						i = i - 1
    					end
    				end
    			end
    
    			t = 0
    		else
    			i = i + 1
    			gg.sleep(100)
    		end
    	end
    end
    leveljump = function(beacon)
        --real empty level -833E0
        --DoFashionToDusk
        --Ap17Intro - 0x1405
    	xar = {}
    	xtr = c.pointer + jsonObj.offsets['portal']
    	writeString({
    		{xtr + 0x39D0, beacon, 24},
    		{xtr + 0x39F0, "Black", 28},
    		{xtr + 0x39AC,gg.TYPE_DWORD,#beacon,false}
    	})
    	xar = {
    	
    		{address = xtr - 0x34, flags=gg.TYPE_QWORD,value=49},
    		{address = xtr - 0x30, flags=gg.TYPE_DWORD,value=0},
    	
    		{address = xtr - 0x6C, flags=gg.TYPE_FLOAT,value=80000},
    		{address = xtr - 0x6C+0x4, flags=gg.TYPE_FLOAT,value=80000},
    		{address = xtr - 0x6C+0xC, flags=gg.TYPE_FLOAT,value=80000},
    		--[[
    		{address = xtr - 0x80, flags=gg.TYPE_FLOAT,value=80000},  --0x80,
    		{address = xtr - 0x80+0x4, flags=gg.TYPE_FLOAT,value=80000},
    		{address = xtr - 0x80+0xC, flags=gg.TYPE_FLOAT,value=80000},
    		{address = xtr - 0x94, flags=gg.TYPE_FLOAT,value=80000},  --0x94,
    		{address = xtr - 0x94+0x4, flags=gg.TYPE_FLOAT,value=80000},
    		{address = xtr - 0x94+0xC, flags=gg.TYPE_FLOAT,value=80000},
    		{address = xtr - 0xA8, flags=gg.TYPE_FLOAT,value=80000},  --0xA8,
    		{address = xtr - 0xA8+0x4, flags=gg.TYPE_FLOAT,value=80000},
    		{address = xtr - 0xA8+0xC, flags=gg.TYPE_FLOAT,value=80000},
    	    ]]
    		{address = xtr - 0x2C, flags=gg.TYPE_DWORD,value=28},
    		{address = xtr - 0x24, flags=gg.TYPE_QWORD,value=xtr + 0x39D0},
    		{address = xtr + 0x39AC, flags = gg.TYPE_DWORD,value = #beacon},
    	
    		{address = xtr - 0x1C, flags=gg.TYPE_DWORD,value=49},
    		{address = xtr - 0x18, flags=gg.TYPE_DWORD,value=0},
    		{address = xtr - 0x14, flags=gg.TYPE_DWORD,value=10},
    		{address = xtr - 0x10, flags=gg.TYPE_DWORD,value=0},
    	
    		{address = xtr - 0xC, flags=gg.TYPE_QWORD,value=xtr+0x39F0},
    		{address = xtr, flags = gg.TYPE_DWORD,value = 666}
	    }
    	gg.setValues(xar)
    	gg.sleep(800)
    end
    

-------------------------------------------------------------------------------
----- Data Generated
-------------------------------------------------------------------------------

    stringtable = function (array)
        local str = " {\n"
        for i, v in pairs(array) do
            if type(i) == "string" then
                str = str .. "\t\t" .. i .. " ="
            end
            if type(v) == "table" then
                str = str .. stringtable(v)
            elseif type(v) == "boolean" then
                str = str .. " " .. tostring(v)
            elseif type(v) == 'string' then
                str = str .. " " .. "'" .. tostring(v) .. "'"
            else
                str =  str .. " " .. v
            end
            str = str .. ",\n"
        end
        if str ~= '' then
            str = string.sub(str, 1, string.len(str) - 1)
        end 
        str = str .. "\n}"
        return str
    end
    
    transtring = function(syn, str)
        dec, hex = syn, syn
        if syn == dec then
            dec = "%d"
          else
          if syn == hex then
            hex = "%x"
          end
      if str ~= '?' then
        string.format(syn, str)
       end
       return str
      end
    end
    
    newfolder = function(s)
        local s = s
        if s:sub(#s) ~= "/" then
            s = s .. "/"
          end
        s = s .. ".cache"
        gg.saveList(s)
        os.remove(s)
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
    
    dataDecode = function()
        dir = io.open('/sdcard/Android/Akaxel/akx-' .. title .. 'farm.json', 'r')
        if dir == nil then
            dataEncode()
           return
        end
        obj = dir:read("*a")
        jsonObj = {
            scripts = json.decode(obj)[1]['script'],
            specs = json.decode(obj)[1]['game'],
            offsets = json.decode(obj)[1]['offsets'],
            other = json.decode(obj)[1]['other']
        }
    end
    
    buttonToast = function(msg,num)
        local bool, int = pcall(load, msg)
        if bool then
            gg.toast(msg)
            gg.sleep(num)
          end
        end
    buttonExit = function(msg,num)
        local bool, int = pcall(load, msg)
        if bool then
            gg.toast(msg)
            gg.sleep(num)
            gg.setVisible(true)
            os.exit()
          end
        end
    buttonFunc = function(msg,fun,num)
        local a, c = pcall(load, msg)
        if a then
          gg.toast(msg)
          load(fun)
          gg.sleep(num)
        end
      end
        
    createfile = function()
        local file = io.open('/sdcard/Android/Akaxel/akx-setting.lua', 'w')
        file:write('--//file generated by akaxel')
        file:write("\nsettings = " ..stringtable(settings)..'\n--cache[')
        file:close()
      end
    
    stringbuild = function()
    local tbl = {}
        for i = 1, 1 do
            table.insert(tbl, {
                defs = {
                    code = string.format("%d", langcode),
                    link = "https://www.deepl.com/translator",
                }
            })
        end
        jsv = io.open('/sdcard/Android/Akaxel/langcode.json', 'w')
        jsv:write(json.encode(tbl))
        jsv:close()
      end
    
    stringdirect = function()
        local directory = '/sdcard/Android/Akaxel/langcode.json'
        check = io.open(directory)
        if check == nil then
            stringbuild()
          return stringdirect()
        end
        getlang = gg.makeRequest("https://raw.githubusercontent.com/frzzee/LazySky/main/language.json").content
        if not(getlang) then
            gg.setVisible(true)
            os.exit()
        else
        ccode = io.open(directory, 'r')
        rcode = ccode:read("*a")
        git_string = json.decode(getlang)
        code = json.decode(rcode)[1]['defs']
        end
        
        if code['code'] == "62" then
            text = git_string[1]['id']
        elseif code['code'] == "44" then
            text = git_string[1]['en']
        elseif code['code'] == "81" then
            text = git_string[1]['jp']
         end
       end
    
    savestring = function(save,file,str,new)
        local r = io.open(save, 'r'):read("*a")
        local h = io.open(file, 'w')
        h:write(string.gsub(r, str, new))
        h:close()
     end

    loadsave = function()
        local int = '/sdcard/Android/Akaxel/akx-setting.lua'
        local drc = io.open(int)
        if drc ~= nil then
            pcall(load(io.open(int, 'r'):read("*a")))
          else
          gg.toast(text['parse'])
        end
      end
        
    crsetting = function()
        locdir = '/sdcard/Android/Akaxel/akx-setting.lua'
        local sc = gg.prompt({text['interval'].." ⌥ 500 ~ 2000"},{settings.crspeed},{'number'})
        if sc ~= nil then
            if tonumber(sc[1]) > 2000 then
                settings.crspeed = 2000
                buttonToast(text['slow']..'?',800)
               return
             end
            if tonumber(sc[1]) < 500 then
                settings.crspeed = 500
                buttonToast(text['fast'],800)
              return
            end
          savestring(locdir,locdir,settings.crspeed,tonumber(sc[1]))
          cspd = tonumber(sc[1])
          settings.crspeed = cspd
            return docandlerun()
          else
          docandlerun()
        end
      end
    
-------------------------------------------------------------------------------
----- Main Function
-------------------------------------------------------------------------------

    getCandles = function()
    		local a = {}
            
    		local n = c.pointer + jsonObj.offsets['candle'] --0x1D4
    		for i=1, 680 do
    			table.insert(a, {address = n + (i - 1) * 0x1C0, flags = gg.TYPE_FLOAT, value = 0, name = 'cndl', freeze = false})
    		end
    
    		return a
        end
    getPlants = function()
    		local m = {}
    
    		for i=1, 998 do
    			m[i]= {address = c.pointer + jsonObj.offsets['plant'] + ((i - 1) * 8), flags = gg.TYPE_FLOAT}
    		end
    
    		local plants = gg.getValues(m)
    
    		return plants
    	end
    set_autoburn = function(b)
        candles = getCandles()
        plants = getPlants()
     
    		for i, v in ipairs(candles) do
    			v.value = 1.0
    			v.freeze = true
    		end
    		
    		gg.setValues(candles)
    		gg.addListItems(candles)
    
    		for i, v in ipairs(plants) do
    			v.value = 0.0
    			v.freeze = true
    		end
    
    		gg.setValues(plants)
    		gg.addListItems(plants)
        end    	
    dis_autoburn = function()
        candles = getCandles()
        plants = getPlants()
    	    
    		for i, v in ipairs(candles) do
    			v.value = 0.0
    			v.freeze = false
    		end
    		
    		gg.setValues(candles)
    		gg.removeListItems(candles)
    
    		for i, v in ipairs(plants) do
    			v.value = 1.0
    			v.freeze = false
    		end
    
    		gg.setValues(plants)
    		gg.removeListItems(plants)
    	end
    
    set_speed = function(speed)
        valset(c.pointer + jsonObj.offsets['speed'],gg.TYPE_FLOAT,speed,false)
    end  
    dogamespeed = function()
    if jsonObj.offsets['gspd'] == 0x00 then
        gg.toast(text['parse'])
     end
    local spd = getadd(c.pointer + jsonObj.offsets['speed'],gg.TYPE_FLOAT)
    local speed = gg.prompt({text['speedhack'].." ⌥ [1;20]"},{spd},{"number"})
        if speed ~= nil then
            set_speed(speed[1])
        else
            return startMain()
        end
    end
    doautofarm = function()
    if jsonObj.offsets['autofarm'] == 0x00 then
        gg.toast(text['parse'])
     end
    local exc = gg.alert(text['farm'],''..text['yes'],''..text['no'])
        if exc == nil then
          return
        end
        if exc == 1 then            
            gg.toast(text['icon'])
            gg.sleep(800)
            sc = 0
            set_speed(90)
            dtaddr = c.pointer + jsonObj.offsets['embarn']
            local time = os.time()
            for i,v in pairs(pickupid) do
                if gg.isVisible(true) then
                    gg.setVisible(false)
                    sc = 1               
                    break
                else
                set_speed(90)
                gg.setValues(
                    {
                        {   
                            address = dtaddr,
                            flags = 4,
                            value = v[1]
                        }
                    })
                for k = 0, (#v - 1) / 32 do
                    local sendid = {}
                    for j = 1, 32 do
                    if v[j + (1 + k*32)] ~= nil then
                        sendid[j] = {
                            address = dtaddr + j*4,
                            flags = 4,
                            value = v[j + (1 + k*32)]
                        }
                        else
                            break --//hentikan pengisian jika 32 Id kosong
                        end
                    end
                    sendid[33] = {
                        address = dtaddr + 33 * 4,
                        flags = 4,
                        value = #sendid
                    }
                    gg.setValues(sendid) --⎅
                    gg.toast("⎗ "..i.." / "..#pickupid.." | "..((k+1)*100/((#v-1)/32+1)).." % ⎘")
                    while gg.getValues(sendid)[33].value ~= 0 do
                    end
                  end                
                end
              set_speed(1)            
              if sc == 1 then
              gg.toast(text['time'].. " ~ " ..os.time() - time..' '..text['sec']..' ⏱')
            end
          end
        gg.toast(text['time'].. " ~ " ..os.time() - time..' '..text['sec']..' ⏱')
      end
      if exc == 2 then
         return startMain()
       end
     end
     
    doeventbarn = function(options)
    if jsonObj.offsets['autofarm'] == 0x00 then
        gg.toast(text['parse'])
     end
    local event = gg.alert(text['cticket'],''..text['yes'],''..text['no'])
    if event == 1 then
        local time = os.time()
        set_speed(90)
        ev = 0
        dtaddr = c.pointer + jsonObj.offsets['embarn']
        for i, v in pairs(tickid) do
           if gg.isVisible(true) then
             gg.setVisible(false)
             ev = 1
            break
           else
           set_speed(90)
           gg.setValues({{address=dtaddr,flags=4,value=v[1]}})
           for k = 0, (#v -1)/32 do
               local input = {}
               for g = 1, 32 do
               if v[ g + (1 + k*32)] ~= nil then
                  input[g] = {
                      address = dtaddr + g*4,
                      flags = 4,
                      value = v[ g + (1 + k*32)]
                  }
                  else
                break
              end
            end
           input[33] = {
              address = dtaddr + 33*4,
              flags = 4,
              value = #input
           }
           gg.setValues(input)
           gg.toast("⎗ "..i.." / "..#tickid.." ⎘")
           while gg.getValues(input)[33].value ~= 0 do
              end
            end
          end
        set_speed(1)
        if ev == 1 then
          gg.toast(text['time'].. " ~ " ..os.time() - time..' '..text['sec']..' ⏱')
          gg.sleep(800)
        end
       end
       gg.toast(text['time'].." ~ " ..os.time() - time..' '..text['sec']..' ⏱')
       gg.sleep(800)
      end
    if event == 2 then
        startMain();
       return
     end
    end
    
    dodailyquest = function()
    if jsonObj.offsets['daily'] == 0x00 then
        gg.toast(text['parse'])
    end
        cquest = string.format("%d", jsonObj.other['daycount'])
        local quest = gg.alert(text['dday'],''..text['yes'],''..text['no'])
            if quest == nil then
                return
            end
            if quest == 1 then
                gg.toast(text['loading']..'...')
                gg.sleep(1500)
                num = cquest
                rwjg = num * 8 + 12
                rwjg2 = rwjg + num * 4
                mrrwid = {}
                mrrw = c.pointer + jsonObj.offsets['daily']
                for i = 1, num do
                    mrrwid[i] = i
                end
                local tmp2 = {}
                for i = 1, num do
                    tmp2[i] = { address = mrrw + 4 + (mrrwid[i] - 1) * 8, flags = 16 }
                end
                tmp2 = gg.getValues(tmp2)
                for i = 1, num do
                    tmp2[i].value = tmp2[i].value + 60
                end
                gg.setValues(tmp2)
                tmp2 = {}
                for v = 1, num do
                    tmp2[v] = { address = mrrw + rwjg + 4 * v, flags = 4, value = mrrwid[v] }
                end
                gg.setValues(tmp2)
                local tmp3 = { { address = mrrw + rwjg2, flags = 4, value = num } }
                gg.setValues(tmp3)
                gg.toast(text['comp'])
                gg.sleep(800)
                gg.toast(text['accept'])
              end
            if quest == 2 then
                return startMain()
              end
            end
            
    docandlerun = function()
    if jsonObj.offsets['avmod'] == 0x00 or jsonObj.offsets['candle'] == 0x00 or jsonObj.offsets['plant'] == 0x00 then
        gg.toast(text['parse'])
      end
    local can = gg.alert(text['crun'],''..text['start'].." ⌲","⌨ "..text['setting'])
        if can == nil then
          return
        end
        if can == 2 then
            crsetting()
          end
        if can == 1 then
            set_autoburn(true)
            gg.toast(text['icon'])
            gg.sleep(800)
        	local cords, bool = getCrun();
            if bool then
                for i, v in ipairs(cords) do
        			if gg.isVisible(true) then
        				break;
        			end
                    gg.setValues({
                    {
                        address = coords.x,
                        flags = gg.TYPE_FLOAT,
                        value = v[1],
                    },
                    {
                        address = coords.y,
                        flags = gg.TYPE_FLOAT,
                        value = v[2],
                    },
                    {
                        address = coords.z,
                        flags = gg.TYPE_FLOAT,
                        value = v[3],
                    },
                });
            gg.sleep(settings.crspeed);        
           end
            gg.toast(text['clear'])
            dis_autoburn(true)
           else
            gg.toast(text['route'])
            dis_autoburn(true)
          end
        end
      end
    
    dowingrun = function()
    if jsonObj.offsets['avmod'] == 0x00 then
        gg.toast(text['parse'])
       end
    local wr = gg.alert(text['wrun'],''..text['start'])
    if wr == nil then
        return
    end
        if wr == 1 then
    	local map = getLevel()
    	local wmp = get_wings(map)
    
    	if wmp ~= nil then
    	    if gg.toast(text['click']) == gg.sleep(1000) then
    			DoPoints(make_wings(map))
    		else
    			return
    		end
    		    gg.toast(text['clear'])
    	    else
    			gg.toast(text['route'])
    		end
    	  end
        end
        
    doninjaeden = function()
    if jsonObj.offsets['avmod'] == 0x00 or jsonObj.offsets['avawin'] == 0x00 then
        gg.toast(text['parse'])
        return
      end
    if getadd(c.pointer + jsonObj.offsets['energy'], gg.TYPE_DWORD) ~= 505794560 then
        unl = on
        valset(c.pointer + jsonObj.offsets['energy'], gg.TYPE_DWORD, 505794560)
    end
        local map = getLevel()
        local stat = get_statue(map)
        local wing = getadd(c.pointer + jsonObj.offsets['avawin'],gg.TYPE_DWORD)
        
        if stat ~= nil then
            if wing ~= 0 then
                buttonToast(text['click'],1000)
                DoPoints(find_statue(map))
            else
            buttonToast(text['dead'],800)
          end
        unl = off
        valset(c.pointer + jsonObj.offsets['energy'], gg.TYPE_DWORD, 505571328)
        gg.toast(text['clear'])
        else
        gg.toast(text['route'])
      end
    end
    
    optionslevel = function()
        local tbl = {}
        local arr = {}
        gate = {
            {text['home'], home},
            {text['aviar'], aviary},
            {text['isle'], dawn},
            {text['prai'], day},
            {text['rain'], rain},
            {text['suns'], sunset},
            {text['dusk'], dusk},
            {text['vault'], night},
            {text['eye'], eye},           
            {text['aurora'], aurora},
            {text['other'], other},
        }
        for i, v in ipairs(gate) do
            table.insert(tbl, v[1])
            table.insert(arr, v[2])
        end
         local mps = gg.choice(tbl, nil, text['maps'])
         if mps ~= nil then
            local point = {}
            local core = {}
            for i, v in ipairs(arr[mps]) do
                table.insert(point, v[1])
                table.insert(core, v[2])
             end
            local name = gg.choice(point, nil, text['beacon'])
            if name ~= nil then
                if core[name] == getLevel() then
                    gg.toast("You already at its place")
                  return
                end
                leveljump(core[name])
                gg.sleep(800)
                if getLevel() == core[name] then
                    gg.toast('✈️ '..point[name])
                  else
                  gg.toast('⚠️ '..text['fail'])
                  gg.sleep(800)
                  gg.alert(text['wrongplace'])
                end
              else
                return optionslevel()
              end
            else
              return startMain()
            end
          end
    
    dumplocat = function()
        local dm = {}
        for i = 1, 5 do
            table.insert(dm, {
                map = "getmap",
                name = "empty",
                x = "px",
                y = "py",
                z = "pz"
            })
        end
        dc = io.open(locatdir, 'w')
        dc:write("akxloc = "..stringtable(dm)..'\n\n--')
        dc:close()
      end
    
    dumplocat2 = function()
        local dm = {}
        akx = io.open(locatdir, 'r'):read("*a")
        pcall(load(akx))
    
        if string.find(akx, 'empty') == tonumber(26) then
            buttonToast(text['nosave'],800)
           return playercoords();
        end
    
        for i, v in ipairs(akxloc) do
            for name in akx(not 'empty') do
            for i = 1, 1 do
                table.insert(dw, {
                coords = {
                    map = v.map,
                    name = v.name,
                    x = v.x,
                    y = v.y,
                    z = v.z
                }})
                end
            end
        end
        date = os.date("*t")
        file = '['..date['hour']..'.'..date['min']..']akx-savelocat.json'
        download = '/sdcard/Download'
        local promp = gg.prompt({text['dsave']}, {download}, {"path"})
        
            if promp ~= nil then
                if io.open(promp[1]) == nil then
                    buttonToast(text['fail'],800)
                  return promp
                end
                dnd = io.open(promp[1]..'/'..file, 'w')
                dnd:write(json.encode(dw))
                dnd:close()
                buttonToast(text['done'],800)
                gg.alert(text['saveto'] .. ' : \n\n' .. promp[1] .. '/' .. file)
              return playercoords();
            else
              return playercoords();
           end   
        end
    
    dumpcoords = function()
    local dw = {}
    akx = io.open(locatdir, 'r'):read("*a")
    pcall(load(akx))
    
    if string.find(akx, 'empty') == tonumber(26) then
        buttonToast(text['nosave'],800)
       return playercoords();
    end
    
    for i, v in ipairs(akxloc) do
        for i = 1, 1 do
            table.insert(dw, {
            coords = {
                map = v.map,
                name = v.name,
                x = v.x,
                y = v.y,
                z = v.z
            }})
        end
    end
    date = os.date("*t")
    file = '['..date['hour']..'.'..date['min']..']akx-savelocat.json'
    download = '/sdcard/Download'
    local promp = gg.prompt({text['dsave']}, {download}, {"path"})
    
        if promp ~= nil then
            if io.open(promp[1]) == nil then
                buttonToast(text['fail'],800)
              return promp
            end
            dnd = io.open(promp[1]..'/'..file, 'w')
            dnd:write(json.encode(dw))
            dnd:close()
            buttonToast(text['done'],800)
            gg.alert(text['saveto'] .. ' : \n\n' .. promp[1] .. '/' .. file)
          return playercoords();
        else
          return playercoords();
       end   
    end

savepos = {
    map = function()
    local gmp = getLevel()
    local mp = io.open(locatdir, 'r')
        gm = mp:read("*a")
        pcall(load(gm))
        sm = io.open(locatdir, 'w')
        sm:write(string.gsub(gm, 'getmap', gmp, 1))
        sm:close()
    end,
    cx = function()
    curx = getadd(coords['x'], gg.TYPE_FLOAT)
    local ax = io.open(locatdir, 'r')
        bx = ax:read("*a")
        pcall(load(bx))
        dx = io.open(locatdir, 'w')
        dx:write(string.gsub(bx, 'px', curx, 1))
        dx:close()
    end,
    cy = function()
    cury = getadd(coords['y'], gg.TYPE_FLOAT)
    local ay = io.open(locatdir, 'r')
        by = ay:read("*a")
        pcall(load(by))
        dy = io.open(locatdir, 'w')
        dy:write(string.gsub(by, 'py', cury, 1))
        dy:close()
    end,
    cz = function()
    curz = getadd(coords['z'], gg.TYPE_FLOAT)
    local az = io.open(locatdir, 'r')
        bz = az:read("*a")
        pcall(load(bz))
        dz = io.open(locatdir, 'w')
        dz:write(string.gsub(bz, 'pz', curz, 1))
        dz:close()
    end
}
    
    currpos = function()
    local p = ""
    val = gg.getValues({
    		{address = coords['x'], flags = 16},
    		{address = coords['y'], flags = 16},
    		{address = coords['z'], flags = 16},
    	})
    
    	crds = {
    		x = tostring(val[1].value),
    		y = tostring(val[2].value),
    		z = tostring(val[3].value)
    	}
    	p = p..''..text['maps'].." : "..getLevel()..'\n\n'
        p = p..''..text['posX']..' : '..crds.x..'\n\n'
        p = p..''..text['posY']..' : '..crds.y..'\n\n'
        p = p..''..text['posZ']..' : '..crds.z..'\n\n'
        local pos = gg.alert(p, text['txcopy'], text['save'])
            if pos == 1 then
                gg.copyText(getLevel().." = " .. stringtable(crds))
            elseif pos == 2 then
            local upt = gg.prompt({text['cname']},{'names'},{'text'})
            if upt ~= nil then
                if io.open(locatdir) == nil then
                    dumplocat();
                 end
                sloc = io.open(locatdir, 'r'):read("*a")
                pcall(load(sloc))
                if string.find(sloc, 'empty') == nil then
                    buttonToast(text['sfull'],800)
                  return playercoords();
                end
                saving = io.open(locatdir, 'w')
                saving:write(string.gsub(sloc, 'empty', upt[1], 1))
                saving:close()
                savepos.map()
                savepos.cx()
                savepos.cy()
                savepos.cz()
                gg.toast(text['saved'])
            else
              return playercoords();
            end
          end
        end
        
    usercoord = function(arr, name)
        for i, v in ipairs(akxloc) do
            if v.name == name then
                return {x = v.x, y = v.y, z = v.z}
            end
        end
    end
    
    localpos2 = function()
    local gtbl = {}
    local ptbl = {}
    if io.open(locatdir) == nil then
        dumplocat();
     end
    pcall(load(io.open(locatdir, 'r'):read("*a")))
    
    for i, v in ipairs(akxloc) do
        table.insert(gtbl, v.name)
        table.insert(ptbl, {v.x, v.y, v.z})
    end
    local chc = gg.choice(gtbl, nil, text['slist'])
        if chc ~= nil then
            if gtbl[chc] == 'empty' then
                gg.toast(text['empty'])
              return chc
            end
            gt = getnames(akxloc)
            pos = usercoord(gt, gtbl[chc])
            setpos(pos.x,pos.y,pos.z)
            gg.toast('✈️ '..gtbl[chc])
        else
          return playercoords();
       end
    end
    
    localpos = function()
    local gtbl = {}
    local gmps = {}
    local ptbl = {}
    if io.open(locatdir) == nil then
        dumplocat();
     end
    pcall(load(io.open(locatdir, 'r'):read("*a")))
    
    for i, v in ipairs(akxloc) do
        table.insert(gtbl, v.name)
        table.insert(gmps, v.map)
        table.insert(ptbl, {v.x, v.y, v.z})
    end
    local chc = gg.choice(gtbl, nil,''..text['svcoord'])
        if chc ~= nil then
            if gtbl[chc] == 'empty' then
                gg.toast(text['empty'])
              return chc
            end
            if getLevel() ~= gmps[chc] then
                gg.toast(text['nothis'])
              return
            end
            gt = getnames(akxloc)
            pos = usercoord(gt, gtbl[chc])
            setpos(pos.x,pos.y,pos.z)
            gg.toast('✈️ '..gtbl[chc])
        else
          return playercoords();
       end
    end
    
    
    inputcoord = function()
    val = {
        {address = coords['x'],flags = 16},
        {address = coords['y'],flags = 16},
        {address = coords['z'],flags = 16}
    }
        pos = {
            x = gg.getValues(val)[1].value,
            y = gg.getValues(val)[2].value,
            z = gg.getValues(val)[3].value
        }
        local prompt = gg.prompt(
            {text['posX']..' : ',text['posY']..' : ',text['posZ']..' : '},
            {pos.x,pos.y,pos.z},
            {"text","text","text"}
        )
            if prompt ~= nil then
                setpos(prompt[3],prompt[2],prompt[1])
                gg.toast('✈️ ' ..text['jump'])
            else
            return playercoords();
        end
    end
    
    portalskip = function()
        local go = gg.prompt({text['cname']..' : '}, {getLevel()}, {'text'})
        if go ~= nil then
            leveljump(go[1])
            gg.sleep(800)
            if getLevel() == go[1] then
                gg.toast('✈️ '..go[1])
              else
            gg.toast('⚠️ '..text['wrongplace'])
            end
          else
           return playercoords();
        end
      end
        
    
    playercoords = function()
    local pc = gg.choice({
        sign..''..text['curpos'],
        sign..''..text['incoord'],
        --sign..''..text['lvcoord'],
        sign..''..text['svcoord'],
        sign..''..text['reset'],
        sign..''..text['dwnl'],
        "➥ "..text['back']},
        nil,text['coords'])
        if pc == nil then
        while true do
            if gg.isVisible() then
                gg.setVisible(false)
              break
            end
          end
           return playercoords();
        end
        if pc == 1 then
            currpos();
        elseif pc == 2 then
            inputcoord();
            --[[
        elseif pc == 3 then
            portalskip();]]
        elseif pc == 3 then
            localpos();
        elseif pc == 4 then
            dumplocat()
            buttonToast(text['done'],500);
        elseif pc == 5 then
            dumpcoords();
        elseif pc == 6 then
            return startMain();
          end
        end
    
    configuration = function()
        gg.toast(text['loading']..'...')
        maincode();
        if jsonObj.offsets['viscape'] ~= 0x0 then
            if getadd(c.pointer + jsonObj.offsets['viscape'], gg.TYPE_FLOAT) == -1 then
                vcp = on
              else
               vcp = off
            end
          else
          gg.toast(text['viscape']..' : '..text['fail'])
        end
        if jsonObj.offsets['cloth'] ~= 0x0 then
            if getadd(bootloader + jsonObj.offsets['cloth'], gg.TYPE_DWORD) == 1384120352 then
                ucl = on
              else
               ucl = off
            end
          else
          gg.toast(text['clothes']..' : '..text['fail'])
        end
        if jsonObj.offsets['energy'] ~= 0x0 then
            if getadd(bootloader + jsonObj.offsets['energy'], gg.TYPE_DWORD) == 505794560 then
                unl = on
              else
               unl = off
            end
          else
          gg.toast(text['energy']..' : '..text['fail'])
        end
        if jsonObj.offsets['cutscene'] ~= 0x0 then
            if getadd(bootloader + jsonObj.offsets['cutscene'], gg.TYPE_DWORD) == 1 then
               scn = on
              else
               scn = off
            end
          else
          gg.toast("Skip Cutscene : " ..text['fail'])
        end
      end
    
        
-------------------------------------------------------------------------------
----- User Interface
-------------------------------------------------------------------------------
    
    dataGene = function()
        txt = ""
        txt = txt..'⊚ '..text['data'].." :\n\n"
        txt = txt..''..text['target'].." : " .. info.label .. " " ..info.versionName .."\n"
        txt = txt..''..text['pack'].." : " .. info.packageName .. "\n"
        local new = gg.alert(txt,''..text['continue']..'','',''..text['close'])
           if new == 1 then
             dataUpdate();
           elseif new == 3 then
           return os.exit();
         end
       end
        
    configUpdate = function()
        local time = os.time()
        notif = ""
        notif = notif..''..text['game'].." : " ..info.label..' '..info.versionName.. "\n\n"
        notif = notif..''..text['exp']
            local alert = gg.alert(notif, "〄 "..text['update'],'',"⍉ "..text['close'])
            if alert == 1 then
               dataUpdate();
               --buttonToast(text['comp'],500);
            elseif alert == 3 then
              os.exit();
            end
          end
          
    updater = function()
        local frzzee = "https://raw.githubusercontent.com/frzzee/LazySky/main/Updater.lua"
        local res = "https://gist.githubusercontent.com/frzzee/c3950868c0e18cbbef64df25e9c6f116/raw/1c78935ee1f1e3c0ce0591d1e6f56580a54c310b/resfarm.txt"
        local dc = "/sdcard/Android/Akaxel"
        
        if io.open(dc .. '/akx-setting.lua') == nil then
            createfile()
          else
          loadsave()
        end
        if io.open(dc .. '/akx-' .. title ..'farm.json') == nil then
            txt = ""
            txt = txt..''..text['target'].." : " .. info.label .. " " ..info.versionName .."\n"
            txt = txt..''..text['pack'].." : " .. info.packageName .. "\n"
            local gen = gg.alert(txt,''..text['continue']..'','',''..text['close'])
            if gen == 1 then
                getup = gg.makeRequest(frzzee).content
                if not(getup) then
                    gg.setVisible(true)
                    os.exit()
                  else
                pcall(load(getup))
                end
            elseif gen == 3 then
                pcall({gg.setVisible(true), os.exit()})
            end
        dataUpdate();
        end
        dataDecode(true)
        if info.versionName ~= jsonObj.specs['version'] then
            notif = ""
            notif = notif..''..text['game'].." : " ..info.label..' '..info.versionName.. "\n\n"
            notif = notif..''..text['exp']
            local alert = gg.alert(notif, "〄 "..text['update'],'',"⍉ "..text['close'])
            if alert == 1 then
               pcall(load(gg.makeRequest(frzzee).content))
               dataUpdate()
            elseif alert == 3 then
              os.exit();
            end
          end
        sources = gg.makeRequest(res).content
        pcall(load(sources))
        gg.alert(''.. info.label .. ' ' .. info.versionName .. '\n\n' .. info.packageName .. '')
        configuration();
        coords = getCords();
      end

    
    tableinfo = function()
      if jsonObj.specs['version'] == info.versionName then
            curversion = text['vers']
          else
          curversion = text['needup']
        end
      if jsonObj.scripts['author'] ~= author then
            gg.alert('why you trying to change my name? ☻')
          end
      local txt = ""
      txt = txt..""..text['curgame'].." : " .. info.label .. ' ['..title..']\n'
      txt = txt.."━━━━━━━━━━━━━━━━\n"
      txt = txt..""..text['title'].." : " .. jsonObj.scripts['label'] .. '\n'
      txt = txt..""..text['stat'].." : " .. curversion ..'\n'
      txt = txt..""..text['last'].." : " .. jsonObj.scripts['modified'] .. '\n'
      txt = txt..""..text['author'].." : ".. author .. '\n'
      txt = txt..""..text['url'].." : ".. url .."\n\n"
        local alert = gg.alert(txt, '⎙ '..text['copy'],'〄 '..text['update'],'➥ '..text['back'])
          if alert == 1 then
            gg.copyText(url)
            gg.sleep(800)
            return tableinfo();
          elseif alert == 2 then
            if jsonObj.specs['version'] == info.versionName then
                buttonToast(text['avail'],800)
              return tableinfo();
            end
            pcall(dataUpdate);
          elseif alert == 3 then
            startMain();
          end
        end
 
    languages = function()
        local ch = gg.choice({
            sign.."English",
            sign.."Indonesia",
            sign.."Japan",
            "➥ "..text['back']},
            nil,''..text['lang'])
           if ch == nil then
              return mainsetting()
           end
           if ch == 1 then
              langcode = '44'
              stringbuild() stringdirect()
              buttonToast(text['applied'],800)
            return languages()
           elseif ch == 2 then
              langcode = '62'
              stringbuild() stringdirect()
              buttonToast(text['applied'],800)
            return languages()
           elseif ch == 3 then
              langcode = '81'
              stringbuild() stringdirect()
              buttonToast(text['applied'],800)
            return languages()
           elseif ch == 4 then
              return mainsetting();
           end
        end
        
    mainsetting = function()
    local ms = gg.choice({
        sign..''..text['lang'],
        unl..''..text['unenergy'],
        ucl..''..text['clothes'],
        vcp..''..text['viscape'],
        scn.."Skip Cutscene",
        --fhm..''..text['fhome'],
        "➥ "..text['back']},nil,''..text['setting'])
        if ms == nil then
        while true do
            if gg.isVisible() then
                gg.setVisible(false)
              break
            end
          end
           return mainsetting();
        end
        if ms == 1 then
            languages();
        elseif ms == 2 then
            if jsonObj.offsets['energy'] == 0x00 then
                gg.toast(text['fail'])
              return ms
            end
            if unl == on then
                unl = off
                valset(bootloader + jsonObj.offsets['energy'], gg.TYPE_DWORD, 505571328)
                gg.toast(text['off'])
                else
                unl = on
                valset(bootloader + jsonObj.offsets['energy'], gg.TYPE_DWORD, 505794560)
                gg.toast(text['on'])
            end;
        elseif ms == 3 then
            if jsonObj.offsets['cloth'] == 0x00 then
                gg.toast(text['fail'])
              return ms
            end
            if ucl == on then
                ucl = off
                valset(bootloader + jsonObj.offsets['cloth'], gg.TYPE_DWORD, 446629856)
                gg.toast(text['off'])
                else
                ucl = on
                valset(bootloader + jsonObj.offsets['cloth'], gg.TYPE_DWORD, 1384120352)
                gg.toast(text['on'])
            end;
        elseif ms == 4 then
            if jsonObj.offsets['viscape'] == 0x00 then
                gg.toast(text['fail'])
              return ms
            end
            if vcp == on then
                vcp = off
                freezeval(c.pointer + jsonObj.offsets['viscape'], gg.TYPE_FLOAT, 1.0, false)
                gg.toast(text['off'])
                else
                vcp = on
                freezeval(c.pointer + jsonObj.offsets['viscape'], gg.TYPE_FLOAT, -1, true)
                gg.toast(text['on'])
            end;
        elseif ms == 5 then
            if jsonObj.offsets['cutscene'] == 0x00 then
                gg.toast(text['fail'])
              return ms
            end
            if scn == on then
                scn = off
                valset(bootloader + jsonObj.offsets['cutscene'], gg.TYPE_DWORD, 0)
                gg.toast(text['off'])
                else
                scn = on
                valset(bootloader + jsonObj.offsets['cutscene'], gg.TYPE_DWORD, 1)
                gg.toast(text['on'])
            end;
        elseif ms == 6 then
            return startMain();
        end
     end
     --[[
    smain = {
        main = {
            name = '[' .. info.label .. ']',
            selected = nil,
            buttons = {
                ButtonFun(' ⌂  ' .. text['home'], 
                ButtonMenu(sign .. '' .. text['teleport'], optionslevel),
                ButtonMenu(sign .. '' .. text['coords'], opsicoords),,
                ButtonFun(sign.. '' .. text['speedhack'], set_speed),
                ButtonFun(sign .. '' .. text['autofarm'], doautofarm),
                ButtonFun(sign .. '' .. text['daily'], dodailyquest),
                ButtonFun(sign .. '' .. text['ticket'], doeventbarn),
                ButtonFun(sign .. '' .. text['cnrun'], docandlerun),
                ButtonFun(sign .. '' .. text['wnrun'], dowingrun),
                ButtonFun(sign .. '' .. text['edenrun'], doninjaeden),
                ButtonMenu(sign .. '' .. text['setting'], opsimenu),
                ButtonFun(sign .. '' .. text['info'], tableinfo),
            }
        },
        opsicoords = {
            name = '[' .. text['coords'] .. ']',
            selected = true,
            buttons = {
            
        
    ]]
    startMain = function()
        local smain = gg.choice({
            --" ⌂  "..text['home'],            
            sign..''..text['speedhack'],
            sign..''..text['autofarm'],
            sign..''..text['daily'],
            sign..''..text['ticket'],
            sign..''..text['cnrun'],
            sign..''..text['wnrun'],
            sign..''..text['edenrun'],
            sign..''..text['teleport'],
            sign..''..text['coords'],            
            sign..''..text['setting'],
            sign..''..text['info'],
            "🔻 "..text['exit']},
            nil,"" .. jsonObj.specs['label'] .. " : " .. jsonObj.specs['version'] .. "")
            local acc = getadd(c.pointer + jsonObj.offsets['daily'],gg.TYPE_DWORD)
            if smain == nil then
                return
              end
              --[[
              if smain == 1 then
                if getLevel() == "CandleSpace" then
                    buttonToast(text['alhome'],800)
                  return
                end
                leveljump("CandleSpace")
                gg.sleep(800)
                 if getLevel() == "CandleSpace" then
                    gg.toast('✈️ '..text['home'])
                  else
                  gg.toast('⚠️ '..text['fail'])
                  gg.sleep(800)
                  gg.toast(text['wrongplace'])
                end;          ]]    
              if smain == 1 then
                dogamespeed();
              elseif smain == 2 then
                if acc == 0 then
                    buttonToast(text['login'],800)
                  return
                end
                doautofarm();
              elseif smain == 3 then
                if acc == 0 then
                    buttonToast(text['login'],800)
                  return
                end
                dodailyquest();
              elseif smain == 4 then
                if acc == 0 then
                    buttonToast(text['login'],800)
                  return
                end
                doeventbarn();
              elseif smain == 5 then
                if acc == 0 then
                    buttonToast(text['login'],800)
                  return
                end
                docandlerun();
              elseif smain == 6 then
                if acc == 0 then
                    buttonToast(text['login'],800)
                  return
                end
                dowingrun();
              elseif smain == 7 then
                if getLevel() ~= 'StormEnd' then
                    buttonToast(text['eden'],800)
                  return
                end
                doninjaeden();
              elseif smain == 8 then
                optionslevel();
              elseif smain == 9 then
                playercoords();
              elseif smain == 10 then
                mainsetting();
              elseif smain == 11 then                
                tableinfo();
              elseif smain == 12 then
              local tm = gg.getListItems()
                  if tm ~= nil then
                    gg.removeListItems(tm)
                  end
                buttonExit(text['exit'],0)
              end
            end

        gg.clearResults()
        gg.setVisible(false)
        if io.open('/sdcard/Android/Akaxel') == nil then
            newfolder('/sdcard/Android/Akaxel')
        end
        stringdirect(true);
        copyright = "LazyFarm_live"
        proc = "Live"
      
        if gg.getFile():match('[^/]+$') ~= copyright..'.lua' then
            pcall({gg.alert("Dont rename the file");gg.setVisible(true);os.exit()})
        end
        
        if current.package == nil then
            gg.alert(text['wrong'] .. " : \n\n" .. info.packageName .. "")
            gg.setVisible(true)
            os.exit()
          else
        if current.package == tgc.live then
            title = proc
            patern = {rng = 0x20000000, rgb = 0x00, ptr = 0x14D5}
            sign = "🔸 "
            updater()
            startMain()
          else
            gg.alert(text['wrong'] .. " : \n\n" .. info.packageName .. "")
            gg.setVisible(true)
            os.exit()
          end
        end
    
    
    ---//developer options menu
    bmain = function()
    dataDecode()
    gg.showUiButton()
    while(true)do
        if gg.isClickedUiButton() then
        local mn = gg.choice({"Daily Scan","Emitter Scan","Scan Coords","Scan Gtime","Get Pointer","Update","Exit"},nil,"" .. jsonObj.specs['label'] .. " : " .. jsonObj.specs['version'] .. "")
        if mn == nil then
            return bmain();
        end
        if mn == 1 then
            scanDaily();
        elseif mn == 2 then
            scanEmitter();
        elseif mn == 3 then
            avatarPos();
        elseif mn == 4 then
            scanGtime();
        elseif mn == 5 then
            getCpointer();
        elseif mn == 6 then
            configUpdate();
        elseif mn == 7 then
            gg.toast("Exit")
            os.exit();
            end
          end
        end
      end
    --artUpMain()
    while(true)do
    if gg.isVisible(true) then
        gg.setVisible(false)
        startMain()
       else
      end
    end
    
    if gg.getListItems() ~= nil then
        gg.removeListItems(gg.getListItems)
    end
   
   
   --[[
   
   pointer avatar -3,255,450,993
   :Resources/Persistent.lua
   h 6B 45 6E 61 62 6C 65 41 6C 6C 52 65 6C 61 74 69 6F 6E 73 68 69 70 41 62 69 6C 69 74 69 65 73
   h 6B
   
   avawing - 0xAC7C8 : Obtained Spirit
   aurora concert = concert_from_candlespace_noob * pointer + 0x3C
   ]]
        
        
        
        

        