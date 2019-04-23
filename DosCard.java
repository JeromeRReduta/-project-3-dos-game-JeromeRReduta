// DosCard.java

public class DosCard extends Card {
    // Constructor
    public DosCard(int faceValue, int suitValue) {
        super(faceValue, suitValue);
        setSuitName(suitValue);

    }

    // Sets suit name to designated color
    public void setSuitName(int suitValue) {
        switch(suitValue) {
            case 1:
                super.setSuitName("BLUE");
                break;

            case 2:
                super.setSuitName("GREEN");
                break;

            case 3:
                super.setSuitName("RED");
                break;

            case 4:
                super.setSuitName("YELLOW");
                break;

            // Case: Wild color card
            case 5:
                super.setSuitName("ANY");
                break;
        }

    }

    public String toString() {

        // Case: Wild # card
        if (super.getFace() < 0) {
            return super.getSuitName() + " #";
        }

        // Case: All others
        return super.getSuitName() + " " + super.getFace();
    }
}
