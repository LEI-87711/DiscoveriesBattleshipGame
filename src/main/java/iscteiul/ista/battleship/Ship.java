
/** Nome da categoria correspondente ao galeão. */
private static final String GALEAO = "galeao";

/** Nome da categoria correspondente à fragata. */
private static final String FRAGATA = "fragata";

/** Nome da categoria correspondente à nau. */
private static final String NAU = "nau";

/** Nome da categoria correspondente à caravela. */
private static final String CARAVELA = "caravela";

/** Nome da categoria correspondente à barca. */
private static final String BARCA = "barca";

/**
 * Cria um navio do tipo especificado.
 *
 * <p>O tipo de navio determina qual das subclasses concretas será
 * instanciada.</p>
 *
 * @param shipKind tipo de navio a criar
 * @param bearing orientação do navio
 * @param pos posição inicial do navio
 * @return o navio correspondente ao tipo especificado ou {@code null}
 *         caso o tipo não seja reconhecido
 */
static Ship buildShip(String shipKind, Compass bearing, Position pos) {
    Ship s;
    switch (shipKind) {
        case BARCA:
            s = new Barge(bearing, pos);
            break;
        case CARAVELA:
            s = new Caravel(bearing, pos);
            break;
        case NAU:
            s = new Carrack(bearing, pos);
            break;
        case FRAGATA:
            s = new Frigate(bearing, pos);
            break;
        case GALEAO:
            s = new Galleon(bearing, pos);
            break;
        default:
            s = null;
    }
    return s;
}

/** Categoria do navio. */
private String category;

/** Orientação do navio. */
private Compass bearing;

/** Posição inicial do navio. */
private IPosition pos;

/** Lista de posições ocupadas pelo navio. */
protected List<IPosition> positions;

/**
 * Constrói um navio com a categoria, orientação e posição especificadas.
 *
 * @param category categoria do navio
 * @param bearing orientação do navio
 * @param pos posição inicial do navio
 */
public Ship(String category, Compass bearing, IPosition pos) {
    assert bearing != null;
    assert pos != null;

    this.category = category;
    this.bearing = bearing;
    this.pos = pos;
    positions = new ArrayList<>();
}

/**
 * Devolve a categoria do navio.
 *
 * @return categoria do navio
 */
@Override
public String getCategory() {
    return category;
}

/**
 * Devolve a lista de posições ocupadas pelo navio.
 *
 * @return lista de posições ocupadas pelo navio
 */
public List<IPosition> getPositions() {
    return positions;
}

/**
 * Devolve a posição inicial do navio.
 *
 * @return posição inicial do navio
 */
@Override
public IPosition getPosition() {
    return pos;
}

/**
 * Devolve a orientação do navio.
 *
 * @return orientação do navio
 */
@Override
public Compass getBearing() {
    return bearing;
}

/**
 * Verifica se o navio ainda está a flutuar.
 *
 * <p>Um navio considera-se a flutuar enquanto existir pelo menos uma
 * posição que ainda não tenha sido atingida.</p>
 *
 * @return {@code true} se o navio ainda estiver a flutuar;
 *         {@code false} caso todas as suas posições tenham sido atingidas
 */
@Override
public boolean stillFloating() {
    for (int i = 0; i < getSize(); i++)
        if (!getPositions().get(i).isHit())
            return true;
    return false;
}

/**
 * Devolve a linha da posição mais acima ocupada pelo navio.
 *
 * @return índice da linha mais acima ocupada pelo navio
 */
@Override
public int getTopMostPos() {
    int top = getPositions().get(0).getRow();
    for (int i = 1; i < getSize(); i++)
        if (getPositions().get(i).getRow() < top)
            top = getPositions().get(i).getRow();
    return top;
}

/**
 * Devolve a linha da posição mais abaixo ocupada pelo navio.
 *
 * @return índice da linha mais abaixo ocupada pelo navio
 */
@Override
public int getBottomMostPos() {
    int bottom = getPositions().get(0).getRow();
    for (int i = 1; i < getSize(); i++)
        if (getPositions().get(i).getRow() > bottom)
            bottom = getPositions().get(i).getRow();
    return bottom;
}

/**
 * Devolve a coluna da posição mais à esquerda ocupada pelo navio.
 *
 * @return índice da coluna mais à esquerda ocupada pelo navio
 */
@Override
public int getLeftMostPos() {
    int left = getPositions().get(0).getColumn();
    for (int i = 1; i < getSize(); i++)
        if (getPositions().get(i).getColumn() < left)
            left = getPositions().get(i).getColumn();
    return left;
}

/**
 * Devolve a coluna da posição mais à direita ocupada pelo navio.
 *
 * @return índice da coluna mais à direita ocupada pelo navio
 */
@Override
public int getRightMostPos() {
    int right = getPositions().get(0).getColumn();
    for (int i = 1; i < getSize(); i++)
        if (getPositions().get(i).getColumn() > right)
            right = getPositions().get(i).getColumn();
    return right;
}

/**
 * Verifica se o navio ocupa a posição indicada.
 *
 * @param pos posição a verificar
 * @return {@code true} se o navio ocupar a posição indicada;
 *         {@code false} caso contrário
 */
@Override
public boolean occupies(IPosition pos) {
    assert pos != null;

    for (int i = 0; i < getSize(); i++)
        if (getPositions().get(i).equals(pos))
            return true;
    return false;
}

/**
 * Verifica se este navio está demasiado próximo de outro navio.
 *
 * <p>Um navio está demasiado próximo de outro se pelo menos uma das
 * suas posições estiver adjacente a uma posição do outro navio.</p>
 *
 * @param other outro navio a verificar
 * @return {@code true} se os navios estiverem demasiado próximos;
 *         {@code false} caso contrário
 */
@Override
public boolean tooCloseTo(IShip other) {
    assert other != null;

    Iterator<IPosition> otherPos = other.getPositions().iterator();
    while (otherPos.hasNext())
        if (tooCloseTo(otherPos.next()))
            return true;

    return false;
}

/**
 * Verifica se o navio está demasiado próximo da posição indicada.
 *
 * @param pos posição a verificar
 * @return {@code true} se alguma posição do navio for adjacente à
 *         posição indicada; {@code false} caso contrário
 */
@Override
public boolean tooCloseTo(IPosition pos) {
    for (int i = 0; i < this.getSize(); i++)
        if (getPositions().get(i).isAdjacentTo(pos))
            return true;
    return false;
}

/**
 * Dispara sobre a posição indicada.
 *
 * <p>Se a posição indicada pertencer ao navio, essa posição é marcada
 * como atingida.</p>
 *
 * @param pos posição sobre a qual disparar
 */
@Override
public void shoot(IPosition pos) {
    assert pos != null;

    for (IPosition position : getPositions()) {
        if (position.equals(pos))
            position.shoot();
    }
}

/**
 * Devolve uma representação textual do navio.
 *
 * @return representação textual contendo a categoria, orientação
 *         e posição inicial do navio
 */
@Override
public String toString() {
    return "[" + category + " " + bearing + " " + pos + "]";
}

