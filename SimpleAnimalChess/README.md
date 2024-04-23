## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies
  Meanwhile, the compiled output files will be generated in the `bin` folder by default.
  > If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

# MY NOTES

Tutorial that I followed: https://www.youtube.com/watch?v=jzCxywhTAUI (currently stopped at 17:17)

Main --> GamePanel --> Board

## Things I learned

### GENERAL

- when you instantiate a class, wherever you instantiate it, you now have access to ALL of that class's public methods??

### SPECIFIC

[1] Notes: changing the x and y (first two values in fillRect) changes where in the jPanel the board can be printed, you can manipulate this to force the board to print in middle of the screen
[ 1 can be found inside Baord.java ]

QUESTION

- do i need to make a tile class?????

TO DOs
[] inside GamePanel, change first player to Black
