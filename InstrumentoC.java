    
package trabalho2ics;
import sintese.*;


public class InstrumentoC extends Dispositivo
{
    private boolean    canal;
    private float      lambda;
    private float      ummenoslambda;
    private float      fase;
    private float      ganho = 1;
    
    private Envoltoria  envAMPLITUDE;
    private Ruido       geradorRUIDO;   //--unidade de Ruído
    private Oscilador   oscSENOIDAL1;    //--unidade Senoidal
    private Oscilador   oscSENOIDAL2;
    private Somador     somaRUIDOSC;
    
    private float fatorCorte;
    

    
    public InstrumentoC(float fc)
    { 
        this(new Ruido(), new Envoltoria(), new Oscilador(), fc);
    }

    
    public InstrumentoC(Ruido ruido_, Envoltoria env_, Oscilador osc_, float fc_)
    {
        super();        
        fatorCorte = fc_; 
        
        this.geradorRUIDO    = ruido_;
        this.envAMPLITUDE    = env_;
        this.oscSENOIDAL1    = osc_;
        

        
        this.oscSENOIDAL2 = new Oscilador();
        this.somaRUIDOSC = new Somador(oscSENOIDAL1,geradorRUIDO);
       

        
        this.setRelogio(0);
    }
    
   
    public void relogio() 
    { 
        oscSENOIDAL2.relogio();
    }
   
   
    public void setRelogio(long n)
    { 
      envAMPLITUDE.setRelogio(n);
      oscSENOIDAL1.setRelogio(n);
      oscSENOIDAL2.setRelogio(n);
      geradorRUIDO.setRelogio(n);
      somaRUIDOSC.setRelogio(n);
      
      this.saida = oscSENOIDAL2.getSaida()*((canal) ? ummenoslambda : lambda);
      
      canal = !canal;
      reset();
    }
        

    public float getSaida()
    {         
      this.saida = oscSENOIDAL2.getSaida()*((canal) ? ummenoslambda : lambda);
      canal      = !canal;
      return ganho*this.saida;
    }
    
    
    public void reset() 
    {        
        envAMPLITUDE.setDuracao(duracao);
        envAMPLITUDE.reset();

        geradorRUIDO.setFrequencia((float)frequencia*fatorCorte);
        geradorRUIDO.setFase(fase);
        geradorRUIDO.setDuracao(duracao);
        geradorRUIDO.reset();
                
        oscSENOIDAL1.setFrequencia((float)frequencia);
        oscSENOIDAL1.setFase(fase);
        oscSENOIDAL1.setDuracao(duracao);
        oscSENOIDAL1.reset(); 
        
        somaRUIDOSC.setFrequencia((float)frequencia);
        somaRUIDOSC.setDuracao(duracao);
        somaRUIDOSC.reset();
        
        oscSENOIDAL2.setDispositivoAmplitude(envAMPLITUDE);
        oscSENOIDAL2.setDispositivoFrequencia(somaRUIDOSC);
        oscSENOIDAL2.setFase(fase);
        oscSENOIDAL2.setDuracao(duracao);
        oscSENOIDAL2.reset(); 
        
    }

    public void setGanho(float g)
    {this.ganho = g;
     
    }

    public void setDuracao(float d)
    {   this.duracao = d;
        envAMPLITUDE.setDuracao(d);
        geradorRUIDO.setDuracao(d);
        oscSENOIDAL1.setDuracao(d);
        oscSENOIDAL2.setDuracao(d);
        somaRUIDOSC.setDuracao(d);
        reset();
    }


    
    public void setFrequencia(float freq)
    { this.frequencia = freq;
      reset();
    }

    public void setLambda(double lambda_)
    { this.lambda = (float)lambda_;
      this.ummenoslambda = 1 - this.lambda;
      reset();
    }

    public void setEnvoltoria(Envoltoria envAMPLITUDE)
    { this.envAMPLITUDE = envAMPLITUDE;
      reset();
    }

    public void setFase(float fase)
    { this.fase = fase;
      reset();
    }

}

