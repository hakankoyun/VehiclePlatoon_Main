package com.example.demo.Vehicle;

import com.example.demo.Vehicle.Verbindung.Client;
import com.example.demo.Vehicle.Verbindung.Server;
import com.example.demo.Vehicle.VehicleModel.Vehicle;
import java.text.MessageFormat;
import static com.example.demo.Vehicle.Verbindung.Connection.*;

public class Start {


    private static Vehicle vhic;
    private static Server srv;
    private static Server frtCsrv;
    private static Client frt;
    private static Client bck;
    private static boolean frtExist;

    public static void main(String[] args) {


        try {
            vhic = reg(0xa);

            if (0 != vhic.getFrontID())
            {
                frt = new Client(vhic.getFrontID(), 2);
                frt.sendMsg("HINTER DIR!" + vhic.getId());
                frt.closeConnection();
                frtExist = true;
            }
            if (0 != vhic.getBackID())
            {
                bck = new Client(vhic.getBackID(), 1);
                bck.listen();
                bck.sendMsg("VOR DIR!" + vhic.getId());
            }
            srv = new Server(vhic.getId(), 1);
            frtCsrv = new Server(vhic.getId(), 2);
            con(vhic.getId());
            System.out.println(vhic);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void newSpd(int spd){
        vhic.setVehicleSpeed(spd);
        if (bck != null){
            bck.sendMsg(MessageFormat.format("Jetzige Geschwindigkeit: {0}", vhic.getVehicleSpeed()));
        }
    }

    public static void frontConnection(int id){
        frtExist = true;
        srv.sendMsg(MessageFormat.format("Jetzige Geschwindigkeit: {0}", vhic.getVehicleSpeed()));
    }

    public static void backConnection(int id){
        vhic.setBackID(id);
        bck = new Client(vhic.getBackID(), 1);
        bck.listen();
    }
}