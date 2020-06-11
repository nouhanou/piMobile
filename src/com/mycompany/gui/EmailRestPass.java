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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.servicces.ServiceUser;
import java.util.Random;

/**
 *
 * @author HP
 */
public class EmailRestPass {
   
    Form f ; 
    static String code ;
    static String emailReset ;
    
    public EmailRestPass(Resources res){
         f = new Form();
        f.setTitle("Mot de passe oublié ");
        Container x = new Container(BoxLayout.y());
        
        Label l = new Label("Email de récuperation");
        TextField email = new TextField("", "Email de récepuration");
         Button add = new Button("Ajouter !");
        add.addActionListener((ActionEvent evt) -> {
            
                          Random rand = new Random(); 

        int rand_int1 = rand.nextInt(1000)+1000; 
                  System.out.println(rand_int1);
                  code = Integer.toString(rand_int1);
                  emailReset = email.getText();
                  new ServiceUser().sendmail(emailReset, code);
              Dialog.show("Error","un mail avec code de Reset a été envoyer vers votre boite mail","ok",null);
              new ValidationReset(res).getF().show();
               
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
