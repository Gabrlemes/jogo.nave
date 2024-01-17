package Player;

import Tiro.Tiro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.IllegalFormatWidthException;
import java.util.List;

public class Player {

    private int x,y;
    private int dx,dy;
    private Image imagem;
    private int altura, largura;
    private List <Tiro> tiros;


    public Player(){
        this.x = 100;
        this.y = 100;

        tiros = new ArrayList<Tiro>();
    }

    public void load(){
        ImageIcon referencia = new ImageIcon("src\\res\\nave.png");
        imagem = referencia.getImage();

        altura = imagem.getHeight(null);
        largura = imagem.getWidth(null);
    }

    public void update() {
        x += dx;
        y += dy;
    }

    public void tiroSimples() {
        this.tiros.add(new Tiro(x+largura, y+(altura/2)));
    }

    public Rectangle getBounds() {
        return new Rectangle(x,y,largura,altura);
    }

    public void keyPressed(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_A) {
            tiroSimples();
        }

        if (codigo == KeyEvent.VK_UP) {
            dy=-3;
        }
        if (codigo == KeyEvent.VK_DOWN) {
            dy=3;
        }
        if (codigo == KeyEvent.VK_LEFT) {
            dx=-3;
        }
        if (codigo == KeyEvent.VK_RIGHT) {
            dx=3;
        }
    }

    public void keyRelease(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_UP) {
            dy=0;
        }
        if (codigo == KeyEvent.VK_DOWN) {
            dy=0;
        }
        if (codigo == KeyEvent.VK_LEFT) {
            dx=0;
        }
        if (codigo == KeyEvent.VK_RIGHT) {
            dx=0;
        }
    }

    public int getX() {
        return x;
    }

    public int getY(){
        return y;
    }

    public Image getImagem(){
        return imagem;
    }

    public List<Tiro> getTiros() {
        return tiros;
    }
}