package com.ep.zookeeper;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.KeeperException;

public class DeleteGroup extends ConnectionWatcher {
	public void delete(String groupName) throws InterruptedException, KeeperException{
        String path="/"+groupName;
        List<String> children;
        try {
            children = zooKeeper.getChildren(path, false);
            for(String child:children){
            	zooKeeper.delete(path+"/"+child, -1);            
            }
            zooKeeper.delete(path, -1);
        } catch (KeeperException.NoNodeException e) {
            System.out.printf("Group %s does not exist\n", groupName);
            System.exit(1);
        }    
    }
    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        DeleteGroup deleteGroup = new DeleteGroup();
        deleteGroup.connect("172.16.2.199:2181");
        deleteGroup.delete("liliwei");
        deleteGroup.close();
    }
}
