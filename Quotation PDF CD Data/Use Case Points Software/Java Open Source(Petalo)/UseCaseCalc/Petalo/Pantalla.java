import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
class Imagen extends Panel{
ImageIcon Img;
final int  HOR_TAMANO,VER_TAMANO;
public Imagen(String img){
		Img=new ImageIcon(img);
	    HOR_TAMANO=400;
	    VER_TAMANO=250;
		setSize(new Dimension(HOR_TAMANO,VER_TAMANO));
		repaint();
					
	}
	public void paint(Graphics g){
		     g.drawImage(Img.getImage(),(HOR_TAMANO/2)-100,0,Color.black,this);		 	 
		 	 }

    
}
public class Pantalla extends Frame{ 
		boolean cerrar=false;
		public Pantalla(){
		super("Pantalla");
		Imagen t=new Imagen("img/petalo.jpg");
	    Panel p=new Panel();
	    p.setLayout(new BorderLayout());
	    p.add("Center",t);
	    Panel abajo=new Panel();
	    TextArea lic=new TextArea("Universidad de Guadalajara \n Jorge Fernando Gutiérrez Ramírez \n Copyright (C) \n This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 2 of the License, or (at your option) any later version.\nThis program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.\nYou should have received a copy of the GNU General Public License along with this program; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA",3,30,1);
	    abajo.add(lic);
	    Button b=new Button("Aceptar");
	    b.addActionListener(new ActionListener(){
	    public void actionPerformed(ActionEvent e){
		String comando=e.getActionCommand();
		if(comando.equals("Aceptar")){ 
			cerrar=true;
			hide();
			}		
		}
	   });
	    abajo.add(b);
	    p.add("South",abajo);
	 	add(p);
	 	setSize(new Dimension(500,400));
	 	show();
	 }
	 public static void main(String args[]){
	 	Pantalla p=new Pantalla();
	 	}
}