package com.FritzPersonal;

import java.awt.*;
import java.util.ArrayList;

public class Render {

    private static final String fontType = "Monospaced";
    private static int fontSize = 24;

    private static boolean init = false;

    private static double penRad = 0.005;


    private static int res = 900;

    private static double zoomScale = 0.4;
    private static Vector2D camPos = Vector2D.zero();

    public static void drawThese(ArrayList<DrawData> _in) {
        for (DrawData _toDraw : _in) {
            draw(_toDraw);
        }
    }

    public static void consoleText(String _newLine) {
        //TODO
        StdDraw.setPenColor(Color.GREEN);
        Font font = new Font(fontType, Font.PLAIN, fontSize);
        StdDraw.setFont(font);

        double xMax = camPos.x() + (1 / zoomScale);
        double yMin = camPos.y() - (0.9 / zoomScale);

        StdDraw.textRight(xMax, yMin, _newLine);
    }

    public static void clear() {
        if (!init) {
            init();
            init = true;
        }

        double xMin = camPos.x() - (1 / zoomScale);
        double xMax = camPos.x() + (1 / zoomScale);
        double yMin = camPos.y() - (1 / zoomScale);
        double yMax = camPos.y() + (1 / zoomScale);

        StdDraw.setXscale(xMin, xMax);
        StdDraw.setYscale(yMin, yMax);
        StdDraw.clear(Color.DARK_GRAY);
    }

    public static void show() {
        StdDraw.show();
    }

    private static void init() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(res, res);
    }

    private static void draw(DrawData _in) {
        Vector2D[] vertices = _in.vertices();
        int verticesNo = vertices.length;

        penRad = _in.lineThickness();

        StdDraw.setPenRadius(penRad);
        StdDraw.setPenColor(_in.colour());

        if (verticesNo == 1) {
            //draw dot:
            StdDraw.filledCircle(vertices[0].x(), vertices[0].y(), penRad);
        } else if (verticesNo == 2) {
            //draw 1 line between the two vertices
            StdDraw.line(vertices[0].x(), vertices[0].y(), vertices[1].x(), vertices[1].y());
        } else {
            //draw lines between each vertex

        }
    }

}