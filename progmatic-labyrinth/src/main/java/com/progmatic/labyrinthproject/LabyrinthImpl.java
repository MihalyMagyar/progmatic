package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.enums.CellType;
import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.exceptions.CellException;
import com.progmatic.labyrinthproject.exceptions.InvalidMoveException;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author pappgergely
 */
public class LabyrinthImpl implements Labyrinth {

    private HashMap<Coordinate, CellType> pos;
    private Coordinate currentPosition;
    private int height;
    private int width;
    private boolean finish;

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public CellType getCellType(Coordinate c) throws CellException {
        if(pos.get(c) != null){
            return pos.get(c);
        }

        throw new CellException(c, "Nem létező koordináta");
    }

    @Override
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void setCellType(Coordinate c, CellType type) throws CellException {
        if(type.equals(CellType.END)) finish = true;
        pos.put(c, type);
    }

    @Override
    public Coordinate getPlayerPosition() {
        return currentPosition;
    }

    @Override
    public boolean hasPlayerFinished() {
        return finish;
    }

    @Override
    public List<Direction> possibleMoves() {
        return null;
    }

    @Override
    public void movePlayer(Direction direction) throws InvalidMoveException {
        int rowMove = currentPosition.getRow();
        int colMove = currentPosition.getCol();

        switch (direction){
            case EAST:
                colMove += 1;
                break;
            case NORTH:
                rowMove -= 1;
                break;
            case WEST:
                colMove -= 1;
                break;
            case SOUTH:
                rowMove += 1;
                break;
        }

        if(colMove < 0 || colMove > getWidth() || rowMove < 0 || rowMove > getHeight()){
            throw new InvalidMoveException();
        }

        currentPosition = new Coordinate(colMove, rowMove);
    }

    public LabyrinthImpl() {
        height = -1;
        width = -1;
        pos = new HashMap<>();
        finish = false;
        currentPosition = new Coordinate(0,1);
    }

    @Override
    public void loadLabyrinthFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            int width = Integer.parseInt(sc.nextLine());
            int height = Integer.parseInt(sc.nextLine());

            for (int hh = 0; hh < height; hh++) {
                String line = sc.nextLine();
                for (int ww = 0; ww < width; ww++) {
                    switch (line.charAt(ww)) {
                        case 'W':

                            break;
                        case 'E':

                            break;
                        case 'S':

                            break;
                    }
                }
            }
        } catch (FileNotFoundException | NumberFormatException ex) {
            System.out.println(ex.toString());
        }
    }
}