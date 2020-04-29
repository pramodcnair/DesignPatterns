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
 * Created on 12/01/2004
 *
 */

/**
 * @author Jorge Gutierrez Ramirez
 *
 */
 
import java.util.Vector;

public class VectorDUC {

	Vector loscasos;
	public VectorDUC(){
	  loscasos = new Vector();
	}

	/**
	 * @param tokens
	 */
	
	public void agregaUC(CasoDUso elcaso) {
		loscasos.add(elcaso);
	  	
	//	System.out.println("\t\tSe agreg´un caso de uso\n"+elcaso);
		
	}
	public String toString(){
		return loscasos.toString();
	}
}
