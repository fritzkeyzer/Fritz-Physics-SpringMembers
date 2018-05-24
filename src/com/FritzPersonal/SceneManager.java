package com.FritzPersonal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class SceneManager {

    private static final double detail = 1;                    //min 0.5

    private static double fixedDeltaTime;
    private static int showEvery = 20000;

    private static int frame = 0;

    private static ArrayList<Joint> joints = new ArrayList<Joint>();
    private static ArrayList<Member> members = new ArrayList<Member>();

    private static double lastTime = System.nanoTime();

    private static boolean init = false;

    public static void main(String[] args) {
        init();

        //create some objects...

        Joint a = new Joint(new Vector2D(0, 0), 1);
        Joint b = new Joint(new Vector2D(0.5, 0.5), 20);
        Joint c = new Joint(new Vector2D(1, 0), 20);
        Joint d = new Joint(new Vector2D(0, 0.5), 20);

        Member ab = new Member(a, b);
        Member bc = new Member(b, c);
        Member cd = new Member(c, d);
        Member ac = new Member(c, a);
        Member bd = new Member(b, d);
        Member ad = new Member(a, d);

        //bc.setK(100);
        //ac.setK(30);
        //ac.setCol(Color.RED);
        //bd.setK(20);
        //bd.setCol(Color.BLUE);
        //ad.setK(10);
        //ad.setCol(Color.GREEN);

        a.fixed(true);
        //d.fixed(true);

        //add to collections
        joints.add(a);
        joints.add(b);
        joints.add(c);
        joints.add(d);

        members.add(ab);
        members.add(bc);
        members.add(cd);
        //members.add(ac);
        //members.add(bd);
        //members.add(ad);


        while (true) {
            update();
        }
    }

    private static void init() {
        init = true;
        fixedDeltaTime = 0.000001 / detail;
    }

    public static void update() {
        double _dt = fixedDeltaTime;
        if (!init) {
            init();
        }
        for (Joint _j : joints) {
            _j.applyGravity();
        }
        for (Member _m : members) {
            _m.update(_dt);
        }
        for (Joint _j : joints) {
            _j.update(_dt);
        }

        if (frame % showEvery == 0) {        //every 1/100 S
            frame = 0;
            double time = System.nanoTime();
            double deltaTime = (time - lastTime);
            lastTime = time;

            Render.clear();
            ArrayList<DrawData> drawables = new ArrayList<DrawData>();
            for (Joint _j : joints) {
                drawables.add(_j.drawable());
            }
            for (Member _m : members) {
                drawables.add(_m.drawable());
            }
            Render.drawThese(drawables);


            double dt = _dt * (double) showEvery; //in s
            double actualDt = deltaTime * 0.000000001;
            double ratio = dt / actualDt;
            ratio = round(ratio, 1);

            Render.consoleText("Time Ratio = " + String.valueOf(ratio) + " ");


            Render.show();

        }

        frame++;


    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}