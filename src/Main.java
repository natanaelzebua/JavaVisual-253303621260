import com.unpri.charactermanager.model.Character;       // import class Character dari package model
import com.unpri.charactermanager.model.CharacterType;   // import enum CharacterType

public class Main {
    public static void main(String[] args) {              // method main: titik awal eksekusi program

        System.out.println("=== Character Manager v0.1 ===");

        // Membuat 3 object Character menggunakan constructor pertama
        Character hero = new Character("Arjuna", CharacterType.WARRIOR, 5);
        Character wizard = new Character("Merlin", CharacterType.MAGE, 3);
        Character scout = new Character("Athena", CharacterType.ARCHER, 4);

        // Menampilkan ketiganya menggunakan toString()
        System.out.println(hero);
        System.out.println(wizard);
        System.out.println(scout);

        System.out.println();
        System.out.println(">> " + hero.getName() + " level up!");
        hero.setLevel(hero.getLevel() + 1);     // naik 1 level, HP & Attack dihitung ulang otomatis
        System.out.println("After level up: " + hero);

        System.out.println();
        System.out.println(">> Nama karakter: " + hero.getName());
        System.out.println(">> Jenis karakter: " + hero.getType());
        System.out.println(">> HP saat ini: " + hero.getHitPoints());
    }
}