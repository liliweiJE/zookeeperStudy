package com.ep.zookeeper;

import java.io.IOException;
import java.util.List;

public class ListGroup extends ConnectionWatcher{

	public void list(String groupName){
		String path="/"+groupName;
		
		try{
			List<String> list = zooKeeper.getChildren(path, false);
			
			if(null==list&&list.size()<=0){
				System.out.printf("No memebers in group %s\n",groupName);
                System.exit(1);
			}
			
			for (String string : list) {
				System.out.println(string);
			}
		}catch(Exception e){
			System.out.printf("No memebers in group %s\n",groupName);
            System.exit(1);
		}
	} 
	
	public static void main(String[] args) throws IOException, InterruptedException {
		 	ListGroup listGroup = new ListGroup();
		 	/*System.out.println(args[0]);
		 	System.out.println(args[1]);*/
	        listGroup.connect("172.16.2.199:2181");
	        listGroup.list("zookeeper");
	        listGroup.close();
	}
	
}
