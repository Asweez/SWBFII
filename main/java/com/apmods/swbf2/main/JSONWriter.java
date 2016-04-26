package com.apmods.swbf2.main;

import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.apmods.swbf2.item.ItemManager;


public class JSONWriter {
	public static void loadJSON() throws JSONException {
	for(int i = 0; i < (ItemManager.allItems.size()); i++){
		System.out.print("yoyo");
   	 JSONObject obj = new JSONObject();
   	 obj.put("parent", "battlefront:item/baseItem");
   	 JSONObject obj1 = new JSONObject();
   	 obj1.put("layer0", "battlefront:items/" + ItemManager.allItems.get(i).getUnlocalizedName().substring(5));
   	 obj.put("textures", obj1);

		try {
	
			FileWriter file = new FileWriter(System.getProperty("user.home") + "/Desktop/MiniMods/SWBFII 1.8/src/main/resources/assets/battlefront/models/item/" + ItemManager.allItems.get(i).getUnlocalizedName().substring(5) + ".json");
			file.write(obj.toString());
			file.flush();
			file.close();
	
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
    }

}
