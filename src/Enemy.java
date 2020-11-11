import java.util.ArrayList;

class Enemy {

    private ArrayList<Integer> enemyShips = new ArrayList<>();

    public Enemy(int shipSize) {
        this.enemyShips = setShips(shipSize);
    }

    public ArrayList<Integer> getShips() {
        return this.enemyShips;
    }

    public ArrayList<Integer> setShips(int shipSize) {

        int gridSize = 49;
        int[] grid = new int[gridSize];
        int[] coords = new int[shipSize];
        int attempts = 0;
        int location;

        for (int shipCount = 1; shipCount <= 3; shipCount++) {

            boolean success = false;
            int gridLength = 7;
            int incr = (shipCount % 2) == 1 ? gridLength : -1;

            while (!success & attempts++ < 200) {

                location = (int) (Math.random() * gridSize);

                int x = 0;
                success = location != 0;
                while (success && x < shipSize) {

                    if (grid[location] == 0) {

                        coords[x++] = location;
                        location += incr;

                        if (location >= gridSize) {
                            success = false;
                        }

                        if ((x == 0 || x == 1) && (location % gridLength == 0)) {
                            success = false;
                        }
                    } else {
                        success = false;
                    }
                }
            }
            byte x = 0;
            while (x < shipSize) {
                grid[coords[x]] = 1;
                enemyShips.add(coords[x]);
                //System.out.println(coords[x]);
                x++;
            }
        }
        return enemyShips;
    }

    public int enemyChoise(int prevCell){
        int cell = 0;
        int direction = (int) (Math.random() * 4);
            if (prevCell != 50) {
                if( (direction == 0) && (prevCell % 7 != 0) ) {
                    cell = prevCell - 1;
                } else {
                    if ((direction == 1) && (prevCell % 7 != 6)) {
                        cell = prevCell + 1;
                    } else {
                        if ((direction == 2) && (prevCell < 42)) {
                            cell = prevCell + 7;
                        } else {
                            if ((direction == 3) && (prevCell > 6)) {
                                cell = prevCell - 7;
                            } else {
                                enemyChoise(prevCell);
                            }
                        }
                    }
                }
            } else {
                cell = (int) (Math.random() * 49);
            }
        return cell;
    }
}