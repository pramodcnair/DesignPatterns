/*
 * Created on 4/01/2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */

/**
 * @author jorge
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */





public class Token {
	public String traduceTipo(int a){
		switch(a){
			case Lexico.ABREPAREN: return "ABREPAREN";
			case Lexico.CIERRAPAREN : return "CIERRAPAREN";
			case Lexico.IDENT: return "IDENT";
			case Lexico.INTEGER: return "INTEGER";
			case Lexico.TAG: return "TAG";
			case Lexico.STRING: return "STRING";
			case Lexico.MULTI_STRING: return "MULTI-STRING";
			case Lexico.FLOAT: return "FLOAT";
			case Lexico.COMA: return "COMA";
			case Lexico.ERROR: return "ERROR";
			case Lexico.EOF: return "EOF";
			
			
		default: return "RARO";
		}
	}
	public static Token EOF = new Token(Lexico.EOF, "",-1);;
	String cadena;
	int tipo;
	int linea;
	//public static Token EOF = new Token(Lexico.EOF, "",-1);
	
	public String toString(){
		return 	"Token	"+traduceTipo(tipo)+	"->"+cadena+" 	:"+linea;
	}
	public boolean esEOF(){
		return tipo==Lexico.EOF;
	}
	public boolean esCadena(){
				return tipo==Lexico.STRING;
			}
	public boolean esCadenaM(){
				return tipo==Lexico.MULTI_STRING;
			}
	public boolean esEntero(){
			return tipo==Lexico.INTEGER;
		}
	public boolean esBooleano(){
				return cadena.equalsIgnoreCase("TRUE")||cadena.equalsIgnoreCase("FALSE");
			}
	public boolean esABREPAREN(){
			return tipo==Lexico.ABREPAREN;
		}
	public boolean esCIERRAPAREN(){
			return tipo==Lexico.CIERRAPAREN;
		}
	public Token(int t, String s, int l){
		cadena =s;
		tipo =t ;
		linea = l;
	}
	  
   



	public static void main(String[] args) {
	}
}
