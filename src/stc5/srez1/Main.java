package stc5.srez1;

//Вариант 1:
//        Реализовать программу из 2-х потоков.
// Один из потоков каждую секунду генерирует случайное число в интервале [0;99]. Второй поток раз в
// пять секунд выводит количество уникальных чисел, сгенерированных первым потоком. После того, как будет
// сгенерировано все 100 чисел, оба потока должны остановить свое выполнение.

public class Main {

    public static void main(String[] args) {
        SyncData container = new SyncData();
        RandomGenerator randomGenerator = new RandomGenerator(container);
        Printer printer = new Printer(container);

        try {
            randomGenerator.t.join();
            printer.t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
