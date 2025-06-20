package TrabalhoFinalPSC;

public class Despesa extends Transacao {
    public Despesa(double valor, String descricao) {
     super(valor,descricao);
    }
    @Override 
    public String getTipo() {
        return "Despesa"; 
    }
}

