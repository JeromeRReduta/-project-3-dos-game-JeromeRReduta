# dos-game

Jerome Reduta
22 Apr. 2019, 11:15 PM

Consulted:
    Prof. Leese's files - sorting and compareTo()

    https://stackoverflow.com/questions/8232980/java-for-loop-skips-runs-twice -
        fixed a bug where for loop ran 2 iterations w/o stopping
        Basically, if you don't put scan.nextLine() after scan.nextAnything()
        (except for scan.nextLine()), scan.nextLine() will automatically
        pick up the end of the last line as its input
        Putting scan.nextLine() fixes that problem

    The Powers That Be - for somehow making me able to work for about 10 hours
        today to finish this project

    My Bed - because I need to go to sleep after this

Edit - 3:00 PM, 6 May

    Fixed two bugs:
         Game-breaking - W/ one player, code would loop endlessly when player's hand was empty
            This was because I kept the index and winner numbers equal to each other - made winner = index + 1 if players[index] won

         Bad but not game-breaking - Center row did not reduce to 2 stacks @ end if none of the stacks were matched
            I had forgotten to add that functionality - added it