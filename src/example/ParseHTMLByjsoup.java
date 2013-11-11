package example;

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



public class ParseHTMLByjsoup {
	public static String generateXiaMiJS(String string){
		return "http://www.xiami.com/widget/player-single?uid=0"
	           +"&sid="+string+"&mode=js";
	}
	public static void main(String[] strings) {
		File file = new File("C:\\Users\\I079951\\Desktop\\test.html");
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(fileInputStream));
			StringBuilder sb = new StringBuilder();
		      String line;
		      while(( line = bufferedReader.readLine()) != null ) {
		         sb.append( line );
		         sb.append( '\n' );
		      }	
		      
		     Document document = Jsoup.parse(file,"UTF-8","");
		    
		    
		     ArrayList<String> nameStrings = new ArrayList<String>();
		     
		     Elements styleNameElements = document.getElementsByClass("am_img");
		     for (Element element : styleNameElements) {
				String nameString = element.children().first().attr("title");
				System.out.println(nameString);
				nameStrings.add(nameString);
			}
		     
		     Elements styleElements = document.select("div[class=song_list i_border]");
		     System.out.println("SIZE:" + styleElements.size());
		     
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
		    	System.out.println(nameStrings.get(i));
		    	ArrayList<String> songs = songsOfStylesList.get(i);
		    	for (String song : songs) {
					System.out.println(song);
				}
		    }
		     
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
