/********************************************************
Copyright (C) 2004 Jorge F. Gutierrez Ramirez

    This program is free software; you can redistribute it
    and/or modify it under the terms of the GNU 
    General Public License as published by the 
    Free Software Foundation; either version 2 of the 
    License, or (at your option) any later version.

    This program is distributed in the hope that it will
    be useful, but WITHOUT ANY WARRANTY; without even the
    implied warranty of 
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
    See the GNU General Public License for more details.

    You should have received a copy of the 
    GNU General Public License along with this program;
    if not, write to the Free Software Foundation, Inc.,
    59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
**************************************************7
/*
 * Created on 11/01/2004
 *
 */

/**
 * @author Jorge Gutierrez Ramirez
 *
 */
 
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

class DatosC{
	String Factor; 
	String Description; 
	float Value; 
	public DatosC(String Factor,String Description,float Value){
		this.Factor=Factor;
		this.Description=Description;  
		this.Value=Value; 
		}
	public DatosC(String Factor,String Description){
		this.Factor=Factor;
		this.Description=Description; 
		}
	public String toString(){
		return (Factor+" "+Description+" "+Value+"\n");
		}
	}
public class Cuestionario{
   private JPanel principal,p1,p2,p3,px,pb;
   private JLabel facts[];
   private JLabel descs[];
   private JTextField vals[];
   private Vector list;
   private JScrollPane cont;
   private static JFrame f;
   boolean cerrar=false;
   public Cuestionario(Vector v){
   list=v;	
   int tam=list.size();
   principal= new JPanel();
   p1= new JPanel();
   p2= new JPanel();
   p3= new JPanel();
   p1.setBackground(Color.white);
   p2.setBackground(Color.white);
   p3.setBackground(Color.white);
   principal.setBackground(Color.white);
   principal.setSize(new Dimension(300,tam*30));
   cont=new JScrollPane(principal);
   cont.setBackground(Color.white);
   cont.setSize(new Dimension(800,300));
   facts=new JLabel[tam];
   descs=new JLabel[tam];
   vals=new JTextField[tam];
   p1.setLayout( new GridLayout(tam+1,1));
   p2.setLayout( new GridLayout(tam+1,1));
   p3.setLayout( new GridLayout(tam+1,1));
   principal.setLayout( new BorderLayout());
   principal.add("West",p1);
   principal.add("Center",p2);
   principal.add("East",p3);
   agregarComponentes();
   pb=new JPanel();
   pb.setBackground(Color.white);
   px=new JPanel();
   px.setBackground(Color.white);
   px.setLayout( new BorderLayout());
	   JButton b=new JButton("Insertar");
	   b.setBackground(Color.white);
	   b.addActionListener(new ActionListener(){
	   public void actionPerformed(ActionEvent e){
		String comando=e.getActionCommand();
		if(comando.equals("Insertar")){ 
			sacar();
			cerrar=true;
			f.hide();
			}
		
		}
	   });
	   pb.add(b);
	   px.add("Center",cont);
	   px.add("South",pb);
	f=new JFrame("Factores de Ajuste");
		f.setSize(new Dimension(500,400));
		f.setBackground(Color.white);
		f.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent we) {
						//cerrar=true;
						//f.hide();
						}
				});
		f.getContentPane().add( ref());
		f.show();
   }
public JPanel ref(){
	return px;
	}   
private void agregarComponentes(){
   Panel x=new Panel();
   p1.add(new JLabel("Factor "));
   p2.add(new JLabel("Descripción "));
   p3.add(new JLabel("Valor"));
   DatosC aux=null;
   for(int i=0;i<list.size();i++){
   aux=(DatosC)list.elementAt(i);
   facts[i]=new JLabel(aux.Factor+" ");
   p1.add(facts[i]);
   descs[i]=new JLabel(aux.Description+" ");
   p2.add(descs[i]);
   vals[i]=new JTextField(""+aux.Value,3);
   p3.add(vals[i]);
   	}
}
public Vector sacar(){
   DatosC aux=null;
   for(int i=0;i<list.size();i++){
   aux=(DatosC)list.elementAt(i);
   aux.Value=Float.parseFloat((String)vals[i].getText());
   //list.insertElementAt(aux,i);
  }
System.out.println(list);
return list;
}
static Cuestionario t;
public static void main(String args[]){
	Vector x=new Vector();
	x.addElement(new DatosC("objetin","mira que bonito es objetin",0.5f));
	x.addElement(new DatosC("objetote","mira que feo es objetote",2.20f));
	x.addElement(new DatosC("objeto","mira que simple es objeto",1.10f));
	x.addElement(new DatosC("objeto-x","mira que enigmatico es objeto-x",2.20f));
	x.addElement(new DatosC("objetote","mira que feo es objetote",2.20f));
	x.addElement(new DatosC("objeto","mira que simple es objeto",1.10f));
	x.addElement(new DatosC("objeto-x","mira que enigmatico es objeto-x",2.20f));
	x.addElement(new DatosC("objetote","mira que feo es objetote",2.20f));
	x.addElement(new DatosC("objeto","mira que simple es objeto",1.10f));
	x.addElement(new DatosC("objeto-x","mira que enigmatico es objeto-x",2.20f));
	x.addElement(new DatosC("objetote","mira que feo es objetote",2.20f));
	x.addElement(new DatosC("objeto","mira que simple es objeto",1.10f));
	x.addElement(new DatosC("objeto-x","mira que enigmatico es objeto-x",2.20f));
	x.addElement(new DatosC("objetote","mira que feo es objetote",2.20f));
	x.addElement(new DatosC("objeto","mira que simple es objeto",1.10f));
	x.addElement(new DatosC("objeto-x","mira que enigmatico es objeto-x",2.20f));
	x.addElement(new DatosC("objetote","mira que feo es objetote",2.20f));
	x.addElement(new DatosC("objeto","mira que simple es objeto",1.10f));
	x.addElement(new DatosC("objeto-xxxxxxxxxx","mira que enigmatico es objeto-xxxxxxxxxxx",2.20f));
	t=new Cuestionario(x);
	}
}