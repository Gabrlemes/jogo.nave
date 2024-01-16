package Enemy;

import java.awt.*;
import javax.swing.ImageIcon;

public class Enemy1 {
    private Image imagem;
    private int x, y;
    private int largura, altura;
    private boolean isVisivel;

    //    private static final int LARGURA = 938;
    private static int VELOCIDADE = 2;

    public Enemy1(int x, int y) {
        this.x = x;
        this.y = y;
        isVisivel = true;
    }

    public void load() {
        ImageIcon referencia = new ImageIcon("src\\res\\inimigo1.png");
        imagem = referencia.getImage();

        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);
    }

    public void update() {
        this.x -= VELOCIDADE;
//        if(this.x > LARGURA){
//            isVisivel = false;
//        }
    }

    public static int getVELOCIDADE() {
        return VELOCIDADE;
    }
    public static void setVELOCIDADE(int VELOCIDADE) {
//        Tiro.VELOCIDADE = VELOCIDADE;
    }

    public boolean isVisivel() {
        return isVisivel;
    }
    public void setVisivel(boolean visivel) {
        isVisivel = visivel;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImagem() {
        return imagem;
    }
}

