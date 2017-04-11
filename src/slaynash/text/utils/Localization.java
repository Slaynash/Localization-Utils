package slaynash.text.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Localization {
	private static List<TextDef> texts = new ArrayList<TextDef>();
	private static String localizationPath;

	public static void setLang(String lang){
		File locFile;
		if(lang.toLowerCase().split("_",2)[0].equals("fr")){
			locFile = new File(localizationPath+"/fr.hgf");
		}
		else{
			locFile = new File(localizationPath+"/en.hgf");
		}
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(locFile));
			String line;
			while((line = br.readLine()) != null){
				String[] v = line.split("=", 2);
				texts.add(new TextDef(v[0], v[1]));
			}
			br.close();
		}
		catch (FileNotFoundException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
	}
	
	public static String getText(String value){
		for(TextDef t:texts) if(t.getValue().equals(value)) return t.getText();
		return "*"+value+"*";
	}
	
	public static void setLocalizationPath(String path){
		localizationPath = path;
	}
}
