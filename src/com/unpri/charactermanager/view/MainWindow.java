package com.unpri.charactermanager.view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;

public class MainWindow extends JFrame {

    private ArrayList<Character> characterList;

    public MainWindow(ArrayList<Character> characterList) {
        super("Character Manager v0.5");
        this.characterList = characterList;

        initComponents();

        pack();
        setMinimumSize(new Dimension(560, 520));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        getContentPane().setBackground(AppTheme.BG_MAIN);
        setLayout(new BorderLayout()); // Kerangka utama 4 wilayah

        add(createHeaderPanel(), BorderLayout.NORTH); // NORTH: Header + Statistik (GridLayout 2x2)
        add(createListArea(),    BorderLayout.CENTER); // CENTER: JScrollPane daftar karakter
        add(createLegendPanel(), BorderLayout.EAST);   // EAST: Legenda Role (BoxLayout Y_AXIS)
        add(createStatusPanel(), BorderLayout.SOUTH);  // SOUTH: Bar Status (Kiri & Kanan v0.5)
    }

    /** NORTH: Header Utama + Panel Statistik 2x2 */
    private JPanel createHeaderPanel() {
        JPanel headerOuter = new JPanel(new BorderLayout());
        headerOuter.setBackground(AppTheme.BG_MAIN);
        headerOuter.setBorder(BorderFactory.createEmptyBorder(16, 16, 8, 16));

        // Baris judul (WEST: Judul, EAST: 8 karakter)
        JPanel titleRow = new JPanel(new BorderLayout());
        titleRow.setBackground(AppTheme.BG_MAIN);

        JLabel title = new JLabel("Character Manager");
        title.setFont(AppTheme.FONT_TITLE);
        title.setForeground(AppTheme.TEXT_MAIN);

        JLabel count = new JLabel(characterList.size() + " karakter");
        count.setFont(AppTheme.FONT_BODY);
        count.setForeground(AppTheme.GOLD);

        titleRow.add(title, BorderLayout.WEST);
        titleRow.add(count, BorderLayout.EAST);

        // Kalkulasi Statistik
        int total = characterList.size();
        double totalLevel = 0;
        int maxLevel = 0;
        int highLevelCount = 0;

        for (Character c : characterList) {
            totalLevel += c.getLevel();
            if (c.getLevel() > maxLevel) maxLevel = c.getLevel();
            if (c.getLevel() >= AppTheme.HIGH_LEVEL_THRESHOLD) highLevelCount++;
        }
        double avgLevel = total > 0 ? totalLevel / total : 0;

        // Panel Statistik Grid 2 x 2
        JPanel statsGrid = new JPanel(new GridLayout(2, 2, 8, 8));
        statsGrid.setBackground(AppTheme.BG_MAIN);
        statsGrid.setBorder(BorderFactory.createEmptyBorder(12, 0, 0, 0));

        statsGrid.add(createStatCard("Total karakter", String.valueOf(total)));
        statsGrid.add(createStatCard("Rata-rata level", String.format("%.2f", avgLevel)));
        statsGrid.add(createStatCard("Level tertinggi", String.valueOf(maxLevel)));
        statsGrid.add(createStatCard("Level tinggi (>= 5)", String.valueOf(highLevelCount)));

        headerOuter.add(titleRow, BorderLayout.NORTH);
        headerOuter.add(statsGrid, BorderLayout.CENTER);
        return headerOuter;
    }

