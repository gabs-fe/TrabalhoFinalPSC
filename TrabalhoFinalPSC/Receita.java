package TrabalhoFinalPSC;

public class Receita extends Transacao { // extends é herança (esta herdando a classe transacao)
  public Receita(double valor,String descricao) {
    super(valor, descricao); // chama o construtor da classe mãe (Transacao) e enviar os dados para a mesma.
  }  
  @Override // avisando que vc esta substituindo um metodo herdado (getTipo)
  public String getTipo() { // identificou o metodo herdado
    return "Receita"; // atribuiu o tipo
  }
}