package com.unpri.charactermanager.model;

public class Character {
    private int id;
    private String name;
    private CharacterType type;
    private int level;
    private int hitPoints;
    private int attackPower;

    public Character(String name, CharacterType type, int level) {
        this.id = 0;
        this.name = name;
        this.type = type;
        this.level = level;
        this.hitPoints = 150;
        this.attackPower = 35;
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

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Character)) return false;
        Character other = (Character) obj;
        return this.name.equals(other.name);
    }

    @Override
    public String toString() {
        return "Character{id=" + id + ", name='" + name + "', type=" + type + ", level=" + level + ", hp=" + hitPoints + ", atk=" + attackPower + "}";
    }
}