public class JogoDaVelha {
    private int[][] tabuleiro;
    private int jogadorAtual;
    private int placarJogador1;
    private int placarJogador2;

    public JogoDaVelha() {
        reiniciarJogo();
    }

    public boolean realizarJogada(int linha, int coluna) {
        if (isPosicaoValida(linha, coluna)) {
            tabuleiro[linha][coluna] = jogadorAtual;
            return true;
        }
        return false;
    }

    public boolean verificarVitoria() {
        return verificarLinhas() || verificarColunas() || verificarDiagonais();
    }

    public boolean isEmpate() {
        return !verificarVitoria() && isTabuleiroCheio();
    }

    public void trocarJogador() {
        jogadorAtual = (jogadorAtual == 1) ? 2 : 1;
    }

    public void reiniciarJogo() {
        tabuleiro = new int[3][3];
        jogadorAtual = 1;
    }

    public int getPlacarJogador1() {
        return placarJogador1;
    }

    public int getPlacarJogador2() {
        return placarJogador2;
    }

    public void incrementarPlacar(int jogador) {
        if (jogador == 1) {
            placarJogador1++;
        } else if (jogador == 2) {
            placarJogador2++;
        }
    }

    public int getJogadorAtual() {
        return jogadorAtual;
    }

    private boolean isPosicaoValida(int linha, int coluna) {
        return linha >= 0 && linha < 3 && coluna >= 0 && coluna < 3 && tabuleiro[linha][coluna] == 0;
    }

    private boolean isTabuleiroCheio() {
        for (int[] linha : tabuleiro) {
            for (int celula : linha) {
                if (celula == 0) return false;
            }
        }
        return true;
    }

    private boolean verificarLinhas() {
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] == jogadorAtual && tabuleiro[i][1] == jogadorAtual && tabuleiro[i][2] == jogadorAtual) return true;
        }
        return false;
    }

    private boolean verificarColunas() {
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[0][i] == jogadorAtual && tabuleiro[1][i] == jogadorAtual && tabuleiro[2][i] == jogadorAtual) return true;
        }
        return false;
    }

    private boolean verificarDiagonais() {
        return (tabuleiro[0][0] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][2] == jogadorAtual) ||
                (tabuleiro[0][2] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][0] == jogadorAtual);
    }
}
