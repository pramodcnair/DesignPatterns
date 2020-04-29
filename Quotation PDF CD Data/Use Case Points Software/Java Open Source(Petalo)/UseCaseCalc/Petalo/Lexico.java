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
 
import java.io.IOException;


class Lexico{
	public static final int ABREPAREN    = 0;
	public static final int CIERRAPAREN  = 1;
	public static final int IDENT        = 2;
	public static final int INTEGER      = 3;
	public static final int FLOAT        = 4;
	public static final int BOOLEAN      = 5;
	public static final int TAG          = 6;
	public static final int STRING       = 7;
	public static final int MULTI_STRING = 8;
	public static final int COMA         = 9;
	public static final int EOF          = -1;
	public static final int ERROR        = 10;
	int nlinea=0; //empieza desde 0, porque aun no se lee ninguna línea
	Lector arch;
	public Lexico(Lector a){
		arch=a;
	}
	public boolean esdigito(char a){

		switch(a){
			case '.':
			case '0':
			case '1':  
			case '2': 
			case '3': 
			case '4': 
			case '5':		 
			case '6': 
			case '7': 
			case '8': 
			case '9': return true;
			default: return false;	
		}
	  
	}
	
	
	public VectorDeTokens anaLex(){
		VectorDeTokens listadetokens = new VectorDeTokens();
		Token nvoToken;
		try {
			do{
				nvoToken= dameToken();
				listadetokens.ingresaToken(nvoToken);
			}while(!nvoToken.esEOF());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listadetokens;		
	}
	
	public Token dameToken()throws IOException {
		int    tipo;
		int estado=0;
		char leido=' ';
		String lexema="";
		Token  reconocido;
		boolean estag=false, esreal=false;
                /**
                * quitar espacios
                * */
                do{
                    leido=arch.leec();
                }while(Character.isWhitespace((char)leido));
			
		  switch(leido) {
		  	case '(': return new Token(ABREPAREN, "(", arch.lineact());
		  	case ')': return new Token(CIERRAPAREN, ")", arch.lineact());
		  	case ',': return new Token(COMA, ",", arch.lineact());
			case 65535: return new Token(EOF, "", arch.lineact());
			case '|':
					lexema=arch.leelinea();
					int lineainicia=arch.lineact();
					while(arch.siguiente()=='|'){
						leido=arch.leec();
						lexema+=arch.leelinea();	
					}
					return new Token(MULTI_STRING, lexema, lineainicia);
			case '"':
				lexema="\"";
				leido=arch.leec();
				while(leido != '"') {
				  if(leido == '\\') {
					lexema+="\\";
					leido=arch.leec();
				  }
				  lexema+=leido;
				  leido=arch.leec();
				}
				lexema+="\"";
				return new Token(STRING, lexema, arch.lineact());
			case '@':
				estag=true;
			case '0':
			case '1': 
			case '2': 
			case '3': 
			case '4': 
			case '5':
			case '6': 
			case '7': 
			case '8': 
			case '9': 
			case '-': 
			case '+':
				lexema+=leido;
				while(esdigito(arch.siguiente())){
					leido=arch.leec();
					if(leido=='.'){
						esreal=true;
					}
					lexema+=leido;
				}
				return new Token(((estag)?TAG:((esreal)?FLOAT:INTEGER)), lexema, arch.lineact());
			default: 
				if(Character.isJavaIdentifierStart(leido)){
					lexema+=leido;
					while(Character.isJavaIdentifierPart(arch.siguiente())){
						leido=arch.leec();
						lexema+=leido;
					}
					return new Token(IDENT, lexema, arch.lineact());
				}
				else{
					ManejaErrores.avientaError("Error procesando archivo se encontró"+lexema+"en linea"+arch.lineact()+" y pos"+arch.cactual+"\n\t"+arch.muestralinea());
					return new Token(ERROR, lexema, arch.lineact());
				}
		  }
		
	  }
	public static void main(String[] args) {
		System.out.println("lexico");
	}
}
