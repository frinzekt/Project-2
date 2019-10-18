# CITS1001 (Object-Oriented Programming With Java) Project 2 - Slither Link

## Task
Create a visual puzzle game played with a mouse for [slither link](https://en.wikipedia.org/wiki/Slitherlink).

![Slither](https://maybepuzzles.files.wordpress.com/2014/03/slitherlink-liar-example.png)

## Approach
1. Divide into three main parts `AnalyzeSolution`, `Puzzle`, `Slitherlink`
2. Use `SimpleCanvas` to create the interface
3. Use `FileIO` to read from files

### `AnalyzeSolution`
Responsible for analyzing the validity of the solution
1. Check the lines around the square and match it to the number assigned to the square
2. Find the connection (all the lines segments) of each dot to the 4 main directions (top, bottom, left, right)
3. Check all the line segments in the board and match that it has the correct number of matching segments 
4. Trace around the line segment to make sure there is no dividing path or closed path
- it should end where it started for a closed loop

### `Puzzle`
Class for the internal representation of the puzzle

### `Slitherlink`
Class for connecting the `puzzle` and interfacing the representation along with connection to `AnalyzeSolution` upon interaction of user to the screen
