import java.util.ArrayList;

public class Main {

    public int insertionCount = 0;
    public int mergeCount = 0;
    public int numCalls = 100;

    public static void main(String[] args){
        Main m = new Main();
        m.analyzeSizes();
    }

    public void analyzeSizes() {
        for (int size = 5; size <= 1000; size += 5) {

            int insertionTotal = 0;
            int mergeTotal = 0;

            for (int call = 0; call < numCalls; call++) {

                insertionCount = 0;
                mergeCount = 0;

                ArrayList<Integer> list = makeList(10, 10, size);
                shuffle(list);
                ArrayList<Integer> copyForMerge = new ArrayList<>(list);

                insertionSort(list);
                mergeSort(copyForMerge, 0, copyForMerge.size() - 1);

                insertionTotal += insertionCount;
                mergeTotal += mergeCount;
            }

            int insertionAvg = insertionTotal / numCalls;
            int mergeAvg = mergeTotal / numCalls;

            System.out.println(size + "," + insertionAvg + "," + mergeAvg);
        }
    }

    public void averageCounts(){
        for (int i = 0; i < numCalls; i++) {
            startSorts();
        }
        System.out.println("Insertion Sort Average Count: " + insertionCount/numCalls);
        System.out.println("Merge Sort Average Count: " + mergeCount/numCalls);
    }

    public void startSorts(){
        ArrayList<Integer> shuffledList = makeShuffledList();
        ArrayList<Integer> copyForMerge = new ArrayList<>(shuffledList);

        ArrayList<Integer> insertionSortedList = insertionSort(shuffledList);
        ArrayList<Integer> mergeSortedList = mergeSort(copyForMerge, 0, copyForMerge.size()-1);

//        System.out.println("Insertion Sort: " + insertionSortedList);
//        System.out.println("Merge Sort: " + mergeSortedList);
//        System.out.println("Insertion Sort Count: " + insertionCount);
//        System.out.println("Merge Sort Count: " + mergeCount);

    }

    /* Make an array list of "len" unique integers.
     * The first integer is "start" and the rest go
     * up by "increment".
     */
    public ArrayList<Integer> makeList(int start, int increment, int len){
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        int num = start;
        for(int i = 1; i<= len; i++) {
            numbers.add(num);
            num += increment;
        }
        return numbers;
    }

    /*
     * Shuffle a list by repeatedly determining two random
     * indexes in the list and swapping the elements at those indexes.
     *
     * Note: there does not need to be a return statement for this
     * method because ArrayLists are objects, and objects pass by reference.
     */
    public void shuffle(ArrayList<Integer> nums){
        for(int i = 0; i< nums.size(); i++) {
            int randomIndex1 = (int)(Math.random()*(nums.size()));
            int randomIndex2 = (int)(Math.random()*(nums.size()));
            int n2 = nums.get(randomIndex2);
            int n1 = nums.remove(randomIndex1);
            nums.add(randomIndex1, n2);
            nums.remove(randomIndex2);
            nums.add(randomIndex2,n1);
        }
    }

    /*
     * This method makes a list of numbers from 10 to 100, counting by 10s, and shuffles it.
     * It is to test the helper methods.
     */
    public ArrayList<Integer> makeShuffledList() {
        ArrayList<Integer> list = makeList(10,10,10);
//        System.out.println(list);
        shuffle(list);
//        System.out.println(list);
        return list;
    }

    public ArrayList<Integer> insertionSort(ArrayList<Integer> nums){
        for(int i = 0;  i < nums.size(); i++){
            int key = nums.get(i);
            insertionCount++;
            int j = i - 1;
            insertionCount++;
            while(j >= 0 && nums.get(j) > key){
                nums.set(j+1, nums.get(j));
                insertionCount++;
                j = j-1;
                insertionCount++;
            }
            nums.set(j+1, key);
            insertionCount++;
        }
        return nums;
    }

    public ArrayList<Integer> mergeSort(ArrayList<Integer> nums, int left, int right){
        if (left < right){
            int middle = left + (right - left) / 2;
            mergeCount++;
            mergeSort(nums, left, middle);
            mergeCount++;
            mergeSort(nums, middle+1, right);
            mergeCount++;

            merge(nums, left, middle, right);
            mergeCount++;
        }
        return nums;
    }

    public void merge(ArrayList<Integer> nums, int left, int middle, int right){
        int subarray1 = middle - left + 1;
        mergeCount++;
        int subarray2 = right - middle;
        mergeCount++;

        ArrayList<Integer> tempL = new ArrayList<>(subarray1);
        mergeCount++;
        ArrayList<Integer> tempR = new ArrayList<>(subarray2);
        mergeCount++;

        for(int i = 0; i<subarray1; i++){
            tempL.add(nums.get(left + i));
            mergeCount++;
        }
        for (int j = 0; j < subarray2; j++){
            tempR.add(nums.get(middle + 1 + j));
            mergeCount++;
        }

        int i = 0;
        mergeCount++;
        int j = 0;
        mergeCount++;

        int k = left;
        mergeCount++;

        while (i < subarray1 && j < subarray2) {
            if (tempL.get(i) <= tempR.get(j)) {
                nums.set(k, tempL.get(i));
                mergeCount++;
                i++;
                mergeCount++;
            } else {
                nums.set(k, tempR.get(j));
                mergeCount++;
                j++;
                mergeCount++;
            }
            k++;
            mergeCount++;
        }

        while (i < subarray1){
            nums.set(k, tempL.get(i));
            mergeCount++;
            i++;
            mergeCount++;
            k++;
            mergeCount++;
        }

        while(j < subarray2){
            nums.set(k, tempR.get(j));
            mergeCount++;
            j++;
            mergeCount++;
            k++;
            mergeCount++;
        }

    }

}
