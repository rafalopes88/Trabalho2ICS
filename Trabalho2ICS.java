package trabalho2ics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import sintese.Curva;
import sintese.Envoltoria;
import sintese.Melodia;
import sintese.Polifonia;
import sintese.Som;
import sintese.Tema;
import sintese.Voz;

public class Trabalho2ICS extends JPanel implements ActionListener
{  
   InstrumentoA instrumentoA;
   InstrumentoB instrumentoB;
   InstrumentoC instrumentoC;
   Envoltoria   env;   
   Envoltoria   envFREQUENCIACORTE;   
   Curva        curva;      
   Voz          v1;
   Voz          v2;
   Voz          v3;
   Polifonia    p;
   JButton botaoA, botaoB, botaoC, botaoT;
   private static JFrame janela;
   private int indexInstrumentos, indexTema;
   LoadPlayer thPlayer;
   Thread threadPlayer;
   JComboBox listaTema;
   JTextArea nota;
   JScrollPane sp;
   
   
   
   //Seleciona qual instrumento tocar a partir de um indice

   private static void createAndShowGUI() {
       
       janela = new JFrame("Tocador de mid");
       janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       
       janela.add(new Trabalho2ICS());

       
       janela.pack();
       janela.setVisible(true);
   }

   public class LoadPlayer implements Runnable{
	   
	   public void getNotas(Melodia melodia)
	   {
		   String str = "";
		   str += "\n" + melodia.getNome()+"\n";
	       System.out.println("\n" + melodia.getNome());
	       for(int i=0; i < melodia.getNumeroDeNotas(); i++){
	    	   str += "Nota: " + melodia.getNota(i).getNome() +
	    	           "  Oitava: " + melodia.getNota(i).getOitava() + "\n";
	           //System.out.println("Nota: " + melodia.getNota(i).getNome() +
	           //"  Oitava: " + melodia.getNota(i).getOitava());
	       }
	       nota.setText(str);
	   }
	   
	   public void playInst(int index)
	   {
	       if(index == 0)
	       {
	           Som som = v1.getSom();
	           som.setNome("voz 1");
	           som.visualiza();
	       }
	       else if (index == 1)
	       {
	           Som som = v2.getSom();
	           som.setNome("voz 2");
	           som.visualiza();
	       }
	       else if (index == 2){
	           Som som = v3.getSom();
	           som.setNome("voz 3");
	           som.visualiza();
	       }
	       else if(index == 3)
	       {
	            Som som = p.getSom();
	           som.setNome("polifonia");
	           som.visualiza();
	           
	       }
	   }
	   
	   public Melodia selecTema(int indice)
	   {
	      Melodia[] temas = {Tema.melodiasexta1(), Tema.sonata_scarlatti(), Tema.tema_aa_drawing_quintet_flauta(),
	      Tema.tema_aa_fuga1(), Tema.tema_bwv775_invencao14_direita(), Tema.tema_bwv775_invencao4_direita(),
	      Tema.tema_bwv775_invencao4_esquerda(), Tema.tema_bwv988goldberg_v03_eq(), Tema.tema_duda_no_frevo_eq(),
	      Tema.tema_duda_no_frevo_eqYYY()};
	
	      return temas[indice];
	   }
	   
