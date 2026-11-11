package com.unpri.charactermanager.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.util.ArrayList;
import com.unpri.charactermanager.model.Character;

/**
 * Jendela utama aplikasi Character Manager - Sprint #3.
 */
public class MainWindow extends JFrame {

    private JPanel mainPanel;
    private ArrayList<Character> characterList;
    private double averageLevel;

    public MainWindow(ArrayList<Character> characterList, double averageLevel) {
        super("Character Manager v0.3");
        this.characterList = characterList;
        this.averageLevel = averageLevel;

        initComponents();

        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        StringBuilder sb = new StringBuilder();
        // Ukuran font sedikit lebih besar dan rata kiri, tanpa padding berlebih
        sb.append("<html><div style='font-family: Arial; font-size: 13pt; text-align: left; padding: 20px;'>");
        
        // Judul (posisi normal seperti semula)
        sb.append("=== Daftar Karakter ===<br><br>");

        // Looping setiap karakter dengan tambahan jarak (<br><br>) di setiap akhir baris karakter
        for (Character c : characterList) {
            sb.append(c.getName()).append(" (")
              .append(c.getType()).append(") - Level ")
              .append(c.getLevel()).append(" | HP: ")
              .append(c.getHitPoints()).append(" | ATK: ")
              .append(c.getAttackPower()).append("<br><br>"); // Diberi jarak antar data karakter
        }

        // Statistik rata-rata level di bagian bawah (posisi normal seperti semula)
        sb.append("Rata-rata Level: ").append(String.format("%.2f", averageLevel));
        sb.append("</div></html>");

        JLabel contentLabel = new JLabel(sb.toString());
        mainPanel.add(contentLabel, BorderLayout.NORTH);

        add(mainPanel);
    }
}