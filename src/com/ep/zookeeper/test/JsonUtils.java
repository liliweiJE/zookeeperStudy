package com.ep.zookeeper.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 */
public class JsonUtils {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转换成json字符串。
     * <p>Title: pojoToJson</p>
     * <p>Description: </p>
     * @param data
     * @return
     */
    public static String objectToJson(Object data) {
    	try {
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    /**
     * 将json结果集转化为对象
     * 
     * @param jsonData json数据
     * @param clazz 对象中的object类型
     * @return
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            T t = MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 将json数据转换成pojo对象list
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T>List<T> jsonToList(String jsonData, Class<T> beanType) {
    	JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
    	try {
    		List<T> list = MAPPER.readValue(jsonData, javaType);
    		return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return null;
    }
    
    /**
     * 
     * #贴现开户
#系统头
inputHead==SYS_HEAD.PINBLOCK==01==
#服务头
inputHead==SRV_HEAD.TXN_COD==01==
#服务体
inputBody==SRV_BODY.ORPRA_BFLG==01==
inputBody==SRV_BODY.FDC0011.DIS_LN_COD==11==
inputBody==SRV_BODY.BANK_KIND==12== 

{
	"SYS_HEAD": {
        "PINBLOCK": "01"
	},
	"SRV_HEAD": {
        "TXN_COD": "01"
	},
	"SRV_BODY": {
		"ORPRA_BFLG": "01",
		"FDC0011": {
			"DIS_LN_COD": "11"
		},
		"BANK_KIND": "12"
	}
}
     * 
     */
    
    public static String txToJson(String s){
    	String[] split = s.split("\\.");
    	List<String> lt=new ArrayList<String>();
    	List<LinkedHashMap<String,Object>> lv=new ArrayList<LinkedHashMap<String,Object>>();
    	for (int i = 1; i < split.length; i++) {
			if(split[i].contains("==")){
				String[] strings = split[i].split("==");
				LinkedHashMap<String, Object> hs=new LinkedHashMap<>();
				hs.put(strings[0], strings[1]);
				lv.add(hs);
			}else{
				lt.add(split[i]);
			}
		}
    	String[] split2 = split[0].split("==");
    	LinkedHashMap<String, Object> pinjie = pinjie(lt,lv,split2[1]);
    	
    	String objectToJson = objectToJson(pinjie);
    	
    	String replace = objectToJson.replace("[", "").replace("]", "");
    	return replace;
    }

	private static LinkedHashMap<String, Object> pinjie(List<String> lt, List<LinkedHashMap<String, Object>> lv,String tt) {
		LinkedHashMap<String, Object> now = null;
		LinkedHashMap<String, Object> lst =new  LinkedHashMap<String, Object>();
		List<LinkedHashMap<String,Object>> ltv=new ArrayList<LinkedHashMap<String,Object>>();
		// TODO Auto-generated method stub
		if(lt.size()<=0){
			lst=lv.get(0);
		}
		for(int i=lt.size()-1;i>=0;i--){
			if(i==lt.size()-1){
				lst.put(lt.get(i), lv);
			}else{
				now= new  LinkedHashMap<String, Object>();
				now.put(lt.get(i), ltv.get(0));
				ltv.remove(0);
				ltv.add(0, now);
			}
		}
		
		if(now==null){
			now= new  LinkedHashMap<String, Object>();
		}
		now.put(tt, lst);
		return now;
	}
 
	
	public static void main(String[] args) {
		String s="inputHead==SYS_HEAD.PINBLOCK==01==";
		System.out.println(txToJson(s));
	}
    
}
