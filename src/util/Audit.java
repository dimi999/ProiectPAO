package util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

public final class Audit {
    private static File audit;
    private static Path path;
    private Audit() {
    }

    public static void initAudit() {
        audit = new File("audit.csv");
        try {
            audit.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        path = Paths.get(audit.toURI());
    }

    public static void printAction(String function) {
        String output = function + ", " + new Date() + '\n';
        byte[] strDecoded = output.getBytes();
        try {
            Files.write(path, strDecoded, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
