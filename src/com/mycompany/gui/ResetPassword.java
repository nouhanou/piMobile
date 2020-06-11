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
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.servicces.ServiceUser;

/**
 *
 * @author HP
 */
public class ResetPassword {
    Form f ; 
     public ResetPassword(Resources res){
        f = new Form();
        f.setTitle("Mot de passe oublié ");
        Container x = new Container(BoxLayout.y());
        
        Label l = new Label("Nouveau mot de passe ");
        TextField email = new TextField("", "Code de reset");
         Button add = new Button("Valider !");
        add.addActionListener((evt) -> {
          
                 if(!email.getText().isEmpty() && email.getText().length()>=4){
                     Login.user = new ServiceUser().resetPasword(EmailRestPass.emailReset, email.getText());
                    Dialog.show("Merci","Votre mot de passe est modifié","ok",null);
                                         new Profil(res).getF().show();

                   }
                 else if (email.getText().length()<4){
                    Dialog.show("Error","Mot de passe tres courte ","ok",null);
                   
              }else {
                    Dialog.show("Error","Veuillez entrer votre mot de passe ","ok",null);
                   
              }
          });
         x.add(l);
         x.add(email);
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
