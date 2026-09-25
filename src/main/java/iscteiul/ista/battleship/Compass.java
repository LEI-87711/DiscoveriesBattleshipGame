/**

Represents the four cardinal compass directions and an unknown direction.

<p>Each direction is associated with a single character:
<ul>
<li>{@code n} - North</li>

<li>{@code s} - South</li>

<li>{@code e} - East</li>

<li>{@code o} - West</li>

<li>{@code u} - Unknown</li>

</ul>
@author fba
*/
package iscteiul.ista.battleship;

/**

Represents the possible compass directions used in the Battleship game.
*/
public enum Compass {

/** North direction, represented by {@code 'n'}. */
NORTH('n'),

/** South direction, represented by {@code 's'}. */
SOUTH('s'),

/** East direction, represented by {@code 'e'}. */
EAST('e'),

/** West direction, represented by {@code 'o'}. */
WEST('o'),

/** Unknown or invalid direction, represented by {@code 'u'}. */
UNKNOWN('u');

/** Character associated with this compass direction. */
private final char c;

/**

Creates a compass direction associated with the specified character.

@param c character representing the compass direction
*/
Compass(char c) {
this.c = c;
}

/**

Returns the character associated with this compass direction.

@return the direction character
*/
public char getDirection() {
return c;
}

/**

Returns the character representation of this compass direction

as a string.

@return the direction character as a string
*/
@Override
public String toString() {
return "" + c;
}

/**

Converts a character into its corresponding {@code Compass} value.

<p>If the character does not represent a known direction,
{@link #UNKNOWN} is returned.

@param ch character to convert

@return the corresponding compass direction, or {@link #UNKNOWN}

    if the character is not recognized

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
