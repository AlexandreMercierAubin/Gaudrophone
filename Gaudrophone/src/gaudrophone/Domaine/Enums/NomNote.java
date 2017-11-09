package gaudrophone.Domaine.Enums;

public enum NomNote {
    C(0),
    CDiese(1),
    D(2),
    DDiese(3),
    E(4),
    F(5),
    FDiese(6),
    G(7),
    GDiese(8),
    A(9),
    ADiese(10),
    B(11);
    
    private int numNote;

    NomNote(int numNote) {
        this.numNote = numNote;
    }

    public int getNumNote() {
        return numNote;
    }
}
