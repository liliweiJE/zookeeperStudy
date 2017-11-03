package com.ep.zookeeper.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ttt {
	public static void main(String[] args) {
		List<String> strings=new ArrayList<String>();
		/*strings.add("inputBody==SRV_BODY.FDC0011.DIS_LN_COD.C.B.A==11==");*/
		strings.add("inputHead==SRV_HEAD.TXN_COD==01==");
		strings.add("inputBody==SRV_BODY.ORPRA_BFLG==01==");
		//strings.add("inputBody==SRV_BODY.FDC0011.DIS_LN_COD==11==");
		strings.add("inputBody==SRV_BODY.FDC0011.DIS_LN_COD.C.B.A==11==");
		strings.add("inputBody==SRV_BODY.BANK_KIND==12==");
		//比较
	//	List<String> ls=new ArrayList<String>();
		
		String json = getJson(strings);
		
		System.out.println(json);
		/*String[] sp= strings.get(0).split("==");
		strings.remove(0);
		String count=sp[2]+"=="+sp[3]+"=="+sp[4]+"==";
		for (String string : strings) {
			String[] sl = string.split("==");
			String[] sc = count.split("==");
			if(sc[0].contains(sl[2])){
				count=count+sl[3]+"=="+sl[4]+"==";
			}else{
				String[] tl = sl[2].split(sc[0]);
				String sk="";
				for (String string2 : tl) {
					if(null!=string2&&""!=string2.trim()){
						sk=sc[0]+string2;
					}
				}
				sc[0]=sk;
				String retu = retu(sc);
				count=retu+sl[3]+"=="+sl[4]+"==";
			}
		}*/
	}
	
	public static String retu(String[] sttr){
		String newCount="";
		for (String string : sttr) {
			newCount+=string+"==";
		}
		return newCount;
	}
	
	public static String getJson(List<String> strings){
				//是否有
				String[] toBeStored = new String[strings.size()];    
				strings.toArray(toBeStored); 
				
				String count="";
				
				String hd="";
				
				int s = Arrays.toString(toBeStored).split("SYS_HEAD").length;
				//开始拼接
				for (int j = 0; j < strings.size(); j++) {
					String[] ss = strings.get(j).split("\\.");
					for (int i = 0; i < ss.length; i++) {
						if(ss[i].contains("input")){
							String[] split = ss[i].split("==");
							if(!count.contains(split[1])){
								count+="\\\""+split[1]+"\\\""+":{";
								hd=split[1];
							}
						}else if(ss[i].contains("==")){
							String[] split = ss[i].split("==");
							count+="\\\""+split[0]+"\\\""+":"+"\\\""+split[1]+"\\\""+",";
							if(ss.length-1==i&&ss.length==2&&(Arrays.toString(toBeStored).split(hd).length<=2)){
								count= count.substring(0, count.length()-1);
								count+="},";
							}
						}else{
							
							if(ss[i+1].contains("==")){
								String[] split = ss[i+1].split("==");
								count+="\\\""+ss[i]+"\\\""+":{";
								count+="\\\""+split[0]+"\\\""+":"+"\\\""+split[1]+"\\\""+"},";
								break;
							}else{
								count+="\\\""+ss[i]+"\\\""+":{";
							}
							
						}
					}
					if(ss.length>3){
						count=ckCount(count.substring(0, count.length()-1),ss[1],toBeStored);
					}
					/*if(){
						
					}*/
					toBeStored[j]=" ";
				}
				count= count.substring(0, count.length()-1);
				count="{"+count+"}";
				
				return count;
	}
	
	
	public static String ckCount(String count,String s,String[] toBeStored){
		byte[] bytes = count.getBytes();
		int z=0;
		int y=0;
		for (byte b : bytes) {
			if(b=='{'){
				z++;
			}
			if(b=='}'){
				y++;
			}
		}
		
		int t=z-y;
		String[] split = s.split("==");
		
		if(Arrays.toString(toBeStored).contains(split[0])&&toBeStored.length>1){
			t=t-1;
		}
		
		for(int i=1;i<=t;i++){
			if(i==t){
				count+="},";
			}else{
				count+="}";	
			}
		}
		return count;
	}

}
