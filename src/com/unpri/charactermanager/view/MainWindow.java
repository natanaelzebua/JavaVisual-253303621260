package com.unpri.charactermanager.view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;

public class MainWindow extends JFrame {

    private static final String VERSION = "v0.5"; // satu sumber untuk judul dan label versi

    private ArrayList<Character> characterList; // data dari Main.java (Model)

    public MainWindow(ArrayList<Character> characterList) {
        super("Character Manager " + VERSION);
        this.characterList = characterList;

        initComponents(); // 1. susun semua area
        pack(); // 2. ukuran awal = preferred size isi
        setMinimumSize(new Dimension(560, 380)); // 3. batas terkecil yang rapi
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 4. pusatkan SETELAH pack()
    }

    private void initComponents() {
        getContentPane().setBackground(AppTheme.BG_MAIN); // cegah celah abu-abu
        setLayout(new BorderLayout());

        add(createTopPanel(), BorderLayout.NORTH); // header + statistik
        add(createListArea(), BorderLayout.CENTER); // satu-satunya yang tumbuh
        add(createLegendPanel(), BorderLayout.EAST);
        add(createStatusPanel(), BorderLayout.SOUTH);
    }

    /** Header (NORTH): judul di kiri, jumlah karakter di kanan. */
    private JPanel createHeaderPanel() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(AppTheme.BG_MAIN);
        header.setBorder(AppTheme.HEADER_BORDER);

        JLabel title = new JLabel("Character Manager");
        title.setFont(AppTheme.FONT_TITLE);
        title.setForeground(AppTheme.TEXT_MAIN);

        JLabel count = new JLabel(characterList.size() + " karakter");
        count.setFont(AppTheme.FONT_BODY);
        count.setForeground(AppTheme.GOLD);

