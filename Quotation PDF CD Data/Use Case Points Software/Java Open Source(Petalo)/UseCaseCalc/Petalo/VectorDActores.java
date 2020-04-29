import java.util.Vector;

/*
 * Created on 12/01/2004
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
public class VectorDActores {
	Vector actorcitos;
	
	public VectorDActores(){
		actorcitos=new Vector();
	}

	public static void main(String[] args) {
	}

	/**
	 * @param tokens
	 */
	public void agregaActor(VectorDeTokens tokens) {
		Token t=tokens.sacaToken();
		String nombre=t.cadena;
		String doc="";
		boolean eshijo=false;
		String quid;
		if(!t.esCadena()){
			ManejaErrores.avientaError("Actor malhecho--->"+t.cadena+" esperaba un nombre como cadena");
		}
		t=tokens.sacaToken();
		while(!t.esEOF()){
			if(t.cadena.equalsIgnoreCase("quid")){
				quid=tokens.sacaToken().cadena;
				t=tokens.sacaToken();
				continue;
			}
			if(t.cadena.equalsIgnoreCase("stereotype")){
				t=tokens.sacaToken();
				if(!t.cadena.equalsIgnoreCase("\"actor\"")){
					ManejaErrores.avientaError("Actor malhecho--->"+nombre+" su estereotipo no es actor sino"+t.cadena);
				continue;
				}
			}
			t=tokens.sacaToken();
			//TODO documentacion
		}
		
		Actor nvoActor= new Actor(nombre,doc,eshijo);
		actorcitos.add(nvoActor);
		//System.out.println("\t\tSe agregó el Actor\n"+nombre);
	}
	public void add(Actor a){
		this.actorcitos.add(a);	
	}
	public VectorDActores regresaAU(){
		VectorDActores nuevo= new VectorDActores();
		int t=actorcitos.size();
		for(int i=0;i<t;i++){
			if(!((Actor)actorcitos.elementAt(i)).eshijo){
				nuevo.add((Actor)actorcitos.elementAt(i));
			}
		}
		return nuevo;
	}
	
	public String toString(){
		return actorcitos.toString();
	}
	
}
