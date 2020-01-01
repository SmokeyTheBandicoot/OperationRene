package operationrene.alarm;

import operationrene.utils.RandomUtils;

/**
 * 
 * @author DonPedro
 */
public class PulsatingLasersAlarm extends MapAlarm {
    
    // This array contains the timer's last digits when the laser is off
    // They're different for each configuration
    private int[] offSecs;
    
    public PulsatingLasersAlarm(Dimension dim) {
        super(dim);
    }

    public int[] getOffSecs() {
        return offSecs;
    }

    public void setOffSecs(int[] offSecs) {
        this.offSecs = offSecs;
    }
    
    @Override
    public void randomizeS() {
        
        int config = RandomUtils.genRandomInt(1, 3);
        
        switch(config) {
            case 1:
                randomizeS1();
                break;
            case 2:
                randomizeS2();
                break;
            case 3:
                randomizeS3();
                break;
        }
    }

    @Override
    public void randomizeM() {
        
        int config = RandomUtils.genRandomInt(1, 3);
        
        switch(config) {
            case 1:
                randomizeM1();
                break;
            case 2:
                randomizeM2();
                break;
            case 3:
                randomizeM3();
                break;
        }
    }

    @Override
    public void randomizeL() {
        
        int config = RandomUtils.genRandomInt(1, 3);
        
        switch(config) {
            case 1:
                randomizeL1();
                break;
            case 2:
                randomizeL2();
                break;
            case 3:
                randomizeL3();
                break;
        }
    }

    @Override
    public void randomizeRS() {
        
        int config = RandomUtils.genRandomInt(1, 2);
        
        switch(config) {
            case 1:
                randomizeRS1();
                break;
            case 2:
                randomizeRS2();
                break;
        }
    }

    @Override
    public void randomizeRL() {
        
        int config = RandomUtils.genRandomInt(1, 2);
        
        switch(config) {
            case 1:
                randomizeRL1();
                break;
            case 2:
                randomizeRL2();
                break;
        }
    }

    
    
    public void randomizeS1() {
        
        offSecs = new int[] {1, 2, 3, 7, 8, 9};
        
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                if(i == 0 && j == 4)
                    this.getMatrix()[i][j] = 4;
                else if(j >= 2 && j <= 3)
                    this.getMatrix()[i][j] = 1;
                else
                    this.getMatrix()[i][j] = 0;
    }

    public void randomizeS2() {
        
        offSecs = new int[] {3, 4, 5, 6, 7};
        
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                if(j == 2 || j == 3 || (i <= 2 && j == 0))
                    this.getMatrix()[i][j] = 0;
                else if(i >= 3 && j == 0)
                    this.getMatrix()[i][j] = 4;
                else
                    this.getMatrix()[i][j] = 1;
    }

    public void randomizeS3() {
        
        offSecs = new int[] {0, 1, 2, 4, 5, 6};
        
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                if(j == 0 || j == 3)
                    this.getMatrix()[i][j] = 1;
                else if(i <= 1 && j >= 1 && j <= 2)
                    this.getMatrix()[i][j] = 4;
                else
                    this.getMatrix()[i][j] = 0;
    }

    public void randomizeM1() {
        
        offSecs = new int[] {1, 2, 5, 6};
        
        for(int i = 0; i < 7; i++)
            for(int j = 0; j < 7; j++)
                if((j >= 1 && j <= 2) ||
                   (j >= 4 && i != 3))
                    this.getMatrix()[i][j] = 0;
                else if(i >= 3 && j == 3)
                    this.getMatrix()[i][j] = 4;
                else
                    this.getMatrix()[i][j] = 1;
    }

    public void randomizeM2() {
        
        offSecs = new int[] {2, 3, 8, 9};
        
        for(int i = 0; i < 7; i++)
            for(int j = 0; j < 7; j++)
                if((i >= 5 && j <= 1) ||
                   (i >= 4 && j >= 3 && j <= 5) ||
                   (i <= 2 && j >= 2))
                    this.getMatrix()[i][j] = 0;
                else if((i <= 4 && j <= 1) ||
                        (i >= 4 && j == 6))
                    this.getMatrix()[i][j] = 4;
                else
                    this.getMatrix()[i][j] = 1;
    }

