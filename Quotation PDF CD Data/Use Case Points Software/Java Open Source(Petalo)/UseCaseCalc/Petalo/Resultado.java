import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

class DatosR {
	String s1;
	String s2;
	public DatosR(String s1, String s2) {
		this.s1 = s1;
		this.s2 = s2;
	}
	public String toString(){
	 String tostring="["+s1+" : "+s2+"]";
	 return tostring;
	}
}
public class Resultado {
	private JPanel principal, p1, p2, px, pb, pt, pt2, pt3, pbs;
	private JLabel s1s[];
	private JLabel s2s[];
	private Vector list;
	private static JFrame f;
	boolean cerrar = false;
	JTextField hhu = new JTextField("20", 12);
	JTextField hesa = new JTextField(12);
	JTextField hea = new JTextField(12);
	public Resultado(Vector v) {
		list = v;
		int tam = list.size();
		principal = new JPanel();
		p1 = new JPanel();
		p2 = new JPanel();
		p1.setBackground(Color.white);
		p2.setBackground(Color.white);
		principal.setBackground(Color.white);
		principal.setSize(new Dimension(300, tam * 30));
		s1s = new JLabel[tam];
		s2s = new JLabel[tam];
		p1.setLayout(new GridLayout(tam + 1, 1));
		p2.setLayout(new GridLayout(tam + 1, 1));
		principal.setLayout(new BorderLayout());
		principal.add("Center", p1);
		principal.add("East", p2);
		agregarComponentes();
		pb = new JPanel();
		pt = new JPanel();
		pt2 = new JPanel();
		pt3 = new JPanel();
		pbs = new JPanel();
		pb.setBackground(Color.white);
		pbs.setBackground(Color.white);
		pt.setBackground(Color.white);
		pt2.setBackground(Color.white);
		pt3.setBackground(Color.white);
		pb.setLayout(new GridLayout(4, 1));
		px = new JPanel();
		px.setBackground(Color.white);
		px.setLayout(new BorderLayout());
		pt.setLayout(new BorderLayout());
		pt2.setLayout(new BorderLayout());
		pt3.setLayout(new BorderLayout());
		hesa.setEditable(false);
		hea.setEditable(false);
		JButton b = new JButton("Aceptar");
		b.setBackground(Color.white);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String comando = e.getActionCommand();
				if (comando.equals("Aceptar")) {
					cerrar = true;
					System.exit(0);
				}
			}
		});
		JButton c = new JButton("Recalcular");
		c.setBackground(Color.white);
		c.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String comando = e.getActionCommand();
				if (comando.equals("Recalcular")) {
					Calcula();
				}
			}
		});
		
		pt.add("Center", new JLabel("Horas Hombre por UCP "));
		pt.add("East", hhu);
		pt2.add("Center", new JLabel("Horas Hombre Estimadas sin ajustar"));
		pt2.add("East", hesa);
		pbs.add(c);
		pbs.add(b);
		pt3.add("Center", new JLabel("Horas Hombre Estimadas ajustadas"));
		pt3.add("East", hea);
		pb.add(pt);
		pb.add(pt2);
		pb.add(pt3);
		pb.add(pbs);
		px.add("Center", principal);
		px.add("South", pb);
		Calcula();
		f = new JFrame("Resultado");
		f.setSize(new Dimension(500, (tam * 30) + 300));
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				cerrar = true;
				System.exit(0);
			}
		});
		f.getContentPane().add(ref());
		f.show();
	}
	public void Calcula() {
		float res1 =
			Float.parseFloat(((DatosR) list.elementAt(2)).s2)
				* Float.parseFloat((String) hhu.getText());
		float res2 =
			Float.parseFloat(((DatosR) list.elementAt(5)).s2)
				* Float.parseFloat((String) hhu.getText());
		hesa.setText(res1 + "");
		hea.setText(res2 + "");
	}
	public JPanel ref() {
		return px;
	}
	private void agregarComponentes() {
		Panel x1 = new Panel();
		x1.setBackground( Color.lightGray );
		Panel x2 = new Panel();
		x2.setBackground( Color.lightGray);
		Panel x3 = new Panel();
		x3.setBackground( Color.lightGray);
		Panel x4 = new Panel();
		x4.setBackground( Color.lightGray);
		x1.setLayout(new GridLayout(1, 1));
		x2.setLayout(new GridLayout(1, 1));
		x3.setLayout(new GridLayout(1, 1));
		x4.setLayout(new GridLayout(1, 1));
		p1.add(new JLabel("Descripcion "));
		p2.add(new JLabel("Valor"));
		DatosR aux = null;
		aux=(DatosR) list.elementAt(6);
		String valHH="";
		float facTec=Float.parseFloat(aux.s2);
		if(facTec<3) valHH="20";
		else if(facTec<4) valHH="28";
		else{
		 JOptionPane.showMessageDialog(null,
		 "Los factores ambientales, presentan un factor de riesgo alto, el valor de hora hombre por caso de uso deberá ser alto",
		 "Cuidado, demasiado riesgo",
		 JOptionPane.WARNING_MESSAGE );
		 valHH="36";
		 
		  }
		hhu.setText(valHH);
		for (int i = 0; i < list.size(); i++) {
			aux = (DatosR) list.elementAt(i);
			System.out.println("Aux en "+i+":\n\t"+aux);
			s1s[i] = new JLabel(aux.s1 + " ");
			switch(i){
				case 2:x1.add(s1s[i]);
				p1.add(x1);
				break;
				case 5:x3.add(s1s[i]);
				p1.add(x3);
				break;
			    default: p1.add(s1s[i]);
				break;
			}
			
			s2s[i] = new JLabel(aux.s2 + " ");
			switch(i){
				case 2:x2.add(s2s[i]);
				p2.add(x2);
				break;
				case 5:x4.add(s2s[i]);
				p2.add(x4);
				break;
				default: p2.add(s2s[i]);
				break;
			}
		}
	}
	static Resultado t;

}