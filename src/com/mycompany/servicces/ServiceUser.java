/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.servicces;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Enfant;
import com.mycompany.entities.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP
 */
public class ServiceUser {

    User user;
    ArrayList<Enfant> enfants = new ArrayList<>();
    Boolean test;

    public User parseListTaskJson(String json) {
        System.out.println(json);
        try {
            if (json.equals("\"not found\"")) {
                return new User(0);

            } else {
                JSONParser j = new JSONParser();
                Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
                List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                for (Map<String, Object> obj : list) {

                    User e = new User();
                    float id = Float.parseFloat(obj.get("id").toString());
                    e.setId((int) id);

                    e.setUsername(obj.get("username").toString());
                    e.setEmail(obj.get("email").toString());
                    e.setPassword(obj.get("password").toString());

                    System.out.println(e);

                    return e;
                }
            }
        } catch (IOException ex) {

        }
        return null;
    }

    public ArrayList<Enfant> listenfant(String json) {
        try {

            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            for (Map<String, Object> obj : list) {

                Enfant e = new Enfant();
                float id = Float.parseFloat(obj.get("id").toString());

                e.setName(obj.get("name").toString());
                e.setGarderie(obj.get("garderie").toString());
                e.setClasse(obj.get("classe").toString());
                e.setPlace(obj.get("place").toString());

                enfants.add(e);

            }
        } catch (IOException ex) {

        }
        return enfants;
    }

    public ArrayList<Enfant> listEnfant(int iduser) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/kidzyWeb/web/app_dev.php/enfants/" + iduser);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser ser = new ServiceUser();
                try {
                    enfants = ser.listenfant(new String(con.getResponseData()));

                } catch (Exception e) {
                    enfants = new ArrayList<>();
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return enfants;
    }

    public User login(String email, String password) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/kidzyWeb/web/app_dev.php/login/" + email + "/" + password);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser ser = new ServiceUser();
                user = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return user;
    }

    public User inscription(String username, String email, String password) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        con.setUrl("http://localhost/kidzyWeb/web/app_dev.php/inscription/" + username + "/" + email + "/" + password);// création de l'URL
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser ser = new ServiceUser();
                user = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return user;
    }

    public User updateProfil(int iduser, String username, String email, String password) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        con.setUrl("http://localhost/kidzyWeb/web/app_dev.php/update/" + iduser + "/" + username + "/" + email + "/" + password);// création de l'URL
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser ser = new ServiceUser();
                user = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return user;
    }

    public void addEnfant(String name, String place, String garderie, String classe, String info, int iduser) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        con.setUrl("http://localhost/kidzyWeb/web/app_dev.php/add/enfant/" + name + "/" + place + "/" + garderie + "/" + classe + "/" + info + "/" + iduser);// création de l'URL
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser ser = new ServiceUser();
            }
        });
        NetworkManager.getInstance().addToQueue(con);
    }

    public Boolean testmail(String mail) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        con.setUrl("http://localhost/kidzyWeb/web/app_dev.php/emailTest/" + mail);// création de l'URL
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser ser = new ServiceUser();
                test = Boolean.parseBoolean(new String(con.getResponseData()));

                System.out.println(test);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return test;
    }

    public User resetPasword(String email, String password) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        con.setUrl("http://localhost/kidzyWeb/web/app_dev.php/resetPassword/" + email + "/" + password);// création de l'URL
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser ser = new ServiceUser();
                user = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return user;
    }

    public void sendmail(String email, String code) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        con.setUrl("http://localhost/kidzyWeb/web/app_dev.php/sendmail/" + code + "/" + email);// création de l'URL
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser ser = new ServiceUser();
            }
        });
        NetworkManager.getInstance().addToQueue(con);
    }

    public ArrayList<Enfant> listAllEnfant() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/kidzyWeb/web/app_dev.php/enfants");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser ser = new ServiceUser();
                try {
                    enfants = ser.listenfant(new String(con.getResponseData()));

                } catch (Exception e) {
                    enfants = new ArrayList<>();
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return enfants;
    }

}
