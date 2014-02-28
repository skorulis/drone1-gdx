package com.skorulis.drone1.def;

import java.util.ArrayList;
import java.util.Arrays;

public class HullDef extends BaseDef {

	public String modelName;
	
	public ArrayList<String> allModels() {
		return new ArrayList<String>(Arrays.asList(modelName));
	}
	
}
