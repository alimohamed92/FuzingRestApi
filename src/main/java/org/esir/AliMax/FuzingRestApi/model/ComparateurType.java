package org.esir.AliMax.FuzingRestApi.model;

import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;

import io.swagger.models.Model;
import io.swagger.models.Path;
import io.swagger.models.properties.Property;

/**
 * @author AliMohamed
 *
 */
public class ComparateurType {

	public static boolean verifierTypeRef(Model m, JSONObject res){
		for( Entry<String, Property> entr : m.getProperties().entrySet()){
			try {
				if(!res.has(entr.getKey())) return false;
				Object tmp = res.get(entr.getKey());
				if(!tmp.getClass().getSimpleName().equalsIgnoreCase(entr.getValue().getType())){
					if(entr.getValue().getType().equalsIgnoreCase("array") && tmp.getClass().getSimpleName().equalsIgnoreCase("JSONArray")){
						continue;
					}
					if(!entr.getValue().getType().equalsIgnoreCase("number")){
						return false;
					}
					else{
						if(! (tmp instanceof Number)) return false;
					}
				}
			} catch (org.json.JSONException e) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean verifierTypePrim(String typeSpec, String reponse){
		if(typeSpec.equalsIgnoreCase("String"))return true;
		if(typeSpec.equalsIgnoreCase("number")){
			try {
				double v = Double.valueOf(reponse);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}
		if(typeSpec.equalsIgnoreCase("int")||typeSpec.equalsIgnoreCase("Integer") ||typeSpec.equalsIgnoreCase("long")
				|| typeSpec.equalsIgnoreCase("double")||typeSpec.equalsIgnoreCase("float")){
			try {
				int v = Integer.valueOf(reponse);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}
		if(typeSpec.equalsIgnoreCase("boolean")){
			if(reponse.equals("true")|| reponse.equals("false")) return true;
			else return false;
		}
		if(typeSpec.equalsIgnoreCase("array")){
			try {
				JSONObject json = new JSONObject(reponse);
				return true;
			} catch (Exception e) {
				return false;
			} 
		}
		return false;
	}
}