	   public void run(){
		   
		 instrumentoA = new InstrumentoA(0.1f);
		 instrumentoB  = new InstrumentoB(0.1f);
		 instrumentoC = new InstrumentoC(0.1f);
		
		 env   = new Envoltoria();
		 curva = new Curva(720);
		 curva.addPonto(  0f,   0f);
		 curva.addPonto( 30f, 400f);
		 curva.addPonto(240f, 300f);
		 curva.addPonto(720f,   0f);
		   
		 env.setCURVA(curva);
		
		 v1 = new Voz(instrumentoA);
		 v2 = new Voz(instrumentoB);
	 	 v3 =  new Voz(instrumentoC);
		
		 
		       
		 instrumentoA.setEnvoltoria(env);
		 instrumentoA.setLambda(0.5f);
		 instrumentoA.setFase(0f);
		 instrumentoA.setGanho(103);  
		 
		 instrumentoB.setEnvoltoria(env);
		 instrumentoB.setLambda(0.5f);
		 instrumentoB.setFase(0f);
		 instrumentoB.setGanho(103);
		 
		 instrumentoC.setEnvoltoria(env);
		 instrumentoC.setLambda(0.5f);
		 instrumentoC.setFase(0f);
		 instrumentoC.setGanho(103);
		
		 
		 Melodia m1 = selecTema(indexTema); //Melodia selecionada na interface
		 m1.getAutor();     
		 m1.setAndamento(1f);
		 
		 Melodia m2 = selecTema(indexTema);
		 
		 Melodia m3 = selecTema(indexTema);
		 
		         
		 v1.addMelodia(m1);
		 v2.addMelodia(m2);
		 v3.addMelodia(m3);
		
		 v1.ganho(1);
		 v2.ganho(1);
		 v3.ganho(2);
		 
		 p = new Polifonia();
		 p.addVoz(v1);
		 p.addVoz(v2);
		 p.addVoz(v3);
		 
		 p.ganho(1.6f);
		 
		 getNotas(m1);
	     getNotas(m2);
	     getNotas(m3);
		 
		 //Toca o instrumento selecionado 
		 playInst(indexInstrumentos); 
		
		 try{ System.in.read();
		      System.exit(0);
		 }
		 catch(Exception e){};   
	  }
   }

   public Trabalho2ICS()
   { 
	 super(new BorderLayout());
	 
         String[] nometema = {"Melodiasexta1", "Sonata_scarlatti", "Tema_aa_drawing_quintet_flauta",
           "Tema_aa_fuga1", "Tema_bwv775_invencao14_direita", "Tema_bwv775_invencao4_direita",
           "Tema_bwv775_invencao4_esquerda", "Tema_bwv988goldberg_v03_eq", "Tema_duda_no_frevo_eq",
           "Tema_duda_no_frevo_eqYYY"};

        listaTema = new JComboBox(nometema);

	 
	 nota = new JTextArea(5,30);
	 nota.setText("Nota: Oitava:");
     nota.setEditable(false);
     
     sp = new JScrollPane (nota);
     sp.setPreferredSize(new Dimension(350, 110));
     sp.setVisible(true);
     
	 botaoA = new JButton("Intrumento A");
	 botaoB = new JButton("Intrumento B");
	 botaoC = new JButton("Intrumento C");
	 botaoT = new JButton("Polifonia");
	 
	 botaoA.addActionListener(this);
	 botaoB.addActionListener(this);
	 botaoC.addActionListener(this);
	 botaoT.addActionListener(this);
	 
	 JPanel temasPainel = new JPanel();
	 JPanel mostraNota = new JPanel();
	 JPanel botaoPainel = new JPanel();
	 
	 temasPainel.add(listaTema);
	 mostraNota.add(sp);
	 
	 botaoPainel.add(botaoA);
	 botaoPainel.add(botaoB);
	 botaoPainel.add(botaoC);
	 botaoPainel.add(botaoT);
	 
	 add(temasPainel, BorderLayout.PAGE_START);
	 add(mostraNota, BorderLayout.CENTER);
	 add(botaoPainel, BorderLayout.PAGE_END);
	 thPlayer = new LoadPlayer();
   }      
      
   public static void main(String args[])
   { 
	   SwingUtilities.invokeLater(new Runnable() {
	       public void run() {
	           UIManager.put("swing.boldMetal", Boolean.FALSE);
	           createAndShowGUI();
	       }
		 });
   }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == botaoA) {
            indexInstrumentos = 0;
        }
		else if (e.getSource() == botaoB) {
			indexInstrumentos = 1;
        }
		else if (e.getSource() == botaoC) {
			indexInstrumentos = 2;
        }
		else if (e.getSource() == botaoT) {
			indexInstrumentos = 3;
        }
		indexTema = listaTema.getSelectedIndex();
		threadPlayer = new Thread(thPlayer);
		threadPlayer.start();
	
		
		
	}  

}
