import javax.swing.JTextField;

public class Placar {
    private JTextField nomeJogador1;
    private JTextField nomeJogador2;

    public Placar() {
        nomeJogador1 = new JTextField("Jogador 1");
        nomeJogador2 = new JTextField("Jogador 2");
    }

    public String getNomeJogador1() {
        return nomeJogador1.getText();
    }

    public String getNomeJogador2() {
        return nomeJogador2.getText();
    }

    public void setNomeJogador1(String nome) {
        nomeJogador1.setText(nome);
    }

    public void setNomeJogador2(String nome) {
        nomeJogador2.setText(nome);
    }
}
