package lab3;

public class Main {

    public static void main(String[] args) {

    PracownikEtatowy p1 = new PracownikEtatowy();
    PracownikEtatowy p2 = new PracownikEtatowy();
    PracownikEtatowy p3 = new PracownikEtatowy();

    p1.pesel = "11111111111";
    p1.wynagrodzenieBrutto = 2300.50;
    p2.pesel = "12345678912";
    p2.wynagrodzenieBrutto = 5000;
    p3.pesel = "11223344556";
    p3.wynagrodzenieBrutto = 5000;

    Student s1 = new Student();
    Student s2 = new Student();

    s1.pesel = "98989898981";
    s1.wynagrodzenieBrutto = 0;
    s2.pesel = "98765432198";
    s2.wynagrodzenieBrutto = 500;

    Kadry kadra = new Kadry();
    kadra.add(p1);
    kadra.add(p2);
    kadra.add(p3);
    kadra.add(s1);
    kadra.add(s2);

    Pracownik pracownik = kadra.find("11223344556");
    System.out.println(pracownik.pesel +"\t"+ pracownik.wynagrodzenieBrutto);
    kadra.delete(pracownik);

    kadra.sort();


    }
}
