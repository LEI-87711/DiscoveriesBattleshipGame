package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um jogo da Batalha Naval disputado contra uma frota.
 * <p>
 * Regista os tiros disparados e mantém estatísticas sobre o jogo: tiros
 * inválidos (fora do tabuleiro), tiros repetidos, acertos e navios afundados.
 * Permite ainda imprimir na consola o tabuleiro com os tiros efetuados ou com
 * a disposição da frota.
 *
 * @author fba
 * @see IGame
 * @see IFleet
 */
public class Game implements IGame {

    /** Frota contra a qual os tiros são disparados. */
    private IFleet fleet;

    /** Tiros válidos e não repetidos, pela ordem em que foram disparados. */
    private List<IPosition> shots;

    /** Número de tiros disparados para fora do tabuleiro. */
    private Integer countInvalidShots;

    /** Número de tiros disparados para posições já atingidas anteriormente. */
    private Integer countRepeatedShots;

    /** Número de tiros que acertaram num navio. */
    private Integer countHits;

    /** Número de navios afundados. */
    private Integer countSinks;

    /**
     * Cria um novo jogo contra a frota indicada, sem tiros disparados.
     *
     * @param fleet frota contra a qual o jogo é disputado
     */
    public Game(IFleet fleet) {
        shots = new ArrayList<>();
        countInvalidShots = 0;
        countRepeatedShots = 0;
        countHits = 0;
        countSinks = 0;
        this.fleet = fleet;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Um tiro fora do tabuleiro é contabilizado como inválido e um tiro numa
     * posição já atingida é contabilizado como repetido; em ambos os casos não
     * tem mais nenhum efeito. Um tiro válido e novo é registado e, se atingir
     * um navio, conta como acerto.
     *
     * @param pos posição para onde o tiro é disparado
     * @return o navio afundado por este tiro, ou {@code null} se o tiro não
     *         afundou nenhum navio
     */
    @Override
    public IShip fire(IPosition pos) {
        if (!validShot(pos))
            countInvalidShots++;
        else { // valid shot!
            if (repeatedShot(pos))
                countRepeatedShots++;
            else {
                shots.add(pos);
                IShip s = fleet.shipAt(pos);
                if (s != null) {
                    s.shoot(pos);
                    countHits++;
                    if (!s.stillFloating()) {
                        countSinks++;
                        return s;
                    }
                }
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @return lista dos tiros válidos e não repetidos, pela ordem em que foram
     *         disparados (a lista devolvida é a interna, não uma cópia)
     */
    @Override
    public List<IPosition> getShots() {
        return shots;
    }

    /**
     * {@inheritDoc}
     *
     * @return número de tiros disparados para posições já atingidas
     */
    @Override
    public int getRepeatedShots() {
        return this.countRepeatedShots;
    }

    /**
     * {@inheritDoc}
     *
     * @return número de tiros disparados para fora do tabuleiro
     */
    @Override
    public int getInvalidShots() {
        return this.countInvalidShots;
    }

    /**
     * {@inheritDoc}
     *
     * @return número de tiros que acertaram num navio
     */
    @Override
    public int getHits() {
        return this.countHits;
    }

    /**
     * {@inheritDoc}
     *
     * @return número de navios afundados até ao momento
     */
    @Override
    public int getSunkShips() {
        return this.countSinks;
    }

    /**
     * {@inheritDoc}
     *
     * @return número de navios da frota que ainda flutuam
     */
    @Override
    public int getRemainingShips() {
        List<IShip> floatingShips = fleet.getFloatingShips();
        return floatingShips.size();
    }

    /**
     * Verifica se uma posição está dentro dos limites do tabuleiro.
     *
     * @param pos posição a verificar
     * @return {@code true} se a posição estiver dentro do tabuleiro,
     *         {@code false} caso contrário
     */
    private boolean validShot(IPosition pos) {
        return pos.getRow() >= 0 && pos.getRow() < Fleet.BOARD_SIZE
                && pos.getColumn() >= 0 && pos.getColumn() < Fleet.BOARD_SIZE;
    }

    /**
     * Verifica se já foi disparado um tiro para a posição indicada.
     *
     * @param pos posição a verificar
     * @return {@code true} se a posição já tiver sido atingida,
     *         {@code false} caso contrário
     */
    private boolean repeatedShot(IPosition pos) {
        for (int i = 0; i < shots.size(); i++)
            if (shots.get(i).equals(pos))
                return true;
        return false;
    }

    /**
     * Imprime o tabuleiro na consola, assinalando as posições indicadas.
     * <p>
     * As posições da lista são marcadas com {@code marker} e as restantes
     * com {@code '.'}.
     *
     * @param positions posições a assinalar no tabuleiro
     * @param marker    carácter usado para assinalar as posições
     */
    public void printBoard(List<IPosition> positions, Character marker) {
        char[][] map = new char[Fleet.BOARD_SIZE][Fleet.BOARD_SIZE];

        for (int r = 0; r < Fleet.BOARD_SIZE; r++)
            for (int c = 0; c < Fleet.BOARD_SIZE; c++)
                map[r][c] = '.';

        for (IPosition pos : positions)
            map[pos.getRow()][pos.getColumn()] = marker;

        for (int row = 0; row < Fleet.BOARD_SIZE; row++) {
            for (int col = 0; col < Fleet.BOARD_SIZE; col++)
                System.out.print(map[row][col]);
            System.out.println();
        }
    }

    /**
     * Imprime o tabuleiro na consola com os tiros válidos já disparados,
     * assinalados com {@code 'X'}.
     *
     * @see #printBoard(List, Character)
     */
    @Override
    public void printValidShots() {
        printBoard(getShots(), 'X');
    }

    /**
     * Imprime o tabuleiro na consola com as posições ocupadas pelos navios
     * da frota, assinaladas com {@code '#'}.
     *
     * @see #printBoard(List, Character)
     */
    @Override
    public void printFleet() {
        List<IPosition> shipPositions = new ArrayList<IPosition>();

        for (IShip s : fleet.getShips())
            shipPositions.addAll(s.getPositions());

        printBoard(shipPositions, '#');
    }

    /**
     * Imprime o tabuleiro do jogador, mostrando a disposição da frota.
     *
     * @param fleet frota a imprimir no tabuleiro
     */
    @Override
    public void printMyBoard(IFleet fleet) {
        BoardPrinter.printPlayerBoard(fleet);
    }

    /**
     * Imprime o tabuleiro do adversário, mostrando os tiros válidos já efetuados.
     */
    @Override
    public void printOpponentBoard() {
        BoardPrinter.print(getShots(), 'X', "TABULEIRO DO ADVERSÁRIO (conhecido)");
    }
}