// Perform a minimum cost spanning forest of a graph.
// (c) 1996, 2001 duane a. bailey

// the result of the program is to print out
// a minimum cost spanning forest of the graph found on the input.
import structure5.*;
import java.util.Iterator;
import java.io.*;

public class MCSF {
    public static void main(String[] args)
    {
        ReadStream s = new ReadStream();
        Graph<String,Integer> g = new GraphListUndirected<String,Integer>();

        // We now read a list of ordered pairs.
        while (!s.eof()) {
            String here = s.readString();
            String there = s.readString();
            Integer weight = s.readInt();
            s.skipWhite();
            g.add(here);
            g.add(there);
            g.addEdge(here,there,weight);
        }
        System.out.println("In original graph there are "+g.size()+" vertices"+
                           " and "+g.edgeCount()+" edges.");

        mcsf(g);
        Iterator<Edge<String,Integer>> ei = g.edges();
        int weight = 0, edges=0;
        while (ei.hasNext())
        {
            Edge<String,Integer> e = ei.next();
            if (e.isVisited()) {
               weight += e.label();
               edges++;
               System.out.println(e);
            }
        }
        System.out.println("Total weight is "+weight+" over "+edges+" edges.");
    }

    static public void mcsf(Graph<String,Integer> g)
    // post: marks the edges of the graph that participate in
    //       a minimum spanning forest
    {
        PriorityQueue<ComparableEdge<String,Integer>> q =
          new SkewHeap<ComparableEdge<String,Integer>>(); // for ranking edges
        g.reset();              // clear marks
        
        // add all edges to priority queue.
        // a comparable edge allows ordering based on edge weight
        Iterator<Edge<String,Integer>> ei = g.edges();
        while (ei.hasNext())
        {
            q.add(new ComparableEdge<String,Integer>(ei.next()));
        }

        while (!q.isEmpty())
        {
            ComparableEdge<String,Integer> e = q.remove();

            if (g.isVisited(e.here()) &&
                g.isVisited(e.there())) continue;
            // edges that mention at least one new vertex
            // are added to the tree.
            g.visit(e.here());
            g.visit(e.there());
            g.visitEdge(g.getEdge(e.here(),e.there()));
        }
    }
}

