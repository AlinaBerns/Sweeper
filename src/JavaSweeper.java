import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import sweeper.Box;
import sweeper.Coord;
import sweeper.Game;
import sweeper.Ranges;

public class JavaSweeper extends JFrame {

    private Game game;

    //Вызываем панель, чтобы можно было рисовать
    private JPanel panel;
    private JLabel label;

    //Устанавливаем размер нашего поля и экрана игры при помощи констант
    // COLS - сколько столбцов у нашей игры
    //ROWS -сколько строчек
    //Еще нужно добавит константу, которая называется IMAGE_SIZE - это будет размер одной картинки

    private final int COLS = 9;
    private final int ROWS = 9;
    private final int BOMBS = 10;
    private final int IMAGE_SIZE = 50;

    public static void main(String[] args) {

        //Запустить программу в отдельном окне
        new JavaSweeper().setVisible(true);

    }

    private JavaSweeper () {

        game = new Game(COLS, ROWS, BOMBS);
        game.start();
        setImages(); //После запуска программы все картинки будут погружены, но еще не будут отображены
        initLabel();
        //Инициализируем панель
        initPanel();

        //Вызываем инициализацию метода ниже
        initFrame();

    }

    private void initLabel () {
        label = new JLabel("Welcome!");
        add(label, BorderLayout.SOUTH);
    }

    private void initPanel () {
        //Создаем панель. При ее создании создается анонимный класс.
        panel = new JPanel() {

            //Функция, которая рисует все


            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                for (Coord coord : Ranges.getAllCoords())
                    g.drawImage((Image) game.getBox(coord).image,
                            coord.x * IMAGE_SIZE, coord.y * IMAGE_SIZE, this);
            }
        };



        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coord coord = new Coord(x, y);
                if (e.getButton() == MouseEvent.BUTTON1) {
                    game.pressLeftButton (coord);

                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    game.pressRightButton(coord);
                }

                if (e.getButton() == MouseEvent.BUTTON2) {
                    game.start();
                }

                label.setText(getMessage ());

                panel.repaint();
            }
        });
        //Устанавливаем размер окна с помощью экземпляра объекта  Dimension
        panel.setPreferredSize(new Dimension(
                Ranges.getSize().x *IMAGE_SIZE,
                Ranges.getSize().y * IMAGE_SIZE));

        //добавляем панель

        add (panel);
    }

    private void initFrame () {

        //Действие по умолчанию при закрытии программы
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Прописываем название(Установим заголовок)
        setTitle("JavaSweeper_A.Berns");

        //Нам не нужно изменять размер нашего окна
        setResizable(false);

        //Чтобы было видно форму
        setVisible(true);

        // Метод pack() устанавливает такой минимальный размер контейнера,
        //который достаточен для отображения всех компонентов
        pack();
        //Делаем заголовок по центру
        setLocationRelativeTo(null);
        //Устанавливаем иконку
        setIconImage(getImage("icon"));


    }

    //Пишем функцию для загрузки картинок
    private void setImages () {
        for (Box box : Box.values()) {
            box.image = getImage(box.name());
        }

    }

    //Создадим функцию для установки картинок и их получения
    private Image getImage (String name) {
        String filename = "img/" + name.toLowerCase() + ".png";

        //Перед созданием объекта передачи картинок нужно сделать в проекте новую директорию,
        //а затем подключить ее через правую кнопку мыши->MarkDirectoryUs-> TestRecourseRoot
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(filename)));
        return icon.getImage();
    }

    public String getMessage() {
        switch (game.getState()) {
            case PLAYED: return "Think twice";
            case BOMBED: return "YOU LOSE! BIG BA-DA-BOOM!!!";
            case WINNER: return "CONGRATULATIONS!!!";
            default    : return "Welcome!";
        }
    }

    public void setMessage(String message) {

    }
}
