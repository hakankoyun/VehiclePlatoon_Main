package com.example.demo.Vehicle.Verbindung;

import com.example.demo.Vehicle.Start;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class Server {

    ServerSocket sock;
    Socket c;
    public Server(int id, int lim) {
        int port = 500+ id *5 + lim;
        try
        {
            sock = new ServerSocket(port);

            new Timer().schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            try {
                                c = sock.accept();
                                var din=new DataInputStream(c.getInputStream());

                                String msg = din.readUTF();
                                msgHandler(msg);
                                System.out.println("Server " + lim + ", client says: "+msg);


                            } catch (Exception e)
                            {
                                e.printStackTrace();
                            }

                        }
                    }, 5,500
            );

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    public void sendMsg(String msg)
    {
        try {
            DataOutputStream dot = new DataOutputStream(c.getOutputStream());
            dot.writeUTF(msg);
            dot.flush();
        } catch (Exception c)
        {
            c.printStackTrace();
        }
    }

    private void msgHandler(String msg)
    {
        if (msg.contains("HINTER DIR!"))
        {
            String id = msg.substring(16);
            Start.backConnection(Integer.parseInt(id));
        }

        else if (msg.contains("VOR DIR!"))
        {
            String id = msg.substring(13);
            Start.frontConnection(Integer.parseInt(id));
        }

        else if (msg.contains("Jetzige Geschwindigkeit: ")){
            int spd = Integer.parseInt(msg.substring(26));
            Start.newSpd(Integer.parseInt(String.valueOf(spd)));
        }
    }
}

