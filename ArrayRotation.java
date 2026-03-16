
//See Instrucitons:
// http://apcsa-book.ausdk12.org/apcsa/r/cur/c4/L23_2D_arrays/exercises3.html?topic=c4%2FL23_2D_arrays.topic
public class ArrayRotation{
    private int[][] nums;
    private int[][] temp;
    public ArrayRotation(int n){
        nums = new int [n][n];
        temp = new int [n][n];
        int y = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                nums[r][c] = ++y;
            }
        }
    }

    public void rotate(){
        for (int r = 0; r < nums.length; r++) {
            for (int c = 0; c < nums.length; c++) {
                temp[c][nums.length-r-1] = nums[r][c];
            }
        }
        temp = nums;
    }

    public void print() {
        for (int r = 0; r < nums.length; r++) {
            for (int c = 0; c < nums.length; c++) {
                System.out.print(nums[r][c] + " | ");
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        ArrayRotation ar = new ArrayRotation(5);
        ar.rotate();
        ar.print();
    }
}