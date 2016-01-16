package org.usfirst.frc.team2537.robot;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public final class SaberMessage {
	public static void printMessage() {
		// BufferedReader in = null;
		// String s = "";
		// try {
		// in = new BufferedReader(new FileReader("SaberPic"));
		// } catch (FileNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// Scanner fileScan = new Scanner(in);
		// try {
		// while(true) {
		// s += fileScan.nextLine() + "\n";
		// }
		// } catch(Exception e) {
		//
		// }
		String s = "                 `-..`......`-:.```````:-``...`````````-/-``.:-```````..`...-.`.......-++:..`        \n" +
"                `.-..`.`...``:-.```````+```.-``````````-:-```.:.``````...`...:.`.......:o+-..       \n" +
"                `........`.``:.```````-+```.-``````````::..```:..``````......--........-+++-.       \n" +
"                 .--..`..`.``:.```````/+```.-``````````::..```-:`.`````......./........-:o++-`      \n" +
"                ..--..`..`.`.:.```````++```.:``````````/:.`.``./.`.````......./:.......--o++:.      \n" +
"                `.--..`...```:.```.``.o+```.+.`````````/:.`.```::``.```.......-+.....-.-:+++/-      \n" +
"`                .--..`...```:.```..`-o/.``-+-.``.`````/:.``.``-/.`.```....-..-o.....:--:+++/:`     \n" +
"``               .--......```:.```-..:+:-``-+/..`..`.``::-```.`./:``.......:..-o-..-./--:+/o/:.     \n" +
" ``             ` .-......```--`.`/..:+:-``:++..`....``::-```...:/-``....../..-o:---.+/-/+:+/--`    \n" +
"  ``           `` `-......````/...+-.:+:...-+/:-`...-`.-::````..-//.`....-./::/s+----o/-++-//-.``   \n" +
"   ``          `   -..`....```:...//.:+:`-.-++:-:...--../:.````..:/:``-.-::+/-/o/---/o//o/-:--..  ` \n" +
"     `         `   -..`..-.``.-/--/+::+/``--/+/.:-.../..::.`````--:/:::/---o//oo/--:oo/++---.--.`  `\n" +
"      `        .   -..``..-.```:--:+::o+--:/+++-.:-..::--/-``````-/++:/osyo+osyh/-/ooo/+:---..-..   \n" +
"       `       ``  ...``..-..``./-/++oyso+++o/+++/+/::+:-::`````.-:/shhhhhy---/y:/oso+/:-......--   \n" +
"        `      ``` ....``..-.```-+ys++hhhhhhhy+++:::::/+:---``.``.-oyhdhsos:`.+/:/oss-/-.......--   \n" +
"         ``     .  ....``..--.`.`:h+-ohhhddsoo/-//-.--://:--.`````-/soyo//+: .---/soo/:--.......-`  \n" +
" `        -`    `` `...```..-....`/s:/hyssy//:/ `-.````.-::..``````.+////.-``-..-/soo+-:........-.  \n" +
"  ``      `-.    ```....``...--...`//-oo////- `  `````````..````````-::::/.`....-+ooo+:-..........  \n" +
"   `-`     `..    `.-...```.../-....//.:/:::::. ``````````````````````....``....-soo+.---.-.....`.  \n" +
"    `-.      ..     -....```..-+/....:/.`--.-.``````````````````````````````....+o++. `--..-....`.` \n" +
"      .-`     `-`   .....````../++-...-+-```````````````````````````````````...-s+-   `.-..-....`..`\n" +
"       `-`     `-.  ......``..--+++/-..-/:```````````````````.`````````````....+`       --.-.....``.\n" +
"         :.     `.. `......```.-:ooo+/-..:/-`````````````````.````````````....+s:.      --..-....`. \n" +
"          --`     .-`..`...````.-+ooso+/-.-//.``````````````````````````....-odhhhyo:.` `:..-....`. \n" +
"           -:`     `-..`....````.:-+hhyo/:--:/:.``````````````````````....-/yhhdddddhhs/`:.---..... \n" +
"           `.:`     `-.`.....````.:syhhhy+----::-```````````````````....-/yh+/oyhhhddhhhh:.-.--.-.. \n" +
"      -/++++:-/.     `.-`.....``...:ysyddhs/----::-.``````````````....-+hdddd+``-+syhhddd:--`.--... \n" +
"    `oyyyddddsso:`   ``.-.`....`....:hyshdhoo+:-.--::.``````````....-/sdmmddddh/`  .+dddd/-- .--... \n" +
"   `ydhhhmNNmmdhs/``.--.`--:/-.......:hhsydsoooo+/-----...........-+ssymmmmmmmddh+.``yddd--. `--.-/s\n" +
"  `ydmdhymmmmmmdhyo..:::-.:+oo:-....../hdyoysooooooo+/:--.......:+sooshdmmmmmmddddds+yddy--`  ---odm\n" +
" `ydmmdyssddddhhyyso:.://:--oyy+--...../dhysyssooooo+++++/::--/+oooosshhhmmmmmmmmmmmmmmd+-.  `:./hdm\n" +
" odmmmho:./syyyhyyyss+.://::-oyhs:-.....oddyso/-..--::///::::::::::////+oossyyyyymmmmmmd:. `/y+.ymmm\n" +
":dmmNmh-````-+syhhhhhyo-:///:-+syho---..-ymhshyo:...````  `````-/osss+///+ooshhysdmNmdo/./ymmm/:mNNN\n" +
"hmNNNmho:-````.:oyyyyyss::////:/o/+y/:---:dysh+:..---.`      `.://--:+///+osdhyssdmmNmmhmNNmmd-ymNNN\n" +
"dNNNNmhyso+:.```.oyyyyyss/:////:::`:ys::--oysy-...`......`   `.-:-.--://+oshysyhddNNNNNNNNmhd+ohdddh\n" +
"mNNNmhhhyyyso/::osyyhhhhyy+:////:::..sho+-:yyh--...```````.````--:--:+syooyshddhhdNNymmNNNmhhyhhdhhh\n" +
"mNNNdhhhhhyyyyyyyyhhddmms..+://///-/-.odyy:ydd:/+o+/-...``` ````.-::::+yyosyyyyyhmddsoyydmhhmddddhhh\n" +
"NNNmhhhhhhhhhhhhhdddhmmdy+.`///////:/:-/hdhsdmo/-..```````````````..:/sysossyyhdmNhyhmNmmNdhddhddhhh\n" +
"NNNdhhhddhhhhhdddhdhddhhhds..:/:///////:+hhhhy+/:///::--.`````````.-:+oossyhdmmmdddmmmNNNNNmddhmhhhh\n" +
"NNmdhhdddhhhhddhhmdhmdhhhhys-.-/:++++osyhhhhhhh+:://///////://+++ossyhddmddhhyhhhhhdddmNNNNMNmdmhdhh\n";
		System.out.println(s + "\n Welcome to Saber bot!");

	}
}
