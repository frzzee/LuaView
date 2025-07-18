textDes = [[
This script is still under development so if there are any errors like ui stuck, force close etc will be fixed later, sooner. (or maybe never cus I'm busy)

About version :
base game version "Sky 0.29.6".
support for other platforms like "Huawei" or other third party apps has not been tested.

® script by akaxell
]]

local ios = {
    wing = {
        gotoButton = function(text, ids, strings)
            return {
                LinearLayout;
                layout_height="25dp";
                layout_width="50dp";
                layout_marginEnd="5dp";
                layout_marginBottom="2dp";
                background="button.xml";
                id=ids;
                onClickListener=function()
                    local runnable = {
                        run = function()
                            gg.setValues({
                                {address = v_main.pos, flags = 16, value = getvalues(strings, 16)},
                                {address = v_main.pos + 0x4, flags = 16, value = getvalues(strings + 0x4, 16) + 0.5},
                                {address = v_main.pos + 0x8, flags = 16, value = getvalues(strings + 0x8, 16)}
                            })
                        end
                    }
                    rx.b(runnable)
                end;
                {
                    TextView;
                    layout_height="match_parent";
                    layout_width="match_parent";
                    text=text;
                    textSize="10sp";
                    gravity="center";
                };
            }
        end,
        bringButton = function(text, ids, strings)
            return {
                LinearLayout;
                layout_height="25dp";
                layout_width="50dp";
                layout_marginEnd="5dp";
                layout_marginBottom="2dp";
                background="button.xml";
                id=ids;
                onClickListener=function()
                    local runnable = {
                        run = function()
                            gg.setValues({
                                {address = strings, flags = 16, value = getvalues(v_main.pos, 16)},
                                {address = strings + 0x4, flags = 16, value = getvalues(v_main.pos + 0x4, 16) + 0.5},
                                {address = strings + 0x8, flags = 16, value = getvalues(v_main.pos + 0x8, 16)}
                            })
                        end
                    }
                    rx.b(runnable)
                end;
                {
                    TextView;
                    layout_height="match_parent";
                    layout_width="match_parent";
                    text=text;
                    textSize="10sp";
                    gravity="center";
                };
            }
        end,
        absorbWing = function(text, ids, strings)
            return {
                LinearLayout;
                layout_height="25dp";
                layout_width="50dp";
                layout_marginEnd="5dp";
                layout_marginBottom="2dp";
                background="button.xml";
                id=ids;
                onClickListener=function()
                    local runnable = {
                        run = function()
                            setvalues(strings, 4, 4)
                            setvalues(strings, 4, 8)
                        end
                    }
                    rx.b(runnable)
                end;
                {
                    TextView;
                    layout_height="match_parent";
                    layout_width="match_parent";
                    text=text;
                    textSize="10sp";
                    gravity="center";
                };
            }
        end
    },
    player = {
        sendrel = function(text, ids, strings, panel, mod, layout)
            return {
                LinearLayout;
                layout_height="25dp";
                layout_width="50dp";
                layout_marginEnd="5dp";
                layout_marginBottom="2dp";
                background="button.xml";
                id=ids;
                onClickListener=function()
                    local rvs = loadlayout(rpn)
                    panel.addView(rvs, layout)
                    mod.setVisibility(View.GONE)
                    
                    sendRelation = function(button, strings, values)
                        button.onClick = function(v, view)
                            local runnable = { run = function()
                                local senrels = getvalues(v_main.pos - 0x20, 32) + 0x260
                                local exec = {
                                    {address = senrels, flags = 4, value = values, freeze = true},
                                    {address = senrels + 0x4, flags = 4, value = getvalues(strings + 0x4D8F8, 4), freeze = true}
                                }
                                gg.addListItems(exec)
                                gg.setValues(exec)
                            end } rx.b(runnable)
                        end
                    end
                    
                    local button = {
                        {s_hug, 3}, {s_hug2, 8}, {s_hfive, 4}, {s_hfive2, 9}, {s_fbump, 5},
                        {s_fbump, 10}, {s_dbfive, 6}, {s_dbfive2, 11}, {s_pgback, 12},
                        {s_pgback2, 13}, {s_htousle, 14}, {s_htousle2, 15}, {s_pfight, 16},
                        {s_pfight2, 17}, {s_bearhug, 18}, {s_bearhug2, 19}, {s_ddance, 20},
                        {s_ddance2, 21}, {s_hshake, 22}, {s_hshake, 23}, {s_sdhug, 24},
                        {s_sdhug, 25}, {s_pcarry, 27}, {s_pcarry, 28}, {s_perform, 29},
                        {s_perform2, 30}
                    }
                    for i, v in ipairs(button) do
                        sendRelation(v[1], strings, v[2])
                    end
                    
                    relsback.onClick = function()
                        local runnable = { run = function()
                            local senrels = getvalues(v_main.pos - 0x20, 32) + 0x260
                            local svback = {
                                {address = senrels, flags = 4, value = 0, freeze = false},
                                {address = senrels + 0x4, flags = 4, value = 0, freeze = false}
                            }
                            gg.removeListItems(svback)
                            gg.setValues(svback)
                        end } rx.b(runnable)
                        panel.removeView(rvs)
                        mod.setVisibility(View.VISIBLE)
                        relsmod.setVisibility(View.GONE)
                    end
                end;
                {
                    TextView;
                    layout_height="match_parent";
                    layout_width="match_parent";
                    text=text;
                    textSize="10sp";
                    gravity="center";
                };
            } 
        end,
        reqrels = function(text, ids, strings, panel, mod, layout)
            return {
                LinearLayout;
                layout_height="25dp";
                layout_width="50dp";
                layout_marginEnd="5dp";
                layout_marginBottom="2dp";
                background="button.xml";
                id=ids;
                onClickListener=function()
                    local rvs = loadlayout(rpm)
                    panel.addView(rvs, layout)
                    mod.setVisibility(View.GONE)
                    
                    reqRelation = function(button, strings, values)
                        button.onClick = function(v, view)
                            local runnable = { run = function()
                                local setrel = getvalues(strings - 0x20, 32) + 0x260
                                local exac = {
                                    {address = setrel, flags = 4, value = values, freeze = true},
                                    {address = setrel + 0x4, flags = 4, value = 41249, freeze = true}
                                }
                                gg.addListItems(exac)
                                gg.setValues(exac)
                            end } rx.b(runnable)
                        end
                    end
                    
                    local button = {
                        {r_hug, 3}, {r_hug2, 8}, {r_hfive, 4}, {r_hfive2, 9}, {r_fbump, 5},
                        {r_fbump, 10}, {r_dbfive, 6}, {r_dbfive2, 11}, {r_pgback, 12},
                        {r_pgback2, 13}, {r_htousle, 14}, {r_htousle2, 15}, {r_pfight, 16},
                        {r_pfight2, 17}, {r_bearhug, 18}, {r_bearhug2, 19}, {r_ddance, 20},
                        {r_ddance2, 21}, {r_hshake, 22}, {r_hshake, 23}, {r_sdhug, 24},
                        {r_sdhug, 25}, {r_pcarry, 27}, {r_pcarry, 28}, {r_perform, 29},
                        {r_perform2, 30}
                    }
                    for i, v in ipairs(button) do
                        reqRelation(v[1], strings, v[2])
                    end
                    
                    reqback.onClick = function()
                        local runnable = { run = function()
                            local setrel = getvalues(strings - 0x20, 32) + 0x260
                            local svback = {
                                {address = setrel, flags = 4, value = 0, freeze = false},
                                {address = setrel + 0x4, flags = 4, value = 0, freeze = false}
                            }
                            gg.removeListItems(svback)
                            gg.setValues(svback)
                        end } rx.b(runnable)
                        panel.removeView(rvs)
                        mod.setVisibility(View.VISIBLE)
                        reqmod.setVisibility(View.GONE)
                    end
                end;
                {
                    TextView;
                    layout_height="match_parent";
                    layout_width="match_parent";
                    text=text;
                    textSize="10sp";
                    gravity="center";
                };
            } 
        end,
        copycat = function(text, ids, strings)
            return {
                LinearLayout;
                layout_height="25dp";
                layout_width="50dp";
                layout_marginEnd="5dp";
                layout_marginBottom="2dp";
                background="button.xml";
                id=ids;
                onClickListener=function()
                    local runnable = { run = function()
                        local values = {}
                        for i = 1, 10 do
                            table.insert(values, {address = v_avatar.body + (i - 1) * 0x4, flags = 4, value = getvalues(strings + (i - 1) * 0x4, 4)})
                        end
                        gg.setValues(values)
                    end } rx.b(runnable)
                end;
                {
                    TextView;
                    layout_height="match_parent";
                    layout_width="match_parent";
                    text=text;
                    textSize="10sp";
                    gravity="center";
                };
            } 
        end,
        forceride = function(text, ids, strings)
            return {
                LinearLayout;
                layout_height="25dp";
                layout_width="50dp";
                layout_marginEnd="5dp";
                layout_marginBottom="2dp";
                background="button.xml";
                id=ids;
                onClickListener=function()
                    local runnable = { run = function()
                        local fset = getvalues(v_main.avatar - 0x20, 32) + 0x260
                        setvalues(fset - 0x80, 4, getvalues(strings + 0x4D8F8, 4), true)
                    end } rx.b(runnable)
                end;
                {
                    TextView;
                    layout_height="match_parent";
                    layout_width="match_parent";
                    text=text;
                    textSize="10sp";
                    gravity="center";
                };
            } 
        end
    }
}

function headButton(text, ids)
    return {
        LinearLayout;
        layout_height="25dp";
        layout_width="50dp";
        layout_margin="5dp";
        layout_marginBottom="2dp";
        background="button.xml";
        id=ids;
        {
            TextView;
            layout_height="match_parent";
            layout_width="match_parent";
            text=text;
            textSize="12sp";
            gravity="center";
        };
    }
end

function imageButton(src, id)
    return {
        LinearLayout;
        layout_height="match_parent";
        layout_width="match_parent";
        layout_margin="5dp";
        background="button.xml";
        {
            ImageView;
            id=id;
            layout_height="30dp";
            layout_width="30dp";
            src=src;
        };
    };
end

function wingButton(text, strings)
    return {
        LinearLayout;
        layout_height="60dp";
        layout_margin="5dp";
        layout_width="match_parent";
        orientation="vertical";
        {
            TextView;
            text=text;
            textSize="12sp";
            layout_height="20dp";
            layout_width="match_parent";
            layout_marginBottom="8dp";
        };
        {
            LinearLayout;
            layout_height="40dp";
            layout_width="match_parent";
            orientation="horizontal";
            ios.wing.gotoButton("goto", "tpwing", strings);
           -- ios.wing.bringButton("bring", "wbring", strings);
            ios.wing.absorbWing("absorb", "abswing", strings + 0x28)
        };
    }
end

function playerButton(text, strings, panel, mod, params)
    return {
        LinearLayout;
        layout_height="60dp";
        layout_margin="5dp";
        layout_width="match_parent";
        orientation="vertical";
        {
            TextView;
            text=text;
            textSize="12sp";
            layout_height="20dp";
            layout_width="match_parent";
            layout_marginBottom="8dp";
        };
        {
            LinearLayout;
            layout_height="40dp";
            layout_width="match_parent";
            orientation="horizontal";
            id="playerPanel";
            ios.wing.gotoButton("goto", "tpplayer", strings);
            ios.player.sendrel("send", "sendrels", strings, panel, mod, params);
            ios.player.reqrels("request", "requestrel", strings, panel, mod, params);
            ios.player.copycat("copy", "copycat", strings + 0x7c04);
        };
    }
end

function buttonText(text, ids)
    return {
        LinearLayout;
        layout_height="25dp";
        layout_width="match_parent";
        layout_marginBottom="5dp";
        background="button.xml";
        id=ids;
        {
            TextView;
            layout_height="match_parent";
            layout_width="match_parent";
            text=text;
            textSize="12sp";
            gravity="center";
        };
    }
end

function buttonView(ids, text)
    return {
        LinearLayout;
        layout_height="match_parent";
        layout_width="300dp";
        --layout_margin="5dp";
        layout_marginBottom="2dp";
        {
            LinearLayout;
            layout_height="25dp";
            layout_width="match_parent";
            layout_margin="5dp";
            layout_marginBottom="2dp";
            background="button.xml";
            gravity="center";
            id=ids;
            onClickListener=function()
                local runnable = {
                    run = function()
                        pcall(teleport_to, text)
                    end
                }
                rx.b(runnable)
            end;
            {
                TextView;
                layout_height="25dp";
                layout_width="300dp";
                layout_margin="5dp";
                text=text;
                textSize="12sp";
                gravity="center";
            };
        };
    };
end

function avatarView(ids, text, strings)
    return {
        LinearLayout;
        layout_height="match_parent";
        layout_width="300dp";
        --layout_margin="5dp";
        layout_marginBottom="2dp";
        {
            LinearLayout;
            layout_height="25dp";
            layout_width="match_parent";
            layout_margin="5dp";
            layout_marginBottom="2dp";
            background="button.xml";
            gravity="center";
            id=ids;
            onClickListener=function()
                local runnable = {
                    run = function()
                        for i = 1, 10 do
                            local index = strings + (i - 1) * 0x4
                            local values = {
                                {address = v_avatar.body + (i - 1) * 0x4, flags = 4, value = getvalues(index, 4)}
                            }
                            gg.setValues(values)
                        end
                    end
                }
                rx.b(runnable)
            end;
            {
                TextView;
                layout_height="25dp";
                layout_width="300dp";
                layout_margin="5dp";
                text=text;
                textSize="12sp";
                gravity="center";
            };
        };
    };
end

function buttonProgress(text, idt, idp)
    return {
        RelativeLayout;
        layout_height="25dp";
        layout_width="match_parent";
        layout_marginBottom="5dp";
        {
            ProgressBar;
            layout_height="25dp";
            layout_width="match_parent";
            id=idp;
            style="?android:attr/progressBarStyleHorizontal";
            progressDrawable=loaddrawable("progress.xml");
            progress=0;
            max=100;
        };
        {
            TextView;
            layout_height="match_parent";
            layout_width="match_parent";
            id=idt;
            text=text;
            textSize="12sp";
            gravity="center";
        };
    }
end

function minibuttonProgress(text, idt, idp)
    return {
        RelativeLayout;
        layout_height="25dp";
        layout_width="90dp";
        layout_marginEnd="5dp";
        layout_marginBottom="5dp";
        {
            ProgressBar;
            layout_height="25dp";
            layout_width="match_parent";
            id=idp;
            style="?android:attr/progressBarStyleHorizontal";
            progressDrawable=loaddrawable("progress.xml");
            progress=0;
            max=100;
        };
        {
            TextView;
            layout_height="match_parent";
            layout_width="match_parent";
            id=idt;
            text=text;
            textSize="12sp";
            gravity="center";
        };
    }
end

function singleSwitch(text, ids, bool)
    return {
        LinearLayout;
        layout_height="match_parent";
        layout_width="wrap_content";
        layout_margin="5dp";
        layout_marginBottom="2dp";
        orientation="horizontal";
        {
            TextView;
            layout_height="match_parent";
            layout_width="70dp";
          --  layout_margin="5dp";
            text=text;
            textSize="10sp";
            gravity="center_left";
        };
        {
            Switch;
            layout_height="match_parent";
            layout_width="50dp";
            checked=bool;
            trackDrawable=trackDrawable;
            thumbDrawable=thumbDrawable;
            id=ids;
        };
    }
end

function switchView(text, ids, bool, text2, ids2, bool2)
    return {
        LinearLayout;
        layout_height="match_parent";
        layout_width="wrap_content";
        layout_margin="5dp";
        layout_marginBottom="2dp";
        orientation="horizontal";
        {
            TextView;
            layout_height="match_parent";
            layout_width="70dp";
          --  layout_margin="5dp";
            text=text;
            textSize="10sp";
            gravity="center_left";
        };
        {
            Switch;
            layout_height="match_parent";
            layout_width="50dp";
            checked=bool;
            trackDrawable=trackDrawable;
            thumbDrawable=thumbDrawable;
            id=ids;
        };
        {
            TextView;
            layout_height="match_parent";
            layout_width="70dp";
            layout_marginStart="10dp";
            text=text2;
            textSize="10sp";
            gravity="center_left";
        };
        {
            Switch;
            layout_height="match_parent";
            layout_width="50dp";
            checked=bool2;
            trackDrawable=trackDrawable;
            thumbDrawable=thumbDrawable;
            id=ids2;
        };
    }
end

function buttonBack(text, id)
    return {
        LinearLayout;
        layout_height="25dp";
        layout_width="60dp";
        layout_margin="5dp";
        background="button.xml";
        {
            TextView;
            layout_height="match_parent";
            layout_width="match_parent";
            text=text;
            textSize="12sp";
            gravity="center";
            id=id;
        };
    }
end

function switchValue(button, strings, mask, sval, eval, bool)
    button.onCheckedChangeListener = function()
        if button.checked then
            values = sval
        else
            values = eval
        end
        local runnable = {
            run = function()
                pcall(setvalues, strings, mask, values, button.checked)
            end
        }
        updateiOSwitch(button, button.checked)
        rx.b(runnable)
    end
end

function support_me()
    return web().loadUrl("https://sociabuzz.com/akaxell/tribe")
end

function contact_person()
    return gg.gotoBrowser("https://t.me/akaxell")
end
