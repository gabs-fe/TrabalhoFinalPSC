package TrabalhoFinalPSC;
public abstract class Transacao { // criando classe abstrata (não representa algo completo por si so)
private double valor; // variavel (valor)
private String descricao; // variavel (contadeluz,agua..)
    
public Transacao(double valor,String descricao) {
    this.valor = valor; // atribuindo valor
    this.descricao = descricao; // atribuindo valor
}
public double getValor() { // metodo get (pegar/ler o valor atribuido)
    return valor; // como o atrivuto é private não pode ser acessado diretamente de fora da classe.
}
public String getDescricao() { // metodo get
    return descricao; // lendo atributo 
}
public void setValor(double valor) { // metodo set para alterar valor depois de criado.
        this.valor = valor;
}

public void setDescricao(String descricao) {
        this.descricao = descricao;
}
public abstract String getTipo(); // "Assinatura do abstract" ele obriga que as subclasses que irao herdar dele digam o "tipo de transação"
}
 