package com.temp.code.sequence.util;

public class ServerNameContext {
    private  static ServerNameContext instance = new ServerNameContext();

    public String getServerId() {
        return serverId;
    }

    private  String serverId ;



    public static ServerNameContext getInstancce() {
        return instance;
    }
}
