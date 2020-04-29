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
 * Created on 04/01/2004
 *
 */

/**
 * @author Jorge Gutierrez Ramirez
 *
 */
 
import java.util.Vector;
import java.io.*;

public class Lector {
	Vector lineas;
	String nomarch;
	int lactual=0,cactual=0;
	public int lineact(){
		return  lactual;
	}
   public Lector(String nombre){
   	lineas =new Vector();
   	nomarch=nombre;
   	cargaArch();
   }
  public void cargaArch(){
	try {
		BufferedReader f=new BufferedReader(new FileReader(nomarch));
		while (true){
			String s=f.readLine();
			if (s==null)return;
			lineas.add(s.toLowerCase());
		}
	} catch (FileNotFoundException e) {
		ManejaErrores.avientaError("Error abriendo "+nomarch);
	} catch (IOException e) {
		ManejaErrores.avientaError("Error I/O");
		//e.printStackTrace();
	}
  }
	
	public char leec() {
		if(lactual>=lineas.size()){
			return 65535; //EOF 
		}
		String cadena=(String)lineas.elementAt(lactual);
		if(cactual>=cadena.length()){
			cactual=0;
			lactual++;
			return '\n';
		}
		return cadena.charAt(cactual++);
	}


	public String leelinea() {
		if(lactual>=lineas.size())
			return null; 
		cactual=0;
		return (String)lineas.elementAt(lactual++);
	}
		
	public String muestralinea() {
			if(lactual>=lineas.size())
				return null; 
			return (String)lineas.elementAt(lactual-1);
	}


	public char siguiente() {
		if(lactual>=lineas.size()){
		return 65535; //EOF 
		}
		String cadena=(String)lineas.elementAt(lactual);
		if(cactual>=cadena.length())return '\n';
		return cadena.charAt(cactual);
	}
	public void imprime(){
		int t=lineas.size();
		for(int i=0;i<t;i++){
			System.out.println((String)lineas.elementAt(i));
			}
	}
	public static void main(String[] args) {
		Lector a=new Lector(args[0]);
		Lexico grullo = new Lexico(a);
		VectorDeTokens v=grullo.anaLex();
		 for(int i=0;i<v.losTokens.size();i++) System.out.println("\n"+v.sacaToken());
		
	}

}
