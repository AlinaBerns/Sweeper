package alinaberns.sweeper;

import javax.swing.*;
import java.awt.*;

public class JavaSweeper extends JFrame {

    //Вызываем панель, чтобы можно было рисовать
    private JPanel panel;

    //Устанавливаем размер нашего поля и экрана игры при помощи констант
    // COLS - сколько столбцов у нашей игры
    //ROWS -сколько строчек
    //Еще нужно добавит константу, которая называется IMAGE_SIZE - это будет размер одной картинки

    private final int COLS = 15;
    private final int ROWS = 1;
    private final int IMAGE_SIZE =50;

    public static void main(String[] args) {

        //Запустить программу в отдельном окне
        new JavaSweeper().setVisible(true);

    }

    private JavaSweeper () {

        //Инициализируем панель
        initPanel();

        //Вызываем инициализацию метода ниже
        initFrame();

    }

    private void initPanel () {
        //Создаем панель
        panel = new JPanel();

        //Устанавливаем размер окна с помощью экземпляра объекта  Dimension
        panel.setPreferredSize(new Dimension(COLS*IMAGE_SIZE,ROWS*IMAGE_SIZE));

        //добавляем панель

        add (panel);
    }

    private void initFrame () {


        // Метод pack() устанавливает такой минимальный размер контейнера,
        //который достаточен для отображения всех компонентов

        pack();

        //Действие по умолчанию при закрытии программы
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Прописываем название(Установим заголовок)
        setTitle("JavaSweeper_A.Berns");

        //Делаем заголовок по центру
        setLocationRelativeTo(null);

        //Нам не нужно изменять размер нашего окна
        setResizable(false);

        //Чтобы было видно форму
        setVisible(true);


    }

}
