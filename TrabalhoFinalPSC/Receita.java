package TrabalhoFinalPSC;

public class Receita extends Transacao { 
  public Receita(double valor,String descricao) {
    super(valor, descricao); 
  }  
  @Override 
  public String getTipo() {
    return "Receita"; 
  }
}
