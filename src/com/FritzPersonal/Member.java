package com.FritzPersonal;

import java.awt.*;

public class Member {

    private double length;

    private double k = 1000000;

    private Joint[] joints;

    private DrawData drawable;
    private Vector2D[] vertices;

    public Member(Joint _a, Joint _b) {
        joints = new Joint[2];
        joints[0] = _a;
        joints[1] = _b;

        Vector2D a = Vector2D.zero();
        Vector2D b = Vector2D.zero();
        if (joints[0] != null) {
            a = joints[0].pos();
        } else {
            System.out.println("Member - joint facked out");
        }
        if (joints[1] != null) {
            b = joints[1].pos();
        } else {
            System.out.println("Member - joint facked out");
        }

        length = Vector2D.dist(a, b);

        drawable = new DrawData();
        vertices = new Vector2D[2];
        drawable.vertices(vertices);
        updateVertices();
    }

    public void update(double _dt) {
        Vector2D a = Vector2D.zero();
        Vector2D b = Vector2D.zero();

        if (joints[0] != null) {
            a = joints[0].pos();
        } else {
            System.out.println("Member - joint facked out");
        }
        if (joints[1] != null) {
            b = joints[1].pos();
        } else {
            System.out.println("Member - joint facked out");
        }

        double dist = Vector2D.dist(a, b);

        double angle = a.angleTo(b);

        double dif = dist - length;

        double f = dif * k;

        if (joints[0] != null) {
            joints[0].applyPolarForce(f, angle);
        }
        if (joints[1] != null) {
            joints[1].applyPolarForce(-f, angle);
        }


        updateVertices();
    }

    public void setK(double _k) {
        k = _k;
    }

    public void setCol(Color _in) {
        drawable.colour(_in);
    }

    public DrawData drawable() {
        return drawable;
    }

    private void updateVertices() {
        vertices[0] = Vector2D.zero();
        vertices[1] = Vector2D.zero();

        if (joints[0] != null) {
            vertices[0] = joints[0].pos();
        }
        if (joints[1] != null) {
            vertices[1] = joints[1].pos();
        }

        drawable.vertices(vertices);
    }

}