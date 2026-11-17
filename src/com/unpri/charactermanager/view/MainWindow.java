package com.unpri.charactermanager.view;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.ArrayList;
import com.unpri.charactermanager.model.Character;

/**
 * Jendela utama aplikasi Character Manager.
 * Menampilkan daftar karakter yang diterima dari luar (bukan dibuat sendiri).
 */
public class MainWindow extends JFrame {

    private JPanel mainPanel;
    private ArrayList<Character> characterList;   // data yang akan ditampilkan

    public MainWindow(ArrayList<Character> characterList) {
        super("Character Manager v0.3");           // judul title bar
        this.characterList = characterList;        // simpan data yang diterima dari Main.java

        initComponents();

        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        mainPanel.add(new JLabel("=== Daftar Karakter ==="));

        // DINAMIS: satu JLabel dibuat untuk setiap karakter lewat loop
        for (Character c : characterList) {
            JLabel label = new JLabel(c.getName() + " (" + c.getType() + ") - Level " + c.getLevel()
                    + " | HP: " + c.getHitPoints() + " | ATK: " + c.getAttackPower());
            label.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
            mainPanel.add(label);
        }

        // MINI-CHALLENGE: statistik rata-rata level di bawah daftar
        JLabel averageLabel = new JLabel("Rata-rata Level: " + String.format("%.2f", calculateAverageLevel()));
        averageLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        mainPanel.add(averageLabel);

        add(mainPanel);
    }

    private double calculateAverageLevel() {
        if (characterList.isEmpty()) {
            return 0;   // hindari pembagian dengan nol
        }
        int sum = 0;
        for (Character c : characterList) {
            sum += c.getLevel();
        }
        return (double) sum / characterList.size();
    }
}