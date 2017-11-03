package com.ep.zookeeper.test;

public class TestMain
{
    private String baseName = "base";
    public TestMain()
    {
        callName();
    }

    public void callName()
    {
        System. out. println(baseName);
    }

    static class Sub extends TestMain
    {
        private String baseName = "sub";
        public void callName()
        {
            System. out. println (baseName) ;
        }
    }
    public static void main(String[] args)
    {
    	TestMain b = new Sub();
    }
}