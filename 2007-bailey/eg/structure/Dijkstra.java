// A program to use Dijkstra's algorithm to find the
// shortest road from here to thereName.
// (c) 1996, 2001 duane a. bailey

import structure.*;
import java.io.*;
import java.util.Iterator;

public class Dijkstra {

    public static Map dijkstra(Graph g, Object start)
    // pre: g is a graph; start is source vertex
    // post: returns a dictionary of vertex-based results
    //       value is association (total-distance,prior-edge)
    {
        // keep a priority queue of distances from source
        PriorityQueue q = new SkewHeap();
        Map result = new Table(); // results, sorted by vertex
        Object v = start;       // last vertex added
        // result is a (total-distance,previous-edge) pair
        ComparableAssociation possible =
            new ComparableAssociation(new Integer(0),null);
        // as long as we add a new vertex...
        while (v != null)
        {
            if (!result.containsKey(v))
            {
                // visit node v - record incoming edge
                result.put(v,possible);
                // vDist is shortest distance to v
                int vDist = ((Integer)possible.getKey()).intValue();

                // compute and consider distance to each neighbor
                Iterator ai = g.neighbors(v);
                while (ai.hasNext())
                {
                    // get edge to neighbor
                    Edge e = g.getEdge(v,ai.next());
                    // construct (distance,edge) pair for possible result
                    possible = new ComparableAssociation(
                      new Integer(vDist+((Integer)e.label()).intValue()),
                      e);
                    q.add(possible);    // add to priority queue
                }
            }
            // now, get closest (possibly unvisited) vertex
            if (!q.isEmpty())
            {
                possible = (ComparableAssociation)q.remove();
                // get destination vertex (take care w/undirected graphs)
                v = ((Edge)possible.getValue()).there();
                if (result.containsKey(v))
                    v = ((Edge)possible.getValue()).here();
            } else {
                // no new vertex (algorithm stops)
                v = null;
            }
        }
        return result;
    }

