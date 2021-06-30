package com.example.demo.Vehicle.Verbindung;

import com.example.demo.Vehicle.Start;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class Client {

    int tmp;
    int id;
    Socket C;
    DataOutputStream dataout;

    public Client(int id, int tmp){
        this.tmp = tmp;
        this.id = id;
        int port = 10000 + id*10 + tmp;

        try{
            C=new Socket("localhost",port);

        }catch(Exception e){System.out.println(C);}
    }


    private void messageHandler(String message)
    {
        if (message.contains("Ich bin jetzt hinter dir"))
        {
            String id = message.substring(16);
            Start.backConnection(Integer.parseInt(id));
        }

        else if (message.contains("Ich bin jetzt vor dir"))
        {
            String id = message.substring(13);
            Start.frontConnection(Integer.parseInt(id));
        }

        else if (message.contains("Geschwindigkeit: "))
        {
            int spd = Integer.parseInt(message.substring(26));
            Start.newSpd(Integer.parseInt(String.valueOf(spd)));
        }
    }
    public void listen()
    {
        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        try {
                            DataInputStream dis = new DataInputStream(C.getInputStream());
                            String msg = dis.readUTF();
                            messageHandler(msg);
                            System.out.println("Verbindung :" + msg);


                        } catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                    }
                }, 5, 500
        );
    }

    public void sendMsg(String msg)
    {
        try {
            dataout = new DataOutputStream(C.getOutputStream());
            dataout.writeUTF(msg);
            dataout.flush();
            System.out.println("tmp: " + tmp + " Übermittel an Client  " + id + ": " + msg);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public void closeConnection()
    {
        try{
            dataout.close();
            C.close();
        }catch (Exception C)
        {
            C.printStackTrace();
        }

    }
}

