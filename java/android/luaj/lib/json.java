package luaj.lib;

import java.util.Iterator;
import luaj.LuaTable;
import luaj.LuaValue;
import luaj.ap;
import org.json.JSONArray;
import org.json.JSONObject;

class json extends VarArgFunction {
    json() {
    }

    public ap a_(ap apVar) {
        try {
            return jsonTable(new JSONArray(apVar.r(1)));
        } catch (Exception e) {
            try {
                return jsonTable(new JSONObject(apVar.r(1)));
            } catch (Exception e2) {
                return LuaValue.m(new StringBuffer().append(new StringBuffer().append(e.toString()).append("\n\n").toString()).append(e2.toString()).toString());
            }
        }
    }

    public LuaValue jsonTable(Object obj) {
        if (obj instanceof LuaValue) {
            return (LuaValue) obj;
        }
        if (obj instanceof String) {
            return LuaValue.m(obj.toString());
        }
        if (obj instanceof Number) {
            return LuaValue.m(obj.toString()).H();
        }
        if (obj instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) obj;
            Iterator<String> keys = jSONObject.keys();
            LuaValue luaTable = new LuaTable();
            while (keys.hasNext()) {
                try {
                    String obj2 = keys.next().toString();
                    luaTable.b(obj2, jsonTable(jSONObject.get(obj2)));
                } catch (Exception e) {
                }
            }
            return luaTable;
        } else if (!(obj instanceof JSONArray)) {
            return obj == null ? LuaValue.u : LuaValue.m(obj.toString());
        } else {
            JSONArray jSONArray = (JSONArray) obj;
            LuaValue luaTable2 = new LuaTable();
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    luaTable2.b(i, jsonTable(jSONArray.get(i)));
                } catch (Exception e2) {
                }
            }
            return luaTable2;
        }
    }
}
