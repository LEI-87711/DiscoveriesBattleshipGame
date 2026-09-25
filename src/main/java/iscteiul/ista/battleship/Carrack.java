/**
 * Representa uma Nau no jogo Batalha Naval.
 */
package iscteiul.ista.battleship;

public class Carrack extends Ship {

    /** Tamanho da Nau. */
    private static final Integer SIZE = 3;

    /** Nome da Nau. */
    private static final String NAME = "Nau";

    /**
     * Cria uma nova Nau.
     *
     * @param bearing direção da Nau
     * @param pos posição inicial da Nau
     * @throws IllegalArgumentException se a direção for inválida
     */
    public Carrack(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Carrack.NAME, bearing, pos);

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
                    "ERROR! invalid bearing for the carrack"
                );
        }
    }

    /**
     * Devolve o tamanho da Nau.
     *
     * @return tamanho da Nau
     */
    @Override
    public Integer getSize() {
        return Carrack.SIZE;
    }
}
