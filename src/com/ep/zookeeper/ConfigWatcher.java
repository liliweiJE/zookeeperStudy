package com.ep.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;

public class ConfigWatcher implements Watcher {

	private ActivityKeyValue store;
	
	 public ConfigWatcher(String hosts) throws IOException, InterruptedException {
	        store=new ActivityKeyValue();
	        store.connect(hosts);
	    }
	
	@Override
	public void process(WatchedEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getState()==KeeperState.SyncConnected){
			 try{
	                dispalyConfig();
	            }catch(InterruptedException e){
	                System.err.println("Interrupted. exiting. ");
	                Thread.currentThread().interrupt();
	            }catch(KeeperException e){
	                System.out.printf("KeeperException锛?s. Exiting.\n", e);
	            }
		}
	}
	
	 public void dispalyConfig() throws KeeperException, InterruptedException{
	        String value=store.read(ConfigUpdate.PATH, this);
	        System.out.printf("Read %s as %s\n",ConfigUpdate.PATH,value);
	    }
	 
	 public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
	        ConfigWatcher configWatcher = new ConfigWatcher("172.16.2.199:2181");
	        configWatcher.dispalyConfig();
	        //stay alive until process is killed or Thread is interrupted
	        Thread.sleep(Long.MAX_VALUE);
	    }

}
