public class Wydatek extends Transakcja {

    public Wydatek(double kwota, String opis, String kategoria) {
        super(kwota, opis, kategoria);
    }

    @Override
    public double getKwotaZnakowana() {
        return -kwota;
    }

    @Override
    public String toString() {
        return "[WYDATEK] " + super.toString();
    }
}
