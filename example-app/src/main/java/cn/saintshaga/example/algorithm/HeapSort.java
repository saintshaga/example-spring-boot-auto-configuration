package cn.saintshaga.example.algorithm;

import java.util.PriorityQueue;

public class HeapSort {
    public void heapSort(int[] nums) {
        if(nums == null || nums.length <= 1) {
            return;
        }
        //build heap
        for(int i=nums.length/2; i >= 0; i--) {
            keepMaxHeap(nums, i, nums.length);
        }
        //pop heap each time
        for(int i=nums.length-1; i>0; i--) {
            swap(nums, 0, i);
            keepMaxHeap(nums, 0, i);
        }
    }

    private void keepMaxHeap(int[] nums, int root, int size) {
        if(root >= size) {
            return;
        }
        int i = root;
        while(i < size) {
            int l = left(i), r = right(i);
            int maxIndex = i;
            if(l < size && nums[l] > nums[i]) {
                maxIndex = l;
                swap(nums, i, l);
            }
            if(r < size && nums[r] > nums[i]) {
                maxIndex = r;
                swap(nums, i, r);
            }
            if(maxIndex == i) {
                break;
            }
            i = maxIndex;
        }
    }

    private void swap(int[] nums, int i, int j) {
        if(i == j) {
            return;
        }
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private int left(int i) {
        return 2*i + 1;
    }

    private int right(int i) {
        return 2*i + 2;
    }

    private int root(int i) {
        return (i-1)/2;
    }

    public static void main(String[] args) {
        int[] nums = {50,10,7,3,4,49,20};
        char[][] test = new char[][] {{'1','0','1','0','0'}};
        HeapSort heapSort = new HeapSort();
//        heapSort.maximalSquare(test);
//        heapSort.heapSort(nums);
//        System.out.println(IntStream.of(nums).boxed().collect(Collectors.toList()));
//        System.out.println(heapSort.rob(new int[]{2,7,9,3,1}));
//        Scanner scanner = new Scanner(System.in);
//        int number = scanner.nextInt();
//        for(int i=0; i<number; i++) {
//            int n = scanner.nextInt();
//            int m = scanner.nextInt();
//            int[] cards = new int[n];
//            for(int j=0; j<n; j++) {
//                cards[j] = scanner.nextInt();
//            }
//            System.out.println(heapSort.maxPoints(cards, m));
//        }
        System.out.println(heapSort.maxPoints(new int[]{3, -4, 1, 1, 7}, 2));

        System.out.println(heapSort.longestNumber("abcd12345ed125ss123456789aa123456"));
        PriorityQueue<Integer> queue = new PriorityQueue<>((a1, a2)-> a2-a1);
        queue.add(1);
        queue.add(2);
        queue.add(0);
        System.out.println(queue.peek());
    }

    public String longestNumber(String s) {
        if(s == null || s.length() == 0) {
            return s;
        }
        int c[] = new int[s.length()];
        c[0] = isDigit(s.charAt(0)) ? 1 : 0;
        int longestIndex = 0;
        int maxLength = c[0];
        for(int i=1; i<s.length(); i++) {
            c[i] = isDigit(s.charAt(i)) ? c[i-1] + 1 : 0;
            if(c[i] > maxLength) {
                maxLength = c[i];
                longestIndex = i;
            }
        }
        return maxLength == 0 ? "" : s.substring(longestIndex - maxLength + 1, longestIndex + 1);
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    public int maxPoints(int[] cards, int m) {
        if(m <= 0 || cards == null || cards.length == 0) {
            throw new IllegalArgumentException("Illegal argument.");
        }
        int n = cards.length;
        Integer[] c = new Integer[n];
        int[] b = new int[n];
        c[n-1] = cards[n-1];
        b[n-1] = 1;
        for(int j=n-2; j>=0; j--) {
            int sum = 0;
            for(int i=1; i<=Math.min(m, n-j); i++) {
                sum += cards[j+i-1];
                int currentPoint = sum;
                if(j+i<=n-1 && j+i+b[j+i] <= n-1) {
                    currentPoint += c[j+i+b[j+i]];
                }
                if(c[j] == null || currentPoint > c[j]) {
                    c[j] = currentPoint;
                    b[j] = i;
                }
            }
        }
        return c[0];
    }

    public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] c = new int[m][n];
        int max = 0;
        for(int i=0; i<m; i++) {
            c[m][0] = matrix[m][0] == '1' ? 1 : 0;
            max = Math.max(max, c[m][0]);
        }
        for(int i=1; i<n; i++) {
            c[0][i] = matrix[0][i] == '1' ? 1 : 0;
            max = Math.max(max, c[0][i]);
        }
        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                if(matrix[i][j] == '1' &&
                        rowAreOne(matrix, i, j-c[i-1][j-1], j) &&
                        columnAreOne(matrix, j, i-c[i-1][j-1], i)) {
                    c[i][j] = c[i-1][j-1] + 1;
                    max = Math.max(max, c[i][j]);
                } else {
                    c[i][j] = 0;
                }
            }
        }
        return max;
    }

    private boolean rowAreOne(char[][] matrix, int row, int stc, int edc) {
        for(int i=stc; i<edc; i++) {
            if(matrix[row][i] == '0') {
                return false;
            }
        }
        return true;
    }

    private boolean columnAreOne(char[][] matrix, int col, int str, int edr) {
        for(int i=str; i<edr; i++) {
            if(matrix[i][col] == '0') {
                return false;
            }
        }
        return true;
    }
    public int rob(int[] nums) {
        if(nums==null || nums.length == 0) {
            return 0;
        }
        int[] c = new int[nums.length];
        c[0] = nums[0];
        int max = nums[0], max2 = 0;
        for(int i=1; i<nums.length; i++) {
            int maxBefore = (i >= 2 && i!=nums.length-1) ? Math.max(c[0], max2) : max2;
            c[i] = nums[i] + maxBefore;
            max = Math.max(max, c[i]);
            if(i>=2) {
                max2 = Math.max(max2, c[i-1]);
            }
        }
        for(int i=2; i<nums.length-1; i++) {
            c[0] = Math.max(c[i]+nums[0], c[0]);
        }
        return Math.max(max, c[0]);
    }
}