/*
albany_ny binghamton_ny 142
albany_ny burlington_vt 154
albany_ny lee_ma 42
albany_ny newburgh_ny 88
albany_ny plattsburgh_ny 159
albany_ny springfield_ma 85
albany_ny syracuse_ny 146
albany_ny troy_ny 14
albert_lea_mn des_moines_ia 148
albert_lea_mn madison_wi 262
albert_lea_mn minneapolis_mn 93
albert_lea_mn sioux_falls_sd 168
albuquerque_nm colorado_springs_co 365
albuquerque_nm el_paso_tx 257
albuquerque_nm flagstaff_az 323
albuquerque_nm santa_rosa_nm 114
allentown_pa harrisburg_pa 73
allentown_pa new_york_ny 103
allentown_pa newark_nj 100
allentown_pa philadelphia_pa 60
allentown_pa scranton_pa 72
allentown_pa stroudsburg_pa 41
amarillo_tx canyon_tx 16
amarillo_tx oklahoma_city_ok 267
amarillo_tx santa_rosa_nm 170
asheville_nc charlotte_nc 118
asheville_nc columbia_sc 156
asheville_nc greenville_sc 62
asheville_nc knoxville_tn 118
asheville_nc winston_salem_nc 144
atlanta_ga augusta_ga 144
atlanta_ga birmingham_al 149
atlanta_ga chattanooga_tn 109
atlanta_ga greenville_sc 151
atlanta_ga macon_ga 82
atlanta_ga montgomery_al 165
atlanta_ga tallahassee_fl 275
atlantic_city_nj newark_nj 108
atlantic_city_nj philadelphia_pa 63
atlantic_city_nj salisbury_md 95
augusta_ga columbia_sc 79
augusta_ga greenville_sc 113
augusta_ga jacksonville_fl 253
augusta_ga savannah_ga 133
baldwin_fl jacksonville_fl 19
baldwin_fl lake_city_fl 40
baldwin_fl waldo_fl 37
baltimore_md hagerstown_md 75
baltimore_md harrisburg_pa 79
baltimore_md newark_nj 175
baltimore_md salisbury_md 111
baltimore_md washington_dc 37
baltimore_md wilmington_de 67
barstow_ca kingman_az 203
barstow_ca las_vegas_nv 155
barstow_ca san_bernadino_ca 74
baton_rouge_la houston_tx 253
baton_rouge_la jackson_ms 169
baton_rouge_la mobile_al 213
baton_rouge_la new_orleans_la 89
baton_rouge_la shreveport_la 236
battle_creek_mi detroit_mi 124
battle_creek_mi fort_wayne_in 100
battle_creek_mi gary_in 128
battle_creek_mi lansing_mi 56
billings_mt bismark_nd 418
billings_mt buffalo_wy 161
billings_mt butte_mt 237
billings_mt great_falls_mt 287
biloxi_ms gulfport_ms 15
biloxi_ms mobile_al 58
binghamton_ny scranton_pa 52
binghamton_ny syracuse_ny 64
birmingham_al chattanooga_tn 146
birmingham_al memphis_tn 241
birmingham_al montgomery_al 90
birmingham_al nashville_tn 192
birmingham_al tuscaloosa_al 56
bismark_nd fargo_nd 191
bismark_nd pierre_sd 211
bloomington_il champaign_il 52
bloomington_il chicago_il 133
bloomington_il davenport_ia 140
bloomington_il springfield_il 61
boise_id ogden_ut 323
boise_id pendleton_or 217
boise_id pocatello_id 234
boise_id spokane_wa 391
boston_ma greenfield_ma 99
boston_ma hartford_ct 102
boston_ma manchester_nh 51
boston_ma portland_me 109
boston_ma providence_ri 51
boston_ma springfield_ma 92
breezewood_pa hagerstown_md 47
breezewood_pa harrisburg_pa 83
breezewood_pa morgantown_wv 134
breezewood_pa pittsburgh_pa 121
breezewood_pa wheeling_wv 148
buffalo_ny erie_pa 108
buffalo_ny syracuse_ny 156
buffalo_wy casper_wy 305
buffalo_wy rapid_city_sd 207
burlington_vt white_river_jct._vt 96
butte_mt helena_mt 65
butte_mt missoula_mt 130
butte_mt pocatello_id 261
cambridge_oh charleston_wv 131
cambridge_oh cleveland_oh 116
cambridge_oh columbus_oh 80
cambridge_oh wheeling_wv 48
cambridge_oh zanesville_oh 26
cannon(afb)_nm canyon_tx 99
cannon(afb)_nm santa_rosa_nm 92
captain_cook_hi kailua_kona_hi 11
captain_cook_hi mountain_view_hi 84
casa_grande_az phoenix_az 58
casa_grande_az san_diego_ca 356
casa_grande_az tucson_az 63
casper_wy cheyenne_wy 184
casper_wy rapid_city_sd 254
champaign_il chicago_il 133
champaign_il effingham_il 70
champaign_il indianapolis_in 114
champaign_il springfield_il 86
charleston_sc columbia_sc 95
charleston_sc florence_sc 112
charleston_sc santee_sc 54
charleston_sc savannah_ga 105
charleston_sc wilmington_nc 170
charleston_wv huntington_wv 47
charleston_wv morgantown_wv 156
charleston_wv staunton_va 209
charleston_wv wytheville_va 123
charlotte_nc columbia_sc 95
charlotte_nc greensboro_nc 94
charlotte_nc greenville_sc 105
charlotte_nc lumberton_nc 126
charlotte_nc wadesboro_nc 51
charlotte_nc winston_salem_nc 80
charlotte_nc wytheville_va 134
chattanooga_tn knoxville_tn 109
chattanooga_tn nashville_tn 133
cheraw_sc columbia_sc 80
cheraw_sc wadesboro_nc 23
cheyenne_wy denver_co 98
cheyenne_wy north_platte_ne 223
cheyenne_wy ogden_ut 444
cheyenne_wy rapid_city_sd 316
cheyenne_wy salt_lake_city_ut 457
chicago_il davenport_ia 177
chicago_il gary_in 32
chicago_il madison_wi 140
chicago_il milwaukee_wi 87
cincinnati_oh columbus_oh 109
cincinnati_oh dayton_oh 54
cincinnati_oh huntington_wv 161
cincinnati_oh indianapolis_in 110
cincinnati_oh lexington_ky 78
cincinnati_oh louisville_ky 101
cincinnati_oh sardinia_oh 35
cleveland_oh columbus_oh 140
cleveland_oh erie_pa 80
cleveland_oh mercer_pa 94
cleveland_oh pittsburgh_pa 129
cleveland_oh toledo_oh 112
colorado_springs_co denver_co 67
colorado_springs_co salina_ks 415
columbia_sc florence_sc 80
columbia_sc greenville_sc 104
columbia_sc santee_sc 66
columbus_oh dayton_oh 71
columbus_oh huntington_wv 131
columbus_oh mercer_pa 203
columbus_oh toledo_oh 135
columbus_oh zanesville_oh 54
concord_nh manchester_nh 20
concord_nh portland_me 95
concord_nh white_river_jct._vt 66
cove_fort_ut green_river_ut 158
cove_fort_ut las_vegas_nv 251
cove_fort_ut salt_lake_city_ut 178
dallas_tx ft.worth_tx 32
dallas_tx houston_tx 245
dallas_tx oklahoma_city_ok 205
dallas_tx san_antonio_tx 270
dallas_tx shreveport_la 195
dallas_tx texarkana_tx 183
davenport_ia des_moines_ia 160
davenport_ia gary_in 183
dayton_oh indianapolis_in 104
dayton_oh toledo_oh 147
daytona_beach_fl ft._lauderdale_fl 243
daytona_beach_fl jacksonville_fl 99
daytona_beach_fl orlando_fl 58
denver_co green_river_ut 350
denver_co north_platte_ne 269
denver_co salina_ks 429
des_moines_ia kansas_city_mo 198
des_moines_ia omaha_ne 128
des_moines_ia sioux_city_ia 183
detroit_mi flint_mi 67
detroit_mi gary_in 248
detroit_mi grand_rapids_mi 148
detroit_mi sault_ste._marie_mi 242
detroit_mi toledo_oh 60
dover_de salisbury_md 60
dover_de washington_dc 96
dover_de wilmington_de 46
duluth_mn grand_forks_nd 266
duluth_mn mackinaw_city_mi 414
duluth_mn minneapolis_mn 156
duluth_mn sault_ste._marie_mi 422
durham_nc greensboro_nc 54
durham_nc petersburg_va 131
durham_nc raleigh_nc 27
effingham_il indianapolis_in 136
effingham_il mt._vernon_il 71
effingham_il st._louis_mo 101
el_paso_tx ft.worth_tx 588
el_paso_tx san_antonio_tx 552
el_paso_tx tucson_az 317
ellensburg_wa pendleton_or 233
ellensburg_wa seattle_wa 103
ellensburg_wa spokane_wa 171
erie_pa mercer_pa 66
fargo_nd grand_forks_nd 80
fargo_nd minneapolis_mn 240
fargo_nd sioux_falls_sd 261
flagstaff_az kingman_az 159
flagstaff_az phoenix_az 135
flint_mi lansing_mi 51
flint_mi standish_mi 73
flint_mi toledo_oh 106
florence_sc lumberton_nc 53
florence_sc rocky_mount_nc 165
florence_sc santee_sc 70
fort_walton_bch_fl panama_city_fl 55
fort_walton_bch_fl pensacola_fl 40
fort_wayne_in upland_in 51
ft._lauderdale_fl ft._myers_fl 99
ft._lauderdale_fl miami_fl 27
ft._myers_fl port_charlotte_fl 22
ft.smith_ar little_rock_ar 153
ft.smith_ar oklahoma_city_ok 179
ft.smith_ar texarkana_tx 181
ft.smith_ar tulsa_ok 116
ft.worth_tx oklahoma_city_ok 203
ft.worth_tx san_antonio_tx 267
gainesville_fl lake_city_fl 42
gainesville_fl tampa_fl 129
gainesville_fl waldo_fl 19
gary_in grand_rapids_mi 144
gary_in indianapolis_in 145
gary_in south_bend_in 66
grand_forks_nd minot_nd 215
grand_rapids_mi sault_ste._marie_mi 276
great_falls_mt havre_mt 113
great_falls_mt helena_mt 91
green_bay_wi madison_wi 131
green_bay_wi milwaukee_wi 116
green_bay_wi sault_ste._marie_mi 285
green_river_ut salt_lake_city_ut 176
greenfield_ma north_adams_ma 28
greenfield_ma springfield_ma 38
greensboro_nc roanoke_va 109
greensboro_nc winston_salem_nc 30
gulfport_ms hattisburg_ms 66
gulfport_ms new_orleans_la 80
hagerstown_md harrisburg_pa 77
hagerstown_md morgantown_wv 133
hagerstown_md strasburg_va 51
hagerstown_md washington_dc 64
hanamaulu_hi kapaa_hi 5
hanamaulu_hi lihue_hi 2
harrisburg_pa philadelphia_pa 105
harrisburg_pa scranton_pa 115
hartford_ct new_haven_ct 40
hartford_ct newburgh_ny 101
hartford_ct providence_ri 74
hartford_ct springfield_ma 30
hattisburg_ms jackson_ms 89
hattisburg_ms meridian_ms 86
hattisburg_ms mobile_al 106
hattisburg_ms new_orleans_la 98
havre_mt minot_nd 439
helena_mt missoula_mt 124
hilo_hi kailua_kona_hi 87
hilo_hi mountain_view_hi 15
honolulu_hi kaneohe_hi 9
honolulu_hi pearl_city_hi 12
honolulu_hi waikiki_beach_hi 1
houston_tx san_antonio_tx 197
houston_tx shreveport_la 226
huntington_wv lexington_ky 112
indianapolis_in louisville_ky 114
indianapolis_in upland_in 59
iron_mountain_mi mackinaw_city_mi 197
iron_mountain_mi minneapolis_mn 219
jackson_ms little_rock_ar 267
jackson_ms meridian_ms 90
jackson_ms new_orleans_la 183
jackson_ms shreveport_la 213
jackson_ms winona_ms 116
jacksonville_fl santee_sc 232
jacksonville_fl savannah_ga 143
jacksonville_fl tampa_fl 198
kahului_hi kihei_hi 6
kahului_hi wailuku_hi 2
kailua_kona_hi kamuela_hi 39
kalaheo_hi kekaha_hi 13
kalaheo_hi lihue_hi 12
kansas_city_mo omaha_ne 184
kansas_city_mo st._louis_mo 254
kansas_city_mo topeka_ks 60
kansas_city_mo tulsa_ok 259
kansas_city_mo wichita_ks 201
kaunakakai_hi waialua_hi 19
kingman_az las_vegas_nv 103
kingman_az phoenix_az 184
knoxville_tn lexington_ky 173
knoxville_tn nashville_tn 170
knoxville_tn wytheville_va 187
lahaina_hi wailuku_hi 30
lake_city_fl macon_ga 199
lake_city_fl orlando_fl 160
lake_city_fl tallahassee_fl 98
lake_city_fl tampa_fl 169
las_vegas_nv reno_nv 446
lee_ma pittsfield_ma 11
lee_ma springfield_ma 44
lexington_ky louisville_ky 78
lexington_ky nashville_tn 214
lexington_ky paducah_ky 262
little_rock_ar memphis_tn 138
little_rock_ar springfield_mo 218
little_rock_ar texarkana_tx 130
los_angeles_ca sacramento_ca 382
los_angeles_ca san_bernadino_ca 40
los_angeles_ca san_diego_ca 127
los_angeles_ca san_francisco_ca 387
louisville_ky mt._vernon_il 190
louisville_ky nashville_tn 180
louisville_ky paducah_ky 220
lumberton_nc raleigh_nc 95
lumberton_nc rocky_mount_nc 126
lumberton_nc wilmington_nc 80
mackinaw_city_mi oscoda_mi 149
mackinaw_city_mi sault_ste._marie_mi 57
mackinaw_city_mi standish_mi 154
macon_ga montgomery_al 180
macon_ga savannah_ga 166
macon_ga tallahassee_fl 190
madison_wi milwaukee_wi 77
madison_wi minneapolis_mn 271
madison_wi springfield_il 290
marion_il mt._vernon_il 53
marion_il paducah_ky 42
marion_il sikeston_mo 60
medart_fl panama_city_fl 70
medart_fl tallahassee_fl 28
memphis_tn nashville_tn 219
memphis_tn sikeston_mo 142
memphis_tn winona_ms 96
mercer_pa pittsburgh_pa 61
mercer_pa stroudsburg_pa 303
meridian_ms mobile_al 129
meridian_ms montgomery_al 160
meridian_ms tuscaloosa_al 88
miami_fl orlando_fl 236
miami_fl tampa_fl 266
missoula_mt spokane_wa 201
mobile_al montgomery_al 171
mobile_al new_orleans_la 153
mobile_al pensacola_fl 59
montgomery_al pensacola_fl 159
montgomery_al tallahassee_fl 208
montgomery_al tuscaloosa_al 95
morgantown_wv pittsburgh_pa 69
morgantown_wv wheeling_wv 79
mt._vernon_il scott_afb_il 55
nashville_tn paducah_ky 148
new_haven_ct new_york_ny 70
new_haven_ct providence_ri 102
new_york_ny newark_nj 19
new_york_ny newburgh_ny 66
new_york_ny stroudsburg_pa 78
newark_nj newburgh_ny 69
newark_nj philadelphia_pa 82
newark_nj stroudsburg_pa 66
newburgh_ny scranton_pa 96
norfolk_va richmond_va 99
norfolk_va rocky_mount_nc 136
norfolk_va salisbury_md 129
norfolk_va wilmington_nc 259
north_adams_ma williamstown_ma 5
north_platte_ne omaha_ne 299
north_platte_ne pierre_sd 267
ogden_ut pocatello_id 132
ogden_ut salt_lake_city_ut 34
oklahoma_city_ok tulsa_ok 103
oklahoma_city_ok wichita_ks 158
omaha_ne sioux_city_ia 98
orlando_fl tampa_fl 84
oscoda_mi standish_mi 57
paducah_ky sikeston_mo 59
pearl_city_hi wahiawa_hi 8
pendleton_or portland_or 211
pendleton_or spokane_wa 203
pensacola_fl tallahassee_fl 200
petersburg_va richmond_va 24
petersburg_va rocky_mount_nc 101
philadelphia_pa wilmington_de 29
phoenix_az san_bernadino_ca 334
pierre_sd rapid_city_sd 192
pierre_sd sioux_falls_sd 222
pittsburgh_pa wheeling_wv 57
pittsfield_ma williamstown_ma 20
plattsburgh_ny watertown_ny 170
port_charlotte_fl tampa_fl 93
portland_or seattle_wa 173
portland_or weed_ca 363
raleigh_nc rocky_mount_nc 51
rapid_city_sd sioux_falls_sd 346
reno_nv sacramento_ca 134
reno_nv salt_lake_city_ut 531
reno_nv weed_ca 265
richmond_va staunton_va 106
richmond_va washington_dc 114
roanoke_va staunton_va 81
roanoke_va wytheville_va 67
sacramento_ca san_francisco_ca 90
sacramento_ca weed_ca 227
salina_ks topeka_ks 114
salina_ks wichita_ks 87
salisbury_md washington_dc 120
san_bernadino_ca san_diego_ca 115
san_francisco_ca weed_ca 282
santee_sc savannah_ga 96
scott_afb_il st._louis_mo 17
scranton_pa stroudsburg_pa 51
shreveport_la texarkana_tx 70
sikeston_mo springfield_mo 241
sikeston_mo st._louis_mo 152
sioux_city_ia sioux_falls_sd 85
south_bend_in toledo_oh 126
springfield_il st._louis_mo 94
springfield_ma white_river_jct._vt 118
springfield_mo st._louis_mo 214
springfield_mo tulsa_ok 180
staunton_va strasburg_va 76
strasburg_va washington_dc 72
syracuse_ny watertown_ny 74
tallahassee_fl tampa_fl 252
topeka_ks wichita_ks 142
troy_ny williamstown_ma 34
tulsa_ok wichita_ks 175
tuscaloosa_al winona_ms 144
wadesboro_nc winston_salem_nc 100
winston_salem_nc wytheville_va 83
In original graph there are 237 vertices and 460 edges.
<Edge: visited scranton_pa <-> stroudsburg_pa>
<Edge: visited philadelphia_pa <-> wilmington_de>
<Edge: visited portland_or <-> seattle_wa>
<Edge: visited ft.smith_ar <-> tulsa_ok>
<Edge: visited charleston_sc <-> santee_sc>
<Edge: visited norfolk_va <-> richmond_va>
<Edge: visited butte_mt <-> helena_mt>
<Edge: visited cannon(afb)_nm <-> santa_rosa_nm>
<Edge: visited pendleton_or <-> spokane_wa>
<Edge: visited cincinnati_oh <-> dayton_oh>
<Edge: visited cincinnati_oh <-> lexington_ky>
<Edge: visited cincinnati_oh <-> sardinia_oh>
<Edge: visited ellensburg_wa <-> seattle_wa>
<Edge: visited ellensburg_wa <-> spokane_wa>
<Edge: visited hilo_hi <-> mountain_view_hi>
<Edge: visited kailua_kona_hi <-> kamuela_hi>
<Edge: visited battle_creek_mi <-> lansing_mi>
<Edge: visited fargo_nd <-> grand_forks_nd>
<Edge: visited detroit_mi <-> toledo_oh>
<Edge: visited casa_grande_az <-> phoenix_az>
<Edge: visited casa_grande_az <-> tucson_az>
<Edge: visited marion_il <-> mt._vernon_il>
<Edge: visited marion_il <-> paducah_ky>
<Edge: visited new_york_ny <-> newark_nj>
<Edge: visited new_york_ny <-> newburgh_ny>
<Edge: visited gulfport_ms <-> hattisburg_ms>
<Edge: visited gulfport_ms <-> new_orleans_la>
<Edge: visited omaha_ne <-> sioux_city_ia>
<Edge: visited iron_mountain_mi <-> mackinaw_city_mi>
<Edge: visited hagerstown_md <-> strasburg_va>
<Edge: visited great_falls_mt <-> havre_mt>
<Edge: visited great_falls_mt <-> helena_mt>
<Edge: visited hartford_ct <-> new_haven_ct>
<Edge: visited hartford_ct <-> springfield_ma>
<Edge: visited concord_nh <-> manchester_nh>
<Edge: visited concord_nh <-> portland_me>
<Edge: visited concord_nh <-> white_river_jct._vt>
<Edge: visited sacramento_ca <-> san_francisco_ca>
<Edge: visited sacramento_ca <-> weed_ca>
<Edge: visited scott_afb_il <-> st._louis_mo>
<Edge: visited sioux_city_ia <-> sioux_falls_sd>
<Edge: visited kaunakakai_hi <-> waialua_hi>
<Edge: visited flint_mi <-> lansing_mi>
<Edge: visited morgantown_wv <-> pittsburgh_pa>
<Edge: visited baton_rouge_la <-> new_orleans_la>
<Edge: visited bismark_nd <-> fargo_nd>
<Edge: visited albuquerque_nm <-> el_paso_tx>
<Edge: visited albuquerque_nm <-> santa_rosa_nm>
<Edge: visited columbia_sc <-> santee_sc>
<Edge: visited baldwin_fl <-> jacksonville_fl>
<Edge: visited baldwin_fl <-> lake_city_fl>
<Edge: visited chattanooga_tn <-> knoxville_tn>
<Edge: visited chattanooga_tn <-> nashville_tn>
<Edge: visited kingman_az <-> las_vegas_nv>
<Edge: visited springfield_mo <-> tulsa_ok>
<Edge: visited san_bernadino_ca <-> san_diego_ca>
<Edge: visited kansas_city_mo <-> topeka_ks>
<Edge: visited madison_wi <-> milwaukee_wi>
<Edge: visited lahaina_hi <-> wailuku_hi>
<Edge: visited fort_walton_bch_fl <-> panama_city_fl>
<Edge: visited fort_walton_bch_fl <-> pensacola_fl>
<Edge: visited boston_ma <-> manchester_nh>
<Edge: visited boston_ma <-> providence_ri>
<Edge: visited erie_pa <-> mercer_pa>
<Edge: visited ft._lauderdale_fl <-> miami_fl>
<Edge: visited billings_mt <-> buffalo_wy>
<Edge: visited kahului_hi <-> kihei_hi>
<Edge: visited kahului_hi <-> wailuku_hi>
<Edge: visited mackinaw_city_mi <-> sault_ste._marie_mi>
<Edge: visited des_moines_ia <-> omaha_ne>
<Edge: visited los_angeles_ca <-> san_bernadino_ca>
<Edge: visited chicago_il <-> gary_in>
<Edge: visited shreveport_la <-> texarkana_tx>
<Edge: visited green_bay_wi <-> milwaukee_wi>
<Edge: visited greenfield_ma <-> north_adams_ma>
<Edge: visited dallas_tx <-> ft.worth_tx>
<Edge: visited allentown_pa <-> harrisburg_pa>
<Edge: visited allentown_pa <-> stroudsburg_pa>
<Edge: visited burlington_vt <-> white_river_jct._vt>
<Edge: visited florence_sc <-> lumberton_nc>
<Edge: visited kalaheo_hi <-> kekaha_hi>
<Edge: visited kalaheo_hi <-> lihue_hi>
<Edge: visited raleigh_nc <-> rocky_mount_nc>
<Edge: visited little_rock_ar <-> texarkana_tx>
<Edge: visited hattisburg_ms <-> jackson_ms>
<Edge: visited hattisburg_ms <-> meridian_ms>
<Edge: visited fort_wayne_in <-> upland_in>
<Edge: visited captain_cook_hi <-> kailua_kona_hi>
<Edge: visited ogden_ut <-> pocatello_id>
<Edge: visited ogden_ut <-> salt_lake_city_ut>
<Edge: visited pearl_city_hi <-> wahiawa_hi>
<Edge: visited grand_forks_nd <-> minot_nd>
<Edge: visited cove_fort_ut <-> green_river_ut>
<Edge: visited biloxi_ms <-> gulfport_ms>
<Edge: visited biloxi_ms <-> mobile_al>
<Edge: visited champaign_il <-> effingham_il>
<Edge: visited ft._myers_fl <-> port_charlotte_fl>
<Edge: visited lee_ma <-> pittsfield_ma>
<Edge: visited lumberton_nc <-> wilmington_nc>
<Edge: visited helena_mt <-> missoula_mt>
<Edge: visited augusta_ga <-> columbia_sc>
<Edge: visited binghamton_ny <-> scranton_pa>
<Edge: visited binghamton_ny <-> syracuse_ny>
<Edge: visited oklahoma_city_ok <-> tulsa_ok>
<Edge: visited atlanta_ga <-> macon_ga>
<Edge: visited medart_fl <-> tallahassee_fl>
<Edge: visited casper_wy <-> cheyenne_wy>
<Edge: visited breezewood_pa <-> hagerstown_md>
<Edge: visited baltimore_md <-> washington_dc>
<Edge: visited cheyenne_wy <-> denver_co>
<Edge: visited cheyenne_wy <-> north_platte_ne>
<Edge: visited asheville_nc <-> greenville_sc>
<Edge: visited pierre_sd <-> rapid_city_sd>
<Edge: visited hanamaulu_hi <-> kapaa_hi>
<Edge: visited hanamaulu_hi <-> lihue_hi>
<Edge: visited flagstaff_az <-> phoenix_az>
<Edge: visited santee_sc <-> savannah_ga>
<Edge: visited durham_nc <-> raleigh_nc>
<Edge: visited houston_tx <-> san_antonio_tx>
<Edge: visited paducah_ky <-> sikeston_mo>
<Edge: visited honolulu_hi <-> kaneohe_hi>
<Edge: visited honolulu_hi <-> waikiki_beach_hi>
<Edge: visited oscoda_mi <-> standish_mi>
<Edge: visited indianapolis_in <-> upland_in>
<Edge: visited petersburg_va <-> richmond_va>
<Edge: visited birmingham_al <-> montgomery_al>
<Edge: visited birmingham_al <-> tuscaloosa_al>
<Edge: visited reno_nv <-> sacramento_ca>
<Edge: visited barstow_ca <-> san_bernadino_ca>
<Edge: visited roanoke_va <-> wytheville_va>
<Edge: visited daytona_beach_fl <-> orlando_fl>
<Edge: visited syracuse_ny <-> watertown_ny>
<Edge: visited albany_ny <-> plattsburgh_ny>
<Edge: visited albany_ny <-> troy_ny>
<Edge: visited colorado_springs_co <-> denver_co>
<Edge: visited duluth_mn <-> minneapolis_mn>
<Edge: visited mercer_pa <-> pittsburgh_pa>
<Edge: visited gary_in <-> grand_rapids_mi>
<Edge: visited gary_in <-> south_bend_in>
<Edge: visited gainesville_fl <-> waldo_fl>
<Edge: visited charlotte_nc <-> wadesboro_nc>
<Edge: visited north_adams_ma <-> williamstown_ma>
<Edge: visited staunton_va <-> strasburg_va>
<Edge: visited lexington_ky <-> louisville_ky>
<Edge: visited amarillo_tx <-> canyon_tx>
<Edge: visited columbus_oh <-> zanesville_oh>
<Edge: visited greensboro_nc <-> winston_salem_nc>
<Edge: visited charleston_wv <-> huntington_wv>
<Edge: visited pittsburgh_pa <-> wheeling_wv>
<Edge: visited cambridge_oh <-> wheeling_wv>
<Edge: visited cambridge_oh <-> zanesville_oh>
<Edge: visited atlantic_city_nj <-> philadelphia_pa>
<Edge: visited bloomington_il <-> champaign_il>
<Edge: visited bloomington_il <-> davenport_ia>
<Edge: visited bloomington_il <-> springfield_il>
<Edge: visited cleveland_oh <-> erie_pa>
<Edge: visited dover_de <-> salisbury_md>
<Edge: visited dover_de <-> wilmington_de>
<Edge: visited albert_lea_mn <-> minneapolis_mn>
<Edge: visited boise_id <-> pendleton_or>
<Edge: visited cheraw_sc <-> wadesboro_nc>
<Edge: visited buffalo_ny <-> erie_pa>
<Edge: visited memphis_tn <-> winona_ms>
<Edge: visited orlando_fl <-> tampa_fl>
<Edge: visited salina_ks <-> wichita_ks>
Total weight is 12416 over 165 edges.
*/
