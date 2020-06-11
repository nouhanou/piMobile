/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.User;
import com.mycompany.servicces.ServiceUser;

/**
 *
 * @author HP
 */
public class Login {
    Form f ; 
    public static User user ;
    
    public  Login(Resources res){
        

        f = new Form("Bienvenu à Kidzy ");

        Container x = new Container(BoxLayout.y());

        Label l = new Label("Email");
        Label l2 = new Label("Password");
        TextField email = new TextField("","",20,TextField.EMAILADDR);
        TextField password = new TextField("", "", 20, TextField.PASSWORD);
        Button add = new Button("Login !");
        add.addActionListener((evt) -> {
                   if(!email.getText().isEmpty() && !password.getText().isEmpty()){
                 User u =    new ServiceUser().login(email.getText(),password.getText());
                 if(u.getId()==0)
                    Dialog.show("Info incorrect","Veuillez verifier votre info ","ok",null);
                   else {
                     user =u;
                     new AddEnfant(res).getF().show();
                   }
                   }
                 
                else{
                    Dialog.show("Error","Remplir tout les champs","ok",null);
                    
                }
          });
        Button inscrit = new Button("Inscription !");
        inscrit.addActionListener((evt) -> {
                   new inscription(res).getF().show();
          });
        
        Button b = new Button("Mot de passe oublié ? ");
b.getAllStyles().setBorder(Border.createEmpty());
b.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
b.getAllStyles().setFgColor(0xff0000);
b.addActionListener((evt) -> {
                   new EmailRestPass(res).getF().show();
          });
        
   
    x.add(l);
      x.add(email);

    x.add(l2);
    x.add(password);
    x.add(add);
    x.add(b);
    x.add(inscrit);
    f.add(x);
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
