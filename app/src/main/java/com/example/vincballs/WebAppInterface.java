package com.example.vincballs;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class WebAppInterface {
    Context mContext;
    ArrayList<String> coefficientsList = new ArrayList<String>();

    /** Instantiate the interface and set the context */
    WebAppInterface(Context c, ArrayList<ArrayList<DiscreteFourierTransform.FourierCoefficient>> coefficientsList) {
        mContext = c;
        for(ArrayList<DiscreteFourierTransform.FourierCoefficient> coefficients: coefficientsList){
            Gson gson = new Gson();
            this.coefficientsList.add(gson.toJson(coefficients));
        }
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public String getCoefficientsList(){
        Gson gson = new Gson();
        return gson.toJson(this.coefficientsList);
    }
}
