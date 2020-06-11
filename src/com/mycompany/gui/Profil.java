/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.push.Push;
import com.codename1.push.PushCallback;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Enfant;
import com.mycompany.servicces.ServiceUser;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class Profil implements PushCallback {
     Form f  ; 
     public ArrayList<Enfant> eleves = new ArrayList<>();
       /**
         * Invoked when the push notification occurs
         *
         * @param value the value of the push notification
         */
    public void push(String value) {
        System.out.println("Received push message: " + value);
    }
    /**
     * Invoked when push registration is complete to pass the device ID to the
     * application.
     *
     * @param deviceId OS native push id you should not use this value and
     * instead use <code>Push.getPushKey()</code>
     * @see Push#getPushKey()
     */
    public void registeredForPush(String deviceId) {
        System.out.println("The Push ID for this device is " + Push.getPushKey());
    }
    /**
     * Invoked to indicate an error occurred during registration for push
     * notification
     *
     * @param error descriptive error string
     * @param errorCode an error code
     */
    public void pushRegistrationError(String error, int errorCode) {
        System.out.println("An error occurred during push registration.");
    }
    
    
    public Profil(Resources res){
         f=new Form("Modfifer Profil  :"+Login.user.getUsername()); 
         f.getStyle().setBgColor(0xFFFFFF);
               Container x  = new Container(BoxLayout.y());

         TextField username = new TextField(Login.user.getUsername(), "Username ");

         TextField email = new TextField(Login.user.getEmail(), "Email");
         TextField password = new TextField(Login.user.getPassword(), "Mot de passe",20,TextField.PASSWORD);
         Button add = new Button("Modifier ");
        add.addActionListener((evt) -> {
                  if(!username.getText().isEmpty() && !email.getText().isEmpty()&&  !password.getText().isEmpty()){
                  Login.user=  new ServiceUser().updateProfil(Login.user.getId(),username.getText(),email.getText(),password.getText());
                    Dialog.show("Success","Votre profil est modifié","ok",null);
                                            push("Votre enfant est inscrit");

                    new Profil(res).getF().show();
                   }
                else{
                    Dialog.show("Error","Remplir tout les champs","ok",null);
                                            pushRegistrationError("error", REGISTRATION_INVALID_SENDER);

                    
               }
          });
          Toolbar tb=f.getToolbar();
       
          
            tb.addMaterialCommandToSideMenu("List Enfant ",FontImage.MATERIAL_ACCOUNT_BOX, (e)->{
                new ListEnfant(res).getF().show();
        });
            tb.addMaterialCommandToSideMenu("Ajouter Enfant ",FontImage.MATERIAL_ADD, (e)->{
                new AddEnfant(res).getF().show();
        });
            
             tb.addMaterialCommandToSideMenu("Statistiques ",FontImage.MATERIAL_SHOW_CHART, (e)->{
                new stat(res).getF().show();
        });
              tb.addMaterialCommandToSideMenu("Logout ",FontImage.MATERIAL_ARROW_BACK, (e)->{
                new Login(res).getF().show();
        });
        x.add(username);
        x.add(email);
        x.add(password);
        x.add(add);
        f.add(x);
        registeredForPush(Push.getPushKey());

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
