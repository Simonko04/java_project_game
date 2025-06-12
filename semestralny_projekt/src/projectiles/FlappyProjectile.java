package projectiles;

import java.util.Random;

/**
 * stĺpy pri flappy birdovi
 */
public class FlappyProjectile extends Projectile{
    private int upper_border;
    public FlappyProjectile(int x) {
        super();
        this.projectile_x = x;
        this.upper_border = upper_generate();
    }

    /**
     * v tejto methode sa nastavuje pozicia stlpu - urcuje sa dolna hranica horneho obdlznika
     * @return
     */
    private int upper_generate() {
        Random rand = new Random();
        return rand.nextInt(4)+2;
    }

    public int getUpper_border() {
        return upper_border;
    }

    /**
     * pri flabbybirdovi sa overuje, ci hrac nenabural
     * @param y aby bola znama y-nova suradnica hraca
     * @param tileSize aby bolo jasne, ze aka je medze pre prienik hraca
     * @return
     */
    public boolean collision(int y, int tileSize){
        if (y < (4 * tileSize + upper_border*tileSize/2)|| y > (4 * tileSize + upper_border*tileSize/2 + tileSize - tileSize/4)){
            return true;
        }
        return false;
    }

}