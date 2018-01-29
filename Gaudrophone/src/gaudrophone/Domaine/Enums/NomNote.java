package gaudrophone.Domaine.Enums;

public enum NomNote {
    C(0),
    CSharp(1),
    D(2),
    DSharp(3),
    E(4),
    F(5),
    FSharp(6),
    G(7),
    GSharp(8),
    A(9),
    ASharp(10),
    B(11);
    
    private final int numNote;

    NomNote(int numNote) {
        this.numNote = numNote;
    }

    public int getNumNote() {
        return numNote;
    }
}
