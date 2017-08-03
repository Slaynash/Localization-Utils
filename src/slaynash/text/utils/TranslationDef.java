package slaynash.text.utils;

public class TranslationDef {
	
	private String key;
	private String translation;
	
	public TranslationDef(String key, String translation){
		this.key = key;
		this.translation = translation;
	}

	public String getValue() {
		return key;
	}

	public String getText() {
		return translation;
	}
	
}
