package com.ep.zookeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

public class ConnectionWatcher implements Watcher {
	
	private static final int SESSION_TIME_OUT=5000;
	
	protected ZooKeeper zooKeeper;
	
	CountDownLatch coun=new CountDownLatch(1);
	
	public void connect(String host) throws IOException, InterruptedException{
		zooKeeper=new ZooKeeper(host,SESSION_TIME_OUT,this);
		coun.await();
	}

	@Override
	public void process(WatchedEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getState()==KeeperState.SyncConnected){
			coun.countDown();
		}
	}
	
	public void close() throws InterruptedException{
		zooKeeper.close();
	}

}
