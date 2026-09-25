/**

Representa as quatro direções cardeais da bússola e uma direção desconhecida.

<p>Cada direção está associada a um único carácter:
<ul>
<li>{@code n} - Norte</li>

<li>{@code s} - Sul</li>

<li>{@code e} - Este</li>

<li>{@code o} - Oeste</li>

<li>{@code u} - Desconhecida</li>

</ul>
@author fba
*/
package iscteiul.ista.battleship;

/**

Representa as possíveis direções da bússola utilizadas no jogo Battleship.
*/
public enum Compass {

/** Direção Norte, representada pelo carácter {@code 'n'}. */
NORTH('n'),

/** Direção Sul, representada pelo carácter {@code 's'}. */
SOUTH('s'),

/** Direção Este, representada pelo carácter {@code 'e'}. */
EAST('e'),

/** Direção Oeste, representada pelo carácter {@code 'o'}. */
WEST('o'),

/** Direção desconhecida ou inválida, representada pelo carácter {@code 'u'}. */
UNKNOWN('u');

/** Carácter associado a esta direção da bússola. */
private final char c;

/**

Cria uma direção da bússola associada ao carácter especificado.

@param c carácter que representa a direção da bússola
*/
Compass(char c) {
this.c = c;
}

/**

Devolve o carácter associado a esta direção da bússola.

@return carácter que representa a direção
*/
public char getDirection() {
return c;
}

/**

Devolve a representação textual desta direção da bússola.

@return carácter que representa a direção convertido para {@code String}
*/
@Override
public String toString() {
return "" + c;
}

/**

Converte um carácter para a respetiva direção da bússola.

<p>Caso o carácter não corresponda a nenhuma direção conhecida,
é devolvido {@link #UNKNOWN}.

@param ch carácter a converter

@return a direção correspondente ou {@link #UNKNOWN} caso o carácter

    não seja reconhecido

*/
static Compass charToCompass(char ch) {
Compass bearing;

 switch (ch) {
     case 'n':
         bearing = NORTH;
         break;
     case 's':
         bearing = SOUTH;
         break;
     case 'e':
         bearing = EAST;
         break;
     case 'o':
         bearing = WEST;
         break;
     default:
         bearing = UNKNOWN;
 }

 return bearing;

}
}
