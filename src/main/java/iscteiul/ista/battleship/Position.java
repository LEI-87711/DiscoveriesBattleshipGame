package iscteiul.ista.battleship;

import java.util.Objects;

/**
 * Representa uma posição (célula) do tabuleiro da Batalha Naval.
 * <p>
 * Uma posição é identificada pela linha e pela coluna, e guarda ainda se
 * está ocupada por um navio e se já foi atingida por um tiro.
 * Duas posições
 * são consideradas iguais se tiverem a mesma linha e a mesma coluna,
 * independentemente do seu estado.
 *
 * @see IPosition
 */
public class Position implements IPosition {

    /** Linha da posição no tabuleiro. */
    private int row;

    /** Coluna da posição no tabuleiro. */
    private int column;

    /** Indica se a posição está ocupada por um navio. */
    private boolean isOccupied;

    /** Indica se a posição já foi atingida por um tiro. */
    private boolean isHit;

    /**
     * Cria uma posição na linha e coluna indicadas, inicialmente livre e
     * não atingida.
     *
     * @param row    linha da posição
     * @param column coluna da posição
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
        this.isOccupied = false;
        this.isHit = false;
    }

    /**
     * {@inheritDoc}
     *
     * @return a linha da posição
     */
    @Override
    public int getRow() {
        return row;
    }

    /**
     * {@inheritDoc}
     *
     * @return a coluna da posição
     */
    @Override
    public int getColumn() {
        return column;
    }

    /**
     * Calcula o código de dispersão (hash) da posição.
     *
     * @return o código de dispersão da posição
     */
    @Override
    public int hashCode() {
        return Objects.hash(column, isHit, isOccupied, row);
    }

    /**
     * Compara esta posição com outro objeto.
     * <p>
     * Duas posições são iguais se tiverem a mesma linha e a mesma coluna; o
     * facto de estarem ocupadas ou atingidas não é tido em conta.
     *
     * @param otherPosition objeto a comparar com esta posição
     * @return {@code true} se {@code otherPosition} for uma {@link IPosition}
     *         com a mesma linha e coluna, {@code false} caso contrário
     */
    @Override
    public boolean equals(Object otherPosition) {
        if (this == otherPosition)
            return true;
        if (otherPosition instanceof IPosition) {
            IPosition other = (IPosition) otherPosition;
            return (this.getRow() == other.getRow() && this.getColumn() == other.getColumn());
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * Considera adjacentes as posições que diferem no máximo uma linha e uma
     * coluna, incluindo as diagonais e a própria posição.
     *
     * @param other posição a comparar
     * @return {@code true} se {@code other} for adjacente a esta posição,
     *         {@code false} caso contrário
     */
    @Override
    public boolean isAdjacentTo(IPosition other) {
        return (Math.abs(this.getRow() - other.getRow()) <= 1 && Math.abs(this.getColumn() - other.getColumn()) <= 1);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Marca a posição como ocupada por um navio.
     */
    @Override
    public void occupy() {
        isOccupied = true;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Marca a posição como atingida por um tiro.
     */
    @Override
    public void shoot() {
        isHit = true;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code true} se a posição estiver ocupada por um navio,
     *         {@code false} caso contrário
     */
    @Override
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code true} se a posição já tiver sido atingida,
     *         {@code false} caso contrário
     */
    @Override
    public boolean isHit() {
        return isHit;
    }

    /**
     * Devolve uma representação textual da posição.
     *
     * @return texto no formato {@code "Linha = <linha> Coluna = <coluna>"}
     */
    @Override
    public String toString() {
        return ("Linha = " + row + " Coluna = " + column);
    }
}