import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal {
    private JFrame frame;
    private JButton[][] buttons;
    private JogoDaVelha jogo;
    private JLabel placarLabel;
    private JLabel infoLabel;

    public Principal() {
        jogo = new JogoDaVelha();

        frame = new JFrame("Jogo da Velha - GitHub: yp3droy | LinkedIn: linkedin.com/in/ph-dev/");
        frame.setLayout(new BorderLayout());
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon(getClass().getResource("/resources/logo.jpg")).getImage());
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.BLACK);

        JPanel painelTabuleiro = new JPanel(new GridLayout(3, 3));
        painelTabuleiro.setBackground(Color.BLACK);
        buttons = new JButton[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 100));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].setForeground(Color.BLACK);
                buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY, 4));
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                painelTabuleiro.add(buttons[i][j]);
            }
        }

        JPanel painelSuperior = new JPanel(new BorderLayout());
        painelSuperior.setBackground(Color.BLACK);

        placarLabel = new JLabel();
        placarLabel.setFont(new Font("Arial", Font.BOLD, 20));
        placarLabel.setForeground(Color.WHITE);
        placarLabel.setHorizontalAlignment(SwingConstants.CENTER);
        atualizarPlacar();

        JButton botaoJogarNovamente = new JButton("Jogar Novamente");
        botaoJogarNovamente.setFont(new Font("Arial", Font.BOLD, 20));
        botaoJogarNovamente.setBackground(Color.GRAY);
        botaoJogarNovamente.setForeground(Color.WHITE);
        botaoJogarNovamente.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        botaoJogarNovamente.addActionListener(e -> resetarJogo());

        infoLabel = new JLabel("Pedro Oliveira | phenriquecandidooliveira@gmail.com");
        infoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        infoLabel.setForeground(Color.WHITE);
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel painelPlacar = new JPanel(new BorderLayout());
        painelPlacar.setBackground(Color.BLACK);
        painelPlacar.add(placarLabel, BorderLayout.CENTER);
        painelPlacar.add(botaoJogarNovamente, BorderLayout.SOUTH);

        painelSuperior.add(painelPlacar, BorderLayout.CENTER);
        painelSuperior.add(infoLabel, BorderLayout.SOUTH);

        frame.add(painelTabuleiro, BorderLayout.CENTER);
        frame.add(painelSuperior, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        private int linha;
        private int coluna;

        public ButtonClickListener(int linha, int coluna) {
            this.linha = linha;
            this.coluna = coluna;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (jogo.realizarJogada(linha, coluna)) {
                buttons[linha][coluna].setText(jogo.getJogadorAtual() == 1 ? "X" : "O");
                buttons[linha][coluna].setForeground(jogo.getJogadorAtual() == 1 ? Color.RED : Color.BLUE);

                if (jogo.verificarVitoria()) {
                    JOptionPane.showMessageDialog(frame, "Parabéns, o jogador " + (jogo.getJogadorAtual() == 1 ? "1" : "2") + " venceu!");
                    jogo.incrementarPlacar(jogo.getJogadorAtual());
                    atualizarPlacar();
                    resetarJogo();
                } else if (jogo.isEmpate()) {
                    JOptionPane.showMessageDialog(frame, "Velha!! O jogo terminou empatado.");
                    resetarJogo();
                } else {
                    jogo.trocarJogador();
                }
            }
        }
    }

    private void atualizarPlacar() {
        placarLabel.setText("Placar - Jogador x: " + jogo.getPlacarJogador1() + " | Jogador 2: " + jogo.getPlacarJogador2());
    }

    private void resetarJogo() {
        jogo.reiniciarJogo();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setForeground(Color.BLACK);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Principal::new);
    }
}
