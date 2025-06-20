package TrabalhoFinalPSC;
public abstract class Transacao { 
private double valor; 
private String descricao; 
    
public Transacao(double valor,String descricao) {
    this.valor = valor; 
    this.descricao = descricao;
}
public double getValor() { 
    return valor; 
}
public String getDescricao() { 
    return descricao;
}
public void setValor(double valor) { 
        this.valor = valor;
}

public void setDescricao(String descricao) {
        this.descricao = descricao;
}
public abstract String getTipo(); 
}
 