        header.add(title, BorderLayout.WEST);
        header.add(count, BorderLayout.EAST);
        return header;
    }

    /**
     * Daftar berscroll (CENTER): kartu -> panel cards -> holder (NORTH) ->
     * JScrollPane.
     */
    private JScrollPane createListArea() {
        JPanel cards = new JPanel(new GridLayout(0, 1, 0, 8));
        cards.setBackground(AppTheme.BG_MAIN);
        for (Character c : characterList) {
            cards.add(createCharacterLabel(c)); // ke PANEL, bukan ke scroll pane
        }

        JPanel holder = new JPanel(new BorderLayout());
        holder.setBackground(AppTheme.BG_MAIN);
        holder.setBorder(AppTheme.LIST_BORDER);
        holder.add(cards, BorderLayout.NORTH); // kartu mempertahankan tinggi alami

        JScrollPane scroll = new JScrollPane(holder); // bungkus HOLDER
        scroll.setBorder(null);
        scroll.getViewport().setBackground(AppTheme.BG_MAIN);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setPreferredSize(new Dimension(460, 280)); // cegah jendela raksasa
        return scroll;
    }

    /** Satu kartu karakter (sama seperti v0.4). */
    private JLabel createCharacterLabel(Character c) {
        String info = c.getName() + " | " + c.getType()
                + " | Lv." + c.getLevel()
                + " | HP:" + c.getHitPoints()
                + " | ATK:" + c.getAttackPower();

        JLabel lbl = new JLabel(info);
        lbl.setBackground(AppTheme.colorFor(c.getType()));
        lbl.setOpaque(true);
        lbl.setBorder(AppTheme.CARD_BORDER);

        if (c.getLevel() >= AppTheme.HIGH_LEVEL_THRESHOLD) {
            lbl.setForeground(AppTheme.GOLD);
            lbl.setFont(AppTheme.FONT_BODY_BOLD);
        } else {
            lbl.setForeground(AppTheme.TEXT_LIGHT);
            lbl.setFont(AppTheme.FONT_BODY);
        }

        lbl.setToolTipText(AppTheme.describe(c.getType())
                + " | HP " + c.getHitPoints() + ", ATK " + c.getAttackPower());

        if (c.getLevel() <= AppTheme.DISABLED_LEVEL) {
            lbl.setEnabled(false);
        } else {
            lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        return lbl;
    }
        /** Legenda role (EAST): BoxLayout vertikal, diturunkan dari CharacterType.values(). */
    private JPanel createLegendPanel() {
        JPanel legend = new JPanel();
        legend.setLayout(new BoxLayout(legend, BoxLayout.Y_AXIS));   // target = panel ini sendiri
        legend.setBackground(AppTheme.BG_MAIN);
        legend.setBorder(AppTheme.LEGEND_BORDER);

        JLabel head = new JLabel("Legenda Role");
        head.setFont(AppTheme.FONT_BODY_BOLD);
        head.setForeground(AppTheme.TEXT_MAIN);
        legend.add(head);
        legend.add(Box.createVerticalStrut(8));                      // spasi tetap 8 px

        for (CharacterType type : CharacterType.values()) {          // satu chip per role
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
        legend.add(Box.createVerticalGlue());                        // dorong semua ke atas
        return legend;
    }

    /** Bar status (SOUTH): teks status di kiri, versi di kanan. */
    private JPanel createStatusPanel() {
        JPanel status = new JPanel(new BorderLayout());
        status.setBackground(AppTheme.BG_STATUS);
        status.setBorder(AppTheme.STATUS_BORDER);

        JLabel info = new JLabel("Total: " + characterList.size() + " karakter | Siap");
        info.setFont(AppTheme.FONT_MONO);
        info.setForeground(AppTheme.TEXT_MUTED);

        JLabel version = new JLabel(VERSION);
        version.setFont(AppTheme.FONT_MONO);
        version.setForeground(AppTheme.TEXT_MUTED);

        status.add(info, BorderLayout.WEST);
        status.add(version, BorderLayout.EAST);
        return status;
    }

    /** Pembungkus NORTH: header di atas, panel statistik di bawahnya. */
    private JPanel createTopPanel() {
        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(AppTheme.BG_MAIN);
        top.add(createHeaderPanel(), BorderLayout.NORTH);
        top.add(createStatsPanel(), BorderLayout.CENTER);
        return top;
    }

    /** Panel statistik: GridLayout 2x2, empat sel berukuran sama. */
    private JPanel createStatsPanel() {
        JPanel stats = new JPanel(new GridLayout(2, 2, 8, 8));
        stats.setBackground(AppTheme.BG_MAIN);
        stats.setBorder(AppTheme.STATS_BORDER);

        stats.add(createStatCell("Total karakter", String.valueOf(characterList.size())));
        stats.add(createStatCell("Rata-rata level", String.format("%.2f", calculateAverageLevel())));
        stats.add(createStatCell("Level tertinggi", String.valueOf(findHighestLevel())));
        stats.add(createStatCell("Level tinggi (>= " + AppTheme.HIGH_LEVEL_THRESHOLD + ")",
                String.valueOf(countHighLevel())));
        return stats;
    }

    /** Satu sel statistik: keterangan di atas, angka besar di bawah. */
    private JPanel createStatCell(String caption, String value) {
        JPanel cell = new JPanel(new BorderLayout());
        cell.setBackground(AppTheme.BG_PANEL);
        cell.setBorder(AppTheme.CARD_BORDER);

        JLabel cap = new JLabel(caption);
        cap.setFont(AppTheme.FONT_BODY);
        cap.setForeground(AppTheme.TEXT_MUTED);

        JLabel val = new JLabel(value);
        val.setFont(AppTheme.FONT_TITLE);
        val.setForeground(AppTheme.GOLD);

        cell.add(cap, BorderLayout.NORTH);
        cell.add(val, BorderLayout.CENTER);
        return cell;
    }

    private double calculateAverageLevel() {
        if (characterList.isEmpty()) {
            return 0; // hindari pembagian dengan nol
        }
        int sum = 0;
        for (Character c : characterList) {
            sum += c.getLevel();
        }
        return (double) sum / characterList.size();
    }

    private int findHighestLevel() {
        int max = 0;
        for (Character c : characterList) {
            if (c.getLevel() > max) {
                max = c.getLevel();
            }
        }
        return max;
    }

    private int countHighLevel() {
        int count = 0;
        for (Character c : characterList) {
            if (c.getLevel() >= AppTheme.HIGH_LEVEL_THRESHOLD) {
                count++;
            }
        }
        return count;
    }
}