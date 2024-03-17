package lesson5.homework5;

import java.io.File;

public class Tree {

    /**
     * 2. Доработайте класс Tree и метод print который мы разработали на семинаре.
     * Ваш метод должен распечатать полноценное дерево директорий и файлов относительно корневой директории.
     */
    public static void main(String[] args) {
        print(new File("."), "", true);
    }

    static void print(File file, String indent, boolean isLast){
        System.out.print(indent);
        if (isLast){
            System.out.print("└─");
            indent += "  ";
        }
        else {
            System.out.print("├─");
            indent += "│ ";
        }

        if (file.isDirectory()){
            System.out.println("\uD83D\uDDC0" + file.getName());
        } else{
            System.out.println("—" + file.getName());
        }

        File[] files = file.listFiles();
        if (files == null)
            return;

        int subDirTotal = 0;
        int filesTotal = 0;
        for (int i = 0; i < files.length; i++){
            if (files[i].isDirectory())
            {
                subDirTotal++;
            } else {
                filesTotal++;
            }
        }

        int subDirCounter = 0;
        int filesCounter =0;
        for (int i = 0; i < files.length; i++){
            if (files[i].isDirectory())
            {
                print(files[i], indent, subDirTotal == ++subDirCounter);
            }
            else {
                if (subDirTotal == subDirCounter) {
                    print(files[i], indent, filesTotal == ++filesCounter);
                } else {
                    print(files[i], indent, filesTotal == filesCounter);
                    filesCounter++;
                }
            }
        }

    }

}
