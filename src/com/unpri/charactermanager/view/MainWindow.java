package com.unpri.charactermanager.view;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.unpri.charactermanager.model.Character;

public class MainWindow extends JFrame {

    private JPanel mainPanel;                      // container utama
    private ArrayList<Character> characterList;    // data dari Main.java (Model)

    public MainWindow(ArrayList<Character> characterList) {
        super("Character Manager v0.4");
        this.characterList = characterList;

        initComponents();                          // 1. susun semua komponen dulu
        pack();                                    // 2. ukuran jendela mengikuti isi
        setMinimumSize(new Dimension(420, 300));   // 3. cegah jendela terlalu kecil
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);               // 4. pusatkan SETELAH pack()
    }

    private void initComponents() {
        mainPanel = new JPanel(new GridLayout(0, 1, 0, 8));
        mainPanel.setBackground(AppTheme.BG_MAIN);
        mainPanel.setBorder(AppTheme.PANEL_BORDER);

        mainPanel.add(createTitleLabel());
        for (Character c : characterList) {
            mainPanel.add(createCharacterLabel(c));
        }
        mainPanel.add(createAverageLabel());

        add(mainPanel);
    }

    /** Membuat judul layar. */
    private JLabel createTitleLabel() {
        JLabel lbl = new JLabel("Daftar Karakter (" + characterList.size() + ")",
                                SwingConstants.CENTER);
        lbl.setFont(AppTheme.FONT_TITLE);
        lbl.setForeground(AppTheme.TEXT_MAIN);
        return lbl;
    }

    /** Membuat satu kartu karakter (JLabel berwarna sesuai role). */
    private JLabel createCharacterLabel(Character c) {
        String info = c.getName() + " | " + c.getType()
                    + " | Lv." + c.getLevel()
                    + " | HP:" + c.getHitPoints()
                    + " | ATK:" + c.getAttackPower();   // hanya MEMBACA lewat getter

        JLabel lbl = new JLabel(info);
        lbl.setBackground(AppTheme.colorFor(c.getType()));   // warna dari role
        lbl.setOpaque(true);                                 // WAJIB agar background tampak
        lbl.setBorder(AppTheme.CARD_BORDER);

        // Mini-Challenge: level tinggi tampil emas dan tebal
        if (c.getLevel() >= AppTheme.HIGH_LEVEL_THRESHOLD) {
            lbl.setForeground(AppTheme.GOLD);
            lbl.setFont(AppTheme.FONT_BODY_BOLD);
        } else {
            lbl.setForeground(AppTheme.TEXT_LIGHT);
            lbl.setFont(AppTheme.FONT_BODY);
        }

        // Properti tambahan 1: tooltip berisi deskripsi role dan detail HP/ATK
        lbl.setToolTipText(AppTheme.describe(c.getType())
                + " | HP " + c.getHitPoints() + ", ATK " + c.getAttackPower());

        // Properti tambahan 2: karakter level terendah dinonaktifkan
        if (c.getLevel() <= AppTheme.DISABLED_LEVEL) {
            lbl.setEnabled(false);
        } else {
            lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        return lbl;
    }

    /** Statistik dari Sprint #3: rata-rata level. */
    private JLabel createAverageLabel() {
        JLabel lbl = new JLabel("Rata-rata Level: " + String.format("%.2f", calculateAverageLevel()),
                                SwingConstants.CENTER);
        lbl.setFont(AppTheme.FONT_MONO);
        lbl.setForeground(AppTheme.TEXT_MAIN);
        return lbl;
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