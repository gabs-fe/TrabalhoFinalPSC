package TrabalhoFinalPSC;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FinanceiroGUI extends JFrame {

    private ArrayList<Transacao> transacoes = new ArrayList<>();

    private JTextField txtDescricao;
    private JTextField txtValor;
    private JComboBox<String> tipoTransacao;
    private JTextArea areaTransacoes;
    private JLabel lblSaldo;

    public FinanceiroGUI() {
        setTitle("Sistema Financeiro");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel painelEntrada = new JPanel(new GridLayout(4, 2));
        painelEntrada.add(new JLabel("Tipo:"));
        tipoTransacao = new JComboBox<>(new String[]{"Receita", "Despesa"});
        painelEntrada.add(tipoTransacao);

        painelEntrada.add(new JLabel("Descrição:"));
        txtDescricao = new JTextField();
        painelEntrada.add(txtDescricao);

        painelEntrada.add(new JLabel("Valor:"));
        txtValor = new JTextField();
        painelEntrada.add(txtValor);

        JButton btnAdicionar = new JButton("Adicionar");
        painelEntrada.add(btnAdicionar);

        JButton btnSaldo = new JButton("Ver Saldo");
        painelEntrada.add(btnSaldo);

        add(painelEntrada, BorderLayout.NORTH);

        // Área de transações
        areaTransacoes = new JTextArea();
        areaTransacoes.setEditable(false);
        add(new JScrollPane(areaTransacoes), BorderLayout.CENTER);

        // Rodapé com saldo
        lblSaldo = new JLabel("Saldo atual: R$ 0.00");
        add(lblSaldo, BorderLayout.SOUTH);

        // Evento adicionar transação
        btnAdicionar.addActionListener(e -> adicionarTransacao());

        // Evento ver saldo
        btnSaldo.addActionListener(e -> atualizarSaldo());

        setVisible(true);
    }

    private void adicionarTransacao() {
        try {
            String tipo = (String) tipoTransacao.getSelectedItem();
            String descricao = txtDescricao.getText();
            double valor = Double.parseDouble(txtValor.getText());

            if (valor <= 0 || descricao.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente.");
                return;
            }

            Transacao t;
            if (tipo.equals("Receita")) {
                t = new Receita(valor, descricao);
            } else {
                t = new Despesa(valor, descricao);
            }

            transacoes.add(t);
            areaTransacoes.append(String.format("%s: R$ %.2f - %s\n", t.getTipo(), t.getValor(), t.getDescricao()));

            // Limpar campos
            txtDescricao.setText("");
            txtValor.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Digite um valor numérico válido.");
        }
    }

    private void atualizarSaldo() {
        double saldo = 0.0;
        for (Transacao t : transacoes) {
            if (t instanceof Receita) {
                saldo += t.getValor();
            } else {
                saldo -= t.getValor();
            }
        }
        lblSaldo.setText(String.format("Saldo atual: R$ %.2f", saldo));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FinanceiroGUI());
    }
}
