import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Character Manager v0.1 ===");

        Character hero = new Character("Arjuna", CharacterType.WARRIOR, 5);
        Character wizard = new Character("Merlin", CharacterType.MAGE, 3);
        Character scout = new Character("Athena", CharacterType.ARCHER, 4);

        System.out.println(hero);
        System.out.println(wizard);
        System.out.println(scout);
        System.out.println();

        System.out.println(">> " + hero.getName() + " level up!");
        hero.setLevel(hero.getLevel() + 1);
        System.out.println("After level up: " + hero);
        System.out.println();

        System.out.println(">> Nama karakter: " + hero.getName());
        System.out.println(">> Jenis karakter: " + hero.getType());
        System.out.println(">> HP saat ini: " + hero.getHitPoints());
        
        System.out.println();
        System.out.println("=== Mini-Challenge ===");
        Character healer = new Character("Elara", CharacterType.HEALER, 2);
        healer.setMana(80);
        System.out.println(healer);
        System.out.println(">> " + healer.getName() + " masih hidup? " + healer.isAlive() );
    }
}