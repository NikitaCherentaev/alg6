class AnimalThread extends Thread {
    private final String name; // имя животного
    private final int priority; // приоритет выполнения потока
    private static final int DISTANCE_TO_RUN = 100; // дистанция которую должны пробежать животные
    public AnimalThread(String name, int priority) { // конструктор для инициализации полей
        this.name = name; // инициализируем имя животного
        this.priority = priority; // инициализируем приоритет выполнения потока
        setName(name); // устанавливаем имя потока
        setPriority(priority); // устанавливаем приоритет потока
    }
    @Override
    public void run() { // метод, который выполняется, когда поток запускается
        int distance = 0; // начальное расстояние
        while (distance < DISTANCE_TO_RUN) { //пока расстояние меньше заданной дистанции
            distance += (int)(Math.random() * 10) + 1; //добавляем случайное расстояние от 1 до 10 метров
            System.out.println(name + " пробежал: " + distance + " метров. Приоритет: " + priority); // сколько пробежало животное
            try {
                sleep(100); //слип 100 миллисекунд
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // если поток прерван, устанавливаем флаг прерывания
            }
            if (distance < DISTANCE_TO_RUN / 2) {
                setPriority(Thread.MAX_PRIORITY); // если пробежали меньше половины дистанции, повышаем приоритет чтобы животное быстрее двигалось
            } else {
                setPriority(Thread.MIN_PRIORITY); // уменьшаем приоритет, если дистанция больше половины
            }
        }
        System.out.println(name + " финишировал!"); // животное финишировало
    }
}

public class RabbitAndTurtle {
    public static void main(String[] args) { //основной метод
        AnimalThread rabbit = new AnimalThread("Кролик", Thread.NORM_PRIORITY); // создание потока кролика
        AnimalThread turtle = new AnimalThread("Черепаха", Thread.NORM_PRIORITY); // создание потока черепахи
        //запуск потока кролика
        rabbit.start();
        //запуск потока черепахи
        turtle.start();
    }
}