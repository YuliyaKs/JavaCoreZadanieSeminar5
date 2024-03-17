package lesson5.homework5;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class MakeBackup {

    public static void main(String[] args) throws IOException {

        String sourceDir = "./";
        String backupDir = "./backup";

        makeBackup(sourceDir, backupDir);
    }

    public static void makeBackup(String sourceDir, String backupDir) throws IOException {
        // Создаем папку для резервной копии всех файлов
        File backupDirectory = new File(backupDir);
        if (!backupDirectory.exists()) {
            backupDirectory.mkdir();
        }

        // Получаем список файлов в директории
        File sourceDirectory = new File(sourceDir);
        File[] filesToBackup = sourceDirectory.listFiles();

        // Копируем каждый файл и папку в папку с резервными копиями
        for (File file : filesToBackup) {
            if (file.isFile()) {
                Files.copy(file.toPath(), new File(backupDirectory.getPath() + "/" + file.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
        // Создаем копию каждой папки, аналогичную исходной
            } else if (file.isDirectory() && !file.getName().equals("backup")) {
                String soursePath = file.getPath();
                String backupPath = soursePath.substring(2);
                File directoryInBackup = new File("./backup/" + backupPath);
                directoryInBackup.mkdir();
        // Вызываем рекурсивно метод копирования для каждой папки
                makeBackup(soursePath, directoryInBackup.getPath());
            }
        }
    }
}
