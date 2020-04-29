import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
/*class DatosC{
	String Factor 
	String Description 
	float Weight 
	float Value 
	float Weighted 
	float value
	}*/
class Datos{
	String nom;
	String desc;
	int complejidad=1;
	//nivel es baja 0, media 1, alta 2
	int valor=1;
	public Datos(String nom,String desc,int complejidad,int valor){
		this.nom=nom;
		this.desc=desc;
		this.complejidad=complejidad;
		this.valor=valor;
		}
	public Datos(String nom,String desc,int complejidad){
		this.nom=nom;
		this.desc=desc;
		this.complejidad=complejidad;
		}
	public Datos(String nom,String desc){
		this.nom=nom;
		this.desc=desc;
		}
	public String toString(){
		return (nom+" "+desc+" "+complejidad+" "+valor+"\n");
		}
	}
public class Tabla{
   private JPanel principal,p1,p2,pb,px;
   private JLabel noms[];
   private JTextField vals[];
   private JComboBox complejidad[];
   private Vector list;
   private JScrollPane cont;
   static JFrame f;
   boolean cerrar=false;
   String[] opc={"bajo","medio","alto"};
   public Tabla(Vector v){
   list=v;
   int tam=list.size();
   principal= new JPanel();
   p1= new JPanel();
   p2= new JPanel();
   principal.setSize(new Dimension(300,tam*20));
   p1.setBackground(Color.white);
   p2.setBackground(Color.white);
   principal.setBackground(Color.white);
   noms=new JLabel[tam];
   vals=new JTextField[tam];
   complejidad=new JComboBox[tam];
   principal.setLayout( new BorderLayout());
   p1.setLayout( new GridLayout(tam+1,1 ));
   p2.setLayout( new GridLayout(tam+1,2 ));
   principal.add("Center",p1);
   principal.add("East",p2);
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
		if(comando.equals("Insertar")){ sacar(); 
			cerrar=true;
			f.hide();
}
		}
	   });
	cont=new JScrollPane(principal);
	cont.setBackground(Color.white);
	cont.setSize(new Dimension(450,350));
	   pb.add(b);
	   px.add("Center",cont);
	   px.add("South",pb);
   agregarComponentes();
   f=new JFrame("Actores y Casos de uso");
   f.setBackground(Color.white);
	   f.setSize(new Dimension(500,400));
	   f.addWindowListener(new WindowAdapter() {
				   public void windowClosing(WindowEvent we) {
					   //cerrar=true;
					   //f.hide();
					   }
			   });
	   f.getContentPane().add( px);
	   f.show();
   }
public JScrollPane ref(){
	return cont;
	}   
private void agregarComponentes(){
   p1.add(new JLabel("Nombre "));
   p2.add(new JLabel("Complejidad "));
   p2.add(new JLabel("Valor "));
   Datos aux=null;
   for(int i=0;i<list.size();i++){
   aux=(Datos)list.elementAt(i);
   noms[i]=new JLabel(aux.nom);
   noms[i].setToolTipText(aux.desc);
   p1.add(noms[i]);
   complejidad[i]=new JComboBox(opc);
   complejidad[i].setBackground(Color.white);
   complejidad[i].setMaximumRowCount(3);
   complejidad[i].setSelectedIndex(aux.complejidad);
   p2.add(complejidad[i]);
   vals[i]=new JTextField(""+aux.valor,3);
   p2.add(vals[i]);
   	}
}
public Vector sacar(){
   Datos aux=null;
   for(int i=0;i<list.size();i++){
   aux=(Datos)list.elementAt(i);
   aux.complejidad=complejidad[i].getSelectedIndex();
   aux.valor=Integer.parseInt((String)vals[i].getText());
   //list.insertElementAt(aux,i);
  }
System.out.println(list);
return list;
}
static Tabla t;
public static void main(String args[]){
	JFrame f=new JFrame("Tabla");
	Vector x=new Vector();
	x.addElement(new Datos("objetin","mira que bonito es objetin",0,5));
	x.addElement(new Datos("objetote","mira que feo es objetote",2,20));
	x.addElement(new Datos("objeto","mira que simple es objeto",1,10));
	x.addElement(new Datos("objeto-x","mira que enigmatico es objeto-x",2,20));
	Vector res=new Vector();
	t=new Tabla(x);
	while(!t.cerrar);
	System.out.println(res);
	
	}
}