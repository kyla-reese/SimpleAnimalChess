## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies
  Meanwhile, the compiled output files will be generated in the `bin` folder by default.
  > If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

# -------------------------------------------------- MY NOTES ------------------------------------------------------

Tutorial that I followed: https://www.youtube.com/watch?v=jzCxywhTAUI (currently stopped at 17:17)
Main --> GamePanel --> Board, Piece, Tile

## Things I learned

### GENERAL

- when you instantiate a class, wherever you instantiate it, you now have access to ALL of that class's public??methods

### SPECIFIC

[1] Notes: When working with Graphics2D --> changing the x and y (first two values in fillRect) changes where in the jPanel the board can be printed, you can manipulate this to force the board to print in middle of the panel rather then starting from the left (ie. make col --> col + 1 or row --> row + 1)

### THE PROCESS

To Dos done:

[ X ] only animals with greater or equal power may eat (does not account for mouse and elephant exception)
[ X ] all pieces currently cannot go on water
[ X ] mouse can swim in water
[ X ] tigers and lions can now jump over water (does not account for mouse yet)
[ X ] mouse in water means lion and tiger cannot jump water
[ X ] a player can't move into its own den (all pieces now -- dapat)
[ X ] mouse can capture elephant
[ X ] elphant can't capture mouse
[ X ] trap functionalites (works for enemy traps only, im deciding that elephant still cant eat mouse tho, and mouse can still capture elphant -- obvs)
[ X ] A mouse on the river may not capture an elephant or another mouse on land
[ X ] A mouse on the river may capture another mouse on the river
[ X ] A mouse on land may not capture a mouse on the river

[ ] no win state (reach Den -- does not matter if all oppenets pieces are gone, must reach den, if all pieces for both players gone, game over <-- don't think this is possible)
[ ] does not switch turns yet
[ ] does not end game (part of win state)
[ ] side panel section of jpanel is still empty

tempPower for the animals????
