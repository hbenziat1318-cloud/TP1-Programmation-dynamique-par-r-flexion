package presentation;

import dao.IDao;
import metier.IMetier;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Presentation2 {
    public static void main(String[] args) throws Exception {
        // Lecture du fichier config.txt
        Scanner scanner = new Scanner(new File("config.txt"));

        // Lecture du nom de la classe DAO
        String daoClassName = scanner.nextLine();

        // Instanciation dynamique de la classe DAO
        Class<?> cDao = Class.forName(daoClassName);
        IDao dao = (IDao) cDao.getDeclaredConstructor().newInstance();

        // Lecture du nom de la classe métier
        String metierClassName = scanner.nextLine();
        Class<?> cMetier = Class.forName(metierClassName);
        IMetier metier = (IMetier) cMetier.getDeclaredConstructor().newInstance();

        // Injection de dépendance via réflexion
        Method setDaoMethod = cMetier.getMethod("setDao", IDao.class);
        setDaoMethod.invoke(metier, dao);

        // Appel de la méthode calcul
        System.out.println("Résultats = " + metier.calcul());

        scanner.close();
    }
}
