package operationrene.alarm;

// Class for the "Pressure Tiles" map minigame

import operationrene.utils.RandomUtils;

public class PressureTilesAlarm extends MapAlarm {
    
    public PressureTilesAlarm(Dimension dim) {
        super(dim);
    }
    
    // This function chooses the size S pattern based on the number of blue/red tiles,
    // which is randomly generated
    @Override
    public void randomizeS() {
        
        blue = RandomUtils.genRandomInt(0, 5);
        red = RandomUtils.genRandomInt(0, 5);
        
        // Size S rules
        if(red > 3)
            randomizeS1();
        else if(blue < 3)
            randomizeS2();
        else
            randomizeS3();
    }
    
    // This function chooses the size M pattern based on the number of blue/red tiles,
    // which is randomly generated
    @Override
    public void randomizeM() {
        
        blue = RandomUtils.genRandomInt(0, 7);
        red = RandomUtils.genRandomInt(0, 7);
        
        // Size M rules
        if(red > 4)
            randomizeM1();
        else if(blue < 3)
            randomizeM2();
        else
            randomizeM3();
    }
    
    // This function chooses the size L pattern based on the number of blue/red tiles,
    // which is randomly generated
    @Override
    public void randomizeL() {
        
        blue = RandomUtils.genRandomInt(0, 10);
        red = RandomUtils.genRandomInt(0, 10);
        
        // Size L rules
        if(red > 5)
            randomizeL1();
        else if(blue < 6)
            randomizeL2();
        else
            randomizeL3();
    }
    
    // This function chooses the size RS pattern based on the number of blue/red tiles,
    // which is randomly generated
    @Override
    public void randomizeRS() {
        
        blue = RandomUtils.genRandomInt(0, 6);
        red = RandomUtils.genRandomInt(0, 6);
        
        // Size RS rules
        if(red > blue)
            randomizeRS1();
        else
            randomizeRS2();
    }
    
    // This function chooses the size RL pattern based on the number of blue/red tiles,
    // which is randomly generated
    @Override
    public void randomizeRL() {
        
        blue = RandomUtils.genRandomInt(0, 10);
        red = RandomUtils.genRandomInt(0, 10);
        
        //Size RL rules
        if(blue > red)
            randomizeRL1();
        else
            randomizeRL2();
    }
    
    
    
    // Size S type 1 minigame
    public void randomizeS1() {
        
        white = 13 - (blue + red);
        
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                if((i == 1 && j <= 1) ||
                   (i >= 2 && i <= 3))
                    this.getMatrix()[i][j] = 0;
                else
                    updateColoredTile(i, j);
    }
    
    // Size S type 2 minigame
    public void randomizeS2() {
        
        white = 15 - (blue + red);
        
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                if(i >= 3)
                    this.getMatrix()[i][j] = 0;
                else
                    updateColoredTile(i, j);
    }
    
    // Size S type 3 minigame
    public void randomizeS3() {
        
        white = 11 - (blue + red);
        
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                if((i >= 1 && i <= 2 && j <= 2) || 
                   (i >= 2 && i <= 3 && j >= 1) || 
                   (i == 4 && j >= 3))
                    this.getMatrix()[i][j] = 0;
                else
                    updateColoredTile(i, j);
    }
    
    // Size M type 1 minigame
    public void randomizeM1() {
        
        white = 31 - (blue + red);
        
        for(int i = 0; i < 7; i++)
            for(int j = 0; j < 7; j++)
                if((i >= 2 && i <= 3 && j <= 3) ||
                   (i >= 4 && i <= 5 && j >= 2))
                    this.getMatrix()[i][j] = 0;
                else
                    updateColoredTile(i, j);
    }
    
