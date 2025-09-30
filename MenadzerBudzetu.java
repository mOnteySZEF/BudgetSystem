import java.util.ArrayList;
import java.io.*;

public class MenadzerBudzetu {
    private ArrayList<Transakcja> transakcje;

    public MenadzerBudzetu() {
        transakcje = new ArrayList<>();
    }

    public void addTransakcje(Transakcja t) {
        transakcje.add(t);
    }

    public void usunTransakcje(int index) {
        if (index >= 0 && index < transakcje.size()) {
            transakcje.remove(index);
            System.out.println("Usunieto transakcje");
        } else {
            System.out.println("Zly nr transakcji");
        }
    }

    public double getSaldo() {
        double suma = 0;
        for (Transakcja t : transakcje) {
            suma += t.getKwotaZnakowana();
        }
        return suma;
    }

    public void getTransakcje() {
        if (transakcje.isEmpty()) {
            System.out.println("Brak");
            return;
        }
        for (int i = 0; i < transakcje.size(); i++) {
            System.out.println(i + ". " + transakcje.get(i));
        }
    }

    public void getTransakcjeKategoria(String kategoria) {
        boolean znaleziono = false;
        for (Transakcja t : transakcje) {
            if (t.getKategoria().equalsIgnoreCase(kategoria)) {
                System.out.println(t);
                znaleziono = true;
            }
        }
        if (!znaleziono) {
            System.out.println("brak transakcji");
        }
    }

    public void getNajwiekszyWydatek() {
        double max = -1;
        Transakcja najwiekszy = null;
        for (Transakcja t : transakcje) {
            if (t instanceof Wydatek && t.getKwota() > max) {
                max = t.getKwota();
                najwiekszy = t;
            }
        }
        if (najwiekszy != null) {
            System.out.println("Największy wydatek: " + najwiekszy);
        } else {
            System.out.println("Brak wydatkow");
        }
    }

    public void getNajwiekszyPrzychod() {
        double max = -1;
        Transakcja najwiekszy = null;
        for (Transakcja t : transakcje) {
            if (t instanceof Przychod && t.getKwota() > max) {
                max = t.getKwota();
                najwiekszy = t;
            }
        }
        if (najwiekszy != null) {
            System.out.println("Największy przychód: " + najwiekszy);
        } else {
            System.out.println("Brak przychodow");
        }
    }

    public void zapiszDane(String nazwaPliku) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nazwaPliku))) {
            writer.write("typ;kwota;opis;kategoria"); //schemacik
            writer.newLine();

            for (Transakcja t : transakcje) {
                String typ = (t instanceof Wydatek) ? "WYDATEK" : "PRZYCHOD";
                writer.write(typ + ";" + t.getKwota() + ";" + t.getOpis() + ";" + t.getKategoria());
                writer.newLine();
            }
            System.out.println("Zapisano dane");
        } catch (IOException e) {
            System.out.println("error");
        }
    }

    public void wczytajZPliku(String nazwaPliku) {
        transakcje.clear(); 
        File plik = new File(nazwaPliku);
        if (!plik.exists()) {
            System.out.println("plik nie istnieje");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(plik))) {
            String linia;
            while ((linia = reader.readLine()) != null) {
                linia = linia.trim();
                if (linia.isEmpty()) continue;

                String[] dane = linia.split(";");
                if (dane.length != 4) {
                    continue;
                }
                if (dane[0].equalsIgnoreCase("typ") || dane[1].equalsIgnoreCase("kwota")) {
                    continue;
                }

                String typ = dane[0].trim();
                String kwotaStr = dane[1].trim().replace(",", ".");
                double kwota;
                try {
                    kwota = Double.parseDouble(kwotaStr);
                } catch (NumberFormatException e) {
                    continue;
                }
                String opis = dane[2].trim();
                String kategoria = dane[3].trim();
                if (typ.equalsIgnoreCase("WYDATEK") || typ.equalsIgnoreCase("W")) {
                    transakcje.add(new Wydatek(kwota, opis, kategoria));
                } else if (typ.equalsIgnoreCase("PRZYCHOD") || typ.equalsIgnoreCase("P")) {
                    transakcje.add(new Przychod(kwota, opis, kategoria));
                }
            }
            System.out.println("Wczytano dane");
        } catch (IOException e) {
            System.out.println("error");
        }
    }
}
