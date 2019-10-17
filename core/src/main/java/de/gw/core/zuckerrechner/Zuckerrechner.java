package de.gw.core.zuckerrechner;

public class Zuckerrechner {

    public String concatKhFpe(double kh,double fpe){
        return String.format("%s %s",khString(kh),fpeString(fpe));
    }

    public String khString(double kh){
        return String.format("Bitte für  %.2f sofort und",kh/12);
    }

    public String fpeString(double fpe){
        String ergStr =  "Bitte für %.2f, verzögert auf %s Std. Spritzen";
        int hours =0;
        if(fpe<=1){
            return "kein verzögertes Insolin nötig";
        }else if(fpe >1 && fpe <=2){
            return String.format(ergStr, fpe,"4");
        }else if(fpe >2 && fpe <3){
            return String.format(ergStr, fpe,"5");
        }else{
            return  String.format(ergStr, fpe,"6-8");
        }
    }

    public double calcFPE(double fett, double eiweis){
        return fett/11+eiweis/25;
    }
}
