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
/*
 *  Modificado el 10/01/2005
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
import javax.swing.filechooser.*;
import java.io.*;

public class MDL2UCP {
	static boolean debug=true;
	static Tabla t;
	static private JFileChooser fc = new JFileChooser();
	static Cuestionario c;
	static final float FacEnv[] = { 1.5f, 0.5f, 1f, 0.5f, 1f, 2f, -1f, -1f };
	static final float FacTec[] =
		{ 2f, 2f, 1f, 1f, 1f, 0.5f, 0.5f, 2f, 1f, 1f, 1f, 1f, 1f };
	static double UAW = 0, UUACW = 0, TCF, TFactor, UUCW,UUCP,EF,ACP,RiskFact;
	public Vector aw = new Vector();
	public static Vector generaCuestionarioEnv() {
		Vector cuest = new Vector();
		cuest.add(new DatosC("F1", "Familiar con el proceso Rational Unified", 0));
		cuest.add(new DatosC("F2", "Experiencia en la Aplicación", 0));
		cuest.add(new DatosC("F3", "Experiencia en orientación a objetos", 0));
		cuest.add(new DatosC("F4", "Capacidad analista", 0));
		cuest.add(new DatosC("F5", "Motivación", 0));
		cuest.add(new DatosC("F6", "Requerimientos estables", 0));
		cuest.add(new DatosC("F7", "Trabajadores Part-time", 0));
		cuest.add(new DatosC("F8", "Lenguaje de programación difícil", 0));
		return cuest;
	}
	public static Vector generaCuestionarioTec() {
		Vector cuest = new Vector();
		cuest.add(new DatosC("F1", "Sistema Distribuido", 0));
		cuest.add(
			new DatosC(
				"F2",
				"Desempeño de la aplicación, tiempo de respuesta",
				0));
		cuest.add(new DatosC("F3", " Eficiencia de usuario final", 0));
		cuest.add(new DatosC("F4", "Procesamiento interno complejo", 0));
		cuest.add(new DatosC("F5", " Código reusable", 0));
		cuest.add(new DatosC("F6", " Facil de instalar", 0));
		cuest.add(new DatosC("F7", " Facil de usar", 0));
		cuest.add(new DatosC("F8", "Portable", 0));
		cuest.add(new DatosC("F9", "Fácil de modificar", 0));
		cuest.add(new DatosC("F10", "Concurrente", 0));
		cuest.add(
			new DatosC("F11", "Características especiales de seguridad", 0));
		cuest.add(new DatosC("F12", "Provee acceso de terceras partes", 0));
		cuest.add(
			new DatosC(
				"F13",
				"Facilidades especiales de entrenamiento de usuario",
				0));
		return cuest;
	}

	public static Vector convierteAct(VectorDActores n) {
		Vector lista = new Vector();
		int t = n.actorcitos.size();
		for (int i = 0; i < t; i++) {
			Actor a = (Actor) n.actorcitos.elementAt(i);
			lista.add(new Datos(a.nombre, a.doc));
		}
		return lista;
	}

	public static void main(String args[]) {
		VectorDActores actores = new VectorDActores();
		VectorDUC casos = new VectorDUC();
		Vector vcasos = new Vector();
		Vector vactores = new Vector();
		Sintax analizador = new Sintax();
		String nombre_de_archivo="";
		int pos;
		/*if (args.length == 0) {
			System.out.println("Se usa \"java MDLUCP <nombre de archivo.dl>\"");
			System.exit(1);
		}*/
		Pantalla pantalla=new Pantalla();
		while(!pantalla.cerrar){
		 //System.out.println("a");
		}
		JFrame f=new JFrame();
		fc.addChoosableFileFilter(new ImageFilter());
		int returnVal = fc.showOpenDialog(f);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //this is where a real application would open the file.
                nombre_de_archivo=file.getAbsolutePath();
            } else {
            	System.out.println("Se usa \"java MDLUCP <nombre de archivo.dl>\"");
                System.exit(1);
            }
            System.out.println(nombre_de_archivo);
		analizador.parseaArchivo(nombre_de_archivo);
		actores = analizador.regresaActoresU();
		int total = actores.actorcitos.size();
		for (pos = 0; pos < total; pos++) {
			Actor elactor = (Actor) actores.actorcitos.elementAt(pos);
			Datos eldato = new Datos(elactor.nombre, elactor.doc, 1);
			vactores.add(eldato);
		}
		
		Tabla tactores=new Tabla(vactores);
		while(!tactores.cerrar){
			//Esperar a que se cierre la forma para abrir la sgte
		}
		vactores=tactores.sacar();
		total = vactores.size();

		for (pos = 0; pos < total; pos++) {
			Datos eldato = ((Datos) vactores.elementAt(pos));
			UAW += (eldato.complejidad + 1) * eldato.valor;
		}
		casos = analizador.regresaCasos();
		total = casos.loscasos.size();
		for (pos = 0; pos < total; pos++) {
			CasoDUso cu = (CasoDUso) casos.loscasos.elementAt(pos);
			int complex=0;
			if (cu.transacciones <= 3)
				complex = 0;
			else if (cu.transacciones <= 7)
				complex = 1;
			else complex = 2;
			Datos du = new Datos(cu.nombre, cu.doc, complex);
			vcasos.add(du);
			if(debug)System.out.println("Caso de uso"+cu.nombre+" interacciones="+cu.transacciones+" complex="+complex);
		}
		Tabla tcasos=new Tabla(vcasos);
		while(!tcasos.cerrar){
			//Esperar a que se cierre la forma para abrir la sgte
		}
		vcasos=tcasos.sacar();
		total = vcasos.size();
		for (pos = 0; pos < total; pos++) {
			Datos eldato = ((Datos) vcasos.elementAt(pos));
			switch(eldato.complejidad){
				case 0:
				UUCW += 5;//(eldato.complejidad + 1) * eldato.valor;
				break;
				case 1:
				UUCW += 10;//(eldato.complejidad + 1) * eldato.valor;
				break;
				case 2:
				UUCW += 15;//(eldato.complejidad + 1) * eldato.valor;
				break;
			}
		}
		UUCP = UAW + UUCW;
		Vector factec=generaCuestionarioTec();
		Cuestionario ctec=new Cuestionario(factec);
		while(!ctec.cerrar){
		 //System.out.println("a");
		}
		factec=ctec.sacar();
		//TODO mostrar cuadro con FacTec
		//copiar valores a factec
		float cont = 0;
		for (pos = 0; pos < 13; pos++)
			cont += ((DatosC)(factec.elementAt(pos))).Value * FacTec[pos];
		TCF = 0.6 + (0.01 * cont);
		
		Vector facenv=generaCuestionarioEnv();
		Cuestionario cenv=new Cuestionario(facenv);
		while(!cenv.cerrar){
		 //System.out.println("a");
		}
		facenv=cenv.sacar();
		//	TODO mostrar cuadro con FacEnv
		//copiar valores a facenv
		
		cont = 0;
		for (pos = 0; pos < 8; pos++)
			cont += ((DatosC)(facenv.elementAt(pos))).Value * FacEnv[pos];
		EF = 1.4 + (-0.03 * cont);
		ACP = UUCP * EF * TCF;
		RiskFact=0;
		for(pos = 0; pos < 6; pos++)
			if(((DatosC)(facenv.elementAt(pos))).Value<3)RiskFact++;
		for(pos = 6; pos < 8; pos++)
			if(((DatosC)(facenv.elementAt(pos))).Value>3)RiskFact++;
		Vector resultados=new Vector();
		resultados.add(new DatosR("Puntos por actores sin ajustar",""+UAW));
		resultados.add(new DatosR("Puntos por casos de uso sin ajustar",""+UUCW));
		resultados.add(new DatosR("Puntos de casos de uso sin ajustar",""+UUCP));
		resultados.add(new DatosR("Factores de ajuste Técnicos",""+TCF));
		resultados.add(new DatosR("Factores de ajuste de ambiente",""+EF));
		resultados.add(new DatosR("Puntos de casos de uso ajustados",""+ACP));
		resultados.add(new DatosR("factor de Riesgo",""+RiskFact));
		Resultado r=new Resultado(resultados);
		}
}