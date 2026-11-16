import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Character Manager v0.2 ===");
        CharacterManager manager = new CharacterManager();

        System.out.println("\n--- Tambah karakter ---");
        manager.addCharacter(new Character("Arjuna", CharacterType.WARRIOR, 5));
        manager.addCharacter(new Character("Merlin", CharacterType.MAGE, 3));
        manager.addCharacter(new Character("Athena", CharacterType.ARCHER, 4));
        System.out.println("Total karakter: " + manager.getTotalCharacters());

        System.out.println("\n--- Tolak duplikat ---");
        manager.addCharacter(new Character("Arjuna", CharacterType.MAGE, 1));
        System.out.println("Total karakter: " + manager.getTotalCharacters());
        System.out.println("Total objek Character dibuat: " + Character.getTotalCharacterCreated());

        System.out.println("\n--- Cari dan hapus ---");
        System.out.println("Cari Merlin: " + manager.findByName("Merlin"));
        System.out.println("Cari Zeus: " + manager.findByName("Zeus"));
        manager.removeCharacter("Athena");
        manager.removeCharacter("Zeus");
        System.out.println("Total karakter: " + manager.getTotalCharacters());

        System.out.println("\n--- Mini-Challenge ---");
        System.out.println("Rata-rata level (manager kosong): " + new CharacterManager().getAverageLevel());
        System.out.println("Rata-rata level: " + manager.getAverageLevel());
        ArrayList<Character> mages = manager.findByType(CharacterType.MAGE);
        System.out.println("Jumlah MAGE: " + mages.size());
        for (Character c : mages) {
            System.out.println(" - " + c.getName());
        }
        System.out.println("Jumlah HEALER: " + manager.findByType(CharacterType.HEALER).size());

        System.out.println("\n--- Fitur Analitik (getTypeSummary) ---");
        System.out.println("Ringkasan tipe: " + manager.getTypeSummary());
        System.out.println("Ringkasan tipe (manager kosong): " + new CharacterManager().getTypeSummary());

        // ===== EKSPERIMEN: Object Reference =====
        System.out.println("\n--- Eksperimen Reference ---");
        Character asli = manager.findByName("Arjuna");
        Character duplikatReferensi = asli;
        System.out.println("Level Arjuna sebelum: " + asli.getLevel());
        duplikatReferensi.setLevel(99);
        System.out.println("Level Arjuna sesudah (lewat 'asli'): " + asli.getLevel());
        System.out.println("asli == duplikatReferensi? " + (asli == duplikatReferensi));

        ArrayList<Character> salinanList = manager.getAllCharacters();
        salinanList.add(new Character("Hantu", CharacterType.MAGE, 1));
        System.out.println("Total di manager asli: " + manager.getTotalCharacters());
        System.out.println("Total di salinan list : " + salinanList.size());
    }
}