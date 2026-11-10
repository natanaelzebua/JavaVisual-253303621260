import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;
import com.unpri.charactermanager.model.CharacterManager;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== Character Manager v0.2 — CRUD Test & Mini-Challenge ===\n");

        CharacterManager manager = new CharacterManager();

        manager.addCharacter(new Character("Arjuna", CharacterType.WARRIOR, 5));
        manager.addCharacter(new Character("Merlin", CharacterType.MAGE, 3));
        manager.addCharacter(new Character("Athena", CharacterType.ARCHER, 4));
        manager.addCharacter(new Character("Gatotkaca", CharacterType.WARRIOR, 6));

        System.out.println();
        manager.addCharacter(new Character("Arjuna", CharacterType.HEALER, 1));

        System.out.println("\nTotal karakter saat ini: " + manager.getTotalCharacters());
        System.out.println("📊 Rata-rata Level Karakter: " + manager.getAverageLevel());

        System.out.println("\n--- Pencarian Berdasarkan Tipe: WARRIOR ---");
        ArrayList<Character> warriors = manager.findByType(CharacterType.WARRIOR);
        for (Character w : warriors) {
            System.out.println(w);
        }

        System.out.println("\n--- Penghapusan ---");
        manager.removeCharacter("Athena");
        System.out.println("Total setelah hapus: " + manager.getTotalCharacters());

        System.out.println("\n--- Daftar Karakter Tersisa ---");
        for (Character c : manager.getAllCharacters()) {
            System.out.println(c);
        }
    }
}