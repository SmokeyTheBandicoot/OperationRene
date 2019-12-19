/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationrene.alarm;

import operationrene.utils.RandomUtils;

/**
 *
 * @author Amministratore
 */

// Class for the "Fixed Lasers" map minigame
// All the methods of this class have the same function of the ones in the
// PressureTilesAlarm class
public class FixedLasersAlarm extends MapAlarm {
    
    public FixedLasersAlarm(Dimension dim) {
        super(dim);
    }

    @Override
    public void randomizeS() {
        
        blue = RandomUtils.genRandomInt(0, 5);
        red = RandomUtils.genRandomInt(0, 5);
        
        // Size S rules
        if(red > 2)
            randomizeS1();
        else if(blue < 4)
            randomizeS2();
        else
            randomizeS3();
    }

    @Override
    public void randomizeM() {
        
        blue = RandomUtils.genRandomInt(0, 7);
        red = RandomUtils.genRandomInt(0, 7);
        
        // Size M rules
        if(blue > 1 && blue < 6)
            randomizeM1();
        else if(red < 5)
            randomizeM2();
        else
            randomizeM3();
    }

    @Override
    public void randomizeL() {
        
        blue = RandomUtils.genRandomInt(0, 10);
        red = RandomUtils.genRandomInt(0, 10);
        
        // Size L rules
        if(red > 6)
            randomizeL1();
        else if(blue % 2 == 1)
            randomizeL2();
        else
            randomizeL3();
    }

    @Override
    public void randomizeRS() {
        
        blue = RandomUtils.genRandomInt(0, 6);
        red = RandomUtils.genRandomInt(0, 6);
        
        // Size RS rules
        if(red % 2 == 0)
            randomizeRS1();
        else
            randomizeRS2();
    }

    @Override
    public void randomizeRL() {
        
        blue = RandomUtils.genRandomInt(0, 10);
        red = RandomUtils.genRandomInt(0, 10);
        
        // Size RL rules
        if(blue % 2 == 0)
            randomizeRL1();
        else
            randomizeRL2();
    }
    
    

    public void randomizeS1() {
        
        white = 13 - (blue + red);
        
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                if((i >= 2 && i <= 3 && j <= 3) ||
                   (i >= 0 && i <= 1 && j >= 2))
                    this.getMatrix()[i][j] = 0;
                else
                    updateColoredTile(i, j);
    }

    public void randomizeS2() {
        
        white = 11 - (blue + red);
        
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                if((i >= 1 && i <= 2 && j >= 1) ||
                   (i >= 3 && j <= 2))
                    this.getMatrix()[i][j] = 0;
                else
                    updateColoredTile(i, j);
    }

    public void randomizeS3() {
        
        white = 9 - (blue + red);
        
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                if(i <= 1 || j >= 3)
                    this.getMatrix()[i][j] = 0;
                else
                    updateColoredTile(i, j);
    }

    public void randomizeM1() {
        
        white = 15 - (blue + red);
        
        for(int i = 0; i < 7; i++)
            for(int j = 0; j < 7; j++)
                if((j >= 2 && j <= 3) ||
                   (j >= 5 && j <= 6) ||
                   (i >= 5 && j <= 1) ||
                   (i <= 1 && j == 4))
                    this.getMatrix()[i][j] = 0;
                else
                    updateColoredTile(i, j);
    }

    public void randomizeM2() {
        
        white = 31 - (blue + red);
        
        for(int i = 0; i < 7; i++)
            for(int j = 0; j < 7; j++)
                if((i >= 2 && i <= 3 && j <= 3) ||
                   (i >= 4 && i <= 5 && j >= 2))
                    this.getMatrix()[i][j] = 0;
                else
                    updateColoredTile(i, j);
    }

    public void randomizeM3() {
        
        white = 21 - (blue + red);
        
        for(int i = 0; i < 7; i++)
            for(int j = 0; j < 7; j++)
                if((i <= 1) ||
                   (j <= 1) ||
                   (i <= 3 && j >= 5))
                    this.getMatrix()[i][j] = 0;
                else
                    updateColoredTile(i, j);
    }

    public void randomizeL1() {
        
        white = 61 - (blue + red);
        
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                if((i >= 5 && i <= 6 && j <= 7) ||
                   (i >= 3 && i <= 4 && j >= 6))
                    this.getMatrix()[i][j] = 0;
                else
                    updateColoredTile(i, j);
    }

    public void randomizeL2() {
        
        white = 49 - (blue + red);
        
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                if((i <= 3 && j >= 2 && j <= 3) ||
                   (i >= 2 && i <= 3 && j >= 2 && j <= 6) ||
                   (i >= 2 && i <= 6 && j >= 5 && j <= 6) ||
                   (i >= 5 && i <= 6 && j >= 1 && j <= 6) ||
                   (i >= 5 && j >= 1 && j <= 2))
                    this.getMatrix()[i][j] = 0;
                else
                    updateColoredTile(i, j);
    }

    public void randomizeL3() {
        
        white = 49 - (blue + red);
        
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                if((i <= 2 && j <= 1) ||
                   (i >= 1 && i <= 2 && j <= 6) ||
                   (i >= 1 && i <= 7 && j >= 5 && j <= 6) ||
                   (i >= 6 && i <= 7 && j >= 2 && j <= 6) ||
                   (i >= 6 && j >= 2 && j <= 3))
                    this.getMatrix()[i][j] = 0;
                else
                    updateColoredTile(i, j);
    }

    public void randomizeRS1() {
        
        white = 21 - (blue + red);
        
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 7; j++)
                if(i >= 2 && i <= 3)
                    this.getMatrix()[i][j] = 0;
                else
                    updateColoredTile(i, j);
    }

    public void randomizeRS2() {
        
        white = 15 - (blue + red);
        
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 7; j++)
                if(j <= 1 || i >= 3)
                    this.getMatrix()[i][j] = 0;
                else
                    updateColoredTile(i, j);
    }

    public void randomizeRL1() {
        
        white = 67 - (blue + red);
        
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 11; j++)
                if((i >= 5 && i <= 6 && j <= 7) ||
                   (i <= 6 && j >= 6 && j <= 7) ||
                   (i <= 1 && j >= 6))
                    this.getMatrix()[i][j] = 0;
                else
                    updateColoredTile(i, j);
    }

    public void randomizeRL2() {
        
        white = 69 - (blue + red);
        
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 11; j++)
                if((i <= 1 && j <= 3) ||
                   (i <= 3 && j >= 2 && j <= 3) ||
                   (i >= 2 && i <= 3 && j >= 2 && j <= 7) ||
                   (i >= 2 && i <= 7 && j >= 6 && j <= 7) ||
                   (i >= 6 && i <= 7 && j >= 6))
                    this.getMatrix()[i][j] = 0;
                else
                    updateColoredTile(i, j);
    }
}
