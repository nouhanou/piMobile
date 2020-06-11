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

/**
 *
 * @author HP
 */
public class ValidationReset {
    Form f ; 
    
    public ValidationReset(Resources res){
         f = new Form();
        f.setTitle("Mot de passe oublié ");
        Container x = new Container(BoxLayout.y());
        
        Label l = new Label("Code de Reset");
        TextField email = new TextField("", "Code de reset");
         Button add = new Button("Valider !");
        add.addActionListener((evt) -> {
            if(EmailRestPass.code.equals(email.getText())){
                new ResetPassword(res).getF().show();
            }
            else {
          Dialog.show("Merci","Veuillez verifier votre code de Reset","ok",null);

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
