package gui;
import audioPlayerComp.SimplePlayer;
import dataSource.SaveLoadJS;
import dataStorage.AudioCatalog;
import playLists.PlayListFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.io.IOException;

public class swingGUI extends JFrame  {
        // Змінні логіки
        AudioCatalog audioCatalog;
        PlayListFactory playListFactory;
        SimplePlayer simplePlayer;

        // -----------------------------------
        boolean dragSlider = false;


        private final JButton playPauseButton;
        private final ImageIcon playIcon;
        private final ImageIcon pauseIcon;
        private final JLabel songTitleLabel;
        private final JSlider songSlider;
        private final JSlider volumeSlider;
        private boolean isPlaying = false;

        private void loadSong(){
            try {
                audioCatalog = AudioCatalog.getInstance();
                audioCatalog.setCatalog(SaveLoadJS.loadSongsList());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            PlayListFactory playListFactory = new PlayListFactory();
            simplePlayer = new SimplePlayer(playListFactory.createRawList());
        }

        private void updateSlider(){
            if (!dragSlider && )
        }

        public swingGUI() {
            loadSong();
            // Налаштування основного вікна
            setTitle("Simple Audio Player");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(800, 600);
            setLocationRelativeTo(null); // Вікно по центру екрана
            setLayout(new BorderLayout());

            // Головна панель (верхня частина, поки що порожня)
            JPanel mainContentPanel = new JPanel();
            mainContentPanel.setBackground(Color.DARK_GRAY);
            add(mainContentPanel, BorderLayout.CENTER);

            // Панель для аудіопрогравача
            JPanel playerPanel = new JPanel(new BorderLayout());
            playerPanel.setBackground(Color.BLACK);
            playerPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Відступи

            // Центральна частина програвача (контроли)
            JPanel controlsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
            controlsPanel.setOpaque(false); // Робить панель прозорою для фону

            // Завантаження іконок
            playIcon = new ImageIcon("path/to/play_icon.png"); // Замініть на шлях до своїх іконок
            pauseIcon = new ImageIcon("path/to/pause_icon.png"); // Замініть на шлях до своїх іконок

            // Створення кнопок
            JButton prevButton = new JButton("prev");
            playPauseButton = new JButton("Pl/Pa");
            JButton nextButton = new JButton("next");

            // Стилізація кнопок
            Dimension buttonSize = new Dimension(70, 50);
            Font buttonFont = new Font("Arial", Font.BOLD, 15);

            prevButton.setFont(buttonFont);
            playPauseButton.setFont(buttonFont);
            nextButton.setFont(buttonFont);

            prevButton.setPreferredSize(buttonSize);
            playPauseButton.setPreferredSize(buttonSize);
            nextButton.setPreferredSize(buttonSize);

            controlsPanel.add(prevButton);
            controlsPanel.add(playPauseButton);
            controlsPanel.add(nextButton);

            /*ThreadGroup threadGroup = new ThreadGroup("song");
            Thread thread = new Thread(threadGroup, () -> {
                simplePlayer.setSongPositionInList(0);
                simplePlayer.play();
            });*/
            // Кнопка play/pause: змінюємо її вигляд при кліку
            simplePlayer.setSongPositionInList(0);
            playPauseButton.addActionListener(e -> {
                isPlaying = !isPlaying;
                if (isPlaying) {
                    // Логіка для "Play"
                    playPauseButton.setText("❚❚"); // Символ паузи
                    // Тут має бути виклик вашого контролера для початку відтворення
                    //thread.start();
                    simplePlayer.play();
                } else {
                    // Логіка для "Pause"
                    playPauseButton.setText("▶");// Символ відтворення
                    simplePlayer.pause();
                    /*Thread thread1 = new Thread(() -> {
                        simplePlayer.pause();
                    });
                    thread1.start();*/
                }
            });

            // Інші кнопки
            prevButton.addActionListener(e -> simplePlayer.prevSong());
            nextButton.addActionListener(e -> simplePlayer.nextSong());

            // Нижній блок: повзунок пісні та гучності
            JPanel bottomPanel = new JPanel(new BorderLayout(10, 0));
            bottomPanel.setOpaque(false);

            // Лейбл для назви пісні
            songTitleLabel = new JLabel("Немає пісні", SwingConstants.CENTER);
            songTitleLabel.setForeground(Color.WHITE);
            songTitleLabel.setFont(new Font("Arial", Font.PLAIN, 16));

            JPanel labelAndSliderPanel = new JPanel(new BorderLayout());
            labelAndSliderPanel.setOpaque(false);
            labelAndSliderPanel.add(songTitleLabel, BorderLayout.NORTH);

            // Повзунок пісні
            songSlider = new JSlider(0, 1000, 0);
            songSlider.setOpaque(false);
            songSlider.setForeground(Color.WHITE);
            songSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    if (songSlider.getValueIsAdjusting()){
                        dragSlider = true;
                    } else if (dragSlider) {
                        simplePlayer.changeTrackPos(songSlider.getValue());
                        dragSlider = false;
                    }
                }
            });


            labelAndSliderPanel.add(songSlider, BorderLayout.CENTER);

            // Повзунок гучності
            volumeSlider = new JSlider(0, 100, 100);
            volumeSlider.setOrientation(JSlider.HORIZONTAL);
            volumeSlider.setOpaque(false);
            volumeSlider.setForeground(Color.WHITE);
            volumeSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    simplePlayer.changeVolume(volumeSlider.getValue());
                }
            });

            // Додаємо елементи на панель програвача
            playerPanel.add(labelAndSliderPanel, BorderLayout.NORTH);
            playerPanel.add(controlsPanel, BorderLayout.CENTER);
            playerPanel.add(volumeSlider, BorderLayout.EAST);

            // Додаємо панель програвача до основного вікна
            add(playerPanel, BorderLayout.SOUTH);
        }


        /**
         * Методи для оновлення стану GUI
         */
        public void setSongTitle(String title) {
            songTitleLabel.setText(title);
        }

        public void setPlaying(boolean isPlaying) {
            this.isPlaying = isPlaying;
            if (isPlaying) {
                playPauseButton.setText("❚❚");
            } else {
                playPauseButton.setText("▶");
            }
        }
    }