    // Size M type 2 minigame
    public void randomizeM2() {
        
        white = 27 - (blue + red);
        
        for(int i = 0; i < 7; i++)
            for(int j = 0; j < 7; j++)
                if((i >= 1 && i <= 2 && j <= 2) ||
                   (i >= 3 && i <= 4 && j >= 1 && j <= 3) ||
                   (i >= 5 && j >= 2))
                    this.getMatrix()[i][j] = 0;
                else
                    updateColoredTile(i, j);
    }
    
    // Size M type 3 minigame
    public void randomizeM3() {
        
        white = 25 - (blue + red);
        
        for(int i = 0; i < 7; i++)
            for(int j = 0; j < 7; j++)
                if((i >= 2 && j <= 1) ||
                   (i >= 5 && j <= 5) ||
                   (i >= 3 && i <= 4 && j >= 4))
                    this.getMatrix()[i][j] = 0;
                else
                    updateColoredTile(i, j);
    }
    
    // Size L type 1 minigame
    public void randomizeL1() {
        
        white = 43 - (blue + red);
        
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                if((i >= 1 && i <= 2 && j != 3) ||
                   (i >= 3 && i <= 7 && (j >= 1 && j <= 2 || j >= 4 && j <= 5)) ||
                   (i >= 6 && i <= 7 && j == 3))
                    this.getMatrix()[i][j] = 0;
                else
                    updateColoredTile(i, j);
    }
    
    // Size L type 2 minigame
    public void randomizeL2() {
        
        white = 59 - (blue + red);
        
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                if((i >= 2 && i <= 3 && j <= 2) ||
                   (i >= 4 && i <= 5 && j >= 1))
                    this.getMatrix()[i][j] = 0;
                else
                    updateColoredTile(i, j);
    }
    
    // Size L type 3 minigame
    public void randomizeL3() {
        
        white = 51 - (blue + red);
        
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                if((i >= 7 && i <= 8 && j >= 1 && j <= 5) ||
                   (i >= 3 && j >= 4 && j <= 5) ||
                   (i >= 1 && i <= 4 && j >= 5 && j <= 7) ||
                   (i >= 1 && i <= 2 && j >= 5) ||
                   (i >= 7 && i <= 8 && j == 0))
                    this.getMatrix()[i][j] = 0;
                else
                    updateColoredTile(i, j);
    }
    
    // Size RS type 1 minigame
    public void randomizeRS1() {
        
        white = 17 - (blue + red);
        
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 7; j++)
                if((i >= 2 && i <= 3 && j <= 5) ||
                   (i <= 1 && j >= 4))
                    this.getMatrix()[i][j] = 0;
                else
                    updateColoredTile(i, j);
    }

    // Size RS type 2 minigame
    public void randomizeRS2() {
        
        white = 17 - (blue + red);
        
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 7; j++)
                if((i >= 1 && i <= 2 && j <= 2) ||
                   (i >= 3 && j >= 1))
                    this.getMatrix()[i][j] = 0;
                else
                    updateColoredTile(i, j);
    }
    
    // Size RL type 1 minigame
    public void randomizeRL1() {
        
        white = 41 - (blue + red);
        
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 11; j++)
                if((i <= 1 && j >= 1) ||
                   (i == 2 && j >= 1 && j <= 2) ||
                   (i >= 3 && i <= 4 && j >= 1 && j <= 8) ||
                   (i == 5 && j >= 7 && j <= 8) ||
                   (i >= 6 && i <= 7 && j <= 8))
                    this.getMatrix()[i][j] = 0;
                else
                    updateColoredTile(i, j);
    }

    // Size RL type 2 minigame
    public void randomizeRL2() {
        
        white = 65 - (blue + red);
        
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 11; j++)
                if((i >= 1 && i <= 2 && j <= 2) ||
                   (i >= 3 && i <= 4 && j != 0 && j != 3) ||
                   (i >= 5 && i <= 6 && j >= 1 && j <= 5))
                    this.getMatrix()[i][j] = 0;
                else
                    updateColoredTile(i, j);
    }
}
