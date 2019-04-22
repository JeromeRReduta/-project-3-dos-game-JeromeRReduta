public class DosCard extends Card {

    public DosCard(int faceValue, int suitValue) {
        super(faceValue, suitValue);
        setSuitName(suitValue);

    }

    public void setSuitName(int suitValue) {
        switch(suitValue) {

            case 5:
                super.setSuitName("ANY");
                break;

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
        }

    }

    public String toString() {
        if (super.getFace() < 0) {
            return super.getSuitName() + " #";
        }
        return super.getSuitName() + " " + super.getFace();
    }
}
