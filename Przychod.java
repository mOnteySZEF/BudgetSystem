public class Przychod extends Transakcja {

    public Przychod(double kwota, String opis, String kategoria) {
        super(kwota, opis, kategoria);
    }

    @Override
    public double getKwotaZnakowana() {
        return kwota;
    }

    @Override
    public String toString() {
        return "[PRZYCHÓD] " + super.toString();
    }
}
