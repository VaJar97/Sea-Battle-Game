package com.dotcomgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class GameHelper {

    private static final String alphabet = "abcdefg";
    private int gridLength = 7;
    private int gridSize = 49;
    private int[] grid = new int[gridSize]; // Установили размер массива - 49
    private int comCount = 0;

    public String getUserInput(String prompt) {

        String inputLine = "";

        System.out.println(prompt + " ");

        while(inputLine.equals("")) {
            try {
                BufferedReader is = new BufferedReader(new InputStreamReader(System.in));

                inputLine = is.readLine();

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            if (inputLine.equals("")) {
                System.out.println("You have not entered anything. Please, enter a coordinate:");
            }
        }
        return inputLine.toLowerCase();
    }

    public ArrayList<String> placeDotCom(int comSize) {   // comeSize = размер одного "корабля", 3 ячейки

        ArrayList<String> alphaCells = new ArrayList<>();  // Массив для хранения координат
        String temp;            // Переменная, для хранения временного значения
        int[] coords = new int[comSize];        // Массив, для хранения 3х циферных координат "корабля"
        int attempts = 0;                       // Переменная для подсчета попыток
        boolean success = false;                // Показывает успешное расположение первоночальной координаты
        int location;                       // Для хранение первой циферной координаты "корабля"

        comCount++;                             // Подсчитывает созданых "кораблей"

        int incr = 1;                           // Переменная для инкремента от начальной координаты
        if ((comCount % 2) == 1) {              // Проверка на наличие уже "кораблей", чтобы сделать их разной формы
            incr = gridLength;                  // incr = 7
        }
        //System.out.println(comCount +" "+ incr);
        while ( !success & attempts++ < 200) {      // Пока удачное расположение не найдено И количетсво попыток найти меньше 200

            location = (int) (Math.random() * gridSize);    //Рандом из 49 ячеек, записываем в переменную для первой координаты
            //System.out.println(" try " + location);
            int x = 0;                                    // Устанавливает порядок (индекс) текущей координаты, Всего 3, от 0 до 2
            success = true;
            while (success && x < comSize) {            // Пока расположение считается удачным, и количество координат у корабля меньше 3
                if (grid[location] == 0) {          // Проверяет в пустом массиве, с размером 49, по индексу равному выбраной ячейке, наличие уже добавленной туда ячейки
                    coords[x++] = location;         // В координату по указанному индексу ставит проверяемую ячейку и увеличивает индекс, двигая порядок к следующей координате
                    location += incr;               // Выбранная первая ячейка увеличивается на значение incr: 1 или 7
                    if (location >= gridSize) {    // Если следующая, от изначально-выбранной, ячейка больше или равна 49, то поиск прекращается (правая сторона)
                        success = false;
                    }
                    if (x > 0 && (location % gridLength == 0)) {        // Проверка на границу с правой стеной сетки
                        success = false;                                // Если следующая, от изначально-выбранной, ячейка рядом со стеной, поиск прекращается
                    }
                } else {                    // Если под выбранным индексом уже есть что-то, то поиск прекращается
                    //System.out.println(" used " + location);
                    success = false;
                }
            }                      // Нумерация ячеек в самой сетке начинается с 0. с 0 до 48 - 49 ячеек
        }
        int x = 0;          // Устанавливает порядок (индекс) координат. Всего три - от 0 до 2
        int row;            // Ряд
        int column;         // Колона
        while (x < comSize) {               // Пока порядок ячейки меньше 3х, так как номера ячеек от 0,1,2 (размер корабля)
            grid[coords[x]] = 1;           // Из координаты с индексом достаем значение ячейки, и записываем ее наличие в пустой массив (занимаем место)
            row = (coords[x] / gridLength); // Координату целочисленно делим на 7, тем самым выбирая ряд по вертикали( от 0 до 6)

            column = coords[x] % gridLength;    // Остаток от деления на 7 указывает на столбик по горизонтали. от 0 до 6.

            temp = String.valueOf(alphabet.charAt(column)); //По номеру строки от 0 до 6 берем букву из масива ABCDEFG и сохраняем как String

            alphaCells.add(temp.concat(Integer.toString(row))); // Добавляем координату в пустой слот массива, объединяем Букву и Цифру при помощи Конкатенации
            System.out.println(" coord " + x + " = " + alphaCells.get(x) + "  " + coords[x]);
            x++;                     // Переходим к следующей координате "корабля" от 0 до 2

        }
        //System.out.println("\n");
        return alphaCells;          // Возвращаем массив с ячейками для дальнейшей записи их в массив массивов
    }
}
