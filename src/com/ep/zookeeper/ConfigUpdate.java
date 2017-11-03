package com.ep.zookeeper;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.KeeperException;

public class ConfigUpdate {

	public static final String PATH="/config";
	
	private ActivityKeyValue store;
	private Random random=new Random();
	
	 public ConfigUpdate(String hosts) throws IOException, InterruptedException {
	        store = new ActivityKeyValue();
	        store.connect(hosts);
	    }
	 
	 public void run() throws InterruptedException, KeeperException{
	        while(true){
	            String value=random.nextInt(100)+"";
	            store.writer(PATH, value);
	            System.out.printf("Set %s to %s\n",PATH,value);
	            TimeUnit.SECONDS.sleep(random.nextInt(100));
	        }
	    }
	 
	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
		ConfigUpdate configUpdate=new ConfigUpdate("172.16.2.199:2181");
		configUpdate.run();
	}
}
