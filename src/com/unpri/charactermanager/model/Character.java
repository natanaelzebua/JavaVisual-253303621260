package com.unpri.charactermanager.model;

public class Character {
    private String name;
    private CharacterType type;
    private int level;

    public Character(String name, CharacterType type, int level) {
        this.name = name;
        this.type = type;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public CharacterType getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    // Perhitungan HP dinamis agar nilainya bervariasi tiap karakter (misal: Arjuna 150, Merlin 130, dll)
    public int getHitPoints() {
        return 100 + (level * 10);
    }

    // Perhitungan Attack Power dinamis
    public int getAttackPower() {
        return 10 + (level * 5);
    }
}