    public void randomizeM3() {
        
        offSecs = new int[] {0, 1, 7, 8};
        
        for(int i = 0; i < 7; i++)
            for(int j = 0; j < 7; j++)
                if((i >= 4 && j == 1) ||
                   (i <= 4 && j == 4))
                    this.getMatrix()[i][j] = 4;
                else if((i <= 3 && j == 1) ||
                        (i == 4 && j >= 2 && j <= 3) ||
                        (i == 4 && j >= 5 && j <= 6) ||
                        (i >= 5 && j == 4))
                    this.getMatrix()[i][j] = 1;
                else
                    this.getMatrix()[i][j] = 0;
    }

    public void randomizeL1() {
        
        offSecs = new int[] {1, 2, 3};
        
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                if(j == 2 || j == 5 || j == 8)
                    this.getMatrix()[i][j] = 1;
                else if((j <= 1 && i != 3 && i != 4) ||
                        (j == 3 && i >= 5) ||
                        (j == 6 && i <= 3))
                    this.getMatrix()[i][j] = 4;
                else
                    this.getMatrix()[i][j] = 0;
    }

    public void randomizeL2() {
        
        offSecs = new int[] {4, 5, 6};
        
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                if((i >= 3 && j <= 5) ||
                   (i <= 6 && j == 8))
                    this.getMatrix()[i][j] = 4;
                else if((i <= 2 && (j == 2 || j == 5)) ||
                        (j >= 6 && j <= 7 && (i == 3 || i == 6)))
                    this.getMatrix()[i][j] = 1;
                else
                    this.getMatrix()[i][j] = 0;
    }

    public void randomizeL3() {
        
        offSecs = new int[] {8, 9, 0};
        
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                if((i <= 3 && j >= 2 && j <= 6) ||
                   (i >= 7 && j >= 2))
                    this.getMatrix()[i][j] = 4;
                else if((i >= 4 && i <= 6 && (j == 2 || j == 5)) ||
                        (i == 2 && j >= 7))
                    this.getMatrix()[i][j] = 1;
                else
                    this.getMatrix()[i][j] = 0;
    }

    public void randomizeRS1() {
        
        offSecs = new int[] {0, 1, 8, 9};
        
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 7; j++)
                if(i <= 2 && (j == 0 || j == 3 || j == 4))
                    this.getMatrix()[i][j] = 4;
                else if(j == 2 || j == 5)
                    this.getMatrix()[i][j] = 1;
                else
                    this.getMatrix()[i][j] = 0;
    }

    public void randomizeRS2() {
        
        offSecs = new int[] {3, 4, 9, 0};
        
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 7; j++)
                if((i <= 2 && j >= 4) ||
                   (i >= 3 && j <= 1))
                    this.getMatrix()[i][j] = 4;
                else if((i <= 2 && j >= 2 && j <= 3) ||
                        (i >= 3 && j == 4))
                    this.getMatrix()[i][j] = 1;
                else
                    this.getMatrix()[i][j] = 0;
    }

    public void randomizeRL1() {
        
        offSecs = new int[] {4, 5};
        
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 11; j++)
                if((i >= 4 && (j == 2 || j == 8)) ||
                   (i <= 4 && j == 5))
                    this.getMatrix()[i][j] = 4;
                else if((i <= 3 && (j == 2 || j == 8)) ||
                        (i == 4 && (j == 3 || j == 4 || j == 6 || j == 7)))
                    this.getMatrix()[i][j] = 1;
                else
                    this.getMatrix()[i][j] = 0;
    }

    public void randomizeRL2() {
        
        offSecs = new int[] {0, 1};
        
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 11; j++)
                if((j <= 3 && (i <= 1 || i >= 7)) ||
                   (i >= 3 && j >= 6 && j <= 8) ||
                   (i == 0 && j >= 9))
                    this.getMatrix()[i][j] = 4;
                else if((i >= 2 && i <= 6 && j == 2) ||
                        (j == 5) ||
                        (i <= 2 && j == 8))
                    this.getMatrix()[i][j] = 1;
                else
                    this.getMatrix()[i][j] = 0;
    }
}
