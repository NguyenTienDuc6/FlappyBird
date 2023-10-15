package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

import java.util.ArrayList;

public class Bird extends BaseObject{  //Bird inherits BaseObject's propertise
    private ArrayList<Bitmap> arrBms = new ArrayList<>();
    private int count, vFlap, idCurrentBitmap;
        //for each interation we let count increase by 1 value
        // if count = vflap then we will change the bitmap of the bird
        //idCurrentBitmap is the Bitmap currently used in the Bitmap array(arrBms)
    private float drop;
    public Bird(){
        this.count = 0;
        this.vFlap = 5;
        this.idCurrentBitmap = 0;
        this.drop = 0;
    }
    public void  draw(Canvas canvas){
        drop();
        canvas.drawBitmap(this.getBm(), this.x, this.y, null); //draw it on the canvas
    }

    private void drop() {
        this.drop+=0.6;
        this.y+=this.drop;
    }

    public ArrayList<Bitmap> getArrBms() {
        return arrBms;
    }

    public void setArrBms(ArrayList<Bitmap> arrBms) {
        this.arrBms = arrBms;
        for (int i = 0; i < arrBms.size(); i++){ //scale those bitmap to the H and W of bird
            this.arrBms.set(i, Bitmap.createScaledBitmap(this.arrBms.get(i), this.width, this.height, true));
        }
    }

    @Override
    public Bitmap getBm() {

        count ++; // for each interation we let count increase by 1 value
        if(this.count == this.vFlap){ //if count = vflap then we will change the bitmap of the bird
            for (int i = 0; i < arrBms.size(); i++){
                if(i == arrBms.size()-1){
                    this.idCurrentBitmap = 0;
                    break;
                } else if (this.idCurrentBitmap == i) {
                    idCurrentBitmap = i+1;
                    break;

                }
            }
            count = 0;
        }
        if (this.drop<0){ //rotate bird when it soar and drop
            Matrix matrix = new Matrix();
            matrix.postRotate(-25);
            return Bitmap.createBitmap(arrBms.get(idCurrentBitmap), 0, 0, arrBms.get(idCurrentBitmap).getWidth(), arrBms.get(idCurrentBitmap).getHeight(), matrix, true);
        } else if (drop>=0) {
            Matrix matrix = new Matrix();
            if (drop < 70) {
                matrix.postRotate(-25 + (drop * 2));
            } else {
                matrix.postRotate(45);
            }
            return Bitmap.createBitmap(arrBms.get(idCurrentBitmap), 0, 0, arrBms.get(idCurrentBitmap).getWidth(), arrBms.get(idCurrentBitmap).getHeight(), matrix, true);
        }
        return  this.arrBms.get(idCurrentBitmap); //get the first Bitmap of the Biitmap array
    }//check bird flap the wing

    public float getDrop() {
        return drop;
    }

    public void setDrop(float drop) {
        this.drop = drop;
    }
}
