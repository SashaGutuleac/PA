package org.example.homework12;

public class DummyClass {

    @MyTestAnnotation
    public void metodaFaraArgumente() {
        System.out.println("   [EXEC] -> 'metodaFaraArgumente' a fost apelata cu succes!");
    }

    @MyTestAnnotation
    public void metodaCuUnIntreg(int valoare) {
        System.out.println("   [EXEC] -> 'metodaCuUnIntreg' a fost apelata cu valoarea mock: " + valoare);
    }

    @MyTestAnnotation
    public void metodaCuString(String text) {
        System.out.println("   [EXEC] -> Asta NU trebuie sa fie apelata (are parametru String).");
    }

    public void metodaFaraAdnotare() {
        System.out.println("   [EXEC] -> Asta NU trebuie apelata (nu are adnotare).");
    }
}