public class RGBImage{

    // folder to look for image files
    private String defaultDirectory = "images";    

    // The pixels are stored in three 2D arrays.
    // These are the data structures we will use to perform 
    // the actual image manipulation.
    private int image[][][];
    private int red[][];
    private int green[][];
    private int blue[][];
    
    // Helper will do the 'ugly' Java stuff (saving images to files, etc)
    private Helper myHelper;
    
    
    // Constructor, use an open file dialog to specify image
    public RGBImage() {
        myHelper = new Helper(this, defaultDirectory);
    }
    
    // Constructor, String argument specifies filename of image
    public RGBImage(String filename) {
        myHelper = new Helper(this, defaultDirectory, filename);
    }
    
    // this is called from Helper, when a new image is loaded from a file.  
    // You never need to call it.
    protected void updateArrays(int[][] red, int [][] green, int[][] blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    
    // Call this method to load a new image
    public void load() {
        myHelper.load(defaultDirectory);
    }
    
    // Call this method to save the image to the same file
    public void save() {
        myHelper.save();
    }

    // Call this method to save the image to a different file name
    public void saveAs(String filename) {
        myHelper.saveAs(filename);
    }
    
    // call this in case you closed the viewer, but want to see it again.
    public void show() {
        myHelper.show();
    }
    
    
    // This method should always be called after manipulating 
    // the pixels via the 2D arrays.  
    private void refresh() {
        myHelper.refresh(red, green, blue);
    }
    
    
    
    // Example: flips the image vertically.
    public void flipVertical() {
        int height = red.length;
        int width = red[0].length;
        
        int [][] tempR = new int[height][width];
        int [][] tempB = new int[height][width];
        int [][] tempG = new int[height][width];
        
        // Flip the red channel.
        for (int h=0;h<height;h++) {
            for (int w=0;w<width;w++) {
                tempR[h][w] = red[height-h-1][w];
                tempG[h][w] = green[height-h-1][w];
                tempB[h][w] = blue[height-h-1][w];
            }
        } 
        for (int h=0;h<height;h++) {
            for (int w=0;w<width;w++) {
                red[h][w] = tempR[h][w];
                green[h][w] = tempG[h][w];
                blue[h][w] = tempB[h][w];
            }
        } 
                
        // Always do this after manipulating pixels.
        refresh();
    }

    public void flipHorizontal() {
        int height = red.length;
        int width = red[0].length;
        
        int [][] tempR = new int[height][width];
        int [][] tempB = new int[height][width];
        int [][] tempG = new int[height][width];
        
        for (int h=0;h<height;h++) {
            for (int w=0;w<width;w++) {
                tempR[h][w] = red[h][width-w-1];
                tempG[h][w] = green[h][width-w-1];
                tempB[h][w] = blue[h][width-w-1];
            }
        } 
        for (int h=0;h<height;h++) {
            for (int w=0;w<width;w++) {
                red[h][w] = tempR[h][w];
                green[h][w] = tempG[h][w];
                blue[h][w] = tempB[h][w];
            }
        } 
                
        refresh();
    }

    public void greyScale() {
        int height = red.length;
        int width = red[0].length;
        
        int [][] tempR = new int[height][width];
        int [][] tempB = new int[height][width];
        int [][] tempG = new int[height][width];
        int grey = 0;
        for (int h=0;h<height;h++) {
            for (int w=0;w<width;w++) {
                grey = (red[h][w] + green[h][w] + blue[h][w])/3;
                tempR[h][w] = grey;
                tempG[h][w] = grey;
                tempB[h][w] = grey;
            }
        } 
        for (int h=0;h<height;h++) {
            for (int w=0;w<width;w++) {
                red[h][w] = tempR[h][w];
                green[h][w] = tempG[h][w];
                blue[h][w] = tempB[h][w];
            }
        } 
                
        refresh();
    }

    public void mirror() {
        int height = red.length;
        int width = red[0].length;
        
        int [][] tempR = new int[height][width];
        int [][] tempB = new int[height][width];
        int [][] tempG = new int[height][width];
        for (int h=0;h<height;h++) {
            for (int w=0;w<width/2;w++) {
                tempR[h][width-w-1] = red[h][w];
                tempG[h][width-w-1] = green[h][w];
                tempB[h][width-w-1] = blue[h][w];
            }
        } 
        for (int h=0;h<height;h++) {
            for (int w=0;w<width;w++) {
                red[h][w] = tempR[h][w];
                green[h][w] = tempG[h][w];
                blue[h][w] = tempB[h][w];
            }
        } 
                
        refresh();
    }

    public void blackAndWhite() {
        int height = red.length;
        int width = red[0].length;
        
        int [][] tempR = new int[height][width];
        int [][] tempB = new int[height][width];
        int [][] tempG = new int[height][width];
        for (int h=0;h<height;h++) {
            for (int w=0;w<width;w++) {
                if (red[h][w] + green[h][w] + blue[h][w] > 127) {
                    tempR[h][w] = 255;
                    tempG[h][w] = 255;
                    tempB[h][w] = 255;
                }
                else {
                    tempR[h][w] = 0;
                    tempG[h][w] = 0;
                    tempB[h][w] = 0;
                }
            }
        } 
        for (int h=0;h<height;h++) {
            for (int w=0;w<width;w++) {
                red[h][w] = tempR[h][w];
                green[h][w] = tempG[h][w];
                blue[h][w] = tempB[h][w];
            }
        } 
                
        refresh();
    }

    public void contrastStretch() {
        int height = red.length;
        int width = red[0].length;
        image = new int[3][height][width];
        image[0] = red;
        image[1] = green;
        image[2] = blue;
        int [][][] tempImage = new int [3][height][width];
        int [][] tempR = new int[height][width];
        int [][] tempB = new int[height][width];
        int [][] tempG = new int[height][width];
        tempImage[0] = tempR;
        tempImage[1] = tempG;
        tempImage[2] = tempB;
        int max = image[0][0][0];
        int min = image[0][0][0];
        for (int i = 0; i < 3; i++) {
            for (int h=0;h<height;h++) {
                for (int w=0;w<width;w++) {
                    if (image[i][h][w] > max) {
                        max = image[i][h][w];
                    }if (image[i][h][w] < min) {
                        min = image[i][h][w];
                    }
                }
            } 
            for (int h=0;h<height;h++) {
                for (int w=0;w<width;w++) {
                    tempImage[i][h][w] = 255*(image[i][h][w] - min)/(max-min);
                }
            } 
            for (int h=0;h<height;h++) {
                for (int w=0;w<width;w++) {
                    image[i][h][w] = tempImage[i][h][w];
                }
            } 
        }
        refresh();
    }


}
