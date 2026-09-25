package iscteiul.ista.battleship;

/**
 * Representa uma fragata no jogo da Batalha Naval.
 * <p>
 * A fragata é um navio com {@value #SIZE} posições, colocado na horizontal
 * ou na vertical a partir de uma posição inicial, consoante a orientação
 * indicada.
 *
 * @see Ship
 */
public class Frigate extends Ship {

    /** Número de posições ocupadas por uma fragata. */
    private static final Integer SIZE = 4;

    /** Nome do navio. */
    private static final String NAME = "Fragata";

    /**
     * Cria uma fragata com a orientação e a posição inicial indicadas.
     * <p>
     * Se a orientação for {@link Compass#NORTH} ou {@link Compass#SOUTH}, a
     * fragata ocupa posições consecutivas na mesma coluna, a partir de
     * {@code pos} e em linhas crescentes. Se for {@link Compass#EAST} ou
     * {@link Compass#WEST}, ocupa posições consecutivas na mesma linha, em
     * colunas crescentes.
     *
     * @param bearing orientação da fragata; não pode ser {@code null}
     * @param pos     posição inicial a partir da qual a fragata é colocada;
     *                não pode ser {@code null}
     * @throws NullPointerException     se {@code bearing} ou {@code pos} forem {@code null}
     * @throws IllegalArgumentException se {@code bearing} não for uma orientação válida
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
                throw new IllegalArgumentException("ERROR! invalid bearing for the frigate");
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return o tamanho da fragata, sempre {@value #SIZE}
     */
    @Override
    public Integer getSize() {
        return Frigate.SIZE;
    }
}
