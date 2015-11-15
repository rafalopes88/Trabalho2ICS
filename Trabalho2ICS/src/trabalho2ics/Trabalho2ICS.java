package trabalho2ics;
import sintese.*;

public class Trabalho2ICS
{  
   InstrumentoA instrumentoA;
   Envoltoria   env;   
   Envoltoria   envFREQUENCIACORTE;   
   Curva        curva;      
   
   public Trabalho2ICS()
   { 
     //instrumentoA  = new InstrumentoT2A(1);        //---instrumento atonal
     instrumentoA  = new InstrumentoA(0.2f);     //---quase tonal
     //instrumentoA  = new InstrumentoT2A(0.065f);   //---atonal
     //instrumentoA  = new InstrumentoT2A(0.001f);   //---atonal ruidoso
     //instrumentoA  = new InstrumentoT2A(0.0001f);  //---tonal: formante estreita
     //instrumentoA  = new InstrumentoT2A(0.00001f); //---tonal: som puro
     env   = new Envoltoria(); 
     curva = new Curva(720);     

     curva.addPonto(  0f,   0f);
     curva.addPonto( 30f, 400f);
     curva.addPonto(240f, 300f);
     curva.addPonto(720f,   0f);

     env.setCURVA(curva);
           
     instrumentoA.setEnvoltoria(env);
     instrumentoA.setLambda(0.5f);
     instrumentoA.setFase(0f);
     instrumentoA.setGanho(103);     
     
        
     Melodia m2 = Tema.tema_aa_fuga1();
     //Melodia m2 = Tema.tema_aa_drawing_quintet_flauta();
     //Melodia m2 = Tema.tema_duda_no_frevo_eq();
     
     m2.getAutor();     
     
     //m2.setAndamento(0.45f);
     m2.setAndamento(0.95f);

     Som som = m2.getSom(instrumentoA);     
     som.setNome("teste2A");   
     som.visualiza();

     try{ System.in.read();
          System.exit(0);
        }
     catch(Exception e){};          
     
   }      
      
   public static void main(String args[])
   { 
       new Trabalho2ICS();
   }   

}
