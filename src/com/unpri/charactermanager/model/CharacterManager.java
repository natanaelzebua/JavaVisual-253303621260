package com.unpri.charactermanager.model;

import java.util.ArrayList;

/**
 * Mengelola koleksi Character: tambah, cari, hapus, dan statistik dasar.
 * Menerapkan Composition: CharacterManager "has-a" ArrayList<Character>.
 */
public class CharacterManager {
    private ArrayList<Character> characterList;

    public CharacterManager() {
        this.characterList = new ArrayList<>();
    }

    /**
     * Menambahkan karakter baru ke dalam list.
     * Menolak jika sudah ada karakter dengan nama yang sama.
     * @return true jika berhasil ditambahkan, false jika ditolak karena duplikat
     */
    public boolean addCharacter(Character character) {
        if (findByName(character.getName()) != null) {
            System.out.println("❌ Gagal: karakter dengan nama '" + character.getName() + "' sudah ada!");
            return false;
        }
        characterList.add(character);
        System.out.println("✅ Karakter '" + character.getName() + "' berhasil ditambahkan.");
        return true;
    }

    /**
     * Mencari karakter berdasarkan nama.
     * @return object Character jika ditemukan, null jika tidak ada
     */
    public Character findByName(String name) {
        for (Character c : characterList) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Menghapus karakter dari list berdasarkan nama.
     * @return true jika berhasil dihapus, false jika tidak ditemukan
     */
    public boolean removeCharacter(String name) {
        Character target = findByName(name);
        if (target == null) {
            System.out.println("❌ Gagal hapus: karakter '" + name + "' tidak ditemukan.");
            return false;
        }
        characterList.remove(target);
        System.out.println("🗑️ Karakter '" + name + "' berhasil dihapus.");
        return true;
    }

    public int getTotalCharacters() {
        return characterList.size();
    }

    /**
     * Mengembalikan SALINAN list, bukan referensi asli,
     * agar pemanggil tidak bisa mengubah data internal manager secara langsung.
     */
    public ArrayList<Character> getAllCharacters() {
        return new ArrayList<>(characterList);
    }

    // ===== BAGIAN B: Mini-Challenge =====

    /**
     * Menghitung rata-rata level seluruh karakter.
     * @return rata-rata level, atau 0 jika list kosong (menghindari pembagian dengan nol)
     */
    public double getAverageLevel() {
        if (characterList.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (Character c : characterList) {
            sum += c.getLevel();
        }
        return (double) sum / characterList.size();
    }

    /**
     * Mengembalikan semua karakter dengan tipe tertentu.
     * @return list baru berisi karakter bertipe tersebut (kosong jika tidak ada)
     */
    public ArrayList<Character> findByType(CharacterType type) {
        ArrayList<Character> result = new ArrayList<>();
        for (Character c : characterList) {
            if (c.getType() == type) {
                result.add(c);
            }
        }
        return result;
    }

    // ===== BAGIAN C: Fitur rancangan sendiri =====

    /**
     * Membuat ringkasan jumlah karakter untuk setiap tipe.
     * Mengembalikan String (bukan list), sehingga ArrayList internal
     * tidak terekspos ke pemanggil.
     *
     * @return ringkasan berbentuk "WARRIOR: 1  MAGE: 1  ARCHER: 0  HEALER: 0"
     */
    public String getTypeSummary() {
        StringBuilder sb = new StringBuilder();
        for (CharacterType type : CharacterType.values()) {
            int count = 0;
            for (Character c : characterList) {
                if (c.getType() == type) {
                    count++;
                }
            }
            sb.append(type).append(": ").append(count).append("  ");
        }
        return sb.toString().trim();
    }
}