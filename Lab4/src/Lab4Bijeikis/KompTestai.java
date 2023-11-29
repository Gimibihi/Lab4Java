package Lab4Bijeikis;

import laborai.studijosktu.HashType;
import laborai.studijosktu.Ks;
import laborai.studijosktu.MapKTUx;

import java.util.Locale;

public class KompTestai {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        atvaizdzioTestas();
    }
    public static void atvaizdzioTestas(){
        Kompiuteris komp1 = new Kompiuteris("Intel","i5",16,256,8000,240.24);
        Kompiuteris komp2 = new Kompiuteris("AMD","Ryzen 5",32,1024,12000,985.99);
        Kompiuteris komp3 = new Kompiuteris("Apple;M1;16;512;4000;1500.99");
        Kompiuteris komp4 = new Kompiuteris.Builder().buildRandom();
        Kompiuteris komp5 = new Kompiuteris("Qualcomm;Snapdragon 8;8;128;1500;400");
        Kompiuteris komp6 = new Kompiuteris("Intel;i7;128;2048;21000;1799.99");

        String[] kompID = {"K001","K002","K003","K004","K005","K006","K007"};
        int id=0;
        MapKTUx<String, Kompiuteris> atvaizdis = new MapKTUx<>(new String(), new Kompiuteris(), HashType.DIVISION);
        Kompiuteris[] komp = {komp1, komp2, komp3, komp4, komp5, komp6};
        for(Kompiuteris k : komp){
            atvaizdis.put(kompID[id++], k);
        }
        atvaizdis.println("Porų išsidėstymas atvaizdyje pagal raktus");
        Ks.oun("Ar egzistuoja pora atvaizdyje?");
        Ks.oun(atvaizdis.contains(kompID[4]));
        Ks.oun(atvaizdis.contains(kompID[6]));
        Ks.oun("Pasalinamos poros is atvaizdzio");
        Ks.oun(atvaizdis.remove(kompID[1]));
        Ks.oun(atvaizdis.remove(kompID[6]));
        atvaizdis.println("Porų išsidėstymas atvaizdyje pagal raktus");
        Ks.oun("Atliekame porų paiešką atvaizdyje:");
        Ks.oun(atvaizdis.get(kompID[2]));
        Ks.oun(atvaizdis.get(kompID[6]));
        Ks.oun("Išspausdiname atvaizdžio poras String eilute:");
        Ks.oun("Koks vidutinis ilgis");
        Ks.oun(atvaizdis.getAverageChainSize());
        Ks.oun("Kiek yra tusciu grandiniu");
        Ks.oun(atvaizdis.getAmountEmptyChains());
        Ks.ounn(atvaizdis);
    }
}
