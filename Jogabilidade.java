import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Jogabilidade extends JFrame implements ActionListener {

    private Hero heroi;
    private Villain vilao;

    // Componentes da UI
    private JLabel lblNomeHeroi, lblHPHeroi, lblAtaqueHeroi;
    private JLabel lblNomeVilao, lblHPVilao, lblAtaqueVilao;
    private JTextArea areaLog;
    private JButton btnAtacarHeroi, btnAtacarVilao, btnResetar;

    public Jogabilidade(Hero heroi, Villain vilao) {
        this.heroi = heroi;
        this.vilao = vilao;

        setTitle("Jogo de Turno");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // Layout principal com espaçamento

        // **ADICIONE ESTA LINHA AQUI:**
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // --- Restante do seu código do construtor... ---

        // Painel de Informações dos Jogadores
        JPanel painelJogadores = new JPanel(new GridLayout(1, 2, 20, 0));
        painelJogadores.setBorder(BorderFactory.createTitledBorder("Informações dos Combatentes"));

        // Painel do Herói
        JPanel painelHeroi = new JPanel(new GridLayout(3, 1));
        painelHeroi.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        lblNomeHeroi = new JLabel("Herói: " + heroi.getNome());
        lblHPHeroi = new JLabel("HP: " + heroi.getHP());
        lblAtaqueHeroi = new JLabel("Ataque: " + heroi.getAtackForce());
        painelHeroi.add(lblNomeHeroi);
        painelHeroi.add(lblHPHeroi);
        painelHeroi.add(lblAtaqueHeroi);
        painelJogadores.add(painelHeroi);

        // Painel do Vilão
        JPanel painelVilao = new JPanel(new GridLayout(3, 1));
        painelVilao.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        lblNomeVilao = new JLabel("Vilão: " + vilao.getNome());
        lblHPVilao = new JLabel("HP: " + vilao.getHP());
        lblAtaqueVilao = new JLabel("Ataque: " + vilao.getAtackForce());
        painelVilao.add(lblNomeVilao);
        painelVilao.add(lblHPVilao);
        painelVilao.add(lblAtaqueVilao);
        painelJogadores.add(painelVilao);

        add(painelJogadores, BorderLayout.NORTH);

        // --- Área de Log do Jogo ---
        areaLog = new JTextArea();
        areaLog.setEditable(false);
        areaLog.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollLog = new JScrollPane(areaLog);
        scrollLog.setBorder(BorderFactory.createTitledBorder("Log do Jogo"));
        add(scrollLog, BorderLayout.CENTER);

        // --- Painel de Ações ---
        JPanel painelAcoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnAtacarHeroi = new JButton("Herói Ataca Vilão");
        btnAtacarHeroi.addActionListener(this);
        btnAtacarVilao = new JButton("Vilão Ataca Herói");
        btnAtacarVilao.addActionListener(this);
        btnResetar = new JButton("Resetar Jogo");
        btnResetar.addActionListener(this);

        painelAcoes.add(btnAtacarHeroi);
        painelAcoes.add(btnAtacarVilao);
        painelAcoes.add(btnResetar);
        add(painelAcoes, BorderLayout.SOUTH);

        atualizarStatusJogadores();
        log("Jogo iniciado! " + heroi.getNome() + " vs " + vilao.getNome() + ".");
    }

    // ... (restante dos métodos inalterados: atualizarStatusJogadores, log, realizarAtaque, actionPerformed) ...
    private void atualizarStatusJogadores() {
        lblHPHeroi.setText("HP: " + heroi.getHP());
        lblHPVilao.setText("HP: " + vilao.getHP());

        // Desabilitar botões se um jogador estiver sem HP
        if (heroi.getHP() <= 0) {
            btnAtacarVilao.setEnabled(false);
            log(heroi.getNome() + " foi derrotado!");
        }
        if (vilao.getHP() <= 0) {
            btnAtacarHeroi.setEnabled(false);
            log(vilao.getNome() + " foi derrotado!");
        }
        if (heroi.getHP() <= 0 || vilao.getHP() <= 0) {
            log("Fim de jogo!");
        }
    }

    // Método para adicionar mensagens ao log
    private void log(String mensagem) {
        areaLog.append(mensagem + "\n");
        areaLog.setCaretPosition(areaLog.getDocument().getLength()); // Rola para o final
    }

    // Lógica do combate (simplificada para o exemplo da GUI)
    private void realizarAtaque(Player atacante, Player defensor) {
        if (atacante.getHP() <= 0 || defensor.getHP() <= 0) {
            log("Um dos combatentes já está derrotado. Não é possível atacar.");
            return;
        }

        Random rand = new Random();
        double danoBase = atacante.getAtackForce();
        TiposDano tipoDano = TiposDano.NORMAL;

        // 20% de chance de dano crítico
        if (rand.nextDouble() < 0.2) {
            tipoDano = TiposDano.CRITICO;
        }

        double danoCalculado = tipoDano.calcularDano(danoBase);
        // Aplica uma simplificação de defesa (ex: defesa reduz 10% do dano)
        danoCalculado -= (defensor.getDefesa() * 0.1);
        if (danoCalculado < 0) danoCalculado = 0; // Dano não pode ser negativo

        defensor.receberDano(danoCalculado);

        log(atacante.getNome() + " atacou " + defensor.getNome() + " com um dano " + tipoDano.getDanoFormatado() + " de " + String.format("%.2f", danoCalculado) + "!");
        atualizarStatusJogadores();
    }

    // Lida com os eventos dos botões
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAtacarHeroi) {
            realizarAtaque(heroi, vilao);
        } else if (e.getSource() == btnAtacarVilao) {
            realizarAtaque(vilao, heroi);
        } else if (e.getSource() == btnResetar) {
            // Recria os jogadores para resetar o HP
            heroi = new Hero("Mano Brown", 100, 10, 20, 10, 35, 40);
            vilao = new Villain("Capitão Vilão", 80, 12, 15, 8, 30, 30);
            areaLog.setText(""); // Limpa o log
            btnAtacarHeroi.setEnabled(true);
            btnAtacarVilao.setEnabled(true);
            atualizarStatusJogadores();
            log("Jogo resetado! " + heroi.getNome() + " vs " + vilao.getNome() + ".");
        }
    }
}