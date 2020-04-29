import java.util.Vector;
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
public class VectorDeTokens {
  Vector losTokens;

  int posact=0;
  public VectorDeTokens() {
  	losTokens=new Vector();
  }
  public void ingresaToken(Token t){
    losTokens.add(t);
  }
  
  public Token sacaToken(){
	if(posact>=losTokens.size()){
		return Token.EOF;
	}
   return (Token) losTokens.elementAt(posact++);
  }
  public int leePosAct(){return posact;}
  
  public Token siguienteToken(){
	 if(posact>=losTokens.size()){return Token.EOF;}
	 return (Token) losTokens.elementAt(posact+1);
	}
	public String toString(){
			String x="";
			int tam=losTokens.size();
			for(int i=0;i<tam;i++)x+=(losTokens.elementAt(i))+"\n";
			return x;
		}
	
	public String toStringLex(){
				return toStringLex(0,losTokens.size());
	}
	public String toStringLex(int i){
					return toStringLex(i,losTokens.size());
		}
	public String toStringLex(int inicio, int fin){
					String x="";
					if(fin>losTokens.size()||fin<0||inicio<0)return "";
					int tam=fin;
					for(int i=inicio;i<tam;i++)
						 x+=
						  ((Token)losTokens.elementAt(i)).cadena
						 +"\n";
					return x;
	}
		
	public static void main(String[] args) {
	}
}
