package com.ep.zookeeper;

import java.nio.charset.Charset;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;

public class ActivityKeyValue extends ConnectionWatcher{
	
	private static final Charset CHARSET=Charset.forName("UTF-8");
	
	public void writer(String path,String value) throws KeeperException, InterruptedException{
		Stat stat = zooKeeper.exists(path, false);
			 if(stat==null){
				 zooKeeper.create(path, value.getBytes(CHARSET),Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		        }else{
		        	zooKeeper.setData(path, value.getBytes(CHARSET),-1);
		        }
	}
	
	public String read(String path,Watcher watch) throws KeeperException, InterruptedException {
		 byte[] data = zooKeeper.getData(path, watch, null);
	        return new String(data,CHARSET);
	}

}
