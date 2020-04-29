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
 
public class CasoDUso {

	/**
	 * @param nombre
	 * @param doc
	 * @param transacciones
	 */
	String nombre;
	String doc;
	int transacciones;
	public CasoDUso(String nombre, String doc, int transacciones) {
		
		this.nombre=nombre;
		this.doc=doc;
		this.transacciones=transacciones;
	}
	public String toString(){
		String d=(doc.length()>0)?("\nDescripcion:"+doc):"";
		return new String("Caso de uso:"+nombre+d+"\nTransacciones="+String.valueOf(transacciones));
	}

	public static void main(String[] args) {
	}
}
