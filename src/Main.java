import javax.swing.SwingUtilities;
import com.crawler.database.DatabaseSetup;
import com.crawler.ui.ChooseOptionGUI;

public class Main {

    public static void main(String[] args) {

        DatabaseSetup.createTable();

        SwingUtilities.invokeLater(() -> {
            new ChooseOptionGUI();
        });
    }
}