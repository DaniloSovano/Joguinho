import javax.swing.SwingUtilities;

public class MainJogo {

    public static void main(String[] args) {
        // Instancia os personagens do jogo
        Hero heroi = new Hero("Mano Brown", 100, 10, 20, 10, 35, 40);
        Villain vilao = new Villain("Capitão Vilão", 80, 12, 15, 8, 30, 30);

        // Inicia a interface gráfica na Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            Jogabilidade jogoGUI = new Jogabilidade(heroi, vilao);
            jogoGUI.setVisible(true);
        });
    }
}