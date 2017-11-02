package gaudrophone.Domaine.Enums;

public enum NomNote {
    Do(0),
    DoDiese(1),
    Re(2),
    ReDiese(3),
    Mi(4),
    Fa(5),
    FaDiese(6),
    Sol(7),
    SolDiese(8),
    La(9),
    LaDiese(10),
    Si(11);
    
    private int numNote;

    NomNote(int numNote) {
        this.numNote = numNote;
    }

    public int getNumNote() {
        return numNote;
    }
}
