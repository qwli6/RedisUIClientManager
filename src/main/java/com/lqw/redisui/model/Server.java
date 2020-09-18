package com.lqw.redisui.model;

public class Server {

    public Server(String addr, int port) {
        this.addr = addr;
        this.port = port;
    }

    public Server(int id, String addr, int port) {
        this(id, "RedisClient["+addr+"]", addr, port, "");
    }


    public Server(int id, String addr, int port, String password) {
        this(id,"RedisClient["+addr+"]", addr, port, password);
    }

    public Server(int id, String name, String addr, int port) {
       this(id, name, addr, port, "");
    }

    public Server(int id, String name, String addr, int port, String password) {
        super();
        this.id = id;
        this.name = name;
        this.addr = addr;
        this.port = port;
        this.password = password;
    }

    private int id;

    private String name;

    private String addr;

    private int port;

    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Server{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addr='" + addr + '\'' +
                ", port=" + port +
                ", password='" + password + '\'' +
                '}';
    }
}
