import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.view.MainWindow;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {

        // 1. Siapkan DATA terlebih dahulu (layer Model)
        CharacterManager manager = new CharacterManager();
        manager.addCharacter(new Character("Arjuna", CharacterType.WARRIOR, 5));
        manager.addCharacter(new Character("Merlin", CharacterType.MAGE, 3));
        manager.addCharacter(new Character("Athena", CharacterType.ARCHER, 4));
        manager.addCharacter(new Character("Elara", CharacterType.HEALER, 2));
        manager.addCharacter(new Character("Gandalf", CharacterType.MAGE, 10));
        manager.addCharacter(new Character("Gatotkaca", CharacterType.WARRIOR, 7));   // level tinggi
        manager.addCharacter(new Character("Morgana", CharacterType.MAGE, 1));        // level rendah

        // 2. Jalankan GUI dengan AMAN di Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            MainWindow window = new MainWindow(manager.getAllCharacters());
            window.setVisible(true);
        });
    }
}