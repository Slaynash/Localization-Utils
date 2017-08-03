package slaynash.text.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Localization {
	private static Map<String, ArrayList<TranslationDef>> translations = new HashMap<String, ArrayList<TranslationDef>>();
	private static String currentLang = "en";
	
	public static void setLang(String lang){
		currentLang = lang.toLowerCase();
	}

	public static void loadLangFile(String lang, String path){
		File locFile = new File(path);
		if(!locFile.exists()) return;
		
		ArrayList<TranslationDef> langtranslations;
		
		if((langtranslations = translations.get(lang)) == null) {
			langtranslations = new ArrayList<TranslationDef>();
			translations.put(lang, langtranslations);
		}
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(locFile));
			String line;
			while((line = br.readLine()) != null){
				String[] v = line.split("=", 2);
				langtranslations.add(new TranslationDef(v[0], v[1]));
			}
			br.close();
		}
		catch (FileNotFoundException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
	}
	
	public static String getTranslation(String value){
		if(translations.get(currentLang) != null) for(TranslationDef t:translations.get(currentLang)) if(t.getValue().equals(value)) return t.getText();
		if(translations.get("en") != null) for(TranslationDef t:translations.get(currentLang)) if(t.getValue().equals(value)) return t.getText();
		return "*"+value+"*";
	}
	
	public static String getTranslation(String lang, String value){
		if(translations.get(lang) != null) for(TranslationDef t:translations.get(currentLang)) if(t.getValue().equals(value)) return t.getText();
		if(translations.get("en") != null) for(TranslationDef t:translations.get(currentLang)) if(t.getValue().equals(value)) return t.getText();
		return "*"+value+"*";
	}
	
	public static void registerTranslation(String lang, String key, String translation) {
		ArrayList<TranslationDef> langtranslations;
		if((langtranslations = translations.get(lang)) == null) {
			langtranslations = new ArrayList<TranslationDef>();
			translations.put(lang, langtranslations);
		}
		langtranslations.add(new TranslationDef(key, translation));
	}
}
