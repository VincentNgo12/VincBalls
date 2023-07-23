package com.example.vincballs;


import android.util.Log;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class Sketch extends PApplet {
    private final int screenWidth = 900;
    private final int screenHeight = 1500;
    private ArrayList<ArrayList<PVector>> drawing = new ArrayList<ArrayList<PVector>>();
    private ArrayList<PVector> currentDrawing = new ArrayList<PVector>();
    private String state = "Draw";


    public void mousePressed(){
        currentDrawing = new ArrayList<PVector>();
        state = "Draw";
    }

    public void mouseReleased(){
        drawing.add(currentDrawing);
        state = "Done";
    }


    public void settings() {
        size(screenWidth, screenHeight);
    }

    public void setup() {

    }

    public void draw() {
        background(0);
        if (state.equals("Draw")) {
            PVector point = new PVector(mouseX-screenWidth/2,mouseY-screenHeight/2);
            currentDrawing.add(point);
            noFill();
            stroke(255);
            strokeWeight(4);

            //Draw current drawing
            beginShape();
            for (PVector vector: currentDrawing){
                vertex(vector.x+screenWidth/2,vector.y+screenHeight/2);
            }
            endShape(CLOSE);

            for(ArrayList<PVector> path : drawing){
                beginShape();
                for (PVector vector: path){
                    vertex(vector.x+screenWidth/2,vector.y+screenHeight/2);
                }
                endShape(CLOSE);
            }

        }else{
            //Log.d("Drawing ArrayList", drawing.toString());
            //fill(255);
            beginShape();
            for(ArrayList<PVector> path : drawing){
                beginShape();
                for (PVector vector: path){
                    vertex(vector.x+screenWidth/2,vector.y+screenHeight/2);
                }
                endShape(CLOSE);
            }
        }
    }

    public ArrayList<ArrayList<PVector>> getDrawing() {
        return drawing;
    }

    public void onReset(){
        drawing = new ArrayList<ArrayList<PVector>>();
    }
}

