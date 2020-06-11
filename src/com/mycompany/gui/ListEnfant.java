/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
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
public class ListEnfant {
    Form f ; 
    public ListEnfant(Resources res){
          f=new Form("Liste Enfant :  "+Login.user.getUsername()); 
         f.getStyle().setBgColor(0xFFFFFF);
      Container table  = new Container(BoxLayout.y());
        ArrayList<Enfant> seances = new ArrayList<>(); 
                    seances = new ServiceUser().listEnfant(Login.user.getId());

      for (Enfant s : seances){
      Container y1 = new Container(BoxLayout.y() );
      Container x = new Container(BoxLayout.x() );
      Container y2 = new Container(BoxLayout.y() );

      
          Label nom = new Label("Nom :"+s.getName()); 
          Label classe = new Label("Classe :"+s.getClasse()); 
          Label garderie = new Label("Temps :"+s.getGarderie()); 
          Label place = new Label("Lieux de naissance :"+s.getPlace()); 
y1.add(nom);
y1.add(garderie);
y2.add(classe);
y2.add(place);
 x.add(y1);
 x.add(y2);
 
          
table.add(x);
table.add(new Label("_______________________________________________________________________"));

      }
        Toolbar tb=f.getToolbar();
       
            tb.addMaterialCommandToSideMenu("Ajouter Enfant ",FontImage.MATERIAL_ADD, (e)->{
                new AddEnfant(res).getF().show();
        //   tb.openSideMenu();
        });
            tb.addMaterialCommandToSideMenu("Profil ",FontImage.MATERIAL_ACCOUNT_CIRCLE, (e)->{
                new Profil(res).getF().show();
        });
            tb.addMaterialCommandToSideMenu("Statistiques ",FontImage.MATERIAL_SHOW_CHART, (e)->{
                new stat(res).getF().show();
        });
           
f.add(new Label(""));
              f.add(new Label("_______________________________________________________________________"));
 tb.addMaterialCommandToSideMenu("Logout ",FontImage.MATERIAL_ARROW_BACK, (e)->{
                new Login(res).getF().show();
        });
     f.add(table);
    f.show();
}

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
