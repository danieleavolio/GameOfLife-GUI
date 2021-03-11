import java.util.Random;

public class GameOfLife {

    int lunghezza = 1300/3;
    int larghezza = 800/3;

    String ticks = "50";
    String coloriVivi = "White";
    String coloriMorti = "Black";
    Boolean running = false;
    int countvivi = 0;
    int totali = lunghezza * larghezza;

    public int getTotali() {
        return totali;
    }

    public void setTotali(int totali) {
        this.totali = totali;
    }

    public Boolean getRunning() {
        return running;
    }

    public void setRunning(Boolean running) {
        this.running = running;
    }

    public int getCountvivi() {
        return countvivi;
    }

    public void setCountvivi(int countvivi) {
        this.countvivi = countvivi;
    }

    public String getTicks() {
        return ticks;
    }

    public void setTicks(String ticks) {
        this.ticks = ticks;
    }

    public String getColoriVivi() {
        return coloriVivi;
    }

    public void setColoriVivi(String coloriVivi) {
        this.coloriVivi = coloriVivi;
    }

    public String getColoriMorti() {
        return coloriMorti;
    }

    public void setColoriMorti(String coloriMorti) {
        this.coloriMorti = coloriMorti;
    }

    int [][] matrice;
    int [][] matriceDuplicazione;
    private static GameOfLife gameOfLife = null;
    public Random random = new Random();

    private GameOfLife(){
        matrice = new int[larghezza][lunghezza];
        matriceDuplicazione = new int[larghezza][lunghezza];
        
        //inizializzazione
        for (int i = 0; i < larghezza; i++) {
            for (int j = 0; j < lunghezza; j++) {
                matrice[i][j] = random.nextInt(2);
            }
        }
    }


    //Singleton
    public static GameOfLife getInstance(){
        if (gameOfLife == null){
            gameOfLife = new GameOfLife();
        }

        return gameOfLife;
    }

    public void reset(){
        for (int i = 0; i < larghezza; i++) {
            for (int j = 0; j < lunghezza; j++) {
                matrice[i][j] = random.nextInt(2);
            }
        }
    }
    public void controllo(){
        countvivi = 0;
        for (int i = 1; i < matrice.length-1; i++) {
            for (int j = 1; j < matrice[i].length-1; j++) {
                int vicini = 0;
                vicini =    matrice[i-1][j-1] + matrice[i-1][j] + matrice[i-1][j+1] +
                            matrice[i][j-1] + matrice[i][j+1] +
                            matrice[i+1][j-1] +  matrice[i+1][j] +  matrice[i+1][j+1];


                if (matrice[i][j] == 1){
                    countvivi++;
                    if (vicini < 2 || vicini > 3)
                        matriceDuplicazione[i][j] = 0;
                    if (vicini == 2 || vicini ==3)
                        matriceDuplicazione[i][j] = 1;
                }

                if (matrice[i][j] == 0){
                    if (vicini == 3)
                        matriceDuplicazione[i][j] = 1;
                }
            }
        }

        for (int i = 0; i < matrice.length; i++){
            for (int j = 0; j < matrice[i].length; j++) {
                matrice[i][j] = matriceDuplicazione[i][j];
            }
        }


    }


}
