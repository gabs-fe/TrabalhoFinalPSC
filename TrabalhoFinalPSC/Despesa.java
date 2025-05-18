package TrabalhoFinalPSC;
public class Despesa extends Transacao {
    public Despesa(double valor, String descricao) {
     super(valor,descricao); // herdando da classe mãe
    }
    @Override // avisando que está mudando
    public String getTipo() {
        return "Despesa"; // atribuindo o tipo
    }
}

