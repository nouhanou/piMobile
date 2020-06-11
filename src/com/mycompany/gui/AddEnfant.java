/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Enfant;
import static com.mycompany.gui.EmailRestPass.emailReset;
import com.mycompany.servicces.ServiceUser;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author HP
 */
public class AddEnfant  {

    Form f;
    public ArrayList<Enfant> eleves = new ArrayList<>();
    

    public AddEnfant(Resources res) {
        f = new Form();
        f.setTitle("Inscription enfant");
        Container x = new Container(BoxLayout.y());

        Label l = new Label("Prenom");
        Label l3 = new Label("Lieux de naissance");
        Label l6 = new Label("information");
        Label l4 = new Label("Emploi");
        Label l7 = new Label("Date de naissance");
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);

        TextField name = new TextField();
        TextField place = new TextField();
        TextField garderie = new TextField();
        TextField info = new TextField();

        Button add = new Button("Ajouter !");
        add.addActionListener((ActionListener) (ActionEvent evt) -> {
            String classe = "";
            Boolean testage = true;
            long diff = new Date().getTime() - datePicker.getDate().getTime();
            int diffDays = (int) (diff / (24 * 60 * 60 * 1000) / 360);
            
            

            if (diffDays >= 1 && diffDays <= 3) {
                classe = "garderie  ";
            }
            if (diffDays == 4) {
                classe = "classe maternelle  ";
            }
            if (diffDays == 5) {
                classe = "classe préscolaire ";
            }
            if (diffDays > 5 || diffDays <= 0) {
                testage = false;

            }

            if (!name.getText().isEmpty() && !place.getText().isEmpty() && !garderie.getText().isEmpty() && testage) {
                new ServiceUser().addEnfant(name.getText(), place.getText(), garderie.getText(), classe, info.getText(), Login.user.getId());
                Dialog.show("Merci", "L'age de votre enfant est  " + diffDays + " ans . Il sera inscrit dans le classe :" + classe, "ok", null);
                new ListEnfant(res).getF().show();

            } else if (diffDays > 5 || diffDays <= 0 && !name.getText().isEmpty() && !place.getText().isEmpty() && !garderie.getText().isEmpty()) {
                Dialog.show("age inacceptable", "L'age de votre enfant est  " + diffDays + " ans . Il ne peut pas rejoindre notre jardin d'enfant  :" + classe, "ok", null);

            } else if (name.getText().isEmpty() || place.getText().isEmpty() || garderie.getText().isEmpty() || info.getText().isEmpty()&& !testage) {
                Dialog.show("Error", "Remplir tout les champs", "ok", null);


            }
        });
        //new ServiceUser().sendmail("nouha.noreply@gmail.com", "Votre enfant est inscrit");
        //      Dialog.show("Error","un mail de confirmation a été  envoyé ","ok",null);
        
        Toolbar tb = f.getToolbar();

       
        tb.addMaterialCommandToSideMenu("List Enfant ",FontImage.MATERIAL_ACCOUNT_BOX, (e)->{
                new ListEnfant(res).getF().show();
        });
        tb.addMaterialCommandToSideMenu("Profil ",FontImage.MATERIAL_ACCOUNT_CIRCLE, (e)->{
                new Profil(res).getF().show();
        });
            
            
             tb.addMaterialCommandToSideMenu("Statistiques ",FontImage.MATERIAL_SHOW_CHART, (e)->{
                new stat(res).getF().show();
        });
              tb.addMaterialCommandToSideMenu("Logout ",FontImage.MATERIAL_ARROW_BACK, (e)->{
                new Login(res).getF().show();
        });
        x.add(l);
        x.add(name);
        x.add(l7);
        x.add(datePicker);

        x.add(l3);
        x.add(place);
        x.add(l4);
        x.add(garderie);
        x.add(l6);
        x.add(info);
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
