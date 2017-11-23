package com.linhnt.speedshoot.main;
import com.linhnt.speedshoot.bases.Vector2D;

import java.lang.Math;
import java.util.Scanner;

public class Test {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        Vector2D a = new Vector2D(0.5f, 0.865f);
        Vector2D b = new Vector2D(-1.4f, 1.4f);
        Vector2D c = new Vector2D(-0.865f, -0.5f);
        Vector2D d = new Vector2D(0.5f, -0.865f);

        System.out.println(a.getDegreeAngle());
        System.out.println(b.getDegreeAngle());
        System.out.println(c.getDegreeAngle());
        System.out.println(d.getDegreeAngle());
    }
}
