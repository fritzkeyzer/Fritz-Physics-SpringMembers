package com.FritzPersonal;

import java.awt.*;

public class DrawData {

    private Color thisCol;

    private double lineThickness = 0.005;

    private Vector2D[] vertices;


    public DrawData() {
        thisCol = Color.WHITE;
        Vector2D[] vertices = new Vector2D[0];
    }


    public void colour(Color _in) {
        thisCol = _in;
    }

    public void vertices(Vector2D[] _in) {
        vertices = _in;
    }

    public Color colour() {
        return thisCol;
    }

    public Vector2D[] vertices() {
        return vertices;
    }

    public double lineThickness() {
        return lineThickness;
    }

    public void lineThickness(double _in) {
        lineThickness = _in;
    }

}