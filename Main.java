import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    static Scanner введення = new Scanner(System.in, StandardCharsets.UTF_8);

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        while (true) {
            System.out.println("\n ЛАБОРАТОРНА РОБОТА № 1 ");
            System.out.println("1 — Завдання 1 (варіант 3): обчислення виразу");
            System.out.println("2 — Завдання 2 (варіант 13): вилучення чисел, що повторюються більше 3 разів");
            System.out.println("3 — Завдання 3 (варіант 13): циклічний зсув стовпців матриці");
            System.out.println("4 — Завдання 4 (варіант 13): пошук слів без повторення літер");
            System.out.println("0 — Завершити роботу");
            System.out.print("Оберіть завдання: ");

            int вибір = введення.nextInt();

            switch (вибір) {
                case 1:
                    завданняПерше();
                    break;
                case 2:
                    завданняДруге();
                    break;
                case 3:
                    завданняТретє();
                    break;
                case 4:
                    завданняЧетверте();
                    break;
                case 0:
                    System.out.println("Роботу програми завершено.");
                    return;
                default:
                    System.out.println("Помилка: такого пункту меню немає.");
            }
        }
    }

    // Завдання 1, варіант 3//
    static double обчислитиВираз(double n, double m) {
        double першийЗнаменник = n * n * m + 2;
        double другийЗнаменник = m - n;

        if (першийЗнаменник == 0 || другийЗнаменник == 0) {
            throw new ArithmeticException("Ділення на нуль: перевірте значення n та m.");
        }

        return (n + 1) / першийЗнаменник
                + (m + n) / другийЗнаменник
                + n * n * n;
    }

    static void завданняПерше() {
        System.out.println("\n--- Завдання 1. Варіант 3 ---");
        System.out.println("F(n,m) = (n + 1)/(n^2*m + 2) + (m + n)/(m - n) + n^3");
        System.out.println("1 — дійсні n і m, дійсний результат");
        System.out.println("2 — цілі n і m, дійсний результат");
        System.out.println("3 — дійсні n і m, цілий результат");
        System.out.print("Оберіть режим: ");
        int режим = введення.nextInt();

        if (режим < 1 || режим > 3) {
            System.out.println("Помилка: потрібно обрати режим 1, 2 або 3.");
            return;
        }

        double n;
        double m;

        if (режим == 2) {
            System.out.print("Введіть ціле n: ");
            n = введення.nextInt();
            System.out.print("Введіть ціле m: ");
            m = введення.nextInt();
        } else {
            System.out.print("Введіть дійсне n: ");
            n = введення.nextDouble();
            System.out.print("Введіть дійсне m: ");
            m = введення.nextDouble();
        }

        try {
            double результат = обчислитиВираз(n, m);
            if (режим == 3) {
                System.out.println("Результат (ціла частина): " + (int) результат);
            } else {
                System.out.println("Результат: " + результат);
            }
        } catch (ArithmeticException помилка) {
            System.out.println("Помилка: " + помилка.getMessage());
        }
    }

    // Завдання 2, варіант 13//
    static void завданняДруге() {
        System.out.println("\n--- Завдання 2. Варіант 13 ---");
        System.out.print("Введіть кількість елементів масиву (1–500): ");
        int n = введення.nextInt();

        if (n < 1 || n > 500) {
            System.out.println("Помилка: кількість елементів має бути від 1 до 500.");
            return;
        }

        int[] масив = new int[n];
        System.out.println("Введіть " + n + " цілих чисел:");
        for (int i = 0; i < n; i++) {
            System.out.print("A[" + (i + 1) + "] = ");
            масив[i] = введення.nextInt();
        }

        System.out.print("Початковий масив: ");
        надрукуватиМасив(масив, n);

        int[] початковийМасив = масив.clone();
        int новаДовжина = 0;
        for (int i = 0; i < n; i++) {
            int кількість = 0;
            for (int j = 0; j < n; j++) {
                if (початковийМасив[j] == початковийМасив[i]) {
                    кількість++;
                }
            }

            if (кількість <= 3) {
                масив[новаДовжина] = початковийМасив[i];
                новаДовжина++;
            }
        }

        System.out.print("Масив після вилучення чисел, які повторюються більше 3 разів: ");
        надрукуватиМасив(масив, новаДовжина);
    }

    static void надрукуватиМасив(int[] масив, int довжина) {
        if (довжина == 0) {
            System.out.println("порожній");
            return;
        }
        for (int i = 0; i < довжина; i++) {
            System.out.print(масив[i] + " ");
        }
        System.out.println();
    }

    // Завдання 3, варіант 13//
    static void завданняТретє() {
        System.out.println("\n--- Завдання 3. Варіант 13 ---");
        System.out.print("Введіть розмір квадратної матриці n (1–20): ");
        int n = введення.nextInt();

        if (n < 1 || n > 20) {
            System.out.println("Помилка: розмір матриці має бути від 1 до 20.");
            return;
        }

        int[][] матриця = new int[n][n];
        System.out.println("Введіть елементи матриці:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("A[" + (i + 1) + "][" + (j + 1) + "] = ");
                матриця[i][j] = введення.nextInt();
            }
        }

        System.out.println("Початкова матриця:");
        надрукуватиМатрицю(матриця);

        int мінімум = матриця[0][0];
        int стовпецьМінімуму = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (матриця[i][j] < мінімум) {
                    мінімум = матриця[i][j];
                    стовпецьМінімуму = j;
                }
            }
        }

        int[][] результат = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                результат[i][j] = матриця[i][(j + стовпецьМінімуму) % n];
            }
        }

        System.out.println("Найменший елемент: " + мінімум);
        System.out.println("Номер його стовпця: " + (стовпецьМінімуму + 1));
        System.out.println("Матриця після циклічного зсуву стовпців:");
        надрукуватиМатрицю(результат);
    }

    static void надрукуватиМатрицю(int[][] матриця) {
        for (int i = 0; i < матриця.length; i++) {
            for (int j = 0; j < матриця[i].length; j++) {
                System.out.printf("%6d", матриця[i][j]);
            }
            System.out.println();
        }
    }

    // Завдання 4, варіант 13//
    static void завданняЧетверте() {
        введення.nextLine(); 
        System.out.println("\n--- Завдання 4. Варіант 13 ---");
        System.out.println("Введіть текст:");
        String текст = введення.nextLine();

        String[] слова = текст.split("[^\\p{L}\\p{N}]+");
        System.out.println("Слова, у яких усі літери не повторюються:");

        boolean знайдено = false;
        for (String слово : слова) {
            if (слово.isEmpty()) {
                continue;
            }

            String нижнійРегістр = слово.toLowerCase(java.util.Locale.ROOT);
            boolean безПовторів = true;

            for (int i = 0; i < нижнійРегістр.length(); i++) {
                char літера = нижнійРегістр.charAt(i);
                if (нижнійРегістр.indexOf(літера) != нижнійРегістр.lastIndexOf(літера)) {
                    безПовторів = false;
                    break;
                }
            }

            if (безПовторів) {
                System.out.print(слово + " ");
                знайдено = true;
            }
        }

        if (!знайдено) {
            System.out.print("Таких слів немає.");
        }
        System.out.println();
    }
}
