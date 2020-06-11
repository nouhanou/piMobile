/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.servicces.ServiceUser;

/**
 *
 * @author HP
 */
public class inscription {
    Form f  ; 
    
    
    public inscription(Resources res){
         f=new Form("Inscription :"); 
         f.getStyle().setBgColor(0xFFFFFF);
               Container x  = new Container(BoxLayout.y());

         TextField nom = new TextField("", "Nom ");
                  TextField prenom = new TextField("", "Prenom ");

         TextField email = new TextField("", "Email");
         TextField password = new TextField("", "Mot de passe",20,TextField.PASSWORD);
         Button add = new Button("Inscription ");
        add.addActionListener((evt) -> {
            Boolean mailtest = new ServiceUser().testmail(email.getText());
                   if(!nom.getText().isEmpty() && !email.getText().isEmpty()&& !prenom.getText().isEmpty()&& !password.getText().isEmpty()&& mailtest){
                  Login.user=  new ServiceUser().inscription(nom.getText()+" "+prenom.getText(),email.getText(),password.getText());
                    Dialog.show("Merci","Votre inscription est validée","ok",null);
                    new AddEnfant(res).getF().show();
                   }
                   else if (!mailtest && !email.getText().isEmpty()){
                    Dialog.show("Error","Email Existant ","ok",null);
                    
               } else if(nom.getText().isEmpty() || email.getText().isEmpty()|| prenom.getText().isEmpty()|| password.getText().isEmpty()) {
                                           Dialog.show("Error","Veuillez remplir tous les champs  ","ok",null);

                   }
                   
          });
        x.add(nom);
        x.add(prenom);
        x.add(email);
        x.add(password);
        x.add(add);
        f.add(x);
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
