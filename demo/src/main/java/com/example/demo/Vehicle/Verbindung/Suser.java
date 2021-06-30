package com.example.demo.Vehicle.Verbindung;

import java.io.DataOutputStream;
import java.net.Socket;

public class Suser {

    Socket C;
    public Suser(int id, int lim){
        int port = 500 + id*5+ lim;

        try{
            C=new Socket("localhost",port);
        }catch(Exception e){System.out.println(C);}
    }

    public void sendMsg(String msg)
    {
        try {
            var dout = new DataOutputStream(C.getOutputStream());
            dout.writeUTF(msg);
            dout.flush();
        } catch (Exception C)
        {
            C.printStackTrace();
        }
    }
}

