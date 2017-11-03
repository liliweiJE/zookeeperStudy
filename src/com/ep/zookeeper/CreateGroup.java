package com.ep.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;

public class CreateGroup extends ConnectionWatcher {
	
	public void createGroup(String groupName) throws KeeperException, InterruptedException{
		 String path="/"+groupName;
	        if(zooKeeper.exists(path, false)== null){
	        	zooKeeper.create(path, null/*data*/, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
	        }
	        System.out.println("Created:"+path);
	}
	
	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
		CreateGroup cg=new CreateGroup();
		cg.connect("172.16.2.199:2181");
		cg.createGroup("liliwei");
		cg.close();
	}

}
