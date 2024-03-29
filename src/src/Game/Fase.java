package Game;

import Enemy.Enemy2;
import Tiro.Tiro;
import Player.Player;
import Enemy.Enemy1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.List;

public class Fase extends JPanel implements ActionListener {

    private Image fundo;
    private Player player;
    private Timer timer;
    private List<Enemy1> enemy1;
    private boolean emJogo;


    //fundo do jogo
    public Fase() {
        setFocusable(true);
        setDoubleBuffered(true);

        ImageIcon referencia = new ImageIcon("src\\res\\space.jpg");
        fundo = referencia.getImage();

        player = new Player();
        player.load();

        addKeyListener(new TecladoAdapter());

        timer = new Timer(5, this);
        timer.start();

        inicializaInimigo();
        emJogo = true;
    }

    public void inicializaInimigo() {
        int cordenadas[] = new int[40];
        enemy1 = new ArrayList<Enemy1>();

        for (int i = 0; i < cordenadas.length; i++) {
            int x = (int)(Math.random() * 8000+1024);
            int y = (int)(Math.random() * 650+30);
            enemy1.add(new Enemy1(x, y));

        }
    }

    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        if(emJogo == true) {
            graficos.drawImage(fundo, 0, 0, null);
            graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);

            List<Tiro> tiros = player.getTiros();
            for(int i = 0; i < tiros.size(); i++) {
                Tiro m = tiros.get(i);
                m.load();
                graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
            }

            for (int o = 0; o < enemy1.size(); o++) {
                Enemy1 in = enemy1.get(o);
                in.load();
                graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
            }
            //tela game over
        }
        else{
            ImageIcon fimJogo = new ImageIcon("res\\over.jpg");
            graficos.drawImage(fimJogo.getImage(), 0, 0, null);
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.update();

        List<Tiro> tiros = player.getTiros();
        for(int i = 0; i < tiros.size(); i++) {
            Tiro m = tiros.get(i);
            if(m.isVisivel()) {
                m.update();
            } else{
                tiros.remove(i);
            }
        }

        for (int o = 0; o < enemy1.size(); o++) {
            Enemy1 in = enemy1.get(o);
            if(in.isVisivel()) {
                in.update();
            } else{
                enemy1.remove(o);
            }
        }
        checarColisoes();
        repaint();
    }


    //colisões
    public void checarColisoes() {
        Rectangle formaNave = player.getBounds();
        Rectangle formaEnemy1;
        Rectangle formaTiro;

        for(int i = 0; i < enemy1.size(); i++) {
            Enemy1 tempEnemy1 = enemy1.get(i);
            formaEnemy1 = tempEnemy1.getBounds();
            if(formaNave.intersects(formaEnemy1)) {
                player.setVisivel(false);
                tempEnemy1.setVisivel(false);
                emJogo = false;
            }
        }

        List<Tiro> tiros = player.getTiros();
        for (int j = 0; j < tiros.size(); j++){
            Tiro tempTiro = tiros.get(j);
            formaTiro = tempTiro.getBounds();
            for (int o = 0; o < enemy1.size(); o++) {
                Enemy1 tempEnemy1 = enemy1.get(o);
                formaEnemy1 = tempEnemy1.getBounds();
                if(formaTiro.intersects(formaEnemy1)) {
                    tempEnemy1.setVisivel(false);
                    tempTiro.setVisivel(false);
                }
            }
        }

    }

    private class TecladoAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyRelease(e);
        }

    }

}
