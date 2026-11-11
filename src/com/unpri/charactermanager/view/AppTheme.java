package com.unpri.charactermanager.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import com.unpri.charactermanager.model.CharacterType;

public final class AppTheme {

    public static final int HIGH_LEVEL_THRESHOLD = 5;

    // ---------- Palet Warna ----------
    public static final Color BG_MAIN    = Color.decode("#13131f");
    public static final Color TEXT_MAIN  = Color.decode("#dfe6e9");
    public static final Color TEXT_LIGHT = Color.WHITE;
    public static final Color GOLD       = Color.decode("#fdcb6e");

    // ---------- Font ----------
    public static final Font FONT_TITLE = new Font("SansSerif", Font.BOLD, 22);
    public static final Font FONT_BODY  = new Font("SansSerif", Font.PLAIN, 14);
    public static final Font FONT_MONO  = new Font("Monospaced", Font.PLAIN, 13);

    // ---------- Border ----------
    public static final Border CARD_BORDER = BorderFactory.createEmptyBorder(8, 12, 8, 12);

    private AppTheme() { }

    public static Color colorFor(CharacterType type) {
        switch (type) {
            case WARRIOR: return Color.decode("#d63031"); // merah
            case MAGE:    return Color.decode("#0984e3"); // biru
            case ARCHER:  return Color.decode("#00b894"); // hijau
            case HEALER:  return Color.decode("#6c5ce7"); // ungu
            default:      return Color.GRAY;
        }
    }

    public static String describe(CharacterType type) {
        switch (type) {
            case WARRIOR: return "Warrior: jarak dekat, HP tinggi";
            case MAGE:    return "Mage: serangan sihir, HP rendah";
            case ARCHER:  return "Archer: jarak jauh, seimbang";
            case HEALER:  return "Healer: pemulih, support tim";
            default:      return "Role belum terdaftar";
        }
    }
}