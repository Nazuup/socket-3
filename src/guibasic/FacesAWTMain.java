//FacesAWTMain.java
//FacesAWTMain 目標 インナークラスのFaceObjクラスを作ってみよう。描画処理を移譲してください。
//3x3　の顔を描画してください。色などもぬってオリジナルな楽しい顔にしてください。

package guibasic;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FacesAWTMain {

    public static void main(String[] args) {
        new FacesAWTMain();
    }

    FacesAWTMain() {
        FaceFrame f = new FaceFrame();
        f.setSize(800, 800);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f.setVisible(true);
    }

    // インナークラスを定義
    class FaceFrame extends Frame {
        private int w = 200;
        private int h = 200;
        private int xStart = 50;
        private int yStart = 50;

        public void paint(Graphics g) {
            // この中には、g.drawLine というのは入ってこない
            // Graphicsクラス(型のようなもの---今は--)のgという変数はメソッドに渡す
            FaceObj[] fobjs = new FaceObj[9];

            for (int j = 0; j < 3; j++) {
                for (int i = 0; i < 3; i++) {
                    int rgb = (i + 1) * 10 + (j + 3) * 10;
                    Color color = new Color(255 - rgb, 210 - rgb, 150 + rgb);
                    g.setColor(color);
                    fobjs[i + 3 * j] = new FaceObj();
                    fobjs[i + 3 * j].setPosition(w * i + xStart, h * j + yStart, w, h);
                    fobjs[i + 3 * j].setEmotionLevel(i, j);
                    fobjs[i + 3 * j].drawFace(g);
                }
            }
        }

    }// FaceFrame end

    // Faceクラスを作ってみよう。
    private class FaceObj {
        private int w = 200;
        private int h = 200;
        private int xStart = 50;
        private int yStart = 50;
        private int browEmotion = -10;
        private int mouseEmotion = -20;
        // private FaceObj fobj1;

        public void drawRim(Graphics g) { // wが横幅、hが縦幅
            g.fillRoundRect(xStart, yStart, w, h, 40, 40);
        }

        public void setEmotionLevel(int i, int j) {
            this.browEmotion = 10 - (i * 10);
            this.mouseEmotion = -20 + (j * 20);
        }

        public void drawFace(Graphics g) {
            drawRim(g);
            g.setColor(new Color(0, 0, 0));
            drawBrow(g, browEmotion);
            drawEye(g, 35);
            drawNose(g, 20);
            drawMouth(g, 100, mouseEmotion);
        }

        public void drawBrow(Graphics g, int bEmo) { // xは目の幅 呼ばれる方(=定義する方)
            int wx1 = xStart + w * 2 / 8;
            int wx2 = xStart + w * 6 / 8;
            int wy = yStart + h / 4;
            g.drawLine(wx1, wy + bEmo, wx1 + w * 1 / 8, wy);
            g.drawLine(wx2, wy, wx2 + w * 1 / 8, wy + bEmo);
        }

        public void drawNose(Graphics g, int nx) { // xは鼻の長さ
            int top = yStart + 70 + h / 4;
            int xMiddle = xStart + h - 80;
            g.drawLine(xMiddle, top, xMiddle, top - nx);
        }

        public void drawEye(Graphics g, int r) { // rは目の半径
            g.drawOval(xStart + 45, yStart + 65, r, r);
            g.drawOval(xStart + 140, yStart + 65, r, r);

        }

        public void drawMouth(Graphics g, int mx, int mEmo) { // xは口の幅
            int xMiddle = xStart + w / 2;
            int yMiddle = yStart + h - 30;
            g.drawLine(xMiddle - mx / 2, yMiddle, xMiddle, yMiddle + mEmo);
            g.drawLine(xMiddle, yMiddle + mEmo, xMiddle + mx / 2, yMiddle);
        }

        public void setPosition(int xStart0, int yStart0, int w0, int h0) {
            this.xStart = xStart0;
            this.yStart = yStart0;
            this.w = w0;
            this.h = h0;
        }
    }

}// Faces class end
