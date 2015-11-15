package trabalho2ics;
import sintese.*;

public class Trabalho2ICS
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
   
   
   
   //Seleciona qual instrumento tocar a partir de um indice
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

   public Trabalho2ICS()
   { 
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

     
     Melodia m1 = selecTema(3); //Melodia selecionada na interface
     m1.getAutor();     
     m1.setAndamento(1f);
     
     Melodia m2 = selecTema(3);
     
     Melodia m3 = selecTema(3);
     
             
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
     
     //Toca o instrumento selecionado 
     playInst(3); //Substituir por resultado da combobox

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
