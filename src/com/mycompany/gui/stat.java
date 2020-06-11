/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Enfant;
import com.mycompany.servicces.ServiceUser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author HP
 */
public class stat extends Form {
    Form f ;  
    
     public Enfant e;
/**
 * Creates a renderer for the specified colors.
 */
private DefaultRenderer buildCategoryRenderer(int[] colors) {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(30);
    renderer.setLabelsColor(ColorUtil.GRAY);
    renderer.setLegendTextSize(50);
    renderer.setMargins(new int[]{20, 30, 15, 0});
    for (int color : colors) {
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(color);
        renderer.addSeriesRenderer(r);
    }
    return renderer;
}

/**
 * Builds a category series using the provided values.
 *
 * @param titles the series titles
 * @param values the values
 * @return the category series
 */
protected CategorySeries buildCategoryDataset(String title, double[] values) {
    CategorySeries series = new CategorySeries(title);
    String k = "préscolaire";
    int i=0;
    for (double value : values) {
        series.add("Enfants inscris en  " + k, value);
        k="maternelle";
        i++;
        if(i==2)
            k="garderie";
            
            
    }

    return series;
}

     



    




public  stat(Resources res)
 {   //Form f;
      f = new Form(new BorderLayout());

        f.setTitle("Statistiques des enfants");
     
 
 Container cnt = new Container(BoxLayout.y());
 ArrayList<Enfant> data = new ArrayList<Enfant>();
 ServiceUser v = new ServiceUser();

 data = v.listAllEnfant(); 
Label xl = new Label( "Les inscris préscolaire :");   
cnt.add(xl); 
Label yl = new Label( "Les inscris garderie :");   
cnt.add(yl); 
Label zl = new Label( "Les inscris maternelle :");   
cnt.add(zl); 
Iterator<Enfant> it = data.iterator();
int x = 0;
            int y = 0;
            int z=0;
            
            int arrayLength = data.size();

            for (int i = 0; i < arrayLength; i++) {
                if (data.get(i).getClasse().equals("classe préscolaire")) {
                    x = x + 1;
                }else if(data.get(i).getClasse().equals("garderie"))
                        {y =y +1;}
                else if(data.get(i).getClasse().equals("classe maternelle"))
                        {z = z+1;}
            }

 
//*****************************************
System.out.println("nbr inscris en classe préscolaie :"+x);
System.out.println("nbr inscris en garderie :"+y);
System.out.println("nbr inscris en classe maternelle :"+z);

 double[] values = new double[]{x,y,z};
 // Set up the renderer
    // Set up the renderer
    int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.MAGENTA, ColorUtil.GREEN};
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextSize(200);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
    r.setGradientEnabled(true);
    r.setGradientStart(0, ColorUtil.BLUE);
    r.setGradientStop(0, ColorUtil.GREEN);
    r.setHighlighted(true);

    // Create the chart ... pass the values and renderer to the chart object.
    PieChart chart = new PieChart(buildCategoryDataset("Statistiques des enfants", values), renderer);

    // Wrap the chart in a Component so we can add it to a form
    ChartComponent c = new ChartComponent(chart);

     //Create a form and show it.
   //Form f = new Form("Statistiques ", new BorderLayout());
   f.add(BorderLayout.CENTER, c);
   //return f;
//add(c);
add(cnt);
    
 /*
 Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
 FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_WARNING, s);
//getToolbar().addCommandToLeftBar("Left", icon, (e) -> Log.p("Clicked"));
//getToolbar().addCommandToRightBar("Right", icon, (e) -> Log.p("Clicked"));
getToolbar().addCommandToOverflowMenu("Overflow", icon, (e) -> Log.p("Clicked"));
getToolbar().addCommandToSideMenu("Sidemenu", icon, (e) -> Log.p("Clicked"));
      
   */
 Toolbar tb=f.getToolbar();
       
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
 


 }
public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }



}

