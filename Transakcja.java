public abstract class Transakcja {
    protected double kwota;
    protected String opis;
    protected String kategoria;

    public Transakcja(double kwota, String opis, String kategoria) {
        this.kwota = kwota;
        this.opis = opis;
        this.kategoria = kategoria;
    }

    public double getKwota() {
        return kwota;
    }

    public String getOpis() {
        return opis;
    }

    public String getKategoria() {
        return kategoria;
    }

    public abstract double getKwotaZnakowana();

    @Override
    public String toString() {
        return opis + " | " + kwota + " PLN | kategoria: " + kategoria;
    }
}