    private JPanel createStatCard(String labelText, String valueText) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(AppTheme.BG_STATUS);
        card.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));

        JLabel lblTitle = new JLabel(labelText);
        lblTitle.setFont(AppTheme.FONT_BODY.deriveFont(12f));
        lblTitle.setForeground(AppTheme.TEXT_MUTED);

        JLabel lblValue = new JLabel(valueText);
        lblValue.setFont(AppTheme.FONT_TITLE.deriveFont(20f));
        lblValue.setForeground(AppTheme.GOLD);

        card.add(lblTitle, BorderLayout.NORTH);
        card.add(lblValue, BorderLayout.CENTER);
        return card;
    }

    /** CENTER: Daftar Karakter Berscroll (JScrollPane -> holder -> cards NORTH) */
    private JScrollPane createListArea() {
        JPanel cards = new JPanel(new GridLayout(0, 1, 0, 8));
        cards.setBackground(AppTheme.BG_MAIN);
        for (Character c : characterList) {
            cards.add(createCharacterLabel(c));
        }

        JPanel holder = new JPanel(new BorderLayout());
        holder.setBackground(AppTheme.BG_MAIN);
        holder.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 8));
        holder.add(cards, BorderLayout.NORTH); // Mencegah kartu melar ke bawah

        JScrollPane scroll = new JScrollPane(holder);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(AppTheme.BG_MAIN);
        scroll.setPreferredSize(new Dimension(460, 280)); // Batasi ukuran viewport agar tidak raksasa
        return scroll;
    }

    private JLabel createCharacterLabel(Character c) {
        String info = c.getName() + " | " + c.getType()
                    + " | Lv." + c.getLevel()
                    + " | HP:" + c.getHitPoints()
                    + " | ATK:" + c.getAttackPower();

        JLabel lbl = new JLabel(info);
        lbl.setFont(AppTheme.FONT_BODY);
        lbl.setForeground(AppTheme.TEXT_LIGHT);
        lbl.setBackground(AppTheme.colorFor(c.getType()));
        lbl.setOpaque(true);
        lbl.setBorder(AppTheme.CARD_BORDER);
        lbl.setToolTipText(AppTheme.describe(c.getType()));
        lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return lbl;
    }

    /** EAST: Legenda Role (BoxLayout Y_AXIS, strut, glue) */
    private JPanel createLegendPanel() {
        JPanel legend = new JPanel();
        legend.setLayout(new BoxLayout(legend, BoxLayout.Y_AXIS));
        legend.setBackground(AppTheme.BG_MAIN);
        legend.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 16));

        JLabel head = new JLabel("Legenda Role");
        head.setFont(AppTheme.FONT_BODY.deriveFont(java.awt.Font.BOLD));
        head.setForeground(AppTheme.TEXT_MAIN);
        legend.add(head);
        legend.add(Box.createVerticalStrut(8)); // Strut jarak tetap

        for (CharacterType type : CharacterType.values()) {
            JLabel chip = new JLabel(type.name());
            chip.setOpaque(true);
            chip.setBackground(AppTheme.colorFor(type));
            chip.setForeground(AppTheme.TEXT_LIGHT);
            chip.setFont(AppTheme.FONT_MONO);
            chip.setBorder(AppTheme.CARD_BORDER);
            chip.setToolTipText(AppTheme.describe(type));
            legend.add(chip);
            legend.add(Box.createVerticalStrut(6));
        }
        legend.add(Box.createVerticalGlue()); // Dorong semua ke atas (glue)
        return legend;
    }

    /** SOUTH: Bar Status dengan Mini-Challenge (Kiri & Kanan) */
    private JPanel createStatusPanel() {
        JPanel status = new JPanel(new BorderLayout());
        status.setBackground(AppTheme.BG_STATUS);
        status.setBorder(BorderFactory.createEmptyBorder(6, 16, 6, 16));

        JLabel lblStatus = new JLabel("Total: " + characterList.size() + " karakter  |  Siap");
        lblStatus.setFont(AppTheme.FONT_MONO);
        lblStatus.setForeground(AppTheme.TEXT_MUTED);

        JLabel lblVersion = new JLabel("v0.5");
        lblVersion.setFont(AppTheme.FONT_MONO);
        lblVersion.setForeground(AppTheme.TEXT_MUTED);

        status.add(lblStatus, BorderLayout.WEST);
        status.add(lblVersion, BorderLayout.EAST);
        return status;
    }
}