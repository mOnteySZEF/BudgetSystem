public class Main {
    public static void main(String[] args) {
        MenadzerBudzetu menadzer = new MenadzerBudzetu();
        menadzer.wczytajZPliku("budzet.txt");

        boolean dziala = true;

        while (dziala) {
            System.out.println("\n============= SYSTEM BUDZETU =============");
            System.out.println("1. Dodaj wydatek");
            System.out.println("2. Dodaj przychód");
            System.out.println("3. Pokaż wszystkie transakcje");
            System.out.println("4. Pokaż saldo");
            System.out.println("5. Usuń transakcje");
            System.out.println("6. Filtruj po kategorii");
            System.out.println("7. Największy wydatek");
            System.out.println("8. Największy przychod");
            System.out.println("9. Zapisz dane");
            System.out.println("10. Wyjście");

            int wybor = InputHelper.getInt("Wybierz opcję: ");

            switch (wybor) {
                case 1:
                    double kwotaW = InputHelper.getDouble("Kwota wydatku: ");
                    String opisW = InputHelper.getString("Opis wydatku: ");
                    String katW = InputHelper.getString("Kategoria wydatku: ");
                    menadzer.addTransakcje(new Wydatek(kwotaW, opisW, katW));
                    break;

                case 2:
                    double kwotaP = InputHelper.getDouble("Kwota przychodu: ");
                    String opisP = InputHelper.getString("Opis przychodu: ");
                    String katP = InputHelper.getString("Kategoria przychodu: ");
                    menadzer.addTransakcje(new Przychod(kwotaP, opisP, katP));
                    break;

                case 3:
                    menadzer.getTransakcje();
                    break;

                case 4:
                    System.out.println("Saldo: " + menadzer.getSaldo() + " PLN");
                    break;

                case 5:
                    menadzer.getTransakcje();
                    int index = InputHelper.getInt("Podaj nr transakcji do usunięcia: ");
                    menadzer.usunTransakcje(index);
                    break;

                case 6:
                    String kat = InputHelper.getString("Podaj kategorie: ");
                    menadzer.getTransakcjeKategoria(kat);
                    break;

                case 7:
                    menadzer.getNajwiekszyWydatek();
                    break;

                case 8:
                    menadzer.getNajwiekszyPrzychod();
                    break;

                case 9:
                    menadzer.zapiszDane("budzet.txt");
                    break;

                case 10:
                    menadzer.zapiszDane("budzet.txt");
                    dziala = false;
                    System.out.println("zegnam");
                    break;

                default:
                    System.out.println("zle wybrales opcje!");
            }
        }
    }
}
