package com.FritzPersonal;

public class Joint {

    private double mass;
    private double invMass;

    private Vector2D pos;

    private Vector2D vel;
    private Vector2D forceSum;
    private Vector2D accelPrevFrame;

    private DrawData drawable;
    private Vector2D[] vertices;

    private boolean fixed = false;

    private double velFric = 0.1;

    public Joint(Vector2D _pos, double _mass) {
        pos = _pos;
        mass = _mass;
        invMass = 1 / mass;
        vel = Vector2D.zero();
        forceSum = Vector2D.zero();
        accelPrevFrame = Vector2D.zero();
        drawable = new DrawData();
        vertices = new Vector2D[1];
        vertices[0] = pos;
        drawable.vertices(vertices);
        drawable.lineThickness(0.002 * mass);
    }

    public void update(double _dt) {
        if (!fixed) {
            Vector2D velAdd = Vector2D.multiply(accelPrevFrame, _dt);

            accelPrevFrame = Vector2D.multiply(forceSum, invMass);

            Vector2D velStart = vel;

            vel = Vector2D.sumOf(vel, velAdd);

            Vector2D posAdd = Vector2D.multiply(vel, 0.5 * _dt);

            pos = Vector2D.sumOf(pos, posAdd);

            vel = Vector2D.multiply(vel, (1 - (velFric * _dt)));

            vertices[0] = pos;

            drawable.vertices(vertices);

            forceSum = Vector2D.zero();
        }
    }

    public void fixed(boolean _in) {
        fixed = _in;
    }

    public void applyGravity() {
        applyForce(new Vector2D(0, -9.81 * mass));
    }

    public void applyPolarForce(double _mag, double _ang) {
        applyForce(Vector2D.polar(_mag, _ang));
    }

    public void applyForce(Vector2D _f) {
        forceSum = Vector2D.sumOf(forceSum, _f);
    }

    public DrawData drawable() {
        return drawable;
    }

    public Vector2D pos() {
        return pos;
    }

    public Vector2D vel() {
        return vel;
    }

}