    static public void main(String[] args) {
        Graph g = new GraphMatrixDirected(300);

        // towns and their names
        String hereName, thereName;// two generic towns
        String startName, finishName; // the specific query

        // a road to be added or manipulated
        Integer length;
        ReadStream input = new ReadStream();

        // read in all roads, add two copies <-> bidirectional
        for (hereName = input.readString();
             !hereName.equals("end");
             hereName = input.readString()) {
        
            thereName = input.readString();
            input.readString();
            length = new Integer(input.readInt());
        
            g.add(hereName);
            g.add(thereName);
            // for getting from hereTown to thereTown
            g.addEdge(hereName, thereName, length);
            // for getting from thereTown to hereTown
            g.addEdge(thereName, hereName, length);
        }

        // read in the source town
        startName = input.readString();

        Map results = dijkstra(g,startName);
        Iterator ki = results.keySet().iterator();
        Iterator vi = results.values().iterator();
        while (ki.hasNext())
        {
            String dest = (String)ki.next();
            Association a = (Association)vi.next();
            if (dest.equals(startName)) continue;
            String source = (String)((Edge)a.getValue()).here();
            if (source.equals(dest))
                source = (String)((Edge)a.getValue()).there();
            int len = ((Integer)a.getKey()).intValue();
            System.out.println(dest+" is "+len+" miles from "+startName+" (via "+source+")");
        }
    }
}
/*
albany_ny binghamton_ny I88 142
albany_ny burlington_vt SR7 154
albany_ny lee_ma 90 42
albany_ny newburgh_ny I87 88
albany_ny plattsburgh_ny I87 159
albany_ny springfield_ma I90 85
albany_ny syracuse_ny I90 146
albany_ny troy_ny 787 14
albert_lea_mn des_moines_ia I35 148
albert_lea_mn madison_wi I90 262
albert_lea_mn minneapolis_mn I35 93
albert_lea_mn sioux_falls_sd I90 168
albuquerque_nm colorado_springs_co I25 365
albuquerque_nm el_paso_tx I25 257
albuquerque_nm flagstaff_az I40 323
albuquerque_nm santa_rosa_nm I40 114
allentown_pa harrisburg_pa I78 73
allentown_pa new_york_ny I78 103
allentown_pa newark_nj I78 100
allentown_pa philadelphia_pa Turnpike 60
allentown_pa scranton_pa Turnpike 72
allentown_pa stroudsburg_pa SR22 41
amarillo_tx canyon_tx I27 16
amarillo_tx oklahoma_city_ok I40 267
amarillo_tx santa_rosa_nm I40 170
asheville_nc charlotte_nc SR74 118
asheville_nc columbia_sc I26 156
asheville_nc greenville_sc SR25 62
asheville_nc knoxville_tn I40 118
asheville_nc winston_salem_nc I40 144
atlanta_ga augusta_ga I20 144
atlanta_ga birmingham_al I20 149
atlanta_ga chattanooga_tn I75 109
atlanta_ga greenville_sc I85 151
atlanta_ga macon_ga I75 82
atlanta_ga montgomery_al I85 165
atlanta_ga tallahassee_fl SR27 275
atlantic_city_nj newark_nj Turnpike 108
atlantic_city_nj philadelphia_pa Turnpike 63
atlantic_city_nj salisbury_md SR9 95
augusta_ga columbia_sc I20 79
augusta_ga greenville_sc SR25 113
augusta_ga jacksonville_fl SR1 253
augusta_ga savannah_ga SR25 133
baldwin_fl jacksonville_fl I10 19
baldwin_fl lake_city_fl I10 40
baldwin_fl waldo_fl SR301 37
baltimore_md hagerstown_md I70 75
baltimore_md harrisburg_pa I83 79
baltimore_md newark_nj Turnpike 175
baltimore_md salisbury_md SR50 111
baltimore_md washington_dc I95 37
baltimore_md wilmington_de I95 67
barstow_ca kingman_az I40 203
barstow_ca las_vegas_nv I15 155
barstow_ca san_bernadino_ca I15 74
baton_rouge_la houston_tx I10 253
baton_rouge_la jackson_ms I55 169
baton_rouge_la mobile_al I10 213
baton_rouge_la new_orleans_la I10 89
baton_rouge_la shreveport_la SR71 236
battle_creek_mi detroit_mi I96 124
battle_creek_mi fort_wayne_in I69 100
battle_creek_mi gary_in I94 128
battle_creek_mi lansing_mi I69 56
billings_mt bismark_nd I94 418
billings_mt buffalo_wy I90 161
billings_mt butte_mt I90 237
billings_mt great_falls_mt SR89 287
biloxi_ms gulfport_ms I10 15
biloxi_ms mobile_al I10 58
binghamton_ny scranton_pa I81 52
binghamton_ny syracuse_ny I81 64
birmingham_al chattanooga_tn I59 146
birmingham_al memphis_tn SR78 241
birmingham_al montgomery_al I65 90
birmingham_al nashville_tn I65 192
birmingham_al tuscaloosa_al I59 56
bismark_nd fargo_nd I94 191
bismark_nd pierre_sd SR83 211
bloomington_il champaign_il I74 52
bloomington_il chicago_il I55 133
bloomington_il davenport_ia I74 140
bloomington_il springfield_il I55 61
boise_id ogden_ut I84 323
boise_id pendleton_or I84 217
boise_id pocatello_id I84 234
boise_id spokane_wa SR95 391
boston_ma greenfield_ma SR2 99
boston_ma hartford_ct I86 102
boston_ma manchester_nh I93 51
boston_ma portland_me I95 109
boston_ma providence_ri I95 51
boston_ma springfield_ma I90 92
breezewood_pa hagerstown_md I70 47
breezewood_pa harrisburg_pa Turnpike 83
breezewood_pa morgantown_wv SR48 134
breezewood_pa pittsburgh_pa I76 121
breezewood_pa wheeling_wv I70 148
buffalo_ny erie_pa I90 108
buffalo_ny syracuse_ny I90 156
buffalo_wy casper_wy I25 305
buffalo_wy rapid_city_sd I90 207
burlington_vt white_river_jct._vt I89 96
butte_mt helena_mt I15 65
butte_mt missoula_mt I90 130
butte_mt pocatello_id I15 261
cambridge_oh charleston_wv I77 131
cambridge_oh cleveland_oh I77 116
cambridge_oh columbus_oh I70 80
cambridge_oh wheeling_wv I70 48
cambridge_oh zanesville_oh I70 26
cannon(afb)_nm canyon_tx SR60 99
cannon(afb)_nm santa_rosa_nm SR84 92
captain_cook_hi kailua_kona_hi 11 11
captain_cook_hi mountain_view_hi 11 84
casa_grande_az phoenix_az I10 58
casa_grande_az san_diego_ca I8 356
casa_grande_az tucson_az I10 63
casper_wy cheyenne_wy I25 184
casper_wy rapid_city_sd SR18 254
champaign_il chicago_il I57 133
champaign_il effingham_il I57 70
champaign_il indianapolis_in I74 114
champaign_il springfield_il I72 86
charleston_sc columbia_sc I77 95
charleston_sc florence_sc SR52 112
charleston_sc santee_sc I26 54
charleston_sc savannah_ga SR17 105
charleston_sc wilmington_nc SR17 170
charleston_wv huntington_wv I64 47
charleston_wv morgantown_wv I79 156
charleston_wv staunton_va I64 209
charleston_wv wytheville_va I77 123
charlotte_nc columbia_sc I77 95
charlotte_nc greensboro_nc I85 94
charlotte_nc greenville_sc I85 105
charlotte_nc lumberton_nc SR74 126
charlotte_nc wadesboro_nc SR74 51
charlotte_nc winston_salem_nc I85 80
charlotte_nc wytheville_va I77 134
chattanooga_tn knoxville_tn I75 109
chattanooga_tn nashville_tn I24 133
cheraw_sc columbia_sc SR1 80
cheraw_sc wadesboro_nc SR52 23
cheyenne_wy denver_co I25 98
cheyenne_wy north_platte_ne I80 223
cheyenne_wy ogden_ut I80 444
cheyenne_wy rapid_city_sd SR85 316
cheyenne_wy salt_lake_city_ut I80 457
chicago_il davenport_ia I80 177
chicago_il gary_in I90 32
chicago_il madison_wi I90 140
chicago_il milwaukee_wi I94 87
cincinnati_oh columbus_oh I71 109
cincinnati_oh dayton_oh I75 54
cincinnati_oh huntington_wv SR52 161
cincinnati_oh indianapolis_in I74 110
cincinnati_oh lexington_ky I75 78
cincinnati_oh louisville_ky I71 101
cincinnati_oh sardinia_oh I32 35
cleveland_oh columbus_oh I71 140
cleveland_oh erie_pa I90 80
cleveland_oh mercer_pa I80 94
cleveland_oh pittsburgh_pa I76 129
cleveland_oh toledo_oh I80 112
colorado_springs_co denver_co I25 67
colorado_springs_co salina_ks I70 415
columbia_sc florence_sc I20 80
columbia_sc greenville_sc I26 104
columbia_sc santee_sc I26 66
columbus_oh dayton_oh I70 71
columbus_oh huntington_wv SR23 131
columbus_oh mercer_pa I71 203
columbus_oh toledo_oh SR23 135
columbus_oh zanesville_oh I70 54
concord_nh manchester_nh I93 20
concord_nh portland_me SR4 95
concord_nh white_river_jct._vt I89 66
cove_fort_ut green_river_ut I70 158
cove_fort_ut las_vegas_nv I15 251
cove_fort_ut salt_lake_city_ut I15 178
dallas_tx ft.worth_tx I20 32
dallas_tx houston_tx I45 245
dallas_tx oklahoma_city_ok I35 205
dallas_tx san_antonio_tx I35 270
dallas_tx shreveport_la I20 195
dallas_tx texarkana_tx I30 183
davenport_ia des_moines_ia I80 160
davenport_ia gary_in I80 183
dayton_oh indianapolis_in I70 104
dayton_oh toledo_oh I75 147
daytona_beach_fl ft._lauderdale_fl I95 243
daytona_beach_fl jacksonville_fl I95 99
daytona_beach_fl orlando_fl I4 58
denver_co green_river_ut I70 350
denver_co north_platte_ne I76 269
denver_co salina_ks I70 429
des_moines_ia kansas_city_mo I35 198
des_moines_ia omaha_ne I80 128
des_moines_ia sioux_city_ia I80 183
detroit_mi flint_mi I75 67
detroit_mi gary_in I94 248
detroit_mi grand_rapids_mi I96 148
detroit_mi sault_ste._marie_mi I75 242
detroit_mi toledo_oh I75 60
dover_de salisbury_md SR13 60
dover_de washington_dc SR50 96
dover_de wilmington_de SR13 46
duluth_mn grand_forks_nd SR2 266
duluth_mn mackinaw_city_mi SR2 414
duluth_mn minneapolis_mn I35 156
duluth_mn sault_ste._marie_mi SR2 422
durham_nc greensboro_nc I85 54
durham_nc petersburg_va I85 131
durham_nc raleigh_nc SR70 27
effingham_il indianapolis_in I70 136
effingham_il mt._vernon_il I57 71
effingham_il st._louis_mo I70 101
el_paso_tx ft.worth_tx I20 588
el_paso_tx san_antonio_tx I10 552
el_paso_tx tucson_az I10 317
ellensburg_wa pendleton_or I82 233
ellensburg_wa seattle_wa I90 103
ellensburg_wa spokane_wa I90 171
erie_pa mercer_pa I79 66
fargo_nd grand_forks_nd I29 80
fargo_nd minneapolis_mn I94 240
fargo_nd sioux_falls_sd I29 261
flagstaff_az kingman_az I40 159
flagstaff_az phoenix_az I17 135
flint_mi lansing_mi I69 51
flint_mi standish_mi SR23 73
flint_mi toledo_oh SR23 106
florence_sc lumberton_nc I95 53
florence_sc rocky_mount_nc I95 165
florence_sc santee_sc I95 70
fort_walton_bch_fl panama_city_fl SR98 55
fort_walton_bch_fl pensacola_fl SR98 40
fort_wayne_in upland_in I69 51
ft._lauderdale_fl ft._myers_fl I75 99
ft._lauderdale_fl miami_fl I95 27
ft._myers_fl port_charlotte_fl I75 22
ft.smith_ar little_rock_ar I40 153
ft.smith_ar oklahoma_city_ok I40 179
ft.smith_ar texarkana_tx SR71 181
ft.smith_ar tulsa_ok Turnpike 116
ft.worth_tx oklahoma_city_ok I35 203
ft.worth_tx san_antonio_tx I35 267
gainesville_fl lake_city_fl I75 42
gainesville_fl tampa_fl I75 129
gainesville_fl waldo_fl SR24 19
gary_in grand_rapids_mi I196 144
gary_in indianapolis_in I65 145
gary_in south_bend_in I90 66
grand_forks_nd minot_nd SR2 215
grand_rapids_mi sault_ste._marie_mi SR131 276
great_falls_mt havre_mt SR87 113
great_falls_mt helena_mt I15 91
green_bay_wi madison_wi SR151 131
green_bay_wi milwaukee_wi I43 116
green_bay_wi sault_ste._marie_mi SR41 285
green_river_ut salt_lake_city_ut SR6 176
greenfield_ma north_adams_ma SR2 28
greenfield_ma springfield_ma 91 38
greensboro_nc roanoke_va SR220 109
greensboro_nc winston_salem_nc I40 30
gulfport_ms hattisburg_ms SR49 66
gulfport_ms new_orleans_la I10 80
hagerstown_md harrisburg_pa I81 77
hagerstown_md morgantown_wv SR48 133
hagerstown_md strasburg_va I81 51
hagerstown_md washington_dc I270 64
hanamaulu_hi kapaa_hi SR50 5
hanamaulu_hi lihue_hi SR50 2
harrisburg_pa philadelphia_pa I76 105
harrisburg_pa scranton_pa I81 115
hartford_ct new_haven_ct I91 40
hartford_ct newburgh_ny I84 101
hartford_ct providence_ri SR6 74
hartford_ct springfield_ma I91 30
hattisburg_ms jackson_ms SR49 89
hattisburg_ms meridian_ms I59 86
hattisburg_ms mobile_al SR98 106
hattisburg_ms new_orleans_la I59 98
havre_mt minot_nd SR2 439
helena_mt missoula_mt SR12 124
hilo_hi kailua_kona_hi 19 87
hilo_hi mountain_view_hi 11 15
honolulu_hi kaneohe_hi 63 9
honolulu_hi pearl_city_hi H1 12
honolulu_hi waikiki_beach_hi H1 1
houston_tx san_antonio_tx I10 197
houston_tx shreveport_la SR59 226
huntington_wv lexington_ky I64 112
indianapolis_in louisville_ky I65 114
indianapolis_in upland_in I69 59
iron_mountain_mi mackinaw_city_mi SR2 197
iron_mountain_mi minneapolis_mn I8 219
jackson_ms little_rock_ar SR65 267
jackson_ms meridian_ms I20 90
jackson_ms new_orleans_la I55 183
jackson_ms shreveport_la I20 213
jackson_ms winona_ms I55 116
jacksonville_fl santee_sc I95 232
jacksonville_fl savannah_ga I95 143
jacksonville_fl tampa_fl SR301 198
kahului_hi kihei_hi 311 6
kahului_hi wailuku_hi 32 2
kailua_kona_hi kamuela_hi 190 39
kalaheo_hi kekaha_hi SR50 13
kalaheo_hi lihue_hi SR50 12
kansas_city_mo omaha_ne I29 184
kansas_city_mo st._louis_mo I70 254
kansas_city_mo topeka_ks I70 60
kansas_city_mo tulsa_ok SR169 259
kansas_city_mo wichita_ks I35 201
kaunakakai_hi waialua_hi 450 19
kingman_az las_vegas_nv SR93 103
kingman_az phoenix_az SR93 184
knoxville_tn lexington_ky I75 173
knoxville_tn nashville_tn I40 170
knoxville_tn wytheville_va I81 187
lahaina_hi wailuku_hi 340 30
lake_city_fl macon_ga I75 199
lake_city_fl orlando_fl I75 160
lake_city_fl tallahassee_fl I10 98
lake_city_fl tampa_fl I75 169
las_vegas_nv reno_nv SR395 446
lee_ma pittsfield_ma SR7 11
lee_ma springfield_ma 90 44
lexington_ky louisville_ky I64 78
lexington_ky nashville_tn I65 214
lexington_ky paducah_ky Turnpike 262
little_rock_ar memphis_tn I40 138
little_rock_ar springfield_mo SR65 218
little_rock_ar texarkana_tx I30 130
los_angeles_ca sacramento_ca I5 382
los_angeles_ca san_bernadino_ca I10 40
los_angeles_ca san_diego_ca I5 127
los_angeles_ca san_francisco_ca I5 387
louisville_ky mt._vernon_il I64 190
louisville_ky nashville_tn I65 180
louisville_ky paducah_ky Turnpike 220
lumberton_nc raleigh_nc I95 95
lumberton_nc rocky_mount_nc I95 126
lumberton_nc wilmington_nc SR74 80
mackinaw_city_mi oscoda_mi SR23 149
mackinaw_city_mi sault_ste._marie_mi I75 57
mackinaw_city_mi standish_mi I75 154
macon_ga montgomery_al SR80 180
macon_ga savannah_ga I16 166
macon_ga tallahassee_fl SR319 190
madison_wi milwaukee_wi I94 77
madison_wi minneapolis_mn I94 271
madison_wi springfield_il SR51 290
marion_il mt._vernon_il I57 53
marion_il paducah_ky I24 42
marion_il sikeston_mo I57 60
medart_fl panama_city_fl SR98 70
medart_fl tallahassee_fl SR319 28
memphis_tn nashville_tn I40 219
memphis_tn sikeston_mo I55 142
memphis_tn winona_ms I55 96
mercer_pa pittsburgh_pa I79 61
mercer_pa stroudsburg_pa I80 303
meridian_ms mobile_al SR45 129
meridian_ms montgomery_al SR80 160
meridian_ms tuscaloosa_al I59 88
miami_fl orlando_fl Turnpike 236
miami_fl tampa_fl SR41 266
missoula_mt spokane_wa I90 201
mobile_al montgomery_al I65 171
mobile_al new_orleans_la I10 153
mobile_al pensacola_fl I10 59
montgomery_al pensacola_fl I65 159
montgomery_al tallahassee_fl SR231 208
montgomery_al tuscaloosa_al SR82 95
morgantown_wv pittsburgh_pa I79 69
morgantown_wv wheeling_wv I79 79
mt._vernon_il scott_afb_il I64 55
nashville_tn paducah_ky I24 148
new_haven_ct new_york_ny I95 70
new_haven_ct providence_ri I95 102
new_york_ny newark_nj I95 19
new_york_ny newburgh_ny I87 66
new_york_ny stroudsburg_pa I80 78
newark_nj newburgh_ny I87 69
newark_nj philadelphia_pa Turnpike 82
newark_nj stroudsburg_pa I80 66
newburgh_ny scranton_pa I84 96
norfolk_va richmond_va I64 99
norfolk_va rocky_mount_nc SR58 136
norfolk_va salisbury_md SR13 129
norfolk_va wilmington_nc SR17 259
north_adams_ma williamstown_ma SR2 5
north_platte_ne omaha_ne I80 299
north_platte_ne pierre_sd SR83 267
ogden_ut pocatello_id I15 132
ogden_ut salt_lake_city_ut I15 34
oklahoma_city_ok tulsa_ok Turnpike 103
oklahoma_city_ok wichita_ks I35 158
omaha_ne sioux_city_ia I29 98
orlando_fl tampa_fl I4 84
oscoda_mi standish_mi SR23 57
paducah_ky sikeston_mo SR60 59
pearl_city_hi wahiawa_hi H2 8
pendleton_or portland_or I84 211
pendleton_or spokane_wa SR395 203
pensacola_fl tallahassee_fl I10 200
petersburg_va richmond_va I95 24
petersburg_va rocky_mount_nc I95 101
philadelphia_pa wilmington_de I95 29
phoenix_az san_bernadino_ca I10 334
pierre_sd rapid_city_sd I90 192
pierre_sd sioux_falls_sd I90 222
pittsburgh_pa wheeling_wv I70 57
pittsfield_ma williamstown_ma SR7 20
plattsburgh_ny watertown_ny I3 170
port_charlotte_fl tampa_fl I75 93
portland_or seattle_wa I5 173
portland_or weed_ca I5 363
raleigh_nc rocky_mount_nc SR64 51
rapid_city_sd sioux_falls_sd I90 346
reno_nv sacramento_ca I80 134
reno_nv salt_lake_city_ut I80 531
reno_nv weed_ca SR395 265
richmond_va staunton_va I64 106
richmond_va washington_dc I95 114
roanoke_va staunton_va I81 81
roanoke_va wytheville_va I81 67
sacramento_ca san_francisco_ca I80 90
sacramento_ca weed_ca I5 227
salina_ks topeka_ks I70 114
salina_ks wichita_ks I135 87
salisbury_md washington_dc SR50 120
san_bernadino_ca san_diego_ca I15 115
san_francisco_ca weed_ca I5 282
santee_sc savannah_ga I95 96
scott_afb_il st._louis_mo I64 17
scranton_pa stroudsburg_pa I380 51
shreveport_la texarkana_tx SR71 70
sikeston_mo springfield_mo SR60 241
sikeston_mo st._louis_mo I55 152
sioux_city_ia sioux_falls_sd I29 85
south_bend_in toledo_oh I90 126
springfield_il st._louis_mo I55 94
springfield_ma white_river_jct._vt I91 118
springfield_mo st._louis_mo I44 214
springfield_mo tulsa_ok I44 180
staunton_va strasburg_va I81 76
strasburg_va washington_dc I66 72
syracuse_ny watertown_ny I81 74
tallahassee_fl tampa_fl SR19 252
topeka_ks wichita_ks Turnpike 142
troy_ny williamstown_ma SR2 34
tulsa_ok wichita_ks Turnpike 175
tuscaloosa_al winona_ms SR82 144
wadesboro_nc winston_salem_nc SR52 100
winston_salem_nc wytheville_va SR52 83
end
williamstown_ma
albany_ny is 48 miles from williamstown_ma (via troy_ny)
albert_lea_mn is 1276 miles from williamstown_ma (via madison_wi)
albuquerque_nm is 2138 miles from williamstown_ma (via santa_rosa_nm)
allentown_pa is 304 miles from williamstown_ma (via scranton_pa)
amarillo_tx is 1854 miles from williamstown_ma (via oklahoma_city_ok)
asheville_nc is 914 miles from williamstown_ma (via winston_salem_nc)
atlanta_ga is 1089 miles from williamstown_ma (via greenville_sc)
atlantic_city_nj is 313 miles from williamstown_ma (via newark_nj)
augusta_ga is 980 miles from williamstown_ma (via columbia_sc)
baldwin_fl is 1142 miles from williamstown_ma (via jacksonville_fl)
baltimore_md is 380 miles from williamstown_ma (via newark_nj)
barstow_ca is 2795 miles from williamstown_ma (via las_vegas_nv)
baton_rouge_la is 1544 miles from williamstown_ma (via jackson_ms)
battle_creek_mi is 834 miles from williamstown_ma (via detroit_mi)
billings_mt is 2134 miles from williamstown_ma (via bismark_nd)
biloxi_ms is 1452 miles from williamstown_ma (via gulfport_ms)
binghamton_ny is 190 miles from williamstown_ma (via albany_ny)
birmingham_al is 1141 miles from williamstown_ma (via chattanooga_tn)
bismark_nd is 1716 miles from williamstown_ma (via fargo_nd)
bloomington_il is 1007 miles from williamstown_ma (via chicago_il)
boise_id is 2602 miles from williamstown_ma (via ogden_ut)
boston_ma is 132 miles from williamstown_ma (via greenfield_ma)
breezewood_pa is 430 miles from williamstown_ma (via harrisburg_pa)
buffalo_ny is 350 miles from williamstown_ma (via syracuse_ny)
buffalo_wy is 1997 miles from williamstown_ma (via rapid_city_sd)
burlington_vt is 202 miles from williamstown_ma (via albany_ny)
butte_mt is 2371 miles from williamstown_ma (via billings_mt)
cambridge_oh is 626 miles from williamstown_ma (via wheeling_wv)
cannon(afb)_nm is 1969 miles from williamstown_ma (via canyon_tx)
canyon_tx is 1870 miles from williamstown_ma (via amarillo_tx)
casa_grande_az is 2654 miles from williamstown_ma (via phoenix_az)
casper_wy is 2019 miles from williamstown_ma (via cheyenne_wy)
champaign_il is 967 miles from williamstown_ma (via indianapolis_in)
charleston_sc is 933 miles from williamstown_ma (via florence_sc)
charleston_wv is 713 miles from williamstown_ma (via morgantown_wv)
charlotte_nc is 833 miles from williamstown_ma (via wytheville_va)
chattanooga_tn is 995 miles from williamstown_ma (via knoxville_tn)
cheraw_sc is 893 miles from williamstown_ma (via wadesboro_nc)
cheyenne_wy is 1835 miles from williamstown_ma (via north_platte_ne)
chicago_il is 874 miles from williamstown_ma (via gary_in)
cincinnati_oh is 787 miles from williamstown_ma (via columbus_oh)
cleveland_oh is 538 miles from williamstown_ma (via erie_pa)
colorado_springs_co is 1933 miles from williamstown_ma (via salina_ks)
columbia_sc is 901 miles from williamstown_ma (via florence_sc)
columbus_oh is 678 miles from williamstown_ma (via cleveland_oh)
concord_nh is 203 miles from williamstown_ma (via manchester_nh)
cove_fort_ut is 2389 miles from williamstown_ma (via green_river_ut)
dallas_tx is 1726 miles from williamstown_ma (via texarkana_tx)
davenport_ia is 1025 miles from williamstown_ma (via gary_in)
dayton_oh is 749 miles from williamstown_ma (via columbus_oh)
daytona_beach_fl is 1222 miles from williamstown_ma (via jacksonville_fl)
denver_co is 1881 miles from williamstown_ma (via north_platte_ne)
des_moines_ia is 1185 miles from williamstown_ma (via davenport_ia)
detroit_mi is 710 miles from williamstown_ma (via toledo_oh)
dover_de is 362 miles from williamstown_ma (via wilmington_de)
duluth_mn is 1374 miles from williamstown_ma (via sault_ste._marie_mi)
durham_nc is 686 miles from williamstown_ma (via petersburg_va)
effingham_il is 989 miles from williamstown_ma (via indianapolis_in)
el_paso_tx is 2346 miles from williamstown_ma (via ft.worth_tx)
ellensburg_wa is 2873 miles from williamstown_ma (via spokane_wa)
erie_pa is 458 miles from williamstown_ma (via buffalo_ny)
fargo_nd is 1525 miles from williamstown_ma (via minneapolis_mn)
flagstaff_az is 2461 miles from williamstown_ma (via albuquerque_nm)
flint_mi is 756 miles from williamstown_ma (via toledo_oh)
florence_sc is 821 miles from williamstown_ma (via rocky_mount_nc)
fort_walton_bch_fl is 1430 miles from williamstown_ma (via pensacola_fl)
fort_wayne_in is 934 miles from williamstown_ma (via battle_creek_mi)
ft._lauderdale_fl is 1465 miles from williamstown_ma (via daytona_beach_fl)
ft._myers_fl is 1436 miles from williamstown_ma (via port_charlotte_fl)
ft.smith_ar is 1566 miles from williamstown_ma (via little_rock_ar)
ft.worth_tx is 1758 miles from williamstown_ma (via dallas_tx)
gainesville_fl is 1198 miles from williamstown_ma (via waldo_fl)
gary_in is 842 miles from williamstown_ma (via south_bend_in)
grand_forks_nd is 1605 miles from williamstown_ma (via fargo_nd)
grand_rapids_mi is 858 miles from williamstown_ma (via detroit_mi)
great_falls_mt is 2372 miles from williamstown_ma (via havre_mt)
green_bay_wi is 1077 miles from williamstown_ma (via milwaukee_wi)
green_river_ut is 2231 miles from williamstown_ma (via denver_co)
greenfield_ma is 33 miles from williamstown_ma (via north_adams_ma)
greensboro_nc is 740 miles from williamstown_ma (via durham_nc)
greenville_sc is 938 miles from williamstown_ma (via charlotte_nc)
gulfport_ms is 1437 miles from williamstown_ma (via hattisburg_ms)
hagerstown_md is 424 miles from williamstown_ma (via harrisburg_pa)
harrisburg_pa is 347 miles from williamstown_ma (via scranton_pa)
hartford_ct is 101 miles from williamstown_ma (via springfield_ma)
hattisburg_ms is 1371 miles from williamstown_ma (via meridian_ms)
havre_mt is 2259 miles from williamstown_ma (via minot_nd)
helena_mt is 2436 miles from williamstown_ma (via butte_mt)
houston_tx is 1797 miles from williamstown_ma (via baton_rouge_la)
huntington_wv is 760 miles from williamstown_ma (via charleston_wv)
indianapolis_in is 853 miles from williamstown_ma (via dayton_oh)
iron_mountain_mi is 1180 miles from williamstown_ma (via mackinaw_city_mi)
jackson_ms is 1375 miles from williamstown_ma (via meridian_ms)
jacksonville_fl is 1123 miles from williamstown_ma (via santee_sc)
kansas_city_mo is 1344 miles from williamstown_ma (via st._louis_mo)
kingman_az is 2620 miles from williamstown_ma (via flagstaff_az)
knoxville_tn is 886 miles from williamstown_ma (via wytheville_va)
lake_city_fl is 1182 miles from williamstown_ma (via baldwin_fl)
lansing_mi is 807 miles from williamstown_ma (via flint_mi)
las_vegas_nv is 2640 miles from williamstown_ma (via cove_fort_ut)
lee_ma is 31 miles from williamstown_ma (via pittsfield_ma)
lexington_ky is 865 miles from williamstown_ma (via cincinnati_oh)
little_rock_ar is 1413 miles from williamstown_ma (via memphis_tn)
los_angeles_ca is 2909 miles from williamstown_ma (via san_bernadino_ca)
louisville_ky is 888 miles from williamstown_ma (via cincinnati_oh)
lumberton_nc is 782 miles from williamstown_ma (via rocky_mount_nc)
mackinaw_city_mi is 983 miles from williamstown_ma (via standish_mi)
macon_ga is 1153 miles from williamstown_ma (via savannah_ga)
madison_wi is 1014 miles from williamstown_ma (via chicago_il)
manchester_nh is 183 miles from williamstown_ma (via boston_ma)
marion_il is 1113 miles from williamstown_ma (via mt._vernon_il)
medart_fl is 1308 miles from williamstown_ma (via tallahassee_fl)
memphis_tn is 1275 miles from williamstown_ma (via nashville_tn)
mercer_pa is 524 miles from williamstown_ma (via erie_pa)
meridian_ms is 1285 miles from williamstown_ma (via tuscaloosa_al)
miami_fl is 1492 miles from williamstown_ma (via ft._lauderdale_fl)
milwaukee_wi is 961 miles from williamstown_ma (via chicago_il)
minneapolis_mn is 1285 miles from williamstown_ma (via madison_wi)
minot_nd is 1820 miles from williamstown_ma (via grand_forks_nd)
missoula_mt is 2501 miles from williamstown_ma (via butte_mt)
mobile_al is 1402 miles from williamstown_ma (via montgomery_al)
montgomery_al is 1231 miles from williamstown_ma (via birmingham_al)
morgantown_wv is 557 miles from williamstown_ma (via hagerstown_md)
mt._vernon_il is 1060 miles from williamstown_ma (via effingham_il)
nashville_tn is 1056 miles from williamstown_ma (via knoxville_tn)
new_haven_ct is 141 miles from williamstown_ma (via hartford_ct)
new_orleans_la is 1469 miles from williamstown_ma (via hattisburg_ms)
new_york_ny is 202 miles from williamstown_ma (via newburgh_ny)
newark_nj is 205 miles from williamstown_ma (via newburgh_ny)
newburgh_ny is 136 miles from williamstown_ma (via albany_ny)
norfolk_va is 537 miles from williamstown_ma (via salisbury_md)
north_adams_ma is 5 miles from williamstown_ma (via williamstown_ma)
north_platte_ne is 1612 miles from williamstown_ma (via omaha_ne)
ogden_ut is 2279 miles from williamstown_ma (via cheyenne_wy)
oklahoma_city_ok is 1587 miles from williamstown_ma (via tulsa_ok)
omaha_ne is 1313 miles from williamstown_ma (via des_moines_ia)
orlando_fl is 1280 miles from williamstown_ma (via daytona_beach_fl)
oscoda_mi is 886 miles from williamstown_ma (via standish_mi)
paducah_ky is 1108 miles from williamstown_ma (via louisville_ky)
panama_city_fl is 1378 miles from williamstown_ma (via medart_fl)
pendleton_or is 2819 miles from williamstown_ma (via boise_id)
pensacola_fl is 1390 miles from williamstown_ma (via montgomery_al)
petersburg_va is 555 miles from williamstown_ma (via richmond_va)
philadelphia_pa is 287 miles from williamstown_ma (via newark_nj)
phoenix_az is 2596 miles from williamstown_ma (via flagstaff_az)
pierre_sd is 1666 miles from williamstown_ma (via sioux_falls_sd)
pittsburgh_pa is 551 miles from williamstown_ma (via breezewood_pa)
pittsfield_ma is 20 miles from williamstown_ma (via williamstown_ma)
plattsburgh_ny is 207 miles from williamstown_ma (via albany_ny)
pocatello_id is 2411 miles from williamstown_ma (via ogden_ut)
port_charlotte_fl is 1414 miles from williamstown_ma (via tampa_fl)
portland_me is 241 miles from williamstown_ma (via boston_ma)
portland_or is 3030 miles from williamstown_ma (via pendleton_or)
providence_ri is 175 miles from williamstown_ma (via hartford_ct)
raleigh_nc is 707 miles from williamstown_ma (via rocky_mount_nc)
rapid_city_sd is 1790 miles from williamstown_ma (via sioux_falls_sd)
reno_nv is 2823 miles from williamstown_ma (via salt_lake_city_ut)
richmond_va is 531 miles from williamstown_ma (via washington_dc)
roanoke_va is 632 miles from williamstown_ma (via staunton_va)
rocky_mount_nc is 656 miles from williamstown_ma (via petersburg_va)
sacramento_ca is 2957 miles from williamstown_ma (via reno_nv)
salina_ks is 1518 miles from williamstown_ma (via topeka_ks)
salisbury_md is 408 miles from williamstown_ma (via atlantic_city_nj)
salt_lake_city_ut is 2292 miles from williamstown_ma (via cheyenne_wy)
san_antonio_tx is 1994 miles from williamstown_ma (via houston_tx)
san_bernadino_ca is 2869 miles from williamstown_ma (via barstow_ca)
san_diego_ca is 2984 miles from williamstown_ma (via san_bernadino_ca)
san_francisco_ca is 3047 miles from williamstown_ma (via sacramento_ca)
santa_rosa_nm is 2024 miles from williamstown_ma (via amarillo_tx)
santee_sc is 891 miles from williamstown_ma (via florence_sc)
sardinia_oh is 822 miles from williamstown_ma (via cincinnati_oh)
sault_ste._marie_mi is 952 miles from williamstown_ma (via detroit_mi)
savannah_ga is 987 miles from williamstown_ma (via santee_sc)
scott_afb_il is 1107 miles from williamstown_ma (via st._louis_mo)
scranton_pa is 232 miles from williamstown_ma (via newburgh_ny)
seattle_wa is 2976 miles from williamstown_ma (via ellensburg_wa)
shreveport_la is 1588 miles from williamstown_ma (via jackson_ms)
sikeston_mo is 1167 miles from williamstown_ma (via paducah_ky)
sioux_city_ia is 1368 miles from williamstown_ma (via des_moines_ia)
sioux_falls_sd is 1444 miles from williamstown_ma (via albert_lea_mn)
south_bend_in is 776 miles from williamstown_ma (via toledo_oh)
spokane_wa is 2702 miles from williamstown_ma (via missoula_mt)
springfield_il is 1053 miles from williamstown_ma (via champaign_il)
springfield_ma is 71 miles from williamstown_ma (via greenfield_ma)
springfield_mo is 1304 miles from williamstown_ma (via st._louis_mo)
st._louis_mo is 1090 miles from williamstown_ma (via effingham_il)
standish_mi is 829 miles from williamstown_ma (via flint_mi)
staunton_va is 551 miles from williamstown_ma (via strasburg_va)
strasburg_va is 475 miles from williamstown_ma (via hagerstown_md)
stroudsburg_pa is 271 miles from williamstown_ma (via newark_nj)
syracuse_ny is 194 miles from williamstown_ma (via albany_ny)
tallahassee_fl is 1280 miles from williamstown_ma (via lake_city_fl)
tampa_fl is 1321 miles from williamstown_ma (via jacksonville_fl)
texarkana_tx is 1543 miles from williamstown_ma (via little_rock_ar)
toledo_oh is 650 miles from williamstown_ma (via cleveland_oh)
topeka_ks is 1404 miles from williamstown_ma (via kansas_city_mo)
troy_ny is 34 miles from williamstown_ma (via williamstown_ma)
tucson_az is 2663 miles from williamstown_ma (via el_paso_tx)
tulsa_ok is 1484 miles from williamstown_ma (via springfield_mo)
tuscaloosa_al is 1197 miles from williamstown_ma (via birmingham_al)
upland_in is 912 miles from williamstown_ma (via indianapolis_in)
wadesboro_nc is 870 miles from williamstown_ma (via winston_salem_nc)
waldo_fl is 1179 miles from williamstown_ma (via baldwin_fl)
washington_dc is 417 miles from williamstown_ma (via baltimore_md)
watertown_ny is 268 miles from williamstown_ma (via syracuse_ny)
weed_ca is 3088 miles from williamstown_ma (via reno_nv)
wheeling_wv is 578 miles from williamstown_ma (via breezewood_pa)
white_river_jct._vt is 189 miles from williamstown_ma (via springfield_ma)
wichita_ks is 1545 miles from williamstown_ma (via kansas_city_mo)
wilmington_de is 316 miles from williamstown_ma (via philadelphia_pa)
wilmington_nc is 796 miles from williamstown_ma (via norfolk_va)
winona_ms is 1341 miles from williamstown_ma (via tuscaloosa_al)
winston_salem_nc is 770 miles from williamstown_ma (via greensboro_nc)
wytheville_va is 699 miles from williamstown_ma (via roanoke_va)
zanesville_oh is 652 miles from williamstown_ma (via cambridge_oh)
*/
