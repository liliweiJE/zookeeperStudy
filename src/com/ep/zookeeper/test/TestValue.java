package com.ep.zookeeper.test;


import net.sf.json.JSONObject;



public class TestValue {/*
	 public static void main(String[] args) {
//			String key = "SRV_BODY.FDC0011.DIS_LN_COD";
			String key = "SYS_HEAD.PINBLOCK";
			String value = "11";
//			String string = key.substring(0, key.indexOf("."));
//			String string2 = key.substring(key.indexOf(".")+1);
//			System.out.println(string2);
			try {
				//System.out.println(Test.pinMessage(key, value).toString());
				Class<Object> beanType = null;
				Object jObject = JsonUtils.jsonToPojo(Test.pinMessage(key, value).toString(), beanType);
				System.out.println(jObject);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			System.out.println(JsonUtils.objectToJson(lists));
		}
	 
	 public static List<Object> listFieldObject = new ArrayList<Object>();
	 public static Map<String, Object> map = new LinkedHashMap<String, Object>();
	 public static Map<String, Object> maps = new LinkedHashMap<String, Object>();
	 public static JSONObject jsonArr = new JSONObject();//json格式的数组
	   
	   public static JSONObject pinMessage(String key, Object value) throws JSONException{
		   JSONObject jsonObj = new JSONObject();//创建json格式的数据  
		   
		  
//		   JSONObject jsonObjArr = new JSONObject(); 
		   
		   if (key.contains(".")) {
			   	JSONObject jsonObjArr = new JSONObject(); 
				String parent = key.substring(key.lastIndexOf(".")+1);
				System.out.println(parent);
				String son = key.substring(0,key.lastIndexOf("."));
				jsonObjArr.put(parent, value);
				if (son.contains(".")) {
					pinMessage(son, jsonObjArr);
				} else {
					jsonArr.put(son, jsonObjArr);
				}
				
			} else {
				
			}
			return jsonArr;
	   }
*/
	
	
	//public static List<Object> listFieldObject = new ArrayList<Object>();
	// public static Map<String, Object> map = new LinkedHashMap<String, Object>();
	 //public static Map<String, Object> maps = new LinkedHashMap<String, Object>();
	public static JSONObject jsonArr = new JSONObject();//json格式的数组  
	 public static JSONObject jsonObj = new JSONObject();//创建json格式的数据
	 public static JSONObject jsonObjArr = new JSONObject();
	 public static void main(String[] args) throws Exception {
		 String key1 = "SRV_BODY.ORPRA_BFLG";
		 String key2 = "SRV_BODY.FDC0011.DIS_LN_COD";
		String key3 = "SRV_BODY.BANK_KIND";
//		String key = "SYS_HEAD.PINBLOCK";
		String value1 = "11";
		String value2 = "12";
		String value3 = "13";
		TestValue test = new TestValue();
		test.pinMessage(key1, value1,jsonObjArr);
		test.pinMessage(key2, value2,jsonObjArr);
		test.pinMessage(key3, value3,jsonObjArr);
		System.out.println(jsonArr.toString());
	}
  
  
	 public void pinMessage(String key, Object value , JSONObject a) throws Exception{
	   if (key.contains(".")) {
		   	
			String parent = key.substring(key.lastIndexOf(".")+1);
			System.out.println(parent);
			String son = key.substring(0,key.lastIndexOf("."));
			a.put(parent, value);
			if (son.contains(".")) {
				pinMessage(son, a , jsonObj);
			} else {
				jsonArr.put(son, a);
			}
			
		} else {
			
		}
//		return jsonArr;
  }
}
