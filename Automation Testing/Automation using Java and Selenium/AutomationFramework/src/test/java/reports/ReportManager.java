package reports;

import java.io.FileWriter;
import java.io.IOException;

public class ReportManager {
    private static final String REPORT_FILE = "report.log";

    public static void log(String message) {
        System.out.println("[REPORT] " + message);
        try (FileWriter writer = new FileWriter(REPORT_FILE, true)) {
            writer.write("[REPORT] " + message + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}