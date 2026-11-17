package com.unpri.charactermanager.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import com.unpri.charactermanager.model.CharacterType;

/**
 * Pusat gaya tampilan Character Manager.
 * Mengubah tema aplikasi cukup dengan mengedit file ini.
 */
public final class AppTheme {

    // ---------- Palet warna ----------
    public static final Color BG_MAIN    = Color.decode("#13131f"); // latar jendela
    public static final Color TEXT_MAIN  = Color.decode("#dfe6e9"); // teks utama
    public static final Color TEXT_LIGHT = Color.WHITE;             // teks di atas kartu berwarna
    public static final Color GOLD       = Color.decode("#fdcb6e"); // aksen karakter level tinggi

    // ---------- Font ----------
    public static final Font FONT_TITLE     = new Font("SansSerif", Font.BOLD, 22);
    public static final Font FONT_BODY      = new Font("SansSerif", Font.PLAIN, 14);
    public static final Font FONT_BODY_BOLD = FONT_BODY.deriveFont(Font.BOLD);  // gaya tebal, ukuran tetap
    public static final Font FONT_MONO      = new Font("Monospaced", Font.PLAIN, 13);

    // ---------- Border (immutable, aman dipakai ulang) ----------
    public static final Border CARD_BORDER  = BorderFactory.createEmptyBorder(8, 12, 8, 12);
    public static final Border PANEL_BORDER = BorderFactory.createEmptyBorder(16, 16, 16, 16);

    // ---------- Aturan tampilan (urusan View, bukan aturan game) ----------
    public static final int HIGH_LEVEL_THRESHOLD = 5;  // level >= ini: teks emas dan tebal
    public static final int DISABLED_LEVEL = 1;        // level <= ini: kartu dinonaktifkan

    private AppTheme() { }  // class utilitas: cegah new AppTheme()

    /** Menerjemahkan role (data Model) menjadi warna kartu (urusan View). */
    public static Color colorFor(CharacterType type) {
        switch (type) {
            case WARRIOR: return Color.decode("#d63031"); // merah
            case MAGE:    return Color.decode("#0984e3"); // biru
            case ARCHER:  return Color.decode("#00b894"); // hijau
            case HEALER:  return Color.decode("#6c5ce7"); // ungu
            default:      return Color.GRAY;              // cadangan bila ada role baru
        }
    }

    /** Teks tooltip per role. */
    public static String describe(CharacterType type) {
        switch (type) {
            case WARRIOR: return "Warrior: jarak dekat, HP tinggi";
            case MAGE:    return "Mage: serangan sihir, HP rendah";
            case ARCHER:  return "Archer: jarak jauh, seimbang";
            case HEALER:  return "Healer: pemulih, mendukung tim";
            default:      return "Role belum terdaftar";
        }
    }
}