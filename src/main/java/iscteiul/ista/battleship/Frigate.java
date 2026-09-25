/**
 * Representa uma Fragata no jogo Batalha Naval.
 */
package iscteiul.ista.battleship;

public class Frigate extends Ship {

    /** Tamanho da Fragata. */
    private static final Integer SIZE = 4;

    /** Nome da Fragata. */
    private static final String NAME = "Fragata";

    /**
     * Cria uma nova Fragata.
     *
     * @param bearing direção da Fragata
     * @param pos posição inicial da Fragata
     * @throws IllegalArgumentException se a direção for inválida
     */
    public Frigate(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Frigate.NAME, bearing, pos);

        switch (bearing) {
            case NORTH:
            case SOUTH:
                for (int r = 0; r < SIZE; r++)
                    getPositions().add(new Position(pos.getRow() + r, pos.getColumn()));
                break;

            case EAST:
            case WEST:
                for (int c = 0; c < SIZE; c++)
                    getPositions().add(new Position(pos.getRow(), pos.getColumn() + c));
                break;

            default:
                throw new IllegalArgumentException(
                    "ERROR! invalid bearing for thr frigate"
                );
        }
    }

    /**
     * Devolve o tamanho da Fragata.
     *
     * @return tamanho da Fragata
     */
    @Override
    public Integer getSize() {
        return Frigate.SIZE;
    }
}
