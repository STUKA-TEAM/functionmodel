package song;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import tools.DataBaseUtil;

public class SaveSongCatalog {
	public static String generateXiaMiJS(String string){
		return "http://www.xiami.com/widget/player-single?uid=0"
	           +"&sid="+string+"&mode=js";
	}
	public static void main(String[] strings) {
		
		try {
			 File file = new File("C:\\Users\\I079951\\Desktop\\test.html");
		     Document document = Jsoup.parse(file,"UTF-8","");	     
			 DataBaseUtil dataBaseUtil = DataBaseUtil.init();
		     ArrayList<String> nameStrings = new ArrayList<String>();
		     Elements styleNameElements = document.getElementsByClass("am_img");
		     for (Element element : styleNameElements) {
				String nameString = element.children().first().attr("title");
				
				dataBaseUtil.SqlExec("insert into song_styles values(default,'"+nameString+"')");
				nameStrings.add(nameString);
			}
		     
		     Elements styleElements = document.select("div[class=song_list i_border]");
		     ArrayList<ArrayList<String>> songsOfStylesList = new ArrayList<>();
		     for (Element styleElement : styleElements) {
		    	ArrayList<String> songsOfStyleList = new ArrayList<>();
				Elements songLiTagsElements = styleElement.children().first().children();
				for (Element songLiTag : songLiTagsElements) {
					String songLinkString = songLiTag.select("span[class=song_name]").first().children().first().attr("href");
					String[] splitsStrings = songLinkString.split("/");
					String songIdString = splitsStrings[splitsStrings.length-1];
					String xiMimusicLinkString = generateXiaMiJS(songIdString);
					songsOfStyleList.add(xiMimusicLinkString);
				}
				songsOfStylesList.add(songsOfStyleList);				
			}
		    
		    for(int i = 0; i < songsOfStylesList.size(); i++){
		    	ArrayList<String> songs = songsOfStylesList.get(i);
		    	int styleId = i+1;
		    	for (String song : songs) {
					dataBaseUtil.SqlExec("insert into song_catalog values(default,"+styleId+",'"+song+"')");
				}
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
}
